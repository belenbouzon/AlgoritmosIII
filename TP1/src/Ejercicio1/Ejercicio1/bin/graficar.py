import sys
import matplotlib.pyplot as plt
import numpy as np
from scipy import interpolate


eje_y = np.arange(int(sys.argv[2]))
eje_x = np.arange(int(sys.argv[2]))
i = 0
with open(sys.argv[1],'r') as f:
		for row in f.readlines():
			row = row.split()
			eje_y[i] = int(row[1])
			eje_x[i] = int(row[0])
			i = (i+1)
print eje_x
print eje_y
s = interpolate.InterpolatedUnivariateSpline(eje_x, eje_y)
ynew = s(eje_x)
plt.figure()
plt.plot(eje_x,ynew,eje_x,eje_y,'b')
plt.legend(['Etiqueta'])
plt.axis([0, 7, 0, 7])
plt.title('Titulo')
plt.show()



