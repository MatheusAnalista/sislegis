package br.com.societysystem.sislegis.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.societysystem.sislegis.model.Lancamento;

public class LancamentoDAO extends GenericDAO<Lancamento> {

	// TODO refatorar para que seja dinamico futuramente, e nao ficar amarrando
	// a ligacao com o campo que nao esta preenchido
	@SuppressWarnings("unchecked")
	public List<Lancamento> recuperarPorCotaLigacao() {

		Criteria consulta = super.getSession().createCriteria(Lancamento.class);

		consulta.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		consulta.createAlias("planejamentoCota", "planejamentoCota");

		consulta.createAlias("planejamentoCota.vereador", "vereador");

		consulta.add(Restrictions.isNotNull("numeroDestino"));
		
		consulta.add(Restrictions.ne("numeroDestino", ""));
		
		consulta.addOrder(Order.asc("vereador.nome"));

		return consulta.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Lancamento> recuperarPorDiaria() {

		Criteria consulta = super.getSession().createCriteria(Lancamento.class);

		consulta.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		consulta.createAlias("planejamentoCota", "planejamentoCota");

		consulta.createAlias("planejamentoCota.vereador", "vereador");

		consulta.add(Restrictions.isNotNull("valorDiaria"));
		
		consulta.addOrder(Order.asc("vereador.nome"));

		return consulta.list();
	}
	
	
/*	@SuppressWarnings("unchecked")
	public List<Lancamento> recuperarPorCotaXerografica() {

		Criteria consulta = super.getSession().createCriteria(Lancamento.class);

		consulta.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		consulta.createAlias("planejamentoCota", "planejamentoCota");

		consulta.createAlias("planejamentoCota.vereador", "vereador");

		consulta.add(Restrictions.isNotNull("quantidadeRetirada"));
		
		consulta.addOrder(Order.asc("vereador.nome"));

		return consulta.list();
	}*/
}
