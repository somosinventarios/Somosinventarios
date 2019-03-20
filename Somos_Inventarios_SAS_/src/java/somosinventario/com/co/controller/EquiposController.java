package somosinventario.com.co.controller;

import somosinventario.com.co.entities.Equipos;
import somosinventario.com.co.controller.util.JsfUtil;
import somosinventario.com.co.controller.util.JsfUtil.PersistAction;
import somosinventario.com.co.facade.EquiposFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 * La clase EquiposController se usa para controlar todas la acciones pertientes a los objetos equipos (Equipos).
 * @author Andres Camilo Osorio
 * @author Andres Galvis
 * @author Giancarlo Vásquez
 * @version 1.0
 * @since Somos_Inventario_SAS
 */
@Named("equiposController")
@SessionScoped
public class EquiposController implements Serializable {

    @EJB
    private somosinventario.com.co.facade.EquiposFacade ejbFacade;
    private List<Equipos> items = null;
    private Equipos selected;

    public EquiposController() {
        selected = new Equipos();
    }
    
    public Equipos getSelected() {
        return selected;
    }

    public void setSelected(Equipos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EquiposFacade getFacade() {
        return ejbFacade;
    }
    
    @PostConstruct
    public void actualizar(){
        items = getFacade().findAll();
    }

    public Equipos prepareCreate() {
        selected = new Equipos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        System.out.println("Hooooola");
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EquiposCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EquiposUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EquiposDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Equipos> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Equipos getEquipos(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Equipos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Equipos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Equipos.class)
    public static class EquiposControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EquiposController controller = (EquiposController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "equiposController");
            return controller.getEquipos(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Equipos) {
                Equipos o = (Equipos) object;
                return getStringKey(o.getReferencia());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Equipos.class.getName()});
                return null;
            }
        }

    }

}
