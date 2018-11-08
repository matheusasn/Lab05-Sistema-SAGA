package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import comparable.Comparador;
import entidades.Fornecedor;
import entidades.Produto;

import java.util.List;

/*
 * Classe Controladora de Fornecedor: Responsavel por controlar cada parametro que fornecedor.
 * 
 * Fornecedor irá ficar guardado em um MAP para facilitar o modo que achalo. Ele será encontrado pelo seu nome(String).
 */

public class ControladorFornecedor {
	private Map<String, Fornecedor> fornecedores;

	public ControladorFornecedor() {
		fornecedores = new HashMap<String, Fornecedor>();
	}

	/*
	 * Método cadastrar, responsavel por receber as informações de fornecedor
	 * nome(String), email(String) e telefone(String) e tratalas para ser
	 * cadastrada, caso alguma dessas informações não apresente os parametros certos
	 * um IllegalArgumentException é lançado.
	 */
	public String cadastrar(String nome, String email, String telefone) throws Exception {
		if (nome == null || "".equals(nome)) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
		} else if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
		} else if (this.fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Erro no cadastro de fornecedor: fornecedor ja existe.");
		}
		fornecedores.put(nome, new Fornecedor(nome, email, telefone));
		return nome;
	}

	/*
	 * Método representação: Responsavel por localizar um fornecedor atravez de seu
	 * nome(String) e retornar seu toString. Caso o fornecedor não seja localizado é
	 * lançado um IllegalArgumentException.
	 */

	public String representacao(String nome) {
		if (this.fornecedores.containsKey(nome)) {
			return this.fornecedores.get(nome).toString();
		}
		throw new IllegalArgumentException("Erro na exibicao do fornecedor: fornecedor nao existe.");
	}

	/*
	 * Método representações: Neste método um ArrayList é cliado atravez do map de
	 * fornecedores para que a class comparador possa organizar de acordo com seu
	 * toString e logo depois disso o mesmo ArrayList cliado é colocado em um for
	 * para que todas as representações presentes retornem no formato de Texto.
	 */
	public String representacoes() {
		List<Fornecedor> listFornecedores = new ArrayList<Fornecedor>(this.fornecedores.values());
		Collections.sort(listFornecedores, new Comparador());
		String retorno = "";
		int cont = 0;
		for (Fornecedor fornecedores : listFornecedores) {
			retorno += fornecedores.toString();
			cont += 1;
			if (cont < listFornecedores.size()) {
				retorno += " | ";
			}
		}
		return retorno;
	}

	/*
	 * Método editar: Responsavel por editar as informaçoes de fornecedor localiza o
	 * fornecedor pelo seu nome caso encontre ele irá pegar todas as informações que
	 * desejam mudar no formado de String e irá chamar o método editarFornecedor
	 * localizado na classe Fornecedor. Caso o nome não seja valido irá lançar um
	 * IllegalArgumentException com a representação do erro encontrado.
	 */
	public void editar(String chave, String atributo, String novoValor) {
		if (atributo.equals("nome")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser editado.");
		} else if (atributo == null || atributo.equals("")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		} else if (!(atributo.equals("telefone") || atributo.equals("email"))) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao existe.");
		} else if (novoValor == null || novoValor.equals("")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		} else if (!this.fornecedores.containsKey(chave)) {
			throw new IllegalArgumentException("Erro na exibicao do fornecedor: cpf invalido.");
		} else if (this.fornecedores.containsKey(chave)) {

			this.fornecedores.get(chave).editarFornecedor(atributo, novoValor);
		}

	}

	/*
	 * Método remover: Responsavel por receber o nome(String) do fornecedor e
	 * encontralo no map e caso o usuario deseje removelo ele irá remover o cliente,
	 * caso o nome seja invalido um IllegalArgumentException é lançado.
	 */
	public void remover(String nome) {
		if (nome == null || nome.equals("")) {
			throw new IllegalArgumentException("Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio.");
		} else if (!this.fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Erro na exibicao do fornecedor: fornecedor nao existe.");
		} else {
			this.fornecedores.remove(nome);
		}

	}

	public String cadastrarProduto(String nomeFornecedor, String nome, String descricao, double preco) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		} else if (descricao == null || descricao.isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
		} else if (nomeFornecedor == null || nomeFornecedor.isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if (preco <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de produto: preco invalido.");
		} else if (!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao existe.");
		}
		Fornecedor fornecedor = fornecedores.get(nomeFornecedor);
		return fornecedor.cadastrarProduto(nome, descricao, preco);
	}

	public String representarProduto(String nomeFornecedor, String nome, String descricao) {
		if (nomeFornecedor == null || nomeFornecedor.isEmpty()) {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if (!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao existe.");
		} else if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		} else if (descricao == null || descricao.isEmpty()) {
			throw new IllegalArgumentException("Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		}
		Fornecedor fornecedor = fornecedores.get(nomeFornecedor);
		return fornecedor.exibirProduto(nome + descricao);

	}

//Aqui
	public String representarProdutos(String nomeFornecedor) {
		if (this.fornecedores.containsKey(nomeFornecedor)) {
			Fornecedor fornecedor = fornecedores.get(nomeFornecedor);
			return fornecedor.representarProdutos();
		}
		throw new IllegalArgumentException("Erro no exibir de produto: fornecedor nao ja existe.");
	}

	public void editarProduto(String nomeProduto, String descricao, String nomeFornecedor, double novoPreco) {
		if (nomeFornecedor == null || nomeFornecedor.isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if (novoPreco < 0) {
			throw new IllegalArgumentException("Erro na edicao de produto: preco invalido.");
		} else if (descricao == null || descricao.isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		} else if (nomeProduto == null || nomeProduto.isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		} else if (!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new IllegalArgumentException("Erro na edicao de produto: fornecedor nao existe.");

		} else if (this.fornecedores.containsKey(nomeFornecedor)) {
			Fornecedor fornecedor = fornecedores.get(nomeFornecedor);
			fornecedor.editarProduto(nomeProduto + descricao, novoPreco);
		}
	}

	public void removerProduto(String nomeProduto, String descricao, String nomeFornecedor) {
		if (nomeProduto == null || nomeProduto.isEmpty()) {
			throw new IllegalArgumentException("Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		} else if (descricao == null || descricao.isEmpty()) {
			throw new IllegalArgumentException("Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		} else if (nomeFornecedor == null || nomeFornecedor.isEmpty()) {
			throw new IllegalArgumentException("Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		} else if (!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new IllegalArgumentException("Erro na remocao de produto: fornecedor nao existe.");
		}
		Fornecedor fornecedor = fornecedores.get(nomeFornecedor);
		fornecedor.removerProduto(nomeProduto + descricao);

// combo
	}

	public String cadastrarCombo(String nomeFornecedor, String nome, String descricao, double fator, String produtos) {
		if (nomeFornecedor == null || nomeFornecedor.isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: fornecedor nao pode ser vazio ou nulo.");

		} else if (!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new IllegalArgumentException("Erro no cadastro de combo: fornecedor nao existe.");
		} else if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: nome nao pode ser vazio ou nulo.");
		} else if (descricao == null || descricao.isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: descricao nao pode ser vazia ou nula.");
		} else if (fator <= 0 || fator >= 1) {
			throw new IllegalArgumentException("Erro no cadastro de combo: fator invalido.");
		} else if (produtos == null || produtos.isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de combo: combo deve ter produtos.");
		}

		Fornecedor fornecedor = fornecedores.get(nomeFornecedor);
		return fornecedor.cadastrarCombo(nome, descricao, fator, produtos);

	}

	public String exibirCombos(String nomeFornecedor) {
		if (nomeFornecedor == null || nomeFornecedor.isEmpty()) {
			throw new IllegalArgumentException("nome do fornecedor nao pode ser vazio ou null");
		} else if (!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new IllegalArgumentException("Nao existe fornecedor");
		}
		Fornecedor fornecedor = fornecedores.get(nomeFornecedor);
		return fornecedor.exibirCombos();
	}

	public String representaTodosProdutos() {
		String retorno = "";
		int cont = 0;
		List<Fornecedor> produtosFornecedores = new ArrayList<>(this.fornecedores.values());
		Collections.sort(produtosFornecedores, new Comparador());
		for (Fornecedor fornecedores : produtosFornecedores) {
			retorno += fornecedores.representarProdutos();
			cont += 1;
			if (cont < produtosFornecedores.size()) {
				retorno += " | ";
			}

		}
		return retorno;
	}

	public String editarCombo(String nome, String descricao, String nomeFornecedor, double novoFator) {
		if (nomeFornecedor == null || nomeFornecedor.isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de combo: fornecedor nao pode ser vazio ou nulo.");
		} else if (novoFator < 0) {
			throw new IllegalArgumentException("Erro na edicao de combo: preco invalido.");
		} else if (descricao == null || descricao.isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de combo: descricao nao pode ser vazia ou nula.");
		} else if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Erro na edicao de combo: nome nao pode ser vazio ou nulo.");
		} else if (!this.fornecedores.containsKey(nomeFornecedor)) {
			throw new IllegalArgumentException("Erro na edicao de combo: fornecedor nao existe.");
		}else if (novoFator <= 0 || novoFator >=1) {
			throw new IllegalArgumentException("Erro na edicao de combo: fator invalido.");
		}
		Fornecedor fornecedor = fornecedores.get(nomeFornecedor);
		return fornecedor.editarCombo(nome + descricao, novoFator);
	}
}