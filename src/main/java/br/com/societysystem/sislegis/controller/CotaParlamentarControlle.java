package br.com.societysystem.sislegis.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.omnifaces.util.Messages;

import br.com.societysystem.sislegis.model.CotaParlamentar;
import br.com.societysystem.sislegis.model.Perfil;
import br.com.societysystem.sislegis.repository.CotaParlamentarDAO;


@ManagedBean
public class CotaParlamentarControlle implements Serializable
{
	
	CotaParlamentarDAO cotaDAO = new CotaParlamentarDAO();
	private CotaParlamentar cotaParlamentar;
	private List<CotaParlamentar> cotasParlamentares;
	private List<Perfil> perfis;

	
	public CotaParlamentarControlle()
	{
		cotaParlamentar = new CotaParlamentar();
	}
	
	
	public String cadastrarCota()
	{
		return "cotaParlamentar?faces-redirect=true";
	}
	
	
	@PostConstruct
	public void listar()
	{
		try 
		{
			cotasParlamentares = cotaDAO.listar();
		}
		catch (Exception e) 
		{
			Messages.addGlobalError("Erro ao tentar recuperar a listagem de cotas parlamentares");
		}
	}
	
	
	public void salvar(){
		try
		{
			if	(verificaNomeCota() == false)
			{
				cotaDAO.salvar(cotaParlamentar);	
				Messages.addGlobalInfo("Operação realizada com sucesso!");	
				cotaParlamentar = new CotaParlamentar();
			}
		}
		catch (RuntimeException ex) 
		{
			ex.printStackTrace();
		}
	}
	

	public void excluir(CotaParlamentar cotaParlamentar)
	{
		try
		{
			cotaDAO.excluir(cotaParlamentar);
			cotasParlamentares.remove(cotaParlamentar);
			listar();
			
			Messages.addGlobalInfo("Cota Parlamentar excluída com sucesso!");
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Erro ao tentar excluir a cota parlamentar!");	
			erro.printStackTrace();
		}
	}
	
	
	
	public String atualizar(CotaParlamentar cota)
	{
		this.cotaParlamentar = cota;
		return "/pages/cotaParlamentar.xhtml";
	}
	
	
	
	public boolean verificaNomeCota()
	{	
		if(cotaParlamentar.getId() == null)
		{
			for(CotaParlamentar cota : cotasParlamentares)
			{
			if(cota.getNome().equalsIgnoreCase(cotaParlamentar.getNome()))
			{
				Messages.addGlobalError("Cota Parlamentar já cadastrada no banco de dados do sistema!");
				return true;
			}
			}
		}
			return false;
	}
	
	
	
	public CotaParlamentarDAO getCotaDAO() {
		return cotaDAO;
	}

	public void setCotaDAO(CotaParlamentarDAO cotaDAO) {
		this.cotaDAO = cotaDAO;
	}

	public CotaParlamentar getCotaParlamentar() {
		return cotaParlamentar;
	}

	public void setCotaParlamentar(CotaParlamentar cotaParlamentar) {
		this.cotaParlamentar = cotaParlamentar;
	}

	public List<CotaParlamentar> getCotasParlamentares() {
		return cotasParlamentares;
	}

	public void setCotasParlamentares(List<CotaParlamentar> cotasParlamentares) {
		this.cotasParlamentares = cotasParlamentares;
	}
	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}	
}
