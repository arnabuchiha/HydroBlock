package com.hydrocontract.hydroblock;

import retrofit2.Call;
import retrofit2.http.POST;

public interface countApi {

    String Base_url="http://13.126.150.180:3000/";
    @POST("seller/count")
    Call<countPojo> getCount();
}
