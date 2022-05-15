import Ivisible.Ivisible;
import figures.Figure;
import java.awt.*;

public class Button implements Ivisible {
    static int SPC = 15;
    static int DIM = 40;
    static int PAD = 7;

    public int idx;
    private Figure fig;

    public Button (int idx, Figure fig) {
        this.idx = idx;
        this.fig = fig;
        this.fig.x = PAD+SPC;
        this.fig.y = PAD+SPC + idx*DIM;
        this.fig.w = DIM-PAD*2;
        this.fig.h = DIM-PAD*2;
    }

    public boolean clicked (int x, int y) {
        return SPC<=x && x<=SPC+DIM && SPC+this.idx*DIM<=y && y<=SPC+this.idx*DIM+DIM;
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(focused ? Color.GRAY : Color.LIGHT_GRAY);
        g2d.fillRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        if(this.idx == 3 || this.idx > 4){
            this.fig.y = PAD+SPC + idx*DIM +20;
        }
        
        this.fig.paint(g, false);
    }
}