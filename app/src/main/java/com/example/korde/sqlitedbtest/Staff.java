package com.example.korde.sqlitedbtest;

/**
 * Created by korde on 22/7/16.
 */
public class Staff {
    private int id;
    private String name,ext,place;

    public Staff(int id, String name, String ext, String place){
        this.id = id;
        this.name = name;
        this.ext = ext;
        this.place = place;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt(){
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getPlace(){
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString(){
        return name;
    }
}
