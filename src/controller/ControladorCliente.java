package controller;
import java.util.HashMap;
import java.util.Map;

import comparable.Comparador;
import entidades.Cliente;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Classe Controladora de Cliente: Responsavel por controlar cada parametro que cliente.
 * 
 * Cliente irá ficar guardado em um MAP para facilitar o modo que achalo. Ele será encontrado pelo seu CPF(String).
 */

public class ControladorCliente {

	private Map<String, Cliente> clientes;

	public ControladorCliente() {
		clientes = new HashMap<String, Cliente>();

	}

	/*
	 * Método cadastrar, responsavel por receber as informações de cliente cpf(String), nome(String), email(String)
	 * e localização(String) e tratalas para ser cadastrada, caso alguma dessas informações não apresente os parametros
	 * certos um IllegalArgumentException é lançado.
	 */	
	
	public String cadastrar(String cpf, String nome, String email, String localizacao ){
		if(cpf.length() != 11) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cpf invalido.");
		}else if ("".equals(nome) || nome == null) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: nome nao pode ser vazio ou nulo.");
		}else if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: email nao pode ser vazio ou nulo.");
		
		}else if (localizacao == null || "".equals(localizacao)) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: localizacao nao pode ser vazia ou nula.");
		}else if (this.clientes.containsKey(cpf)) {
			throw new IllegalArgumentException("Erro no cadastro do cliente: cliente ja existe.");
		}
		this.clientes.put(cpf,new Cliente(cpf, nome, email, localizacao));
		return cpf;
	}

	/*
	 * Método representação: Responsavel por localizar um cliente atravez de seu cpf(String) e retornar seu
	 * toString. Caso o cliente não seja localizado é lançado IllegalArgumentException. 
	 */	
	
	public String representacao(String cpf) {
		if (!this.clientes.containsKey(cpf)) {
			throw new IllegalArgumentException("Erro na exibicao do cliente: cliente nao existe.");
		}
		return this.clientes.get(cpf).toString();
	}

	/*
	 * Método representações: Neste método um ArrayList é cliado atravez do map de clientes para que a 
	 * class comparador possa organizar de acordo com seu toString e logo depois disso o mesmo ArrayList
	 * cliado é colocado em um for para que todas as representações presentes retornem no formato de
	 * Texto.
	 */
	
	public String representacoes() {
		List<Cliente> listaClientes =  new ArrayList<>(this.clientes.values());
		Collections.sort(listaClientes,new Comparador());
		
		String retorno = "";
		int cont = 0;
		for (Cliente cliente : listaClientes) {
			retorno += cliente.toString();
			cont += 1;
			if (cont < listaClientes.size()) {
				retorno += " | ";
			}
		}
		return retorno;
	}
	/*
	 * Método editar: Responsavel por editar as informaçoes de cliente localiza o cliente pelo seu cpf
	 * caso encontre ele irá pegar todas as informações que desejam mudar no formado de String e irá 
	 * chamar o método editarCliente localizado na classe Cliente. Caso o cpf não seja valido irá lançar um 
	 * IllegalArgumentException com a representação do erro encontrado.
	 */
	public void editar(String cpf, String atributo, String novoValor){
		if (atributo == null || "".equals(atributo)) {
			throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
		} else if (!(atributo.equals("nome") || atributo.equals("email") || atributo.equals("localizacao"))) {	
			throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao existe.");
		} else if (novoValor == null || "".equals(novoValor)) {
			throw new IllegalArgumentException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
		}else if (!this.clientes.containsKey(cpf)) {
			throw new IllegalArgumentException("Erro na edicao do cliente: cliente nao existe.");
		}else if (this.clientes.containsKey(cpf)) {
			this.clientes.get(cpf).editarCliente(atributo, novoValor);
			
		}
	}
	/*
	 * Método remover: Responsavel por receber o cpf(String) de cliente e encontralo no map e caso o usuario deseje
	 * removelo ele irá remover o cliente, caso o cpf seja invalido um IllegalArgumentException é lançado.
	 */	
	public void remover(String cpf){
		if (!this.clientes.containsKey(cpf)) {
			throw new IllegalArgumentException("Erro na exibicao do cliente: cliente nao existe.");
		}else if (cpf == null || cpf.isEmpty()) {
			throw new IllegalArgumentException("Erro na remocao do cliente: cpf nao pode ser vazio ou nulo.");
			
		}else {
			clientes.remove(cpf);	
		}
			
	}

}
