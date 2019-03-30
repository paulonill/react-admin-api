package br.com.pjr.reactadminapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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

import br.com.pjr.reactadminapi.entidade.Venda;
import br.com.pjr.reactadminapi.service.VendaService;

@RestController
@RequestMapping("/vendas")
public class VendaResource {

	@Autowired
	private VendaService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Venda inserir(@RequestBody Venda venda, HttpServletResponse response) {

		final Venda vendaCadastrado = service.incluir(venda);

		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(vendaCadastrado.getId()).toUri();

		response.setHeader("location", uri.toString());

		return vendaCadastrado;
	}

	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Venda alterar(@RequestBody Venda venda, @PathVariable(value = "id") Long id) {

		return service.alterar(venda, id);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Venda excluir(@PathVariable("id") Long id) {

		service.excluir(id);

		return Venda.builder().id(id).build();
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Venda> listar(@RequestParam(required = false, defaultValue = "0", name = "_start") int start,
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

		Page<Venda> p = service.listar(page, size, srt);

		response.addHeader("X-Total-Count", String.valueOf(p.getTotalElements()));

		return p.getContent();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Venda pesquisarPorId(@PathVariable("id") Long id) {

		final Venda venda = service.pesquisarPorId(id);

		return venda;
	}

}
