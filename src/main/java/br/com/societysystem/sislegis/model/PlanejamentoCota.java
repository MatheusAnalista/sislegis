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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "a9_planejamento_cota_parlamentar_tb")
public class PlanejamentoCota extends Entidade<Long>
{

	private static final long serialVersionUID = 1L;

	@Column(name = "id_planejamento")
	@Id
	@GeneratedValue
	private Long idPlanejamento;
	
	@Column(name = "data_inicio")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "O campo data início não pode ser nulo")
	private Date dataInicio;
	
	@Column(name = "data_fim", nullable = false)
	@Temporal(TemporalType.DATE)
	@NotNull(message = "O campo data fim não pode ser nulo")
	private Date dataFim;
	
	@Column(name = "cota_limitada", nullable = false)
	private boolean cotaLimitada;
	
	@Column(name = "quantidade_permitida")
	private int quantidadePermitida;
	
	@Column(length = 200)
	private String descricao;
	
	
	@ManyToOne
	@JoinColumn(name = "cota_id", nullable = false)
	@NotNull(message = "Selecione a cota parlamentar para o planejamento")
	private CotaParlamentar cotaParlamentar;
	
	@ManyToOne
	@JoinColumn(name = "vereador_id", nullable = false)
	@NotNull(message = "Selecione o vereador para o planejamento")
	private Vereador vereador;

	
	public Long getIdPlanejamento() {
		return idPlanejamento;
	}
	
	public Long getId(){
		return this.idPlanejamento;
	}

	public void setIdPlanejamento(Long idPlanejamento) {
		this.idPlanejamento = idPlanejamento;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public boolean isCotaLimitada() {
		return cotaLimitada;
	}

	public void setCotaLimitada(boolean cotaLimitada) {
		this.cotaLimitada = cotaLimitada;
	}

	public int getQuantidadePermitida() {
		return quantidadePermitida;
	}

	public void setQuantidadePermitida(int quantidadePermitida) {
		this.quantidadePermitida = quantidadePermitida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CotaParlamentar getCotaParlamentar() {
		return cotaParlamentar;
	}

	public void setCotaParlamentar(CotaParlamentar cotaParlamentar) {
		this.cotaParlamentar = cotaParlamentar;
	}

	public Vereador getVereador() {
		return vereador;
	}

	public void setVereador(Vereador vereador) {
		this.vereador = vereador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPlanejamento == null) ? 0 : idPlanejamento.hashCode());
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
		PlanejamentoCota other = (PlanejamentoCota) obj;
		if (idPlanejamento == null) {
			if (other.idPlanejamento != null)
				return false;
		} else if (!idPlanejamento.equals(other.idPlanejamento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanejamentoCota [idPlanejamento=" + idPlanejamento
				+ ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ ", cotaLimitada=" + cotaLimitada + ", quantidadePermitida="
				+ quantidadePermitida + ", descricao=" + descricao
				+ ", cotaParlamentar=" + cotaParlamentar + ", vereador="
				+ vereador + "]";
	}

	
}
