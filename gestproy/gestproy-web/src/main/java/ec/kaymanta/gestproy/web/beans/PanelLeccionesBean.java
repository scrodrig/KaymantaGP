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
        this.leccionesAprendidasList = this.leccionesAprendidasServicio.findByProyecto(proyecto);
        createPieModel();
        this.leccionesAprendidas = new LeccionesAprendidas();
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

    public String getEstado(String estado) {
        if (estado == null || "".equals(estado)) {
            return "";
        } else {
            System.out.println(estado);
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
            System.out.println("LECCIONES APRENDIDAS " + leccionesAprendidas.getProblema());

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
                System.out.println("ESTOY AQUI Y SI SELECCIONE LA LECCION APRENDIDA");
            } catch (Exception e) {
                System.out.println("Error en Lección");
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

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
    
}
