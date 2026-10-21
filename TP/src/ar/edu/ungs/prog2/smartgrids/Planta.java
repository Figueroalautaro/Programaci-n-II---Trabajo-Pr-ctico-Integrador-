package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;



public abstract class Planta {
	protected String nombre;
	protected String direccion;
	//private boolean estadoPlanta;
	protected double capacidadMaxima;
	protected LocalDateTime fechaInicioFuncion;
	private boolean activa = true;
	
	public Planta(String nombre, String direccion, double capacidadMaxima,
			LocalDateTime fechaInicioFuncion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
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

	
	
	public abstract double capacidadMinimaCuota();
}
