package figures;

import Ivisible.Ivisible;
import java.io.Serializable;

public abstract class Figure implements Ivisible, Serializable{
    public int[] rgb;
    public int[] rgb2;
    public int x;
    public int y;
    public int w = 50, h = 50;
    protected boolean quad = false, verificaCor = false;
    

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
}
