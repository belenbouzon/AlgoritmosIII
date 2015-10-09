import sys
import matplotlib.pyplot as plt
import numpy as np
from scipy import interpolate


#eje_x = np.zeros(int(sys.argv[3]))
#eje_y = np.zeros(int(sys.argv[3]))
eje_x = []
eje_y = []
i = 0
with open(sys.argv[1],'r') as f:
		for row in f.readlines():
			row = row.split(' ')
			#eje_x[i] = float(row[0])
			#eje_y[i] = float(row[1])/1000000
			eje_x.append(float(row[0]))
			eje_y.append(float(row[1])/1000000)
			i = i+1

funcion = interpolate.splrep(eje_x, eje_y, s=0)
funcion2 = interpolate.splev(eje_x, funcion, der=0)


plt.figure()
plt.plot(eje_x,funcion2)
plt.legend(['funcion'])
plt.axis([190, 410, 469, 1170])
plt.title('duracion respecto a cantidad de portales')
plt.savefig(sys.argv[2])
