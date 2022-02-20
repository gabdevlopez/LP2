#include <stdio.h>

typedef struct{
    int pontos[10];
} poligonos5;

void imprime (poligonos5* p){
    printf("posição dos vertices: ");

    for(int i = 0; i < 10; i++){
        if(i%2 == 0){
        printf("| %d, ",p ->pontos[i]);
        }
        else{
        printf("%d ",p ->pontos[i]);
        }
    }
}


int main(void){
    
    poligonos5 p1 = {100,100, 120,80, 140,120, 120,140, 100,120}; 
    imprime(&p1);
}

