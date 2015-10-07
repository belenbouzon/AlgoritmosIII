#!/bin/bash

maxM=$1
espaciado=$2
count=1

let maxM+=1
# m = cantidad de aristas del grafo
for (( m = 1; m <= $maxM; m+=$espaciado ))
do
	touch "$count.t"
	line=""
	for (( i = 0; i < $m; i++ ))
	do
		id=$[ RANDOM %  ]
		ih=$[ RANDOM % 10000 ]
		if [[ $id eq $ih ]]; then
			let ih+=1
		fi
		l=$[ 1 + $[ RANDOM % 10000 ]]
		line="$line; $id $ih $l"
	done
	echo $line > "$count.t"
	let count+=1
done

exit 0

# Random connected graph

# https://gist.github.com/bwbaugh/4602818#file-random_connected_graph-py

# https://github.com/MikeMirzayanov/testlib/blob/master/generators/gen-tree-graph.cpp

# https://www-complexnetworks.lip6.fr/~latapy/FV/generation.html