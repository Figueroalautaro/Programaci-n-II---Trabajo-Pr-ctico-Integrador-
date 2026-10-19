package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class RegistroMonitoreo {
	Planta planta;
	private LocalDateTime fechaHoraDeRegistro;
	private double energiaGeneradaHora;
	private int incidenciasHora;
	
	
	public RegistroMonitoreo (Planta planta, LocalDateTime fechaHora, double energiaGeneradaHora, int incidenciasHora){
		this.planta = planta;
		this.fechaHoraDeRegistro = fechaHoraDeRegistro;
		this.energiaGeneradaHora = energiaGeneradaHora;
		this.incidenciasHora = incidenciasHora;
	}
	
	
	
}
