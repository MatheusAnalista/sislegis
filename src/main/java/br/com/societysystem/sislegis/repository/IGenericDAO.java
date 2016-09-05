package br.com.societysystem.sislegis.repository;

import java.io.Serializable;
import java.util.List;
import br.com.societysystem.sislegis.model.Entidade;

public interface IGenericDAO<T extends Entidade<?>> 
{
	void salvar(T objeto);

    void atualizar(T objeto);

    void excluir (T objeto);

    T buscar(Serializable id);

    List<T> listar();
}
