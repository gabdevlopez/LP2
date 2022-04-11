
package figures;

import java.awt.*;

public class poligono extends Figure{

    protected int[] pontosX;
    protected int[] pontosY;
    protected int w = 50;
    protected int aux = 0;

    public poligono(int x, int y, int[] rgb, int[] rgb2) {
        
        super(rgb, rgb2, x, y);
        this.pontosX = new int[] {x, x+25, x+25, x, x-25, x-25};
        this.pontosY = new int[] {y, y+15, y+40, y+55, y+40, y+15};
    }

    public void drag(int dx, int dy){
        this.x += dx;
        this.y += dy;
        
        for(int i = 0; i < 6; i++){
            this.pontosX[i] += dx;
            this.pontosY[i] += dy;
        }
    }
    
    public void tamanho(int num){
        
        if(num > 0){
            num = 5;
        }
        else if(num < 0){
            num = - 5;
        }
        if(w >= 200 && num >= 0){
            return;
        }
        else if(w <= 50 && num <= 0){
            return;
        }
        else{
            w += num;

            this.pontosX[1] += num; 
            this.pontosX[2] += num; 
            this.pontosX[4] -= num; 
            this.pontosX[5] -= num; 
        
            this.pontosY[1] += num/2; 
            this.pontosY[2] += num; 
            this.pontosY[3] += num + num/2; 
            this.pontosY[4] += num; 
            this.pontosY[5] += num/2; 
        }
        
    }
            
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(focus){
            if(verificaCor){
                g2d.setColor(new Color(this.rgb[0], this.rgb[1], this.rgb[2]));
                verificaCor = false;
            }
            else{
                g2d.setColor(new Color(255, 0, 0));
            }
        }
        else{
            g2d.setColor(new Color(this.rgb[0], this.rgb[1], this.rgb[2]));
        }

        BasicStroke bs = new BasicStroke(4);
        g2d.setStroke(bs);
        g2d.drawPolygon(this.pontosX, this.pontosY, 6);

        g2d.setColor(new Color(this.rgb2[0], this.rgb2[1], this.rgb2[2]));
        g2d.fillPolygon(this.pontosX, this.pontosY, 6);

        if(focus){
            g2d.setColor(new Color(255, 0, 0));
            g2d.drawRect(this.pontosX[1], this.pontosY[3], 10,10);
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(this.pontosX[1], this.pontosY[3], 10,10);
        }
    }

    public boolean clicked (int mx, int my) {
        boolean result = false;

        if(this.pontosX[2] <= mx && mx<= this.pontosX[2] + 10 && this.pontosY[3] <= my && my <= this.pontosY[3] + 10){
            this.quad = true;
            result = true;
        }
        else if(this.pontosX[5] <= mx && mx<= this.pontosX[1]  && this.pontosY[0] <= my && my <= this.pontosY[3]){
            this.quad = false;
            result = true;
        }
        return result;
    }
}