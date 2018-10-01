package com.hydrocontract.hydroblock;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface buyApi {
    String BASE_URL="Type ur url";
    @FormUrlEncoded
    @POST("buyFrom")
    Call<SuccessResponse> getResponse(@Field("sellerAddress")String sellerAddress,
                                      @Field("value")int value,
                                      @Field("wallet")String wallet,
                                      @Field("resident_address")String address);
}
