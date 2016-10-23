package br.com.societysystem.sislegis.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.societysystem.sislegis.model.Lancamento;
import br.com.societysystem.sislegis.model.PlanejamentoCota;
import br.com.societysystem.sislegis.model.Vereador;
import br.com.societysystem.sislegis.repository.LancamentoDAO;
import br.com.societysystem.sislegis.repository.PlanejamentoCotaDAO;
import br.com.societysystem.sislegis.util.IsNullUtil;

@ManagedBean
public class GraficoConsumoCota
{
	private BarChartModel graficoConsumo;
	private BarChartModel graficoConsumoDiariaLancado;
	private BarChartModel graficoConsumoLigacaoLancado;
	
	PlanejamentoCotaDAO planejamentoDAO = new PlanejamentoCotaDAO();
	private PlanejamentoCota planejamentoCota = new PlanejamentoCota();
	private List<PlanejamentoCota> planejamentos;

	private Lancamento lancamento = new Lancamento();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	LancamentoDAO lancamentoDAO = new LancamentoDAO();
	
	@PostConstruct
    public void inicializar() {
        graficoConsumoRestante();
        graficoConsumoDiariaLancado();
        graficoConsumoLigacaoLancado();
    }
	
	public GraficoConsumoCota(){
		lancamentos = lancamentoDAO.listar();
	}
	
	private void graficoConsumoRestante(){
		graficoConsumo = consumoRestante();
		graficoConsumo.setTitle("Cota Xerográfica Disponível");
		graficoConsumo.setAnimate(true);
		graficoConsumo.setLegendPosition("ne");
		graficoConsumo.setBarPadding(5);
		graficoConsumo.setBarMargin(8);
		graficoConsumo.setLegendRows(10);
		graficoConsumo.setBarPadding(10);
		Axis yAxis = graficoConsumo.getAxis(AxisType.Y);
        yAxis = graficoConsumo.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

	private BarChartModel consumoRestante(){
		BarChartModel model = new BarChartModel();
        ChartSeries ver = new ChartSeries();
        planejamentos = planejamentoDAO.listar();
        
        for(PlanejamentoCota planejamento : planejamentos){
        	 ver.set(planejamento.getVereador().getNomeParlamentar(), planejamento.getQuantidadePermitida());
        }
        ver.setLabel("Vereadores");
        model.addSeries(ver);
        return model;
	}
		
	private void graficoConsumoDiariaLancado(){
		graficoConsumoDiariaLancado = consumoDiariaLancado();
		graficoConsumoDiariaLancado.setTitle("Consumo de Diária");
		graficoConsumoDiariaLancado.setAnimate(true);
		graficoConsumoDiariaLancado.setLegendPosition("ne");
		graficoConsumoDiariaLancado.setBarPadding(2);
		graficoConsumoDiariaLancado.setBarMargin(8);
		graficoConsumoDiariaLancado.setLegendRows(10);
		graficoConsumoDiariaLancado.setBarPadding(10);
		Axis yAxis = graficoConsumoDiariaLancado.getAxis(AxisType.Y);
        yAxis = graficoConsumoDiariaLancado.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }
	
	private BarChartModel consumoDiariaLancado(){
		BarChartModel model = new BarChartModel();
		
		ChartSeries ver = new ChartSeries();
		
		List<Lancamento> lancamentos = lancamentoDAO.recuperarPorDiaria();
		
		Map<Vereador, Long> mapRelatorio = new HashMap<Vereador, Long>();
		
		if(!IsNullUtil.isNullOrEmpty(lancamentos)){
			
			//Faz a sintese das infrmações para o grafico
			for(Lancamento lancamento : lancamentos){
				
				final Vereador vereador = lancamento.getPlanejamentoCota().getVereador();
				
				Long quantidadeAtual = mapRelatorio.get(vereador);
				
				if(IsNullUtil.isNullOrEmpty(quantidadeAtual)){
				
					mapRelatorio.put(vereador, 1l);
				}else{
					
					mapRelatorio.put(vereador, ++quantidadeAtual);
				}
				
			}
			
		}
		
		//Percorre Map ja preenchido com os dados corretos para o relatorio e seta no grafico
		for (Vereador key : mapRelatorio.keySet()) {
			
			ver.set(key.getNomeParlamentar(),  mapRelatorio.get(key));
		}         
        
		ver.setLabel("Vereadores");
		model.addSeries(ver);    	
        return model;
	}
	
	
	private void graficoConsumoLigacaoLancado(){
		graficoConsumoLigacaoLancado = consumoLigacaoLancado();
		graficoConsumoLigacaoLancado.setTitle("Consumo Cota de Ligação");
		graficoConsumoLigacaoLancado.setAnimate(true);
		graficoConsumoLigacaoLancado.setLegendPosition("ne");
		graficoConsumoLigacaoLancado.setBarPadding(5);
		graficoConsumoLigacaoLancado.setBarMargin(8);
		graficoConsumoLigacaoLancado.setLegendRows(10);
		graficoConsumoLigacaoLancado.setBarPadding(10);
		Axis yAxis = graficoConsumoLigacaoLancado.getAxis(AxisType.Y);
        yAxis = graficoConsumoLigacaoLancado.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

	private BarChartModel consumoLigacaoLancado(){
		
		BarChartModel model = new BarChartModel();
		
		ChartSeries ver = new ChartSeries();
		
		List<Lancamento> lancamentos = lancamentoDAO.recuperarPorCotaLigacao();
		
		Map<Vereador, Long> mapRelatorio = new HashMap<Vereador, Long>();
		
		if(!IsNullUtil.isNullOrEmpty(lancamentos)){
			
			//Faz a sintese das infrmações para o grafico
			for(Lancamento lancamento : lancamentos){
				
				final Vereador vereador = lancamento.getPlanejamentoCota().getVereador();
				
				Long quantidadeAtual = mapRelatorio.get(vereador);
				
				if(IsNullUtil.isNullOrEmpty(quantidadeAtual)){
				
					mapRelatorio.put(vereador, 1l);
				}else{
					
					mapRelatorio.put(vereador, ++quantidadeAtual);
				}
				
			}
			
		}
		
		//Percorre Map ja preenchido com os dados corretos para o relatorio e seta no grafico
		for (Vereador key : mapRelatorio.keySet()) {
			
			ver.set(key.getNomeParlamentar(),  mapRelatorio.get(key));
		}
		
        ver.setLabel("Vereadores");
        model.addSeries(ver);
        return model;
	}
	
	
		
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	public BarChartModel getGraficoConsumo() {
		return graficoConsumo;
	}
	public void setGraficoConsumo(BarChartModel graficoConsumo) {
		this.graficoConsumo = graficoConsumo;
	}
	public PlanejamentoCota getPlanejamentoCota() {
		return planejamentoCota;
	}
	public void setPlanejamentoCota(PlanejamentoCota planejamentoCota) {
		this.planejamentoCota = planejamentoCota;
	}
	public List<PlanejamentoCota> getPlanejamentos() {
		return planejamentos;
	}
	public void setPlanejamentos(List<PlanejamentoCota> planejamentos) {
		this.planejamentos = planejamentos;
	}
	public BarChartModel getGraficoConsumoDiariaLancado() {
		return graficoConsumoDiariaLancado;
	}

	public void setGraficoConsumoDiariaLancado(
			BarChartModel graficoConsumoDiariaLancado) {
		this.graficoConsumoDiariaLancado = graficoConsumoDiariaLancado;
	}

	public BarChartModel getGraficoConsumoLigacaoLancado() {
		return graficoConsumoLigacaoLancado;
	}

	public void setGraficoConsumoLigacaoLancado(
			BarChartModel graficoConsumoLigacaoLancado) {
		this.graficoConsumoLigacaoLancado = graficoConsumoLigacaoLancado;
	}
	
}
