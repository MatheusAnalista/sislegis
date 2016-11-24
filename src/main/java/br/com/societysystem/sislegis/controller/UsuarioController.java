package br.com.societysystem.sislegis.controller;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	private List<String> emailsEncontrados;


	public void inicializar() {
		usuario = new Usuario();
	}

	public UsuarioController() {
		usuario = new Usuario();
		perfis = perfilDAO.listar();
	}

	public String salvar() {
		try {
			if (ehDataCorrente() && eEmailInexistente()) {
				SimpleHash hash = new SimpleHash("md5", usuario.getSenha());
				usuario.setSenha(hash.toHex());
				usuario.setConfirmaSenha(hash.toHex());
				usuarioDAO.salvar(usuario);
				Messages.addGlobalInfo("Operação realizada com sucesso!");
				inicializar();
				listar();
				return "usuarios";
			} 
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar salvar usuário");
		}
			return null;
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


	public List<String> pesquisarPorNome(){	

		emailsEncontrados = usuarioDAO.pesquisarNomeUsuario(usuario.getEmail());
		return emailsEncontrados;
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean ehDataCorrente(){
		LocalDateTime now = LocalDateTime.now(); 
			if(usuario.getDataCadastro().getDate() == now.getDayOfMonth() && usuario.getIdUsuario() == null || usuario.getDataCadastro().getDate() == now.getDayOfMonth() || usuario.getIdUsuario() !=null){
			return true;
			}
			Messages.addGlobalWarn("A data de hoje é diferente da data informada!");
			return false;
	}
	

	
	public boolean eEmailInexistente(){
		String email = usuario.getEmail();
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = usuarioDAO.listar();
		String emailsNoBanco;
		
		for(Usuario usuario : usuarios){
			emailsNoBanco = usuario.getEmail();
			if(emailsNoBanco.equals(email) && this.usuario.getId() == null){
				Messages.addGlobalWarn("E-mail já existente no banco de dados!");
				return false;
			}		
		}
		return true;		
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

	public List<String> getEmailsEncontrados() {
		return emailsEncontrados;
	}

	public void setEmailsEncontrados(List<String> emailsEncontrados) {
		this.emailsEncontrados = emailsEncontrados;
	}
}
