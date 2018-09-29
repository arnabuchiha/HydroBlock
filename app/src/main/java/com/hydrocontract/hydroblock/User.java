package com.hydrocontract.hydroblock;

public class User {
    private String username;
    private String email;
    private String meter_id;
    private String wallet_address;
    public User(){

    }
    public User(String username, String email,String meter_id,String waller_address){
        this.email=email;
        this.username=username;
        this.meter_id=meter_id;
        this.wallet_address=waller_address;
    }

    public String getWaller_address() {
        return wallet_address;
    }

    public void setWaller_address(String waller_address) {
        this.wallet_address = waller_address;
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
