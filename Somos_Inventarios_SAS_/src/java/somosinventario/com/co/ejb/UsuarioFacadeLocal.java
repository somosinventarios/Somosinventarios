/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package somosinventario.com.co.ejb;

import java.util.List;
import somosinventario.com.co.entities.Usuarios;
import javax.ejb.Local;

/**
 *
 * @author Andrés Camilo Osorio
 */
@Local
public interface UsuarioFacadeLocal {
    void create(Usuarios user);
    void edit(Usuarios user);
    void remove(Usuarios user);
    Usuarios find(Object id);
    List<Usuarios> findAll();
    List<Usuarios> findRange(int[] range);
    int count();
    Usuarios iniciarSesion(Usuarios us);
}
