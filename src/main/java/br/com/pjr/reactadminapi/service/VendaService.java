package br.com.pjr.reactadminapi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pjr.reactadminapi.entidade.Venda;
import br.com.pjr.reactadminapi.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repository;

	@Transactional
	public Venda incluir(final Venda venda) {

		venda.setId(null);

		return repository.save(venda);
	}

	@Transactional
	public Venda alterar(final Venda venda, final Long id) {

		Venda vendaSalva = pesquisarPorId(id);

		BeanUtils.copyProperties(venda, vendaSalva, "id");

		return repository.save(vendaSalva);

	}

	public Venda pesquisarPorId(final Long id) {

		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

	}

	public List<Venda> pesquisarVendaPorIdProduto(final Long idProduto) {
		return repository.findByProdutoId(idProduto);
	}

	@Transactional
	public void excluir(final Long id) {

		repository.deleteById(id);
	}

	public Page<Venda> listar(int page, int size, Sort sort) {

		return repository.findAll(PageRequest.of(page, size, sort));

	}
}
