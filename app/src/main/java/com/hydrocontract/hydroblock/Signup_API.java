package com.hydrocontract.hydroblock;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Signup_API {
    String BASE_URL="Type the url";
    @FormUrlEncoded
    @POST("signUp")
    Call<SignupResponse> getResponse(@Field("username")String username,
                                     @Field("email")String email,
                                     @Field("meterId")String meterId);
}
