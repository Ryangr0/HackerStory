package com.webgrip.hackerstory;

import java.util.UUID;

public class Player {
    private String id;
    private int speed;
    private String name;
    public String body = "light";
    public String gender = "male";
    public String face = "001";
    public String hair = "002";
    public String hairColor = "black";
    private int job = 2;

    public Player(){

    }

    public Player(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.speed = 1;
    }

    public Player(String name, int speed){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.speed = speed;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
