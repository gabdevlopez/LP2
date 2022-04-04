import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.String;

import figures.*;

class projetoApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {

    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random A = new Random();
    Point mouse = null;
    Point posMouse = null;
    Figure focus;

    int rgb[];
    int vermelho[] = new int[] {255, 0, 0};
    int preto[] = new int[] {0, 0, 0};
    int branco[] = new int[]{255,255,255};

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent evt){
                    mouse = getMousePosition();
                    focus = null;

                    for (int i = 0; i < figs.size(); i++){
                        if (figs.get(i).clicked(mouse.x,mouse.y)) {
                            focus = figs.get(i); 
                        }
                        else {
                            if(figs.get(i).getClass().getSimpleName().equals("Texto")){
                                figs.get(i).cor(branco); 
                            }
                            else{
                                figs.get(i).cor(preto); 
                            }
                        }  
                    }
                    if (focus != null){ 
                        figs.remove(focus);
                        figs.add(focus);
                        focus.cor(vermelho);
                    }
                    repaint();
                }
            });

            this.addMouseMotionListener( 
                new MouseAdapter() {
                    public void mouseDragged (MouseEvent evt) {
                        if(focus != null){
                            int dx = evt.getX() - mouse.x;
                            int dy = evt.getY() - mouse.y;
                            focus.drag(dx, dy);
                            repaint();
                        }
                        mouse = evt.getPoint();
                    }
                }
            );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {

                    posMouse = getMousePosition();

                    int x, y;
                    
                    if (posMouse != null) {
                        x = posMouse.x;
                        y = posMouse.y;
                    }else{
                        x = A.nextInt(350);
                        y = A.nextInt(350);
                    }

                    rgb = new int[] {A.nextInt(255), A.nextInt(255), A.nextInt(255)};

                    if (evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R'){
                        figs.add(new Rect(x,y, preto, rgb));
                    } 
                    else if (evt.getKeyChar() == 'e' || evt.getKeyChar() == 'E'){
                        figs.add(new Elipse(x,y, preto, rgb));
                    } 
                    else if (evt.getKeyChar() == 'p' || evt.getKeyChar() == 'P'){
                        figs.add(new poligono(x,y, preto, rgb));
                    }
                    else if (evt.getKeyChar() == 't' || evt.getKeyChar() == 'T'){
                        figs.add(new Texto("ola galera!", x,y, branco));
                    } 

                    if (focus != null){
                        if (evt.getKeyCode() == KeyEvent.VK_UP){ 
                            focus.drag(0,-5);   
                        }  
                        else if (evt.getKeyCode() == KeyEvent.VK_DOWN){ 
                            focus.drag(0,5);
                        } 
                        else if (evt.getKeyCode() == KeyEvent.VK_LEFT){ 
                            focus.drag(-5,0);
                        } 
                        else if (evt.getKeyCode() == KeyEvent.VK_RIGHT){ 
                            focus.drag(5,0);
                        } 
                        else if (evt.getKeyCode() == '=' || evt.getKeyCode() == '+'){ 
                            focus.tamanho(5);
                        } 
                        else if (evt.getKeyCode() == '-'){ 
                            focus.tamanho(-5);
                        }
                        else if (evt.getKeyCode() == KeyEvent.VK_DELETE){ 
                            figs.remove(focus);
                        }
                    }
                    repaint();
                }
            }
        );
        this.setTitle("Projeto-1");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.black);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}