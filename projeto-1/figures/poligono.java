
package figures;

import java.awt.*;

public class poligono extends Figure{

    protected int[] pontosX;
    protected int[] pontosY;
    protected int[] rgb2;

    public poligono(int x, int y, int[] rgb, int[] rgb2) {
        
        super(rgb, x, y);
        this.rgb2 = rgb2;
        this.pontosX = new int[] {x, x+25, x+25, x, x-25, x-25, x};
        this.pontosY = new int[] {y, y+15, y+40, y+55, y+40, y+15, y};

    }
    
    public void print () {
        System.out.format("poligono de 5 lados.\n");
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rgb[0], this.rgb[1], this.rgb[2]));
        g2d.drawPolygon(this.pontosX, this.pontosY, 6);

        g2d.setColor(new Color(this.rgb2[0], this.rgb2[1], this.rgb2[2]));
        g2d.fillPolygon(this.pontosX, this.pontosY, 6);
    }

    public boolean clicked (int mx, int my) { //função para dectar quando o mouse clicar na figura
        return (this.x-25 <= mx && mx<= this.x-25 + 50 && this.y <= my && my <= this.y + 50);
    }

    public void drag(int dx, int dy){
        for(int i = 0; i < 6; i++){
            this.pontosX[i] += dx;
            this.pontosY[i] += dy;
        }
    }
}