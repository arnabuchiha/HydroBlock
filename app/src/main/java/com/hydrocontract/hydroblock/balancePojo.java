package com.hydrocontract.hydroblock;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class balancePojo {



    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("balance")
    @Expose
    private Double balance;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
