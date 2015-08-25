package kernel;

/**
 * Classe representativa da Íris.
 * @author João Pedro Santos de Moura
 *
 */
public class Iris {
	
	private float sepallen; // Comprimento da sépala
	private float sepalwid; // Largura da sépala
	private float petallen; // Comprimento da pétala
	private float petalwid; // Largura da pétala
	private String type;    // Classe da Íris (Iris Setosa, Iris Versicolour ou Iris Virginica)
	
	/**
	 * Construtor que recebe todos os atributos.
	 * @param sl Comprimento da sépala
	 * @param sw Largura da sépala
	 * @param pl Comprimento da pétala
	 * @param pw Largura da pétala
	 * @param t Classe da Íris
	 */
	public Iris(float sl, float sw, float pl, float pw, String t) {
		this.sepallen = sl;
		this.sepalwid = sw;
		this.petallen = pl;
		this.petalwid = pw;
		this.type = t;
	}
	
	/**
	 * Realiza a cópia do objeto invocador.
	 * @return Uma nova instância igual a intância invocadora.
	 */
	public Iris clone() {
		Iris result;
		result = new Iris(this.sepallen, this.sepalwid, this.petallen, this.petalwid, this.type);
		return result;
	}
	
	/**
	 * Sobrecarga do método toString(). Retorna uma string representando o conteúdo do objeto.
	 */
	public String toString() {
		return "Tipo: " + this.type + "\nComprimento da sépala: " + this.sepallen + "	Largura da sépala: " + this.sepalwid
				+ "\nComprimento da pétala: " + this.petallen + "	Largura da pétala: " + this.petalwid;
	}
	

	public float getSepallen() {
		return sepallen;
	}
	

	public float getSepalwid() {
		return sepalwid;
	}
	

	public float getPetallen() {
		return petallen;
	}
	

	public float getPetalwid() {
		return petalwid;
	}

	public String getType() {
		return type;
	}

	public void setSepallen(float sepallen) {
		this.sepallen = sepallen;
	}

	public void setSepalwid(float sepalwid) {
		this.sepalwid = sepalwid;
	}

	public void setPetallen(float petallen) {
		this.petallen = petallen;
	}

	public void setPetalwid(float petalwid) {
		this.petalwid = petalwid;
	}	
	
	
}
