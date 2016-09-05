package br.com.societysystem.sislegis.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "a6_legislatura_tb")
public class Legislatura extends Entidade<Long>
{

	private static final long serialVersionUID = 1L;

	@Column(name = "id_legislatura")
	@Id
	@GeneratedValue
	private Long idLegislatura;
	
	@Column(name = "data_inicio", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Column(name = "data_final", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataFinal;
	
	@Column(length = 200)
	private String observacao;

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "a7_legislatura_vereador_tb", joinColumns = 
		@JoinColumn(name = "legislatura_id"), 
			inverseJoinColumns = 
				@JoinColumn(name = "vereador_id"))
	private List<Vereador> vereadores;


	public Long getIdLegislatura() {
		return idLegislatura;
	}
	public Long getId(){
		return this.idLegislatura;
	}

	public void setIdLegislatura(Long idLegislatura) {
		this.idLegislatura = idLegislatura;
	}


	public Date getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}


	public Date getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public List<Vereador> getVereadores() {
		return vereadores;
	}


	public void setVereadores(List<Vereador> vereadores) {
		this.vereadores = vereadores;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result
				+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result
				+ ((idLegislatura == null) ? 0 : idLegislatura.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((vereadores == null) ? 0 : vereadores.hashCode());
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
		Legislatura other = (Legislatura) obj;
		if (dataFinal == null) {
			if (other.dataFinal != null)
				return false;
		} else if (!dataFinal.equals(other.dataFinal))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (idLegislatura == null) {
			if (other.idLegislatura != null)
				return false;
		} else if (!idLegislatura.equals(other.idLegislatura))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (vereadores == null) {
			if (other.vereadores != null)
				return false;
		} else if (!vereadores.equals(other.vereadores))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Legislatura [idLegislatura=" + idLegislatura + ", dataInicio="
				+ dataInicio + ", dataFinal=" + dataFinal + ", observacao="
				+ observacao + ", vereadores=" + vereadores + "]";
	}
}
