package br.com.societysystem.sislegis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t2_municipio_tb")
public class Municipio extends Entidade<Long>
{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_municipio")
	private Long idMunicipio;
	
	@Column(nullable = false, length = 10)
	private String cep;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false)
	private Estado estado;

	
	
	public Long getIdMunicipio() {
		return idMunicipio;
	}

	public Long getId(){
		return this.idMunicipio;
	}
	
	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((idMunicipio == null) ? 0 : idMunicipio.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Municipio other = (Municipio) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (idMunicipio == null) {
			if (other.idMunicipio != null)
				return false;
		} else if (!idMunicipio.equals(other.idMunicipio))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Municipio [idMunicipio=" + idMunicipio + ", cep=" + cep
				+ ", nome=" + nome + ", estado=" + estado + "]";
	}
	
	
}
