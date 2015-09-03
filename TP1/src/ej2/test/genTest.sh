#!/bin/bash

maxN=$1
espaciado=$2
count=1

let maxN+=1

for (( m = 1; m <= $maxN; m+=$espaciado ))
do
	touch "$count.t"
	line=""
	for (( i = 0; i < $m; i++ ))
	do
		num=$[ 1 + $[ RANDOM % 10000 ]]
		line="$line $num"
	done
	echo $line >> "$count.t"
	let count+=1
done

exit 0
