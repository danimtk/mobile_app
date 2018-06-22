package com.example.dani.app12.model;

/**
 * Created by dani on 22/06/18.
 */

public class MyGambi {

    double size_d;
    String size_S;

    public MyGambi() {
    }

    public MyGambi(double size_d, String size_S) {
        this.size_d = size_d;
        this.size_S = size_S;
    }

    public double getSize_d() {
        return size_d;
    }

    public void setSize_d(double size_d) {
        this.size_d = size_d;
    }

    public String getSize_S() {
        return size_S;
    }

    public void setSize_S(String size_S) {
        this.size_S = size_S;
    }
}
