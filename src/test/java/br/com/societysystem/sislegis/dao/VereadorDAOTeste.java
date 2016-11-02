package br.com.societysystem.sislegis.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.societysystem.sislegis.model.Endereco;
import br.com.societysystem.sislegis.model.GeneroEnum;
import br.com.societysystem.sislegis.model.Municipio;
import br.com.societysystem.sislegis.model.Partido;
import br.com.societysystem.sislegis.model.Perfil;
import br.com.societysystem.sislegis.model.Usuario;
import br.com.societysystem.sislegis.model.Vereador;
import br.com.societysystem.sislegis.repository.MunicipioDAO;
import br.com.societysystem.sislegis.repository.PerfilDAO;
import br.com.societysystem.sislegis.repository.VereadorDAO;


public class VereadorDAOTeste
{

	@Test
	public void salvar()
	{
		Partido partido = new Partido();
		partido.setNome("thth");
		partido.setSigla("ss");
		
		Usuario u = new Usuario();
		
		PerfilDAO pDAO = new PerfilDAO();
		List<Perfil> per = pDAO.listar();
		
		u.setEmail("ssss");
		u.setSenha("ss");
		u.setDataCadastro(new Date());
		u.setConfirmaSenha("ss");
		u.setAtivo(true);
		u.setPerfis(per);
	
		
		MunicipioDAO munDAO = new MunicipioDAO();
		Municipio municipio = munDAO.buscar(1L);
		
		Endereco end = new Endereco();
		end.setBairro("sssss");
		end.setComplemento("casa");
		end.setLogradouro("rua");
		end.setNumero((short) 3);
		end.setMunicipio(municipio);

		Vereador v = new Vereador();
		VereadorDAO vDAO = new VereadorDAO();
		
		v.setNome("Jose");
		v.setSobrenome("hhhh");
		v.setTelefone("99999999");
		v.setPresidente(false);
		v.setDataNascimento(new Date());
		v.setCpf("5555555555");
		v.setGenero(GeneroEnum.MASCULINO);
		v.setNomeParlamentar("testePar");
		v.setPartido(partido);
		//v.setEnderecos((List<Endereco>) end);
		v.setUsuario(u);
		
		vDAO.salvar(v);
	}
}
