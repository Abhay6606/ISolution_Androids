package com.example.isolution.Model;

public class CallingDetailsGetterSetter {
    String image,name,number,time;

    public CallingDetailsGetterSetter(String image, String name, String number, String time) {
        this.image = image;
        this.name = name;
        this.number = number;
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
