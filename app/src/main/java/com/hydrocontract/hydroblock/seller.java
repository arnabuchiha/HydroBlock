package com.hydrocontract.hydroblock;

public class seller {
    private String seller,address;
    private int supply;

    public seller(String seller, String address, int supply) {
        this.seller = seller;
        this.address = address;
        this.supply = supply;
    }

    public String getSeller() {
        return seller;
    }

    public String getAddress() {
        return address;
    }

    public int getSupply() {
        return supply;
    }
}
