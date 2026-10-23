package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class RegistroEolico extends RegistroMonitoreo{
	protected double velocidadViento;

	public RegistroEolico(Planta planta, LocalDateTime fechaHora, double energiaGenerada, int incidenciasHora,
			double velocidadViento) {
		super(planta, fechaHora, energiaGenerada, incidenciasHora);
		this.velocidadViento = velocidadViento;
	}
	
	
	

}
