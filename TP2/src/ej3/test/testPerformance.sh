#!/bin/bash

cantFiles=$1
N=$2
result="resultados-$N.out"
touch $result
echo "m	prom" > $result

for (( file = 1; file <= $cantFiles; file++ )) 
do
	max=0
	min=99999999999999
	promedio=0


	for i in {1..100}
	do
	    clocks=$(java -cp ../bin/:./N-$N Main "$file-$N.t" --tiempos)
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

	echo $n	$promedio >> $result

done

exit 0