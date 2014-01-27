/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Riesgo;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.RiesgoServicio;
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
public class PanelRiesgosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of PanelRiesgosBean
     */
    @EJB
    private RiesgoServicio riesgoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    private String ENTIDAD = "Riesgos";
    private Usuario usrSesion;
    private Empleado emplSesion;
    private Riesgo riesgo;
    private Riesgo riesgoSeleccionado;
    private Riesgo respaldo;
    private List<Riesgo> riesgos;
    private Proyecto proyecto;
    private String codProyecto;
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
        this.riesgos = this.riesgoServicio.findByProyecto(proyecto);
        createMeterGaugeChart();
        createMeterGaugeChartSalubridad();
        this.riesgo = new Riesgo();
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

    public String getEstado(String estado) {
        if (estado == null || "".equals(estado)) {
            return "";
        } else {
            if (estado.equals("I")) {
                return "Pendiente";
            } else if (estado.equals("M")) {
                return "Mitigado";
            }
            return "Desconocido";
        }
    }

    public String getImpacto(String impacto) {
        if (impacto == null || "".equals(impacto)) {
            return "";
        } else {
            if (impacto.equals("A")) {
                return "Alto";
            } else if (impacto.equals("M")) {
                return "Medio";
            } else if (impacto.equals("B")) {
                return "Bajo";
            }
            return "";
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

    public void filaSeleccionadaRiesgo(ActionEvent evento) {
        if (riesgoSeleccionado instanceof Riesgo) {
            super.seleccionadoUno();
            try {
                this.riesgo = new Riesgo();
                this.riesgo = (Riesgo) BeanUtils.cloneBean(this.riesgoSeleccionado);
            } catch (Exception e) {
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void guardarRiesgo(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                riesgo.getPk().setProyecto(proyecto.getCodigo());
                riesgo.getPk().setCodigoRiesgo(Long.parseLong(String.valueOf(this.riesgoServicio.obtener().size())) + 1);
                riesgo.setProyecto(proyecto);
                riesgo.setUsrCreacion(usrSesion.getCodigo());
                riesgo.setFcreacion(new Date());
                riesgoServicio.crear(riesgo);
                this.riesgos.add(riesgo);
                MensajesGenericos.infoCrear("Riesgo", this.riesgo.getPk().toString(), Boolean.TRUE);
            } else if (super.getEnEdicion()) {
                int i = this.riesgos.indexOf(this.riesgo);
                riesgo.setUsrModificacion(usrSesion.getCodigo());
                riesgo.setFmodificacion(new Date());
                this.riesgos.set(i, this.riesgo);
                riesgoServicio.actualizar(riesgo);
                MensajesGenericos.infoModificar("Riesgo", this.riesgo.getPk().toString(), Boolean.TRUE);
            }
            super.sinSeleccion();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificarRiesgo(ActionEvent evento) {
        this.riesgo = new Riesgo();
        try {
            this.riesgo = (Riesgo) BeanUtils.cloneBean(this.riesgoSeleccionado);
        } catch (Exception e) {
        }
        super.modificar();
    }

    public void nuevaRiesgo(ActionEvent evento) {
        super.crear();
        this.riesgo = new Riesgo();
    }

    public void verAuditoriaRiesgo(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
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

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }

    public Riesgo getRiesgoSeleccionado() {
        return riesgoSeleccionado;
    }

    public void setRiesgoSeleccionado(Riesgo riesgoSeleccionado) {
        this.riesgoSeleccionado = riesgoSeleccionado;
    }

    public Riesgo getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Riesgo respaldo) {
        this.respaldo = respaldo;
    }

    public List<Riesgo> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(List<Riesgo> riesgos) {
        this.riesgos = riesgos;
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
