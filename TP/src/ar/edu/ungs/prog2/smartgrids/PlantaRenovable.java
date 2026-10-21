package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public abstract class PlantaRenovable extends Planta{
	protected String fuentePrimaria;
	protected double eficienciaPromedio;
	
	public PlantaRenovable(String nombre, String direccion, double capadidadMaxima,
			LocalDateTime fechaInicioFuncion, String fuentePrimaria, double eficienciaPromedio) {
		super(nombre, direccion, capadidadMaxima, fechaInicioFuncion);
		this.fuentePrimaria = fuentePrimaria;
		this.eficienciaPromedio = eficienciaPromedio;
	}
	
	
	
	
	
	

	
}
