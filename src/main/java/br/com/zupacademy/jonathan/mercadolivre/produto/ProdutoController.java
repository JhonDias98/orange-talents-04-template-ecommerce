package br.com.zupacademy.jonathan.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.jonathan.mercadolivre.produto.caracteristica.ProibeCaracteristicasComNomeIgualValidator;
import br.com.zupacademy.jonathan.mercadolivre.usuario.Usuario;
import br.com.zupacademy.jonathan.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository UsuarioRepository;
	
	@InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ProibeCaracteristicasComNomeIgualValidator());
    }
	
//	@PostMapping
//	@Transactional
//	public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody @Valid ProdutoRequest request) {
//		Produto produto = request.toModel(manager);
//		manager.persist(produto);
//		return ResponseEntity.ok(new ProdutoResponse(produto));
//	}
	
	@PostMapping
	@Transactional
	public String cadastrarProduto(@RequestBody @Valid ProdutoRequest request) {
		Usuario dono = UsuarioRepository.findByLogin("jonathan@email.com").get();
		Produto produto = request.toModel(manager, dono);
		manager.persist(produto);
		return request.toString();
	}
}
