package figures;

import java.awt.*;

public class Texto extends Figure{
    protected String texto;

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
        g2d.drawString(this.texto,this.x,this.y);
    }

    public boolean clicked (int ex, int ey) {
        if (ex >= this.x && ex<=this.x + 60 && ey<=this.y && ey>=this.y-20){
            return true;
        }
        return false;
    }
}