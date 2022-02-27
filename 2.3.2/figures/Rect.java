package figures;

import java.awt.*;

public class Rect {
    protected int x, y;
    protected int w, h;
    protected int[] rgb = new int[3];

    public Rect (int x, int y, int w, int h, int[] rgb) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rgb = rgb;
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rgb[0], this.rgb[1], this.rgb[2]));
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}