package br.com.societysystem.sislegis.controller;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.commons.mail.EmailException;
import org.omnifaces.util.Messages;

import br.com.societysystem.sislegis.model.FinalidadeDiariaEnum;
import br.com.societysystem.sislegis.model.Lancamento;
import br.com.societysystem.sislegis.model.Pessoa;
import br.com.societysystem.sislegis.model.PlanejamentoCota;
import br.com.societysystem.sislegis.repository.LancamentoDAO;
import br.com.societysystem.sislegis.repository.PessoaDAO;
import br.com.societysystem.sislegis.repository.PlanejamentoCotaDAO;
import br.com.societysystem.sislegis.util.EnviadorDeEmailHelper;
import br.com.societysystem.sislegis.util.EnviadorDeEmailHelper.ParametroEmail;
import br.com.societysystem.sislegis.vo.ChaveValor;

@ManagedBean
public class LancamentoController {
	
	LancamentoDAO lancamentoDAO = new LancamentoDAO();
	PlanejamentoCotaDAO planejamentoDAO = new PlanejamentoCotaDAO();
	PessoaDAO pessoaDAO = new PessoaDAO();
	private Lancamento lancamento = new Lancamento();
	private List<Lancamento> lancamentos;
	private List<PlanejamentoCota> planejamentos;
	private List<Pessoa> pessoas;
	private List<FinalidadeDiariaEnum> finalidadeDiarias;
	private PlanejamentoCota planejamentoCota = new PlanejamentoCota();

	
	public LancamentoController() {
		lancamento = new Lancamento();
		planejamentos = planejamentoDAO.listar();
		pessoas = pessoaDAO.listar();
	}

	
	public void limparFormulario() {
		lancamento = new Lancamento();
	}

	
	public String salvar() throws EmailException {
		try {	
			if(verificarPreenchimentoCampoXerox()){
				if (verificarDataLancamento()
					&& darBaixaNoPlanejamentoExecutado() && ehDataCorrente()){
				lancamentoDAO.salvar(lancamento);
				//enviarEmail();
				Messages.addGlobalInfo("Operação realizada com sucesso!");
				limparFormulario();
				return "/pages/GraficosConsumo.xhtml";
				}
			}
			else if(verificarPreenchimentoCampoDiaria() || verificarPreenchimentoCampoLigacao()){
				if (verificarDataLancamento() && ehDataCorrente()){
					lancamentoDAO.salvar(lancamento);
					//enviarEmail();
					Messages.addGlobalInfo("Operação realizada com sucesso!");
					limparFormulario();
					}
			}		
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	
	
	@PostConstruct
	public void listar() {
		try {
			lancamentos = lancamentoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar carregar a lista de lançamentos realizados!");
			erro.printStackTrace();
		}
	}

	
	
	public String atualizar(Lancamento lancamento) {
		this.lancamento = lancamento;
		return "/pages/lancamento.xhtml";
	}

	
	
	public void excluir(Lancamento lancamento) {
		try {
			lancamentoDAO.excluir(lancamento);
			lancamentos.remove(lancamento);
			lancamentos = lancamentoDAO.listar();
			Messages.addGlobalInfo("Lançamento excluído com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o lançamento");
			erro.printStackTrace();
		}
	}

	public boolean verificarDataLancamento() {
		if (lancamento.getData().before(
				lancamento.getPlanejamentoCota().getDataInicio())
				|| lancamento.getData().after(
						lancamento.getPlanejamentoCota().getDataFim())) {
			Messages.addGlobalWarn("Data de Lançamento não pertence ao período selecionado!");
			return false;
		} else {
			return true;
		}
	}

	
	@SuppressWarnings("deprecation")
	public boolean ehDataCorrente(){
		LocalDateTime now = LocalDateTime.now(); 
			if(lancamento.getData().getDate() == now.getDayOfMonth() && lancamento.getId() == null || lancamento.getData().getDate() == now.getDayOfMonth() || lancamento.getId() !=null){
			return true;
			}
			Messages.addGlobalWarn("A data de hoje é diferente da data informada!");
			return false;
	}
	
	public boolean darBaixaNoPlanejamentoExecutado() {
		PlanejamentoCota planejamento = lancamento.getPlanejamentoCota();
		int quantidadeDaCota = planejamento.getQuantidadePermitida();
		int quantidadeLancada = lancamento.getQuantidadeRetirada();
        
		if (quantidadeLancada <= quantidadeDaCota
				&& lancamento.getPlanejamentoCota().getId() != null) {
			int quantidadeCotaRestante = quantidadeDaCota - quantidadeLancada;
			planejamento.setQuantidadePermitida(quantidadeCotaRestante);
			planejamentoDAO.atualizar(planejamento);
			return true;
		} else {
			Messages.addGlobalWarn("Quantidade solicitada no lançamento maior que quantidade disponível no planejamento escolhido!");
			return false;
		}
	}

	
	public boolean verificarPreenchimentoCampoDiaria()
	{
		if(lancamento.getValorDiaria() != null){
			return true;
		}
		else return false;
	}
	
	
	public boolean verificarPreenchimentoCampoLigacao(){
		if(lancamento.getNumeroDestino() != null){
			return true;
		}
		else return false;
	}
	
	
	public boolean verificarPreenchimentoCampoXerox(){
		if(lancamento.getQuantidadeRetirada() != null){
			return true;
		}
		else return false;
	}
	
	
	public boolean somarConsumoDiaria(){
		lancamentos = lancamentoDAO.listar();

			for(Lancamento lancamento : lancamentos){
				PlanejamentoCota planejamentoExistenteBanco = lancamento.getPlanejamentoCota();
				
				if(this.lancamento.getPlanejamentoCota().getId() == planejamentoExistenteBanco.getId()){
					Double somaConsumoDiaria = this.lancamento.getValorDiaria() + lancamento.getValorDiaria();
					lancamento.setValorDiaria(somaConsumoDiaria);
					lancamentoDAO.salvar(lancamento);
					return true;
				}
				else if(this.lancamento.getPlanejamentoCota().getId() != lancamento.getPlanejamentoCota().getId()){
					return true;
				}
				else{
					return true;
				}
			}
			return false;
	}
	
	
	private static String templateExemplo = "<b>Nome: </b>%s <br><b>Idade: </b>%s";
	private static StringBuilder templateExemplo2 = new StringBuilder(
			"<b>Nome: </b>%s <br><b>Idade: </b>%s");

	
	
	public boolean analisarEnvioOuNaoDeEmail() throws EmailException{
		int quantidadePermitida = planejamentoCota.getQuantidadePermitida();
		double trintaPorCentoDaQuantidadePermitida = quantidadePermitida * 0.3;
		
		if(planejamentoCota.getQuantidadePermitida() <= trintaPorCentoDaQuantidadePermitida){
			enviarEmail();
			return true;
		}
		return false;
	}
	

	public void enviarEmail() throws EmailException {
		ParametroEmail parametro = new EnviadorDeEmailHelper().new ParametroEmail();
		parametro.setAssunto("Informações sobre os lançamentos de cotas parlamentares");
		parametro.setMensagem("<b> Planejamento executado com menos de 30% da cota restante!</b>");
		
		parametro.setDestinatario(ChaveValor.novoCom("SISLEGIS",
				"cotasparlamentaresdearinos@gmail.com"));
		try {
			EnviadorDeEmailHelper.enviarHtmlMail(parametro);
			System.out.println("Email enviado com sucesso!!!!" + lancamento.getQuantidadeRetirada());
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	
	
	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public List<PlanejamentoCota> getPlanejamentos() {
		return planejamentos;
	}

	public void setPlanejamentos(List<PlanejamentoCota> planejamentos) {
		this.planejamentos = planejamentos;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<FinalidadeDiariaEnum> getFinalidadeDiarias() {
		return finalidadeDiarias;
	}

	public void setFinalidadeDiarias(
			List<FinalidadeDiariaEnum> finalidadeDiarias) {
		this.finalidadeDiarias = finalidadeDiarias;
	}

	public PlanejamentoCota getPlanejamentoCota() {
		return planejamentoCota;
	}

	public void setPlanejamentoCota(PlanejamentoCota planejamentoCota) {
		this.planejamentoCota = planejamentoCota;
	}
}
