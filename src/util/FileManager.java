package util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Classe para gerenciar acesso a arquivos, tanto leitura quanto escrita. Essa classe mantém Buffers de leitura e escrita mas é possível
 * criar 'Streams' de leitura e escrita também.
 * @author João Pedro Santos de Moura
 * @version 1.0
 */
public class FileManager {
	private File file;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	/**
	 * Instancia um objeto File e pode abrir canais de leitura e escrita.
	 * @param f String que contém o nome do arquivo caso esteja na mesma pasta onde a aplicação esteja executando ou o caminho completo do arquivo caso esteja em outra localidade.
	 * @param wr Se TRUE então abre para leitura e escrita, senão só instancia o arquivo.
	 */
	public FileManager(String f, boolean wr) {
		file = new File(f);
		if(wr) {
			openReader();
			openWriter();
		}
		else {
			reader = null;
			writer = null;
		}
	}
	
	/**
	 * Abre um canal para escrita no arquivo
	 * @return TRUE caso o canal seja aberto e FALSE caso contrário.
	 */
	public boolean openWriter() {
		if (file.exists()) {
			try {
				writer = new BufferedWriter(new FileWriter(file));
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		else return false;
	}
	
	/**
	 * Abre um canal para leitura do arquivo
	 * @return TRUE caso o canal seja aberto e FALSE caso contrário.
	 */
	public boolean openReader() {
		if (file.exists()) {
			try {
				reader = new BufferedReader(new FileReader(file));
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		else return false;
	}
	
	/**
	 * Abre um canal de escrita e leitura do arquivo.
	 * @return TRUE caso os canais sejam abertos e FALSE caso contrário.
	 */
	public boolean openAll() {
		return (openWriter() && openReader());
	}
	
	/**
	 * Fecha o canal de escrita do arquivo.
	 * @return TRUE caso o canal seja fechado e FALSE caso contrário.
	 */
	public boolean closeWriter() {
		if(file.exists()) {
			try {
				writer.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		else return true;
	}
	
	/**
	 * Fecha o canal de leitura do arquivo.
	 * @return TRUE caso o canal seja fechado e FALSE caso contrário.
	 */
	public boolean closeReader() {
		if(file.exists()) {
			try {
				reader.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		else return true;
	}
	
	/**
	 * Fecha o canal de escrita e leitura do arquivo.
	 * @return TRUE caso os canais sejam fechados e FALSE caso contrário.
	 */
	public boolean closeAll() {
		return (closeWriter() && closeReader());
	}
	
	public void setFile(String f) {
		file = new File(f);
	}
	

	public BufferedReader getReader() {
		return reader;
	}
	

	public BufferedWriter getWriter() {
		return writer;
	}
	
	public FileInputStream getInputStream() {
		if(file.exists()) {
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else return null;
	}
	
	public FileOutputStream getOutputStream() {
		if(file.exists()) {
			try {
				return new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else return null;
	}
	
	public void closeStream(FileInputStream in, FileOutputStream out) {
		if(in != null)
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(out != null)
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
