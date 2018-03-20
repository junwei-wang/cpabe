Building CPABE
==================

## Dependency

[jPBC](http://gas.dia.unisa.it/projects/jpbc/) 1.2.1, which can be found
[here](https://sourceforge.net/projects/jpbc/files/jpbc_1_2_1/).


## Build
```
mvn package && script/mv_jars.sh
```

## Demo

### BSWABE
```
java -cp "jars/*" co.junwei.bswabe.Demo
```

### CPABE
```
java -cp "jars/*" co.junwei.cpabe.Demo
```

## Get CPABE into Your Project

Check out [JitPack](https://jitpack.io/#junwei-wang/cpabe/)
