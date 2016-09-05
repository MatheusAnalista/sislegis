package br.com.societysystem.sislegis.repository;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.societysystem.sislegis.model.Entidade;
import br.com.societysystem.sislegis.util.HibernateUtil;


public class GenericDAO<T extends Entidade<?>> implements IGenericDAO<T>
{
	private Class<T> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO()
	{
		this.classe = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	@Override
	public void salvar(T entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try
		{
			transacao = sessao.beginTransaction();
			if(entidade.getId() == null){
				
				sessao.persist(entidade);
			}else{
				
				sessao.update(entidade);
			}
			transacao.commit();
		}
		catch(RuntimeException erro)
		{
			if(transacao != null)
			{
				transacao.rollback();
			}
			throw erro;
		}
		finally
		{
			sessao.close();
		}
	}

	
	
	@SuppressWarnings("unchecked")
	public List<T> listar()
	{
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try
		{
			Criteria consulta = sessao.createCriteria(classe);
			consulta.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<T> resultado = consulta.list();
			return resultado;
		}
		catch(RuntimeException erro)
		{
			throw erro;
		}
		finally
		{
			sessao.close();
		}
	}
	
	protected Session getSession(){
		
		return HibernateUtil.getFabricaDeSessoes().openSession();
	}
	


	@Override
	public void atualizar(T entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try
		{
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		}
		catch(RuntimeException erro)
		{
			if(transacao != null)
			{
				transacao.rollback();
			}
			throw erro;
		}
		finally
		{
			sessao.close();
		}
		
	}


	@Override
	public void excluir(T entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		
		try
		{
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
		}
		catch(RuntimeException erro)
		{
			if(transacao != null)
			{
				transacao.rollback();
			}
			throw erro;
		}
		finally
		{
			sessao.close();
		}
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public T buscar(Serializable id) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try
		{
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(id));
			T resultado = (T) consulta.uniqueResult();
			return resultado;
		}
		catch(RuntimeException erro)
		{
			throw erro;
		}
		finally
		{
			sessao.close();
		}
	}
}



	
	
