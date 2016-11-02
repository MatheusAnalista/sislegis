package br.com.societysystem.sislegis.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.societysystem.sislegis.vo.ChaveValor;

public final class EnviadorDeEmailHelper {

	
	public static void enviarHtmlMail(final ParametroEmail parametro)
			throws EmailException {

		if (!parametro.isValido()) {
			throw new IllegalArgumentException(
					"Parametro inválido! preencha as informações.");
		}

		try {
			HtmlEmail email = new HtmlEmail();

			email.setHostName(Configuracao.HOSTNAME.getValueString());

			email.addTo(parametro.getDestinatario().getValor(), parametro
					.getDestinatario().getChave());
			
			email.setSubject(parametro.getAssunto());

			email.setHtmlMsg(parametro.getMensagem());

			email.setFrom(Configuracao.FROM_EMAIL.getValueString(),
					Configuracao.FROM_NOME.getValueString());

			email.setAuthentication(Configuracao.USUARIO.getValueString(),
					Configuracao.SENHA.getValueString());

			email.setSmtpPort(Configuracao.PORTA.getValueInt());

			email.setSSLOnConnect(true);

			email.setStartTLSEnabled(true);
			// email.setDebug(true);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();

			throw e;
		}
	}

	/**
	 * 
	 * Enum referente as configurações padrões para envio de email
	 * 
	 * @author Matheus Mendes
	 *
	 */
	private enum Configuracao {

		PORTA(465),

		USUARIO("cotasparlamentaresdearinos@gmail.com"),

		SENHA(""),

		FROM_EMAIL("contato@sislegis.com.br"),

		FROM_NOME("Contato SISLEGIS"),

		HOSTNAME("smtp.gmail.com");

		private Object value;

		private Configuracao(Object value) {
			this.setValue(value);
		}

		public int getValueInt() {
			return Integer.valueOf(value.toString());
		}

		public String getValueString() {
			return value.toString();
		}

		public void setValue(Object value) {
			this.value = value;
		}

	};

	public class ParametroEmail {
		private ChaveValor<String, String> destinatario;

		private String assunto;

		private String mensagem;

		public ParametroEmail() {
		}

		public ParametroEmail(ChaveValor<String, String> destinatario,
				String assunto, String mensagem) {
			super();
			this.destinatario = destinatario;
			this.assunto = assunto;
			this.mensagem = mensagem;
		}

		public boolean isValido() {
			boolean result = mensagem != null && mensagem.trim().length() > 0;
			result &= assunto != null && assunto.trim().length() > 0;
			result &= destinatario != null
					&& destinatario.getChave().trim().length() > 0
					&& destinatario.getValor().trim().length() > 0;
			return result;
		}

		public ChaveValor<String, String> getDestinatario() {
			return destinatario;
		}

		public void setDestinatario(ChaveValor<String, String> destinatario) {
			this.destinatario = destinatario;
		}

		public String getAssunto() {
			return assunto;
		}

		public void setAssunto(String assunto) {
			this.assunto = assunto;
		}

		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

	}

}
