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
    
    public abstract void paint(Graphics g);
    public abstract void tamanho(int w, int h);
    public abstract boolean clicked (int mx, int my);
}
