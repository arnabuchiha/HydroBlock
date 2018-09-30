package com.hydrocontract.hydroblock;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface balanceApi {

    String Base_Url="http://13.126.150.180:3000/";
    @FormUrlEncoded
    @POST("getBalance")
    Call<balancePojo> getResponse(@Field("wallet")String wallet);

}
