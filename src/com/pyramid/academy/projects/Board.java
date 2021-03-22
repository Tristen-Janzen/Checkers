package com.pyramid.academy.projects;

public class Board {
        simulateTurn(Player player){
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
