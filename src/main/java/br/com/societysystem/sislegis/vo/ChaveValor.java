package br.com.societysystem.sislegis.vo;

public class ChaveValor<K, V> {
	private K chave;
	private V valor;

	private ChaveValor(K chave, V valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public static <K, V> ChaveValor<K, V> novoCom(final K chave, final V valor) {

		return new ChaveValor<K, V>(chave, valor);
	}

	public K getChave() {
		return chave;
	}

	public void setChave(K chave) {
		this.chave = chave;
	}

	public V getValor() {
		return valor;
	}

	public void setValor(V valor) {
		this.valor = valor;
	}

}
