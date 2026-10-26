	package ar.edu.ungs.prog2.smartgrids;
	
	import java.time.LocalDateTime;
import java.util.LinkedList;
	
	
	
	public abstract class Planta {
		protected String nombre;
		protected String direccion;
		//private boolean estadoPlanta;
		protected double capacidadMaxima;
		protected LocalDateTime fechaInicioFuncion;
		private boolean activa = true;
		private LinkedList<PeriodoInactividad> periodosInactividad;
		private LocalDateTime ultimoCambio;
		
		private LocalDateTime ultimaFechaMonitoreo;
		
		public Planta(String nombre, String direccion,
		        double capacidadMaxima,
		        LocalDateTime fechaInicioFuncion) {

		    if (nombre == null || nombre.equals("")) {
		        throw new RuntimeException("El nombre no puede estar vacio");
		    }

		    if (direccion == null || direccion.equals("")) {
		        throw new RuntimeException("La direccion no puede estar vacia");
		    }

		    if (capacidadMaxima <= 0) {
		        throw new RuntimeException(
		                "La capacidad maxima debe ser mayor que cero");
		    }

		    if (fechaInicioFuncion == null) {
		        throw new RuntimeException(
		                "La fecha de inicio no puede ser null");
		    }

		    this.nombre = nombre;
		    this.direccion = direccion;
		    this.capacidadMaxima = capacidadMaxima;
		    this.fechaInicioFuncion = fechaInicioFuncion;

		    periodosInactividad = new LinkedList<>();
		    ultimoCambio = fechaInicioFuncion;
		}
		
		//para ver si esta activa o inactiva las plantas.
		public boolean estaActiva() {
			return activa;
		}
		
		 public void activar(LocalDateTime fechaHora) {
		        validarFechaCambio(fechaHora);

		        if (activa) {
		            return;
		        }

		        PeriodoInactividad periodo =
		                periodosInactividad.getLast();

		        periodo.cerrar(fechaHora);
		        activa = true;
		        ultimoCambio = fechaHora;
		    }

		 public void desactivar(LocalDateTime fechaHora) {
		        validarFechaCambio(fechaHora);

		        if (!activa) {
		            return;
		        }
		        
		        if (ultimaFechaMonitoreo != null
		                && fechaHora.compareTo(ultimaFechaMonitoreo) <= 0) {

		            throw new RuntimeException(
		                    "La desactivacion contradice monitoreos registrados");
		        }

		        periodosInactividad.addLast(
		                new PeriodoInactividad(fechaHora));

		        activa = false;
		        ultimoCambio = fechaHora;
		    }
		
		 public void registrarFechaMonitoreo(LocalDateTime fechaHora) {
			    if (ultimaFechaMonitoreo == null
			            || fechaHora.compareTo(ultimaFechaMonitoreo) > 0) {

			        ultimaFechaMonitoreo = fechaHora;
			    }
			}
		 
		 private void validarFechaCambio(LocalDateTime fechaHora) {
		        if (fechaHora == null) {
		            throw new RuntimeException("La fecha no puede ser null");
		        }

		        if (fechaHora.isBefore(ultimoCambio)) {
		            throw new RuntimeException(
		                    "El cambio no puede ser anterior al ultimo cambio");
		        }
		    }

		    public boolean estuvoInactivaEn(LocalDateTime fechaHora) {
		        for (PeriodoInactividad periodo : periodosInactividad) {
		            if (periodo.contiene(fechaHora)) {
		                return true;
		            }
		        }

		        return false;
		    }

		    public boolean cumpleCuota(RegistroMonitoreo registro) {
		        return registro.getEnergiaGenerada() >= capacidadMinimaCuota();
		    }
		    
		    public String getNombre() {
		        return nombre;
		    }

		    public LocalDateTime getFechaInicioFuncion() {
		        return fechaInicioFuncion;
		    }

		    public abstract double capacidadMinimaCuota();

		    @Override
		    public String toString() {
		        return "Planta [nombre=" + nombre
		                + ", direccion=" + direccion
		                + ", capacidadMaxima=" + capacidadMaxima
		                + ", fechaInicioFuncion=" + fechaInicioFuncion
		                + ", activa=" + activa
		                + ", periodosInactividad=" + periodosInactividad
		                + "]";
		    }
		    }
		