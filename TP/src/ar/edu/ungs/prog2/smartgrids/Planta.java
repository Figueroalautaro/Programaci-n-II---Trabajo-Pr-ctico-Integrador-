package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDate;



public abstract class Planta {
	private String nombre;
	private String direccion;
	private boolean estadoPlanta;
	private double maxGeneracion;
	private LocalDate fechaInicioFuncion;
	
	public Planta(String nombre, String direccion, boolean estadoPlanta, double maxGeneracion,
			LocalDate fechaInicioFuncion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.estadoPlanta = estadoPlanta;
		this.maxGeneracion = maxGeneracion;
		this.fechaInicioFuncion = fechaInicioFuncion;
	}
	
	public void cambiarEstado(String nombre, boolean nuevoEstado) {
		
	}
	
	public boolean cumpleCuota(){
		
	}
	
	
	
	
}
