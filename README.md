# Urban Dictionary App

This application uses RxJava, Retrofit and OkHttp to pull data from the urban dictionary API and allow users to search for definitions of words and phrases.

<img src="https://github.com/ssmobile/urban-dictionary/blob/master/screenshots/main.png?raw=true" width="200">

## 	Search operation

When the user clicks on the search button, the text that is in the search bar is passed as a parameter to an AsyncTask

The Asynctask instantiates a RetrofitHelper, which contains a Service called ObservableUrbanService and an Observer, which is subscribed to the Service called UrbanObserver.

### ObservableUrbanService

The service is make a query based on a string and get a response from the API in an Observable Object.

We have made the service receive the Observable object as an UrbanResponse type, which is our POJO.

### UrbanObserver

The urban observer contains one UrbanRespnse element, which is where the response from the observable is stored.

When the Observer is finished receiving data, the response is passed into an EventBus via an Event object.

### EventBus

Our MainActivity is registered to the Event. The Event object is being received when the Observer is complete and passed into a recyclerview's adapter.

From there, our recyclerview with all the results is populated.


## Sorting

On the toolbar there is a thumbs up and a thumbs down button.

The RecyclerView Adapter contains a Comparator class, which is made sort a ListItem by thumbsup or thumbsdown, descending, or ascending, depending on its parameters.

Depending on which button is pressed and when it is pressed, the comparator compares ListItems differently. The UI helps the user understand intuitively how the list will be sorted based on the UI.

<img src="https://github.com/ssmobile/urban-dictionary/blob/master/screenshots/up_ascending.png?raw=true" width="200">

<img src="https://github.com/ssmobile/urban-dictionary/blob/master/screenshots/up_descending.png?raw=true" width="200">
