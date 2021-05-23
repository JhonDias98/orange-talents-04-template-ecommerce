package br.com.zupacademy.jonathan.mercadolivre.produto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.jonathan.mercadolivre.produto.caracteristica.ProibeCaracteristicasComNomeIgualValidator;
import br.com.zupacademy.jonathan.mercadolivre.produto.imagem.ImagemRequest;
import br.com.zupacademy.jonathan.mercadolivre.produto.imagem.Uploader;
import br.com.zupacademy.jonathan.mercadolivre.produto.opiniao.Opiniao;
import br.com.zupacademy.jonathan.mercadolivre.produto.opiniao.OpiniaoRequest;
import br.com.zupacademy.jonathan.mercadolivre.produto.opiniao.OpiniaoResponse;
import br.com.zupacademy.jonathan.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.jonathan.mercadolivre.produto.pergunta.PerguntaRequest;
import br.com.zupacademy.jonathan.mercadolivre.produto.pergunta.PerguntaResponse;
import br.com.zupacademy.jonathan.mercadolivre.usuario.Usuario;
import br.com.zupacademy.jonathan.mercadolivre.usuario.UsuarioRepository;
import br.com.zupacademy.jonathan.mercadolivre.utils.email.EnviarEmail;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
    private Uploader uploaderFake;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnviarEmail emails;
	
	
	@InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ProibeCaracteristicasComNomeIgualValidator());
    }
	
	
	@PostMapping
	@Transactional
	public String cadastrarProduto(@RequestBody @Valid ProdutoRequest request) {
		Usuario dono = usuarioRepository.findByLogin("jonathan@email.com").get();
		Produto produto = request.toModel(manager, dono);
		manager.persist(produto);
		return request.toString();
	}
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public void cadastrarImagens(@PathVariable Long id, @Valid ImagemRequest request) {
		Usuario dono = usuarioRepository.findByLogin("jonathan@email.com").get();
		Produto produto = manager.find(Produto.class, id);
		
		if(!produto.pertenceAoUsuario(dono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não pode modificar um produto que não seja seu!");
		}
		
		Set<String> links = uploaderFake.envia(request.getImagens());
		produto.associaImagens(links);
		manager.merge(produto);
	}
	
	@PostMapping(value = "/{id}/opiniao")
	@Transactional
	public ResponseEntity<OpiniaoResponse> adicionarOpiniao(@PathVariable Long id, @RequestBody @Valid OpiniaoRequest request) {
		Usuario usuarioLogado = usuarioRepository.findByLogin("jonathan@email.com").get();
		Produto produto = manager.find(Produto.class, id);
		Opiniao opiniao = request.toModel(usuarioLogado, produto);
		manager.persist(opiniao);
		
		return ResponseEntity.ok(new OpiniaoResponse(opiniao));
	}
	
	@PostMapping(value = "/{id}/perguntas")
	@Transactional
	public ResponseEntity<PerguntaResponse> adicionarPergunta(@PathVariable Long id, @RequestBody @Valid PerguntaRequest request) {
		Usuario usuarioLogado = usuarioRepository.findByLogin("jonathan@email.com").get();
		Produto produto = manager.find(Produto.class, id);
		Pergunta pergunta = request.toModel(usuarioLogado, produto);
		manager.persist(pergunta);
		
		emails.novaPergunta(pergunta);
		
		return ResponseEntity.ok(new PerguntaResponse(pergunta));
	}
	
	@GetMapping("/{id}")
	public ProdutoDetalheResponse consultarProduto(@PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		return new ProdutoDetalheResponse(produto);
	}
}
