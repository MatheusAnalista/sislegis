package br.com.societysystem.sislegis.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "a5_vereador_tb")
@PrimaryKeyJoinColumn(name = "id_vereador")
public class Vereador  extends Pessoa
{

	private static final long serialVersionUID = 1L;

	@Column(name = "nome_parlamentar", nullable = false, length = 50)
	private String nomeParlamentar;
	
	@Column(name = "cpf", nullable = false, length = 14)
	private String cpf;
	
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(nullable = false)
	private Short idade;
	
	@Column(nullable = false)
	private boolean presidente;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "partido_id")
	private Partido partido;

	public String getNomeParlamentar() {
		return nomeParlamentar;
	}

	public void setNomeParlamentar(String nomeParlamentar) {
		this.nomeParlamentar = nomeParlamentar;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Short getIdade() {
		return idade;
	}

	public void setIdade(Short idade) {
		this.idade = idade;
	}

	public boolean isPresidente() {
		return presidente;
	}

	public void setPresidente(boolean presidente) {
		this.presidente = presidente;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((idade == null) ? 0 : idade.hashCode());
		result = prime * result
				+ ((nomeParlamentar == null) ? 0 : nomeParlamentar.hashCode());
		result = prime * result + ((partido == null) ? 0 : partido.hashCode());
		result = prime * result + (presidente ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vereador other = (Vereador) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (idade == null) {
			if (other.idade != null)
				return false;
		} else if (!idade.equals(other.idade))
			return false;
		if (nomeParlamentar == null) {
			if (other.nomeParlamentar != null)
				return false;
		} else if (!nomeParlamentar.equals(other.nomeParlamentar))
			return false;
		if (partido == null) {
			if (other.partido != null)
				return false;
		} else if (!partido.equals(other.partido))
			return false;
		if (presidente != other.presidente)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vereador [nomeParlamentar=" + nomeParlamentar + ", cpf=" + cpf
				+ ", dataNascimento=" + dataNascimento + ", idade=" + idade
				+ ", presidente=" + presidente + ", partido=" + partido + "]";
	}
	


}
