package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaTermica extends Planta {
	private String tipoCombustible;
	private double objetivoCO2PorHora;
	private double capacidadAlmacenamientoCombustible;
	
	public PlantaTermica(String nombre, String direccion, double capadidadMaxima,
					LocalDateTime fechaInicioFuncion, String tipoCombustible, double objetivoCO2PorHora,
					double capacidadAlmacenamientoCombustible) {
		super(nombre, direccion, capadidadMaxima, fechaInicioFuncion);
		this.tipoCombustible = tipoCombustible;
		this.objetivoCO2PorHora = objetivoCO2PorHora;
		this.capacidadAlmacenamientoCombustible = capacidadAlmacenamientoCombustible;
	}
	
	
	@Override
    public double capacidadMinimaCuota() {
        return capacidadMaxima / 2; 
    }
    
	
	
	
}
