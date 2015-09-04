#! /usr/bin/python

import random
import math


## CONSTANTES:
## e fijo en 20 y a incrementa cuadráticamente
e = 20
filenameEfijo = "./casosMedicionEfijo.in"


#Empieza código:
amax = (e * (e-1))/2
letras = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
a = 1
fout = open(filenameEfijo, 'w')
while (a <= amax):
    #inicializo exploradoras
    exploradoras = {}
    for j in range(e):
        exploradoras[j] = set()
    #asigno amistades al azar
    amistades = set()
    while(len(amistades) < a):
        amiga0 = random.randint(0,e-1)
        amiga1 = random.randint(0,e-1)
        while (amiga1 == amiga0):       #me aseguro que sean distintas
            amiga1 = random.randint(0,e-1)
        amistad = []
        amistad.append(amiga0)
        amistad.append(amiga1)
        amistad.sort()      # ordeno para no agregar dos veces la misma amistad (1,3) == (3,1)
        amistades.add(tuple(amistad))
    #grabo amistades en cada exploradora
    for amistad in amistades:
        amiga0, amiga1 = amistad
        exploradoras[amiga0].add(amiga1)
        exploradoras[amiga1].add(amiga0)
    #escribo el archivo
    for i in range(e):
        fout.write("{} ".format(letras[i]))
        for amiga in exploradoras[i]:
            fout.write(letras[amiga])
        fout.write(';')
    fout.write("\n")
    a = 2*a

fout.close()
