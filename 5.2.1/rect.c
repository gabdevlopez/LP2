#include "rect.h"
#include <stdio.h>
#include <stdlib.h>

typedef struct Rect{
  int h, w, x, y;
}Rect;

Rect* rect_new(int w, int h, int x, int y){
  Rect* this = malloc(sizeof(Rect));
  this->w = w;
  this->h = h;
  this->x = x;
  this->y = y;
}

void rect_print(Rect* this){
  printf("retangulo de largura: %d, altura: %d e na posicao: (%d,%d)\n",this->w,this->h,this->x,this->y); 
}

void rect_drag(Rect* this, int dx, int dy){
  this->x = dx;
  this->y = dy;
}