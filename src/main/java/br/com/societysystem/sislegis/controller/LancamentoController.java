package br.com.societysystem.sislegis.controller;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.omnifaces.util.Messages;
import br.com.societysystem.sislegis.model.FinalidadeDiariaEnum;
import br.com.societysystem.sislegis.model.Lancamento;
import br.com.societysystem.sislegis.model.Pessoa;
import br.com.societysystem.sislegis.model.PlanejamentoCota;
import br.com.societysystem.sislegis.repository.LancamentoDAO;
import br.com.societysystem.sislegis.repository.PessoaDAO;
import br.com.societysystem.sislegis.repository.PlanejamentoCotaDAO;


@ManagedBean
public class LancamentoController 
{	
	LancamentoDAO lancamentoDAO = new LancamentoDAO();
	PlanejamentoCotaDAO planejamentoDAO = new PlanejamentoCotaDAO();
	PessoaDAO pessoaDAO = new PessoaDAO();
	private Lancamento lancamento;
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

	
	public void salvar() throws EmailException {
		try {
			if (verificarDataLancamento() == true && darBaixaNoPlanejamentoExecutado() == true) {
				lancamentoDAO.salvar(lancamento);
				enviarEmail();
				Messages.addGlobalInfo("Operação realizada com sucesso!");
				limparFormulario();
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}
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
		if (lancamento.getData().before(lancamento.getPlanejamentoCota().getDataInicio())
				|| lancamento.getData().after(lancamento.getPlanejamentoCota().getDataFim()))
		{
			Messages.addGlobalWarn("Data de Lançamento não pertence ao período selecionado!");
			return false;
		} else {
			return true;
		}
	}

	
	public boolean darBaixaNoPlanejamentoExecutado()
	{
		PlanejamentoCota planejamento = lancamento.getPlanejamentoCota();	
		int quantidadeDaCota = planejamento.getQuantidadePermitida();
		int quantidadeLancada = lancamento.getQuantidadeRetirada();

		if(quantidadeLancada <= quantidadeDaCota && lancamento.getPlanejamentoCota().getId() != null){
			int quantidadeCotaRestante = quantidadeDaCota - quantidadeLancada;
			planejamento.setQuantidadePermitida(quantidadeCotaRestante);
			planejamentoDAO.atualizar(planejamento);	
			return true;
		}
		else{
			Messages.addGlobalWarn("Quantidade solicitada no lançamento maior que quantidade disponível no planejamento escolhido!");
			return false;
			}
		}
	
	
	public void enviarEmail() throws EmailException  
	{
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		email.addTo("suportetecnologia@outlook.com.br", "Teste"); //destinatário
		email.setFrom("suportetecnologia@outlook.com.br", "Matheus Mendes"); // remetente
		email.setSubject("Lançamento de consumo de cota"); // assunto do e-mail
		email.setMsg("mensagem"); //conteudo do e-mail
		email.setAuthentication("sti.tecnologiainformacao@gmail.com", "acreditoqposso");
		email.setSmtpPort(465);
		email.setSSL(true);
		email.setTLS(true);
		email.send();		
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
