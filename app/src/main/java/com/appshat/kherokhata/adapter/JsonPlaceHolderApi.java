package com.appshat.kherokhata.adapter;

import com.appshat.kherokhata.Room.ENTITY.TransactionEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("transaction")
    Call<List<TransactionEntity>> getPostes(@Query( "customer_number" ) String mobile);

    @POST("transaction")
    Call<TransactionEntity> createPost(@Body TransactionEntity transactionEntity);

}
