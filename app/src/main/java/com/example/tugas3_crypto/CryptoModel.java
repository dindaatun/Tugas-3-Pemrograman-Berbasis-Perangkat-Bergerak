package com.example.tugas3_crypto;

public class CryptoModel {
    private String rank;
    private String name;
    private String symbol;
    private String priceUsd;

    // Constructor
    public CryptoModel(String rank, String name, String symbol, String priceUsd) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
    }

    // Getters
    public String getRank() { return rank; }
    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public String getPriceUsd() { return priceUsd; }
}