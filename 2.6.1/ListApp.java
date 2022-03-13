import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {

    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random A = new Random();

    int x, y, w, h;
    int rgb[], rgb2[];

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'r') {
                        atribuicoes();
                        figs.add(new Rect(x,y, w,h, rgb, rgb2));
                    } 

                    if (evt.getKeyChar() == 'e') {
                        atribuicoes();
                        figs.add(new Elipse(x,y, w,h, rgb, rgb2));
                    } 
                    if (evt.getKeyChar() == 'p') {
                        atribuicoes();
                        int vetor[] = new int[] {A.nextInt(350), A.nextInt(350), A.nextInt(350),
                                                 A.nextInt(350), A.nextInt(350), A.nextInt(350),
                                                 A.nextInt(350), A.nextInt(350)};
                        figs.add(new poligono(vetor, rgb, rgb2));
                    } 
                    repaint();
                }
            }
        );

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.black);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }

    void atribuicoes(){
        x = A.nextInt(350);
        y = A.nextInt(350);
        w = A.nextInt(100);
        h = A.nextInt(100);
        rgb = new int[] {A.nextInt(255), A.nextInt(255), A.nextInt(255)};
        rgb2 = new int[] {A.nextInt(255), A.nextInt(255), A.nextInt(255)};
    }
}