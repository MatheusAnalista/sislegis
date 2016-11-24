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
public class GraficoConsumoCota{

	 private BarChartModel animatedModel2;
	 private BarChartModel animatedModel3;
	 private BarChartModel animatedModel4;
	 private List<Lancamento> lancamentosDeLigacoes = new ArrayList<>();
	 private List<Lancamento> lancamentosDiarias = new ArrayList<>();
	 private List<Lancamento> lancamentosXerograficas = new ArrayList<>();
	 LancamentoDAO lancamentoDAO = new LancamentoDAO();
	
	 
	 public GraficoConsumoCota(){
		lancamentosDeLigacoes = lancamentoDAO.recuperarPorCotaLigacao();
		lancamentosDiarias = lancamentoDAO.recuperarPorDiaria();
		lancamentosXerograficas = lancamentoDAO.recuperarPorCotaXerografica();
	 }
	 
	 @PostConstruct
	    public void init() {
	        createAnimatedModels();
	        createAnimatedModel3();
	        createAnimatedModel4();
	    }
	 
	 
	 //Estrutura para montagem do gráfico de consumo de ligações
	private void createAnimatedModels() {
		 animatedModel2 = initBarModel();
	     animatedModel2.setTitle("Consumo cota de ligações");
	     animatedModel2.setAnimate(true);
	     animatedModel2.setLegendPosition("ne");
	     Axis yAxis = animatedModel2.getAxis(AxisType.Y);
	     yAxis.setMin(0);	     
	     yAxis = animatedModel2.getAxis(AxisType.Y);
	    
	}

	
	private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries ver = new ChartSeries();
        Map<Vereador, Long> mapRelatorio = new HashMap<Vereador, Long>();
        
	if(!IsNullUtil.isNullOrEmpty(lancamentosDeLigacoes)){
			
			//Faz a sintese das infrmações para o grafico
			for(Lancamento lancamento : lancamentosDeLigacoes){
				
				final Vereador vereador = lancamento.getPlanejamentoCota().getVereador();
				
				Long quantidadeAtual = mapRelatorio.get(vereador);
				
				if(IsNullUtil.isNullOrEmpty(quantidadeAtual)){
				
					mapRelatorio.put(vereador, 1l);
				}
				else{					
					mapRelatorio.put(vereador, ++quantidadeAtual);
				}
			}		
		}
		for (Vereador key : mapRelatorio.keySet()) {
		
		ver.set(key.getNomeParlamentar(),  mapRelatorio.get(key));
		}		
    	ver.setLabel("Vereadores");
    	model.addSeries(ver);
    	return model;

    }

	
	
	//Estrutura para montagem do gráfico de consumo diárias
	
	private void createAnimatedModel3() {
		 animatedModel3 = initBarModel3();
	     animatedModel3.setTitle("Consumo de diárias");
	     animatedModel3.setAnimate(true);
	     animatedModel3.setLegendPosition("ne");
	     Axis yAxis = animatedModel3.getAxis(AxisType.Y);
	     yAxis.setMin(0);	     
	     yAxis = animatedModel3.getAxis(AxisType.Y);
	    
	}
	
	private BarChartModel initBarModel3() {
        BarChartModel model = new BarChartModel();
        ChartSeries ver = new ChartSeries();
        Map<Vereador, Long> mapRelatorio = new HashMap<Vereador, Long>();
        
	if(!IsNullUtil.isNullOrEmpty(lancamentosDiarias)){
			
			//Faz a sintese das infrmações para o grafico
			for(Lancamento lancamento : lancamentosDiarias){
				
				final Vereador vereador = lancamento.getPlanejamentoCota().getVereador();
				
				Long quantidadeAtual = mapRelatorio.get(vereador);
				
				if(IsNullUtil.isNullOrEmpty(quantidadeAtual)){
				
					mapRelatorio.put(vereador, 1l);
				}
				else{					
					mapRelatorio.put(vereador, ++quantidadeAtual);
				}
			}		
		}
		for (Vereador key : mapRelatorio.keySet()) {
		
		ver.set(key.getNomeParlamentar(),  mapRelatorio.get(key));
		}		
    	ver.setLabel("Vereadores");
    	model.addSeries(ver);
    	return model;

    }

	
	//Estrutura para montagem do gráfico de consumo xerografico

	
	private void createAnimatedModel4() {
		 animatedModel4 = initBarModel4();
	     animatedModel4.setTitle("Consumo cota xerográfica");
	     animatedModel4.setAnimate(true);
	     animatedModel4.setLegendPosition("ne");
	     Axis yAxis = animatedModel4.getAxis(AxisType.Y);
	     yAxis.setMin(0);	     
	     yAxis = animatedModel4.getAxis(AxisType.Y);
	    
	}
	
	private BarChartModel initBarModel4() {
        BarChartModel model = new BarChartModel();
        ChartSeries ver = new ChartSeries();
        Map<Vereador, Long> mapRelatorio = new HashMap<Vereador, Long>();
        
	if(!IsNullUtil.isNullOrEmpty(lancamentosXerograficas)){
			
			//Faz a sintese das infrmações para o grafico
			for(Lancamento lancamento : lancamentosXerograficas){
				
				final Vereador vereador = lancamento.getPlanejamentoCota().getVereador();
				
				Long quantidadeAtual = mapRelatorio.get(vereador);
				
				if(IsNullUtil.isNullOrEmpty(quantidadeAtual)){
				
					mapRelatorio.put(vereador, 1l);
				}
				else{					
					mapRelatorio.put(vereador, ++quantidadeAtual);
				}
			}		
		}
		for (Vereador key : mapRelatorio.keySet()) {
		
		ver.set(key.getNomeParlamentar(),  mapRelatorio.get(key));
		}		
    	ver.setLabel("Vereadores");
    	model.addSeries(ver);
    	return model;

    }
	
	
	
	
	
	
	public BarChartModel getAnimatedModel2() {
		return animatedModel2;
	}
	public void setAnimatedModel2(BarChartModel animatedModel2) {
		this.animatedModel2 = animatedModel2;
	}
	public List<Lancamento> getLancamentosDeLigacoes() {
		return lancamentosDeLigacoes;
	}
	public void setLancamentosDeLigacoes(List<Lancamento> lancamentosDeLigacoes) {
		this.lancamentosDeLigacoes = lancamentosDeLigacoes;
	}

	public BarChartModel getAnimatedModel3() {
		return animatedModel3;
	}

	public void setAnimatedModel3(BarChartModel animatedModel3) {
		this.animatedModel3 = animatedModel3;
	}

	public List<Lancamento> getLancamentosDiarias() {
		return lancamentosDiarias;
	}

	public void setLancamentosDiarias(List<Lancamento> lancamentosDiarias) {
		this.lancamentosDiarias = lancamentosDiarias;
	}

	public BarChartModel getAnimatedModel4() {
		return animatedModel4;
	}

	public void setAnimatedModel4(BarChartModel animatedModel4) {
		this.animatedModel4 = animatedModel4;
	}

	public List<Lancamento> getLancamentosXerograficas() {
		return lancamentosXerograficas;
	}

	public void setLancamentosXerograficas(List<Lancamento> lancamentosXerograficas) {
		this.lancamentosXerograficas = lancamentosXerograficas;
	}
	
	
}
