GCC=g++
CFLAGS= -Wall -O2 -std=c++11 -ggdb
CPPCHECK=cppcheck
CPP_FILES := ej1.cpp 
OBJ_FILES := $(CPP_FILES:.cpp=.o)


ALL = ej1

all: $(ALL)


ej1: $(OBJ_FILES)
	$(GCC) $(CFLAGS) $^ -o $@

%.o:%.cpp
	$(GCC) $(CFLAGS) $^ -c -o $@

cppcheck:
	$(CPPCHECK) --enable=all --inconclusive --suppress=missingIncludeSystem --std=posix  $(CPP_FILES)

clean:
	rm $(ALL) *.o


.PHONY: clean ALL
