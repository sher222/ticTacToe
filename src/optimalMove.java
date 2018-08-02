import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by sheryl on 3/30/18.
 */
public class optimalMove {

    //return row, column of move
    //3 3 means board is completed and x won
    //4 4 means board is completed and y won
    //5 5 means baord is completed an no one won


    public static int[] bestMove(int[][] current, int turn){
        int[][] board = current.clone();
        LinkedList<int[]> spots = avaliableSpots(board);
//        System.out.println("board "+Arrays.deepToString(board));
        if (won(board.clone()) != 3) {
            return null;
        }



        int[] result = new int[spots.size()];

        for (int index = 0; index < spots.size(); index++){

            int[] move = spots.get(index);
            int[][] newBoard = new int[3][3];
            for (int i = 0; i < 3; i++){
                newBoard[i] = board[i].clone();
            }

            newBoard[move[0]][move[1]] = turn;
            result[index] = play(newBoard.clone(), swap(turn), turn);


        }
//        System.out.println("results "+Arrays.toString(result));
//        System.out.println("moves "+Arrays.deepToString(spots.toArray()));
        int greatest = -10;
        int greatestIndex = 0;
        for (int i = 0; i < result.length; i++){
            if (result[i] > greatest){
                greatest = result[i];
                greatestIndex = i;
            }
        }
        return spots.get(greatestIndex);
    }
    public static int play(final int[][] l, int turn, int supposedToWin){
        int[][] board = l.clone();
//        System.out.println("board passed to play "+Arrays.deepToString(board));
        int temp = won(board);
        if (temp == supposedToWin){
//            System.out.println("temp == supossedToWin");
            return 10;
        }
        if (temp == swap(supposedToWin)){

//            System.out.println("temo == notSuposedToWin");
            return -10;
        }
        if (temp == 0){
            //int[] ret = {5, 5};
//            System.out.println("temp == 0");
            return 0; //tie
        }
        int newTurn = swap(turn);
        LinkedList<int[]> check = avaliableSpots(board);
        int[] values = new int[check.size()];

        for (int a = 0; a<check.size(); a++){
            int[] i = check.get(a);
            int[][] hi = new int[3][3];
            for (int g = 0; g < 3; g++){
                hi[g] = board[g].clone();
            }
//            System.out.println("hi "+Arrays.deepToString(hi));
            hi[i[0]][i[1]] = turn;
            int thisResult = play(hi, newTurn, supposedToWin);
//            System.out.println("this result "+thisResult);

            values[a] = thisResult;

        }
//        System.out.println("turn "+turn+" supposed to win "+supposedToWin+" values "+Arrays.toString(values));

        if (turn == supposedToWin){//we want to maxamize score
            int currentMax = -10;
            for (int i : values){
                if (i > currentMax){
                    currentMax = i;
                }
            }
//            System.out.println("max "+currentMax);
            return currentMax;

        }
        else{//we wanna minimize score
            int currentMin = 10;
            for (int i : values){
                if (i < currentMin){
                    currentMin = i;
                }
            }
//            System.out.println("min "+currentMin);
            return currentMin;
        }




    }


    public static int swap(int og){
        if (og == 1){
            return 2;
        }
        else{
            return 1;
        }
    }
    //0 means no one won 1 means x won 2 means o won 3 means still playing
    public static int won(int[][] board){
        //check rows
        for (int[] i:board){
            if (i[0] == i[1] && i[1] == i[2] && i[2] != 0){
//                System.out.println("rows");
                return i[1];
            }
        }

        //check diagonals
        if (board[0][0] == board[1][1] && board[1][1]== board[2][2] && board[0][0] != 0){
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != 0){

            return board[1][1];

        }

        //check columns
        for (int second = 0; second < 3; second++){
            if (board[0][second] == board[1][second] && board[1][second]==board[2][second] && board[2][second] != 0){
                return board[0][second];
            }
        }
//        System.out.println("c");
        for (int[] i : board){
            for (int c : i){
                //System.out.println(c);
                if (c == 0){

                    return 3;
                }
            }
        }
        return 0;
    }

    //returns linked list of array indexes of available spots
    public static LinkedList<int[]> avaliableSpots(int[][] board){
        LinkedList<int[]> ans = new LinkedList<>();
        for (int i = 0; i < 3; i++){
            for (int c = 0; c < 3; c++){
                if (board[i][c] == 0){
                    int[] temp = {i, c};
                    ans.add(temp);
                }
            }
        }
        return ans;
    }
}
