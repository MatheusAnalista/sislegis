package br.com.societysystem.sislegis.controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	private List<Lancamento> lancamentosCotaXerografica;
	private List<Lancamento> lancamentosDiarias;
	private List<Lancamento> lancamentosCotaLigacao;
	private List<PlanejamentoCota> planejamentos;
	private List<Pessoa> pessoas;
	private List<FinalidadeDiariaEnum> finalidadeDiarias;
	private PlanejamentoCota planejamentoCota = new PlanejamentoCota();

	
	public LancamentoController() {
		lancamento = new Lancamento();
		planejamentos = planejamentoDAO.listar();
		pessoas = pessoaDAO.listar();
		listarSomenteConsumoDiaria();
		listarSomenteConsumoLigacao();
		listarSomenteConsumoXerografico();
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
				analisarPossibilidadeDeEnvioDeEmail();
				Messages.addGlobalInfo("Operação realizada com sucesso!");
				limparFormulario();
				return "GraficosConsumo?faces-redirect=true";
				}
			}
			else if(verificarPreenchimentoCampoDiaria() || verificarPreenchimentoCampoLigacao()){
				if (verificarDataLancamento() && ehDataCorrente()){
					lancamentoDAO.salvar(lancamento);
					analisarNecessidadeDeEnvioEmailDiaria();
					Messages.addGlobalInfo("Operação realizada com sucesso!");
					limparFormulario();
					return "GraficosConsumo?faces-redirect=true";
					}
			}		
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	

	public String atualizarConsumoDiaria(Lancamento lancamento) 
	{
		this.lancamento = lancamento;
		return "lancamentoCotaDiaria";
	}
	
	public String atualizarConsumoCotaXerografica(Lancamento lancamento) 
	{
		this.lancamento = lancamento;
		return "lancamentoCotaXerografica";
	}

	public String atualizarConsumoCotaLigacao(Lancamento lancamento) 
	{
		this.lancamento = lancamento;
		return "lancamentoCotaLigacao";
	}
	
	
	
	public void excluir(Lancamento lancamento) {
		try {		
			if(lancamentosCotaLigacao.contains(lancamento)){
				lancamentoDAO.excluir(lancamento);
				lancamentosCotaLigacao.remove(lancamento);
				listarSomenteConsumoLigacao();
			}
			if(lancamentosCotaXerografica.contains(lancamento)){
				lancamentoDAO.excluir(lancamento);
				lancamentosCotaXerografica.remove(lancamento);
				listarSomenteConsumoXerografico();
			}	
			if(lancamentosDiarias.contains(lancamento)){		
				lancamentoDAO.excluir(lancamento);
				lancamentosDiarias.remove(lancamento);
				listarSomenteConsumoDiaria();
			}
			Messages.addGlobalInfo("Lançamento excluído com sucesso!");
		}
			catch (RuntimeException erro) {
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
		int ultimaQuantidadeRetirada = 0;
		
		if(lancamento.getId() != null){
			
			for(Lancamento lancamento : lancamentosCotaXerografica){
				if(lancamento.getPlanejamentoCota().getId() == planejamento.getId()){
					ultimaQuantidadeRetirada = lancamento.getQuantidadeRetirada();
					int quantRestante = quantidadeLancada - ultimaQuantidadeRetirada;
					
					if(quantRestante <= quantidadeDaCota){
						int restante = quantidadeDaCota - quantRestante;
						planejamento.setQuantidadePermitida(restante);
						planejamentoDAO.atualizar(planejamento);
						return true;
					}
					else {
						Messages.addGlobalWarn("Quantidade solicitada no lançamento maior que quantidade disponível no planejamento escolhido!");
						return false;
					}
				}
			}	
		}
		
		if (quantidadeLancada <= quantidadeDaCota
				&& lancamento.getPlanejamentoCota().getId() != null) {
			int quantidadeCotaRestante = quantidadeDaCota - quantidadeLancada;
			planejamento.setQuantidadePermitida(quantidadeCotaRestante);
			planejamentoDAO.atualizar(planejamento);
			return true;
		} 
		else {
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

	
	
	public void analisarPossibilidadeDeEnvioDeEmail() throws EmailException{
		if(lancamento.getPlanejamentoCota().getQuantidadePermitida() != null){
			int quantidadeDisponivelCota = lancamento.getPlanejamentoCota().getQuantidadePermitida();
			if(quantidadeDisponivelCota <= 50){
				enviarEmailConsumoXerografico();
			}
		}
	}
	

	
	
	public void analisarNecessidadeDeEnvioEmailDiaria() throws EmailException{
		if(lancamento.getValorDiaria() != null){
			double valorConsumido = lancamento.getValorDiaria();
			if(valorConsumido >= 1000){
				enviarEmailConsumoDiaria();
			}
		}
	}

	public void enviarEmailConsumoXerografico() throws EmailException {
		ParametroEmail parametro = new EnviadorDeEmailHelper().new ParametroEmail();
		parametro.setAssunto("Informações sobre os lançamentos de cotas parlamentares");
		parametro.setMensagem("<b>Há planejamento de cota xerográfica com quantidade inferior a 50 cópias e/ou impressões</b>");
		parametro.setDestinatario(ChaveValor.novoCom("SISLEGIS",
				"cotasparlamentaresdearinos@gmail.com"));
		try {
			EnviadorDeEmailHelper.enviarHtmlMail(parametro);
			System.out.println("Email enviado com sucesso!!!!" + lancamento.getQuantidadeRetirada());
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	
	
	public void enviarEmailConsumoDiaria() throws EmailException {
		ParametroEmail parametro = new EnviadorDeEmailHelper().new ParametroEmail();
		parametro.setAssunto("Informações sobre os lançamentos de cotas parlamentares");
		parametro.setMensagem("<b>Lançamento de consumo de diárias acima de R$1.000,00. Verifique seus gastos, bem como benefícios oriundos destes.</b>");
		
		parametro.setDestinatario(ChaveValor.novoCom("SISLEGIS",
				"cotasparlamentaresdearinos@gmail.com"));
		try {
			EnviadorDeEmailHelper.enviarHtmlMail(parametro);
			System.out.println("Email enviado com sucesso!!!!" + lancamento.getQuantidadeRetirada());
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@PostConstruct
	public void listarSomenteConsumoXerografico(){
		lancamentosCotaXerografica = lancamentoDAO.recuperarPorCotaXerografica();
	}
	
	
	@PostConstruct
	public void listarSomenteConsumoDiaria(){
		lancamentosDiarias = lancamentoDAO.recuperarPorDiaria();
	}
	
	
	@PostConstruct
	public void listarSomenteConsumoLigacao(){
		lancamentosCotaLigacao = lancamentoDAO.recuperarPorCotaLigacao();
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

	public List<Lancamento> getLancamentosCotaXerografica() {
		return lancamentosCotaXerografica;
	}
	public void setLancamentosCotaXerografica(
			List<Lancamento> lancamentosCotaXerografica) {
		this.lancamentosCotaXerografica = lancamentosCotaXerografica;
	}

	public List<Lancamento> getLancamentosDiarias() {
		return lancamentosDiarias;
	}

	public void setLancamentosDiarias(List<Lancamento> lancamentosDiarias) {
		this.lancamentosDiarias = lancamentosDiarias;
	}

	public List<Lancamento> getLancamentosCotaLigacao() {
		return lancamentosCotaLigacao;
	}

}
