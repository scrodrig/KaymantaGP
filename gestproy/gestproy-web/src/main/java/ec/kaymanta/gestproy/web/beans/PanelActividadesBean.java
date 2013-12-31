/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ActividadServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class PanelActividadesBean extends BotonesBean implements Serializable{

    /**
     * Creates a new instance of PanelActividadesBean
     */
   
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    @EJB
    private ActividadServicio actividadServicio;
    
     private String ENTIDAD = "Panel de Actividades";
    private Usuario usrSesion;
    private Empleado emplSesion;
    
    //Variables de actividad
    private Actividad actividad;
    private Actividad actividadSeleccionada;
    private Actividad respaldoActividad;
    
    private List<Actividad> actividades;
    
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
        this.actividades = this.actividadServicio.findByProyecto(proyecto);        
        createPieModel();
        this.actividad = new Actividad();
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
    
     public String getColor(BigDecimal avance) {
        if (avance.compareTo(BigDecimal.valueOf(100)) < 0 && avance.compareTo(BigDecimal.valueOf(60)) > 0) {
            return "darkgreen";
        } else if (avance.compareTo(BigDecimal.valueOf(60)) < 0 && avance.compareTo(BigDecimal.valueOf(10)) > 0) {
            return "yellowgreen";
        } else if (avance.compareTo(BigDecimal.valueOf(10)) < 0) {
            return "red";
        }
        return "navy";


    }
    
    public void nuevoActividad(ActionEvent evento) {
        super.crear();
        this.actividad = new Actividad();
    }
    
    public void verAuditoriaActividad(ActionEvent evento) throws IllegalAccessException {
        try {
            this.actividad = new Actividad();
            this.actividad = (Actividad) BeanUtils.cloneBean(this.actividadSeleccionada);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }
    
    public void modificarActividad(ActionEvent evento) {
        this.actividad = new Actividad();
        try {
            this.actividad = (Actividad) BeanUtils.cloneBean(this.actividadSeleccionada);
        } catch (Exception e) {
        }
        super.modificar();
    }
    
    public void guardarActividad(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                //CREAR ACTIVIDAD
                this.actividad.setProyecto(proyecto);
                this.actividad.setCodProyecto(proyecto.getCodigo());
                this.actividad.setUsrCreacion(usrSesion.getCodigo());
                this.actividad.setFcreacion(new Date());
                this.actividad.setAvance(BigDecimal.ZERO);

                this.actividad.sethDiaEst(0L);
                this.actividad.sethDiaReal(0L);
                this.actividad.sethTrabEst(BigDecimal.ZERO);
                this.actividad.sethTrabReal(BigDecimal.ZERO);
                this.actividad.settTotalEst(0L);
                this.actividad.settTotalReal(0L);

                System.out.println("Actividad: " + actividad.getNombreActividad());
                this.actividadServicio.crear(actividad);
                //--CREAR ACTIVIDAD
                this.actividades.add(this.actividad);
                MensajesGenericos.infoCrear("Actividad", this.actividad.getCodigo().toString().concat(" - ").concat(this.actividad.getNombreActividad()), Boolean.TRUE);
            } else if (super.getEnEdicion()) {
                int i = this.actividades.indexOf(this.actividad);
                this.actividad.setUsrModificacion(usrSesion.getCodigo());
                this.actividad.setFmodificacion(new Date());
                this.actividadServicio.actualizar(actividad);
                System.out.println("Actividad: " + actividad.getNombreActividad());
                this.actividadServicio.actualizar(actividad);
                this.actividades.set(i, this.actividad);
                MensajesGenericos.infoModificar("Actividad", this.actividad.getCodigo().toString().concat(" - ").concat(this.actividad.getNombreActividad()), Boolean.TRUE);
            }
            super.sinSeleccion();
            //CREAR ACTIVIDAD CON RESPONSABLE
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }
    
    public void filaSeleccionadaActividad(ActionEvent evento) {
        if (actividadSeleccionada instanceof Actividad) {
            super.seleccionadoUno();
            try {
                this.actividad = new Actividad();
                this.actividad = (Actividad) BeanUtils.cloneBean(this.actividadSeleccionada);
                System.out.println("ESTOY AQUI Y SI SELECCIONE, LA ACTIVIDAD ES: " + actividad.getNombreActividad());

            } catch (Exception e) {
                System.out.println("Error en Actividad");
            }
        } else {
            super.sinSeleccion();
            System.out.println("ESTOY ACA Y NO SELECCIONE");
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

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getActividadSeleccionada() {
        return actividadSeleccionada;
    }

    public void setActividadSeleccionada(Actividad actividadSeleccionada) {
        this.actividadSeleccionada = actividadSeleccionada;
    }

    public Actividad getRespaldoActividad() {
        return respaldoActividad;
    }

    public void setRespaldoActividad(Actividad respaldoActividad) {
        this.respaldoActividad = respaldoActividad;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
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
