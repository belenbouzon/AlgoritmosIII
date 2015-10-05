import sys
import matplotlib.pyplot as plt
import numpy as np
from scipy import interpolate


eje_x = np.zeros(57)
eje_y = np.zeros(57)
i = 0
with open(sys.argv[1],'r') as f:
		for row in f.readlines():
			row = row.split(' ')
			eje_x[i] = float(row[0])
			eje_y[i] = float(row[1])
			i = i+1

funcion = interpolate.splrep(eje_x, eje_y, s=0)
funcion2 = interpolate.splev(eje_x, funcion, der=0)


plt.figure()
plt.plot(eje_x,funcion2)
plt.legend(['funcion'])
plt.axis([3, 60, 0, 80])
plt.title('duracion respecto a cantidad de portales')
plt.savefig(sys.argv[2])
