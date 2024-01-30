package com.example.isolution.Model;

public class CallingTeamDetailsGetterSetter {

    String image,name,time,status,position;

    public CallingTeamDetailsGetterSetter(String image, String name, String time, String status, String position) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.status = status;
        this.position = position;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
