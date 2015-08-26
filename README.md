# ADA - Anomaly Detection Application - ptBR

Esse aplicativo foi desenvolvido como artefato de trabalho prático da disciplina Sistemas de Apoio a Decisão (CEA462) da Universidade Federal de Ouro Preto (UFOP).

Objetivo
--------

A meta deste aplicativo é aplicar o algoritmo ([K-Nearest Neighbor](http://www.saedsayad.com/k_nearest_neighbors.htm)) para detecção de anomalia na base de dados
[Iris](https://archive.ics.uci.edu/ml/datasets/Iris).

Metodologia
-----------

A aplicação é constituída por 3 pacotes: [kernel](https://github.com/jpmoura/AnomalyDetection/tree/master/src/kernel) , [main](https://github.com/jpmoura/AnomalyDetection/tree/master/src/main)
 e [util](https://github.com/jpmoura/AnomalyDetection/tree/master/src/util) .

Os pacotes [main](https://github.com/jpmoura/AnomalyDetection/tree/master/src/main) e [util](https://github.com/jpmoura/AnomalyDetection/tree/master/src/util) possuem as classes
[Main](https://github.com/jpmoura/AnomalyDetection/blob/master/src/main/Main.java) e classes de [leitura](https://github.com/jpmoura/AnomalyDetection/blob/master/src/util/FileManager.java) /
[conversão](https://github.com/jpmoura/AnomalyDetection/blob/master/src/util/Converter.java) respectivamente. Ambas são classes de apoio para as classes que estão contidas no pacote
[kernel](https://github.com/jpmoura/AnomalyDetection/tree/master/src/kernel), que são as classes [Iris](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/Iris.java)
e [AnomalyDetection](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/AnomalyDetection.java).

Basicamente a classe [Iris](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/Iris.java) encapsula todas as características de uma Iris presente no
[data set](https://github.com/jpmoura/AnomalyDetection/blob/master/iris.data) 
enquanto a classe [AnomalyDetection](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/AnomalyDetection.java) realiza a mineração de dados através do algoritmo KNN.

# ADA - Anomaly Detection Application - enUS

This application was develop as a artifact of a practical project of the Decision Support Systems of the Federal University of Ouro Preto (UFOP), Brazil.

### Goal

The main goal of this application is to apply the K-Nearest Neighbor ([KNN](http://www.saedsayad.com/k_nearest_neighbors.htm))
to detect anomalies in the [Iris](https://archive.ics.uci.edu/ml/datasets/Iris) dataset.

### Methodology

The application is composed by 3 packages: [kernel](https://github.com/jpmoura/AnomalyDetection/tree/master/src/kernel), [main](https://github.com/jpmoura/AnomalyDetection/tree/master/src/main)
 and [util](https://github.com/jpmoura/AnomalyDetection/tree/master/src/util).

The [main](https://github.com/jpmoura/AnomalyDetection/tree/master/src/main) and [util](https://github.com/jpmoura/AnomalyDetection/tree/master/src/util) packages have the
[Main](https://github.com/jpmoura/AnomalyDetection/blob/master/src/main/Main.java) and [reading](https://github.com/jpmoura/AnomalyDetection/blob/master/src/util/FileManager.java) /
[conversion](https://github.com/jpmoura/AnomalyDetection/blob/master/src/util/Converter.java) classes respectively. Both are support classes for the classes into
[kernel](https://github.com/jpmoura/AnomalyDetection/tree/master/src/kernel) package, which are the [Iris](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/Iris.java) and
[AnomalyDetection](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/AnomalyDetection.java).

Basically, the [Iris](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/Iris.java) class encapsulates all the characteristics of a Iris in the
[data set](https://github.com/jpmoura/AnomalyDetection/blob/master/iris.data) and the
[AnomalyDetection](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/AnomalyDetection.java) class does the data mining via KNN algorithm.