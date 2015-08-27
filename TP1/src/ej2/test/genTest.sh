#!/bin/bash

file=$1
cantLineas=$2
maxN=$3
touch $file

for ((m=1; m<$cantLineas; m++))
do
	line=""
	n=$[ 1 + $[ RANDOM % $maxN ]]
	for ((i=1; i<=$n; i++))
	do
		num=$[ 1 + $[ RANDOM % 10000 ]]
		line="$line $num "
	done
	echo $line >> $file
done

exit 0
