package br.com.zupacademy.jonathan.mercadolivre.categoria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoriaTest {

	@Autowired
	private EntityManager manager;
	
	@Autowired
	private TestEntityManager managerTest;
	
	@Test
	@DisplayName("Deve criar categoria sem categoria mae")
	public void testeCategoria() {
		CategoriaRequest request = new CategoriaRequest("Eletronicos");
		
		Categoria categoria = managerTest.persist(request.toModel(manager));
		
		assertEquals("Eletronicos", categoria.getNome());
        assertEquals(1L, categoria.getId());
        assertNull(categoria.getCategoria());
	}
	
	@Test
	@DisplayName("Deve criar categoria com categoria mae")
	public void testeCateriaComMae() {
		manager.persist(new Categoria("Eletronicos"));
		CategoriaRequest request = new CategoriaRequest("Smartphones");
		Categoria categoria = managerTest.persist(request.toModel(manager));
		categoria.setCategoria(manager.find(Categoria.class, 1L));
		
		assertEquals("Smartphones", categoria.getNome());
        assertEquals(2L, categoria.getId());
        assertNotNull(categoria.getCategoria());
	}
	
	@Test
	@DisplayName("Não deve criar categoria com o nome duplicado")
	public void testeCateriaComNomeDuplicado() {
		Categoria categoria = new Categoria("Smartphones");
		managerTest.persist(categoria);
		
		assertThrows(PersistenceException.class, () ->{
			managerTest.persist(new Categoria("Smartphones"));
        });
	}
	
	/**
	 * @TODO corrigir o teste com categoria mãe invalida
	 */
	@Test
	@DisplayName("Não deve criar categoria com categoria mae inválida")
	public void testeCateriaComMaeInvalida() {
		CategoriaRequest request = new CategoriaRequest("Smartphones", 5L);

		assertThrows(IllegalArgumentException.class, () -> {
			Categoria categoria = request.toModel(manager);
		});
	}
}