/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadEntregable;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.FechasActividad;
import ec.kaymanta.gestproy.modelo.InstitucionControl;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.TipoEntregable;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ActividadEntregableServicio;
import ec.kaymanta.gestproy.servicio.ActividadServicio;
import ec.kaymanta.gestproy.servicio.FechasActividadServicio;
import ec.kaymanta.gestproy.servicio.InstitucionControlServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.TipoEntregableServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.primefaces.model.chart.MeterGaugeChartModel;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class PanelEntregablesBean extends BotonesBean implements Serializable {

    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    @EJB
    private FechasActividadServicio fechasActividadServicio;
    @EJB
    private ActividadServicio actividadServicio;
    @EJB
    private ActividadEntregableServicio actividadEntregableServicio;
    @EJB
    private TipoEntregableServicio tipoEntregableServicio;
    @EJB
    private InstitucionControlServicio institucionControlServicio;
    private List<TipoEntregable> tiposEntregable;
    private List<ActividadEntregable> actividadEntregables;
    private List<InstitucionControl> institucionesControl;
    //Variables de Actividad Responsable
    private ActividadEntregable actividadEntregable;
    private ActividadEntregable actividadEntregableSeleccionado;
    private String tipoEntregable;
    private String ENTIDAD = "Panel de Entregables del Proyecto";
    private Usuario usrSesion;
    private Empleado emplSesion;
    private Proyecto proyecto;
    private Actividad actividad;
    private Actividad subActividad;
    private String codProyecto;
    private String codActividad;
    private String codSubActividad;
    private String instControl;
    private FechasActividad fechasActividad;
    //ELEMENTO DE VISTA
    private MeterGaugeChartModel meterGaugeChartModel;
    //ELEMENTO DE VISTA
    private MeterGaugeChartModel meterGaugeChartModelSalud;

    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        this.emplSesion = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Empleado");
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parametros = context.getExternalContext().getRequestParameterMap();
        if (this.proyecto == null) {
            this.codProyecto = parametros.get("codProyecto");
            this.codActividad = parametros.get("codActividad");
            this.codSubActividad = parametros.get("codSubActividad");
            this.proyecto = this.proyectoServicio.findByID(Long.parseLong(codProyecto));
            this.actividad = this.actividadServicio.findByID(Long.parseLong(codActividad));
            this.subActividad = this.actividadServicio.findByID(Long.parseLong(codSubActividad));
        }
        this.actividadEntregables = this.actividadEntregableServicio.findBySubActividad(subActividad);
        this.institucionesControl = this.institucionControlServicio.obtener();
        this.tiposEntregable = this.tipoEntregableServicio.obtener();
        this.fechasActividad = this.fechasActividadServicio.findLastByActividad(subActividad);
        createMeterGaugeChart();
        createMeterGaugeChartSalubridad();
        this.actividadEntregable = new ActividadEntregable();
    }

    private void createMeterGaugeChart() {
        meterGaugeChartModel = new MeterGaugeChartModel();
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(25);
                add(50);
                add(75);
                add(100);
            }
        };
        meterGaugeChartModel = new MeterGaugeChartModel(subActividad.getAvance(), intervals);
    }

    private void createMeterGaugeChartSalubridad() {
        List<Number> intervals = new ArrayList<Number>() {
            {
                add(-10);
                add(0);
                add(10);
                add(100);
            }
        };
        if (!this.proyecto.getEstado().equals("F")) {
            meterGaugeChartModelSalud = new MeterGaugeChartModel(numeroDias(fechasActividad.getFestimada()), intervals);
        } else {
            meterGaugeChartModelSalud = new MeterGaugeChartModel(0, intervals);
        }
    }

    public int numeroDias(Date d2) {
        try {
            Date d1 = new Date();
            if (d1.before(d2)) {
                DateTime dt1 = new DateTime(d1);
                DateTime dt2 = new DateTime(d2);
                Days daysBetween = Days.daysBetween(dt1, dt2);
                return -daysBetween.getDays();
            } else if (d1.after(d2)) {
                DateTime dt1 = new DateTime(d2);
                DateTime dt2 = new DateTime(d1);
                Days daysBetween = Days.daysBetween(dt1, dt2);
                return daysBetween.getDays();
            } else if (d1.compareTo(d2) == 0) {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public boolean holgura(int dias) {
        if (dias <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getUsrAuditoria(String usr) {
        if (usr == null || "".equals(usr)) {
            return "";
        } else {
            try {
                usuarioServicio.findByID(usr);
                return usuarioServicio.findByID(usr).getUsuario();
            } catch (NullPointerException e) {
                return "";
            }
        }
    }

    public void guardarEntregables(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.actividadEntregable.setActividad(subActividad);
                this.actividadEntregable.setCodTipoEntregable(Long.parseLong(tipoEntregable));
                this.actividadEntregable.setCodInstitucionControl(Long.parseLong(instControl));
                this.actividadEntregable.setTipoEntregable(this.tipoEntregableServicio.findByID(Long.parseLong(tipoEntregable)));
                this.actividadEntregable.setInstitucionControl(this.institucionControlServicio.findByID(Long.parseLong(instControl)));
                this.actividadEntregable.setEstado("I");
                this.actividadEntregable.getPk().setActividad(subActividad.getCodigo());
                this.actividadEntregable.getPk().setCodigoActividadEntregable(Long.parseLong(String.valueOf(this.actividadEntregableServicio.obtener().size())) + 1);
                this.actividadEntregable.setUsrCreacion(usrSesion.getCodigo());
                this.actividadEntregable.setFcreacion(new Date());
                this.actividadEntregableServicio.crear(actividadEntregable);
                this.actividadEntregables.add(actividadEntregable);

                MensajesGenericos.infoCrear("Entregable", this.actividadEntregable.getPk().toString().concat(" - ").concat(this.actividadEntregable.getNombreEntregable().toString()), Boolean.TRUE);
            } else if (super.getEnEdicion()) {
                int i = this.actividadEntregables.indexOf(this.actividadEntregable);
                this.actividadEntregable.setCodTipoEntregable(Long.parseLong(tipoEntregable));
                this.actividadEntregable.setCodInstitucionControl(Long.parseLong(instControl));
                this.actividadEntregable.setTipoEntregable(this.tipoEntregableServicio.findByID(Long.parseLong(tipoEntregable)));
                this.actividadEntregable.setInstitucionControl(this.institucionControlServicio.findByID(Long.parseLong(instControl)));
                this.actividadEntregable.setUsrModificacion(usrSesion.getCodigo());
                this.actividadEntregable.setFmodificacion(new Date());
                this.actividadEntregableServicio.actualizar(actividadEntregable);
                this.actividadEntregables.set(i, this.actividadEntregable);
                MensajesGenericos.infoModificar("Entregable", this.actividadEntregable.getPk().toString().concat(" - ").concat(this.actividadEntregable.getNombreEntregable().toString()), Boolean.TRUE);
            }
            super.sinSeleccion();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void nuevaActividadEntregable(ActionEvent evento) {
        super.crear();
        this.actividadEntregable = new ActividadEntregable();
    }

    public void modificarEntregable(ActionEvent evento) {
        this.actividadEntregable = new ActividadEntregable();
        try {
            this.actividadEntregable = (ActividadEntregable) BeanUtils.cloneBean(this.actividadEntregableSeleccionado);
            this.instControl = String.valueOf(actividadEntregable.getCodInstitucionControl());
            this.tipoEntregable = String.valueOf(actividadEntregable.getCodTipoEntregable());
        } catch (Exception e) {
        }
        super.modificar();
    }

    public String getEstado(String estado) {

        if (estado == null || "".equals(estado)) {
            return "";
        } else {
            if (estado.equals("I")) {
                return "Inicial";
            } else if (estado.equals("P")) {
                return "Procesando";
            } else if (estado.equals("F")) {
                return "Finalizado";
            } else if (estado.equals("R")) {
                return "Revisado";
            }
            return "Desconocido";

        }
    }

    public void verAuditoriaEntregables(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoria();
            this.actividadEntregable = new ActividadEntregable();
            this.actividadEntregable = (ActividadEntregable) BeanUtils.cloneBean(this.actividadEntregableSeleccionado);
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void filaSeleccionadaEntregable(ActionEvent evento) {
        if (actividadEntregableSeleccionado instanceof ActividadEntregable) {
            super.seleccionadoUno();
            try {
                this.actividadEntregable = new ActividadEntregable();
                this.actividadEntregable = (ActividadEntregable) BeanUtils.cloneBean(this.actividadEntregableSeleccionado);
            } catch (Exception e) {
            }
        } else {
            super.sinSeleccion();
        }
    }

    public ActividadServicio getActividadServicio() {
        return actividadServicio;
    }

    public void setActividadServicio(ActividadServicio actividadServicio) {
        this.actividadServicio = actividadServicio;
    }

    public List<TipoEntregable> getTiposEntregable() {
        return tiposEntregable;
    }

    public void setTiposEntregable(List<TipoEntregable> tiposEntregable) {
        this.tiposEntregable = tiposEntregable;
    }

    public List<ActividadEntregable> getActividadEntregables() {
        return actividadEntregables;
    }

    public void setActividadEntregables(List<ActividadEntregable> actividadEntregables) {
        this.actividadEntregables = actividadEntregables;
    }

    public List<InstitucionControl> getInstitucionesControl() {
        return institucionesControl;
    }

    public void setInstitucionesControl(List<InstitucionControl> institucionesControl) {
        this.institucionesControl = institucionesControl;
    }

    public ActividadEntregable getActividadEntregable() {
        return actividadEntregable;
    }

    public void setActividadEntregable(ActividadEntregable actividadEntregable) {
        this.actividadEntregable = actividadEntregable;
    }

    public ActividadEntregable getActividadEntregableSeleccionado() {
        return actividadEntregableSeleccionado;
    }

    public void setActividadEntregableSeleccionado(ActividadEntregable actividadEntregableSeleccionado) {
        this.actividadEntregableSeleccionado = actividadEntregableSeleccionado;
    }

    public String getTipoEntregable() {
        return tipoEntregable;
    }

    public void setTipoEntregable(String tipoEntregable) {
        this.tipoEntregable = tipoEntregable;
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

    public String getCodSubActividad() {
        return codSubActividad;
    }

    public void setCodSubActividad(String codSubActividad) {
        this.codSubActividad = codSubActividad;
    }

    public String getInstControl() {
        return instControl;
    }

    public void setInstControl(String instControl) {
        this.instControl = instControl;
    }

    public FechasActividad getFechasActividad() {
        return fechasActividad;
    }

    public void setFechasActividad(FechasActividad fechasActividad) {
        this.fechasActividad = fechasActividad;
    }

    public MeterGaugeChartModel getMeterGaugeChartModel() {
        return meterGaugeChartModel;
    }

    public void setMeterGaugeChartModel(MeterGaugeChartModel meterGaugeChartModel) {
        this.meterGaugeChartModel = meterGaugeChartModel;
    }

    public MeterGaugeChartModel getMeterGaugeChartModelSalud() {
        return meterGaugeChartModelSalud;
    }

    public void setMeterGaugeChartModelSalud(MeterGaugeChartModel meterGaugeChartModelSalud) {
        this.meterGaugeChartModelSalud = meterGaugeChartModelSalud;
    }
}
