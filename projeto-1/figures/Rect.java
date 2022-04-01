package figures;

import java.awt.*;

public class Rect extends Figure{
    protected int w = 50;
    protected int h = 50;
    protected int[] rgb2;

    public Rect (int x, int y, int[] rgb, int[] rgb2) {
        super(rgb, x, y);
        this.rgb2 = rgb2;

    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
    
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rgb[0], this.rgb[1], this.rgb[2]));
        g2d.drawRect(this.x,this.y, this.w,this.h);

        g2d.setColor(new Color(this.rgb2[0], this.rgb2[1], this.rgb2[2]));
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }

    public boolean clicked (int mx, int my) { //função para dectar quando o mouse clicar na figura
        return (this.x <= mx && mx<= this.x + this.w && this.y <= my && my <= this.y + this.h);
    }

    public void tamanho(int w, int h){
        if(this.w >= 30 && this.w <= 200){
            if(this.w == 200){
                w = -5;
                h = -5;
            }
            else if(this.w == 30){
                w = +5;
                h = +5;
            }
            
            this.w += w;
            this.h += h;
        }
    }
}