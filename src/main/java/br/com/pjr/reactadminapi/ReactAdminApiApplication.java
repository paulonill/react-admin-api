package br.com.pjr.reactadminapi;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.pjr.reactadminapi.entidade.Produto;
import br.com.pjr.reactadminapi.entidade.Venda;
import br.com.pjr.reactadminapi.service.ProdutoService;
import br.com.pjr.reactadminapi.service.VendaService;

@SpringBootApplication
public class ReactAdminApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactAdminApiApplication.class, args);
	}

    @Bean
    public ApplicationRunner init(VendaService vendaService, ProdutoService produtoService) {
    	

    	 Produto produto1 = produtoService.incluir(new Produto(null, "Arroz", "Arroz cristal " , new BigDecimal(10.5), new Date()));    	
    	 vendaService.incluir(new Venda(null, 2, produto1));
    	 
    	 Produto produto2 = produtoService.incluir(new Produto(null, "Açucar", "Açucar cristal " , new BigDecimal(8.4), new Date()));
    	 vendaService.incluir(new Venda(null, 4, produto2));
    	
    	 Produto produto3 = produtoService.incluir(new Produto(null, "Biscoito", "Biscoito de chocolate " , new BigDecimal(3.2), new Date()));
    	 vendaService.incluir(new Venda(null, 6, produto3));
    	 
    	 Produto produto4 = produtoService.incluir(new Produto(null, "Bolacha", "Bolacha passatempo " , new BigDecimal(4), new Date()));
    	 vendaService.incluir(new Venda(null, 2, produto4));
    	 
    	 Produto produto5 = produtoService.incluir(new Produto(null, "Chocolate", "Chocolate nestle " , new BigDecimal(6.1), new Date()));
    	 vendaService.incluir(new Venda(null, 1, produto5));
    	 
    	 Produto produto6 = produtoService.incluir(new Produto(null, "Creme de leite", "Creme de leite 200ml" , new BigDecimal(1.6), new Date()));
    	 vendaService.incluir(new Venda(null, 2, produto6));
    	 
    	 Produto produto7 = produtoService.incluir(new Produto(null, "Coca cola", "Coca cola 2L" , new BigDecimal(6), new Date()));
    	 vendaService.incluir(new Venda(null, 3, produto7));
    	 
    	 Produto produto8 = produtoService.incluir(new Produto(null, "Guaraná Antartica", "Guaraná Antartica 2L " , new BigDecimal(6), new Date()));
    	 vendaService.incluir(new Venda(null, 4, produto8));
    	 
    	 Produto produto9 = produtoService.incluir(new Produto(null, "Batata palha", "Batata palha cristal" , new BigDecimal(2.2), new Date()));
    	 vendaService.incluir(new Venda(null, 4, produto9));
    	 
    	 Produto produto10 = produtoService.incluir(new Produto(null, "Coco ralado", "Coco raldo cristal" , new BigDecimal(3.2), new Date()));
    	 vendaService.incluir(new Venda(null, 3, produto10));
    	 
        return null;
    }
}
