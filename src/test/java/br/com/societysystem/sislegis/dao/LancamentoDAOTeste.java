package br.com.societysystem.sislegis.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.societysystem.sislegis.model.FinalidadeDiariaEnum;
import br.com.societysystem.sislegis.model.Lancamento;
import br.com.societysystem.sislegis.model.Pessoa;
import br.com.societysystem.sislegis.model.PlanejamentoCota;
import br.com.societysystem.sislegis.repository.LancamentoDAO;
import br.com.societysystem.sislegis.repository.PessoaDAO;
import br.com.societysystem.sislegis.repository.PlanejamentoCotaDAO;

public class LancamentoDAOTeste 
{

	@Test
	@Ignore
	public void salvar()
	{

		Lancamento lancamento = new Lancamento();
		LancamentoDAO lancamentoDAO = new LancamentoDAO();
		PlanejamentoCota pla = new PlanejamentoCota();
		PlanejamentoCotaDAO plaDAO = new PlanejamentoCotaDAO();
		pla = plaDAO.buscar(1L);
		PessoaDAO pDAO = new PessoaDAO();
		Pessoa pessoa = pDAO.buscar(1L);
		
		lancamento.setData(new Date());
		lancamento.setFinalidadeDiaria(FinalidadeDiariaEnum.ENCONTRO_PARLAMENTAR.CAPACITACAO);
		lancamento.setLocalLigacao("arinos");
		lancamento.setNumeroDestino("99999");
		lancamento.setObservacao("afafa");
		lancamento.setQuantidadeRetirada( 10);
		lancamento.setPessoa(pessoa);
		lancamento.setPlanejamentoCota(pla);
		
		lancamentoDAO.salvar(lancamento);
	}
	
	public void buscar()
	{
		
	}
}
