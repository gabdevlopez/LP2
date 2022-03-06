import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class listAppElipseRect {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Rect> retan = new ArrayList<Rect>();
    ArrayList<Elipse> oval = new ArrayList<Elipse>();
    Random A = new Random();

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
                        int x = A.nextInt(350);
                        int y = A.nextInt(350);
                        int w = A.nextInt(50);
                        int h = A.nextInt(50);
                        int rgb[] = new int[] {A.nextInt(255), A.nextInt(255), A.nextInt(255)};
                        int rgb2[] = new int[] {A.nextInt(255), A.nextInt(255), A.nextInt(255)};
                        retan.add(new Rect(x,y, w,h, rgb, rgb2));
                        repaint();
                    } 

                    if (evt.getKeyChar() == 'e') {
                        int x = A.nextInt(350);
                        int y = A.nextInt(350);
                        int w = A.nextInt(100);
                        int h = A.nextInt(100);
                        int rgb[] = new int[] {A.nextInt(255), A.nextInt(255), A.nextInt(255)};
                        int rgb2[] = new int[] {A.nextInt(255), A.nextInt(255), A.nextInt(255)};
                        oval.add(new Elipse(x,y, w,h, rgb, rgb2));
                        repaint();
                    } 
                }
            }
        );

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.black);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Elipse fig: this.oval) {
            fig.paint(g);
        }
        for (Rect fig: this.retan) {
            fig.paint(g);
        }
    }
}