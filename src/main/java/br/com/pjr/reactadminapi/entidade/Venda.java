package br.com.pjr.reactadminapi.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Venda", description = "Informações de venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Identificador do produto", name = "id", dataType = "long")
	private Long id;

	@NotNull(message = "A quantidade é obrigatório!")
	@Column(nullable = false)
	@ApiModelProperty(value = "Quantidade do produto", name = "quantidade", dataType = "integer")
	private int quantidade;

	@ManyToOne
	@JoinColumn(name = "idProduto", nullable = false)
	private Produto produto;

	public BigDecimal getValorTotal() {

		return produto.getValor().multiply(new BigDecimal(quantidade));
	}
}
