package br.com.societysystem.sislegis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "cota_parlamentar")
public class CotaParlamentar extends Entidade<Long>
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_cota")
	private Long idCota;
	
	@NotEmpty(message = "O campo nome é obrigatório!")
	@Column(nullable = false, length = 50)
	@Length(max = 50, message = "O campo nome não pode ultrapassar o {max} de caracteres!")
	private String nome;
	
	@NotEmpty(message = "O campo descrição é obrigatório!")
	@Column(length = 255)
	@Length(max = 255, message = "O campo descrição não pode ultrapassar o {max} de caracteres!")
	private String descricao;
	
	
	private boolean ativa;
	
	
	public CotaParlamentar()
	{
		
	}

	public Long getIdCota() {
		return idCota;
	}
	
	public Long getId(){
		return this.idCota;
	}

	public void setIdCota(Long idCota) {
		this.idCota = idCota;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idCota == null) ? 0 : idCota.hashCode());
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
		CotaParlamentar other = (CotaParlamentar) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idCota == null) {
			if (other.idCota != null)
				return false;
		} else if (!idCota.equals(other.idCota))
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
		return "CotaParlamentar [idCota=" + idCota + ", nome=" + nome
				+ ", descricao=" + descricao + "]";
	}
}
