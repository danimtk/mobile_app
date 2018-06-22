package com.example.dani.app12.model;

/**
 * Created by dani on 20/06/18.
 */

public class MyFile {

    private Integer id;
    private String name;

    public MyFile () {

    }

    public MyFile(Integer id) {
        this.id = id;
    }

    public MyFile(String name) {
        this.name = name;
    }

    public MyFile(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyFile myFile = (MyFile) o;

        return name != null ? name.equals(myFile.name) : myFile.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
