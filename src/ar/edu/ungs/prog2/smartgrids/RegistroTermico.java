package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class RegistroTermico extends RegistroMonitoreo{
	protected double co2Emitido; 
	protected double combustibleUtilizado;
	
	public RegistroTermico(Planta planta,
	        LocalDateTime fechaHora,
	        double energiaGenerada, int incidenciasHora,
	        double co2Emitido, double combustibleUtilizado) {

	    super(planta, fechaHora, energiaGenerada, incidenciasHora);

	    if (!(planta instanceof PlantaTermica)) {
	        throw new RuntimeException("La planta no es termica");
	    }

	    if (co2Emitido < 0) {
	        throw new RuntimeException(
	                "El CO2 emitido no puede ser negativo");
	    }

	    if (combustibleUtilizado < 0) {
	        throw new RuntimeException(
	                "El combustible utilizado no puede ser negativo");
	    }

	    this.co2Emitido = co2Emitido;
	    this.combustibleUtilizado = combustibleUtilizado;
	}
	

	    @Override
	    public String toString() {
	        return super.toString()
	                + " [co2Emitido=" + co2Emitido
	                + ", combustibleUtilizado="
	                + combustibleUtilizado + "]";
	    }
	
}
