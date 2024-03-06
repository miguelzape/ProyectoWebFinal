package proyectoFinal.daos;

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

	/************************************************
	 * cRud READ       getUsers
	 * lee todos los usuarios y los devuelve en una lista
	 * @return List<User> con la lista de usuarios leida
	 ************************************************/
	public List<User> getUsers() {
		logger.trace("DAO. Leer todos los usuarios");
		return em.createQuery("from User", User.class).getResultList();
	}
	
	
	/*************************************************
	 * cRud READ       getUsersOrdenados
	 *  Ordena y/o filtra la busqueda
	 *  lee todos los usuarios y los devuelve en una lista ordenada por el 'ordenCampo'
	 *  y filtrada por 'filtroCampo' con el valor 'filtroValor) 
	 * @param ordenCampo. campo por el que se ordena
	 * @param filtroCampo. campo por el que se filtra
	 * @param filtroValor. valor que se busca el el campo 'filtroCampo'
	 * @return List<User> con la lista de usuarios leida
	 ************************************************/
	public List<User> getUsersOrdenados(String ordenCampo, String filtroCampo, String filtroValor) {
		
		String cadena = "from User c";
		
		if (filtroCampo!=null && !filtroCampo.equals("0") && filtroValor!=null && filtroValor.length()>0) {
			cadena = (cadena+" where c."+filtroCampo+"='"+filtroValor+"'");
		}
		
		cadena = (ordenCampo!=null && !ordenCampo.equals(""))?
				 (cadena+" order by c."+ordenCampo):cadena;
		
		logger.trace("DAO. Leer usuarios filtrados por un campo: "+cadena);
		return em.createQuery(cadena, User.class).getResultList();
	}

	/************************************************
	 *  cRud READ       validarUser
	 * Esta funcion devuelve el objeto 'User' que coincida con el usuario y clave
	 * facilitados en caso de no existir se devuelve un null
	 * @param usuario
	 * @param clave
	 * @return User. Devuelve un objeto de tipo User 
	 ************************************************/
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
	
	/************************************************
	 * cRud READ          getUser
	 * lee un usuario cuyo numero de id sea el que se para por parametro
	 * @param id. Un long con el id del usuario que se busca
	 * @return User Devuelve un objeto de tipo User, o un null si no se encuentra 
	 ************************************************/
	public User getUser(long id) {
	logger.trace("DAO. Leer un usuario buscando por su id= "+id);
	   return em.find(User.class, id);
	}

	
	/************************************************
	 * cRud READ           existUsuario
	 * @param usuario. el usuario que se quiere comprobar si ya existe
	 * @return boolean. Devuelve true si existe el usuario buscado y false en caso contrario
	 ************************************************/
	public boolean existUsuario(String usuario) {
		logger.trace("DAO. Comprobar si existe el usuario= "+usuario);
		
		TypedQuery<User> query = em.createQuery("from User c where c.usuario=?1", User.class);
		query.setParameter(1, usuario);
		return (query.getResultList().size()) > 0;

	}
	
	/*************************************************
	 * Crud CREATE     putUser
	 * @param User. El usuario que se añade a la base de datos
	 **************************************************/
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
	
	/*************************************************
	 * cruD DELETE     deleteUser
	 * @param Long. El id que identifica al usuario a borrar
	 **************************************************/
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
	
	/*************************************************
	 * crUd UPDATE     editUser
	 * @param User. El objeto 'User' que se va a sustituir en la base de datos
	 **************************************************/
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
