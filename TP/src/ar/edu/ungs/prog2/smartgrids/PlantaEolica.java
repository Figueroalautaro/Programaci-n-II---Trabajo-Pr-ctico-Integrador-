package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaEolica extends PlantaRenovable{
	private int cantidadAerogeneradores;
	private double alturaTorres;
	private double velocidadMinimaViento;
	
	public PlantaEolica(String nombre, String direccion, boolean estadoPlanta, double maxGeneracion,
			LocalDateTime fechaInicioFuncion, String fuentePrimaria, double eficienciaPromedio, int cantidadAerogeneradores,
			double alturaTorres, double velocidadMinimaViento) {
		super(nombre, direccion, estadoPlanta, maxGeneracion, fechaInicioFuncion, fuentePrimaria, eficienciaPromedio);
		this.cantidadAerogeneradores = cantidadAerogeneradores;
		this.alturaTorres = alturaTorres;
		this.velocidadMinimaViento = velocidadMinimaViento;
	}
	
	
	
	
	
	
	public boolean cumpleVelocidadMinima(double velocidad) {
		if(velocidad > velocidadMinimaViento )
			return true;
		return false;
	}
	
	
	public double generacionMinimaCuota() {
		return getMaxGeneracion() * getEficienciaPromedio();
	}
	
	public double getVelocidadMinimaViento(){
		return velocidadMinimaViento;
	}
}

