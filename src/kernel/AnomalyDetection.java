package kernel;

public class AnomalyDetection {
	
	private Iris[] set; // Conjunto de objetos do tipo Iris que serão analizados
	private float[][] resultTable; // Tabela NxN com as distâncias euclidianas entre os pontos
	private float[][] strangenessLevel; //Tabela Nx1 com o grau de estranheza de cada Iris
	
	/**
	 * Construtor.
	 * @param s Array de objeto Iris.
	 */
	public AnomalyDetection(Iris[] s) {
		
		// Inicialização das variáveis
		this.set = s;
		resultTable = new float[set.length][set.length];
		strangenessLevel = new float[set.length][1];
		
		// Inicialização das tabelas
		for(int i = 0; i < set.length; ++i) {
			strangenessLevel[i][0] = 0;
			for(int j = 0; j < set.length; ++j) resultTable[i][j] = 0;
		}
	}
	
	/**
	 * Normaliza uma valor de um intervalo entre 'min' e 'max' para uma faixa de 0 a 1.
	 * @param min Valor minimo no intervalo
	 * @param max Valor máximo no intervalo
	 * @param valor Valor a ser normalizado
	 * @return Valor normalizado
	 */
	private float normalize(float min, float max, float valor) {
		float maxRelative;
		float result;
		
		maxRelative = max - min; // Obtém o número relativo a 100%
		result = (valor - min) / maxRelative; // Regra de três
		
		return result;
	}
	
	/**
	 * Obtém os maiores e menores valores dos atributos da Iris.
	 * @return Um objeto MinMax que encapsula os valores máximos e mínimos.
	 */
	private MinMax getMinMax() {
		MinMax result; // Resultado
		
		result = new MinMax(set[0].getPetallen(), set[0].getPetallen(), set[0].getPetalwid(), set[0].getPetalwid(),
					set[0].getSepallen(), set[0].getSepallen(), set[0].getSepalwid(), set[0].getSepalwid()); // Instancia o resultado com o valor do primeiro item da coleção
		
		for(int i = 1; i < set.length; ++i) {
			
			// Comprimento da pétala
			if(set[i].getPetallen() > result.maxLenPet) result.maxLenPet = set[i].getPetallen();       // Compara e armazena o maior comprimento da pétala
			else if(set[i].getPetallen() < result.minLenPet) result.minLenPet = set[i].getPetallen();  // Compara e armazena o menor comprimento da pétala
			
			// Largura da pétala
			if(set[i].getPetalwid() > result.maxWidPet) result.maxWidPet = set[i].getPetalwid();       // Compara e armazena a maior largura da pétala
			else if(set[i].getPetalwid() < result.minWidPet) result.minWidPet = set[i].getPetalwid();  // Compara e armazena a menor largura da pétala
			
			// Comprimento da sépala
			if(set[i].getSepallen() > result.maxLenSep) result.maxLenSep = set[i].getSepallen();       // Compara e armazena o maior comprimento da sépala
			else if(set[i].getSepallen() < result.minLenSep) result.minLenSep = set[i].getSepallen();  // Compara e armazena o menor comprimento da sépala
			
			// Largura da sépala
			if(set[i].getSepalwid() > result.maxWidSep) result.maxWidSep = set[i].getSepalwid();       // Compara e armazena o maior largura da sépala
			else if(set[i].getSepalwid() < result.minWidSep) result.minWidSep = set[i].getSepalwid();  // Compara e armazena o menor largura da sépala
		}
		return result;
	}
	
	/**
	 * Normaliza os valores de todos objetos do conjunto 'set'.
	 */
 	private void normalizeSet() {
 		MinMax minmax = getMinMax();  // Obtém os valores mínimos e máximos
 		long time = System.currentTimeMillis();
 		
 		System.out.println("--- Normalizando entradas ---");
 		
 		for(int i = 0; i < set.length; ++i) {
 			//Normalizacao da pétala
 			set[i].setPetallen(normalize(minmax.minLenPet, minmax.maxLenPet, set[i].getPetallen())); // Normaliza o comprimento
 			set[i].setPetalwid(normalize(minmax.minWidPet, minmax.maxLenPet, set[i].getPetalwid())); // Normaliza a largura
 			
 			//Normalizacao da sépala
 			set[i].setSepallen(normalize(minmax.minLenSep, minmax.maxLenSep, set[i].getSepallen())); // Normaliza o comprimento
 			set[i].setSepalwid(normalize(minmax.minWidSep, minmax.maxWidSep, set[i].getSepalwid())); // Normaliza a largura
 			
 		}
 		
 		time = System.currentTimeMillis() - time;
 		System.out.println("--- Normalização encerrada após " + time + "ms ---");
	}
 	
 	/**
 	 * Calcula a distancia euclidiana entre duas Iris.
 	 * @param a Iris A.
 	 * @param b Iris B.
 	 * @return A distancia entre A e B.
 	 */
 	private float euclidianDistance(Iris a, Iris b) {
 		float result;
 		
 		result = (float) Math.pow(a.getPetallen() - b.getPetallen(), 2) + (float) Math.pow(a.getPetalwid() - b.getPetalwid(), 2) +
 				 (float) Math.pow(a.getSepallen() - b.getSepallen(), 2) + (float) Math.pow(a.getSepalwid() - b.getSepalwid(), 2);
 		
 		result = (float) Math.sqrt(result);
 		
 		return result;
 	}
 	
 	/**
 	 * Calcula a distância entre todas as Iris.
 	 */
 	private void knnCalc() {
 		int i, j;
 		
 		System.out.println("--- Iniciando o cálculo das distâncias euclidianas (KNN) ---");
 		long time = System.currentTimeMillis();
 		
 		for(i = 0; i < set.length; ++i) {
 			for(j = 0; j < set.length; ++j) {
 				resultTable[i][j] = euclidianDistance(set[i], set[j]);
 			}
 		}
 		
 		time = System.currentTimeMillis() - time;
 		System.out.println("--- Cálculo de distâncias encerrado após " + time + "ms ---");
 	}
 	
 	/**
 	 * Calcula o grau de estranheza com base nos K vizinhos.
 	 * @param k Número de vizinhos que serão analisados.
 	 */
 	private void knnStrange(int k) {
 		Neighbourhood nbh; // Classe que representa a vizinhança
 		
 		System.out.println("--- Iniciando o cálculo dos graus de estranheza (KNN) ---");
 		long time = System.currentTimeMillis();
 		
 		for(int i = 0; i< set.length; ++i) {  // Para todo elemento da coleção
 			nbh = new Neighbourhood(k); // Cria uma vizinhança para cada elemento
 			
 			 // Descoberta dos vizinhos mais próximos
 			for(int j = 0; j < set.length; ++j) {
 				
 				if(i != j) { // Exclui a distância do elemento para ele mesmo, que e sempre 0
 					//System.out.println("Comparando " + resultTable[i][j] + " com " + nbh.nextDoor);
 					if(resultTable[i][j] < nbh.nextDoor) { // Se o valor da distância de I para J for menor que o maior valor dos vizinhos atuais
 						nbh.nextDoor = resultTable[i][j]; // Ele assume o valor de maior vizinho
 						nbh.neighbour[nbh.doorNumber] = nbh.nextDoor; // Atualiza o valor no vetor de vizinhança
 						nbh.refresh(); // Atualiza os valores de maior vizinho e sua posicao
 						//System.out.println("Trocou e agora o maior é " + nbh.nextDoor);
 					}
 				}
 			}
 			strangenessLevel[i][0] = nbh.getStrangeness(); // Atualiza o seu grau de estranheza na tabela.
 		}
 		
 		time = System.currentTimeMillis() - time;
 		System.out.println("--- Cálculo dos graus de estranheza encerrado após " + time + "ms ---");
 	}
 	
 	/**
 	 * Busca a Iris com o maior grau de estranheza
 	 * @return A posição da Iris na colecao 'set'
 	 */
 	private int find() {
 		int result = 0; // Representa o indice da Iris na coleção 'set'
 		float max = strangenessLevel[0][0]; // Valor de mínimo iniciado com a primeira Iris da coleção.
 		
 		System.out.println("--- Iniciando busca do registro mais anômalo ---");
 		long time = System.currentTimeMillis();
 		
 		// Percorre a tabela com os níveis de estranheza e aquela Iris que possuir o maior nível e a mais anômala
 		for(int i = 1; i < set.length; ++i) if(strangenessLevel[i][0] > max) result = i;
 		
 		time = System.currentTimeMillis() - time;
 		System.out.println("--- Busca da anomalia encerrada após " + time + "ms ---");
 		
 		return result;
 	}
 	
 	/**
 	 * Encontrar o elemento mais anômalo de acordo com sua vizinhança de tamanho K
 	 * @param k Número de vizinhos para determinar o grau de estranheza
 	 * @return A Iris mais anômala
 	 */
 	public Iris detection(int k) {
 		Iris result = null;
 		Iris[] backup = new Iris[set.length];
 		
 		for(int i = 0; i < set.length; ++i) backup[i] =  set[i].clone();
 		
 		normalizeSet(); // Normalização
 		knnCalc(); // Cálculo das Distâncias
 		knnStrange(k); // Cálculo do grau de estranheza
 		//find() retorna o indice da iris com o maior grau de estranheza
 		//result = set[find()]; // Retorna a Iris com valores normalizados
 		result = backup[find()]; // Retorna a Iris com os valores originais
 		
 		return result;
 	}
 	
 	/**
 	 * Classe criada para encapsular os valores mínimos e máximos de uma coleção de dados.
 	 * Dessa maneira só é preciso percorrer a coleção uma única vez.
 	 * @author João Pedro Santos de Moura
 	 * 
 	 */
  	private class MinMax {
 		
		public float minLenPet;
 		public float maxLenPet;
 		public float minWidPet;
 		public float maxWidPet;
 		
		public float minLenSep;
 		public float maxLenSep;
 		public float minWidSep;
 		public float maxWidSep;
 		
		/**
		 * Construtor usando todos os campos.
		 * @param minLenPet Comprimento mínimo da pétala
		 * @param maxLenPet Comprimento máximo da pétala
		 * @param minWidPet Largura mínima da pétala
		 * @param maxWidPet Largura máxima da pétala
		 * @param minLenSep Comprimento mínimo da sépala
		 * @param maxLenSep Comprimento máximo da sépala
		 * @param minWidSep Largura mínima da sépala
		 * @param maxWidSep Largura máxima da sépala
		 */
		public MinMax(float minLenPet, float maxLenPet, float minWidPet, float maxWidPet, float minLenSep, float maxLenSep,
				float minWidSep, float maxWidSep) {
			this.minLenPet = minLenPet;
			this.maxLenPet = maxLenPet;
			this.minWidPet = minWidPet;
			this.maxWidPet = maxWidPet;
			this.minLenSep = minLenSep;
			this.maxLenSep = maxLenSep;
			this.minWidSep = minWidSep;
			this.maxWidSep = maxWidSep;
		}
 		
 		
 	}
  	
  	/**
  	 * Clasee criada para encapsular os métodos relativos aos vizinhos.
  	 * @author João Pedro Santos de Moura
  	 *
  	 */
  	private class Neighbourhood {
  		
  		private float[] neighbour;
  		private float nextDoor;
  		private int doorNumber;
  		
  		/**
  		 * Construtor
  		 * @param k Tamanho da vizinhança
  		 */
  		public Neighbourhood(int k) {
  			neighbour = new float[k];
  			
  	 		for(int i = 0; i < neighbour.length; ++i) { // Inicializacao do vetor de distancia dos vizinhos
  	 			neighbour[i] = 999999;
  	 		}
  	 		
  	 		nextDoor = neighbour[0];
  	 		doorNumber = 0;
  		}
  		
  		/**
  		 * Atualiza os valores do vizinho mais distante e a sua posicao no vetor
  		 */
  		private void refresh() {
  			for(int i = 0; i < neighbour.length; ++i) {
  				if(neighbour[i] > nextDoor) {
  					nextDoor = neighbour[i];
  					doorNumber = i;
  				}
  			}
  		}
  		
  		/**
  		 * Calcula o grau de estranheza de acordo com os seus vizinhos.
  		 * @return Grau de estranheza.
  		 */
  		private float getStrangeness() {
  			float result = 0;
  			
  			for(int i = 0; i < neighbour.length; ++i) {
  				result += neighbour[i];
  			}
  			
  			result = result / neighbour.length;
  			return result;
  		}
  	}
}