package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaSolar extends PlantaRenovable{
	private double superficieCubierta; 
	private int cantidadPaneles;
	private double horasRadiacionDiaria;
	
	//constructor
	public PlantaSolar(String nombre, String direccion, double capadidadMaxima,
			LocalDateTime fechaInicioFuncion, String fuentePrimaria, double eficienciaPromedio, 
			double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		super(nombre, direccion, capadidadMaxima, fechaInicioFuncion, fuentePrimaria, eficienciaPromedio);
		this.superficieCubierta = superficieCubierta;
		this.cantidadPaneles = cantidadPaneles;
		this.horasRadiacionDiaria = horasRadiacionDiaria;
	}
	
	//@Override
	public double capacidadMinimaCuota() {
		return capacidadMaxima * eficienciaPromedio * horasRadiacionDiaria / 23;
	}
	
	
	
}
