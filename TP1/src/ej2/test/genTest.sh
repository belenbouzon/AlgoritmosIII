#!/bin/bash

file=$1
maxN=$2
espaciado=$3
touch $file

for ((m=1; m < $maxN; m+=espaciado))
do
	line=""
	for ((i=0; i<$m; i++))
	do
		num=$[ 1 + $[ RANDOM % 10000 ]]
		line="$line $num "
	done
	echo $line >> $file
done

exit 0
