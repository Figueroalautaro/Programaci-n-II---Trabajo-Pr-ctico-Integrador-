package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaTermica extends Planta {
	private String tipoCombustible;
	private double objetivoCO2PorHora;
	private double capacidadAlmacenamientoCombustible;
	
	public PlantaTermica(String nombre, String direccion,
	        double capacidadMaxima,
	        LocalDateTime fechaInicioFuncion,
	        String tipoCombustible, double objetivoCO2PorHora,
	        double capacidadAlmacenamientoCombustible) {

	    super(nombre, direccion, capacidadMaxima, fechaInicioFuncion);

	    if (tipoCombustible == null || tipoCombustible.equals("")) {
	        throw new RuntimeException(
	                "El tipo de combustible no puede estar vacio");
	    }

	    if (objetivoCO2PorHora <= 0) {
	        throw new RuntimeException(
	                "El objetivo de CO2 debe ser mayor que cero");
	    }

	    if (capacidadAlmacenamientoCombustible <= 0) {
	        throw new RuntimeException(
	                "La capacidad de almacenamiento debe ser mayor que cero");
	    }

	    this.tipoCombustible = tipoCombustible;
	    this.objetivoCO2PorHora = objetivoCO2PorHora;
	    this.capacidadAlmacenamientoCombustible =
	            capacidadAlmacenamientoCombustible;
	}
	
	
	@Override
    public double capacidadMinimaCuota() {
        return capacidadMaxima / 2; 
    }
	
	@Override
	public boolean cumpleCuota(RegistroMonitoreo registro) {
	    RegistroTermico registroTermico = (RegistroTermico) registro;

	    return registroTermico.getEnergiaGenerada()
	                >= capacidadMinimaCuota()
	            && registroTermico.getCo2Emitido()
	                < objetivoCO2PorHora;
	}
    
	@Override
	public String toString() {
	    return super.toString()
	            + " [tipoCombustible=" + tipoCombustible
	            + ", objetivoCO2PorHora=" + objetivoCO2PorHora
	            + ", capacidadAlmacenamientoCombustible="
	            + capacidadAlmacenamientoCombustible
	            + "]";
	}
	
	
}
