# Project 2 - *New York Times Article Searcher*

**New York Times Article Searcher** is an android app that allows a user to search for articles on web using simple filters. The app utilizes [New York Times Search API](http://developer.nytimes.com/docs/read/article_search_api_v2).

Time spent: **30** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **search for news article** by specifying a query and launching a search. Search displays a grid of image results from the New York Times Search API.
* [x] User can **scroll down to see more articles**. The maximum number of articles is limited by the API search.
* [x] User can tap on any image in results to see the full text of article **full-screen**

The following **optional** features are implemented:

* [x] Used the **ActionBar SearchView** or custom layout as the query box
* [x] User can **share an article link** to their friends or email it to themselves
* [x] Improved the user interface and experiment with image assets and/or styling and coloring
* [x] User can click on "settings" which allows selection of **advanced search options** to filter results
  * [x] User can configure advanced search filters such as:
    * [x] Begin Date (using a date picker)
    * [x] News desk values (Arts, Fashion & Style, Sports)
    * [x] Sort order (oldest or newest)
  * [x] Subsequent searches have any selected filters applied to the results
  * [ ] Uses a lightweight modal dialog for filters rather than an activity
* [x] Replaces the default ActionBar with a [Toolbar](http://guides.codepath.com/android/Using-the-App-ToolBar).
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce view boilerplate.
* [ ] Replace `GridView` with the [RecyclerView](http://guides.codepath.com/android/Using-the-RecyclerView) and the `StaggeredGridLayoutManager` to improve the grid of image results displayed.
* [ ] Use Parcelable instead of Serializable leveraging the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [x] Replace Picasso with [Glide](http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en) for more efficient image rendering.
* [ ] Before an article search is triggered by the user, displays the current top stories of the day by default.
* [ ] Hides the `Toolbar` at the top as the user scrolls down through the results using the [CoordinatorLayout and AppBarLayout](http://guides.codepath.com/android/Using-the-App-ToolBar#reacting-to-scroll).
* [ ] Leverage the popular [GSON library](http://guides.codepath.com/android/Using-Android-Async-Http-Client#decoding-with-gson-library) to streamline the parsing of JSON data and avoid manual parsing.

The following **additional** features are implemented:
Placeholders are shown while article images load.

* [x] List anything else that you can get done to improve the app functionality!

Make the spinner not reset to "Newest" value by default every time you tap/click to view the filter activity.
"Save changes" to each filter so that if other filters are applied after browsing articles, they will display the values they were set to last time filters were applied.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/NDGusCQ.gif' title='NYTimes Search App' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.
Implementing the filters was by far the most difficult task I reached throughout the development of this app. It was difficult to pass around the filter object via intents and modify it so that the url string would append certain strings based on which filters were selected. I logged the status of the url each time I selected different filters and found that it works, but I was never able to make it "look" like they were selected.
Implementing the endless scrolling was also a troubling task because I had never worked with abstract classes before.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
