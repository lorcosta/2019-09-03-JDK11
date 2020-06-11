package it.polito.tdp.food.model;

public class Adiacenze {
	private String portion1;
	private String portion2;
	private Integer peso;
	/**
	 * @param portion1
	 * @param portion2
	 * @param peso
	 */
	public Adiacenze(String portion1, String portion2, Integer peso) {
		super();
		this.portion1 = portion1;
		this.portion2 = portion2;
		this.peso = peso;
	}
	public String getPortion1() {
		return portion1;
	}
	public void setPortion1(String portion1) {
		this.portion1 = portion1;
	}
	public String getPortion2() {
		return portion2;
	}
	public void setPortion2(String portion2) {
		this.portion2 = portion2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
}
