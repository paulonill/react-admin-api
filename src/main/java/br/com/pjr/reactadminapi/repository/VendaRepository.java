package br.com.pjr.reactadminapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pjr.reactadminapi.entidade.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	List<Venda> findByProdutoId(Long id);
}
