/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.LeccionesAprendidas;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.LeccionesAprendidasServicio;
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
public class PanelLeccionesBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of PanelLeccionesBean
     */
    /**
     * Creates a new instance of PanelExpectativasBean
     */
    @EJB
    private LeccionesAprendidasServicio leccionesAprendidasServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    private String ENTIDAD = "Panel de Base del Conocimiento";
    private Usuario usrSesion;
    private Empleado emplSesion;
    private LeccionesAprendidas leccionesAprendidas;
    private LeccionesAprendidas leccionesAprendidasSeleccionada;
    private LeccionesAprendidas respaldo;
    private List<LeccionesAprendidas> leccionesAprendidasList;
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
        this.leccionesAprendidasList = this.leccionesAprendidasServicio.findByProyecto(proyecto);
        createMeterGaugeChart();
        createMeterGaugeChartSalubridad();
        this.leccionesAprendidas = new LeccionesAprendidas();
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

    public String getEstado(String estado) {
        if (estado == null || "".equals(estado)) {
            return "";
        } else {
            if (estado.equals("P")) {
                return "Pendiente";
            } else if (estado.equals("C")) {
                return "Cumplida";
            }
            return "Desconocido";
        }
    }

    public void nuevaLeccion(ActionEvent evento) {
        super.crear();
        this.leccionesAprendidas = new LeccionesAprendidas();
    }

    public void modificarLeccion(ActionEvent evento) {
        this.leccionesAprendidas = new LeccionesAprendidas();
        try {
            this.leccionesAprendidas = (LeccionesAprendidas) BeanUtils.cloneBean(this.leccionesAprendidasSeleccionada);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.modificar();
    }

    public void guardarLeccion(ActionEvent evento) {

        try {
            if (super.getEnRegistro()) {
                leccionesAprendidas.getPk().setProyecto(proyecto.getCodigo());
                leccionesAprendidas.getPk().setCodigoLeccionesAprendidas(Long.parseLong(String.valueOf(this.leccionesAprendidasServicio.obtener().size())) + 1);
                leccionesAprendidas.setProyecto(proyecto);
                leccionesAprendidas.setUsrCreacion(usrSesion.getCodigo());
                leccionesAprendidas.setFcreacion(new Date());
                leccionesAprendidasServicio.crear(leccionesAprendidas);
                this.leccionesAprendidasList.add(leccionesAprendidas);
                MensajesGenericos.infoCrear("Lecciones Aprendidas", this.leccionesAprendidas.getPk().toString(), Boolean.TRUE);
            } else if (super.getEnEdicion()) {
                int i = this.leccionesAprendidasList.indexOf(this.leccionesAprendidas);
                leccionesAprendidas.setUsrModificacion(usrSesion.getCodigo());
                leccionesAprendidas.setFmodificacion(new Date());
                this.leccionesAprendidasList.set(i, this.leccionesAprendidas);
                leccionesAprendidasServicio.actualizar(leccionesAprendidas);
                MensajesGenericos.infoModificar("Lecciones Aprendidas", this.leccionesAprendidas.getPk().toString(), Boolean.TRUE);
            }
            super.sinSeleccion();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }

    public void filaSeleccionadaLeccion(ActionEvent evento) {
        if (leccionesAprendidasSeleccionada instanceof LeccionesAprendidas) {
            super.seleccionadoUno();
            try {
                this.leccionesAprendidas = new LeccionesAprendidas();
                this.leccionesAprendidas = (LeccionesAprendidas) BeanUtils.cloneBean(this.leccionesAprendidasSeleccionada);
            } catch (Exception e) {
            }
        } else {
            super.sinSeleccion();
        }
    }

    public void verAuditoriaLeccion(ActionEvent evento) throws IllegalAccessException {
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

    public LeccionesAprendidas getLeccionesAprendidas() {
        return leccionesAprendidas;
    }

    public void setLeccionesAprendidas(LeccionesAprendidas leccionesAprendidas) {
        this.leccionesAprendidas = leccionesAprendidas;
    }

    public LeccionesAprendidas getLeccionesAprendidasSeleccionada() {
        return leccionesAprendidasSeleccionada;
    }

    public void setLeccionesAprendidasSeleccionada(LeccionesAprendidas leccionesAprendidasSeleccionada) {
        this.leccionesAprendidasSeleccionada = leccionesAprendidasSeleccionada;
    }

    public LeccionesAprendidas getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(LeccionesAprendidas respaldo) {
        this.respaldo = respaldo;
    }

    public List<LeccionesAprendidas> getLeccionesAprendidasList() {
        return leccionesAprendidasList;
    }

    public void setLeccionesAprendidasList(List<LeccionesAprendidas> leccionesAprendidasList) {
        this.leccionesAprendidasList = leccionesAprendidasList;
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
