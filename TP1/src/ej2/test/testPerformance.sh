#!/bin/bash

cantFiles=$1
touch resultados.out
echo "n	prom" > resultados.out

for (( file = 1; file <= $cantFiles; file++ )) 
do
	max=0
	min=99999999
	promedio=0


	for i in {1..100}
	do
	    clocks=$(java -cp ../bin/ Main $file.t --tiempos)
	    n=$(echo $clocks | cut -d "-" -f 1)
	    clocks=$(echo $clocks | cut -d "-" -f 2)

	    let promedio+=$clocks
	    #echo $promedio

	    if [[ "$clocks" -gt "$max" ]]; then
	    	max=$clocks
	    fi

	    if [[ "$clocks" -lt "$min" ]]; then
	    	min=$clocks
	    fi
	    
	done


	promedio=$(($promedio-$min-$max))
	promedio=$(($promedio/98))

	echo $n	$promedio >> resultados.out

done

exit 0