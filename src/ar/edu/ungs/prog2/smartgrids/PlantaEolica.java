package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaEolica extends PlantaRenovable{
	private int cantidadAerogeneradores;
	private double alturaTorres;
	private double velocidadMinimaViento;
	
	public PlantaEolica(String nombre, String direccion,
	        double capacidadMaxima,
	        LocalDateTime fechaInicioFuncion,
	        String fuentePrimaria, double eficienciaPromedio,
	        int cantidadAerogeneradores, double alturaTorres,
	        double velocidadMinimaViento) {

	    super(nombre, direccion, capacidadMaxima,
	            fechaInicioFuncion, fuentePrimaria,
	            eficienciaPromedio);

	    if (cantidadAerogeneradores <= 0) {
	        throw new RuntimeException(
	                "La cantidad de aerogeneradores debe ser mayor que cero");
	    }

	    if (alturaTorres <= 0) {
	        throw new RuntimeException(
	                "La altura de las torres debe ser mayor que cero");
	    }

	    if (velocidadMinimaViento <= 0) {
	        throw new RuntimeException(
	                "La velocidad minima debe ser mayor que cero");
	    }

	    this.cantidadAerogeneradores = cantidadAerogeneradores;
	    this.alturaTorres = alturaTorres;
	    this.velocidadMinimaViento = velocidadMinimaViento;
	}
	
	@Override
	public double capacidadMinimaCuota() {
		return capacidadMaxima * eficienciaPromedio;
	}
	
	public boolean cumpleVelocidadMinima(double velocidad) {
		if(velocidad >= velocidadMinimaViento )
			return true;
		return false;
	}
	
	public double getVelocidadMinimaViento(){
		return velocidadMinimaViento;
	}
	
	@Override
	public String toString() {
	    return super.toString()
	            + " [cantidadAerogeneradores=" + cantidadAerogeneradores
	            + ", alturaTorres=" + alturaTorres
	            + ", velocidadMinimaViento=" + velocidadMinimaViento
	            + "]";
	}
}

