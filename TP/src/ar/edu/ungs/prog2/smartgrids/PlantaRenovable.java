package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaRenovable extends Planta{
	private String fuentePrimaria;
	private double eficienciaPromedio;
	
	public PlantaRenovable(String nombre, String direccion, boolean estadoPlanta, double maxGeneracion,
			LocalDateTime fechaInicioFuncion, String fuentePrimaria, double eficienciaPromedio) {
		super(nombre, direccion, estadoPlanta, maxGeneracion, fechaInicioFuncion);
		this.fuentePrimaria = fuentePrimaria;
		this.eficienciaPromedio = eficienciaPromedio;
	}
	
	
	
	public String getFuentePrimaria() {
		return fuentePrimaria;
	}
	
	public double getEficienciaPromedio() {
		return eficienciaPromedio;
	}
	
}
