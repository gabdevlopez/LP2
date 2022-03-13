
package figures;

import java.awt.*;

public class poligono extends Figure{

    protected int[] pontos;
    protected int[] pontosX;
    protected int[] pontosY;
    protected int[] rgb;
    protected int[] rgb2;

    protected int tamanho;

    public poligono(int[] vetor, int[] rgb, int[] rgb2) {
        
        this.pontos = vetor;
        this.tamanho = this.pontos.length;
        this.pontosX = new int[this.tamanho/2];
        this.pontosY = new int[this.tamanho/2];
        this.rgb = rgb; 
        this.rgb2 = rgb2; 

        int j = 0;
        int k = 0;

        for(int i = 0; i < this.tamanho; i++){
            if(i%2 == 0 || i == 0){
                this.pontosX[j] = this.pontos[i];
                j++;
            }
            else{
                this.pontosY[k] = this.pontos[i];
                k++;
            }
        }
    }
    
    public void print () {
        System.out.format("poligono de %d lados.\n",
            this.tamanho/2);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rgb[0], this.rgb[1], this.rgb[2]));
        g2d.drawPolygon(this.pontosX, this.pontosY, this.tamanho/2);

        g2d.setColor(new Color(this.rgb2[0], this.rgb2[1], this.rgb2[2]));
        g2d.fillPolygon(this.pontosX, this.pontosY, this.tamanho/2);
    }
}