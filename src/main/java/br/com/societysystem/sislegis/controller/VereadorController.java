package br.com.societysystem.sislegis.controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;

import br.com.societysystem.sislegis.model.Endereco;
import br.com.societysystem.sislegis.model.Municipio;
import br.com.societysystem.sislegis.model.Partido;
import br.com.societysystem.sislegis.model.Perfil;
import br.com.societysystem.sislegis.model.PlanejamentoCota;
import br.com.societysystem.sislegis.model.Usuario;
import br.com.societysystem.sislegis.model.Vereador;
import br.com.societysystem.sislegis.repository.MunicipioDAO;
import br.com.societysystem.sislegis.repository.PartidoDAO;
import br.com.societysystem.sislegis.repository.PerfilDAO;
import br.com.societysystem.sislegis.repository.VereadorDAO;

@ManagedBean
public class VereadorController {
	
	private Vereador vereador;
	VereadorDAO vereadorDAO = new VereadorDAO();
	private List<Vereador> vereadores = new ArrayList<>();
	private Partido partido;
	PartidoDAO partidoDAO = new PartidoDAO();
	private List<Partido> partidos = new ArrayList<>();
	private List<Municipio> municipios = new ArrayList<>();
	MunicipioDAO municipioDAO = new MunicipioDAO();
	private Endereco endereco;
	private List<Perfil> perfis = new ArrayList<>();
	PerfilDAO perfilDAO = new PerfilDAO();
	private Usuario usuario;
	
	
	
	public VereadorController(){
		vereador = new Vereador();
		partido = new Partido();
		municipios = municipioDAO.listar();
		partidos = partidoDAO.listar();
		endereco = new Endereco();
		perfis = perfilDAO.listar();
		usuario = new Usuario();
	}
	
	public void limparFormulario(){
		vereador = new Vereador();
	}
	

		
	@PostConstruct
	public void listar() {
		try {
			vereadores = vereadorDAO.listar();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao tentar recuperar a listagem de vereadores!");
		}
	}
	
	
	public void salvar()
	{
		try{
			vereadorDAO.salvar(vereador);	
			Messages.addGlobalInfo("Operação realizada com sucesso!");
			limparFormulario();
		}
		catch (RuntimeException ex) 
		{
			ex.printStackTrace();
		}
	}
	
	
	public void salvarPartido()
	{
		try{
			Partido partidoPolitico = vereador.getPartido();
			partidoDAO.salvar(partidoPolitico);
			partidoPolitico = new Partido();
			partidos = partidoDAO.listar();
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
			vereadorDAO.excluir(vereador);
			vereadores.remove(vereador);
			listar();
			Messages.addGlobalInfo("Vereador excluído com sucesso!");
		} 
		catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o vereador!");
			erro.printStackTrace();
		}
	}
	
	
	public String atualizar(Vereador vereador) 
	{
		this.vereador = vereador;
		return "/pages/vereador.xhtml";
	}

	public Vereador getVereador() {
		return vereador;
	}

	public void setVereador(Vereador vereador) {
		this.vereador = vereador;
	}

	public List<Vereador> getVereadores() {
		return vereadores;
	}

	public void setVereadores(List<Vereador> vereadores) {
		this.vereadores = vereadores;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
