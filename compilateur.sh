#!/bin/bash

# . dossier de TP/TD (. si courant)
# labyrinthe nomApp

if test ! -d ./bin 
    then mkdir ./bin;
fi
if test ! -d ./src 
    then mkdir ./src;
fi
if test ! -d ./doc 
    then mkdir ./doc;
fi

srcSize=`du ./src| cut -f1`
echo $srcSize
if test $srcSize -eq 0
    then touch ./src/Main.java
fi

if test ! -s ./src/Main.java
then echo "public class Main {
    public static void main(String[] argv) {
        System.out.println("\"Hello World!\"");
    }
}" >> ./src/Main.java;
fi

javac -d ./bin ./src/*.java;
java -classpath ./bin Main;
javadoc -d ./doc ./src/*.java;

if test ! -f ./labyrinthe.mf 
    then touch ./labyrinthe.mf;
fi

if test ! -s ./labyrinthe.mf 
then echo "Manifest-Version: 1.0
Main-Class: Main" >> ./labyrinthe.mf;
fi

cd ./bin
jar cmf .labyrinthe.mf ../labyrinthe.jar *.class
cd ..
java -jar labyrinthe.jar