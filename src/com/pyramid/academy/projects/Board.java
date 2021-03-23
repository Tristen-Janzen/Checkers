package com.pyramid.academy.projects;

import java.util.ArrayList;

public class Board {
    Player player1;
    Player player2;
    ArrayList<ArrayList<Piece>> board = new ArrayList<>();

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void simulateTurn(Player player){
            boolean move = false;
            do{
                Piece p;
                int y;
                int x;
                do {
                    String str = player.getInputPiece();
                    x = player.strToLocationX(str);
                    y = player.strToLocationY(str);
                    p = getPiece(x, y);
                }while(p.getColor()!="empty");
                String str = player.getInputLocation();
                x = player.strToLocationX(str);
                y = player.strToLocationY(str);
                move = canMove(p,x,y);
            }while(!move);
            checkForKing();

        }
}
