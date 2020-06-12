package it.polito.tdp.food.model;

public class Correlate {
	String vicino;
	Double peso;
	/**
	 * @param vicino
	 * @param peso
	 */
	public Correlate(String vicino, Double peso) {
		super();
		this.vicino = vicino;
		this.peso = peso;
	}
	public String getVicino() {
		return vicino;
	}
	public void setVicino(String vicino) {
		this.vicino = vicino;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Vicino=" + vicino + ", Peso=" + peso;
	}
	
	
}
