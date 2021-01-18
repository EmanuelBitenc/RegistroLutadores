package model;

public class Lutador {
	private double peso, altura;
	private String nome;
	private int id;
	
	public Lutador() {		
	}
	
	public Lutador(int id) {
		this.id = id;
	}
	
	public Lutador(String nome, double peso, double altura) {
		this.nome = nome;
		this.peso = peso;
		this.altura = altura;
	}

	public Lutador(double peso, double altura, String nome, int id) {
		this.peso = peso;
		this.altura = altura;
		this.nome = nome;
		this.id = id;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Lutador [peso=" + peso + ", altura=" + altura + ", nome=" + nome + ", id=" + id + "]";
	}
	


}
