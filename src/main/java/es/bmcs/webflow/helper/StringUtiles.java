package es.bmcs.webflow.helper;

public class StringUtiles {

	static public String generaMensajeError(String mensaje, Exception ex) {
		StringBuffer error = new StringBuffer();
		error.append(mensaje);

		if (ex != null) {
			error.append(" - ");
			error.append(ex.getMessage());
		}
		
		System.out.println(error.toString());
		
		return error.toString();
	}
}
