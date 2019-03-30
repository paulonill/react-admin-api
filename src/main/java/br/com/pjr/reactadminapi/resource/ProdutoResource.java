package br.com.pjr.reactadminapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pjr.reactadminapi.entidade.Produto;
import br.com.pjr.reactadminapi.service.ProdutoService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/produtos")
@Log4j2
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto inserir(@RequestBody Produto produto, HttpServletResponse response) {

		log.trace("POST /produtos ".concat(produto.toString()));

		final Produto produtoCadastrado = service.incluir(produto);

		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(produtoCadastrado.getId()).toUri();

		response.setHeader(HttpHeaders.LOCATION, uri.toString());

		return produtoCadastrado;
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Produto alterar(@RequestBody Produto produto, @PathVariable(value = "id") Long id) {

		return service.alterar(produto, id);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Produto excluir(@PathVariable("id") Long id) {

		service.excluir(id);

		return Produto.builder().id(id).build();
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Produto> listar(@RequestParam(required = false, defaultValue = "0", name = "_start") int start,
			@RequestParam(required = false, defaultValue = "10", name = "_end") int end,
			@RequestParam(required = false, name = "_order") String order,
			@RequestParam(required = false, name = "_sort") String sort,
			@RequestParam(required = false, name = "name") String name, HttpServletResponse response) {

		int size = end - start;

		int page = size == 0 ? 0 : Double.valueOf(Math.ceil(end / size)).intValue() - 1;

		Sort srt = Sort.unsorted();

		if (!ObjectUtils.isEmpty(order) && !ObjectUtils.isEmpty(sort)) {
			srt = Sort.by(Sort.Direction.valueOf(order), sort);
		}

		Page<Produto> p = service.listar(page, size, srt);

		response.addHeader("X-Total-Count", String.valueOf(p.getTotalElements()));

		return p.getContent();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Produto pesquisarPorId(@PathVariable("id") Long id) {

		final Produto produto = service.pesquisarPorId(id);

		return produto;

	}

}
