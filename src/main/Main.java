package main;

import java.util.Scanner;
import util.Converter;
import kernel.AnomalyDetection;
import kernel.Iris;

/**
 * Classe principal do programa de detecção de anomalia, responsável por realizar a interação com usuário.
 * @author João Pedro Santos de Moura
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long time;
		int k;
		Scanner input = new Scanner(System.in);
		
		System.out.print(" 				Algoritmo de Detecção de Anomalias\n\n");
		System.out.print("Implementado por João Pedro Santos de Moura como artefato do Trabalho Prático 1 da disciplina"
				+ " CEA462 - Sistemas de Apoio à Decisão\n\n");
		
		if(args.length == 0) { // Se K não for passado como argumento na chamada do programa, então e necessário perguntar ao usuário
			System.out.print("Informe a quantidade K de vizinhos a ser considerada: ");
			k = input.nextInt();
		}
		else k = Integer.parseInt(args[0]); //Senão obtem o K do argumento informado
		
		while(k <= 0) {
			System.out.print("K DEVE ser maior que 0\n\nInforme novamente a quantidade K de vizinhos a ser considerada: ");
			input.nextLine();
			k = input.nextInt();
		}
		
		//input.nextLine();
		System.out.println("Deseja detectar anomalias pelo tipo da Iris ou dentro de todo o conjunto?");
		String r;
		input.nextLine();
		r = input.nextLine();
		input.close();
		
		System.out.printf("\n\nIniciando a detecção de anomalia considerando o(s) %d vizinho(s) mais próximo(s)", k);
		time = System.currentTimeMillis();
		
		Converter conv = new Converter("iris.data");
		
		if(r.contains("ti") || r.contains("1")) { // Se a resposta contiver um 's' ou o número 1, então significa que deve-se procurar anomalias por grupo
			System.out.println(" para cada tipo de Iris.\n");
			
			Iris[] anomalies = new Iris[3];
			
			for(int i = 0; i < 3; ++i) {
				System.out.println("---> Iniciando a detecção de um tipo <---\n");
				AnomalyDetection ad = new AnomalyDetection(conv.getData(50));
				anomalies[i] = ad.detection(k);
				System.out.print("\n");
			}
			
			time = System.currentTimeMillis() - time;
			System.out.println("\nProcessamento encerrado após " + time + "ms.\n");
			
			System.out.print("---> Resultados <---\n\n" + anomalies[0] + "\n\n" + 
			                  anomalies[1] + "\n\n" + anomalies[2]);
			
		}
		else { // Senão procura anomalias desconsiderando a classe da iris
			System.out.print("\n");
			Iris anomaly;
			AnomalyDetection ad = new AnomalyDetection(conv.getData());
			anomaly = ad.detection(k);
			time = System.currentTimeMillis() - time;
			System.out.print("\nProcessamento encerrado após " + time + "ms!\n\n ---> Resultados <---\n\n"
					+ "A íris mais anômala é:\n\n" + anomaly.toString());
		}
		
	}

}
