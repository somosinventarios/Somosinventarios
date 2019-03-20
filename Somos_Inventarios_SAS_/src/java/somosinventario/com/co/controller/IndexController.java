/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package somosinventario.com.co.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import somosinventario.com.co.entities.Usuarios;
import somosinventario.com.co.ejb.UsuarioFacadeLocal;
/**
 * La clase IndexController se usa para controlar todas la acciones pertientes al inicio de sesion.
 * @author Andres Camilo Osorio
 * @author Andres Galvis
 * @author Giancarlo Vásquez
 * @version 1.0
 * @since Somos_Inventario_SAS
 */
@Named
@javax.faces.view.ViewScoped
public class IndexController implements Serializable {

    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    private Usuarios usuario;

    /**
     *El método init crea una nueva instancia para el logueo del usuario
     */
    @PostConstruct
    public void init() {
        usuario = new Usuarios();
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
   /**
    * El método iniciarSesion se encarga de verficar que el usuario si este registrado en la base de datos, para posteriormente
    * permitirle el acceso

    */
    public String iniciarSesion() {
        Usuarios us;
        String redireccion = null;
        try {
            us = EJBUsuario.iniciarSesion(usuario);
            if (us != null) {     
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "Menu?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Datos incorrectos"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso",e.toString()));
        }
        return redireccion;
    }
    
    
}














