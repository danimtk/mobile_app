package com.example.dani.app12;

public class Card {
    private Integer id;
    private String line1;
    private String line2;
    private String line3;
    private String share;

    public Card(Integer id, String line1, String line2, String line3) {
        this.id = id;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
    }
    public Integer getId(){
        return id;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }

    public String getBtshare() {
        return share;
    }
}
