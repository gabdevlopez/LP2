package figures;

import java.awt.*;

public class Texto extends Figure{
    protected String texto;
    protected int w = 15;

    public Texto (String texto, int x, int y, int[] rgb) {
        super(rgb, x, y);
        this.texto = texto;
    }

    public void print () {
        System.out.format("Texto de conteúdo %s na posição %d, %d.\n",
            this.texto, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rgb[0], this.rgb[1], this.rgb[2]));
        g2d.setFont(new Font("Arial", 1, this.w));
        g2d.drawString(this.texto,this.x,this.y);
    }

    public boolean clicked (int ex, int ey) {
        return (ex >= this.x && ex<=this.x + (this.w*5) && ey<=this.y && ey>=this.y-(this.w - this.w/3));
    }

    public void tamanho(int w, int h){
        if(this.w >= 15 && this.w <= 100){
            if(this.w == 100){
                w = -5;
            }
            else if(this.w == 15){
                w = +5;
            }
            this.w += w;
        }
    }
}