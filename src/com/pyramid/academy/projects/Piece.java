package com.pyramid.academy.projects;

public class Piece {
    private String color;
    private boolean king;
    public Piece(){
        this.king = false;
        this.color = "empty";
    }
    public Piece(String s){
        this.king = false;
        this.color = s;
    }
    public String getColor(){
        return this.color;
    }
    public void setColor(String color){
        this.color = color;
    }
    public boolean getKing(){
        return this.king;
    }
    public void setKing(boolean king){
        this.king = king;
    }
}
