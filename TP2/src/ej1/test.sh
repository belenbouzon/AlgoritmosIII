#! /bin/bash

./ej1 test.in
diff --brief -w test.results test.in.out >/dev/null
comp_value=$?
if [ $comp_value -eq 1 ]
then
    echo "Error en test"
else
    echo "Test OK"
fi

