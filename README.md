# idtokentest

## Problem
I'm trying to get `name` from an ID token (JWT), in this case by examining through http://jwt.io (actually this is performed on a different machine. but that is not relevant).

I created the required client ID information on Google Developer Console

```
Client ID for web application
Client ID
xxxxxxxxxxxxx.apps.googleusercontent.com
Email address
xxxx.gserviceaccount.com
Client secret
xxxxxxxx
Redirect URIs

    https://www.example.com/oauth2callback

JavaScript origins

    https://www.example.com

Client ID for Android application
Client ID
yyyyyyyyy.apps.googleusercontent.com
Redirect URIs

    urn:ietf:wg:oauth:2.0:oob
    http://localhost

Package name
axd.be.idtokentest
Certificate fingerprint (SHA1)
xx:xx:xx....
Deep linking
Disabled
```

## Usage
The app asks for a login, then shows the token. Paste the token on jwt.io and observe absence of `name` (a Google specific claim).

## Refs
* StackOverflow: http://stackoverflow.com/questions/31046681/jwt-id-token-without-name-google-claim-with-github-example
* GitHub: https://github.com/axd1967/idtokentest

## More refs
auth
http://openid.net/
http://stackoverflow.com/questions/15592826/proper-android-oauth2-library-framework
https://shkspr.mobi/blog/2012/04/the-oauth-app-anti-pattern/
http://stackoverflow.com/questions/4153022/is-there-a-oauth2-library-for-java-android-already
http://stackoverflow.com/questions/359472/how-can-i-verify-a-google-authentication-api-access-token/429926#429926
http://www.codeproject.com/Articles/890684/Authentication-Using-JSON-Web-Token
https://github.ugent.be/ciao/rep/issues/81
gen
https://en.wikipedia.org/wiki/Authorization_(computer_access_control)
https://en.wikipedia.org/wiki/OAuth
http://www.thebuzzmedia.com/designing-a-secure-rest-api-without-oauth-authentication/
http://aws.amazon.com/articles/1928
https://en.wikipedia.org/wiki/Basic_access_authentication
http://stackoverflow.com/questions/14563155/oauth-2-0-client-id-and-client-secret-exposed-is-it-a-security-issue
goog
http://android-developers.blogspot.be/2012/09/google-play-services-and-oauth-identity.html
http://stackoverflow.com/questions/30534025/how-to-implement-android-restful-client-with-robospice-or-something-like-this
https://developers.google.com/api-client-library/java/apis/oauth2/v2
https://developers.google.com/identity/protocols/OAuth2InstalledApp
https://developers.google.com/identity/choose-auth
http://stackoverflow.com/questions/11631928/authenticating-with-oauth2-for-an-app-and-a-website
https://developers.google.com/android/guides/http-auth
https://developers.google.com/identity/sign-in/
https://developers.google.com/identity/protocols/OAuth2
https://developers.google.com/identity/protocols/OpenIDConnect
https://gist.github.com/ianbarber/9607551
http://stackoverflow.com/questions/15098834/android-using-google-sign-in-to-get-access-token
http://stackoverflow.com/questions/27988146/google-play-services-api-code-outside-of-an-activity
http://stackoverflow.com/questions/27239405/googleauthutil-get-access-token-that-will-provide-an-email-address
http://stackoverflow.com/questions/17713435/android-google-integration-repeated-userrecoverableauthexception
http://stackoverflow.com/questions/17810078/always-getting-userrecoverableauthexception-for-need-permission-even-when-verify
http://stackoverflow.com/questions/27678496/google-oauth-access-token-expiration-in-mvc-app
http://stackoverflow.com/questions/13777842/how-to-get-offline-token-and-refresh-token-and-auto-refresh-access-to-google-api
http://blog.doityourselfandroid.com/2011/08/06/oauth-2-0-flow-android/
http://www.riskcompletefailure.com/2013/03/common-problems-with-google-sign-in-on.html
http://stackoverflow.com/questions/16312784/what-is-the-proper-way-to-validate-google-granted-oauth-tokens-in-a-node-js-serv
http://android-developers.blogspot.ca/2013/01/verifying-back-end-calls-from-android.html
http://stackoverflow.com/questions/14365219/in-a-nutshell-whats-the-difference-from-using-oauth2-request-getauthtoken-and-g
https://stackoverflow.com/questions/22142641/access-to-google-api-googleaccountcredential-usingoauth2-vs-googleauthutil-get
http://stackoverflow.com/questions/30637984/what-does-offline-access-in-oauth-mean
fb
https://developers.facebook.com/docs
http://androidweekly.us2.list-manage2.com/track/click?u=887caf4f48db76fd91e20a06d&id=f770a3a7c5&e=789075637f
http://stackoverflow.com/questions/12065492/rest-api-for-website-which-uses-facebook-for-authentication
github
https://github.com/3pillarlabs/socialauth-android/
https://github.com/google/google-oauth-java-client
--------------------
https://github.com/wuman/android-oauth-client
https://github.com/mttkay/signpost
https://github.com/fernandezpablo85/scribe-java
https://github.com/oauth-io/oauth-android
https://github.com/codepath/android-oauth-handler
https://github.com/3pillarlabs/socialauth
auth
http://www.riskcompletefailure.com/2013/11/client-server-authentication-with-id.html
http://www.matvelloso.com/2014/06/05/authentication-library-choose/
https://code.google.com/p/socialauth/
http://stackoverflow.com/questions/27053388/best-way-to-handle-401-errors-with-spring-android
http://stackoverflow.com/questions/27798718/android-google-oauth-signin-redirect-url
http://stackoverflow.com/questions/22062145/oauth-2-0-authorization-for-linkedin-in-android
http://stackoverflow.com/questions/27696586/linkedin-oauth2-0-redirect-url-for-ios-app-without-http-or-https
http://stackoverflow.com/questions/17629752/best-way-to-allow-users-access-to-your-app-using-their-google-credentials
http://stackoverflow.com/questions/30613216/can-an-oauth-2-0-access-token-be-used-to-authenticate-a-user-in-another-context
http://stackoverflow.com/questions/30535772/implementing-access-token-architecture-in-my-api
http://stackoverflow.com/questions/14864495/using-robospice-is-there-a-way-to-get-the-http-error-code-out-of-an-exception
https://code.google.com/p/socialauth-android/
http://stackoverflow.com/a/429926/605463
http://stackoverflow.com/questions/10959773/android-accountmanager-oauth-getaccesstoken-does-nothing-except-ask-for-pass
http://stackoverflow.com/questions/23808460/jwt-json-web-token-library-for-java
https://github.com/square/okhttp/wiki/Interceptors#application-interceptors
http://stackoverflow.com/questions/22062145/oauth-2-0-authorization-for-linkedin-in-android
https://scotch.io/tutorials/the-anatomy-of-a-json-web-token
