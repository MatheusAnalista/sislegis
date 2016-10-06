package br.com.societysystem.sislegis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.societysystem.sislegis.model.Perfil;
import br.com.societysystem.sislegis.model.Usuario;
import br.com.societysystem.sislegis.repository.PerfilDAO;
import br.com.societysystem.sislegis.repository.UsuarioDAO;

@ManagedBean
public class UsuarioController implements Serializable {
	private Usuario usuario;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Perfil> perfis;
	PerfilDAO perfilDAO = new PerfilDAO();
	private List<Usuario> usuarios;

	public void inicializar() {
		usuario = new Usuario();
	}

	public UsuarioController() {
		usuario = new Usuario();
		perfis = perfilDAO.listar();
	}

	public void salvar() {
		try {
			if (usuario.getIdUsuario() == null) {
				SimpleHash hash = new SimpleHash("md5", usuario.getSenha());
				usuario.setSenha(hash.toHex());
				usuario.setConfirmaSenha(hash.toHex());
				usuarioDAO.salvar(usuario);
				Messages.addGlobalInfo("Operação realizada com sucesso!");
			} else {
				usuarioDAO.salvar(usuario);
				Messages.addGlobalInfo("Atualização realizada com sucesso!");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar salvar usuário");
		} finally {
			inicializar();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			usuarios = usuarioDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar recuperar a listagem de usuarios");
		}
	}

	public String atualizar(Usuario usuario) {
		this.usuario = usuario;
		return "/pages/usuario.xhtml";
	}

	public void excluir(Usuario usuario) {
		try {
			usuarioDAO.excluir(usuario);
			usuarios.remove(usuario);
			usuarios = usuarioDAO.listar();

			Messages.addGlobalInfo("Usuário excluído com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o usuário!");
			erro.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
