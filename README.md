# README #

The initial commit of this repository satisfied all of HSBC's tech test specifications bar one. Regrettably, the app was so simple that it did little to demonstrate "Good engineering principles". For that reason, I implemented a dependency-injected, reactive MVP arrangement which would otherwise be considered gross over-kill. I felt it was more important to show this than to bog-down the business logic with unecessary Retrofit calls and JSON parsing when a simple html page loaded into a WebView would cover it.

The arrangement is based on the following article:
https://medium.com/@mvarnagiris/reactive-mvp-part-1-b751ce3e3246

With this arrangement, all of the business logic could be written in entirely seperate Presenter and Model modules, not just separate packages, further decoupling the code and mitigating the need for Robolectric - the aim would be to have no business logic in the views to test. Also, the dependancy can be easily maintained as one-way (app relies on presenter, not the other way around) satisfying the 'D' in SOLID. This is demonstrated in my pure-Kotlin module 'presenter'. I have also included a test for the only business logic in the app.

The rx-binding library that the original article depends upon does not include WebView support so I found another library that does:
https://github.com/satoshun/RxWebView

Unfortunately the Maven release of this library is missing the vital event classes which is why there is a custom module 'rxwebview' that adds the required class back in.

The other thing to note is the method of dependancy injection used. It's not Dagger2. In the interests of time conservation I used a Kodein derivative referenced in the following article:
https://proandroiddev.com/dependency-injection-with-kotlin-kodein-koin-3d783745e48d

## Setup ##
I've excluded all of the Studio-specific files so you need to 'Import from Gradle'.