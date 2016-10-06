package br.com.societysystem.sislegis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.societysystem.sislegis.model.Perfil;
import br.com.societysystem.sislegis.model.Usuario;
import br.com.societysystem.sislegis.repository.PerfilDAO;
import br.com.societysystem.sislegis.repository.UsuarioDAO;

public class UsuarioDAOTeste 
{
	
	@Test
	@Ignore
	public void salvar()
	{
			Usuario usuario = new Usuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			PerfilDAO perfilDAO = new PerfilDAO();
			Perfil perfil = perfilDAO.buscar(1L);
			Perfil perfil2 = perfilDAO.buscar(3l);
			List<Perfil> perfiss = new ArrayList<Perfil>();
			perfiss.add(perfil);
			perfiss.add(perfil2);
			
			usuario.setAtivo(true);
			usuario.setEmail("teste2@email.com");
			usuario.setSenhaNaoCriptografada("senha");
		
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaNaoCriptografada());
			
			usuario.setSenha(hash.toHex());
			usuario.setConfirmaSenha(hash.toHex());
			usuario.setDataCadastro(new Date());
			usuario.setPerfis(perfiss);
			
			usuarioDAO.salvar(usuario);
			System.out.println("usuario salvo com sucesso!");
		}
		
	@Test
	public void autenticar()
	{
		String email = "teste2@email.com";
		String senha = "senh";
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(email, senha);
		System.out.println("Usuario autenticado: " +usuario);
	}
	
	@Test
	@Ignore
	public void listar()
	{
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.listar();
		
		for(Usuario usuario : usuarios)
		{
			//System.out.println("Usuario " +usuario.getEmail() + " Perfil " +usuario.getPerfil().getNome());
		}
	}
	
	public void buscar()
	{
		
	}
	
	public void atualizar()
	{
		
	}
	
	public void excluir()
	{
		
	}
}
