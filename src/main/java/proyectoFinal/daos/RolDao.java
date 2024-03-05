package proyectoFinal.daos;

import java.util.List;
import jakarta.persistence.EntityManager;
import proyectoFinal.entities.Rol;
import proyectoFinal.utils.JpaUtil;
public class RolDao {

	private EntityManager em;

	public RolDao() {
		em = JpaUtil.getEM();
	}
	
	public void close() {
		if (em!=null) {
			em.close();
			em=null;
		}
	}
	
	public List<Rol> getRoles (){
		List<Rol> roles;
		
		roles = em.createQuery("from Rol", Rol.class).getResultList();
		
		return roles;
	}
	
	public void putRol (Rol u) {
		 em.getTransaction().begin(); 
		 try {
			 em.persist(u);
			 em.getTransaction().commit();
		 }catch(Exception e) {
			 em.getTransaction().rollback();
		 }
	}
	
	
	
	
	
	
	
}
