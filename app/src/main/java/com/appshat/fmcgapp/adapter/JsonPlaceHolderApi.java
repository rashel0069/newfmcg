package com.appshat.fmcgapp.adapter;

import com.appshat.fmcgapp.Room.ENTITY.TransactionEntity;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    @GET("transaction")
    Call<List<TransactionEntity>> getPostes(@Query( "customer_number" ) String mobile);

    @POST("transaction")
    Call<TransactionEntity> createPost(@Body TransactionEntity transactionEntity);

}
