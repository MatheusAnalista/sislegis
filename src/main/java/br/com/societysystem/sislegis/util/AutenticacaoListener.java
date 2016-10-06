package br.com.societysystem.sislegis.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.societysystem.sislegis.controller.Autenticacao;
import br.com.societysystem.sislegis.model.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
		System.out.println("Pagina atual " +paginaAtual);
		
		boolean epaginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		
		if(!epaginaAutenticacao){
			Autenticacao autenticacao = Faces.getSessionAttribute("autenticacao");
			if(autenticacao == null)
			{
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}	
			Usuario usuario = autenticacao.getUsuarioAutenticado();
			
			if(usuario == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("antes da fase " +event.getPhaseId());
		
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.ANY_PHASE;
	}

}
