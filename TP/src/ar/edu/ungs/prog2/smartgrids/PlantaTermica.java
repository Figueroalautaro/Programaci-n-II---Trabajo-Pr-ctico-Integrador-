package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;

public class PlantaTermica extends Planta {
	private String tipoCombustble;
	private double objetivoCO2PorHora;
	private double capacidadAlmacenamientoCombustible;
	
	public PlantaTermica(String nombre, String direccion, boolean estadoPlanta, double maxGeneracion,
			LocalDate fechaInicioFuncion, String tipoCombustble, double objetivoCO2PorHora,
			double capacidadAlmacenamientoCombustible) {
		super(nombre, direccion, estadoPlanta, maxGeneracion, fechaInicioFuncion);
		this.tipoCombustble = tipoCombustble;
		this.objetivoCO2PorHora = objetivoCO2PorHora;
		this.capacidadAlmacenamientoCombustible = capacidadAlmacenamientoCombustible;
	}
	
	
	
	
	
}
