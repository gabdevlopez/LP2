package figures;

import java.awt.Graphics;
import Ivisible.Ivisible;

public abstract class Figure implements Ivisible{
    protected int[] rgb;
    protected int[] rgb2;
    public int x;
    public int y;
    protected int w = 50, h = 50;
    protected boolean focus = false, quad = false, verificaCor = false;
    

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

        if(w >= 200){
            w = 198;
        }
        else if( w <= 30){
            w = 32;
        }
        w += num;
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
