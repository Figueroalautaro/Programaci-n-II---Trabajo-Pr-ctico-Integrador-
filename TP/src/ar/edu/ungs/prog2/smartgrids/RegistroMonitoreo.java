package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class RegistroMonitoreo {
	Planta planta;
	protected LocalDateTime fechaHora;
	protected double energiaGenerada;
	protected int incidenciasHora;
	
	
	public RegistroMonitoreo (Planta planta, LocalDateTime fechaHora, double energiaGenerada, int incidenciasHora){
		this.planta = planta;
		this.fechaHora = fechaHora;
		this.energiaGenerada = energiaGenerada;
		this.incidenciasHora = incidenciasHora;
	}
	

	
	
}
