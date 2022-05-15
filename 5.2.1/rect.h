typedef struct Rect Rect;
Rect* rect_new(int w, int h, int x, int y);
void rect_drag(Rect* this, int dx, int dy);
void rect_print(Rect* this);