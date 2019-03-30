package br.com.pjr.reactadminapi.entidade;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ApiModel(value = "Produto", description = "Informações de produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Identificador do produto", name = "id", dataType = "long")
	private Long id;

	@NotNull(message = "O nome é obrigatório!")
	@Column(nullable = false, unique = true)
	@ApiModelProperty(value = "Nome do produto", name = "nome", dataType = "string")
	private String nome;

	@Column
	@ApiModelProperty(value = "Descrição do produto", name = "descricao", dataType = "string")
	private String descricao;

	@NotNull(message = "O valor é obrigatório!")
	@Column(nullable = false)
	@ApiModelProperty(value = "Valor do produto", name = "valor", dataType = "number")
	private BigDecimal valor;

	@NotNull(message = "A data de cadastro é obrigatório!")
	@Column(nullable = false)
	@ApiModelProperty(value = "Data de cadastro do produto", name = "dataCadastro", dataType = "date")
	private Date dataCadastro;
}
