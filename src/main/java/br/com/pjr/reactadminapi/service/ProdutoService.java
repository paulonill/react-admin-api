package br.com.pjr.reactadminapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pjr.reactadminapi.entidade.Produto;
import br.com.pjr.reactadminapi.entidade.Venda;
import br.com.pjr.reactadminapi.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private VendaService vendaService;

	@Transactional
	public Produto incluir(Produto produto) {

		produto.setId(null);

		produto.setDataCadastro(new Date());

		return repository.save(produto);
	}

	@Transactional
	public Produto alterar(final Produto produto, final Long id) {

		Produto produtoSalvo = pesquisarPorId(id);

		BeanUtils.copyProperties(produto, produtoSalvo, "id");

		return repository.save(produtoSalvo);

	}

	public Produto pesquisarPorId(final Long id) {

		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));

	}

	@Transactional
	public void excluir(final Long id) {

		List<Venda> vendas = vendaService.pesquisarVendaPorIdProduto(id);

		if (vendas.size() == 0) {
			repository.deleteById(id);
		} else {
			throw new RuntimeException(
					"Existe venda realizada para o produto, por esse motivo não é permitido sua exclusão.");
		}

	}

	public Page<Produto> listar(int page, int size, Sort sort) {

		return repository.findAll(PageRequest.of(page, size, sort));

	}

}
