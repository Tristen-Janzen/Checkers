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
        return board.get(y).get(x);
    }

    public boolean canMove(Piece p, int x, int y, int xOld, int yOld) {
        //Simple out of bounds check
        if((x>=8)||(x<0)||(y>=8)||(y<0))
            return false;
        //Double check to make sure that the piece is indeed not empty
        if(p.getColor().equals("empty"))
            return false;
        //You can never move to whitespace.
        //Now checking if the desired location is a whitespace in rows 0,2,4,6.
        if((y%2==0)&&(x%2==0))
            return false;
        //Now checking if the desired location is a whitespace in rows 1,3,5,7.
        if((y%2==1)&&(x%2==1))
            return false;
        //You can only move backwards if you are a king, so checking for non-king back movement.
        if(!p.getKing()){
            if(p.getColor().equals("Red")){
                if((y-yOld)>0)
                    return false;
            }
            else{
                if((y-yOld)<0)
                    return false;
            }
        }
        //You can't ever move to a spot that is occupied by a piece.
        //Now checking if the desired location has a piece.
        if(!getPiece(x,y).getColor().equals("empty"))
            return false;
        //You can never move within the same column, must be diagonal movement.
        if(x==xOld)
            return false;
        //You can never move within the same row, must be diagonal movement.
        if(y==yOld)
            return false;
        //If you're moving more than 2 rows or columns, the move is illegal, since every jump
        // will be a separate move.
        if((x-xOld>2)||(x-xOld<(-2)))
            return false;
        if((y-yOld>2)||(y-yOld<(-2)))
            return false;
        //If the place you're moving to is two squares away, that means it must be
        //a jump, so if the desired location is two squares away, check for eligible jump.
        if((y-yOld==2)||(y-yOld==-2)){
            //Piece moving down two squares.
            if(y-yOld==2){
                //Piece moving to the right two squares.
                if((x-xOld)==2) {
                    //c denotes the color of the piece you would have to jump in order to
                    //get to the space desired.
                    String c = getPiece(x-1,y-1).getColor();
                    if(p.getColor().equals("Red")){
                        if((c.equals("Red"))||(c.equals("empty")))
                            return false;
                    }
                    else if(p.getColor().equals("Black")){
                        if((c.equals("Black"))||(c.equals("empty")))
                            return false;
                    }
                }
                //Piece moving to the left two squres.
                else if((x-xOld)==-2) {
                    //c denotes the color of the piece you would have to jump in order to
                    //get to the space desired.
                    String c = getPiece(x-1,y+1).getColor();
                    if(p.getColor().equals("Red")){
                        if((c.equals("Red"))||(c.equals("empty")))
                            return false;
                    }
                    else if(p.getColor().equals("Black")){
                        if((c.equals("Black"))||(c.equals("empty")))
                            return false;
                    }
                }
            }
            //Piece moving up two squares.
            else if(y-yOld==-2){
                //Piece moving to the right two squares.
                if((x-xOld)==2) {
                    //c denotes the color of the piece you would have to jump in order to
                    //get to the space desired.
                    String c = getPiece(x+1,y-1).getColor();
                    if(p.getColor().equals("Red")){
                        if((c.equals("Red"))||(c.equals("empty")))
                            return false;
                    }
                    else if(p.getColor().equals("Black")){
                        if((c.equals("Black"))||(c.equals("empty")))
                            return false;
                    }
                }
                //Piece moving to the left two squres.
                else if((x-xOld)==-2) {
                    //c denotes the color of the piece you would have to jump in order to
                    //get to the space desired.
                    String c = getPiece(x+1,y+1).getColor();
                    if(p.getColor().equals("Red")){
                        if((c.equals("Red"))||(c.equals("empty")))
                            return false;
                    }
                    else if(p.getColor().equals("Black")){
                        if((c.equals("Black"))||(c.equals("empty")))
                            return false;
                    }
                }
            }
        }


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
        String player1Color = player1.getColor();
        String player2Color = player2.getColor();

        // check if there any player 1 pieces on row 1(player 2's side) that are not
        // yet a king. If so, set them to king.
        for(int col = 0; col < board.get(0).size(); col++){
            Piece currentPiece = board.get(0).get(col);
            if(currentPiece.getColor() == player1Color && !currentPiece.getKing()){
                currentPiece.setKing(true);
            }
        }

        // check if there any player 2 pieces on row 8(player 1's side) that are not
        // yet a king. If so, set them to king.
        for(int col = 0; col < board.get(7).size(); col++){
            Piece currentPiece = board.get(0).get(col);
            if(currentPiece.getColor() == player2Color && !currentPiece.getKing()){
                currentPiece.setKing(true);
            }
        }
    }
    public boolean checkForWin(){
        boolean isRedPieceOnBoard = false;
        boolean isBlackPieceOnBoard = false;
        for(int row = 0; row < board.size(); row++){
            for(int col = 0; col < board.get(row).size(); col++){
                Piece currentPiece = board.get(row).get(col);
                if(currentPiece.getColor() == "Red"){
                    isRedPieceOnBoard = true;
                }
                if(currentPiece.getColor() == "Black"){
                    isBlackPieceOnBoard = true;
                }
            }
        }
        if(isRedPieceOnBoard && !isBlackPieceOnBoard) {
            System.out.println("Red player has won!");
            return true; // player won
        }
        if(isBlackPieceOnBoard && !isRedPieceOnBoard){
            System.out.println("Black player has won");
            return true; // player won
        }
        return false; // no one has won
    }
    public void simulateTurn(Player player){
        boolean move = false;
        boolean jump = false, lastTurnJump = false;
        Piece p = new Piece("empty");
        int xOld=0, yOld=0;
        do{
            int yNew;
            int xNew;
            lastTurnJump = jump;
            jump = false;
            do {
                if(!lastTurnJump){
                    p = new Piece("empty");
                }
                while (!p.getColor().equals(player.getColor())) {
                    String str = player.getInputPiece();
                    xOld = player.strToLocationX(str);
                    yOld = player.strToLocationY(str);
                    p = getPiece(xOld, yOld);
                    if(!p.getColor().equals(player.getColor()))
                        System.out.println("You cannot select an enemy piece.");
                }
                String str = player.getInputLocation(p);
                xNew = player.strToLocationX(str);
                yNew = player.strToLocationY(str);
                move = canMove(p, xNew, yNew, xOld, yOld);
            } while (!move);
            //Now that we are sure that the move is valid, we move.
            //If the move is 2 squares, we need to set the square in between
            //to empty, update the new location to have the piece we're moving,
            //and make sure that the old location is no longer that piece.
            //This if takes care of Setting the jumped piece to empty and king to false.
            if((yNew-yOld==2)||(yNew-yOld==-2)) {
                jump = true;
                //Piece moving down
                if(yNew-yOld==2){
                    //Piece moving to the right two squares.
                    if((xNew-xOld)==2) {
                        getPiece(xNew-1,yNew-1).setColor("empty");
                        getPiece(xNew-1,yNew-1).setKing(false);
                    }
                    //Piece moving to the left two squares.
                    else if((xNew-xOld)==-2) {
                        getPiece(xNew-1,yNew+1).setColor("empty");
                        getPiece(xNew-1,yNew-1).setKing(false);
                    }
                }
                else if(yNew-yOld==-2){
                    //Piece moving to the right two squares.
                    if((xNew-xOld)==2) {
                        getPiece(xNew+1,yNew-1).setColor("empty");
                        getPiece(xNew-1,yNew-1).setKing(false);
                    }
                    //Piece moving to the left two squres.
                    else if((xNew-xOld)==-2) {
                        getPiece(xNew+1,yNew+1).setColor("empty");
                        getPiece(xNew-1,yNew-1).setKing(false);
                    }
                }
            }
            //Setting the new board position to the piece that the user picked.
            board.get(yNew).set(xNew,p);
            //Setting the old position to empty
            board.get(yOld).set(xOld, new Piece("empty"));
            if(jump)
                jump = jumpAvailable(p,xNew,yNew);
        }while(jump);

        checkForKing();
    }

    public boolean jumpAvailable(Piece p, int x, int y){
        int xNew, yNew;
        String redOrBlack, other;
        redOrBlack = p.getColor();
        if(redOrBlack.equals("Red"))
            other = "Black";
        else
            other = "Red";
        //We need to iterate through up right, up left, down right, and down left
        //if they're in bounds, checking if there is another avaliable jump
        //So, we need to check if the position in any of the 4 corner of this square
        //contain an enemy, we check to see if there is an empty space on the other side
        //of it. If so, we return true. In any other cases, we return false.
        xNew = x+1;
        yNew = y+1;
        //This move (down right) is legal if the piece is Black or if the piece is a king.
        if((redOrBlack.equals("Black"))||(p.getKing())){
            if (getPiece(xNew, yNew).getColor().equals(other)) {
                if (getPiece(xNew + 1, yNew + 1).getColor().equals("empty")) {
                    return true;
                }
            }
        }
        xNew = x-1;
        yNew = y+1;
        //This move (down left) is legal if the piece is Black or if the piece is a king.
        if((redOrBlack.equals("Black"))||(p.getKing())){
            if (getPiece(xNew, yNew).getColor().equals(other)) {
                if (getPiece(xNew + 1, yNew - 1).getColor().equals("empty")) {
                    return true;
                }
            }
        }
        xNew = x-1;
        yNew = y-1;
        //This move (up left) is legal if the piece is Red or if the piece is a king.
        if((redOrBlack.equals("Red"))||(p.getKing())){
            if (getPiece(xNew, yNew).getColor().equals(other)) {
                if (getPiece(xNew - 1, yNew - 1).getColor().equals("empty")) {
                    return true;
                }
            }
        }
        xNew = x+1;
        yNew = y-1;
        //This move (up right) is legal if the piece is Red or if the piece is a king.
        if((redOrBlack.equals("Red"))||(p.getKing())){
            if (getPiece(xNew, yNew).getColor().equals(other)) {
                if (getPiece(xNew - 1, yNew + 1).getColor().equals("empty")) {
                    return true;
                }
            }
        }
        return false;
    }
}
