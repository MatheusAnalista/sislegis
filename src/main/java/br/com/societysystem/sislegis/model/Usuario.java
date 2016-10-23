package br.com.societysystem.sislegis.model;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "a1_usuario_tb")
public class Usuario extends Entidade<Long>
{

	private static final long serialVersionUID = 1L;

	@Column(name = "id_usuario")
	@Id
	@GeneratedValue
	private Long idUsuario;
	
	@Column(length = 50, nullable = false)
	@Length(max = 50, message = "O campo e-mail não pode ultrapassar o {max} de caracteres!")
	@NotEmpty(message = "O campo e-mail é obrigatório")
	@Email(message = "Preencha com um e-mail válido!")
	private String email;
	
	@Column(length = 32, nullable = false)
	@Length(max = 50, message = "O campo senha não pode ultrapassar o {max} de caracteres!")
	@NotEmpty(message = "O campo senha é obrigatório")
	private String senha;
	
	@Transient
	private String senhaNaoCriptografada;
	
	@Column(name = "confirma_senha", length = 32, nullable = false)
	@Length(max = 50, message = "O senha não pode ultrapassar o {max} de caracteres!")
	@NotEmpty(message = "O campo confirma senha é obrigatório")
	private String confirmaSenha;
	
	@Column(nullable = false)
	private boolean ativo;
	
	@Column(name = "data_cadastro", nullable = false, updatable = false)
	@NotNull(message = "O campo data de cadastro é obrigatório")
	private Date dataCadastro;
	
	@Transient
	private byte[] foto;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t4_perfil_usuario_tb", joinColumns = 
		@JoinColumn(name = "usuario_id", nullable = false), 
			inverseJoinColumns = 
				@JoinColumn(name = "perfil_id", nullable = false))
	@NotEmpty(message = "É necessário escolher um ou mais perfis de acesso!")
	private List<Perfil> perfis;

	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Pessoa pessoa;

	
	
	
	
	public String getSenhaNaoCriptografada() {
		return senhaNaoCriptografada;
	}

	public void setSenhaNaoCriptografada(String senhaNaoCriptografada) {
		this.senhaNaoCriptografada = senhaNaoCriptografada;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public Long getId(){
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public boolean isAtivo()
	{
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result
				+ ((confirmaSenha == null) ? 0 : confirmaSenha.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + Arrays.hashCode(foto);
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((perfis == null) ? 0 : perfis.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo)
			return false;
		if (confirmaSenha == null) {
			if (other.confirmaSenha != null)
				return false;
		} else if (!confirmaSenha.equals(other.confirmaSenha))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (!Arrays.equals(foto, other.foto))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (perfis == null) {
			if (other.perfis != null)
				return false;
		} else if (!perfis.equals(other.perfis))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + "]";
	}


	
}
