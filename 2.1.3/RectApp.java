public class RectApp {
    public static void main (String[] args) {
        Rect r1 = new Rect(1,1, 30,22);
        int a = r1.area();
        r1.print();
        r1.drag(20, 10);
        r1.printArea(a);
        r1.printNovaPosicao();
    }
}
class Rect {
    int x, y;
    int w, h;
    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    int area(){
        int rectArea = this.w*this.h;
        return rectArea;
    }
    void drag(int dx, int dy){
        this.x = this.x+dx;
        this.y = this.y+dy;
    }


    void print () {
        System.out.format("Retangulo de largura %d e altura %d na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
    void printArea(int area){
        System.out.format("Area: %d.\n",
            area);
    }
    void printNovaPosicao(){
        System.out.format("nova posicao (%d,%d).\n",
            this.x, this.y);
    }
}