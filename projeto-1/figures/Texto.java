package figures;

import java.awt.*;

public class Texto extends Figure{
    private String texto;

    public Texto (String texto, int x, int y, int[] rgb) {
        super(rgb, null, x, y);
        this.texto = texto;
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
        
        g2d.setFont(new Font("Ink Free", 1, this.w/2));
        g2d.drawString(this.texto,this.x,this.y);

        if(focus){
            g2d.setColor(new Color(255, 0, 0));
            g2d.drawRect(this.x + this.w*2 + this.w/3 + 10, this.y - this.w/2, 10,10);
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(this.x + this.w*2 + this.w/3 + 10, this.y - this.w/2, 10,10);
        }
    }

    public boolean clicked (int ex, int ey) {
        boolean result = false;

        if(ex >= this.x + this.w*2 + this.w/3 + 10 && ex <= this.x + this.w*2 + this.w/3 + 20 && ey <= this.y - this.w/2 + 10 && ey >= this.y - this.w/2){
            result = true;
            this.quad = true;

         }
        else if(ex >= this.x && ex <= this.x + this.w*2 + this.w/3 && ey <= this.y + this.w/5 && ey >= this.y - this.w/3){
            result = true;
            this.quad = false;

        }
        return result;
    }
}