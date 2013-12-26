/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Reunion;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.ReunionServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class PanelReunionesBean extends BotonesBean implements Serializable{

    @EJB
    private ReunionServicio reunionServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    private String ENTIDAD = "Panel de Reuniones";
    private Usuario usrSesion;
    private Empleado emplSesion;
    
    private Reunion reunion;
    private Reunion reunionSeleccionado;
    private List<Reunion> reuniones;
    
    private Proyecto proyecto;
    private String codProyecto;
    
    //ELEMENTO DE VISTA
    private PieChartModel pieModel;
    
    
    
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();

        System.out.println("PROYECTO: " + codProyecto);
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        System.out.println("USUARIO: " + usrSesion);
        this.emplSesion = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Empleado");

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parametros = context.getExternalContext().getRequestParameterMap();
        if (this.proyecto == null) {
            this.codProyecto = parametros.get("codProyecto");
            this.proyecto = this.proyectoServicio.findByID(Long.parseLong(codProyecto));
        }
        System.out.println("PROYECTO: " + proyecto.getNombreProyecto());
        this.reuniones = this.reunionServicio.findByProyecto(proyecto);
        createPieModel();
        this.reunion = new Reunion();
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("Avance", proyecto.getAvance().floatValue());
        pieModel.set("Restante", 100 - proyecto.getAvance().floatValue());
    }
    
    
   
    
     public String getUsrAuditoria(String usr) {
        if (usr == null || "".equals(usr)) {
            return "";
        } else {
            System.out.println(usr);
            System.out.println(usuarioServicio.findByID(usr));
            try {
                usuarioServicio.findByID(usr);
                return usuarioServicio.findByID(usr).getUsuario();
            } catch (NullPointerException e) {
                return "";
            }
        }
    }
    
     public void nuevaReunion(ActionEvent evento) {
        super.crear();
        this.reunion = new Reunion();
    }
     
     public void modificarReunion(ActionEvent evento) {
        this.reunion = new Reunion();
        try {
            this.reunion = (Reunion) BeanUtils.cloneBean(this.reunionSeleccionado);
        } catch (Exception e) {
        }
        super.modificar();
    }
     
     
     public void guardarReunion(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                reunion.getPk().setProyecto(proyecto.getCodigo());
                reunion.getPk().setCodigoReunion(Long.parseLong(String.valueOf(this.reunionServicio.obtener().size())) + 1);
                reunion.setProyecto(proyecto);
                reunion.setUsrCreacion(usrSesion.getCodigo());
                reunion.setFcreacion(new Date());
                reunionServicio.crear(reunion);
                this.reuniones.add(reunion);
                MensajesGenericos.infoCrear("Reunion", this.reunion.getPk().toString(), Boolean.TRUE);
            } else if (super.getEnEdicion()) {
                int i = this.reuniones.indexOf(this.reunion);
                reunion.setUsrModificacion(usrSesion.getCodigo());
                reunion.setFmodificacion(new Date());
                this.reuniones.set(i, this.reunion);
                reunionServicio.actualizar(reunion);
                MensajesGenericos.infoModificar("Reunion", this.reunion.getPk().toString(), Boolean.TRUE);
            }
            super.sinSeleccion();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }
     
     public void verAuditoriaReunion(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }
     
      public void filaSeleccionadaReunion(ActionEvent evento) {
        if (reunionSeleccionado instanceof Reunion) {
            super.seleccionadoUno();
            try {
                this.reunion = new Reunion();
                this.reunion = (Reunion) BeanUtils.cloneBean(this.reunionSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE LA REUNION");
            } catch (Exception e) {
                System.out.println("Error en Gasto");
            }
        } else {
            super.sinSeleccion();
        }
    }

    public String getENTIDAD() {
        return ENTIDAD;
    }

    public void setENTIDAD(String ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }

    public Usuario getUsrSesion() {
        return usrSesion;
    }

    public void setUsrSesion(Usuario usrSesion) {
        this.usrSesion = usrSesion;
    }

    public Empleado getEmplSesion() {
        return emplSesion;
    }

    public void setEmplSesion(Empleado emplSesion) {
        this.emplSesion = emplSesion;
    }

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    public Reunion getReunionSeleccionado() {
        return reunionSeleccionado;
    }

    public void setReunionSeleccionado(Reunion reunionSeleccionado) {
        this.reunionSeleccionado = reunionSeleccionado;
    }

    public List<Reunion> getReuniones() {
        return reuniones;
    }

    public void setReuniones(List<Reunion> reuniones) {
        this.reuniones = reuniones;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(String codProyecto) {
        this.codProyecto = codProyecto;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
      
      
}
