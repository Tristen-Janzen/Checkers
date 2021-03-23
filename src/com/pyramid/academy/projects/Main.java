package com.pyramid.academy.projects;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Red");
        Player player2 = new Player("Black");
        Board board = new Board(player1,player2);
        boolean playerOneTurn = true;
        do{
            if(playerOneTurn)
                board.simulateTurn(board.getPlayer1);
            else
                board.simulateTurn(board.getPlayer2);
            System.out.print(board);
        }while(!board.checkForWin());
    }
}
