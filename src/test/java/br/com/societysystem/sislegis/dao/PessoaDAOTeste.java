package br.com.societysystem.sislegis.dao;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;
import br.com.societysystem.sislegis.model.Endereco;
import br.com.societysystem.sislegis.model.GeneroEnum;
import br.com.societysystem.sislegis.model.Municipio;
import br.com.societysystem.sislegis.model.Pessoa;
import br.com.societysystem.sislegis.model.Usuario;
import br.com.societysystem.sislegis.repository.MunicipioDAO;
import br.com.societysystem.sislegis.repository.PessoaDAO;

public class PessoaDAOTeste 
{
	@Test
	@Ignore
	public void salvar()
	{
		Pessoa pessoa = new Pessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();	
		MunicipioDAO municipioDAO = new MunicipioDAO();
		Municipio m = municipioDAO.buscar(1L);
		
		Usuario u = new Usuario();
		u.setAtivo(false);
		u.setConfirmaSenha("a");
		u.setSenha("a");
		u.setDataCadastro(new Date());
		u.setEmail("email@hotmail.com");
		
		Endereco end = new Endereco();
		
		end.setBairro("dddd");
		end.setComplemento("sss");
		end.setLogradouro("333");
		end.setNumero((short) 55);
		end.setMunicipio(m);
		
		pessoa.setNome("Luan");
		pessoa.setSobrenome("Silva");
		pessoa.setTelefone("8888888");
		pessoa.setGenero(GeneroEnum.MASCULINO);
		pessoa.setEndereco(end);
		pessoa.setUsuario(u);
		pessoaDAO.salvar(pessoa);
		
	}
	
	
	@Test
	@Ignore
	public void buscar()
	{
		PessoaDAO pessoaDAO = new PessoaDAO();	
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		System.out.println("Pessoa selecionada p/ manipulação: " +pessoa.getNome() + "Sobrenome: " +pessoa.getSobrenome());
	}
	
	
	@Test
	@Ignore
	public void atualizar()
	{
		PessoaDAO pessoaDAO = new PessoaDAO();	
		Pessoa pessoa = pessoaDAO.buscar(1L);
		
		System.out.println("Pessoa selecionada p/ manipulação: " +pessoa.getNome() + "Sobrenome: " +pessoa.getSobrenome());
	
		pessoa.setNome("Juliana");
		pessoa.setSobrenome("testando");
		
		pessoaDAO.atualizar(pessoa);
		System.out.println("Pessoa após atualização: " +pessoa.getNome() + "Sobrenome: " +pessoa.getSobrenome());
	}
	
}
