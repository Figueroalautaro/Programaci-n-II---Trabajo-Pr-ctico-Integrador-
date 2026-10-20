package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class SmartGrids implements ISmartGrids {
	private Map<String, Planta> plantas= new HashMap<>();
	private Map<String, List<RegistroMonitoreo>> monitoreos= new HashMap<>();
	
	//constructor
	public SmartGrids() {}
	
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
				true,
				capacidadMaxima,
				fecha,
				fuentePrimaria,
				eficienciaPromedio,
				superficieCubierta,
				cantidadPaneles,
				horasRadiacionDiaria);
		plantas.put(nombre, plantaS);
	}
	
	//planta eolica
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
	            true,
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

}
