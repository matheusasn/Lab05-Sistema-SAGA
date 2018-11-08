package combos;

import entidades.Produto;

public class Combo extends Produto {
	private double fator;
	
	public Combo(String nome, String descricao, double preco, double fator) {
		super(nome, descricao,preco);		
		this.fator = fator;
	}

	public double getFator() {
		return fator;
	}

	public void setFator(double fator) {
		this.fator = fator;
	}

	
}
