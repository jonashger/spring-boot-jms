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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_emailconfiguracao")
@SequenceGenerator(name = "gen_emailconfiguracao", sequenceName = "gen_emailconfiguracao", allocationSize = 1)
public class EmailConfiguracao {

	@Id
	@Column(name = "id_emailconfiguracao")
	@GeneratedValue(generator = "gen_emailconfiguracao", strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tx_tenant")
	private String tenant;

	@Column(name = "cd_modulo")
	private Long modulo;

	@Column(name = "tx_emailremetente")
	private String emailRemetente;

	@Column(name = "tx_emailsenha")
	private String emailSenha;

	@Column(name = "tx_nomeremetente")
	private String nomeRemetente;

	@Column(name = "tx_host")
	private String host;

	@Column(name = "tx_porta")
	private Long porta;

	@Column(name = "fl_ssl")
	private Boolean ssl;
}
