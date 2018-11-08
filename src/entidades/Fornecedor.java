package entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import combos.Combo;
import comparable.Comparador;

/*
 * Classe Fornecedor : Classe responsavel pelo objeto fornecedor e onde fica suas informações como
 * nome(String), email(String) e telefone(String).
 */

public class Fornecedor {
	private String nome;
	private String email;
	private String telefone;
	private Map<String, Produto> produtos;

	public Map<String, Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Map<String, Produto> produtos) {
		this.produtos = produtos;
	}

	public Fornecedor(String nome, String email, String telefone) throws Exception {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		produtos = new HashMap<String, Produto>();

	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * Método editar. Esse método fica localizado em Fornecedor pois aqui onde fica
	 * todas as informações de um fornecedor então ele que deve ficar responsavel
	 * por controlar sua modificação.
	 * 
	 * O método editarFornecedor irá pegar informações como email e seu telefone e
	 * vai mudar de acordo com seu novo valor, o editarFornecedor não pode alterar
	 * seu nome e rejeita qualquer entrada invalida.
	 */

	public void editarFornecedor(String atributo, String novoValor) {
		if (atributo.equals("nome")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser editado.");
		} else if (atributo == null || atributo.equals("")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		} else if (!(atributo.equals("telefone") || atributo.equals("email"))) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao existe.");
		} else if (novoValor == null || novoValor.equals("")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		} else if (atributo.equals("email")) {
			this.email = novoValor;
		} else if (atributo.equals("telefone")) {
			this.telefone = novoValor;
		}

	}

	public String cadastrarProduto(String nome, String descricao, double preco) {
		Produto produto = new ProdutoSimp(nome, descricao, preco);
		if (this.produtos.containsKey(produto.getNome() + produto.getDescricao())) {
			throw new IllegalArgumentException("Erro no cadastro de produto: produto ja existe.");
		}
		this.produtos.put(produto.getNome() + produto.getDescricao(), produto);
		return produto.getNome() + produto.getDescricao();
	}

	public String exibirProduto(String nome) {
		if (!this.produtos.containsKey(nome)) {
			throw new IllegalArgumentException("Erro na exibicao de produto: produto nao existe.");
		}
		Produto produto = produtos.get(nome);
		return produto.toString();
	}

	public String representarProdutos() {
		String retorno = "";
		int cont = 0;
		if (this.produtos.isEmpty()) {
			throw new IllegalArgumentException("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		}
		List<Produto> produtosLista = new ArrayList<>(this.produtos.values());
		Collections.sort(produtosLista, new Comparador());
		for (Produto produto : produtosLista) {
			retorno += this.nome + " - " + produto.toString();
			cont += 1;
			if (cont < produtosLista.size()) {
				retorno += " | ";
			}
		}
		return retorno;
	}

	public String editarProduto(String chave, double novoPreco) {
		if (this.produtos.containsKey(chave)) {
			Produto produto = produtos.get(chave);
			produto.setPreco(novoPreco);
		}
		return produtos.toString();
	}

	public String removerProduto(String chave) {
		if (this.produtos.containsKey(chave)) {
			produtos.remove(chave);
		} else if (!this.produtos.containsKey(chave)) {
			throw new IllegalArgumentException("Erro na remocao de produto: produto nao existe.");
		}
		return produtos.toString();
	}

	@Override
	public String toString() {
		return this.nome + " - " + this.email + " - " + this.telefone;

		// inicio de combo
	}

	public String cadastrarCombo(String nome, String descricao, double fator, String produtos) {
		double valor = 0;
		String[] listaProdutos = produtos.split(", ");
		if (produtos.contains(" + ")) {
			throw new IllegalArgumentException(
					"Erro no cadastro de combo: um combo n�o pode possuir combos na lista de produtos.");
		}
		for (int i = 0; i < listaProdutos.length; i++) {
			String[] prod = listaProdutos[i].split(" - ");
			if (!this.produtos.containsKey(prod[0] + prod[1])) {
				throw new IllegalArgumentException("Erro no cadastro de combo: produto nao existe.");
			}
			Produto produto = this.produtos.get(prod[0] + prod[1]);
			valor += produto.getPreco();
		}
		if (this.produtos.containsKey(nome + descricao)) {
			throw new IllegalArgumentException("Erro no cadastro de combo: combo ja existe.");
		}

		Combo combos = new Combo(nome, descricao, valor * (1 - fator), fator);
		this.produtos.put(nome + descricao, combos);
		return combos.getNome() + combos.getDescricao();
	}

	public String exibirCombos() {
		String retorno = "";
		int cont = 0;
		List<Produto> listCombos = new ArrayList<Produto>(this.produtos.values());
		Collections.sort(listCombos, new Comparador());
		for (Produto combos : listCombos) {
			retorno += combos.toString();
			cont += 1;
			if (cont < listCombos.size()) {
				retorno += " | ";
			}
		}
		return retorno;
	}

	public String editarCombo(String chave, double novoFator) {
		if (this.produtos.containsKey(chave)) {
			Combo produto = (Combo)produtos.get(chave);
			double preco = produto.getPreco()/(1-produto.getFator());
			produto.setPreco(preco*(1- novoFator));
			produto.setFator(novoFator);
		}else if (!this.produtos.containsKey(chave)) {
			throw new IllegalArgumentException("Erro na edicao de combo: produto nao existe.");
		}
		return produtos.toString();
	}
	
}
