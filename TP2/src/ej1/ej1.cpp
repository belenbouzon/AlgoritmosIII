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


            // Con regex parseamos los datos de entrada. Nótese que no se contempla el parseo en el cómputo de complejidad del algoritmo,
            // ya que la STL no especifica con claridad la complejidad del uso de regexes. Sin embargo, como estamos utilizando backreferences,
            // sabemos que la complejidad es al menos exponencial (con respecto a la longitud del string). Sin embargo, consideramos que el parseo
            // no es lo interesante del ejercicio y nos pareció una pérdida de tiempo invertir tiempo en esto.
            // https://swtch.com/~rsc/regexp/regexp1.html
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
        // ejecucion para medicion de tiempos
        cout << "n,p,t" << endl;
        vector<unsigned int> tams = {50,100,200,300,500,750,1000,1250,1500,1750,2000,2500,3000,4000,5000,7000,8000,9000,10000,11000,12000,13000,14000,15000,16000};
        for (unsigned int i=0; i<tams.size(); i++){
            int corridas = 50;
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

int resolver(){
     int i = n-1;
     while (i>=0){  // este ciclo corre N veces (una vez por cada piso entre 0 y N-1)
        int max = -1;   // O(1)
        for (std::list<unsigned int>::iterator it=portales[i].begin(); it != portales[i].end(); ++it){
            // este ciclo corre tantas veces como portales haya en este piso
            int costo = -1;     // O(1)
            if (*it == n){      // O(1)
                // el portal va directo a N
                costo = 1;      // O(1)
            } else if (resultados[*it] == -1){      // O(1)
                // no se puede llegar a N por este portal
                costo = -1;     // O(1)
            } else{
                costo = resultados[*it] + 1;        // O(1)
            }
            if (costo > max){       // O(1)
                max = costo;        // O(1)
            }
        }
        resultados[i] = max;        // O(1)
        i--;                        // O(1)
    }
    int res = resultados[0];        // O(1)
    return res;                     // O(1)
}
