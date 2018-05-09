package com.teqi.bcride21.Remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
//ep16 0558
public interface IGoogleAPI {
    @GET
    Call<String>getPath(@Url String url);
}
