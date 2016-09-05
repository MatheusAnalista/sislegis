package br.com.societysystem.sislegis.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t1_estado_tb")
public class Estado extends Entidade<Long>
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_estado")
	private Long idEstado;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@Column(nullable = false, length = 2)
	private String uf;
	
	
	public Long getIdEstado() {
		return idEstado;
	}
	
	public Long getId(){
		return this.idEstado;
	}
	
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEstado == null) ? 0 : idEstado.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (idEstado == null) {
			if (other.idEstado != null)
				return false;
		} else if (!idEstado.equals(other.idEstado))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Estado [idEstado=" + idEstado + ", nome=" + nome + ", uf=" + uf + "]";
	}
	
	
	
	
}
