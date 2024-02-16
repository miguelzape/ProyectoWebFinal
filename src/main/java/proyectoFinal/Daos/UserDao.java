package proyectoFinal.Daos;

import java.util.Arrays;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import proyectoFinal.entities.User;
import proyectoFinal.utils.JpaUtil;

public class UserDao {

	private EntityManager em;

	public UserDao() {
		em = JpaUtil.getEM();
	}

	public void close() {
		JpaUtil.closeEM();
		em=null;
	}

	public List<User> getUsers() {
		return em.createQuery("from User", User.class).getResultList();
	}
	
	public List<User> getUsersOrdenados(String campo) {
		String cadena = "from User c order by c."+campo;
		return em.createQuery(cadena, User.class).getResultList();
	}

	/**
	 * Esta funcion devuelve el userID que concuerde con el usuario y clave
	 * facilitados en caso de no exister ese usuario se devuelve un -1
	 * 
	 * @param usuario
	 * @param clave
	 * @return Devuelve un long con el idUsuario. -1 si no existe
	 */
	public long validarUser(String usuario, String clave) {
		List<User> users;

		TypedQuery<User> query = em.createQuery("from User c where c.usuario=?1", User.class);
		query.setParameter(1, usuario);

		// no debe existir mas de un usuario con el mismo nombre, pero leo una lista
		// por si ocurre que si existe
		users = query.getResultList();
		if (users.size() < 1) {
			return -1L;
		}
		for (User u : users) {		
			if (u.getClave().equals(clave)) {
				return u.getIdUsuario();
			}
		}
		return -1L;
	}

	public List<User> getUserByUsuario(String usuario) {
		TypedQuery<User> query = em.createQuery("from User c where c.usuario=?1", User.class);
		query.setParameter(1, usuario);
		return query.getResultList();
	}
	
	public User getUser(long id) {
	   return em.find(User.class, id);
	}

	public boolean existUsuario(String usuario) {

		TypedQuery<User> query = em.createQuery("from User c where c.usuario=?1", User.class);
		query.setParameter(1, usuario);
		return (query.getResultList().size()) > 0;

	}

	public void putUser(User u) {
		em.getTransaction().begin();
		try {
			em.persist(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public void deleteUser(long id) {
		try {
			User user = em.find(User.class, id);

			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	public void editUser(User user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

}
