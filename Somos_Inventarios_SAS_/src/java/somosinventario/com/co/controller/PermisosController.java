 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package somosinventario.com.co.controller;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import somosinventario.com.co.entities.Usuarios;
/**
 * La clase PermisosController se usa para controlar todas la acciones pertientes a los objetos IndexController
 * @author Andres Camilo Osorio
 * @author Andres Galvis
 * @author Giancarlo Vásquez
 * @version 1.0
 * @since Somos_Inventario_SAS
 */
@Named
@javax.faces.view.ViewScoped
/**
    * El método PermisosController  se encarga de verficar que el usuario se encuentra logeado cuando desde otro navegador se ingresa a la url 
    * para acceder al las tablas, en caso de que no esté logueado, se redirige a la penstaña index.
    */
public class PermisosController implements Serializable{
    public void verificarSesion() {
        try {
            Usuarios us = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../index.xhtml");
            } else {
            }
        } catch (Exception e) {
        }
    }

    public void verificarSesionPrincipales() {
        try {
            Usuarios us = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../principal.xhtml");
            } else {
            }
        } catch (Exception e) {
        }
    }
    

}

