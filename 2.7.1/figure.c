#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);

typedef struct Figure {
    int x, y;
    Color fg, bg;
    void (* print) (struct Figure*);
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

Rect* rect_new (int x, int y, int w, int h) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) rect_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
           this->w, this->h, sup->x, sup->y);
}

Ellipse* ellipse_new (int x, int y, int w, int h) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Ellipse_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
}

///////////////////////////////////////////////////////////////////////////////


typedef struct{
    Figure super;
    char text[4];
    int w, h; 
}Texto;

void Text_print(Texto* this){
    Figure* sup = (Figure*) this;
    printf("Texto de conteudo (%s) na posicao (%d,%d) de tamanho (%d,%d).\n",this->text, sup->x,sup->y, this->w, this->h);
}

Texto* text_new (int x, int y, int w, int h, char* text){
    Texto* this = malloc(sizeof(Texto));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Text_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    *this->text = text[4];
}
///////////////////////////////////////////////////////////////////////////////

typedef struct{
    Figure super;
    int w, h;
    int xpoints[5];
    int ypoints[5];
} Polygon;

void Polygon_print(Polygon *this){
    Figure *sup = (Figure *)this;
    printf("tamanho do poligono: (%d,%d) e sua posicao: (%d,%d). ",
           this->w, this->h, sup->x, sup->y);
    printf("Com os pontos: ");
    for (int i = 0; i < 6; i++)
    {
        printf("(%d, %d)", this->xpoints[i], this->ypoints[i]);
    }
    printf("\n");
}

Polygon *polygon_new(int x, int y, int w, int h){
    Polygon *this = malloc(sizeof(Polygon));
    Figure *sup = (Figure *)this;
    sup->print = (Figure_Print)Polygon_print;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;

    this->xpoints[0] = x;
    this->xpoints[1] = x + 100;
    this->xpoints[2] = x + 200;
    this->xpoints[3] = x + 200;
    this->xpoints[4] = x + 100;
    this->xpoints[5] = x;

    this->ypoints[0] = y;
    this->ypoints[1] = y - 50;
    this->ypoints[2] = y;
    this->ypoints[3] = y + 50;
    this->ypoints[4] = y + 100;
    this->ypoints[5] = y + 50;
}

///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Figure *figs[8] = {
        (Figure *)rect_new(10, 10, 100, 100),
        (Figure *)ellipse_new(40, 10, 140, 300),
        (Figure *)rect_new(10, 10, 100, 100),
        (Figure *)ellipse_new(210, 110, 305, 130),
        (Figure *)text_new(75,57,30,30,"simm"),
        (Figure *)text_new(55,77,30,50,"naoo"),
        (Figure *)polygon_new(50, 100, 60, 60),
        (Figure *)polygon_new(150, 150, 300, 300)
    };

    ///

    for (int i=0; i<8; i++) {
        figs[i]->print(figs[i]);
    }

    ///

    for (int i=0; i<8; i++) {
        free(figs[i]);
    }
}