package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;



public abstract class Planta {
	private String nombre;
	private String direccion;
	private boolean estadoPlanta;
	private double maxGeneracion;
	private LocalDateTime fechaInicioFuncion;
	private boolean activa;
	
	public Planta(String nombre, String direccion, boolean activa, double maxGeneracion,
			LocalDateTime fechaInicioFuncion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.activa = activa;
		this.maxGeneracion = maxGeneracion;
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
	
	public void cambiarEstado(String nombre, boolean nuevoEstado) {

	}
	
	
	//funciones necesarias
	

	
	
	
	//solo los GETTS NECESARIOS
	public String getNombre() {
		return nombre;
	}
	
	public LocalDateTime getFechaInicioFuncion() {
		return fechaInicioFuncion;
	}
}
