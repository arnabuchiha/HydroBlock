package com.hydrocontract.hydroblock;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface buyersApi {

    String Base_Url="Url here";
    @POST("buyer/read/0")
    Call<BuyersResponse> getResponse();
}
