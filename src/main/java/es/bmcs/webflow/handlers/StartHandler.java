package es.bmcs.webflow.handlers;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.webflow.execution.RequestContext;

import es.bmcs.webflow.helper.StringUtiles;

@Component
public class StartHandler implements Serializable {

	private static final long serialVersionUID = 1L;

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

	/** Propiedades BPM */
	private Map<String,Map<String,String>> relAplicacionModulo = new HashMap<String, Map<String,String>>();

	private Map<String,String> tipologiasTicket;
	private Map<String,String> aplicaciones;
	private Map<String,String> modulosAplicacion;
	private Map<String,String> prioridades;
	private Map<String,String> departamentos;

	@PostConstruct
	public void init() {
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		//TODO Las aplicaciones, los modulos y los departamentos, deben salir de una BBDD
		
		tipologiasTicket = new HashMap<String, String>();
		tipologiasTicket.put(bundle.getString("texto.incidencia"), bundle.getString("texto.incidencia"));
		tipologiasTicket.put(bundle.getString("texto.nueva.funcionalidad"), bundle.getString("texto.nueva.funcionalidad"));

		aplicaciones = new HashMap<String, String>();
		aplicaciones.put("Netcom", "Netcom");

		modulosAplicacion = new HashMap<String, String>();
		modulosAplicacion.put("NetBase", "NetBase");
		relAplicacionModulo.put("Netcom", modulosAplicacion);

		prioridades = new HashMap<String, String>();
		prioridades.put(bundle.getString("texto.prioridad.alta"), bundle.getString("texto.prioridad.alta"));
		prioridades.put(bundle.getString("texto.prioridad.media"), bundle.getString("texto.prioridad.media"));
		prioridades.put(bundle.getString("texto.prioridad.baja"), bundle.getString("texto.prioridad.baja"));

		departamentos = new HashMap<String, String>();
		departamentos.put("IT", "IT");
	}

	public String pasarADepartamento() {
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		
		if (tipoTicket.equals("Incidencia"))
			return bundle.getString("return.false");

		return bundle.getString("return.true");
	}

	public String guardarNuevoTicket(RequestContext context) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		
		try {
			//123456 -> dXN1YXJpbzoxMjM0NTY=  (BASE64)

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("usuario", "123456"));

			Peticion peticion = new Peticion();
			
			peticion.setTipoTicket(getTipoTicket());
			peticion.setAplicacion(getAplicacion());
			peticion.setModulo(getModulo());
			peticion.setTitulo(getTitulo());
			peticion.setDescripcion(getDescripcion());
			peticion.setPrioridad(getPrioridad());
			peticion.setDepartamento(getDepartamento());
			peticion.setFechaTope(getFechaTope());
			peticion.setAprobadoDepto(getAprobadoDepto());
			peticion.setMotivoRechazoDepto(getMotivoRechazoDepto());
			peticion.setAprobadoHD(getAprobadoHD());
			peticion.setMotivoRechazoHD(getMotivoRechazoHD());
			peticion.setAprobadoIT(getAprobadoIT());
			peticion.setMotivoRechazoIT(getMotivoRechazoIT());
			//TODO crear enum para los tipos de estado
			peticion.setIdEstado(0); // CREADO
			peticion.setEnEjecucion(true);
			peticion.setFechaCreacion(new Date());
			peticion.setFechaRevisado(null);
			peticion.setFechaAprobadoNegocio(null);
			peticion.setFechaAprobadoIT(null);
			peticion.setFechaRechazado(null);
			peticion.setFechaResuelto(null);

			HttpEntity<Peticion> request = new HttpEntity<>(peticion);
			ResponseEntity<Peticion> response = restTemplate.exchange("http://localhost:8081/peticiones/", HttpMethod.POST, request, Peticion.class);
			
			Peticion ticketCreado = response.getBody();
			context.getMessageContext().addMessage(new MessageBuilder().info().defaultText(ticketCreado.toString()).build());

			return bundle.getString("return.true");

		} catch(Exception ex) {
			
			System.out.println(StringUtiles.generaMensajeError(bundle.getString("error.creacion.peticion"), ex));
			context.getMessageContext().addMessage(new MessageBuilder().error().defaultText(bundle.getString("error.creacion.peticion")).build());
			
			return bundle.getString("return.false");
		}

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

	public Map<String, String> getTipologiasTicket() {
		return tipologiasTicket;
	}

	public void setTipologiasTicket(Map<String, String> tipologiasTicket) {
		this.tipologiasTicket = tipologiasTicket;
	}

	public Map<String, String> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(Map<String, String> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public Map<String, String> getModulosAplicacion() {
		return modulosAplicacion;
	}

	public void setModulosAplicacion(Map<String, String> modulosAplicacion) {
		this.modulosAplicacion = modulosAplicacion;
	}

	public Map<String, String> getPrioridades() {
		return prioridades;
	}

	public void setPrioridades(Map<String, String> prioridades) {
		this.prioridades = prioridades;
	}

	public Map<String, String> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Map<String, String> departamentos) {
		this.departamentos = departamentos;
	}

	@Override
	public String toString() {
		return "Start [tipoTicket=" + tipoTicket + ", aplicacion=" + aplicacion + ", modulo=" + modulo + ", titulo="
				+ titulo + ", descripcion=" + descripcion + ", prioridad=" + prioridad + ", departamento="
				+ departamento + ", fechaTope=" + fechaTope + ", relAplicacionModulo=" + relAplicacionModulo
				+ ", tipologiasTicket=" + tipologiasTicket + ", aplicaciones=" + aplicaciones + ", modulosAplicacion="
				+ modulosAplicacion + ", prioridades=" + prioridades + ", departamentos=" + departamentos + "]";
	}

}