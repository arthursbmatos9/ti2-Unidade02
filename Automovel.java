package exercicio02;

public class Automovel {
	private String marca;
	private String modelo;
	private String placa;
	private int ano;
	
	public Automovel() {
		this.marca = "";
		this.modelo = "";
		this.placa = "";
		this.ano = -1;
	}
	
	public Automovel(String marca, String modelo, String placa, int ano) {
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.ano = ano;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Automovel [marca=" + marca + ", modelo=" + modelo + ", placa=" + placa + ", ano=" + ano + "]";
	}	
}
