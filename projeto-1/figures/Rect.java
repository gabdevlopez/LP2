package figures;

import java.awt.*;

public class Rect extends Figure{
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
        g2d.drawRect(this.x,this.y, this.w,this.w);

        g2d.setColor(new Color(this.rgb2[0], this.rgb2[1], this.rgb2[2]));
        g2d.fillRect(this.x,this.y, this.w,this.w);
    }
}