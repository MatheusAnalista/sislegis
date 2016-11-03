package br.com.societysystem.sislegis.controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.apache.commons.collections.map.HashedMap;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.societysystem.sislegis.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@ManagedBean
public class RelatorioController {

	public void gerarRelatorioVereadores() {
		String caminhoRelatorio = Faces.getRealPath("/relatorios/vereadores.jasper");

		Map<String, Object> parametros = new HashMap<>();

		Connection conexao = HibernateUtil.getConexao();

		try {
			JasperPrint relatorioDeVereadores = JasperFillManager.fillReport(
					caminhoRelatorio, parametros, conexao);
			JasperPrintManager.printReport(relatorioDeVereadores, false);
		} catch (JRException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar gerar o relatório de vereadores!");
		}
	}

	public void gerarRelatorioDeConsumoCotaXerografica() {
		String caminhoRelatorio = Faces.getRealPath("/relatorios/consumoCotaXerograficaGeral.jasper");

		Map<String, Object> parametros = new HashMap<>();

		Connection conexao = HibernateUtil.getConexao();

		try {
			JasperPrint relatorioDeCotaXerograficaGeral = JasperFillManager.fillReport(
					caminhoRelatorio, parametros, conexao);
			JasperPrintManager.printReport(relatorioDeCotaXerograficaGeral, false);
		} catch (JRException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar gerar o relatório de consumo de cota xerográfica!");
		}
	}

	public void gerarRelatorioDeConsumoCotaLigacao() {
		String caminhoRelatorio = Faces.getRealPath("/relatorios/consumoCotaLigacaoGeral.jasper");

		Map<String, Object> parametros = new HashMap<>();

		Connection conexao = HibernateUtil.getConexao();

		try {
			JasperPrint relatorioDeCotaLigacaoGeral = JasperFillManager.fillReport(
					caminhoRelatorio, parametros, conexao);
			JasperPrintManager.printReport(relatorioDeCotaLigacaoGeral, false);
		} catch (JRException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar gerar o relatório de consumo de cota de ligações!");
		}
	}

	public void gerarRelatorioDeConsumoDiaria() {
		String caminhoRelatorio = Faces.getRealPath("/relatorios/consumoDiariasGeral.jasper");

		Map<String, Object> parametros = new HashMap<>();

		Connection conexao = HibernateUtil.getConexao();

		try {
			JasperPrint relatorioConsumoDiariasGeral = JasperFillManager.fillReport(
					caminhoRelatorio, parametros, conexao);
			JasperPrintManager.printReport(relatorioConsumoDiariasGeral, false);
		} catch (JRException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao tentar gerar o relatório de gastos com diárias!");
		}
	}
}
