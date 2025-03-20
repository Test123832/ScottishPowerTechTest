
# ScottishPower Android Tech Test

Tech test by Giacomo De Bacco
The aim of this exercise was to build an Android application with two screens using Kotlin and Compose. MVVM was the preferred choice for the architecture.

Built using *Android Studio Meerkat | 2024.3.*

# Issues & future improvements

- API error handling has not being fully flashed out in the interest of
  time but the basic structure is there

- UiState is passed in the homepage but not used, this can be used in the future to improve the homepage UI/UX in order to better reflect the different cases to the user.

- The details fragment can be imporved similar to the above, adding a better handling of edge cases and potential data issue.

- A nice improvement for the future would be adding paging to the homepage. This wasn't implemented because it's a time consuming task, even if it would be simple to implement with the current code structure. I don't think it was time well spent and it would have added much value to the exercise.

- I've added a few unit tests on the critical parts of the codebase, The coverage can easily be expanded now that it's all setup.