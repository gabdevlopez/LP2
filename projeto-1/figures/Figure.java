package figures;

import java.awt.Graphics;

public abstract class Figure {
    protected int[] rgb;
    protected int x, y;
    protected int w = 50, h = 50;

    public Figure(int rgb[], int x, int y) {
        this.rgb = rgb;
        this.x = x;
        this.y = y;
    }
    public void drag(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    public void cor(int [] rgb){
        this.rgb = rgb;
    }

    public boolean clicked (int mx, int my) {
        return (this.x <= mx && mx<= this.x + this.w && this.y <= my && my <= this.y + this.w);
    }

    public void tamanho(int qtd){
        if(this.w >= 30 && this.w <= 200){
            if(this.w == 200){
                qtd = -5;
            }
            else if(this.w == 30){
                qtd = +5;
            }
            this.w += qtd;
        }
    }
    
    public abstract void paint(Graphics g);
}
