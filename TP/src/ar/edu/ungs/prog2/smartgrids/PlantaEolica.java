package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaEolica extends PlantaRenovable{
	private int cantidadAerogeneradores;
	private double alturaTorres;
	private double velocidadMinimaViento;
	
	public PlantaEolica(String nombre, String direccion, double capadidadMaxima,
			LocalDateTime fechaInicioFuncion, String fuentePrimaria, double eficienciaPromedio, int cantidadAerogeneradores,
			double alturaTorres, double velocidadMinimaViento) {
		super(nombre, direccion, capadidadMaxima, fechaInicioFuncion, fuentePrimaria, eficienciaPromedio);
		this.cantidadAerogeneradores = cantidadAerogeneradores;
		this.alturaTorres = alturaTorres;
		this.velocidadMinimaViento = velocidadMinimaViento;
	}
	
	@Override
	public double capacidadMinimaCuota() {
		return getCapacidadMaxima() * getEficienciaPromedio();
	}
	
	public boolean cumpleVelocidadMinima(double velocidad) {
		if(velocidad > velocidadMinimaViento )
			return true;
		return false;
	}
	
	

	
	public double getVelocidadMinimaViento(){
		return velocidadMinimaViento;
	}
}

