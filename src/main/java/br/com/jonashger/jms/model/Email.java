package br.com.jonashger.jms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "id")
@NoArgsConstructor
@Entity
@Table(name = "tb_email")
@SequenceGenerator(name = "gen_email", sequenceName = "gen_email", allocationSize = 1)
public class Email {

	@Id
	@Column(name = "id_email")
	@GeneratedValue(generator = "gen_email", strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tx_para")
	private String para;

	@Column(name = "tx_assunto")
	private String assunto;

	@Column(name = "bl_corpomensagem")
	private byte[] corpoMensagem;

	@Column(name = "cd_modulo")
	private Long modulo;

	@Column(name = "tx_tenant")
	private String tenant;

}
