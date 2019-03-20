/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package somosinventario.com.co.facade;

import somosinventario.com.co.ejb.UsuarioFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import somosinventario.com.co.entities.Usuarios;

/**
 * La clase UsuariosFacade establece la conexión entre las entities y el controlador del programa de los objetos Usuarios.
 * @author Andres Camilo Osorio
 * @author Andres Galvis
 * @author Giancarlo Vásquez
 * @version 1.0
 * @since Somos_Inventario_SAS
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuarioFacadeLocal{
    
    @PersistenceContext(unitName = "Somos_Inventarios_SASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public Usuarios iniciarSesion(Usuarios us) {
        Usuarios usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuarios u WHERE u.username = ?1 and u.password = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getUsername());
            query.setParameter(2, us.getPassword());

            List<Usuarios> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }       
        return usuario;
    }
 }


