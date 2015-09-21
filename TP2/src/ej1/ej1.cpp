#include <time.h>
#include <fstream>
#include <string>
#include <vector>
#include <list>
#include <iostream>
#include <regex>

using namespace std;

int main( int argc, char* argv[]){
    bool debug = false;
    /*
    bool tiempos = false;
    int corridas = 1;                      // cantidad de corridas a hacer para medir tiempos
    for (int i=1; i<argc; ++i){
        string p = argv[i];
        if (p == "--debug"){
            debug = true;
        }
        if (p == "--topdown"){
            topdown = true;
        }
        if (p == "--tiempos"){
            tiempos = true;
            corridas = 100;
        }
    }
    */



    std::ifstream infile("input.txt");
    
    std::string line;
    while (std::getline(infile, line)){ // aca levanto la primera linea del problema (n)
        int n = stoi(line);
        vector< int > resultados;
        vector< list < int > > portales;
        portales.resize(n);
        resultados.resize(n);
        for (int i=0; i<n-1; ++i){      // inicializo portales (input) y resultados (tabla resultados parciales)
            std::list<int> l;
            portales[i] = l;
            resultados[i] = -1;
        }
        
        string lineaPortales;
        std::getline(infile, lineaPortales);

        std::regex reg1("\\s*(\\d+)\\s+(\\d+)\\s*[;\\n$]*");
        sregex_iterator it(lineaPortales.begin(), lineaPortales.end(), reg1);
        sregex_iterator it_end;
        while(it != it_end) {
            std::smatch res = *it;
            int sale = stoi(res[1]);
            int llega = stoi(res[2]);
            portales[sale].push_back(llega);
            ++it;
        }
    
        // ya quedaron los datos de entrada del problema cargados.

        if (debug){
            cout << "Piso N (n = " << n << ")" << endl;
            for (int i = n-1; i>=0; i--){
                cout << "Piso " << i << ": ";
                for (std::list<int>::iterator j = portales[i].begin(); j != portales[i].end(); ++j){
                    cout << *j << ",";
                }
                cout << endl;
            }
        }

        // resolucion:
        int i = n-1;
        while (i>=0){
            int max = -1;
            for (std::list<int>::iterator it=portales[i].begin(); it != portales[i].end(); ++it){
                int costo = -1;
                if (*it == n){
                    // el portal va directo a N
                    costo = 1;
                } else if (resultados[*it] == -1){
                    // no se puede llegar a N por este portal
                    costo = -1;
                } else{
                    costo = resultados[*it] + 1;
                }
                if (costo > max){
                    max = costo;
                }
            }
            resultados[i] = max;
            i--;
        }

        cout << resultados[0] << endl;
        
    }


    


	return 0;
}


