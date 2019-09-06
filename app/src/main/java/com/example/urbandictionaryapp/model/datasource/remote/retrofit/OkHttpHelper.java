package com.example.urbandictionaryapp.model.datasource.remote.retrofit;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpHelper {

    public OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    public Response executeSyncOkHttpRequest() throws IOException {
        OkHttpClient client = getClient();

        Request request = new Request.Builder()
                .url("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=wat")
                .get()
                .addHeader("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "5ad1da50afmsh8d0149ca311cdf9p16113ejsna31bc05f7986")
                .build();

        Response response = client.newCall(request).execute();

        return response;
    }

}
