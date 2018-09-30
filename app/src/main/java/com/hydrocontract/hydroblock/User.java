package com.hydrocontract.hydroblock;

public class User {
    private String username;
    private String email;
    private String meter_id;
    private String wallet_address;
    public User(){

    }
    public User(String username, String email,String meter_id,String wallet_address){
        this.email=email;
        this.username=username;
        this.meter_id=meter_id;
        this.wallet_address=wallet_address;
    }

    public String getWallet_address() {
        return wallet_address;
    }

    public void setWallet_address(String wallet_address) {
        this.wallet_address = wallet_address;
    }

    public String getMeter_id() {
        return meter_id;
    }

    public void setMeter_id(String meter_id) {
        this.meter_id = meter_id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
