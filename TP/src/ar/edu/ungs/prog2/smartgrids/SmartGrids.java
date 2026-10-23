package ar.edu.ungs.prog2.smartgrids;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SmartGrids implements ISmartGrids {
	private Map<String, Planta> plantas= new HashMap<>();
	private Map<String, List<RegistroMonitoreo>> monitoreos= new HashMap<>();
	
	
	//private Map<String, List<RegistroEstado>> estados = new HashMap<>();
	
	//constructor
	public SmartGrids() {}
	
	
	// creacion de todas las plantas  //////////////////////
	@Override
	public void registrarPlantaSolar(String nombre, String direccion, double capacidadMaxima,
			String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
			double superficieCubierta, int cantidadPaneles, double horasRadiacionDiaria) {
		if ( plantas.containsKey(nombre)) {
			throw new RuntimeException("ya existe una planta con ese nombre");
		}
		LocalDateTime fecha= LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento);
		
		//2 creamo la planta
		PlantaSolar plantaS = new PlantaSolar(
				nombre,
				direccion,
				capacidadMaxima,
				fecha,
				fuentePrimaria,
				eficienciaPromedio,
				superficieCubierta,
				cantidadPaneles,
				horasRadiacionDiaria);
		plantas.put(nombre, plantaS);
	}
	
	@Override
	public void registrarPlantaEolica(String nombre, String direccion, double capacidadMaxima,
	        String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio,
	        int cantidadAerogeneradores, double alturaTorres, double velocidadMinimaViento) {

	    if (plantas.containsKey(nombre)) {
	        throw new RuntimeException("Nombre duplicado");
	    }

	    LocalDateTime fecha = LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento);

	    PlantaEolica plantaE = new PlantaEolica(
	            nombre, 
	            direccion, 
	            capacidadMaxima,
	            fecha,
	            fuentePrimaria, 
	            eficienciaPromedio,
	            cantidadAerogeneradores, 
	            alturaTorres, 
	            velocidadMinimaViento
	    );

	    plantas.put(nombre, plantaE);
	}
	
	@Override
	public void registrarPlantaHidraulica(String nombre, String direccion, double capacidadMaxima,
	        String fechaHoraPuestaEnFuncionamiento, String fuentePrimaria, double eficienciaPromedio, 
	        int cantidadTurbinas, double capacidadEmbalse) {

	    if (plantas.containsKey(nombre)) {
	        throw new RuntimeException("Nombre duplicado");
	    }

	    LocalDateTime fecha = LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento);

	    PlantaHidraulica plantaH = new PlantaHidraulica(
	            nombre, 
	            direccion, 
	            capacidadMaxima,
	            fecha,
	            fuentePrimaria, 
	            eficienciaPromedio,
	            cantidadTurbinas,
	            capacidadEmbalse
	    );

	    plantas.put(nombre, plantaH);
	}
	
	@Override
	public void registrarPlantaTermica(String nombre, String direccion, double capacidadMaxima,
	        		String fechaHoraPuestaEnFuncionamiento, String tipoCombustible ,
	        		double objetivoEmisionesCo2PorHora, double capacidadAlmacenamientoCombustible) {

	    if (plantas.containsKey(nombre)) {
	        throw new RuntimeException("Nombre duplicado");
	    }

	    LocalDateTime fecha = LocalDateTime.parse(fechaHoraPuestaEnFuncionamiento);

	    PlantaTermica plantaT = new PlantaTermica(
	            nombre, 
	            direccion, 
	            capacidadMaxima,
	            fecha,

	            tipoCombustible,
	            objetivoEmisionesCo2PorHora,
	            capacidadAlmacenamientoCombustible
	    );

	    plantas.put(nombre, plantaT);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public List<String> consultarPlantas(){
		List<String> consultaP = new ArrayList<>();
		
		for (Planta planta : plantas.values()) {
			String estadoP;
			
			if (planta.estaActiva()) 
				estadoP= "ACTIVA"; 
			else 
				estadoP = "INACTIVA";
			
			String detalle = planta.nombre + " (" + estadoP +"): " + planta.fechaInicioFuncion;
			consultaP.add(detalle);
		}
		return consultaP;
	}


	@Override
	public double capacidadMinimaCuota(String nombre) {
		Planta planta = plantas.get(nombre);
		
		if(planta == null) 
			throw new RuntimeException ("la planta no existe  " + nombre);
		return planta.capacidadMinimaCuota();
	}


	
	////activar y desactivar planta
	@Override
	public void activarPlanta(String nombre, String fechaHora) {
		Planta planta = plantas.get(nombre);
		if(planta == null )
			throw new RuntimeException("la planta no existe  " + nombre);
		
		planta.activar();
	}
	@Override
	public void desactivarPlanta(String nombre, String fechaHora) {
		Planta planta= plantas.get(nombre);
		if(planta == null) 
			throw new RuntimeException("la planta no existe  " + nombre);
		planta.desactivar();
	}
	@Override
	public boolean estaActiva(String nombre) {
		Planta planta = plantas.get(nombre);
		if(planta == null) 
			throw new RuntimeException("la planta no existe  "); 
		
		return planta.estaActiva();
	}


	
	/////monitoreosssssssss
	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias) {
		//esto se repite siemrpe para corroborar que no haya ningun problema
		Planta planta = plantas.get(nombre);
		if (planta == null) 
			throw new RuntimeException("Planta no existe");
		else if (!planta.estaActiva())
			throw new RuntimeException("planta inactiva ");
		else if (fechaHora==null)
			throw new RuntimeException("la fecha no existe ");
		
		LocalDateTime fecha = LocalDateTime.parse(fechaHora);
		//no se como hacer para consultar si una fecha se puso anterior o posterior a la fecha puesta en funcion
		
		if(energiaGenerada < 0 || incidencias < 0) 
			throw new RuntimeException("valores negativos");
		monitoreos.putIfAbsent(nombre, new ArrayList<>());
		List<RegistroMonitoreo> lista = monitoreos.get(nombre);
		
		
		for(RegistroMonitoreo registro : lista) {
			if(registro.fechaHora.equals(fecha)) 
				throw new RuntimeException("registro duplicado"); 
			
		}
		RegistroMonitoreo nuevo = new RegistroMonitoreo (planta, fecha, energiaGenerada,incidencias);
		lista.add(nuevo);
	}


	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
						double velocidadViento) {
		
		//esto se repite siemrpe para corroborar que no haya ningun problema
		Planta planta = plantas.get(nombre);
		if(planta == null) 
			throw new RuntimeException("La planta esta duplicada");
		if(!planta.estaActiva())
			throw new RuntimeException("la planta esta inactiva");
		if(fechaHora == null)
			throw new RuntimeException("la fecha no existe");
		
		LocalDateTime fecha = LocalDateTime.parse(fechaHora);
		
		if(energiaGenerada < 0 || incidencias < 0 || velocidadViento < 0 )
			throw new RuntimeException("Valores negativvos");
		
		monitoreos.putIfAbsent(nombre, new ArrayList<>());
		List<RegistroMonitoreo> lista = monitoreos.get(nombre);
		
		for(RegistroMonitoreo registro : lista) {
			if(registro.fechaHora.equals(fecha))
				throw new RuntimeException("registro duplicado");
		}
		RegistroMonitoreo nuevo = new RegistroEolico(planta, fecha, energiaGenerada, incidencias, velocidadViento );
		lista.add(nuevo);
	}


	@Override
	public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
					double co2Emitido, double combustibleUtilizado) {
		
		//esto se repite siemrpe para corroborar que no haya ningun problema
		Planta planta = plantas.get(nombre);
		if(planta == null) 
			throw new RuntimeException("La planta esta duplicada");
		if(!planta.estaActiva())
			throw new RuntimeException("la planta esta inactiva");
		if(fechaHora == null)
			throw new RuntimeException("la fecha no existe");
		
		LocalDateTime fecha = LocalDateTime.parse(fechaHora);
		
		if(energiaGenerada < 0 || incidencias < 0 || co2Emitido < 0 || combustibleUtilizado<0)
			throw new RuntimeException("Valores negativvos");
		
		monitoreos.putIfAbsent(nombre, new ArrayList<>());
		List<RegistroMonitoreo> lista = monitoreos.get(nombre);
		
		for(RegistroMonitoreo registro : lista) {
			if(registro.fechaHora.equals(fecha))
				throw new RuntimeException("registro duplicado");
		}
		RegistroMonitoreo nuevo = new RegistroTermico(planta, fecha, energiaGenerada, incidencias, co2Emitido, combustibleUtilizado);
		lista.add(nuevo);
	}

		
	


	@Override
	public double totalGenerado(String nombre) {
		Planta planta = plantas.get(nombre);
		if(planta == null)
			throw new RuntimeException("la planta no exite ");
		
		List<RegistroMonitoreo> lista= monitoreos.get(nombre);
		if (!planta.estaActiva() || lista==null)
			return 0;
		
		double total= 0;
		for(RegistroMonitoreo registro: lista) {
			total+= registro.energiaGenerada;
		}
		return total;
	}


	@Override
	public int[] incidenciasPorHora(String nombre) {
		Planta planta = plantas.get(nombre);
		if(planta == null) 
			throw new RuntimeException("la planta no existe  "); 
		
		int[] resultado= new int[24]; // dice que el maximo son 24 por las hroas del dia
		
		List<RegistroMonitoreo> lista= monitoreos.get(nombre);
		if(lista==null )
			return resultado;
		
		
		
		return resultado;
	}


	@Override
	public int[] histogramaCumplimiento(String nombre, String fechaDesde, String fechaHasta) {

		
	}


	@Override
	public Map<String, Integer> plantasRenovablesPorFuente() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> plantasQueNuncaCumplieronCuota() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String horasSinVelocidadMinimaPorPlantaEolica() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int limpiarMonitoreosSinDatos() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<String> plantasOrdenadasPorEnergiaGenerada() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> plantasInactivasEn(String fechaHora) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
