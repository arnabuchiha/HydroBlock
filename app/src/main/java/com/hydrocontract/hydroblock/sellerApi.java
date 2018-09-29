package com.hydrocontract.hydroblock;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface sellerApi {

    String Base_Url="http://13.126.150.180:3000/";
    @POST("seller/read/{id}")
    Call<sellerPojo> getSellers(@Path("id") int countId);
}
