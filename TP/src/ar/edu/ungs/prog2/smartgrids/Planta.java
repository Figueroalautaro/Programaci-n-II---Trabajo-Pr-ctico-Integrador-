package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;



public abstract class Planta {
	private String nombre;
	private String direccion;
	//private boolean estadoPlanta;
	private double capadidadMaxima;
	private LocalDateTime fechaInicioFuncion;
	protected boolean activa = true;
	
	public Planta(String nombre, String direccion, double capadidadMaxima,
			LocalDateTime fechaInicioFuncion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capadidadMaxima = capadidadMaxima;
		this.fechaInicioFuncion = fechaInicioFuncion;
	}
	
	//para ver si esta activa o inactiva las plantas.
	public boolean estaActiva() {
		return activa;
	}
	public void activar() {
		activa = true;
	}
	public void desactivar() {
		activa = false;
	}
	
	
	
	//funciones necesarias
	

	
	
	
	//solo los GETTS NECESARIOS
	public String getNombre() {
		return nombre;
	}
	
	public LocalDateTime getFechaInicioFuncion() {
		return fechaInicioFuncion;
	}
	
	public double getCapacidadMaxima() {
		return capadidadMaxima;
	}
	
	
	public abstract double capacidadMinimaCuota();
}
