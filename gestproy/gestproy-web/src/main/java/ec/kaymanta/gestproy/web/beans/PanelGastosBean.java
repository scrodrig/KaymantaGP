/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Gasto;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.TipoGasto;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ActividadServicio;
import ec.kaymanta.gestproy.servicio.GastoServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.TipoGastoServicio;
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
public class PanelGastosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of PanelGastosBean
     */
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    @EJB
    private ActividadServicio actividadServicio;
    @EJB
    private GastoServicio gastoServicio;
    @EJB
    private TipoGastoServicio tipoGastoServicio;
    private String ENTIDAD = "Panel de Gastos del Proyecto";
    private Usuario usrSesion;
    private Empleado emplSesion;
    private Proyecto proyecto;
    private Actividad actividad;
    private Actividad subActividad;
    private String codProyecto;
    private String codActividad;
    private String codSubActividad;
    private String tipoGasto;
    private List<TipoGasto> tiposGasto;
    //Variables de Gastos
    private Gasto gasto;
    private Gasto gastoSeleccionado;
    private List<Gasto> gastos;
    
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
            this.codSubActividad = parametros.get("codSubActividad");
            this.proyecto = this.proyectoServicio.findByID(Long.parseLong(codProyecto));
            this.actividad = this.actividadServicio.findByID(Long.parseLong(codActividad));
            this.subActividad = this.actividadServicio.findByID(Long.parseLong(codSubActividad));
        }
        System.out.println("PROYECTO: " + proyecto.getNombreProyecto() + " ACTIVIDAD " + actividad.getNombreActividad());
        this.gastos = this.gastoServicio.findBySubActividad(subActividad);
        this.tiposGasto=this.tipoGastoServicio.obtener();
        createPieModel();
        this.gasto = new Gasto();
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("Avance", subActividad.getAvance().floatValue());
        pieModel.set("Restante", 100 - subActividad.getAvance().floatValue());
    }
    
    public void nuevoGasto(ActionEvent evento) {
        super.crear();
        this.gasto = new Gasto();
    }
    
    public void verAuditoriaGastos(ActionEvent evento) throws IllegalAccessException {
        try {
            super.verAuditoria();
            this.gasto = new Gasto();
            this.gasto = (Gasto) BeanUtils.cloneBean(this.gastoSeleccionado);
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }
    
    public void modificarGasto(ActionEvent evento) {
        this.gasto = new Gasto();
        try {
            this.gasto = (Gasto) BeanUtils.cloneBean(this.gastoSeleccionado);
            this.tipoGasto = String.valueOf(gasto.getTipoGasto().getCodigo());
        } catch (Exception e) {
        }
        super.modificar();
    }
    
    public void eliminar(ActionEvent evento) {
        System.out.println(this.gastoSeleccionado);
        this.gastoServicio.eliminar(this.gastoSeleccionado);
        this.gastos.remove(this.gastoSeleccionado);
        MensajesGenericos.infoEliminar("Gasto", this.gasto.getActividad().getNombreActividad().toString().concat(" - ").concat(this.gasto.getCodTipoGasto().toString()), Boolean.TRUE);
        super.sinSeleccion();
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
    
    public void guardarGasto(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.gasto.setActividad(subActividad);
                this.gasto.setTipoGasto(this.tipoGastoServicio.findByID(Long.parseLong(tipoGasto)));
                this.gasto.setCodTipoGasto(Long.parseLong(tipoGasto));
                this.gasto.getPk().setActividad(subActividad.getCodigo());
                this.gasto.getPk().setCodigoGasto(Long.parseLong(String.valueOf(this.gastoServicio.obtener().size())) + 1);
                this.gasto.setUsrCreacion(usrSesion.getCodigo());
                this.gasto.setFcreacion(new Date());
                this.gasto.setValorReal(BigDecimal.ZERO);

                this.gastoServicio.crear(gasto);
                this.gastos.add(gasto);
                MensajesGenericos.infoCrear("Gasto", this.gasto.getPk().toString().concat(" - ").concat(this.gasto.getValorPlan().toString()), Boolean.TRUE);

            } else if (super.getEnEdicion()) {
                int i = this.gastos.indexOf(this.gasto);
                this.gasto.setTipoGasto(this.tipoGastoServicio.findByID(Long.parseLong(tipoGasto)));
                this.gasto.setUsrModificacion(usrSesion.getCodigo());
                this.gasto.setFmodificacion(new Date());
                this.gastoServicio.actualizar(gasto);
                this.gastos.set(i, this.gasto);
                MensajesGenericos.infoModificar("Gasto", this.gasto.getPk().toString().concat(" - ").concat(this.gasto.getValorPlan().toString()), Boolean.TRUE);

            }
            super.sinSeleccion();

        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
        }

    }
    
     public void filaSeleccionadaGasto(ActionEvent evento) {
        if (gastoSeleccionado instanceof Gasto) {
            super.seleccionadoUno();
            try {
                this.gasto = new Gasto();
                this.gasto = (Gasto) BeanUtils.cloneBean(this.gastoSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE, EL GASTO ES: " + gasto.getPk());
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

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public List<TipoGasto> getTiposGasto() {
        return tiposGasto;
    }

    public void setTiposGasto(List<TipoGasto> tiposGasto) {
        this.tiposGasto = tiposGasto;
    }

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }

    public Gasto getGastoSeleccionado() {
        return gastoSeleccionado;
    }

    public void setGastoSeleccionado(Gasto gastoSeleccionado) {
        this.gastoSeleccionado = gastoSeleccionado;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
     
     
    
}