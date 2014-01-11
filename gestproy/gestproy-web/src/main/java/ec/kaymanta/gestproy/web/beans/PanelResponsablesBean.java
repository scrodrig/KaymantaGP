/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadEmpleado;
import ec.kaymanta.gestproy.modelo.ActividadSegumiento;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.FechasActividad;
import ec.kaymanta.gestproy.modelo.FechasNoLaborables;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ActividadEmpleadoServicio;
import ec.kaymanta.gestproy.servicio.ActividadSeguimientoServicio;
import ec.kaymanta.gestproy.servicio.ActividadServicio;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
import ec.kaymanta.gestproy.servicio.FechasActividadServicio;
import ec.kaymanta.gestproy.servicio.FechasNoLaborablesServicio;
import ec.kaymanta.gestproy.servicio.ProyectoServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class PanelResponsablesBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of PanelResponsablesBean
     */
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private ProyectoServicio proyectoServicio;
    @EJB
    private ActividadServicio actividadServicio;
    @EJB
    private FechasNoLaborablesServicio fechasNoLaborablesServicio;
    @EJB
    private EmpleadoServicio empleadoServicio;
    @EJB
    private ActividadSeguimientoServicio actividadSeguimientoServicio;
    @EJB
    private FechasActividadServicio fechasActividadServicio;
    @EJB
    private ActividadEmpleadoServicio actividadEmpleadoServicio;
    private ActividadEmpleado actividadEmpleado;
    private ActividadEmpleado actividadEmpleadoSeleccionado;
    private List<ActividadEmpleado> actividadEmpleados;
    private List<FechasNoLaborables> fechasNoLaborables;
    private List<Empleado> empleados;
    private List<Actividad> actividades;
    private List<Actividad> subActividades;
    //Variables Fechas Actividad
    private ActividadSegumiento actividadSegumiento;
    private FechasActividad fechasActividad;
    private FechasActividad fechasActividadRespaldo;
    private String ENTIDAD = "Panel de Responsables";
    private Usuario usrSesion;
    private Empleado emplSesion;
    private Proyecto proyecto;
    private Actividad actividad;
    private Actividad subActividad;
    private String codProyecto;
    private String codActividad;
    private String codSubActividad;
    //ELEMENTO DE VISTA
    private MeterGaugeChartModel meterGaugeChartModel;
    //ELEMENTO DE VISTA
    private MeterGaugeChartModel meterGaugeChartModelSalud;
    private Long horasAnterior;

    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();

        System.out.println("PROYECTO: " + codProyecto);
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        System.out.println("USUARIO: " + usrSesion);
        this.emplSesion = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Empleado");
        this.fechasNoLaborables = this.fechasNoLaborablesServicio.obtener();
        this.empleados = this.empleadoServicio.obtener();
        this.fechasActividad = new FechasActividad();
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
        this.actividadEmpleados = this.actividadEmpleadoServicio.findBySubActividad(subActividad);
        this.fechasActividadRespaldo=this.fechasActividadServicio.findLastByActividad(subActividad);
        createMeterGaugeChart();
        createMeterGaugeChartSalubridad();
        this.actividadEmpleado = new ActividadEmpleado();
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

        meterGaugeChartModelSalud = new MeterGaugeChartModel(numeroDias(fechasActividadRespaldo.getFestimada()), intervals);
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

    public void nuevoResponsable(ActionEvent evento) {
        super.crear();
        this.actividadEmpleado = new ActividadEmpleado();
    }

    public void verAuditoriaResponsable(ActionEvent evento) throws IllegalAccessException {
        try {
            this.actividadEmpleado = new ActividadEmpleado();
            this.actividadEmpleado = (ActividadEmpleado) BeanUtils.cloneBean(this.actividadEmpleadoSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public String getEstado(String estado) {
        if (estado == null || "".equals(estado)) {
            return "";
        } else {
            System.out.println(estado);
            if (estado.equals("S")) {
                return "Si";
            } else if (estado.equals("N")) {
                return "No";
            }
            return "Desconocido";
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

    public void modificarResponsable(ActionEvent evento) {
        this.actividadEmpleado = new ActividadEmpleado();
        try {
            this.actividadEmpleado = (ActividadEmpleado) BeanUtils.cloneBean(this.actividadEmpleadoSeleccionado);
            this.horasAnterior = actividadEmpleado.gettTotalReal();
            this.actividadEmpleado.settTotalReal(0L);
            //Invariable Objetos de Auditoria
            this.actividadSegumiento = new ActividadSegumiento();
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void guardarResponsable(ActionEvent evento) {
        try {
            int days = 0;
            if (super.getEnRegistro()) {
                //RESPONSABLES
                this.actividadEmpleado.setActividad(subActividad);
                this.actividadEmpleado.getPk().setActividad(subActividad.getCodigo());
                this.actividadEmpleado.setEmpleado(this.empleadoServicio.findByID(this.actividadEmpleado.getPk().getResponsable()));
                this.actividadEmpleado.setUsrCreacion(usrSesion.getCodigo());
                this.actividadEmpleado.setFcreacion(new Date());
                //MEANWHILE                
                this.actividadEmpleado.setAvance(BigDecimal.ZERO);
                this.actividadEmpleado.sethDiaReal(0L);
                this.actividadEmpleado.sethTrabReal(BigDecimal.ZERO);
                this.actividadEmpleado.settTotalReal(0L);
                this.actividadEmpleado.sethTrabEst(BigDecimal.valueOf(actividadEmpleado.gethDiaEst()));
                days = numeroDias(actividadEmpleado.getFinicio(), actividadEmpleado.getFfin());
                System.out.println("HTRABEST" + actividadEmpleado.gethTrabEst() + "DIAS: " + days);
                this.actividadEmpleado.settTotalEst((days * actividadEmpleado.gethDiaEst()));
                this.actividadEmpleadoServicio.crear(actividadEmpleado);

                this.actividadEmpleados.add(this.actividadEmpleado);
                MensajesGenericos.infoCrear("Responsable", this.actividadEmpleado.getPk().toString(), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.actividadEmpleados.indexOf(this.actividadEmpleado);
                this.actividadEmpleado.setUsrModificacion(usrSesion.getCodigo());
                this.actividadEmpleado.setFmodificacion(new Date());
                //this.actividadEmpleado.sethTrabReal(BigDecimal.valueOf(this.actividadEmpleado.gettTotalReal()*days));
                //this.actividadEmpleado.sethTrabEst(BigDecimal.valueOf(actividadEmpleado.gethDiaEst()));
                System.out.println("HORAS ANTERIOR= " + horasAnterior);
                System.out.println("HORAS ANTERIOR suma= " + (horasAnterior + this.actividadEmpleado.gettTotalReal()));
                this.actividadEmpleado.settTotalReal(horasAnterior + this.actividadEmpleado.gettTotalReal());
                days = numeroDias(actividadEmpleado.getFinicio(), new Date());
                System.out.println("Valor de los dias" + days);
                this.actividadEmpleado.sethDiaReal(this.actividadEmpleado.gettTotalReal() / days);
                this.actividadEmpleado.sethTrabReal(BigDecimal.valueOf(this.actividadEmpleado.gettTotalReal()));
                this.actividadEmpleadoServicio.actualizar(actividadEmpleado);
                this.actividadEmpleados.set(i, this.actividadEmpleado);
                MensajesGenericos.infoModificar("Responsable", this.actividadEmpleado.getPk().toString(), Boolean.TRUE);
                super.sinSeleccion();

                //PARA INGRESAR SEGUIMIENTO
                //CADA VEZ QUE SE MODIFICA SE REGISTRA UNA ACTIVIDAD CORRESPONDIENTE A SER REALIZADA
                //POR DEFECTO EL SUPERVISOR SERA EL RESPONSABLE DEL PROYECTO
                
                this.actividadSegumiento.getPk().setCodigoActividadSeguimiento(Long.parseLong(String.valueOf(this.actividadSeguimientoServicio.obtener().size())) + 1);
                this.actividadSegumiento.getPk().setActividad(this.actividadEmpleado.getActividad().getCodigo());
                this.actividadSegumiento.setActividad(this.actividadEmpleado.getActividad());
                this.actividadSegumiento.setAvance(this.actividadEmpleado.getAvance());
                this.actividadSegumiento.setResponsable(actividadEmpleado.getEmpleado().getCodigo());
                this.actividadSegumiento.setEmpleado(proyecto.getEmpleado());
                this.actividadSegumiento.setEstado("P");
                this.actividadSegumiento.setUsrCreacion(usrSesion.getCodigo());
                this.actividadSegumiento.setFcreacion(new Date());
                this.actividadSeguimientoServicio.crear(actividadSegumiento);

                //SEGUIMIENTO

                this.actualizarSubActividad(subActividad);
                this.actualizarActividad(actividad);
                this.actualizarProyecto(proyecto);



            }
        } catch (Exception e) {
            e.printStackTrace();
            MensajesGenericos.errorGuardar();
            super.sinSeleccion();
        }

    }

    public void filaSeleccionadaResponsable(ActionEvent evento) {
        if (actividadEmpleadoSeleccionado instanceof ActividadEmpleado) {
            super.seleccionadoUno();
            try {
                this.actividadEmpleado = new ActividadEmpleado();
                this.actividadEmpleado = (ActividadEmpleado) BeanUtils.cloneBean(this.actividadEmpleadoSeleccionado);
                System.out.println("ESTOY AQUI Y SI SELECCIONE EL RESPONSABLE");
            } catch (Exception e) {
                System.out.println("Error en Responsable");
            }
        } else {
            super.sinSeleccion();
        }
    }

    public int numeroDias(Date d1, Date d2) {
        try {
            if (d1.before(d2)) {
                DateTime dt1 = new DateTime(d1);
                DateTime dt2 = new DateTime(d2);
                Days daysBetween = Days.daysBetween(dt1, dt2);


                return quitarNoLaborables(daysBetween.getDays() + 1, d1, d2);
            } else if (d1.after(d2)) {
                DateTime dt1 = new DateTime(d2);
                DateTime dt2 = new DateTime(d1);
                Days daysBetween = Days.daysBetween(dt1, dt2);
                return quitarNoLaborables(daysBetween.getDays() + 1, d2, d1);
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public int quitarNoLaborables(int dias, Date d1, Date d2) {
        int li = 0, lf = 0;

        for (int i = 0; i < this.fechasNoLaborables.size(); i++) {
            if (d1.before(this.fechasNoLaborables.get(i).getFecha()) || d1.equals(this.fechasNoLaborables.get(i).getFecha())) {
                li = i - 1;
                System.out.println(d1 + "COMPARADO CON" + this.fechasNoLaborables.get(i).getFecha());
                break;
            }
        }
        for (int i = 0; i < this.fechasNoLaborables.size(); i++) {
            if (d2.before(this.fechasNoLaborables.get(i).getFecha()) || d2.equals(this.fechasNoLaborables.get(i).getFecha())) {
                lf = i - 1;
                System.out.println(d2 + "COMPARADO CON" + this.fechasNoLaborables.get(i).getFecha());
                break;
            }

        }
        if (li != 0 && lf != 0) {
            System.out.println("li: " + li + "lf: " + lf);

        }


        return dias - (lf - li);
    }

    public void actualizarSubActividad(Actividad subActividad) {
        ///////////////////////////////////////PARA SUBACTIVIDADES////////////////////////////////////////////////////////
                /*AVANCE*/
        fechasActividad = this.fechasActividadServicio.findLastByActividad(subActividad);
        BigDecimal totalDias = BigDecimal.valueOf(numeroDias(fechasActividad.getFinicio(), fechasActividad.getFfin()));
        BigDecimal diasResp;
        System.out.println("NUMERO DIAS SUBACT: " + totalDias);
        BigDecimal suma = BigDecimal.ZERO;
        BigDecimal dividendo = BigDecimal.ZERO;
        BigDecimal resultado = BigDecimal.ZERO;
        for (int j = 0; j < actividadEmpleados.size(); j++) {
            diasResp = BigDecimal.valueOf(numeroDias(actividadEmpleados.get(j).getFinicio(), actividadEmpleados.get(j).getFfin()));
            suma = actividadEmpleados.get(j).getAvance();
            if (totalDias.compareTo(BigDecimal.ZERO) >= 0) {
                dividendo = diasResp.divide(totalDias, 2, RoundingMode.HALF_UP);
                System.out.println("NUMERO DIAS dividendo: " + dividendo);
                resultado = resultado.add(suma.multiply(dividendo));
            } else {
                resultado = BigDecimal.ZERO;
            }
        }

        resultado = resultado.divide(BigDecimal.ONE, 2, RoundingMode.UP);

        System.out.println("NUMERO DIAS SUBACT: " + totalDias);
        System.out.println("AVANCE SA" + resultado);
        System.out.println("SUBACTIVIDAD " + subActividad.getNombreActividad());
        if (resultado.compareTo(BigDecimal.valueOf(100)) <= 0) {
            subActividad.setAvance(resultado);
        } else {
            subActividad.setAvance(BigDecimal.valueOf(100));
        }
        /**/
        /*HORAS ESTIMADAS*/
        int hDiariasSA = 0;
        for (int j = 0; j < actividadEmpleados.size(); j++) {
            hDiariasSA += actividadEmpleados.get(j).gethDiaEst();
        }
        subActividad.sethDiaEst(Long.parseLong(String.valueOf(hDiariasSA / (actividadEmpleados.size()))));
        System.out.println("HORAS ESTIMADAS SA" + subActividad.gethDiaEst());
        //HORAS REAL//
        int hDiariasREAL = 0;
        for (int j = 0; j < actividadEmpleados.size(); j++) {
            hDiariasREAL += actividadEmpleados.get(j).gethDiaReal();
        }
        subActividad.sethDiaReal(Long.parseLong(String.valueOf(hDiariasREAL / (actividadEmpleados.size()))));
        System.out.println("HORAS REALES SA" + subActividad.gethDiaReal());
        //HORAS TRABAJO ESTIMADO
        BigDecimal hTrabEst = BigDecimal.ZERO;
        for (int j = 0; j < actividadEmpleados.size(); j++) {
            hTrabEst = hTrabEst.add(actividadEmpleados.get(j).gethTrabEst());
        }
        subActividad.sethTrabEst(hTrabEst);
        System.out.println("HORAS TRABAJO ESTIMADO SA" + hTrabEst);
        //HORAS TRABAJO REAL
        BigDecimal hTrabReal = BigDecimal.ZERO;
        for (int j = 0; j < actividadEmpleados.size(); j++) {
            hTrabReal = hTrabReal.add(actividadEmpleados.get(j).gethTrabReal());
        }
        subActividad.sethTrabReal(hTrabReal);
        System.out.println("HORAS TRABAJO REAL SA" + hTrabReal);

        //TIEMPO TOTAL ESTIMADO
        Long tTotEst = 0L;
        for (int j = 0; j < actividadEmpleados.size(); j++) {
            tTotEst += actividadEmpleados.get(j).gettTotalEst();
            System.out.println("DESPUES DE LA SUMA SA " + tTotEst);
        }
        subActividad.settTotalEst(tTotEst);
        System.out.println("TIEMPO TOTAL ESTIMADO SA" + tTotEst);


        //TIEMPO TOTAL REAL
        Long tTotReal = 0L;
        for (int j = 0; j < actividadEmpleados.size(); j++) {
            tTotReal += actividadEmpleados.get(j).gettTotalReal();
            System.out.println("DESPUES DE LA SUMA SA " + tTotReal);

        }
        subActividad.settTotalReal(tTotReal);
        System.out.println("TIEMPO TOTAL REAL SA" + tTotReal);

        this.actividadServicio.actualizar(subActividad);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void actualizarActividad(Actividad actividad) {
        ///////////////////////////////////////PARA SUBACTIVIDADES////////////////////////////////////////////////////////
                /*AVANCE*/
//        fechasActividad = this.fechasActividadServicio.findLastByActividad(subActividad);
//        Date dateInicioActividad=new Date();
//        Date dateFinActividad= new Date();        
        subActividades = actividadServicio.findByProyectoAndActividad(actividad.getProyecto(), actividad);
        int index = subActividades.size() - 1;
        FechasActividad fa1 = this.fechasActividadServicio.findLastByActividad(subActividades.get(0));
        FechasActividad fa2 = this.fechasActividadServicio.findLastByActividad(subActividades.get(index));

        BigDecimal totalDias = BigDecimal.valueOf(numeroDias(fa1.getFinicio(), fa2.getFfin()));
        BigDecimal diasResp;
        System.out.println("NUMERO DIAS ACT: " + totalDias);
        BigDecimal suma = BigDecimal.ZERO;
        BigDecimal dividendo = BigDecimal.ZERO;
        BigDecimal resultado = BigDecimal.ZERO;
        for (int j = 0; j < subActividades.size(); j++) {
            fechasActividad = this.fechasActividadServicio.findLastByActividad(subActividades.get(j));
            diasResp = BigDecimal.valueOf(numeroDias(fechasActividad.getFinicio(), fechasActividad.getFfin()));
            suma = subActividades.get(j).getAvance();
            if (totalDias.compareTo(BigDecimal.ZERO) >= 0) {

                dividendo = diasResp.divide(totalDias, 2, RoundingMode.HALF_UP);
                System.out.println("NUMERO DIAS dividendo: " + dividendo);
                resultado = resultado.add(suma.multiply(dividendo));
            } else {
                resultado = BigDecimal.ZERO;
            }


        }

        resultado = resultado.divide(BigDecimal.ONE, 2, RoundingMode.UP);


        System.out.println("NUMERO DIAS ACT: " + totalDias);
        System.out.println("AVANCE A" + resultado.multiply(BigDecimal.valueOf(100)));
        System.out.println("ACTIVIDAD " + actividad.getNombreActividad());

        if (resultado.compareTo(BigDecimal.valueOf(100)) <= 0) {
            actividad.setAvance(resultado);
        } else {
            actividad.setAvance(BigDecimal.valueOf(100));
        }
        /**/
        /*HORAS ESTIMADAS*/
        int hDiariasSA = 0;
        for (int j = 0; j < subActividades.size(); j++) {
            hDiariasSA += subActividades.get(j).gethDiaEst();
        }
        actividad.sethDiaEst(Long.parseLong(String.valueOf(hDiariasSA / (subActividades.size()))));
        System.out.println("HORAS ESTIMADAS ACTIVIDAD" + actividad.gethDiaEst());
        //HORAS REAL//
        int hDiariasREAL = 0;
        for (int j = 0; j < subActividades.size(); j++) {
            hDiariasREAL += subActividades.get(j).gethDiaReal();
        }
        actividad.sethDiaReal(Long.parseLong(String.valueOf(hDiariasREAL / (subActividades.size()))));
        System.out.println("HORAS REALES ACTIVIDAD" + actividad.gethDiaReal());
        //HORAS TRABAJO ESTIMADO
        BigDecimal hTrabEst = BigDecimal.ZERO;
        for (int j = 0; j < subActividades.size(); j++) {
            hTrabEst = hTrabEst.add(subActividades.get(j).gethTrabEst());
            System.out.println("DESPUES DE LA SUMA Act " + hTrabEst);

        }
        actividad.sethTrabEst(hTrabEst);
        System.out.println("HORAS TRABAJO ESTIMADO ACTIVIDAD" + hTrabEst);
        //HORAS TRABAJO REAL
        BigDecimal hTrabReal = BigDecimal.ZERO;
        for (int j = 0; j < subActividades.size(); j++) {
            hTrabReal = hTrabReal.add(subActividades.get(j).gethTrabReal());
            System.out.println("DESPUES DE LA SUMA Act " + hTrabReal);

        }
        actividad.sethTrabReal(hTrabReal);
        System.out.println("HORAS TRABAJO REAL ACTIVIDAD" + hTrabReal);

        //TIEMPO TOTAL ESTIMADO
        Long tTotEst = 0L;
        for (int j = 0; j < subActividades.size(); j++) {
            tTotEst += subActividades.get(j).gettTotalEst();
        }
        actividad.settTotalEst(tTotEst);
        System.out.println("TIEMPO TOTAL ESTIMADO ACTIVIDAD" + tTotEst);


        //TIEMPO TOTAL REAL
        Long tTotReal = 0L;
        for (int j = 0; j < subActividades.size(); j++) {
            tTotReal += subActividades.get(j).gettTotalReal();
        }
        actividad.settTotalReal(tTotReal);
        System.out.println("TIEMPO TOTAL REAL ACTIVIDAD" + tTotReal);

        this.actividadServicio.actualizar(actividad);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void actualizarProyecto(Proyecto proyecto) {
        ///////////////////////////////////////PARA SUBACTIVIDADES////////////////////////////////////////////////////////
                /*AVANCE*/

        actividades = actividadServicio.findByProyecto(proyecto);
        int index = 0;
        List<Actividad> subAct = new ArrayList<Actividad>();
        BigDecimal diasResp;
        BigDecimal totalDias = BigDecimal.ZERO;
        BigDecimal suma = BigDecimal.ZERO;
        BigDecimal dividendo = BigDecimal.ZERO;
        BigDecimal resultado = BigDecimal.ZERO;
        diasResp = BigDecimal.ZERO;
        totalDias = BigDecimal.valueOf(numeroDias(proyecto.getFinicio(), proyecto.getFfin()));
        System.out.println("DIAS TOTAL PROYECTO: " + diasResp);
        for (int j = 0; j < actividades.size(); j++) {

            subAct = this.actividadServicio.findByProyectoAndActividad(proyecto, actividades.get(j));
            for (int k = 0; k < subAct.size(); k++) {
                index = subAct.size() - 1;
                FechasActividad f1 = this.fechasActividadServicio.findLastByActividad(subAct.get(0));
                FechasActividad f2 = this.fechasActividadServicio.findLastByActividad(subAct.get(index));
                diasResp = diasResp.add(BigDecimal.valueOf(numeroDias(f1.getFinicio(), f2.getFfin())));
                System.out.println("dias Actividades PROYECTO: " + diasResp);
            }

            suma = actividades.get(j).getAvance();
            if (totalDias.compareTo(BigDecimal.ZERO) >= 0) {
                dividendo = diasResp.divide(totalDias, 2, RoundingMode.HALF_UP);
                System.out.println("NUMERO DIAS dividendo PROYECTO: " + dividendo);
                resultado = resultado.add(suma.multiply(dividendo));
            } else {
                resultado = BigDecimal.ZERO;
            }

        }


        resultado = resultado.divide(BigDecimal.ONE, 2, RoundingMode.UP);

        System.out.println("NUMERO DIAS PROY: " + totalDias);
        System.out.println("AVANCE P" + resultado);
        System.out.println("ACTIVIDAD PROYECTO" + actividad.getNombreActividad());

        if (resultado.compareTo(BigDecimal.valueOf(100)) <= 0) {
            proyecto.setAvance(resultado);
        } else {
            proyecto.setAvance(BigDecimal.valueOf(100));
        }
        /**/
        /*HORAS ESTIMADAS*/
        int hDiariasSA = 0;
        for (int j = 0; j < actividades.size(); j++) {
            hDiariasSA += actividades.get(j).gethDiaEst();
        }
        proyecto.sethDiaEst(Long.parseLong(String.valueOf(hDiariasSA / (actividades.size()))));
        System.out.println("HORAS ESTIMADAS PROYECTO" + proyecto.gethDiaEst());
        //HORAS REAL//
        int hDiariasREAL = 0;
        for (int j = 0; j < actividades.size(); j++) {
            hDiariasREAL += actividades.get(j).gethDiaReal();
        }
        proyecto.sethDiaReal(Long.parseLong(String.valueOf(hDiariasREAL / (actividades.size()))));
        System.out.println("HORAS REALES PROYECTO" + proyecto.gethDiaReal());
        //HORAS TRABAJO ESTIMADO
        BigDecimal hTrabEst = BigDecimal.ZERO;
        for (int j = 0; j < actividades.size(); j++) {
            hTrabEst = hTrabEst.add(actividades.get(j).gethTrabEst());
        }
        proyecto.sethTrabEst(hTrabEst);
        System.out.println("HORAS TRABAJO ESTIMADO PROYECTO" + hTrabEst);
        //HORAS TRABAJO REAL
        BigDecimal hTrabReal = BigDecimal.ZERO;
        for (int j = 0; j < actividades.size(); j++) {
            hTrabReal = hTrabReal.add(actividades.get(j).gethTrabReal());
        }
        proyecto.sethTrabReal(hTrabReal);
        System.out.println("HORAS TRABAJO REAL PROYECTO" + hTrabReal);

        //TIEMPO TOTAL ESTIMADO
        Long tTotEst = 0L;
        for (int j = 0; j < actividades.size(); j++) {
            tTotEst += actividades.get(j).gettTotalEst();
            System.out.println("DESPUES DE LA SUMA PROY tTotEst" + tTotEst);

        }
        proyecto.settTotalEst(tTotEst);
        System.out.println("TIEMPO TOTAL ESTIMADO PROYECTO" + tTotEst);


        //TIEMPO TOTAL REAL
        Long tTotReal = 0L;
        for (int j = 0; j < actividades.size(); j++) {
            tTotReal += actividades.get(j).gettTotalReal();
            System.out.println("DESPUES DE LA SUMA PROY tTotEst" + tTotReal);

        }
        proyecto.settTotalReal(tTotReal);
        System.out.println("TIEMPO TOTAL REAL PROYECTO" + tTotReal);

        this.proyectoServicio.actualizar(proyecto);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public ActividadEmpleado getActividadEmpleado() {
        return actividadEmpleado;
    }

    public void setActividadEmpleado(ActividadEmpleado actividadEmpleado) {
        this.actividadEmpleado = actividadEmpleado;
    }

    public ActividadEmpleado getActividadEmpleadoSeleccionado() {
        return actividadEmpleadoSeleccionado;
    }

    public void setActividadEmpleadoSeleccionado(ActividadEmpleado actividadEmpleadoSeleccionado) {
        this.actividadEmpleadoSeleccionado = actividadEmpleadoSeleccionado;
    }

    public List<ActividadEmpleado> getActividadEmpleados() {
        return actividadEmpleados;
    }

    public void setActividadEmpleados(List<ActividadEmpleado> actividadEmpleados) {
        this.actividadEmpleados = actividadEmpleados;
    }

    public List<FechasNoLaborables> getFechasNoLaborables() {
        return fechasNoLaborables;
    }

    public void setFechasNoLaborables(List<FechasNoLaborables> fechasNoLaborables) {
        this.fechasNoLaborables = fechasNoLaborables;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<Actividad> getSubActividades() {
        return subActividades;
    }

    public void setSubActividades(List<Actividad> subActividades) {
        this.subActividades = subActividades;
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

   
    public Long getHorasAnterior() {
        return horasAnterior;
    }

    public void setHorasAnterior(Long horasAnterior) {
        this.horasAnterior = horasAnterior;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public ActividadSegumiento getActividadSegumiento() {
        return actividadSegumiento;
    }

    public void setActividadSegumiento(ActividadSegumiento actividadSegumiento) {
        this.actividadSegumiento = actividadSegumiento;
    }
    
    
}
