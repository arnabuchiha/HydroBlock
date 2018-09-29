package com.hydrocontract.hydroblock;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupResponse {

    @SerializedName("walletAddress")
    @Expose
    private String walletAddress;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}