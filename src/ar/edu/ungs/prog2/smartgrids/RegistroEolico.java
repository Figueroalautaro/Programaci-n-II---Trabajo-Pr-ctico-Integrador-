	package ar.edu.ungs.prog2.smartgrids;
	
	import java.time.LocalDateTime;
	
	public class RegistroEolico extends RegistroMonitoreo{
		protected double velocidadViento;
	
		public RegistroEolico(Planta planta,
		        LocalDateTime fechaHora,
		        double energiaGenerada, int incidenciasHora,
		        double velocidadViento) {

		    super(planta, fechaHora, energiaGenerada, incidenciasHora);

		    if (!(planta instanceof PlantaEolica)) {
		        throw new RuntimeException("La planta no es eolica");
		    }

		    if (velocidadViento < 0) {
		        throw new RuntimeException(
		                "La velocidad del viento no puede ser negativa");
		    }

		    this.velocidadViento = velocidadViento;
		}
		
		public double getVelocidadViento() {
	        return velocidadViento;
	    }

	    @Override
	    public String toString() {
	        return super.toString()
	                + " [velocidadViento=" + velocidadViento + "]";
	    }
		
	
	}
