package com.pyramid.academy.projects;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    ArrayList<Piece> pieces = new ArrayList<>();
    String color;

    public Player(String c){
        color = c;
        for(int i = 0;i<12;i++){
            Piece p = new Piece();
            p.setColor(color);
            pieces.add(p);
        }
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
    public void takePiece(Piece piece){
        piece.setColor("empty");
    }
    public String getInputPiece(){
        try {
            System.out.println("Please enter the location eg. 1,2 of the piece you" +
                    "would like to move.");
            Scanner userInput = new Scanner(System.in);
            return userInput.nextLine();
        }
        catch(Exception e){
            return "";
        }
    }
    public int strToLocationX(String s){
        String x = s.split(",")[0];
        return Integer.parseInt(x);
    }
    public int strToLocationY(String s){
        String y = s.split(",")[1];
        return Integer.parseInt(y);
    }
    public String getInputLocation(Piece piece){
        try {
            System.out.println("Please enter the location eg. 1,2 of where you" +
                    "would like to move your selected piece.");
            Scanner userInput = new Scanner(System.in);
            return userInput.nextLine();
        }
        catch(Exception e){
            return "";
        }
    }
}
