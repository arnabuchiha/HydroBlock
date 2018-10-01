package com.hydrocontract.hydroblock;

import retrofit2.Call;
import retrofit2.http.POST;

public interface countApi {

    String Base_url="Url";
    @POST("seller/count")
    Call<countPojo> getCount();
}
