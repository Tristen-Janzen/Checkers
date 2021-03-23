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

    @Override
    public String toString() {
        String boardString = "  ";
        for(int i = 0; i < board.size(); i++) { // add row of number at the top
            if(i < board.size()-1){
                boardString += ((i+1) + "      ");
            }
            else{
                boardString += ((i+1) + "      \n");
            }
        }
        for(int i = 0; i < board.size(); i++){ // loop through board row
            String[] asciiRow = new String[] {"", "", ""}; // used to build row of squares using 3 string lines
            for(int j = 0; j < board.get(i).size(); j++){ // loop through columns
                for(int r = 0; r < 3; r++){ // builds board row of 3 character height i.e. three lines per row
                    if(j == 0){ // add row numbers at beginning a board row
                        if(r == 0){
                            asciiRow[0] += ((j+1) + " ");

                        }
                        else{
                            asciiRow[0] += "  ";
                        }
                    }
                    if(i % 2 == 0){ // first square in row is white
                        if(j % 2 == 0){ // even jth column is white
                            asciiRow[r] += emptyWhiteSquareAscii[r];
                        }
                        else{ // odd jth column is black
                            String squareLine;
                            if(r == 2){ // put piece initial in middle line, e.g. KR, R, KB, or K
                                Piece currentPiece = board.get(i).get(j);
                                squareLine = getPieceSquareLine(currentPiece); // helper method returns middle line
                            }
                            else{ // line is empty space (1st or 3rd row)
                                squareLine = emptyBlackSquareAscii[r];
                            }
                            asciiRow[r] += squareLine;
                        }
                    }
                    else{ // first square in row is black
                        if(j % 2 == 0){ // even jth column is black
                            String squareLine;
                            if(r == 2){ // put piece initial in middle line, e.g. KR, R, KB, or K
                                Piece currentPiece = board.get(i).get(j);
                                squareLine = getPieceSquareLine(currentPiece); // helper method returns middle line
                            }
                            else{ // line is empty space (1st or 3rd row)
                                squareLine = emptyBlackSquareAscii[r];
                            }
                            asciiRow[r] += squareLine;
                        }
                        else{ // odd jth column is black
                            asciiRow[r] += emptyWhiteSquareAscii[r];
                        }
                    }
                }
            }
            for(String str: asciiRow){
                boardString += (str + "\n");
            }
        }
        return boardString;
    }

    // represents a white board square across 3 lines
    private static final String[] emptyWhiteSquareAscii = new String[]{"......|", "......|", "______|"};
    // represents a black board square across 3 lines
    private static final String[] emptyBlackSquareAscii = new String[]{"      |", "      |", "______|"};

    // toString helper method
    // returns middle line of a square. may be KR, R, KB, B, or nothing of empty piece
    private String getPieceSquareLine(Piece currentPiece){
        String squareLine = emptyBlackSquareAscii[2]; // default value
        if(currentPiece.getColor() == "Red" && currentPiece.getKing()){ // KR
            squareLine = "  KR  |";
        }
        else if(currentPiece.getColor() == "Red"){
            squareLine = "  R   |";
        }
        else if(currentPiece.getColor() == "Black" && currentPiece.getKing()){
            squareLine = "  KB  |";
        }
        else if(currentPiece.getColor() == "Black"){
            squareLine = "  B   |";
        }
        else{ // empty
            squareLine = emptyBlackSquareAscii[2];
        }
        return squareLine;
    }
}
