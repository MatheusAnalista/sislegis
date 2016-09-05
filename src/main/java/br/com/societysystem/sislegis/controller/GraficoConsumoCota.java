package br.com.societysystem.sislegis.controller;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.societysystem.sislegis.model.Lancamento;
import br.com.societysystem.sislegis.model.PlanejamentoCota;
import br.com.societysystem.sislegis.repository.LancamentoDAO;
import br.com.societysystem.sislegis.repository.PlanejamentoCotaDAO;


@ManagedBean
public class GraficoConsumoCota
{
	private BarChartModel graficoConsumo;
	PlanejamentoCotaDAO planejamentoDAO = new PlanejamentoCotaDAO();
	private PlanejamentoCota planejamentoCota = new PlanejamentoCota();
	private List<PlanejamentoCota> planejamentos;

	
	@PostConstruct
    public void inicializar() 
	{
        graficoConsumoRestante();
    }
	
	private void graficoConsumoRestante()
	{
		graficoConsumo = consumoRestante();
		graficoConsumo.setTitle("Cotas Parlamentares Restantes");
		graficoConsumo.setAnimate(true);
		graficoConsumo.setLegendPosition("ne");
		graficoConsumo.setBarPadding(5);
		graficoConsumo.setBarMargin(8);
		graficoConsumo.setLegendRows(10);
		graficoConsumo.setBarPadding(10);
		Axis yAxis = graficoConsumo.getAxis(AxisType.Y);
        yAxis = graficoConsumo.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(350);
    }


	private BarChartModel consumoRestante()
	{
		BarChartModel model = new BarChartModel();
        ChartSeries ver = new ChartSeries();
        
        planejamentos = planejamentoDAO.listar();
        
        for(PlanejamentoCota planejamento : planejamentos)
        {
        	 ver.set(planejamento.getVereador().getNomeParlamentar(), planejamento.getQuantidadePermitida());
        }

        ver.setLabel("Vereadores");
     
        model.addSeries(ver);
 
        return model;
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
}
