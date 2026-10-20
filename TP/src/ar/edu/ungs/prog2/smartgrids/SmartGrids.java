package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SmartGrids implements ISmartGrids {
	private Map<String, Planta> plantas= new HashMap<>();
	private Map<String, List<RegistroMonitoreo>> monitoreos= new HashMap<>();
	
	//constructor
	public SmartGrids() {}
	
	
	// creacion de todas las plantas  //////////////////////
	@Override
	public void registrarPlantaSolar(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		if ( plantas.containsKey(nombre)) {
			throw new RuntimeException("ya existe una planta con ese nombre");
		}
		LocalDateTime fecha= LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento);
		
		//2 creamo la planta
		PlantaSolar plantaS = new PlantaSolar(
				nombre,
				direccion,
				capacidadMaxima,
				fecha,
				fuentePrimaria,
				eficienciaPromedio,
				superficieCubierta,
				cantidadPaneles,
				horasRadiacionDiaria);
		plantas.put(nombre, plantaS);
	}
	
	@Override
	public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima,
	        String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
	        int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {

	    if (plantas.containsKey(nombre)) {
	        throw new RuntimeException("Nombre duplicado");
	    }

	    LocalDateTime fecha = LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento);

	    PlantaEolica plantaE = new PlantaEolica(
	            nombre, 
	            direccion, 
	            capacidadMaxima,
	            fecha,
	            fuentePrimaria, 
	            eficienciaPromedio,
	            cantidadAerogeneradores, 
	            alturaTorres, 
	            velocidadMinimaViento
	    );

	    plantas.put(nombre, plantaE);
	}
	
	@Override
	public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima,
	        String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, 
	        int cantidadTurbinas, double capacidadEmbalse) {

	    if (plantas.containsKey(nombre)) {
	        throw new RuntimeException("Nombre duplicado");
	    }

	    LocalDateTime fecha = LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento);

	    PlantaHidraulica plantaH = new PlantaHidraulica(
	            nombre, 
	            direccion, 
	            capacidadMaxima,
	            fecha,
	            fuentePrimaria, 
	            eficienciaPromedio,
	            cantidadTurbinas,
	            capacidadEmbalse
	    );

	    plantas.put(nombre, plantaH);
	}
	
	@Override
	public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima,
	        		String fechaHoraPuestaEnFuncionamiento, String tipoCombustible ,
	        		double objetivoEmisionesCo2PorHora, double capacidadAlmacenamientoCombustible) {

	    if (plantas.containsKey(nombre)) {
	        throw new RuntimeException("Nombre duplicado");
	    }

	    LocalDateTime fecha = LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento);

	    PlantaTermica plantaT = new PlantaTermica(
	            nombre, 
	            direccion, 
	            capacidadMaxima,
	            fecha,

	            tipoCombustible,
	            objetivoEmisionesCo2PorHora,
	            capacidadAlmacenamientoCombustible
	    );

	    plantas.put(nombre, plantaT);
	}
	
	////////////////////////////////////////////////////////////
	
	@Override
	public List<String> consultarPlantas(){
		List<String> consultaP = new ArrayList<>();
		
		for (Planta planta : plantas.values()) {
			String estadoP;
			
			if (planta.estaActiva()) 
				estadoP= "ACTIVA"; 
			else 
				estadoP = "INACTIVA";
			String detalle = planta.getNombre() + "(" + estadoP +"): " + planta.getFechaInicioFuncion();
			consultaP.add(detalle);
		}
		return consultaP;
	}

}
