package br.com.societysystem.sislegis.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class TeclasAtalho {

	public String atalhoParaTelaLancamentos(){
		return "lancamentos?faces-redirect=true";
	}
	
	public String atalhoParaTelaCotasParlamentares(){
		return "cotasParlamentares?faces-redirect=true";
	}
	
	public String atalhoParaTelaPlanejamentoCotas(){
		return "planejamentoCotas?faces-redirect=true";
	}
	
	public String atalhoParaTelaGraficos(){
		return "GraficosConsumo?faces-redirect=true";
	}
	
	public String atalhoParaTelaRelatorios(){
		return "relatorios?faces-redirect=true";
	}
	
	public String atalhoParaTelaUsuarios(){
		return "usuarios?faces-redirect=true";
	}
	
	public String atalhoParaTelaSolicitantes(){
		return "pessoas?faces-redirect=true";
	}
	
	public String atalhoParaTelaVereadores(){
		return "vereadores?faces-redirect=true";
	}
	
	
	public void lancarTodosAtalhos(){
		atalhoParaTelaGraficos();
		atalhoParaTelaLancamentos();
		atalhoParaTelaPlanejamentoCotas();
		atalhoParaTelaRelatorios();
		atalhoParaTelaSolicitantes();
		atalhoParaTelaUsuarios();
		atalhoParaTelaVereadores();
	}
}
