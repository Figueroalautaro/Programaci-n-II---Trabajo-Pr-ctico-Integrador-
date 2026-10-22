package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaTermica extends Planta {
	private String tipoCombustble;
	private double objetivoCO2PorHora;
	private double capacidadAlmacenamientoCombustible;
	
	public PlantaTermica(String nombre, String direccion, double capadidadMaxima,
					LocalDateTime fechaInicioFuncion, String tipoCombustble, double objetivoCO2PorHora,
					double capacidadAlmacenamientoCombustible) {
		super(nombre, direccion, capadidadMaxima, fechaInicioFuncion);
		this.tipoCombustble = tipoCombustble;
		this.objetivoCO2PorHora = objetivoCO2PorHora;
		this.capacidadAlmacenamientoCombustible = capacidadAlmacenamientoCombustible;
	}
	
	
	@Override
    public double capacidadMinimaCuota() {
        return capacidadMaxima / 2; 
    }
    
	
	
	
}
