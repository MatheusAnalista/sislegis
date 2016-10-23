package br.com.societysystem.sislegis.util;

import java.util.List;

import br.com.societysystem.sislegis.model.Lancamento;

public class IsNullUtil {
	public static boolean isNull(Object parametro) {
		return parametro == null;
	}

	public static boolean isNullOrEmpty(String parametro) {

		return parametro == null || parametro.trim().length() == 0;
	}

	public static boolean isNullOrEmpty(Number parametro) {

		return parametro == null || parametro.doubleValue() == 0.0d;
	}

	public static boolean isNullOrEmpty(List<Lancamento> lancamentos) {

		return lancamentos == null || lancamentos.isEmpty();
	}

}
