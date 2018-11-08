package backend;

import controller.ControladorCliente;
import controller.ControladorFornecedor;
import easyaccept.EasyAccept;
import entidades.Cliente;
import entidades.Fornecedor;

/*
 * Classe compatador: Responsavel por comparar toString de clientes com toString de clientes e o
 * toString de fornecedores com o toString de fornecedores e organizalos por ordem decrescente.
 */

public class Facade {

	private ControladorCliente cc;
	private ControladorFornecedor cf;

	public Facade() {
		cc = new ControladorCliente();
		cf = new ControladorFornecedor();

	}

	public String adicionaCliente(String cpf, String nome, String email, String localTrabalho) {
		return cc.cadastrar(cpf, nome, email, localTrabalho);

	}

	public String exibeCliente(String cpf) {
		return cc.representacao(cpf);

	}

	public String exibeClientes() {
		return cc.representacoes();
	}

	public void editaCliente(String cpf, String atributo, String novoValor) {
		cc.editar(cpf, atributo, novoValor);
	}

	public void removeCliente(String cpf) {
		cc.remover(cpf);
	}

	// Facade de Fornecedor.
	public String adicionaFornecedor(String nome, String email, String telefone) throws Exception {
		return cf.cadastrar(nome, email, telefone);

	}

	public String exibeFornecedor(String nome) {
		return cf.representacao(nome);

	}

	public String exibeFornecedores() {
		return cf.representacoes();
	}

	public void editaFornecedor(String chave, String atributo, String valor) {
		cf.editar(chave, atributo, valor);
	}

	public void removeFornecedor(String nome) {
		cf.remover(nome);
	}

	// facade de fornecedor/produto

	public String adicionaProduto(String nomeFornecedor, String nome, String descricao, double preco) {
		return cf.cadastrarProduto(nomeFornecedor, nome, descricao, preco);
	}

	public String exibeProduto(String nome, String descricao, String nomeFornecedor) {
		return cf.representarProduto(nomeFornecedor, nome, descricao);
	}

	public String exibeProdutosFornecedor(String nomeFornecedor) {
		return cf.representarProdutos(nomeFornecedor);
	}

	public String exibeProdutos() {
		return cf.representaTodosProdutos();
	}

	public void editaProduto(String nomeProduto, String descricao, String nomeFornecedor, double preco) {
		cf.editarProduto(nomeProduto, descricao, nomeFornecedor, preco);
	}

	public void removeProduto(String nomeProduto, String descricao, String nomeFornecedor) {
		cf.removerProduto(nomeProduto, descricao, nomeFornecedor);
	}

	public static void main(String[] args) {
		args = new String[] { "backend.Facade", "Teste/use_case_1.txt", "Teste/use_case_2.txt", "Teste/use_case_3.txt","Teste/use_case_4.txt"  };
		EasyAccept.main(args);
	}
	
	public String adicionaCombo(String nomeFornecedor, String nome, String descricao, double fator,String produtos) {
		return cf.cadastrarCombo(nomeFornecedor, nome, descricao, fator,produtos);
	}
	
	public String editaCombo(String nome, String descricao, String nomeFornecedor, double novoFator) {
		return cf.editarCombo(nome, descricao, nomeFornecedor, novoFator);
	}
}