package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class RegistroTermico extends RegistroMonitoreo{
	protected double co2Emitido; 
	protected double combustibleUtilizado;
	
	public RegistroTermico(Planta planta, LocalDateTime fechaHora, double energiaGenerada, int incidenciasHora,
			double co2Emitido, double combustibleUtilizado) {
		super(planta, fechaHora, energiaGenerada, incidenciasHora);
		this.co2Emitido = co2Emitido;
		this.combustibleUtilizado = combustibleUtilizado;
	}
	
	

	
}
