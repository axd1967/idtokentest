package axd.be.idtokentest;

import android.accounts.Account;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import java.io.IOException;

/**
 * Created by alex on 29/05/2015.
 *
 * demo activity
 *
 */
public class GoogleSignInActivity extends Activity
        implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    /*///////////////////////////
    // OnConnectionFailedListener
    ///////////////////////////*/

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.d(LOGTAG, "connection failed: " + result);

        if (!mIntentInProgress) {
            if (mSignInClicked && result.hasResolution()) {
                // The user has already clicked 'sign-in' so we attempt to resolve all
                // errors until the user is signed in, or they cancel.
                try {
                    result.startResolutionForResult(this, RC_SIGN_IN);
                    mIntentInProgress = true;
                } catch (IntentSender.SendIntentException e) {
                    // The intent was canceled before it was sent.  Return to the default
                    // state and attempt to connect to get an updated ConnectionResult.
                    mIntentInProgress = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Show dialog using GooglePlayServicesUtil.getErrorDialog()
                showErrorDialog(result.getErrorCode());
                mIntentInProgress = true;

            }
        }
    }

    /* Creates a dialog for an error message */
    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(getFragmentManager(), "errordialog");
    }

    /* Called from ErrorDialogFragment when the dialog is dismissed. */
    public void onDialogDismissed() {
        mIntentInProgress = false;
    }

    /* A fragment to display an error dialog */
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() {
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this.getActivity(), REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            ((GoogleSignInActivity) getActivity()).onDialogDismissed();
        }
    }
    /*///////////////////////////
    // ConnectionCallbacks
    ///////////////////////////*/

    /**
     * True if the sign-in button was clicked.  When true, we know to resolve all
     * issues preventing sign-in without waiting.
     */
    private boolean mSignInClicked;

    /**
     * True if we are in the process of resolving a ConnectionResult
     */
    private boolean mIntentInProgress;

    @Override
    public void onConnected(Bundle bundle) {

        // We've resolved any connection errors.  mGoogleApiClient can be used to
        // access Google APIs on behalf of the user.
        mSignInClicked = false;
        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

        getAccessToken();

//        finish();
    }

    private void getAccessToken() {

        String accountName = Plus.AccountApi.getAccountName(mGoogleApiClient);
        Log.d(LOGTAG, "account name: " + accountName);
        new RetrieveTokenTask().execute(accountName);
    }

    @Override
    public void onConnectionSuspended(int i) {

        mGoogleApiClient.connect();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnected()) {
                mGoogleApiClient.reconnect();
            }
        } else if (requestCode == REQ_NEW_LOGIN) {

            // FIXME check responseCode!
            Log.d(LOGTAG, "response code:" + responseCode);

            Bundle extra = intent.getExtras();
            String oneTimeToken = extra.getString("authtoken");

            Log.d(LOGTAG, "after all: onetime: " + oneTimeToken);
        }
    }

    private static final String STATE_RESOLVING_ERROR = "resolving_error";

    /*///////////////////////////
    // GoogleSignInActivity
    ///////////////////////////*/

    private static final String LOGTAG = GoogleSignInActivity.class.getSimpleName();

    @Deprecated
    private static final String _SCOPES = "oauth2:" +
            " https://www.googleapis.com/auth/plus.me" +
            " https://www.googleapis.com/auth/plus.profile.emails.read";

    private static final String SCOPES =
            "audience:server:client_id:" +
            // see also http://android-developers.blogspot.ca/2013/01/verifying-back-end-calls-from-android.html
            PrivateConstants.SCOPE // something similar to "xxxxxxxxxxxxxxxxxx.apps.googleusercontent.com"
            ;

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    private static final int REQ_NEW_LOGIN = 1;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mIntentInProgress);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mIntentInProgress = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)

                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PLUS_LOGIN))
                .addScope(new Scope(Scopes.PLUS_ME))
                .addScope(new Scope("https://www.googleapis.com/auth/plus.profile.emails.read"))

//                .enableAutoManage() ?

                .build();

        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            mGoogleApiClient.connect();
        }
    }

    // Bool to track whether the app is already resolving an error

    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";

    @Override
    protected void onStart() {
        super.onStart();

        if (!mIntentInProgress)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }


    private class RetrieveTokenTask extends AsyncTask<String, Void, String> {

        private final String LOGTAG = RetrieveTokenTask.class.getSimpleName();

        @Override
        protected String doInBackground(String... params) {
            String accountName = params[0];

            String accessToken = null;
            try {
                Account account = new Account(accountName, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);

                accessToken = GoogleAuthUtil.getToken(
                        getApplicationContext(),
                        account,
                        SCOPES
                );

            } catch (IOException e) {
                Log.e(LOGTAG, "IOException: gettoken(): " + e.getMessage(), e);
            } catch (UserRecoverableAuthException e) {
                Log.i(LOGTAG, "recoverable exception: " + e.getMessage(), e);
                startActivityForResult(e.getIntent(), REQ_NEW_LOGIN);

            } catch (GoogleAuthException e) {
                Log.e(LOGTAG, "GoogleAuthException:" + e.getMessage(), e);
            }

            return accessToken;
        }

        @Override
        protected void onPostExecute(String token) {
            super.onPostExecute(token);
            Log.d(LOGTAG, "token: " + token);
            TextView tokenview = (TextView) findViewById(R.id.token);
            tokenview.setText("token: " + token); // paste this into http://jwt.io/ to discover the payload => 'name' is missing. possibly a scope issue?

        }

    }
}
