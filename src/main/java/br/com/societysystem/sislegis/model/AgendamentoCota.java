package br.com.societysystem.sislegis.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "a11_agendamento_cota_tb")
public class AgendamentoCota extends Entidade<Long>
{

	private static final long serialVersionUID = -1107224606284359987L;

	@Column(name = "id_agendamento")
	@Id
	@GeneratedValue
	private Long idAgendamento;
	
	@Column(name = "quantidade_solicitada")
	private Short quantidadeSolicitada;
	
	@Column(name = "numero_destino", length = 14)
	private String numeroDestino;
	
	@Column(name = "data_solicitacao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataSolicitacao;
	
	@ManyToOne
	@JoinColumn(name = "planejamento_id", nullable = false)
	private PlanejamentoCota planejamentoCota;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	
	
	public Long getIdAgendamento() {
		return idAgendamento;
	}
	
	@Override
	public Long getId(){
		return this.idAgendamento;
	}

	public void setIdAgendamento(Long idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public Short getQuantidadeSolicitada() {
		return quantidadeSolicitada;
	}

	public void setQuantidadeSolicitada(Short quantidadeSolicitada) {
		this.quantidadeSolicitada = quantidadeSolicitada;
	}

	public String getNumeroDestino() {
		return numeroDestino;
	}

	public void setNumeroDestino(String numeroDestino) {
		this.numeroDestino = numeroDestino;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public PlanejamentoCota getPlanejamentoCota() {
		return planejamentoCota;
	}

	public void setPlanejamentoCota(PlanejamentoCota planejamentoCota) {
		this.planejamentoCota = planejamentoCota;
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
				+ ((dataSolicitacao == null) ? 0 : dataSolicitacao.hashCode());
		result = prime * result
				+ ((idAgendamento == null) ? 0 : idAgendamento.hashCode());
		result = prime * result
				+ ((numeroDestino == null) ? 0 : numeroDestino.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime
				* result
				+ ((planejamentoCota == null) ? 0 : planejamentoCota.hashCode());
		result = prime
				* result
				+ ((quantidadeSolicitada == null) ? 0 : quantidadeSolicitada
						.hashCode());
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
		AgendamentoCota other = (AgendamentoCota) obj;
		if (dataSolicitacao == null) {
			if (other.dataSolicitacao != null)
				return false;
		} else if (!dataSolicitacao.equals(other.dataSolicitacao))
			return false;
		if (idAgendamento == null) {
			if (other.idAgendamento != null)
				return false;
		} else if (!idAgendamento.equals(other.idAgendamento))
			return false;
		if (numeroDestino == null) {
			if (other.numeroDestino != null)
				return false;
		} else if (!numeroDestino.equals(other.numeroDestino))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (planejamentoCota == null) {
			if (other.planejamentoCota != null)
				return false;
		} else if (!planejamentoCota.equals(other.planejamentoCota))
			return false;
		if (quantidadeSolicitada == null) {
			if (other.quantidadeSolicitada != null)
				return false;
		} else if (!quantidadeSolicitada.equals(other.quantidadeSolicitada))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AgendamentoCota [idAgendamento=" + idAgendamento
				+ ", quantidadeSolicitada=" + quantidadeSolicitada
				+ ", numeroDestino=" + numeroDestino + ", dataSolicitacao="
				+ dataSolicitacao + ", planejamentoCota=" + planejamentoCota
				+ ", pessoa=" + pessoa + "]";
	}
	
	
	
	
}
