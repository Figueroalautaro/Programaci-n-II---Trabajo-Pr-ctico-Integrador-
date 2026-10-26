package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaHidraulica extends PlantaRenovable{
	private int cantidadTurbinas;
	private double capacidadEmbalse;
	
	public PlantaHidraulica(String nombre, String direccion,
	        double capacidadMaxima,
	        LocalDateTime fechaInicioFuncion,
	        String fuentePrimaria, double eficienciaPromedio,
	        int cantidadTurbinas, double capacidadEmbalse) {

	    super(nombre, direccion, capacidadMaxima,
	            fechaInicioFuncion, fuentePrimaria,
	            eficienciaPromedio);

	    if (cantidadTurbinas <= 0) {
	        throw new RuntimeException(
	                "La cantidad de turbinas debe ser mayor que cero");
	    }

	    if (capacidadEmbalse <= 0) {
	        throw new RuntimeException(
	                "La capacidad del embalse debe ser mayor que cero");
	    }

	    this.cantidadTurbinas = cantidadTurbinas;
	    this.capacidadEmbalse = capacidadEmbalse;
	}
	
	public double capacidadMinimaCuota() {
		return capacidadMaxima * eficienciaPromedio;
	}
	
	
	@Override
	public String toString() {
	    return super.toString()
	            + " [cantidadTurbinas=" + cantidadTurbinas
	            + ", capacidadEmbalse=" + capacidadEmbalse
	            + "]";
	}

}
