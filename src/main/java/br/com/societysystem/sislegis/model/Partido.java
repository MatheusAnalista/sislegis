package br.com.societysystem.sislegis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "a4_partido_tb")
public class Partido extends Entidade<Long>
{

	private static final long serialVersionUID = 1L;

	@Column(name = "id_partido")
	@Id
	@GeneratedValue
	private Long idPartido;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@Column(nullable = false, length = 20)
	private String sigla;

	public Long getIdPartido() {
		return idPartido;
	}

	public Long getId(){
		return this.idPartido;
	}
	
	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPartido == null) ? 0 : idPartido.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Partido other = (Partido) obj;
		if (idPartido == null) {
			if (other.idPartido != null)
				return false;
		} else if (!idPartido.equals(other.idPartido))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partido [idPartido=" + idPartido + ", nome=" + nome
				+ ", sigla=" + sigla + "]";
	}

	
}
