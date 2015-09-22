#include <time.h>
#include <fstream>
#include <string>
#include <vector>
#include <list>
#include <iostream>
#include <regex>
#include <chrono>

using namespace std;

int main( int argc, char* argv[]){
    bool debug = false;
    bool tiempos = false;
    int corridas = 1;                      // cantidad de corridas a hacer para medir tiempos
    string nombreInput = "";
    if (argc < 2){
        cout << "Uso:\n./ej1 [--tiempos] [--debug] infile.in\n";
    }
    for (int i=1; i<argc; ++i){
        string p = argv[i];
        if (p == "--debug"){
            debug = true;
        } else if (p == "--tiempos"){
            tiempos = true;
            corridas = 100;
        } else{
            nombreInput = p; 
        }
    }

    int p = 0;  // quiero saber la cantidad total de portales para el análisis de tiempos

    std::ifstream infile(nombreInput);
    std::ofstream outfile(nombreInput+".out");

    if (tiempos){
        cout << "n,p,t" << endl;
    }
    
    std::string line;
    while (std::getline(infile, line)){ // aca levanto la primera linea del problema (n)
        int n = stoi(line);
        vector< int > resultados;
        vector< list < int > > portales;
        portales.resize(n);     // O(n)
        resultados.resize(n);   // O(n)
        for (int i=0; i<n-1; ++i){      // inicializo portales (input) y resultados (tabla resultados parciales)
            std::list<int> l;
            portales[i] = l;
            resultados[i] = -1;
        }   // O(n)
        
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
            p++;
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
        int ejecucion = 0;
        while (ejecucion < corridas){
            // resolucion:
            std::chrono::high_resolution_clock::time_point t0 = std::chrono::high_resolution_clock::now();
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
            if (!tiempos){
                outfile << resultados[0] << endl;
            }else{
                std::chrono::high_resolution_clock::time_point t1 = std::chrono::high_resolution_clock::now();
                auto duration = std::chrono::duration_cast<std::chrono::microseconds>( t1 - t0 ).count();
                cout << n << "," << p << "," << duration << endl;
            }
            ejecucion++;
        } // terminan todas las corridas
    } // termino problema

	return 0;
}


