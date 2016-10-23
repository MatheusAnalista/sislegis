package br.com.societysystem.sislegis.controller;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;

import br.com.societysystem.sislegis.model.Endereco;
import br.com.societysystem.sislegis.model.Municipio;
import br.com.societysystem.sislegis.model.Perfil;
import br.com.societysystem.sislegis.model.Pessoa;
import br.com.societysystem.sislegis.model.PlanejamentoCota;
import br.com.societysystem.sislegis.model.Usuario;
import br.com.societysystem.sislegis.repository.MunicipioDAO;
import br.com.societysystem.sislegis.repository.PerfilDAO;
import br.com.societysystem.sislegis.repository.PessoaDAO;


@ManagedBean
public class PessoaController {

	private Pessoa pessoa;
	private Endereco endereco;
	MunicipioDAO municipioDAO = new MunicipioDAO();
	private List<Pessoa> pessoas;
	private List<Municipio> municipios;
	PessoaDAO pessoaDAO = new PessoaDAO();

	
	
	
	public PessoaController()
	{
		pessoa = new Pessoa();
		municipios = municipioDAO.listar();
		endereco = new Endereco();
	}	

	public void limparFormulario()
	{
		pessoa = new Pessoa();
	}
	
	
	@PostConstruct
	public void listar()
	{
		try{
			pessoas = pessoaDAO.listar();
		}
		catch(RuntimeException erro){
			
		}
	}
	
	public void salvar()
	{
		try
		{
			pessoaDAO.salvar(pessoa);
			Messages.addGlobalInfo("Operação realizada com sucesso!");
			limparFormulario();
		}
		catch(RuntimeException erro)
		{
			erro.printStackTrace();
			Messages.addGlobalError("Erro ao tentar salvar o solicitante!");
		}
	}
	
	
	
	public void excluir(Pessoa pessoa)
	{
		try 
		{
			pessoaDAO.excluir(pessoa);
			pessoas.remove(pessoa);
			listar();

			Messages.addGlobalInfo("Solicitante excluído com sucesso!");
		} 
		catch (RuntimeException erro) 
		{
			Messages.addGlobalError("Erro ao tentar excluir o solicitante!");
			erro.printStackTrace();
		}
	}

	
	
	public String atualizar(Pessoa pessoa) 
	{
		this.pessoa = pessoa;
		return "/pages/pessoa.xhtml";
	}

	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

}
