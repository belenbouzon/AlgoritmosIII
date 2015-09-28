#include <iostream>
#include <stdio.h>
using namespace std;

int main(int argc, char** argv){
    cout << "all: generar_entradas promedios unificar grafico" << endl;
    int primer_parametro = 14;
    int segundo_parametro = 16;
    int tercer_parametro = 10;
    int cantidad;
    int internas;
    sscanf(argv[1],"%d",&cantidad);
    sscanf(argv[2],"%d",&internas);
    cout << "generar_entradas:" << endl;
    for(int j= 0; j< cantidad; j++){
        for(int i = 1;i<=internas;i++){
            cout << "	java GeneradorRandomDeEntradas " << primer_parametro << ' ' << segundo_parametro << ' ' << tercer_parametro << ' ' << "> entradas/salida" << primer_parametro << '_' << segundo_parametro << '_' << tercer_parametro << '_' << i << ".txt" << endl;
        }
        primer_parametro++;
        segundo_parametro++;
        tercer_parametro++;
    }
    primer_parametro = 14;
    segundo_parametro = 16;
    tercer_parametro = 10;
    cout << "promedios:" << endl;
    for(int j= 0; j< cantidad; j++){
        cout << "	java Tester entradas/salida" << primer_parametro << '_' << segundo_parametro << '_' << tercer_parametro << " > valores/valor" << primer_parametro << '_' << segundo_parametro << '_' << tercer_parametro << ".txt" << ' ' << argv[2] << endl;
        primer_parametro++;
        segundo_parametro++;
        tercer_parametro++;
    }
    cout << "unificar:" << endl;
    cout << "	./unificar_archivos " << cantidad << " > valores/valores.txt" << endl;
    cout << "grafico:" << endl;
    cout << "	python graficar.py valores/valores.txt valores/grafico.png" << cantidad << endl;
}
