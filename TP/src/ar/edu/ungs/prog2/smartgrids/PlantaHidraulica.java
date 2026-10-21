package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaHidraulica extends PlantaRenovable{
	private int cantidadTurbinas;
	private double capacidadEmbalse;
	
	public PlantaHidraulica(String nombre, String direccion, double capadidadMaxima,
					LocalDateTime fechaInicioFuncion, String fuentePrimaria, double eficienciaPromedio, 
					int cantidadTurbinas, double capacidadEmbalse) {
		super(nombre, direccion, capadidadMaxima, fechaInicioFuncion, fuentePrimaria, eficienciaPromedio);
		this.cantidadTurbinas = cantidadTurbinas;
		this.capacidadEmbalse = capacidadEmbalse;
	}
	
	public double capacidadMinimaCuota() {
		return capacidadMaxima * eficienciaPromedio;
	}
	
	
	

}
