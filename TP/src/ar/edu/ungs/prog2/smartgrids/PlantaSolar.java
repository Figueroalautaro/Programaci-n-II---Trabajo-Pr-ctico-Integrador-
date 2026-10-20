package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaSolar extends PlantaRenovable{
	private double superficieCubierta; 
	private int cantidadPaneles;
	private double horasRadiacionDiaria;
	
	//constructor
	public PlantaSolar(String nombre, String direccion, boolean estadoPlanta, double maxGeneracion,
			LocalDateTime fechaInicioFuncion, String fuentePrimaria, double eficienciaPromedio, 
			double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		super(nombre, direccion, estadoPlanta, maxGeneracion, fechaInicioFuncion, fuentePrimaria, eficienciaPromedio);
		this.superficieCubierta = superficieCubierta;
		this.cantidadPaneles = cantidadPaneles;
		horasRadiacionDiaria = horasRadiacionDiaria;
	}
	
	//@Override
	public double capadidadMinimaCuota() {
		return getMaxGeneracion() * getEficienciaPromedio() * horasRadiacionDiaria / 23;
	}
	
	
	
}
