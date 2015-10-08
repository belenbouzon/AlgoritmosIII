#!/bin/bash
# cant de nodos del grafo
N=$1
# el M inicial es igual a N-1 (min para un grafo conexo)
# muestras es la cantidad de grafos que se quieren generar para el N fijo (ej 100, 200)
muestras=$2
count=1
# cant de aristas del grafo completo de N nodos.
maxM=$(($N*($N-1)/2))
let espaciado=($maxM-$N+1)/$muestras
# m = cantidad de aristas del grafo
for (( m = $N-1; m <= $maxM; m+=$espaciado ))
do
	touch "$count-$N.t"
	python random_connected_graph.py $N -e $m > "$count-$N.t"
	let count+=1
done

exit 0