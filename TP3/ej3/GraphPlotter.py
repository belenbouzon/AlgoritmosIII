__author__ = 'Belen Bouzon'
import networkx as nx
import matplotlib.pyplot as plt
import colorsys

#Recibo <numero de nodo, su numero de color>
#Obs: nunca un numero de color puede ser mayor a la cantidad de nodos.
nodes = [(2,1),(1,0),(0,3),(3,2),(4,4),(5,5),(6,6), (7,7),(8,8),(9,9),(10,10)]
#Recibo las aristas
aristas = [(0,1), (0,2), (1,0), (1,2), (2,1), (5,6), (6,7), (7,8), (8,9), (9,10), (9,1)]

cantidadDeNodos = len(nodes)

 #Hago un array con tantos colores como cantidad de nodos tengo
HSV_tuples = [(x*1.0/(cantidadDeNodos*0.7), 0.3, 0.9) for x in range(cantidadDeNodos)]
#RGB_tuples = map(lambda x: colorsys.hsv_to_rgb(*x), HSV_tuples)
RGB_tuples = map(lambda x: colorsys.hsv_to_rgb(*x), HSV_tuples)
print len(RGB_tuples)

#Armo un array con los colores ordenados en el orden natural de los nodos
nodeColors = []
for i in range (0, cantidadDeNodos):
    color =  [nodo[1] for nodo in nodes if nodo[0] == i][0]
    nodeColors.insert(i,RGB_tuples[color])

G=nx.Graph()
G.add_nodes_from([x[0] for x in nodes])
G.add_edges_from(aristas)
pos=nx.fruchterman_reingold_layout(G,iterations=50)
plt.figure(figsize=(10,10))
nx.draw(G,pos,node_color = nodeColors ,node_size=800, with_labels= True)
plt.axis('equal')
plt.show()