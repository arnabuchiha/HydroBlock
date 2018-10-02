package com.hydrocontract.hydroblock;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface sellerApi {

    String Base_Url="Url here";
    @POST("seller/read/{id}")
    Call<sellerPojo> getSellers(@Path("id") int countId);
}
