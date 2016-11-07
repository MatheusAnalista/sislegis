package br.com.societysystem.sislegis.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.societysystem.sislegis.model.Usuario;
import br.com.societysystem.sislegis.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {

	public Usuario autenticar(String email, String senha){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("email", email));
		
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			Usuario usuarioRetornado = (Usuario) consulta.uniqueResult();
			return usuarioRetornado;
		}
		catch(RuntimeException erro){
			throw erro;
		}
		finally{
			sessao.close();
		}
	}
	
	
	
	public List<String> pesquisarPorNomeDoUsuario(String email){
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		Criteria criterio = sessao.createCriteria(Usuario.class);
		
		criterio.add(Restrictions.eq("email", email));
		
		@SuppressWarnings("unchecked")
		List<String> emailsEncontrados = criterio.list();
		
		return emailsEncontrados;
	}
	
	
	public List<String> pesquisarNomeUsuario(String email){
		EntityManager entityManager = null;
		String jpql = "select u from Usuario u where u.email = :email";
		Query pesquisa = entityManager.createQuery(jpql, Usuario.class);
		pesquisa.setParameter("email", email);
		return (List<String>) pesquisa;
	}
}
