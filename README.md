# MVVMStarter
This is my MVVM bootstrap project.
The app downloads and parses very simple JSON from http://www.mocky.io and caches it in repository. After that some content
from JSON is displayed on textview using Data-Binding.

Main Libraries used in Project
- Dagger 2
- Room
- Moshi
- Retrofit
- Timber
- Coroutines
- Data Binding


Instead of RXJava 2 that I generally use in my projects I decided to use Coroutines. I might be using them in a wrong way
as I never used them before. There are imports for Navigation and Glide however neither of them is used in this project but I almost
always use those libs in my bigger projects so I left the imports.


