package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kernel.Iris;

/**
 * Classe responsável pela conversão dos dados no arquivo para objetos em tempo de execução.
 * @author João Pedro Santos de Moura
 *
 */
public class Converter {
	
	FileManager fm;
	BufferedReader reader;
	
	/**
	 * Construtor.
	 * @param s String com o nome do arquivo.
	 */
	public Converter(String s) {
		fm = new FileManager(s, false);
		if(fm.openReader()) reader = fm.getReader();
		else reader = null;
	}
	
	/**
	 * Verifica se a linha lida é uma linha válida para processamento.
	 * @param array String de caracteres convertida em um vetor de char.
	 * @return TRUE se é uma linha válida, FALSE caso contrário.
	 */
	private boolean checkLine(char[] array) {
		if(array == null) return false;
		else if(array.length == 0) return false;
		else if(array[0] == '/' || array[0] == ' ') return false;
		else return true;
	}
	
	/**
	 * Lê todos os registros dos arquivos e converte em objetos Iris.
	 * @return Uma coleção de Iris.
	 */
	public Iris[] getData() {
		Iris[] result = null;
		List <Iris> set = new ArrayList<Iris>();
		String line;
		long time = 0;
		
		try {
			
			System.out.println("--- Iniciando conversão dos registros em arquivo para objetos ---");
			time = System.currentTimeMillis();
			
			line = reader.readLine();
			
			while(checkLine(line.toCharArray())) {
				set.add(makeIris(line));
				line = reader.readLine();
			}
			
			// CONVERTER LIST PARA O ARRAY
			result = new Iris[set.size()];
			for(int i = 0; i < set.size(); ++i) result[i] = set.get(i);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fm.closeReader();
		
		time = System.currentTimeMillis() - time;
		System.out.println("--- Conversão finalizada em " + time + "ms ---");
		
		return result;
	}
	
	/**
	 * Lê todos os registros dos arquivos e converte em objetos Iris. Método sobrecarregado para leitura de cada grupo.
	 * @param Limite de objetos lidos.
	 * @return Uma coleção de Iris.
	 */
	public Iris[] getData(int lim) {
		Iris[] result = null;
		List <Iris> set = new ArrayList<Iris>();
		String line;
		long time = 0;
		int count = 0;
		
		try {
			
			System.out.println("--- Iniciando conversão dos registros em arquivo para objetos ---");
			time = System.currentTimeMillis();
			
			line = reader.readLine();
			++count;
			
			while(checkLine(line.toCharArray())) {
				set.add(makeIris(line));
				if(count == lim) break;
				line = reader.readLine();
				++count;
			}
			
			// CONVERTER LIST PARA O ARRAY
			result = new Iris[set.size()];
			for(int i = 0; i < set.size(); ++i) result[i] = set.get(i);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		time = System.currentTimeMillis() - time;
		System.out.println("--- Conversão finalizada em " + time + "ms ---");
		
		return result;
	}
	
	/**
	 * Converte a linha lida em um objeto do tipo Iris.
	 * @param s Linha a ser convertida.
	 * @return Um objeto Iris.
	 */
	private Iris makeIris(String s) {
		Iris result;
		float sl, sw, pl, pw;
		String t;
		
		sl = Float.parseFloat(s.substring(0, 3));
		sw = Float.parseFloat(s.substring(4, 7));
		pl = Float.parseFloat(s.substring(8, 11));
		pw = Float.parseFloat(s.substring(12, 15));
		t = s.substring(16);
		
		result = new Iris(sl, sw, pl, pw, t);
		//System.out.println("Gerada-> "+ result.toString() + "\n");
		return result;
	}

}
