package es.bmcs.webflow.handlers;

import java.io.Serializable;
import java.util.Date;

public class Peticion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String tipoTicket;
	private String aplicacion;
	private String modulo;
	private String titulo;
	private String descripcion;
	private String prioridad;
	private String departamento;
	private Date fechaTope;
	private Boolean aprobadoDepto = Boolean.FALSE;
	private String motivoRechazoDepto;
	private Boolean aprobadoHD = Boolean.FALSE;
	private String motivoRechazoHD;
	private Boolean aprobadoIT = Boolean.FALSE;
	private String motivoRechazoIT;
	
	private Integer idEstado;
	private Boolean enEjecucion;
	private Date fechaCreacion;
	private Date fechaRevisado;
	private Date fechaAprobadoNegocio;
	private Date fechaAprobadoIT;
	private Date fechaRechazado;
	//TODO: private Date fechaDesarrollo;
	private Date fechaResuelto;
	
	public Peticion() {
	}

	public Peticion(Integer id, String tipoTicket, String aplicacion, String modulo, String titulo, String descripcion,
			String prioridad, String departamento, Date fechaTope, Boolean aprobadoDepto, String motivoRechazoDepto,
			Boolean aprobadoHD, String motivoRechazoHD, Boolean aprobadoIT, String motivoRechazoIT, Integer idEstado,
			Boolean enEjecucion, Date fechaCreacion, Date fechaRevisado, Date fechaAprobadoNegocio,
			Date fechaAprobadoIT, Date fechaRechazado, Date fechaResuelto) {
		super();
		this.id = id;
		this.tipoTicket = tipoTicket;
		this.aplicacion = aplicacion;
		this.modulo = modulo;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.departamento = departamento;
		this.fechaTope = fechaTope;
		this.aprobadoDepto = aprobadoDepto;
		this.motivoRechazoDepto = motivoRechazoDepto;
		this.aprobadoHD = aprobadoHD;
		this.motivoRechazoHD = motivoRechazoHD;
		this.aprobadoIT = aprobadoIT;
		this.motivoRechazoIT = motivoRechazoIT;
		this.idEstado = idEstado;
		this.enEjecucion = enEjecucion;
		this.fechaCreacion = fechaCreacion;
		this.fechaRevisado = fechaRevisado;
		this.fechaAprobadoNegocio = fechaAprobadoNegocio;
		this.fechaAprobadoIT = fechaAprobadoIT;
		this.fechaRechazado = fechaRechazado;
		this.fechaResuelto = fechaResuelto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoTicket() {
		return tipoTicket;
	}

	public void setTipoTicket(String tipoTicket) {
		this.tipoTicket = tipoTicket;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Date getFechaTope() {
		return fechaTope;
	}

	public void setFechaTope(Date fechaTope) {
		this.fechaTope = fechaTope;
	}

	public Boolean getAprobadoDepto() {
		return aprobadoDepto;
	}

	public void setAprobadoDepto(Boolean aprobadoDepto) {
		this.aprobadoDepto = aprobadoDepto;
	}

	public String getMotivoRechazoDepto() {
		return motivoRechazoDepto;
	}

	public void setMotivoRechazoDepto(String motivoRechazoDepto) {
		this.motivoRechazoDepto = motivoRechazoDepto;
	}

	public Boolean getAprobadoHD() {
		return aprobadoHD;
	}

	public void setAprobadoHD(Boolean aprobadoHD) {
		this.aprobadoHD = aprobadoHD;
	}

	public String getMotivoRechazoHD() {
		return motivoRechazoHD;
	}

	public void setMotivoRechazoHD(String motivoRechazoHD) {
		this.motivoRechazoHD = motivoRechazoHD;
	}

	public Boolean getAprobadoIT() {
		return aprobadoIT;
	}

	public void setAprobadoIT(Boolean aprobadoIT) {
		this.aprobadoIT = aprobadoIT;
	}

	public String getMotivoRechazoIT() {
		return motivoRechazoIT;
	}

	public void setMotivoRechazoIT(String motivoRechazoIT) {
		this.motivoRechazoIT = motivoRechazoIT;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Boolean getEnEjecucion() {
		return enEjecucion;
	}

	public void setEnEjecucion(Boolean enEjecucion) {
		this.enEjecucion = enEjecucion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRevisado() {
		return fechaRevisado;
	}

	public void setFechaRevisado(Date fechaRevisado) {
		this.fechaRevisado = fechaRevisado;
	}

	public Date getFechaAprobadoNegocio() {
		return fechaAprobadoNegocio;
	}

	public void setFechaAprobadoNegocio(Date fechaAprobadoNegocio) {
		this.fechaAprobadoNegocio = fechaAprobadoNegocio;
	}

	public Date getFechaAprobadoIT() {
		return fechaAprobadoIT;
	}

	public void setFechaAprobadoIT(Date fechaAprobadoIT) {
		this.fechaAprobadoIT = fechaAprobadoIT;
	}

	public Date getFechaRechazado() {
		return fechaRechazado;
	}

	public void setFechaRechazado(Date fechaRechazado) {
		this.fechaRechazado = fechaRechazado;
	}

	public Date getFechaResuelto() {
		return fechaResuelto;
	}

	public void setFechaResuelto(Date fechaResuelto) {
		this.fechaResuelto = fechaResuelto;
	}

	@Override
	public String toString() {
		return "Peticion [id=" + id + ", tipoTicket=" + tipoTicket + ", aplicacion=" + aplicacion + ", modulo=" + modulo
				+ ", titulo=" + titulo + ", descripcion=" + descripcion + ", prioridad=" + prioridad + ", departamento="
				+ departamento + ", fechaTope=" + fechaTope + ", aprobadoDepto=" + aprobadoDepto
				+ ", motivoRechazoDepto=" + motivoRechazoDepto + ", aprobadoHD=" + aprobadoHD + ", motivoRechazoHD="
				+ motivoRechazoHD + ", aprobadoIT=" + aprobadoIT + ", motivoRechazoIT=" + motivoRechazoIT
				+ ", idEstado=" + idEstado + ", enEjecucion=" + enEjecucion + ", fechaCreacion=" + fechaCreacion
				+ ", fechaRevisado=" + fechaRevisado + ", fechaAprobadoNegocio=" + fechaAprobadoNegocio
				+ ", fechaAprobadoIT=" + fechaAprobadoIT + ", fechaRechazado=" + fechaRechazado + ", fechaResuelto="
				+ fechaResuelto + "]";
	}
}
