/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package somosinventario.com.co.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import somosinventario.com.co.entities.Equipos;

/**
 * La clase EquiposFacade establece la conexión entre las entities y el controlador del programa de los Equipos.
 * @author Andres Camilo Osorio
 * @author Andres Galvis
 * @author Giancarlo Vásquez
 * @version 1.0
 * @since Somos_Inventario_SAS
 */
@Stateless
public class EquiposFacade extends AbstractFacade<Equipos> {

    @PersistenceContext(unitName = "Somos_Inventarios_SASPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquiposFacade() {
        super(Equipos.class);
    }
    
}
