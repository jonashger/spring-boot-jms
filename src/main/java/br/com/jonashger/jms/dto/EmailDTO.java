package br.com.jonashger.jms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "id", "mensagem", "assunto" })
@NoArgsConstructor
public class EmailDTO {

	private Long id;

	private String para;

	private String assunto;

	private Long modulo;

	private String tenant;

	private String mensagem;

}
