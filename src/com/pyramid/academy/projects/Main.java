package com.pyramid.academy.projects;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Red");
        Player player2 = new Player("Black");
        Board board = new Board(player1,player2);
        boolean playerOneTurn = true;
        System.out.println(board);
        do{
            if(playerOneTurn) {
                System.out.println(String.format("%s's Turn!",player1.getColor()));
                board.simulateTurn(board.getPlayer1());
                playerOneTurn = false;
            }
            else {
                System.out.println(String.format("%s's Turn!",player2.getColor()));
                board.simulateTurn(board.getPlayer2());
                playerOneTurn = true;
            }
            System.out.print(board);
        }while(!board.checkForWin() || board.draw());
    }
}
