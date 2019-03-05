package es.bmcs.webflow.handlers;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.webflow.execution.RequestContext;

@Component
public class IndexHandler implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Peticion> peticiones = new ArrayList<Peticion>();

	public ArrayList<Peticion> getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(ArrayList<Peticion> peticiones) {
		this.peticiones = peticiones;
	}

	public void loadData(RequestContext context) {

		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("usuario", "123456"));

			ResponseEntity<List<Peticion>> response = restTemplate.exchange("http://localhost:8081/peticiones", HttpMethod.GET, null, new ParameterizedTypeReference<List<Peticion>>(){});
			this.peticiones = (ArrayList<Peticion>) response.getBody();
		} catch (Exception e) {
			
			ResourceBundle bundle = ResourceBundle.getBundle("messages");
			
			context.getMessageContext().addMessage(new MessageBuilder().error().defaultText(bundle.getString("error.datos.peticion")).build());
			System.out.println(bundle.getString("error.datos.peticion"));
		}
	}
	
	//TODO Esto deberia salir de la base de datos
	public String getNombreEstado(Peticion peticion) {
		if (peticion.getIdEstado() == 0)
			return "CREADO";
		
		if (peticion.getIdEstado() == 1)
			return "REVISADO";
		
		if (peticion.getIdEstado() == 2)
			return "APROBADO";
		
		if (peticion.getIdEstado() == 3)
			return "APROBADO NEGOCIO";
		
		if (peticion.getIdEstado() == 4)
			return "RECHAZADO";
		
		if (peticion.getIdEstado() == 8)
			return "RESUELTO";
		
		return "OTRO";
	}
	
	public String getUltimaFecha(Peticion peticion) {
		List<Date> fechas = new ArrayList<Date>();
		
		if (peticion.getFechaCreacion() != null)
			fechas.add(peticion.getFechaCreacion());
		
		if (peticion.getFechaRevisado() != null)
			fechas.add(peticion.getFechaRevisado());
		if (peticion.getFechaAprobadoNegocio() != null)
			fechas.add(peticion.getFechaAprobadoNegocio());
		if (peticion.getFechaAprobadoIT() != null)
			fechas.add(peticion.getFechaAprobadoIT());
		if (peticion.getFechaRechazado() != null)
			fechas.add(peticion.getFechaRechazado());
		if (peticion.getFechaResuelto() != null)
			fechas.add(peticion.getFechaResuelto());
		
        Collections.sort(fechas, new Comparator<Date>(){
 
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });
		
		if (fechas.size() > 0) {
			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			return df.format(fechas.get(fechas.size() - 1));
		}
		
		return "";
	}

    //TODO Codificar correctamente esta cosa tan loca
    public String getPieChart() {
    	StringBuffer codigo = new StringBuffer();
    	
    	// Themes begin
    	codigo.append("am4core.useTheme(am4themes_animated);");
		// Themes end

		// Create chart instance
    	codigo.append("var chart = am4core.create(\"chartdiv\", am4charts.PieChart);");

		// Add and configure Series
    	codigo.append("var pieSeries = chart.series.push(new am4charts.PieSeries());");
    	codigo.append("pieSeries.dataFields.value = \"litres\";");
    	codigo.append("pieSeries.dataFields.category = \"country\";");

		// Let's cut a hole in our Pie chart the size of 30% the radius
    	codigo.append("chart.innerRadius = am4core.percent(30);");

		// Put a thick white border around each Slice
    	codigo.append("pieSeries.slices.template.stroke = am4core.color(\"#fff\");");
    	codigo.append("pieSeries.slices.template.strokeWidth = 2;");
    	codigo.append("pieSeries.slices.template.strokeOpacity = 1;");
    	codigo.append("pieSeries.slices.template");
		// change the cursor on hover to make it apparent the object can be interacted with
    	codigo.append(".cursorOverStyle = [ {\"property\" : \"cursor\", \"value\" : \"pointer\"} ];");

    	codigo.append("pieSeries.alignLabels = false;");
    	codigo.append("pieSeries.labels.template.bent = true;");
    	codigo.append("pieSeries.labels.template.radius = 3;");
    	codigo.append("pieSeries.labels.template.padding(0, 0, 0, 0);");

    	codigo.append("pieSeries.ticks.template.disabled = true;");

		// Create a base filter effect (as if it's not there) for the hover to return to
    	codigo.append("var shadow = pieSeries.slices.template.filters.push(new am4core.DropShadowFilter);");
    	codigo.append("shadow.opacity = 0;");

		// Create hover state
    	codigo.append("var hoverState = pieSeries.slices.template.states.getKey(\"hover\");"); 
		// normally we have to create the hover state, in this case it already exists

		// Slightly shift the shadow and make it more prominent on hover
    	codigo.append("var hoverShadow = hoverState.filters.push(new am4core.DropShadowFilter);");
    	codigo.append("hoverShadow.opacity = 0.7;");
    	codigo.append("hoverShadow.blur = 5;");

		// Add a legend
    	codigo.append("chart.legend = new am4charts.Legend();");

    	codigo.append("chart.data = [ {");
    	codigo.append("	\"country\" : \"Spain\",");
    	codigo.append("	\"litres\" : 501.9");
    	codigo.append("}, {");
    	codigo.append("	\"country\" : \"Germany\",");
    	codigo.append("	\"litres\" : 165.8");
    	codigo.append("}, {");
    	codigo.append("	\"country\" : \"Australia\",");
    	codigo.append("	\"litres\" : 139.9");
    	codigo.append("}, {");
    	codigo.append("	\"country\" : \"Austria\",");
    	codigo.append("	\"litres\" : 128.3");
    	codigo.append("}, {");
    	codigo.append("	\"country\" : \"UK\",");
    	codigo.append("	\"litres\" : 99");
    	codigo.append("}, {");
    	codigo.append("	\"country\" : \"Belgium\",");
    	codigo.append("	\"litres\" : 60");
    	codigo.append("} ];");
    	
    	return codigo.toString();
    }
}
