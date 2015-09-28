#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <stdio.h>
using namespace std;

int main(int argc, char** argv){
	int primer_parametro = 14;
	int segundo_parametro = 16;
	int tercer_parametro = 10;
	int entrada = 10;
	int cantidad;
        sscanf(argv[1],"%d",&cantidad);
	for(int j = 0; j<=cantidad;j++){
		stringstream ss;
		ss << "valores/valor" << primer_parametro << '_' << segundo_parametro << '_' << tercer_parametro << ".txt";
		string archivo = ss.str();
		fstream ar;
		ar.open(archivo.c_str(),std::fstream::in);
		int val;
		ar >> val;
		cout << entrada << ' ' << ((double)val)/1000000 << endl;
		ar.close();

		primer_parametro++;
		segundo_parametro++;
		tercer_parametro++;
		entrada++;
	}
}
