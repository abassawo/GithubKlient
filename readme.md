# Bitcoin Tracker

1. [Overview](#overview)
1. [Cloning the Project](#cloning-the-project)
1. [Building](#building)
1. [Testing](#testing)
    1. [Unit Tests](#unit-tests)
1. [Architecture](#architecture)
    1. [Model-View-Presenter](#model-view-presenter)
    1. [Base Classes](#base-classes)
    1. [Third Party Libraries](#third-party-libraries)
1. [Design](#design)
    1. [Flows](#flows)
    1. [Error-Handling](#error-handling)

## Overview
The project contains the following components:
-   Ktx (Kotlin synthetic properties for more efficient view binding)
-   Android Design Library
-   Commonly used third party dependencies (RxJava, retrofit, etc))
-   Base MVP framework
-   Other setups to support performance and dependency injection (dagger, custom Application, Timber, etc)

## Cloning the project
To check out the source, simply download/clone the repo

## Building
This project does not require any additional setup or special configurations to build or run.

## Testing
### Unit Tests
Unit tests exist under the "test" directory.

## Architecture
### Model-View-Presenter
The app uses the popular MVP architecture to allow for separation of logic and ease of testing. In this paradigm, all business logic should live inside presenters (but they can delegate some tasks to other classes that are injected as dependencies). Activities and fragment will act as "views", they should not have any logic other than passing the user events to the presenter and displaying the data. There are also Contract classes that specify the communication interface between the views and presenters.

### Base Classes
- `BaseMvpActivity`: Base class for activities. Includes setup for interactions with presenter.
- `BaseMvpFragment`: Basically the same as `BaseMvpActivity`, but for fragments.
- `BasePresenter`: Base class for all presenters. Includes lifecycle setup and common dependencies. Goes along with `BaseMvpActivity` and `BasMvpFragment`.
- `BaseContract`: Includes interfaces that all views and presenters should implement.

### Third Party Libraries
- RxJava/RxAndroid (helps with asynchronous event handling)
- Retrofit/OkHttp (simplifies network requests)
- Timber (better logging tool)
- Mockito (mocks out classes for tests)

## Design
### Flows
- Home screen offers two viewpager tabs with different flows for searching github repos
(either by searching a query from the Search tab or re-searching a persisted query from the History tab)
- From search/suggestions, either enter a new query in the searchview or click an existing suggested terms
- The history page showcases a contrived example of persisting data with SharedPreference, though for more involved data, we could've relied on other approaches like SqlLiteDatabase or Room.
- To delete an item from shared preference, simply swipe left on said item.

###  Error Handling
- Leverage MVP construct to support error-handling at presenter and view level, and testing these interactions
- Use of kotlin sealed class to represent possible loading, success, and error states
- Use custom exception to detect no connectivity to better provide prompts to user when network is unavailable

### Preview
![GithubKlient](https://i.imgur.com/QkasZKrl.png)
![GithubKlient](https://i.imgur.com/4y5elfEl.png)
