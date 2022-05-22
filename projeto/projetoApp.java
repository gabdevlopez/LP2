import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.String;
import javax.swing.JFileChooser;
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
    String texto = "Hello world!";
    String textoR = "Hello world!";
    String options[] = {"Borda", "Fundo", "Ambas"};
    String options2[] = {"Sim", "Nao"};


    ListFrame(){

        buts.add(new Button(1, new Rect(0,0, rgb2, rgb2)));
        buts.add(new Button(2, new Elipse(0,0, rgb2, rgb2)));
        buts.add(new Button(3, new poligono(35,138, rgb2, rgb2, true)));
        buts.add(new Button(4, new Texto("Texto", 0,0, rgb2)));
        buts.add(new Button(5, new Texto("Fundo", 0,0, rgb2)));
        buts.add(new Button(6, new Texto("Borda", 0,0, rgb2)));
        buts.add(new Button(7, new Texto("Delete", 0,0, rgb2)));
        buts.add(new Button(8, new Texto("Cor", 0,0, rgb2)));
        buts.add(new Button(9, new Texto("DALL", 0,0, rgb2)));

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
                        if(!(mouse.x < 60 && mouse.y < 425) && but_focus.idx != 4 && but_focus.idx != 8){
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
                            if(idxAux != but.idx){
                                but_key3 = false;
                                retorno = 0;
                            }
                           idxAux = but.idx;
                        }
                        repaint();
                    }

                    for (int i = 0; i < figs.size(); i++){
                        if (figs.get(i).clicked(mouse.x,mouse.y)) {
                            focus = figs.get(i);
                            quad = focus.foco2();
                            cont = i+1;

                            if((mouse.x < 55 && mouse.x > 10 && mouse.y < 425) && but_key){
                                focus = null;
                            }
                        }
                       repaint();
                    }
                    
                    if(focus != null){ 
                        figs.remove(focus);
                        figs.add(focus);
                    }
                
                    if(focus == null && but_key3 || but_focus == null && focus == null){
                        but_focus = null;
                        but_key2 = false;
                        but_key3 = false;
                        retorno = 0;
                    }

                    if(but_key2 && focus != null){

                        if(retorno == 1){
                            focus.corFundo(rgbBut);
                            if(focus.getClass().getSimpleName().equals("Texto")) focus.corBorda(rgbBut);;
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
                            but_key3 = true;
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
                                // if(focus.x < 65 && focus.y < 425){
                                //     dx = 0;
                                //     focus.x = 66;
                                // }
                            
                                focus.drag(dx, dy);
                            }
                        }
                        mouse = evt.getPoint();
                        repaint();
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
                    
                    if((posMouse.x < 55 && posMouse.y < 425)){
                        x = 60;
                    }
                        if (evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R'){
                            figs.add(new Rect(x,y, rgb, rgb2));
                        } 
                        else if (evt.getKeyChar() == 'e' || evt.getKeyChar() == 'E'){
                            figs.add(new Elipse(x,y, rgb, rgb2)); 
                        } 
                        else if (evt.getKeyChar() == 'p' || evt.getKeyChar() == 'P'){
                            if((posMouse.x < 55 && posMouse.y < 425)){
                                x = 85;
                            }
                            figs.add(new poligono(x,y, rgb, rgb2, false));
                        }
                        else if (evt.getKeyChar() == 't' || evt.getKeyChar() == 'T'){
                            figs.add(new Texto(texto, x,y, rgb2)); 
                        }
            
                    else if (evt.getKeyChar() == 'c' || evt.getKeyChar() == 'C'){
                        butFigs(8, 0, 0);
                    } 

                    else if(evt.getKeyChar() == VK_TAB){
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
                    if(evt.getKeyChar() == 's'){
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Salvar no formato SVG. Defina o local e o nome do arquivo");   
                        int userSelection = fileChooser.showSaveDialog(null);
                         if (userSelection == JFileChooser.APPROVE_OPTION){
                            File fileToSave = fileChooser.getSelectedFile();
                            SVG(figs, fileToSave.getAbsolutePath());
                        }
                    }
                    

                    if (focus != null){

                        if (evt.getKeyChar() == 'b' || evt.getKeyChar() == 'B'){
                            focus.corBorda(rgb);
                        } 
                        else if (evt.getKeyChar() == 'f' || evt.getKeyChar() == 'F'){
                            focus.corFundo(rgb2);
                            if(focus.getClass().getSimpleName().equals("Texto")) focus.corBorda(rgb2);;
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
        this.setSize(450, 450);
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

                    if(novaCor != null){

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
                            but_focus = null;
                        }
                    }
                    else but_focus = null;
                }
                else but_focus = null;
                break;

            case 9:
                int optionDelete = JOptionPane.showOptionDialog(null, "Voce realmente deseja deletar TODA a sua area de trabalho?", 
                "Delete All", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options2, options2[1]);
                    
                if(optionDelete == 0){
                    figs.removeAll(figs);
                    but_focus =  null;
                } 
                    else but_focus =  null;
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

    public void SVG(ArrayList<Figure> figs, String fileName){
		String format = ".svg";
		try{
      		File Stream = new File(fileName + format );

      		if (!Stream.createNewFile() ) {
      			System.out.println("Arquivo já existe\n");
      		}

      		FileWriter Writer = new FileWriter(fileName+format);
      		Writer.write("<svg width=\"1500\" height=\"1000\">\n");

      		Writer.write(" <rect width=\"100%\" height=\"100%\" fill=\"white\" />\n");

      		for(Figure fig: figs){
				String rgb = String.format("rgb(%d,%d,%d)", fig.rgb[0],  fig.rgb[1], fig.rgb[2]);
				String rgb2 = String.format("rgb(%d,%d,%d)", fig.rgb2[0], fig.rgb2[0], fig.rgb2[0]);
				if(fig instanceof Rect){
					Writer.write("<Rect x=\""+ fig.x +"\" y=\""+ fig.y +"\" width=\""+ fig.w +
					"\" height=\"" + fig.h + "\" style=\"fill:"+ rgb2 +
					";stroke-width:3;stroke:"+ rgb +"\" />\n");
				}
				else if( fig instanceof Texto){
					Writer.write("<Texto x=\""+ fig.x +"\" y=\""+ fig.y +
					"\" rx=\"10\" ry=\"10\" width=\""+ fig.w +
					"\" height=\"" + fig.h + "\" style=\"fill:"+ rgb2 +
					";stroke-width:3;stroke:"+ rgb +"\" />\n");
				}
				else if( fig instanceof Elipse){
					Writer.write("<Elipse cx=\""+ (fig.x + (fig.w*0.5)) +"\" cy=\""+ (fig.y + (fig.h*0.5))+ "\" rx=\""+ (fig.w*0.5) + "\"" +
					" ry=\""+ (fig.h*0.5) + "\""+
					" style=\"fill:"+ rgb2 +
					";stroke-width:3;stroke:"+ rgb +"\" />\n");
				}
				else if( fig instanceof poligono){
                    // this.pontosX = new int[] {x, x+25, x+25, x, x-25, x-25};
                    // this.pontosY = new int[] {y, y+15, y+40, y+55, y+40, y+15};
					String points = String.format("%d,%d %d,%d %d,%d, %d,%d %d,%d, %d,%d", fig.x, fig.y, fig.x+25, fig.y+15, fig.x+25, fig.y+40, fig.x, fig.y+55, fig.x-25, fig.y+40, fig.x-25, fig.y+15);
					Writer.write("<poligono points=\""+ points +"\" "+ "style=\"fill:"+ rgb2 + ";stroke-width:3;stroke:"+ rgb +"\" />\n");	
				}
			}
      		Writer.write("</svg>");
      		Writer.close();
    	}
    	catch (IOException e){
      		System.out.println("Ocorreu um erro.");
      		e.printStackTrace();
    	}
	}
}