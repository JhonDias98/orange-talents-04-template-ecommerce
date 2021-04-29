package br.com.zupacademy.jonathan.mercadolivre.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

	private String login;
	private SenhaLimpa senha;
	
	@BeforeEach
	public void inicio() {
		login = "jonathan@email.com";
		senha = new SenhaLimpa("123456");
	}
	
	@Test
    @DisplayName("Deve criar usuario valido")
	public void criaUsuario() {
		Usuario usuario = new Usuario(login, senha);
		assertEquals(login, usuario.getLogin());
	}
	
	@Test
    @DisplayName("Não deve aceitar login em branco")
    public void testLogin(){
        assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario("", senha);
        });
    }
	
	@Test
    @DisplayName("Não deve aceitar senha com menos de 6 caracters")
	public void senhaInvalida() {
		assertThrows(IllegalArgumentException.class, () -> {
            Usuario usuario = new Usuario(login, new SenhaLimpa("12345"));
        });
	}
}
