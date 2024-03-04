package proyectoFinal.Daos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import proyectoFinal.entities.User;
import proyectoFinal.utils.JpaUtil;

public class UserDao {

	private static final Logger logger = LogManager.getLogger(UserDao.class);
	private EntityManager em;

	public UserDao() {
		em = JpaUtil.getEM();
	}

	public void close() {
		JpaUtil.closeEM();
		em=null;
	}

	// cRud READ
	// lee todos los usuarios y los devuelve en una lista
	public List<User> getUsers() {
		logger.trace("DAO. Leer todos los usuarios");
		return em.createQuery("from User", User.class).getResultList();
	}
	
	// cRud READ
	// lee todos los usuarios y los devuelve en una lista ordenada por el 'campo'
	public List<User> getUsersOrdenados(String campo) {
		
		String cadena = "from User c order by c."+campo;
		logger.trace("DAO. Leer todos los usuarios ordenador por un campo: "+cadena);
		return em.createQuery(cadena, User.class).getResultList();
	}

	/**
	 * Esta funcion devuelve el objeto 'User' que concuerde con el usuario y clave
	 * facilitados en caso de no exister ese usuario se devuelve un nul
	 * 
	 * @param usuario
	 * @param clave
	 * @return Devuelve un objeto de tipo User
	 */
	//cRud READ
	public User validarUser(String usuario, String clave) {
		logger.trace("DAO. Validar si el usuario y clave son validos");
		
		TypedQuery<User> query = em.createQuery("from User c where c.usuario=?1", User.class);
		query.setParameter(1, usuario);

		// no debe existir mas de un usuario con el mismo nombre, pero leo una lista
		// por si ocurre que si existe
		List<User> users = query.getResultList();
		if (users.size() < 1) {
			return null;
		}
		for (User u : users) {		
			if (u.getClave().equals(clave)) {
				return u;
			}
		}
		return null;
	}
	
	//cRud READ
	public User getUser(long id) {
	logger.trace("DAO. Leer un usuario buscando por su id= "+id);
	   return em.find(User.class, id);
	}

	//cRud READ
	public boolean existUsuario(String usuario) {
		logger.trace("DAO. Comprobar si existe el usuario= "+usuario);
		
		TypedQuery<User> query = em.createQuery("from User c where c.usuario=?1", User.class);
		query.setParameter(1, usuario);
		return (query.getResultList().size()) > 0;

	}

	//Crud CREATE
	public void putUser(User u) {
		logger.trace("DAO. Crear un usuario nuevo= "+u.getUsuario());
		em.getTransaction().begin();
		try {
			em.persist(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	//cruD DELETE
	public void deleteUser(long id) {
		logger.trace("DAO. Eliminar un usuario usando su id= "+id);
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
	
	//crUd UPDATE
	public void editUser(User user) {
		logger.trace("DAO. Editar un usuario= "+user.getUsuario());
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
