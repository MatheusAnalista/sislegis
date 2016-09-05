package br.com.societysystem.sislegis.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "a3_endereco_tb")
public class Endereco extends Entidade<Long>
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_endereco")
	@GeneratedValue
	private Long idEndereco;
	
	@Column(nullable = false, length = 50)
	private String logradouro;
	
	@Column(nullable = false, length = 50)
	private String bairro;
	
	@Column(nullable = false)
	private Short numero;
	
	@Column(length = 50)
	private String complemento;

	@ManyToOne
	@JoinColumn(name = "municipio_id", nullable = false)
	private Municipio municipio;

	@OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL)
	private Pessoa pessoa;

	public Long getIdEndereco() {
		return idEndereco;
	}
	
	public Long getId(){
		return this.idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
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
		result = prime * result
				+ ((idEndereco == null) ? 0 : idEndereco.hashCode());
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
		Endereco other = (Endereco) obj;
		if (idEndereco == null) {
			if (other.idEndereco != null)
				return false;
		} else if (!idEndereco.equals(other.idEndereco))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [idEndereco=" + idEndereco + ", logradouro="
				+ logradouro + ", bairro=" + bairro + ", numero=" + numero
				+ ", complemento=" + complemento + ", municipio=" + municipio
				+ ", pessoa=" + pessoa + "]";
	}

}
