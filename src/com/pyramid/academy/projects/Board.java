package com.pyramid.academy.projects;

import java.util.ArrayList;

public class Board {
    Player player1;
    Player player2;
    ArrayList<ArrayList<Piece>> board = new ArrayList<>();

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        int player1Count = 0;
        int player2Count = 0;
        ArrayList<Piece> row1 = new ArrayList<>();
        ArrayList<Piece> row2 = new ArrayList<>();
        ArrayList<Piece> row3 = new ArrayList<>();
        ArrayList<Piece> row4 = new ArrayList<>();
        ArrayList<Piece> row5 = new ArrayList<>();
        ArrayList<Piece> row6 = new ArrayList<>();
        ArrayList<Piece> row7 = new ArrayList<>();
        ArrayList<Piece> row8 = new ArrayList<>();
        for(int i = 0;i<8;i++){
            if(i%2==0)
                row1.add(new Piece("empty"));
            else {
                row1.add(player2.getPieces().get(player2Count));
                player2Count++;
            }
        }
        for(int i = 0;i<8;i++){
            if(i%2==1)
                row2.add(new Piece("empty"));
            else {
                row2.add(player2.getPieces().get(player2Count));
                player2Count++;
            }
        }
        for(int i = 0;i<8;i++){
            if(i%2==0)
                row3.add(new Piece("empty"));
            else {
                row3.add(player2.getPieces().get(player2Count));
                player2Count++;
            }
        }
        for(int i = 0;i<8;i++){
            row4.add(new Piece("empty"));
        }
        for(int i = 0;i<8;i++){
            row5.add(new Piece("empty"));
        }
        for(int i = 0;i<8;i++){
            if(i%2==1)
                row6.add(new Piece("empty"));
            else {
                row6.add(player1.getPieces().get(player1Count));
                player1Count++;
            }
        }
        for(int i = 0;i<8;i++){
            if(i%2==0)
                row7.add(new Piece("empty"));
            else {
                row7.add(player1.getPieces().get(player1Count));
                player1Count++;
            }
        }
        for(int i = 0;i<8;i++){
            if(i%2==1)
                row8.add(new Piece("empty"));
            else {
                row8.add(player1.getPieces().get(player1Count));
                player1Count++;
            }
        }
        board.add(row1);
        board.add(row2);
        board.add(row3);
        board.add(row4);
        board.add(row5);
        board.add(row6);
        board.add(row7);
        board.add(row8);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Piece getPiece(int x, int y){
        return board.get(x).get(y);
    }

    public boolean canMove(Piece p, int x, int y, int xOld, int yOld) {
        //Simple out of bounds check
        if((x>=8)||(x<0)||(y>=8)||(y<0))
            return false;
            //You can't ever move to whitespace.
            //Now checking if the desired location is a whitespace in rows 0,2,4,6.
        else if((x%2==0)&&(y%2==0))
            return false;
            //Now checking if the desired location is a whitespace in rows 1,3,5,7.
        else if((x%2==1)&&(y%2==1))
            return false;
            //You can only move backwards if you are a king, so checking for non-king back movement.
        else if(!p.getKing()){
            if(p.getColor().equals("Red")){
                if((x-xOld)>0)
                    return false;
            }
            else{
                if((x-xOld)<0)
                    return false;
            }
        }
        //You can't ever move to a spot that is occupied by a piece of your same color.
        //Now checking if the desired location has a piece of the same color.
        else if(getPiece(x,y).getColor().equals(p.getColor()))
            return false;
            //You can never move within the same column, must be diagonal movement.
        else if(x==xOld)
            return false;
            //You can never move within the same row, must be diagonal movement.
        else if(y==yOld)
            return false;
            //If you're moving more than 2 columns, the move is illegal, since every jump
            // will be a separate move.
        else if((x-xOld>2)||(x-xOld<(-2)))
            return false;
        return true;
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
                            asciiRow[r] += ((i+1) + " ");

                        }
                        else{
                            asciiRow[r] += "  ";
                        }
                    }
                    if(i % 2 == 0){ // first square in row is white
                        if(j % 2 == 0){ // even jth column is white
                            asciiRow[r] += emptyWhiteSquareAscii[r];
                        }
                        else{ // odd jth column is black
                            String squareLine;
                            if(r == 1){ // put piece initial in middle line, e.g. KR, R, KB, or K
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
                            if(r == 1){ // put piece initial in middle line, e.g. KR, R, KB, or K
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
            squareLine = emptyBlackSquareAscii[1];
        }
        return squareLine;
    }
    public void checkForKing(){
        //TODO
    }
    public boolean checkForWin(){
        //TODO

        return false;
    }
    public void simulateTurn(Player player){
        boolean move = false;
        do{
            Piece p;
            int yOld, yNew;
            int xOld, xNew;
            do {
                String str = player.getInputPiece();
                xOld = player.strToLocationX(str);
                yOld = player.strToLocationY(str);
                p = getPiece(xOld, yOld);
            }while(p.getColor()=="empty");
            String str = player.getInputLocation(p);
            xNew = player.strToLocationX(str);
            yNew = player.strToLocationY(str);
            move = canMove(p,xNew,yNew,xOld,yOld);
        }while(!move);
        checkForKing();
    }
}
