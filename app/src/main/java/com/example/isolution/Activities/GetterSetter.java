package com.example.isolution.Activities;

public class GetterSetter {

    String name,city,category,status;
    public GetterSetter(String name,String city,String category,String status){
        this.name=name;
        this.category=category;
        this.status=status;
        this.city=city;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
