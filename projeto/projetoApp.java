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
    ArrayList<Button> buts = new ArrayList<Button>();

    Random A = new Random();
    Point mouse = null;
    Point posMouse = null;
    Figure focus = null;
    Button but_focus = null;

    boolean quad = false;
    boolean but_key = false;
    boolean but_key2 = false;
    boolean but_key3 = false;
    int retorno;
    int cont = 0;
    int idxAux = 0;
    int rgb[];
    int rgb2[] =  new int[]{235, 245, 255};
    int rgbBut[] =  new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)};
    int rgbBut2[] = new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)};
    String texto = "Hello world";
    String textoR = "Hello world";
    String options[] = {"borda", "fundo", "ambas"};
    ListFrame () {


        buts.add(new Button(1, new Rect(0,0, rgb2, rgb2)));
        buts.add(new Button(2, new Elipse(0,0, rgb2, rgb2)));
        buts.add(new Button(3, new poligono(35,138, rgb2, rgb2, true)));
        buts.add(new Button(4, new Texto("Texto", 0,0, rgb2)));
        buts.add(new Button(5, new Texto("fundo", 0,0, rgb2)));
        buts.add(new Button(6, new Texto("borda", 0,0, rgb2)));
        buts.add(new Button(7, new Texto("Delete", 0,0, rgb2)));
        buts.add(new Button(8, new Texto("cor", 0,0, rgb2)));

        try{
            FileInputStream f = new FileInputStream("proj.bin");
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
                        FileOutputStream f = new FileOutputStream("proj.bin");
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
                    
                    if(but_key && but_focus != null){
                        if(!(mouse.x < 60 && mouse.y < 380) && but_focus.idx != 4 && but_focus.idx != 8){
                            butFigs(but_focus.idx, mouse.x, mouse.y);
                            but_key = false;
                            but_focus = null;
                        }
                        repaint();
                    }
                
                    for(Button but: buts){
                        
                        if(but.clicked(mouse.x, mouse.y)){
                            but_focus = but;
                            but_key = true;

                            if(but_focus.idx > 3){
                                butFigs(but_focus.idx, mouse.x, mouse.y);
                            }

                            if(evt.getClickCount() == 2 && !evt.isConsumed()){
                                evt.consume();
                                but_focus = null;
                                retorno = 0;
                           }
                        }
                        repaint();
                    }

                    for (int i = 0; i < figs.size(); i++){
                        if (figs.get(i).clicked(mouse.x,mouse.y)) {
                            focus = figs.get(i);
                            quad = focus.foco2();
                            cont = i+1;
                        }
                       repaint();
                    }
                    
                    if(focus != null){ 
                        figs.remove(focus);
                        figs.add(focus);
                    }

                    
                    if(focus == null && but_key3){
                        but_focus = null;
                        but_key3 = false;
                        retorno = 0;
                    }
                    if(but_key2 && focus != null){

                        if(retorno == 1){
                            focus.corFundo(rgbBut);
                            but_focus = buts.get(4);
                            but_key3 = true;
                        }
                        else if(retorno == 2){
                            focus.corBorda(rgbBut2);
                            but_focus = buts.get(5);
                            but_key3 = true;
                        }
                        else if(retorno == 3){

                            figs.remove(focus);
                            but_focus = buts.get(6);
                            focus = null;
                            // but_key3 = true;
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
                                if(focus.x < 65 && focus.y < 380){
                                    dx = 0;
                                    focus.x = 66;
                                }
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
                        figs.add(new poligono(x,y, rgb, rgb2, false));
                    }
                    else if (evt.getKeyChar() == 't' || evt.getKeyChar() == 'T'){
                        figs.add(new Texto(texto, x,y, rgb2)); 
                    }
        
                    if(evt.getKeyChar() == VK_TAB){
                        if(figs.size() > 0){
                            
                            if(cont > figs.size()-1){
                                cont = 0;
                            }

                            for(Figure fig: figs){
                                if(fig == figs.get(cont)){
                                    focus = figs.get(cont);
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
                            focus = null;
                        }
                    }
                    repaint();
                }
            });

        setFocusTraversalKeysEnabled(false);
        this.setTitle("Projeto");
        this.setSize(400, 400);
        this.getContentPane().setBackground(Color.black);

    }

    public void butFigs(int idx, int x, int y){
        
        rgb =  new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)};
        rgb2 =  new int[]{A.nextInt(255), A.nextInt(255), A.nextInt(255)};
        
        if(idx > 4 && idx < 8){
            but_key2 = true;
        }
        else{
            retorno = 0;
            but_key2 = false;
        }

        switch (idx){

            case 1:
                figs.add(new Rect(x,y, rgb, rgb2));
                break;
            case 2:
                figs.add(new Elipse(x,y, rgb, rgb2));
                break;
            case 3:
                figs.add(new poligono(x,y, rgb, rgb2, false));
                break;
            case 4:
                textoR  = JOptionPane.showInputDialog(null, "Digite o texto");
                if(textoR != null && !textoR.isEmpty() && !textoR.trim().equals("")){
                    texto = textoR;
                    figs.add(new Texto(texto, 200,200, rgb2)); 
                    but_focus = null;
                    }
                else{
                    but_focus = null;
                }
                break;
            case 5:
                retorno = 1;
                break;
            case 6:
                retorno = 2;
                break;
            case 7:
                retorno = 3;
                break;
            case 8:

                int option = JOptionPane.showOptionDialog(null, "Voce deseja trocar a cor da borda ou de fundo?", 
                        "troca de cor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                
                if(option == 0 || option == 1 || option == 2){
                    Color novaCor = JColorChooser.showDialog(null, "Por favor escolha a cor", Color.blue);
                    if(option == 0){
                        rgbBut2 = new int[]{novaCor.getRed(), novaCor.getGreen(), novaCor.getBlue()};
                        but_focus = buts.get(5);
                        retorno = 1;
                        but_key3 = false;
                    }
                    else if(option == 1){
                        rgbBut = new int[]{novaCor.getRed(), novaCor.getGreen(), novaCor.getBlue()};
                        but_focus = buts.get(4);
                        retorno = 2;
                        but_key3 = false;
                    }
                    else if(option == 2){
                        rgbBut = new int[]{novaCor.getRed(), novaCor.getGreen(), novaCor.getBlue()};
                        rgbBut2 = new int[]{novaCor.getRed(), novaCor.getGreen(), novaCor.getBlue()};
                    }
                    else{
                        but_focus = null;
                    }
                }
            break;
            
            default:
                break;
        }
        repaint();
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