package com.example.urbandictionaryapp.model.datasource.remote.retrofit.services;

import com.example.urbandictionaryapp.model.UrbanResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.urbandictionaryapp.model.datasource.remote.UrlConstants.HOST_DESC1;
import static com.example.urbandictionaryapp.model.datasource.remote.UrlConstants.HOST_DESC2;
import static com.example.urbandictionaryapp.model.datasource.remote.UrlConstants.HOST_TITLE1;
import static com.example.urbandictionaryapp.model.datasource.remote.UrlConstants.HOST_TITLE2;
import static com.example.urbandictionaryapp.model.datasource.remote.UrlConstants.PATH_API;
import static com.example.urbandictionaryapp.model.datasource.remote.UrlConstants.QUERY_TERM;

public interface ObservableUrbanService {

    @Headers({HOST_TITLE1 + ":" + HOST_DESC1,
              HOST_TITLE2 + ":" + HOST_DESC2})
    @GET(PATH_API)
    Observable<UrbanResponse> getResponses(@Query(QUERY_TERM) String term);

}
