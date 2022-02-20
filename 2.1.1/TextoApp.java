public class TextoApp {
    public static void main (String[] args){
        texto t1 = new texto(100,100, 50, 134, 252, 156);
        t1.imprime();
    }
}
class texto{
    int posX;
    int posY;
    int tamanho;
    int r, g, b;

    texto(int posX, int posY, int tamanho, int r, int g, int b){
        this.posX = posX;
        this.posY = posY;
        this.tamanho = tamanho;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    void imprime(){
        System.out.format("posição do texto: (%d,%d) \ntamanho: (%d) \ncor: (%d,%d,%d)", 
        this.posX, this.posY, this.tamanho, this.r, this.g, this.b);
    }
}



