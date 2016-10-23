package br.com.societysystem.sislegis.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "a10_lancamento_cota_tb")
public class Lancamento extends Entidade<Long>
{
	private static final long serialVersionUID = 1L;

	@Column(name = "id_lancamento")
	@Id
	@GeneratedValue
	private Long idLancamento;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "O campo data não pode ser nulo")
	private Date data;
	
	@Column(name = "quantidade_retirada")
	private int quantidadeRetirada;
	
	@Column(name = "numero_destino", length = 14)
	private String numeroDestino;
	
	@Column(name = "local_ligacao", length = 50)
	private String localLigacao;
	
	@Column(name = "valor_diaria")
	private Double valorDiaria;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "finalidade_diaria", length = 20)
	private FinalidadeDiariaEnum finalidadeDiaria;
	
	@Column(length = 200)
	private String observacao;
	
	@ManyToOne
	@NotNull(message = "É obrigatória a seleção de um planejamento para realizar o lançamento!")
	@JoinColumn(name = "planejamento_id")
	private PlanejamentoCota planejamentoCota;
	
	@ManyToOne
	@NotNull(message = "É obrigatória a seleção de um solicitante para realizar o lançamento!")
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	
	
	
	
	public Long getIdLancamento() {
		return idLancamento;
	}

	public Long getId(){
		return this.idLancamento;
	}
	
	public void setIdLancamento(Long idLancamento) {
		this.idLancamento = idLancamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getQuantidadeRetirada() {
		return quantidadeRetirada;
	}

	public void setQuantidadeRetirada(int quantidadeRetirada) {
		this.quantidadeRetirada = quantidadeRetirada;
	}

	public String getNumeroDestino() {
		return numeroDestino;
	}

	public void setNumeroDestino(String numeroDestino) {
		this.numeroDestino = numeroDestino;
	}

	public String getLocalLigacao() {
		return localLigacao;
	}

	public void setLocalLigacao(String localLigacao) {
		this.localLigacao = localLigacao;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public FinalidadeDiariaEnum getFinalidadeDiaria() {
		return finalidadeDiaria;
	}

	public void setFinalidadeDiaria(FinalidadeDiariaEnum finalidadeDiaria) {
		this.finalidadeDiaria = finalidadeDiaria;
	}

	@Basic(fetch = FetchType.LAZY)
	@Lob
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
				+ ((idLancamento == null) ? 0 : idLancamento.hashCode());
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
		Lancamento other = (Lancamento) obj;
		if (idLancamento == null) {
			if (other.idLancamento != null)
				return false;
		} else if (!idLancamento.equals(other.idLancamento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lancamento [quantidadeRetirada=" + quantidadeRetirada
				+ ", finalidadeDiaria=" + finalidadeDiaria + ", observacao="
				+ observacao + "]";
	}

}
