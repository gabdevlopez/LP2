package figures;

import java.awt.Graphics;

public abstract class Figure {
    protected int[] rgb;
    protected int[] rgb2;
    public int x;
    public int y;
    public int w = 50, h = 50;
    public boolean focus = false, quad = false, verificaCor = false;
    

    public Figure(int rgb[], int rgb2[], int x, int y) {
        this.rgb = rgb;
        this.rgb2 = rgb2;
        this.x = x;
        this.y = y;
    }

    public void drag(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    public boolean foco(boolean trueFalse){
        return focus = trueFalse;
    }

    public boolean foco2(){
        return quad;
    }
    
    public void tamanho(int num){

        if(num >= 0){
            if(w >= 200){
                w += -5;
            }
            w += 5;
        }
        else{
            if(w <= 30){
                w += 5;
            }
            w += -5;
        }
    }
    
    public void corBorda(int rgb[]){
        this.rgb = rgb;
        verificaCor = true;
    }
    public void corFundo(int rgb2[]){
        this.rgb2 = rgb2;
        verificaCor = true;
    }
    
    public abstract void paint(Graphics g);
    public abstract boolean clicked(int mx, int my);
}
