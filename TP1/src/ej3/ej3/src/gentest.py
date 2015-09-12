#! /usr/bin/python

import random
import math


## CONSTANTES:
## e fijo y a incrementa linealmente
e = 10
filenameEfijo = "./casosMedicionEfijo.in"
filenameA2e = "./casosA2e.in"
filenameA1e = "./casosA1e.in"
letras = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
emax = len(letras)

#Empieza código:

#E fijo
amax = (e * (e-1))/2
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
    a = a + 1

fout.close()

'''
#A = 2e
e = 2
fout = open(filenameA2e, 'w')
while (e <= emax):
    a = 2*e
    amax = (e * (e-1))/2
    if (a>amax):
        a = amax
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
    e = e + 1

fout.close()
'''

#A = e
e = 2
fout = open(filenameA1e, 'w')
while (e <= emax):
    a = e
    amax = (e * (e-1))/2
    if (a>amax):
        a = amax
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
    e = e + 1

fout.close()
