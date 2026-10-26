package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public abstract class PlantaRenovable extends Planta{
	protected String fuentePrimaria;
	protected double eficienciaPromedio;
	
	public PlantaRenovable(String nombre, String direccion,
	        double capacidadMaxima,
	        LocalDateTime fechaInicioFuncion,
	        String fuentePrimaria, double eficienciaPromedio) {

	    super(nombre, direccion, capacidadMaxima, fechaInicioFuncion);

	    if (fuentePrimaria == null || fuentePrimaria.equals("")) {
	        throw new RuntimeException(
	                "La fuente primaria no puede estar vacia");
	    }

	    if (eficienciaPromedio < 0 || eficienciaPromedio > 1) {
	        throw new RuntimeException(
	                "La eficiencia promedio debe estar entre 0 y 1");
	    }

	    this.fuentePrimaria = fuentePrimaria;
	    this.eficienciaPromedio = eficienciaPromedio;
	}
	
	 public String getFuentePrimaria() {
	        return fuentePrimaria;
	    }
	 
	 @Override
	 public String toString() {
	     return super.toString()
	             + " [fuentePrimaria=" + fuentePrimaria
	             + ", eficienciaPromedio=" + eficienciaPromedio
	             + "]";
	 }
	}
	

