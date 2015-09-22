#! /usr/bin/python

#import random

tams = [50,100,200,300,500,750,1000,1250,1500,1750,2000,2500,3000,4000,5000,7000,8000,9000,10000,11000,12000,13000,14000,15000,16000]
for i in range(len(tams)):
    ## hacemos peor caso
    n = tams[i]
    fout = open("./casos/{:03d}.in".format(i), 'w')
    fout.write("{}\n".format(n))
    for p in range(0, n):
        for d in range(p+1, n+1):
            if (p == n-1) and (d == n):
                fout.write('{} {}'.format(p,d))
            else:
                fout.write('{} {}; '.format(p,d))
    fout.close()
    tambytes = 16 * n + 8 * ((n * (n+1))/2)
    print("Test {:03d} ocupa {:06}MBs".format(i,((tambytes/1024)/1024)))

