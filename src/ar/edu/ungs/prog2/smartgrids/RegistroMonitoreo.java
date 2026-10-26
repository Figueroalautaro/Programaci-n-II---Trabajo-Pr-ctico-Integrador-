package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class RegistroMonitoreo {
	Planta planta;
	protected LocalDateTime fechaHora;
	protected double energiaGenerada;
	protected int incidenciasHora;
	
	
	public RegistroMonitoreo(Planta planta,
	        LocalDateTime fechaHora,
	        double energiaGenerada, int incidenciasHora) {

	    if (planta == null) {
	        throw new RuntimeException("La planta no puede ser null");
	    }

	    if (fechaHora == null) {
	        throw new RuntimeException("La fecha no puede ser null");
	    }

	    if (fechaHora.compareTo(planta.fechaInicioFuncion) < 0) {
	        throw new RuntimeException(
	                "El registro es anterior a la puesta en funcionamiento");
	    }
	    
	    if (fechaHora.getMinute() != 0
	            || fechaHora.getSecond() != 0
	            || fechaHora.getNano() != 0) {

	        throw new RuntimeException(
	                "El registro debe corresponder a una hora exacta");
	    }

	    if (energiaGenerada < 0) {
	        throw new RuntimeException(
	                "La energia generada no puede ser negativa");
	    }

	    if (incidenciasHora < 0) {
	        throw new RuntimeException(
	                "Las incidencias no pueden ser negativas");
	    }

	    this.planta = planta;
	    this.fechaHora = fechaHora;
	    this.energiaGenerada = energiaGenerada;
	    this.incidenciasHora = incidenciasHora;
	}
	
	public Planta getPlanta() {
        return planta;
    }


    @Override
    public String toString() {
        return "RegistroMonitoreo [fechaHora=" + fechaHora
                + ", energiaGenerada=" + energiaGenerada
                + ", incidenciasHora=" + incidenciasHora + "]";
    }
	
	
}
