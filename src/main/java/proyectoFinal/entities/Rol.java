package proyectoFinal.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="TB_ROLES")
public class Rol implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idRol;
	
	//private User user;
	private String tipo;
	
	
	// Constructores
	public Rol() {
		super();
	}
	
	public Rol(String tipo) {
		super();
		this.tipo = tipo;
	}
	
	//metodos Get y Set
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getIdRol() {
		return idRol;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", tipo=" + tipo + "]";
	}
	
	
	
	
}



