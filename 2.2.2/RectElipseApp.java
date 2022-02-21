import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class RectElipseApp {
    public static void main (String[] args) {
        rectElipseframe frame = new rectElipseframe();
        frame.setVisible(true);
    }
}

class rectElipseframe extends JFrame {
    Rect r1, r2, r3, r4, r5, r6, r7, r8, r9;
    Elipse e1, e2, e3, e4, e5;

    rectElipseframe () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.black);
        
        this.r1 = new Rect(125,175, 100,30);
        this.r2 = new Rect(65,205, 100,30);
        this.r3 = new Rect(185,205, 100,30);
        this.r4 = new Rect(235,235, 100,30);
        this.r5 = new Rect(15,235, 100,30);
        this.r6 = new Rect(65,145, 100,30);
        this.r7 = new Rect(185,145, 100,30);
        this.r8 = new Rect(235,115, 100,30);
        this.r9 = new Rect(15,115, 100,30);

        this.e1 = new Elipse(30, 180, 25,25);
        this.e2 = new Elipse(150, 40, 20,20);
        this.e3 = new Elipse(155, 60, 80,80);
        this.e4 = new Elipse(140, 240, 70,70);
        this.e5 = new Elipse(285, 170, 50,50);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g, new Color(56,255,142), new Color(255,56,142));
        this.r2.paint(g, new Color(56,255,142), new Color(255,156,202));
        this.r3.paint(g, new Color(56,255,142), new Color(135,46,142));
        this.r4.paint(g, new Color(56,255,142), new Color(25,254,122));
        this.r5.paint(g, new Color(56,255,142), new Color(15,236,182));
        this.r6.paint(g, new Color(56,255,142), new Color(135,46,142));
        this.r7.paint(g, new Color(56,255,142), new Color(255,156,202));
        this.r8.paint(g, new Color(56,255,142), new Color(15,236,182));
        this.r9.paint(g, new Color(56,255,142), new Color(25,254,122));

        this.e1.paint(g, new Color(56,255,142), new Color(255,56,142));
        this.e2.paint(g, new Color(56,255,142), new Color(135,46,142));
        this.e3.paint(g, new Color(56,255,142), new Color(255,156,202));
        this.e4.paint(g, new Color(56,255,142), new Color(25,254,122));
        this.e5.paint(g, new Color(56,255,142), new Color(15,236,182));
    }
}

class Rect {
    int x, y;
    int w, h;

    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
    
    void paint (Graphics g, Color borda, Color preencher) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(borda);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g.setColor(preencher);
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }   
}

class Elipse {
    int x, y;
    int w, h;

    Elipse (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g, Color borda, Color preencher) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(borda);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g.setColor(preencher);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));

    }
}