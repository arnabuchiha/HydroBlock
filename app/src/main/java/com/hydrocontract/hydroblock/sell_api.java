package com.hydrocontract.hydroblock;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface sell_api {
    String BASE_URL="Url here";
    @FormUrlEncoded
    @POST("sell")
    Call<SuccessResponse> getResponse(@Field("supply")String supply,
                                     @Field("wallet")String wallet);
}
