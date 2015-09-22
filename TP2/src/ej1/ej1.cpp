#include <time.h>
#include <fstream>
#include <string>
#include <vector>
#include <list>
#include <iostream>
#include <regex>
#include <chrono>

using namespace std;

void genPeorCaso();
int resolver();

unsigned int n;
vector< int > resultados;
vector< list < unsigned int > > portales;

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
            corridas = 50;
            break;
        } else{
            nombreInput = p; 
        }
    }

    

    if (!tiempos){
        std::ifstream infile(nombreInput);
        std::ofstream outfile(nombreInput+".out");
    
        int p = 0;  // quiero saber la cantidad total de portales para el análisis de tiempos
        std::string line;
        while (std::getline(infile, line)){ // aca levanto la primera linea del problema (n)
            // Parseo input
            n = stoi(line);
            portales.resize(n);     // O(n)
            resultados.resize(n);   // O(n)
            for (unsigned int i=0; i<n; ++i){      // inicializo portales (input) y resultados (tabla resultados parciales)
                std::list<unsigned int> l;
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
                unsigned int sale = stoi(res[1]);
                unsigned int llega = stoi(res[2]);
                portales[sale].push_back(llega);
                p++;
                ++it;
            }

            // ya quedaron los datos de entrada del problema cargados.

            if (debug){
                cout << "Piso N (n = " << n << ")" << endl;
                for (int i = n-1; i>=0; i--){
                    cout << "Piso " << i << ": ";
                    for (std::list<unsigned int>::iterator j = portales[i].begin(); j != portales[i].end(); ++j){
                        cout << *j << ",";
                    }
                    cout << endl;
                }
            }

            // Resuelvo
            int res = resolver();
            outfile << res << endl;
        } // termino problemas
    }else{
        cout << "n,p,t" << endl;
        vector<unsigned int> tams = {50,100,200,300,500,750,1000,1250,1500,1750,2000,2500,3000,4000,5000,7000,8000,9000,10000,11000,12000,13000,14000,15000,16000};
        for (unsigned int i=0; i<tams.size(); i++){
            int ejecucion = 0;
            while (ejecucion < corridas){
                n = tams[i];
                int p = (n * (n+1))/2;
                vector< int > resultados;
                vector< list < unsigned int > > portales;
                genPeorCaso();
                std::chrono::high_resolution_clock::time_point t0 = std::chrono::high_resolution_clock::now();
                resolver();

                std::chrono::high_resolution_clock::time_point t1 = std::chrono::high_resolution_clock::now();
                auto duration = std::chrono::duration_cast<std::chrono::microseconds>( t1 - t0 ).count();
                cout << n << "," << p << "," << duration << endl;
                
                ejecucion++;
            } // terminan todas las corridas
        }
    }
    
	return 0;
}


//void genPeorCaso(unsigned int n, vector< int >& resultados, vector< list < unsigned int > >& portales){
void genPeorCaso(){
    // genero una instancia del problema donde hay la máxima cantidad posible de portales
    resultados.resize(n);
    portales.resize(n);

    for (unsigned int p=0; p<n; p++){
        resultados[p] = -1;
        std::list<unsigned int> l;
        portales[p] = l;
        for (unsigned int h=p+1; h<=n; h++){
            portales[p].push_back(h);
        }
    }
}

//int resolver(unsigned int n, vector< int >& resultados, vector< list < unsigned int > >& portales){
int resolver(){
     int i = n-1;
     while (i>=0){
        int max = -1;
        for (std::list<unsigned int>::iterator it=portales[i].begin(); it != portales[i].end(); ++it){
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
    int res = resultados[0];
    return res;
}