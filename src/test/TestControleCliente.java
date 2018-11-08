package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.ControladorCliente;
import entidades.Cliente;

class TestControleCliente {
	private Cliente victor;
	private Cliente wilson;
	private Cliente amigao;
	private ControladorCliente cc;

	@BeforeEach
	void setUp() {
		cc = new ControladorCliente();
		victor = new Cliente("00023827490", "Victor Emanuel", "vitao@ccc.ufcg.edu.br", "Labarc");
		wilson = new Cliente("94412783134", "Wilson Andre", "wilson_andre@ccc.ufcg.edu.br", "Embedded");
		amigao = new Cliente("19418510068", "Amigao Fernandes", "amigao_fernandes@ccc.ufcg.edu.br", "LSD");
	}

	@Test
	void testCadastrar() {
		// Testa se ta cadastrando o cliente.
		assertEquals("00023827490", cc.cadastrar("00023827490", "Victor Emanuel", "vitao@ccc.ufcg.edu.br", "Labarc"));
		try {
			cc.cadastrar("", "Victor Emanuel", "vitao@ccc.ufcg.edu.br", "Labarc");
			assertTrue("devia ter lançado exception", false);

		} catch (Exception e) {
			assertEquals("Erro no cadastro do cliente: cpf invalido.", e.getMessage());
		}
	}

	@Test
	void testEditar() {
		try {
			cc.editar("00023827490", "rotmail", "victor@ccc.ufcg.edu.br");
			assertTrue("devia ter lançado exception", false);

		} catch (Exception e) {
			assertEquals("Erro na edicao do cliente: atributo nao existe.", e.getMessage());
		}
		try {
			cc.editar("00023827490", "", "victor@ccc.ufcg.edu.br");
			assertTrue("devia ter lançado exception", false);

		} catch (Exception e) {
			assertEquals("Erro na edicao do cliente: atributo nao pode ser vazio ou nulo.", e.getMessage());
		}
		try {
			cc.editar("00023827123", "email", "victor@ccc.ufcg.edu.br");
			assertTrue("devia ter lançado exception", false);

		} catch (Exception e) {
			assertEquals("Erro na edicao do cliente: cliente nao existe.", e.getMessage());
		}
	}

	@Test
	void testRemover() {
		try {
			cc.remover("12345678911");
			assertTrue("devia ter lançado exception", false);

		} catch (Exception e) {
			assertEquals("Erro na exibicao do cliente: cliente nao existe.", e.getMessage());
		}
	}
}
