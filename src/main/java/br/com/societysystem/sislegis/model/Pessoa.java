package br.com.societysystem.sislegis.model;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends Entidade<Long> implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Column(name = "id_pessoa")
	@Id
	@GeneratedValue
	private Long idPessoa;
	
	@Column(nullable = false, length = 30)
	@NotEmpty(message = "O campo nome não pode ser nulo!")
	private String nome;
	
	@Column(nullable = false, length = 50)
	@NotEmpty(message = "O campo sobrenome não pode ser nulo!")
	private String sobrenome;
	
	@Column(nullable = false, length = 16)
	@NotEmpty(message = "O campo telefone não pode ser nulo!")
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	@Column( length = 9)
	@NotNull(message = "Selecione um gênero")
	private GeneroEnum genero;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "endereco_id")
	@NotNull(message = "Preencha as informações de endereço!")
	private Endereco endereco;

	
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "usuario_id", nullable = true)
	private Usuario usuario;

	
	public Pessoa()
	{
		endereco = new Endereco();
		//usuario = new Usuario();
	}
	

	
	
	
	
	public Long getIdPessoa() {
		return idPessoa;
	}
	
	public Long getId(){
		return this.idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public GeneroEnum getGenero() {
		return genero;
	}

	public void setGenero(GeneroEnum genero) {
		this.genero = genero;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPessoa == null) ? 0 : idPessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (idPessoa == null) {
			if (other.idPessoa != null)
				return false;
		} else if (!idPessoa.equals(other.idPessoa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome
				+ ", sobrenome=" + sobrenome + ", telefone=" + telefone
				+ ", genero=" + genero + ", endereco=" + endereco
				+ ", usuario=" + usuario + "]";
	}
	
}
