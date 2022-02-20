import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1, r2, r3, r4, r5, r6, r7, r8, r9;
    PaintFrame () {
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
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint3(g);
        this.r3.paint2(g);
        this.r4.paint4(g);
        this.r5.paint5(g);
        this.r6.paint2(g);
        this.r7.paint3(g);
        this.r8.paint5(g);
        this.r9.paint4(g);
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
    
    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(56,255,142));
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g.setColor(new Color(255,56,142));
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }   
    void paint2 (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(56,255,142));
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g.setColor(new Color(135,46,142));
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }   
    void paint3 (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(56,255,142));
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g.setColor(new Color(255,156,202));
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }   
    void paint4 (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(56,255,142));
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g.setColor(new Color(25,254,122));
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }   
    void paint5 (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(56,255,142));
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g.setColor(new Color(15,236,182));
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }   
}