package entidades;

/*
 * Classe Cliente: Aqui onde fica localizado o objeto cliente e onde ficar suas informações como
 * nome(String), email(String), cpf(String) e localizacao(String).
 */

public class Cliente {
	private String nome;
	private String email;
	private String cpf;
	private String localizacao;
	
	public Cliente (String cpf,String nome, String email, String localTrabalho) {

		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.localizacao = localTrabalho;
		
	}
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	
	}
	public String getLocalTrabalho() {
		return localizacao;
	}
	
	public void setLocalTrabalho(String localTrabalho) {
		this.localizacao = localTrabalho;
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
	 * Método editar. Esse método fica localizado em Cliente pois aqui onde fica todas as informações 
	 * de um cliente então ele que deve ficar responsavel por controlar sua modificação.
	 * 
	 * O método editarCliente irá pegar informações como nome, email e sua localização e vai mudar de acordo 
	 * com seu novo valor, o editarCliente não pode alterar seu CPF e rejeita qualquer entrada invalida.
	 */
	
	public void editarCliente(String atributo, String novoValor) {
			if (atributo == null || atributo.equals("")) {
				throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.");
			} else if (!(atributo.equals("nome") || atributo.equals("email") || atributo.equals("localizacao"))) {	
				throw new IllegalArgumentException("Erro na edicao do cliente: atributo nao existe.");
			} else if (novoValor == null || novoValor.equals("")) {
				throw new IllegalArgumentException("Erro na edicao do cliente: novo valor nao pode ser vazio ou nulo.");
			}else if (atributo.equals("nome")) {
				this.nome = novoValor;
			}else if (atributo.equals("email")) {
				this.email = novoValor;
			}else if (atributo.equals("localizacao")) {
				this.localizacao = novoValor;
			}
	}
	
	@Override
	public String toString() {
		return this.nome + " - " + this.localizacao + " - " + this.email; 
	}
}
