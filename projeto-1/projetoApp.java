import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.String;
import java.io.*;
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
    ArrayList<Figure> figs2 = new ArrayList<Figure>();
    ArrayList<Button> buts = new ArrayList<Button>();

    Random A = new Random();
    Point mouse = null;
    Point posMouse = null;
    Figure focus = null;
    Figure figAux = null;
    Button but_focus = null;

    boolean quad = false;
    boolean but_key = false;
    int cont = 0;
    int rgb[];
    int rgb2[];
    int rgb3[] =  new int[]{235, 245, 255};

    ListFrame () {


        buts.add(new Button(1, new Rect(0,0, rgb3, rgb3)));
        buts.add(new Button(2, new Elipse(0,0, rgb3, rgb3)));
        buts.add(new Button(3, new Texto("Text", 0,0, rgb3)));
        buts.add(new Button(4, new poligono(35,178, rgb3, rgb3, true)));
        buts.add(new Button(5, new Texto("fundo", 0,0, rgb3)));
        buts.add(new Button(6, new Texto("borda", 0,0, rgb3)));
        buts.add(new Button(7, new Texto("Delete", 0,0, rgb3)));

        try{
            FileInputStream f = new FileInputStream("proj.svg");
            ObjectInputStream o = new ObjectInputStream(f);
            figs = (ArrayList<Figure>)o.readObject();
            o.close();

        }catch (Exception x){
            System.out.println("ERRO!!! <Em abrir o arquivo>");
        }

        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {

                    try{
                        FileOutputStream f = new FileOutputStream("proj.svg");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();

                    }catch (Exception x){
                        System.out.println("ERRO!!! <Em escrever o arquivo>");
                    }
                    System.exit(0);
                }
            }
        );

        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent evt){
                    mouse = getMousePosition();
                    focus = null;

                    //but_key é uma chave de controle. Ela é acessada no próximo click após clicar em um botão, fazendo assim
                    //surgir a figura do respectivo botão onde aconteceu o click;
                    if(but_key){
                        if(!(mouse.x < 60 && mouse.y < 340)){
                            butFigs(but_focus.idx, mouse.x, mouse.y);
                            but_key = false;
                            but_focus = null;
                        }
                    }

                    for(Button but: buts){
                        if(but.clicked(mouse.x, mouse.y)){
                            but_focus = but;
                            but_key = true;
                        }
                    }

                    for (int i = 0; i < figs.size(); i++){
                        if (figs.get(i).clicked(mouse.x,mouse.y)) {
                            focus = figs.get(i);
                            quad = focus.foco2();
                            cont = i+1;
                        }
                    }
                    if(focus != null){ 
                        figs.remove(focus);
                        figs.add(focus);
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
                        adicionaFig(new Rect(x,y, rgb, rgb2));
                    } 
                    else if (evt.getKeyChar() == 'e' || evt.getKeyChar() == 'E'){
                        adicionaFig(new Elipse(x,y, rgb, rgb2)); 
                    } 
                    else if (evt.getKeyChar() == 'p' || evt.getKeyChar() == 'P'){
                        adicionaFig(new poligono(x,y, rgb, rgb2, false));
                    }
                    else if (evt.getKeyChar() == 't' || evt.getKeyChar() == 'T'){
                        adicionaFig(new Texto("ola galera!", x,y, rgb2)); 
                    }
        
                    if(evt.getKeyChar() == VK_TAB){
                        if(figs.size() > 0){
                            
                            if(cont > figs2.size()-1){
                                cont = 0;
                            }

                            for(Figure fig: figs2){
                                if(fig == figs2.get(cont)){
                                    focus = figs2.get(cont);
                                }
                            }
                            cont++;
                        }
                    } 

                    if (focus != null){

                        if (evt.getKeyChar() == 'c' || evt.getKeyChar() == 'C'){
                            focus.corBorda(rgb);
                        } 
                        else if (evt.getKeyChar() == 'f' || evt.getKeyChar() == 'F'){
                            focus.corFundo(rgb2);
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
                            figs2.remove(focus);
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

    public void butFigs(int idx, int x, int y){

        rgb =  new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)};
        rgb2 =  new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)};

        switch (idx) {
            case 1:
                adicionaFig(new Rect(x,y, rgb, rgb2));
                break;
            case 2:
                adicionaFig(new Elipse(x,y, rgb, rgb2));
                break;
            case 3:
                adicionaFig(new Texto("ola galera!", x,y, rgb2)); 
                break;
            case 4:
                adicionaFig(new poligono(x,y, rgb, rgb2, false));
                break;
            
            default:
                break;
        }
    }

    public void adicionaFig(Figure fig){
        figs.add(fig);
        figs2.add(fig);
        focus = fig;
    }

    public void paint (Graphics g) {
        super.paint(g);

        for(Figure fig: this.figs){
            fig.paint(g, fig == focus);
        }

        for(Button but: this.buts){
            but.paint(g, but == but_focus);
        }
    }
}