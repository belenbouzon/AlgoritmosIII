__author__ = 'Belen Bouzon'
import networkx as nx
import matplotlib.pyplot as plt
import colorsys

#Recibo <numero de nodo, su numero de color>
iteracionesColores = open("iteraciones.txt",'r')
nodes = eval(iteracionesColores.readline())
cantColores = 60

#Recibo las aristas
aristas = [(6,3),(4,5),(5,5),(6,6)]

cantidadDeNodos = len(nodes)

#Hago un array con tantos colores como cantidad de nodos tengo
HSV_tuples = [(x/(cantidadDeNodos*0.1555555555555), 0.3, 0.9) for x in range(cantColores+2)]
RGB_tuples = map(lambda x: colorsys.hsv_to_rgb(*x), HSV_tuples)

index = 0
while nodes != []:
    #Armo un array con los colores ordenados en el orden natural de los nodos
    nodeColors = []

    for i in range (0, cantidadDeNodos):
        color =  [nodo[1] for nodo in nodes if nodo[0] == i][0]
        nodeColors.insert(i,RGB_tuples[color])
    G=nx.Graph()
    G.add_nodes_from([x[0] for x in nodes])
    G.add_edges_from(aristas)
    pos=nx.circular_layout(G)
    plt.figure(figsize=(8,8))
    #nx.draw_graphviz(G,prog='neato',node_color = nodeColors, node_size=700, with_labels = True)
    nx.draw(G,pos,node_color = nodeColors ,node_size=700, with_labels= True, alpha=0.7, node_shape="h", linewidths=0.5, width= 0.5,style= 'solid', font_size =10)
    plt.axis('equal')
    plt.savefig(str(cantidadDeNodos).zfill(3) + "Nodos.png", transparent = False)
    plt.close()
    index += 1
    var = iteracionesColores.readline()
    if var != "":
        nodes= eval(var)
    else:
        nodes = []

iteracionesColores.close()
#h = hexagono
