import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.String;
import static java.awt.event.KeyEvent.*;

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

    boolean quad = false;
    int cont = 0;
    int rgb[];
    int rgb2[];
   
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
                            quad = focus.foco2();
                            cont = i;
                        }
                        else {
                            figs.get(i).foco(false);
                        }  
                    }
                    if (focus != null){ 
                        figs.remove(focus);
                        figs.add(focus);
                        for(Figure fig: figs){
                            if(focus == fig){
                                focus.foco(true);
                            }
                            else{
                                fig.foco(false);
                            }
                        }
                    }
                    repaint();
                }
            });

            this.addMouseMotionListener( 
                new MouseAdapter() {
                    public void mouseDragged (MouseEvent evt) {
                        if(quad && focus != null){
                            focus.tamanho(evt.getX() - mouse.x);
                        }
                        else{
                            if(focus != null){
                                int dx = evt.getX() - mouse.x;
                                int dy = evt.getY() - mouse.y;
                                focus.drag(dx, dy);
                            }
                        }
                        repaint();
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
                    rgb =  new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)};
                    rgb2 =  new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)};

                    if (evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R'){
                        figs.add(new Rect(x,y, rgb, rgb2));
                    } 
                    else if (evt.getKeyChar() == 'e' || evt.getKeyChar() == 'E'){
                        figs.add(new Elipse(x,y, rgb, rgb2));
                    } 
                    else if (evt.getKeyChar() == 'p' || evt.getKeyChar() == 'P'){
                        figs.add(new poligono(x,y, rgb, rgb2));
                    }
                    else if (evt.getKeyChar() == 't' || evt.getKeyChar() == 'T'){
                        figs.add(new Texto("ola galera!", x,y, rgb2));
                    }
        
                    if(figs.size() > 0){
                        if(evt.getKeyChar() == VK_TAB){
                            
                            if(cont > figs.size()-1){
                                cont = 0;
                            }

                            for(Figure fig: figs){
                                if(fig == figs.get(cont)){
                                    focus = figs.get(cont);
                                    focus.foco(true);
                                }
                                else{
                                    fig.foco(false);
                                }
                            }
                            cont++;
                        }
                    } 

                    if (focus != null){

                        if (evt.getKeyChar() == 'c' || evt.getKeyChar() == 'C'){
                            focus.corBorda(new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)});
                        } 
                        else if (evt.getKeyChar() == 'f' || evt.getKeyChar() == 'F'){
                            focus.corFundo(new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)});
                        } 
                        else if (evt.getKeyCode() == VK_UP){ 
                            focus.drag(0,-5);   
                        }  
                        else if (evt.getKeyCode() == VK_DOWN){ 
                            focus.drag(0,5);
                        } 
                        else if (evt.getKeyCode() == VK_LEFT){ 
                            focus.drag(-5,0);
                        } 
                        else if (evt.getKeyCode() == VK_RIGHT){ 
                            focus.drag(5,0);
                        } 
                        else if (evt.getKeyCode() == VK_DELETE){ 
                            figs.remove(focus);
                            focus = null;
                        }
                    }
                    repaint();
                }
            });

        setFocusTraversalKeysEnabled(false);
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