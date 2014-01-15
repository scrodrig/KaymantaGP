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
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class PanelActividadesBean extends BotonesBean implements Serializable {

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
            this.proyecto = this.proyectoServicio.findByID(Long.parseLong(codProyecto));
        }
        this.actividades = this.actividadServicio.findByProyecto(proyecto);
        createMeterGaugeChart();
        createMeterGaugeChartSalubridad();
        this.actividad = new Actividad();
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
        meterGaugeChartModel = new MeterGaugeChartModel(proyecto.getAvance(), intervals);
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
            meterGaugeChartModelSalud = new MeterGaugeChartModel(numeroDias(proyecto.getFestimada()), intervals);
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
                this.actividadServicio.crear(actividad);
                //--CREAR ACTIVIDAD
                this.actividades.add(this.actividad);
                MensajesGenericos.infoCrear("Actividad", this.actividad.getCodigo().toString().concat(" - ").concat(this.actividad.getNombreActividad()), Boolean.TRUE);
            } else if (super.getEnEdicion()) {
                int i = this.actividades.indexOf(this.actividad);
                this.actividad.setUsrModificacion(usrSesion.getCodigo());
                this.actividad.setFmodificacion(new Date());
                this.actividadServicio.actualizar(actividad);
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
            } catch (Exception e) {
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
