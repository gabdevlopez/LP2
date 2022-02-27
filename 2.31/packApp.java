import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;

class packApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Rect r1, r2;
    Elipse e1, e2;
    poligono p1, p2;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.black);
        
        int[] vet = {50,200, 100,150, 150,200, 150,250, 50,250};
        int[] vet2 = {200,175, 250,150, 300,175, 300,225, 250,250, 200,225};
        
        this.r1 = new Rect(50,50, 100,30);
        this.r2 = new Rect(200,50, 100,30);
        this.e1 = new Elipse(50,100, 100,30);
        this.e2 = new Elipse(200,100, 100,30);
        this.p1 = new poligono(vet);
        this.p2 = new poligono(vet2);
    }

    public void paint (Graphics g) {
        super.paint(g);

        g.setColor(new Color(255,156,202));
        this.r1.paint(g);
        this.e1.paint(g);
        this.p1.paint(g);

        g.setColor(new Color(25,254,122));
        this.r2.paint(g);
        this.e2.paint(g);
        this.p2.paint(g);
    }
}