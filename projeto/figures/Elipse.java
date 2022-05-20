package figures;
import java.awt.*;

public class Elipse extends Figure{

    public Elipse (int x, int y, int[] rgb, int[] rgb2) {
        super(rgb, rgb2, x, y);
    }

    
    public boolean clicked (int mx, int my) {
        boolean result =  false;
        
        if(mx >= this.x + this.w - 5 && mx <= this.x + this.w + 5 && my <= this.y + this.w + 5 && my >= this.y + this.w - 5){
            result = true;
            this.quad = true;
        }
        else if(this.x <= mx && mx<= this.x + this.w && this.y <= my && my <= this.y + this.w){
            result = true;
            this.quad = false;
        }
        return result;
    }
    
    public void paint (Graphics g, boolean focus) {
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
        g2d.drawOval(this.x,this.y, this.w,this.w);

        g2d.setColor(new Color(this.rgb2[0], this.rgb2[1], this.rgb2[2]));
        g2d.fillOval(this.x,this.y, this.w,this.w);

        if(focus){
            g2d.setColor(new Color(255, 0, 0));
            g2d.drawRect(this.x + this.w - 5, this.y + this.w - 5, 10,10);
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(this.x + this.w - 5, this.y + this.w - 5, 10,10);
        }
    }
}