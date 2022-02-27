package figures;

import java.awt.*;

public class poligono {

    protected int[] pontos;
    protected int[] pontosX;
    protected int[] pontosY;
    protected int tamanho;

    public poligono(int[] vetor) {
        
        this.pontos = vetor;
        this.tamanho = this.pontos.length;
        this.pontosX = new int[this.tamanho/2];
        this.pontosY = new int[this.tamanho/2];

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
        g2d.drawPolygon(this.pontosX, this.pontosY, this.tamanho/2);
    }
}
