import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by sheryl on 3/30/18.
 */

//public class draw extends JPanel{
//    public static JPanel grid = new JPanel(){};
//    static public Graphics2D g2d;
//    @Override
//    public void paint(Graphics g){
//        super.paintComponent(g);
//        g2d = (Graphics2D) g;
//        grid.setBackground(Color.BLACK);
//        grid.setSize(600, 600);
//
//        //horizontal lines
//        Shape line1 = new Line2D.Double(0, 200, 600, 200);
//        Shape line2 = new Line2D.Double(0, 400, 600, 400);
//
//        //vertical lines
//        Shape line3 = new Line2D.Double(200, 0, 200, 600);
//        Shape line4 = new Line2D.Double(400, 0, 400, 600);
//
//        g2d.draw(line1);
//        g2d.draw(line2);
//        g2d.draw(line3);
//        g2d.draw(line4);
//        System.out.println("ran paintComponent");
//    }
//
//}
import java.awt.*;
public class draw extends JPanel{
    public int[][] board;
    public int[][] line = new int[2][2];
    Graphics2D g2;
    public void drawing(int[][] squares){
        board = squares.clone();
        repaint();


    }
    int[][][] possibleCombinations = {
            {{0, 0}, {0, 1}, {0, 2}},
            {{1, 0}, {1, 1}, {1, 2}},
            {{2, 0}, {2, 1}, {2, 2}},
            {{0, 0}, {1, 0}, {2, 0}},
            {{0, 1}, {1, 1}, {2,1}},
            {{0, 2}, {1, 2}, {2, 2}},
            {{0,0},{1,1},{2,2}},
            {{0,2}, {1, 1}, {2, 0}}
    };
    public void winLine(){
        int[][] endPoints = new int[2][2];
        for (int[][] com : possibleCombinations){
            System.out.println(Arrays.deepToString(com));
            if (board[com[0][0]][com[0][1]] == board[com[1][0]][com[1][1]] && board[com[1][0]][com[1][1]] == board[com[2][0]][com[2][1]]){
                endPoints[0] = com[0].clone();
                endPoints[1] = com[2].clone();
                System.out.println("endpoints "+ Arrays.deepToString((endPoints)));

                break;
            }
        }
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                System.out.println("endpoints "+Arrays.deepToString(endPoints));
                System.out.println(endPoints[i][j]);
                System.out.println("line "+Arrays.deepToString(line));
                line[i][j] = endPoints[i][j] * 200+100;

            }
        }
        System.out.println("final line "+Arrays.deepToString(line));




    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
//        g.fillRect(0, 0, 100, 100);
//        Shape line1 = new Line2D.Double(0, 200, 600, 200);
        g2.drawLine(10, 200, 590, 200);
        g2.drawLine(10, 400, 590, 400);
        g2.drawLine(200, 10, 200, 590);
        g2.drawLine(400, 10, 400, 590);




        g2.setColor(Color.BLUE);
//        g2.drawLine(50, 100, 550, 100);
//        //draw O use width and height 150 and x and y are x*100-75

//


//        //1 means x, 2 means O, 0 means empty
        for (int row = 0; row < 3; row++){
            for (int column = 0; column < 3; column++){
               // System.out.println("row "+row+" column "+column);
                if (board[row][column] == 0){
                }
                else if (board[row][column] == 1){
                    g2.setColor(Color.green);
                    g2.drawLine((column+1)*200 - 175, (row+1) * 200 - 175, (column+1)*200-25, (row+1)*200-25);
                    g2.drawLine((column+1)*200 - 175, (row+1)*200-25, (column+1)*200 - 25, (row+1)*200 - 175);
                }
                else{
                    g2.setColor(Color.blue);
                    g2.drawOval((column+1)*200-175, (row+1)*200-175, 150, 150);
                }

            }
        }

        if (optimalMove.won(board) == 2 || optimalMove.won(board) == 1){
            winLine();
            g2.setColor(Color.cyan);
            g2.drawLine(line[0][1], line[0][0], line[1][1], line[1][0]);
        }
    }

}