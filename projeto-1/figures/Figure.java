package figures;

import java.awt.Graphics;

public abstract class Figure {
    protected int[] rgb;
    int x, y;

    public Figure(int rgb[], int x, int y) {
        this.rgb = rgb;
        this.x = x;
        this.y = y;
    }
    public abstract void paint(Graphics g);

    public boolean clicked (int mx, int my) { //função para dectar quando o mouse clicar na figura
        return (this.x <= mx && mx<= this.x + 50 && this.y <= my && my <= this.y + 50);
    }
    
    public void drag(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void cor(int [] rgb){
        this.rgb = rgb;
    }

}
