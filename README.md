# README #

The initial commit of this repository satisfied all of HSBC's tech test specifications bar one. Regrettably, the app was so simple that it did little to demonstrate "Good engineering principles". For that reason, I implemented a dependency-injected, reactive MVP arrangement which would otherwise be considered gross over-kill. I felt it was more important to show this than to bog-down the business logic with unecessary Retrofit calls and JSON parsing when a simple html page loaded into a WebView would cover it.

The arrangement is based on the following article:
https://medium.com/@mvarnagiris/reactive-mvp-part-1-b751ce3e3246

With this arrangement, all of the business logic could be written in entirely seperate Presenter and Model modules, not just separate packages, further decoupling the code and mitigating the need for Robolectric - the aim would be to have no business logic in the views to test. Also, the dependancy can be easily maintained as one-way (app relies on presenter, not the other way around) satisfying the 'D' in SOLID. This is demonstrated in my pure-Kotlin module 'presenter'. I have also included a unit test in the presenter module for the business logic and some instrumentation tests in the app module that verify the basic presenter interaction and observable supply.

The rx-binding library that the original article depends upon does not include WebView support so I found another library that does:
https://github.com/satoshun/RxWebView

The other thing to note is the method of dependancy injection used. It's not Dagger2. In the interests of time conservation I used Kodein:
http://kodein.org/Kodein-DI/?6.0/android

## Setup ##
I've excluded all of the Studio-specific files so you need to 'Import from Gradle'.