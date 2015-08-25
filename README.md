Anomaly Detection Application
=============================

Esse aplicativo foi desenvolvido como artefato de trabalho prático da disciplina Sistemas de Apoio a Decisão (CEA462) da Universidade Federal de Ouro Preto (UFOP).

Objetivo
--------

A meta deste aplicativo é aplicar o algoritmo K-Nearest Neighbor ([KNN](http://www.saedsayad.com/k_nearest_neighbors.htm)) para detecção de anomalia na base de dados [Iris](https://archive.ics.uci.edu/ml/datasets/Iris).

Metodologia
-----------

A aplicação é constituída por 3 pacotes: [kernel](https://github.com/jpmoura/AnomalyDetection/tree/master/src/kernel), [main](https://github.com/jpmoura/AnomalyDetection/tree/master/src/main)
 e [util](https://github.com/jpmoura/AnomalyDetection/tree/master/src/util).

Os pacotes [main](https://github.com/jpmoura/AnomalyDetection/tree/master/src/main) e [util](https://github.com/jpmoura/AnomalyDetection/tree/master/src/util) possuem as classes
[Main](https://github.com/jpmoura/AnomalyDetection/blob/master/src/main/Main.java)e classes de [leitura](https://github.com/jpmoura/AnomalyDetection/blob/master/src/util/FileManager.java)/
[conversão](https://github.com/jpmoura/AnomalyDetection/blob/master/src/util/Converter.java) respectivamente. Ambas são classes de apoio para as classes que estão contidas no pacote
[kernel](https://github.com/jpmoura/AnomalyDetection/tree/master/src/kernel), que são as classes [Iris](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/Iris.java)
e [AnomalyDetection](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/AnomalyDetection.java).

Basicamente a classe [Iris](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/Iris.java) encapsula todas as características de uma Iris presente no [data set](https://github.com/jpmoura/AnomalyDetection/blob/master/iris.data)
enquanto a classe [AnomalyDetection](https://github.com/jpmoura/AnomalyDetection/blob/master/src/kernel/AnomalyDetection.java) realiza a mineração de dados através do algoritmo KNN.