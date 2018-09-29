package com.hydrocontract.hydroblock;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class sellerPojo {
    @SerializedName("walletAddress")
    @Expose
    private String walletAddress;
    @SerializedName("supply")
    @Expose
    private String supply;
    @SerializedName("username")
    @Expose
    private String username;

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
