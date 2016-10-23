package br.com.societysystem.sislegis.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Messages;
import br.com.societysystem.sislegis.model.CotaParlamentar;
import br.com.societysystem.sislegis.model.PlanejamentoCota;
import br.com.societysystem.sislegis.model.Vereador;
import br.com.societysystem.sislegis.repository.CotaParlamentarDAO;
import br.com.societysystem.sislegis.repository.PlanejamentoCotaDAO;
import br.com.societysystem.sislegis.repository.VereadorDAO;

@ManagedBean
public class PlanejamentoCotaController {

	private PlanejamentoCota planejamentoCota;
	PlanejamentoCotaDAO planejamentoCotaDAO = new PlanejamentoCotaDAO();
	private List<PlanejamentoCota> planejamentos;

	private List<CotaParlamentar> cotasParlamentares;
	CotaParlamentarDAO cotaDAO = new CotaParlamentarDAO();

	private List<Vereador> vereadores;
	VereadorDAO vereadorDAO = new VereadorDAO();

	

	public PlanejamentoCotaController()
	{
		planejamentoCota = new PlanejamentoCota();
		vereadores = vereadorDAO.listar();
		cotasParlamentares = cotaDAO.listar();
	}

	
	public void iniciallizar() 
	{
		planejamentoCota = new PlanejamentoCota();
	}

	
	
	public String criarPlanejamento() 
	{
		return "planejamentoCota?faces-redirect=true";
	}

	
	
	@PostConstruct
	public void listar() {
		try {
			planejamentos = planejamentoCotaDAO.listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao tentar recuperar a listagem de planejamentos!");
		}
	}

	
	
	public void salvar()
	{
		try
		{
			if	(verificaDataInicioDataFim() == false)
			{
				planejamentoCotaDAO.salvar(planejamentoCota);	
				Messages.addGlobalInfo("Operação realizada com sucesso!");
				iniciallizar();
			}
		}
		catch (RuntimeException ex) 
		{
			ex.printStackTrace();
		}
	}

	
	
	public void excluir(PlanejamentoCota planejamentoCota)
	{
		try 
		{
			planejamentoCotaDAO.excluir(planejamentoCota);
			planejamentos.remove(planejamentoCota);
			listar();

			Messages.addGlobalInfo("Planejamento de cota excluído com sucesso!");
		} 
		catch (RuntimeException erro) 
		{
			Messages.addGlobalError("Erro ao tentar excluir o planejamento de cota!");
			erro.printStackTrace();
		}
	}

	
	
	public String atualizar(PlanejamentoCota planejamento) 
	{
		this.planejamentoCota = planejamento;
		return "/pages/planejamentoCota.xhtml";
	}

	
	
	public boolean verificaDataInicioDataFim() 
	{
		if (planejamentoCota.getDataInicio().after(planejamentoCota.getDataFim()))
		{
			Messages.addGlobalError("Data início não pode ser posterior a data fim!");
			return true;
		}
		else
		{
			return false;
		}
	}

	
	
	public PlanejamentoCota getPlanejamentoCota() {
		return planejamentoCota;
	}

	public void setPlanejamentoCota(PlanejamentoCota planejamentoCota) {
		this.planejamentoCota = planejamentoCota;
	}

	public List<CotaParlamentar> getCotasParlamentares() {
		return cotasParlamentares;
	}

	public void setCotasParlamentares(List<CotaParlamentar> cotasParlamentares) {
		this.cotasParlamentares = cotasParlamentares;
	}

	public List<PlanejamentoCota> getPlanejamentos() {
		return planejamentos;
	}

	public void setPlanejamentos(List<PlanejamentoCota> planejamentos) {
		this.planejamentos = planejamentos;
	}

	public List<Vereador> getVereadores() {
		return vereadores;
	}

	public void setVereadores(List<Vereador> vereadores) {
		this.vereadores = vereadores;
	}

}
