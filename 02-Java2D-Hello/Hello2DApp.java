import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(350, 350);
        this.setVisible(true);
    }

    public void paint (Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();
        g2d.setPaint(Color.black); //fundo
        g2d.fillRect(0, 0, w, h);
        
        g2d.setPaint(Color.orange); //linhas
        g2d.drawLine(0,h, w,0);
        g2d.drawLine(0,0, w,h);
        
        g2d.setPaint(Color.red); //circulo centralizado e responsivo (ou seja acompanha o tamanho da tela)
        g2d.drawOval(w/4,h/4, w/2,h/2); 
       
        g2d.setPaint(Color.green); //quadrados responsivos centralizados do ponto de vista do centro do quadrado e nao do ponto de criação
        g2d.drawRect(w/28, (h/2)-(h/10), w/5, h/5); //quadrado da direita 
        g2d.drawRect((w - w/4) + w/70, (h/2)-(h/10), w/5, h/5); //quadrado da esquerda


        g2d.setPaint(Color.MAGENTA);

        g2d.drawLine(175, 330, 200,300); //Coração inferior
        g2d.drawLine(200,300, 180,285);
        g2d.drawLine(180,285, 175,300);
        g2d.drawLine(175,300, 170,285);
        g2d.drawLine(170,285, 150, 300);
        g2d.drawLine(150, 300, 175, 330);

        g2d.drawLine(45, 200, 70,170);  //Coração esquerdo
        g2d.drawLine(70,170, 50,155);
        g2d.drawLine(50,155, 45,170);
        g2d.drawLine(45,170, 40,155);
        g2d.drawLine(40,155, 20, 170);
        g2d.drawLine(20, 170, 45, 200);

        g2d.drawLine(305, 200, 330,170);  //Coração direito
        g2d.drawLine(330,170, 310,155);
        g2d.drawLine(310,155, 305,170);
        g2d.drawLine(305,170, 300,155);
        g2d.drawLine(300,155, 280, 170);
        g2d.drawLine(280, 170, 305, 200);



    }
}