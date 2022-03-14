package figures;

import java.awt.Graphics;

public abstract class Figure {
    protected int[] rgb;
    protected int[] rgb2;

    public Figure(int rgb[], int[] rgb2) {
        this.rgb = rgb;
        this.rgb2 = rgb2;
    }
    public abstract void paint(Graphics g);
}
