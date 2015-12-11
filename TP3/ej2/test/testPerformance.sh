#!/bin/bash

cantFiles=$1 #total de archivos
inicio=$2 # desde donde empieza a contar.
variable=$3 # opciones:{Nodos, Aristas, Colores}
incremento=$4 # cuanto hay que sumar para otener el proximo archivo
podas=$5 #si se quiere correr el algorimo con podas pasar '-p' sino no poner nada
result="resultados$variable$podas.out"
touch $result


for (( file = $inicio; file <= $cantFiles; file+=$incremento )) 
do
	max=0
	min=99999999999999
	promedio=0


	for i in {1..50}
	do
	    clocks=$(java -Xms512m -Xmx2048m -cp ../bin/:./$variable Main "$file$variable.out" --tiempos $variable $podas)
	    n=$(echo $clocks | cut -d "-" -f 1)
	    clocks=$(echo $clocks | cut -d "-" -f 2)

	    let promedio+=$clocks

	    if [[ "$clocks" -gt "$max" ]]; then
	    	max=$clocks
	    fi

	    if [[ "$clocks" -lt "$min" ]]; then
	    	min=$clocks
	    fi
	    
	done

	promedio=$(($promedio-$min-$max))
	promedio=$(($promedio/98))

	echo $n	$promedio >> $result
	echo "done $file"

done

exit 0