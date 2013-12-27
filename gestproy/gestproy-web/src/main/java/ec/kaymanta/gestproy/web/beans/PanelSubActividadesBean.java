/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.FechasActividad;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ActividadServicio;
import ec.kaymanta.gestproy.servicio.FechasActividadServicio;
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
public class PanelSubActividadesBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of PanelSubActividadesBean
     */
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    @EJB
    private ActividadServicio actividadServicio;
    @EJB
    private FechasActividadServicio fechasActividadServicio;
    private String ENTIDAD = "Panel de Sub-Actividades";
    private Usuario usrSesion;
    private Empleado emplSesion;
    private Proyecto proyecto;
    private String codProyecto;
    private String codActividad;
    //Variables SubActividad
    private List<Actividad> subActividades;
    private Actividad actividad;
    private Actividad subActividad;
    private Actividad subActividadSeleccionada;
    private Actividad respaldoSubActividad;
    //Variables Fechas Actividad
    private FechasActividad fechasActividad;
    private FechasActividad fechasActividadRespaldo;
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
            this.codActividad = parametros.get("codActividad");
            this.proyecto = this.proyectoServicio.findByID(Long.parseLong(codProyecto));
            this.actividad = this.actividadServicio.findByID(Long.parseLong(codActividad));
        }
        System.out.println("PROYECTO: " + proyecto.getNombreProyecto() + " ACTIVIDAD " + actividad.getNombreActividad());
        this.subActividades = this.actividadServicio.findByProyectoAndActividad(proyecto, actividad);
        createPieModel();
        this.subActividad = new Actividad();
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("Avance", actividad.getAvance().floatValue());
        pieModel.set("Restante", 100 - actividad.getAvance().floatValue());
    }
    
   
    public void nuevoSubActividad(ActionEvent evento) {
        super.crear();
        this.subActividad = new Actividad();
        this.fechasActividad = new FechasActividad();
    }

    public void verAuditoriaSubActividad(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoria();
            this.subActividad = new Actividad();
            this.subActividad = (Actividad) BeanUtils.cloneBean(this.subActividadSeleccionada);
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

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

    public void modificarSubActividad(ActionEvent evento) {
        this.subActividad = new Actividad();
        this.fechasActividad = new FechasActividad();
        try {
            this.subActividad = (Actividad) BeanUtils.cloneBean(this.subActividadSeleccionada);
            this.fechasActividad = this.fechasActividadServicio.findLastByActividad(subActividad);
            this.fechasActividadRespaldo = (FechasActividad) BeanUtils.cloneBean(this.fechasActividad);
            if (this.fechasActividad == null) {
                this.fechasActividad = new FechasActividad();
            }
        } catch (Exception e) {
        }
        super.modificar();
    }

    public void guardarSubActividad(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                //CREAR SUBACTIVIDAD
                this.subActividad.setActividad(actividad);
                this.subActividad.setProyecto(proyecto);
                this.subActividad.setCodProyecto(proyecto.getCodigo());
                this.subActividad.setSubactividad(actividad.getCodigo());
                this.subActividad.setAvance(BigDecimal.ZERO);
                this.subActividad.sethDiaEst(0L);
                this.subActividad.sethDiaReal(0L);
                this.subActividad.sethTrabEst(BigDecimal.ZERO);
                this.subActividad.sethTrabReal(BigDecimal.ZERO);
                this.subActividad.settTotalEst(0L);
                this.subActividad.settTotalReal(0L);
                this.subActividad.setUsrCreacion(usrSesion.getCodigo());
                this.subActividad.setFcreacion(new Date());
                System.out.println("SubActividad: " + subActividad.getNombreActividad());
                this.actividadServicio.crear(subActividad);
                this.subActividades.add(this.subActividad);
                //CREAR  FECHAS ACTIVIDAD
                this.fechasActividad.setActividad(subActividad);
                this.fechasActividad.getPk().setActividad(subActividad.getCodigo());
                this.fechasActividad.getPk().setCodigoFechasActividad(Long.parseLong(String.valueOf(fechasActividadServicio.obtener().size())) + 1);
                this.fechasActividad.setUsrCreacion(usrSesion.getCodigo());
                this.fechasActividad.setFfin(fechasActividad.getFestimada());
                this.fechasActividad.setFcreacion(new Date());
                System.out.println("FechasActividad: " + fechasActividad.getActividad().getNombreActividad());
                this.fechasActividadServicio.crear(fechasActividad);
                MensajesGenericos.infoCrear("Sub-Actividad", this.subActividad.getCodigo().toString().concat(" - ").concat(this.subActividad.getNombreActividad()), Boolean.TRUE);



            } else if (super.getEnEdicion()) {

                System.out.println("SubActividad: " + subActividad);
                int i = this.subActividades.indexOf(this.subActividad);

                this.subActividad.setUsrModificacion(usrSesion.getCodigo());
                this.subActividad.setFmodificacion(new Date());
                this.actividadServicio.actualizar(subActividad);
                this.subActividades.set(i, this.subActividad);

                MensajesGenericos.infoModificar("SubActividad", this.subActividad.getCodigo().toString().concat(" - ").concat(this.subActividad.getNombreActividad()), Boolean.TRUE);


                if (fechasActividad.getFinicio().compareTo(fechasActividadRespaldo.getFinicio()) == 0
                        && fechasActividad.getFestimada().compareTo(fechasActividadRespaldo.getFestimada()) == 0) {
                    System.out.println("NO GUARDO FECHAS YA QUE SON LAS MISMOS");
                } else {
                    FechasActividad fechasActividadNueva = new FechasActividad();
                    fechasActividadNueva.getPk().setActividad(subActividad.getCodigo());
                    System.out.println("CLAVE AUTOSUMA: " + Long.parseLong(String.valueOf(fechasActividadServicio.obtener().size())) + 1);

                    fechasActividadNueva.getPk().setCodigoFechasActividad(Long.parseLong(String.valueOf(fechasActividadServicio.obtener().size())) + 1);

                    fechasActividadNueva.setActividad(subActividad);
                    fechasActividadNueva.setFinicio(fechasActividad.getFinicio());
                    fechasActividadNueva.setFestimada(fechasActividad.getFestimada());
                    fechasActividadNueva.setFfin(fechasActividad.getFestimada());
                    fechasActividadNueva.setUsrCreacion(usrSesion.getCodigo());
                    fechasActividadNueva.setFcreacion(new Date());
                    this.fechasActividadServicio.crear(fechasActividadNueva);
                }

                actividadServicio.actualizar(actividad);
            }
            super.sinSeleccion();
            //CREAR ACTIVIDAD CON RESPONSABLE
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void filaSeleccionadaSubActividad(ActionEvent evento) {
        if (subActividadSeleccionada instanceof Actividad) {
            super.seleccionadoUno();
            try {
                this.subActividad = new Actividad();
                this.subActividad = (Actividad) BeanUtils.cloneBean(this.subActividadSeleccionada);
                System.out.println("ESTOY AQUI Y SI SELECCIONE, LA ACTIVIDAD ES: " + subActividad.getNombreActividad());
            } catch (Exception e) {
                System.out.println("Error en Sub-actividad");
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

    public String getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(String codActividad) {
        this.codActividad = codActividad;
    }

    public List<Actividad> getSubActividades() {
        return subActividades;
    }

    public void setSubActividades(List<Actividad> subActividades) {
        this.subActividades = subActividades;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getSubActividad() {
        return subActividad;
    }

    public void setSubActividad(Actividad subActividad) {
        this.subActividad = subActividad;
    }

    public Actividad getSubActividadSeleccionada() {
        return subActividadSeleccionada;
    }

    public void setSubActividadSeleccionada(Actividad subActividadSeleccionada) {
        this.subActividadSeleccionada = subActividadSeleccionada;
    }

    public Actividad getRespaldoSubActividad() {
        return respaldoSubActividad;
    }

    public void setRespaldoSubActividad(Actividad respaldoSubActividad) {
        this.respaldoSubActividad = respaldoSubActividad;
    }

    public FechasActividad getFechasActividad() {
        return fechasActividad;
    }

    public void setFechasActividad(FechasActividad fechasActividad) {
        this.fechasActividad = fechasActividad;
    }

    public FechasActividad getFechasActividadRespaldo() {
        return fechasActividadRespaldo;
    }

    public void setFechasActividadRespaldo(FechasActividad fechasActividadRespaldo) {
        this.fechasActividadRespaldo = fechasActividadRespaldo;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

 
    
}
