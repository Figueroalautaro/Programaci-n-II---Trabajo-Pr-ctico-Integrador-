package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PlantaSolar extends PlantaRenovable{
	private double superficieCubierta; 
	private int cantidadPaneles;
	private double horasRadiacionDiaria;
	
	public PlantaSolar(String nombre, String direccion, double capacidadMaxima, LocalDateTime fechaInicioFuncion,
	        			String fuentePrimaria, double eficienciaPromedio,double superficieCubierta, int cantidadPaneles,
	        			double horasRadiacionDiaria) {

	    super(nombre, direccion, capacidadMaxima,
	            fechaInicioFuncion, fuentePrimaria,
	            eficienciaPromedio);

	    if (superficieCubierta <= 0) {
	        throw new RuntimeException(
	                "La superficie cubierta debe ser mayor que cero");
	    }

	    if (cantidadPaneles <= 0) {
	        throw new RuntimeException(
	                "La cantidad de paneles debe ser mayor que cero");
	    }

	    if (horasRadiacionDiaria < 0 || horasRadiacionDiaria > 24) {
	        throw new RuntimeException(
	                "Las horas de radiacion deben estar entre 0 y 24");
	    }

	    this.superficieCubierta = superficieCubierta;
	    this.cantidadPaneles = cantidadPaneles;
	    this.horasRadiacionDiaria = horasRadiacionDiaria;
	}
	
	   @Override
	    public double capacidadMinimaCuota() {
	        return capacidadMaxima * eficienciaPromedio
	                * horasRadiacionDiaria / 24;
	    }
	   
	   @Override
	   public String toString() {
	       return super.toString()
	               + " [superficieCubierta=" + superficieCubierta
	               + ", cantidadPaneles=" + cantidadPaneles
	               + ", horasRadiacionDiaria=" + horasRadiacionDiaria
	               + "]";
	   }
	}
	
