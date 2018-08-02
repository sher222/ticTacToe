import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

/**
 * Created by sheryl on 3/30/18.
 */
public class startGame {
    static int turn;
    static int playerTurn;
    static boolean playing;
    static int hi[][] = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    public static void main(String[] args){
        JFrame frame = new JFrame("Test");
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerTurn = 1;
        turn = 1;
        System.out.println("player is x");
//        frame.setBackground(Color.black);
        draw object = new draw();
        frame.add(object);
        playing = true;
        object.drawing(hi);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rawX = e.getX();
                int rawY = e.getY();
                int rowIndex = (int)Math.floor(rawY/200);
                int columnIndex = (int)Math.floor(rawX/200);
//                System.out.println("player turn "+playerTurn+" current turn "+turn);
                if (turn == playerTurn && playing){
                    if (hi[rowIndex][columnIndex] == 0){
//                        System.out.println(Arrays.deepToString(hi));
                        hi[rowIndex][columnIndex] = turn;
                        object.drawing(hi);
                        turn = optimalMove.swap(turn);
                        System.out.println(Arrays.deepToString(hi));
                        //it is now AI's turn
                        System.out.println("SWITCH TURNS");
                        int[][] y = new int[3][3];
                        for (int a = 0; a < 3; a++){
                            for (int b = 0; b < 3; b++){
                                y[a][b] = hi[a][b];
                            }
                        }

                        System.out.println("new turn "+turn);
                        System.out.println("ideal results "+Arrays.toString(getIdealResults(turn)));
                        int[] temp = optimalMove.bestMove(y, turn);
                        if (temp == null){
                            return;
                        }
//                        System.out.println("board after calling function but not updating "+Arrays.deepToString(hi));
                        System.out.println("optimalMove "+Arrays.toString(temp));
//                        System.out.println(hi[temp[0]][temp[1]]);
                        hi[temp[0]][temp[1]] = turn;
//                        System.out.println("board after move" + Arrays.deepToString(hi));
                        object.drawing(hi);
                        turn = optimalMove.swap(turn);
                        if (optimalMove.won(hi) == 2 || optimalMove.won(hi) == 1 ){
                            playing = false;
                        }
                    }

                }




            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    public static int[] getIdealResults(int winner){
        if (winner == 1){ //x won
            int[] ans = {3, 3};
            return ans;
        }
        else{
            int[] ans = {4,4};
            return ans;
        }
    }

}
