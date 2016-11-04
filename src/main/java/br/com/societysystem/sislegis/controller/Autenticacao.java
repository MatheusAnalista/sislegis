package br.com.societysystem.sislegis.controller;
import java.io.IOException;
import br.com.societysystem.sislegis.repository.UsuarioDAO;
import br.com.societysystem.sislegis.util.HibernateUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.Session;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.societysystem.sislegis.model.Perfil;
import br.com.societysystem.sislegis.model.Usuario;

@ManagedBean
@SessionScoped
public class Autenticacao {

	private Usuario usuario;
	private Usuario usuarioAutenticado;
	private Perfil perfil;
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@PostConstruct
	public void inicializar()
	{
		usuario = new Usuario();
	}
	
	
	
	public void autenticar() throws IOException
	{
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioAutenticado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha());
		if(usuarioAutenticado == null)
		{
			Messages.addGlobalError("E-mail e/ou senha inválidos");
			return;
		}
		else if(!usuarioAutenticado.isAtivo())
		{
			Messages.addGlobalWarn("Usuário desativado, verifique com o administrador do sistema!");
			return;
		}
		inicializar();
		Faces.redirect("./pages/GraficosConsumo.xhtml");
		
	}
	

	public String encerrarSessaoDoUsuario()
	{
		usuarioAutenticado = null;
		return "autenticacao.xhtml?faces-redirect=true";
	}
	
	
	
	/*public boolean isAdministrador()
	{
		if(this.usuarioAutenticado.getPerfis().contains("Administrador")){
			return true;
		}

		return false;
	}*/
	

	
	
		public Usuario getUsuarioAutenticado() {
			return usuarioAutenticado;
		}

		public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
			this.usuarioAutenticado = usuarioAutenticado;
		}

		public Perfil getPerfil() {
			return perfil;
		}

		public void setPerfil(Perfil perfil) {
			this.perfil = perfil;
		}
		
}
