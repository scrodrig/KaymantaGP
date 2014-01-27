/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Interesado;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.EmpresaServicio;
import ec.kaymanta.gestproy.servicio.InteresadoServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
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
public class PanelInteresadosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of PanelInteresadosBean
     */
    @EJB
    private InteresadoServicio interesadoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    @EJB
    private EmpresaServicio empresaServicio;
    private String ENTIDAD = "Interesados del Proyecto";
    private Usuario usrSesion;
    private Empleado emplSesion;
    private String empresa;
    private Interesado interesado;
    private Interesado interesadoSeleccionado;
    private Interesado respaldo;
    private List<Interesado> interesados;
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
        this.interesados = this.interesadoServicio.obtenerByProyecto(proyecto.getEmpresa(), proyecto.getCodigo());
        createMeterGaugeChart();
        createMeterGaugeChartSalubridad();
        this.interesado = new Interesado();
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

    public void modificarInteresado(ActionEvent evento) {
        this.interesado = new Interesado();
        try {
            this.interesado = (Interesado) BeanUtils.cloneBean(this.interesadoSeleccionado);
            //Invariable Objetos de Auditoria            
            this.empresa = this.empresaServicio.findByID(interesado.getCodEmpresa()).getCodigo();
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void guardarInteresado(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.interesado.setUsrCreacion(usrSesion.getCodigo());
                this.interesado.setFcreacion(new Date());
                this.interesado.setProyecto(proyecto.getCodigo());
                this.interesado.setEmpresa(this.proyecto.getEmpresa());
                this.interesado.setCodEmpresa(this.proyecto.getEmpresa().getCodigo());
                this.interesadoServicio.crear(this.interesado);
                this.interesados.add(this.interesado);
                MensajesGenericos.infoCrear("Interesado", this.interesado.getCodigo().toString().concat(" - ").concat(this.interesado.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.interesados.indexOf(this.interesado);
                this.interesado.setUsrModificacion(usrSesion.getCodigo());
                this.interesado.setFmodificacion(new Date());
                this.interesadoServicio.actualizar(this.interesado);
                this.interesados.set(i, this.interesado);
                MensajesGenericos.infoModificar("Interesado", this.interesado.getCodigo().toString().concat(" - ").concat(this.interesado.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void filaSeleccionadaInteresado(ActionEvent evento) {
        if (interesadoSeleccionado instanceof Interesado) {
            super.seleccionadoUno();
            try {
                this.interesado = new Interesado();
                this.interesado = (Interesado) BeanUtils.cloneBean(this.interesadoSeleccionado);
            } catch (Exception e) {
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void nuevoInteresado(ActionEvent evento) {
        super.crear();
        this.interesado = new Interesado();
    }

    public void verAuditoriaInteresado(ActionEvent evento) throws IllegalAccessException {
        try {
            this.interesado = new Interesado();
            this.interesado = (Interesado) BeanUtils.cloneBean(this.interesadoSeleccionado);
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    public Interesado getInteresadoSeleccionado() {
        return interesadoSeleccionado;
    }

    public void setInteresadoSeleccionado(Interesado interesadoSeleccionado) {
        this.interesadoSeleccionado = interesadoSeleccionado;
    }

    public Interesado getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Interesado respaldo) {
        this.respaldo = respaldo;
    }

    public List<Interesado> getInteresados() {
        return interesados;
    }

    public void setInteresados(List<Interesado> interesados) {
        this.interesados = interesados;
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
