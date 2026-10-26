package ar.edu.ungs.prog2.smartgrids;
import java.util.LinkedHashMap;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmartGrids implements ISmartGrids {
    private Map<String, Planta> plantas = new LinkedHashMap<>();
    
    private Map<String, Map<LocalDateTime, RegistroMonitoreo>> monitoreos = new HashMap<>();

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
        monitoreos.put(nombre, new LinkedHashMap<>());
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
        monitoreos.put(nombre, new LinkedHashMap<>());
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
        monitoreos.put(nombre, new LinkedHashMap<>());
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
        monitoreos.put(nombre, new LinkedHashMap<>());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<String> consultarPlantas() {
        List<String> resultado = new ArrayList<>();

        DateTimeFormatter formato =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        for (Planta planta : plantas.values()) {
            String estado;

            if (planta.estaActiva()) {
                estado = "ACTIVA";
            } else {
                estado = "INACTIVA";
            }

            String detalle = planta.nombre
            + " (" + estado + "): "
            + planta.fechaInicioFuncion.format(formato);

            resultado.add(detalle);
        }

        return resultado;
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

        if (planta == null) {
            throw new RuntimeException("La planta no existe");
        }

        LocalDateTime fecha = LocalDateTime.parse(fechaHora);
        planta.activar(fecha);
    }

    @Override
    public void desactivarPlanta(String nombre, String fechaHora) {
        Planta planta = plantas.get(nombre);

        if (planta == null) {
            throw new RuntimeException("La planta no existe");
        }

        LocalDateTime fecha = LocalDateTime.parse(fechaHora);
        planta.desactivar(fecha);
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

        if (planta instanceof PlantaEolica || planta instanceof PlantaTermica) {
            throw new RuntimeException("Esta planta necesita datos adicionales de monitoreo");
        }

        LocalDateTime fecha = LocalDateTime.parse(fechaHora);

        if (fecha.compareTo(planta.fechaInicioFuncion) < 0) {
            throw new RuntimeException(
            "El monitoreo es anterior a la puesta en funcionamiento");
        }

        if (planta.estuvoInactivaEn(fecha)) {
            throw new RuntimeException(
            "La planta estaba inactiva en esa fecha");
        }

        if(energiaGenerada < 0 || incidencias < 0)
        throw new RuntimeException("valores negativos");
        Map<LocalDateTime, RegistroMonitoreo> registros = monitoreos.get(nombre);

        if (registros.containsKey(fecha)) {
            throw new RuntimeException("Registro duplicado");
        }
        RegistroMonitoreo nuevo = new RegistroMonitoreo (planta, fecha, energiaGenerada,incidencias);
        registros.put(fecha, nuevo);
        
        planta.registrarFechaMonitoreo(fecha);
    }

    @Override
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
    double velocidadViento) {

        //esto se repite siemrpe para corroborar que no haya ningun problema
        Planta planta = plantas.get(nombre);
        if(planta == null)
        throw new RuntimeException("La planta no existe");
        if(!planta.estaActiva())
        throw new RuntimeException("la planta esta inactiva");
        if(fechaHora == null)
        throw new RuntimeException("la fecha no existe");

        if (!(planta instanceof PlantaEolica)) {
            throw new RuntimeException("La planta no es eolica");
        }

        LocalDateTime fecha = LocalDateTime.parse(fechaHora);

        if (fecha.compareTo(planta.fechaInicioFuncion) < 0) {
            throw new RuntimeException(
            "El monitoreo es anterior a la puesta en funcionamiento");
        }

        if (planta.estuvoInactivaEn(fecha)) {
            throw new RuntimeException(
            "La planta estaba inactiva en esa fecha");
        }

        if(energiaGenerada < 0 || incidencias < 0 || velocidadViento < 0 )
        throw new RuntimeException("Valores negativvos");
        Map<LocalDateTime, RegistroMonitoreo> registros = monitoreos.get(nombre);

        if (registros.containsKey(fecha)) {
            throw new RuntimeException("Registro duplicado");
        }
        RegistroMonitoreo nuevo = new RegistroEolico(planta, fecha, energiaGenerada, incidencias, velocidadViento );
        registros.put(fecha, nuevo);
        planta.registrarFechaMonitoreo(fecha);
    }

    @Override
    public void registrarMonitoreo(String nombre, String fechaHora, double energiaGenerada, int incidencias,
    double co2Emitido, double combustibleUtilizado) {

        //esto se repite siemrpe para corroborar que no haya ningun problema
        Planta planta = plantas.get(nombre);
        if(planta == null)
        	throw new RuntimeException("La planta no existe");
        if(!planta.estaActiva())
        	throw new RuntimeException("la planta esta inactiva");
        if(fechaHora == null)
        	throw new RuntimeException("la fecha no existe");

        if (!(planta instanceof PlantaTermica))
            throw new RuntimeException("La planta no es termica");
        

        LocalDateTime fecha = LocalDateTime.parse(fechaHora);

        if (fecha.compareTo(planta.fechaInicioFuncion) < 0) {
            throw new RuntimeException(
            "El monitoreo es anterior a la puesta en funcionamiento");
        }

        if (planta.estuvoInactivaEn(fecha)) {
            throw new RuntimeException(
            "La planta estaba inactiva en esa fecha");
        }

        if(energiaGenerada < 0 || incidencias < 0 || co2Emitido < 0 || combustibleUtilizado<0)
        throw new RuntimeException("Valores negativvos");
        Map<LocalDateTime, RegistroMonitoreo> registros = monitoreos.get(nombre);

        if (registros.containsKey(fecha)) {
            throw new RuntimeException("Registro duplicado");
        }
        RegistroMonitoreo nuevo = new RegistroTermico(planta, fecha, energiaGenerada, incidencias, co2Emitido, combustibleUtilizado);
        registros.put(fecha, nuevo);
        planta.registrarFechaMonitoreo(fecha);
    }

    @Override
    public double totalGenerado(String nombre) {
        Planta planta = plantas.get(nombre);

        if (planta == null) {
            throw new RuntimeException("La planta no existe");
        }

        Map<LocalDateTime, RegistroMonitoreo> registros = monitoreos.get(nombre);

        if (registros == null) {
            return 0;
        }

        double total = 0;

        for (RegistroMonitoreo registro : registros.values()) {
            total += registro.energiaGenerada;
        }

        return total;
    }

    @Override
    public int[] incidenciasPorHora(String nombre) {
        Planta planta = plantas.get(nombre);

        if (planta == null) {
            throw new RuntimeException("La planta no existe");
        }

        int[] resultado = new int[24];

        Map<LocalDateTime, RegistroMonitoreo> registros = monitoreos.get(nombre);

        if (registros == null) {
            return resultado;
        }

        for (RegistroMonitoreo registro : registros.values()) {
            int hora = registro.fechaHora.getHour();

            resultado[hora] += registro.incidenciasHora;
        }

        return resultado;
    }
    
    //////////////////////////////////////////////////

    @Override
    public int[] histogramaCumplimiento(String nombre,
    String fechaDesde, String fechaHasta) {

        Planta planta = plantas.get(nombre);

        if (planta == null) {
            throw new RuntimeException("La planta no existe");
        }

        LocalDate desde = LocalDate.parse(fechaDesde);
        LocalDate hasta = LocalDate.parse(fechaHasta);

        if (desde.compareTo(hasta) > 0) {
            throw new RuntimeException(
            "La fecha inicial es posterior a la final");
        }

        int[] resultado = new int[24];

        Map<LocalDateTime, RegistroMonitoreo> registros = monitoreos.get(nombre);

        if (registros == null) {
            return resultado;
        }

        for (RegistroMonitoreo registro : registros.values()) {
            LocalDate dia = registro.fechaHora.toLocalDate();

            if (dia.compareTo(desde) >= 0
            && dia.compareTo(hasta) <= 0) {

                if (planta.cumpleCuota(registro)) {
                    int hora = registro.fechaHora.getHour();
                    resultado[hora]++;
                }

            }
        }

        return resultado;
    }

    @Override
    public Map<String, Integer> plantasRenovablesPorFuente() {
        Map<String, Integer> cantidades = new HashMap<>();
        List<String> fuentesOrdenadas = new ArrayList<>();

        for (Planta planta : plantas.values()) {
            if (planta instanceof PlantaRenovable) {
                PlantaRenovable renovable = (PlantaRenovable) planta;
                String fuente = renovable.fuentePrimaria;

                if (cantidades.containsKey(fuente)) {
                    int cantidad = cantidades.get(fuente);
                    cantidades.put(fuente, cantidad + 1);
                } else {
                    cantidades.put(fuente, 1);

                    int posicion = 0;

                    while (posicion < fuentesOrdenadas.size()
                            && fuentesOrdenadas.get(posicion)
                                    .compareTo(fuente) < 0) {
                        posicion++;
                    }

                    fuentesOrdenadas.add(posicion, fuente);
                }
            }
        }

        Map<String, Integer> resultado = new LinkedHashMap<>();

        for (String fuente : fuentesOrdenadas) {
            resultado.put(fuente, cantidades.get(fuente));
        }

        return resultado;
    }

    @Override
    public List<String> plantasQueNuncaCumplieronCuota() {
        List<String> resultado = new ArrayList<>();

        for (Planta planta : plantas.values()) {
            boolean cumplio = false;

            Map<LocalDateTime, RegistroMonitoreo> registros =
            monitoreos.get(planta.nombre);

            if (registros != null) {
                for (RegistroMonitoreo registro : registros.values()) {
                    if (planta.cumpleCuota(registro)) {
                        cumplio = true;
                        break;
                    }
                }
            }

            if (!cumplio) {
                resultado.add(planta.nombre);
            }
        }

        return resultado;
    }

    @Override
    public String horasSinVelocidadMinimaPorPlantaEolica() {
        StringBuilder resultado = new StringBuilder();

        resultado.append("Plantas Eolicas: Horas con poco viento\n");

        for (Planta planta : plantas.values()) {
            if (planta instanceof PlantaEolica) {
                PlantaEolica eolica = (PlantaEolica) planta;

                boolean[] horasConRegistros = new boolean[24];
                double[] velocidadesMaximas = new double[24];

                Map<LocalDateTime, RegistroMonitoreo> registros =
                monitoreos.get(planta.nombre);

                if (registros != null) {
                    for (RegistroMonitoreo registro : registros.values()) {
                        RegistroEolico registroEolico =
                        (RegistroEolico) registro;

                        int hora = registro.fechaHora.getHour();
                        double velocidad =
                        registroEolico.getVelocidadViento();

                        horasConRegistros[hora] = true;

                        if (velocidad > velocidadesMaximas[hora]) {
                            velocidadesMaximas[hora] = velocidad;
                        }
                    }
                }

                resultado.append("- ");
                resultado.append(planta.nombre);
                resultado.append(":  ");
                resultado.append(eolica.getVelocidadMinimaViento());
                resultado.append(" m/s\n");

                for (int hora = 0; hora < 24; hora++) {
                    if (horasConRegistros[hora]
                    && !eolica.cumpleVelocidadMinima(
                    velocidadesMaximas[hora])) {

                        resultado.append("       ");

                        if (hora < 10) {
                            resultado.append("0");
                        }

                        resultado.append(hora);
                        resultado.append(": ");
                        resultado.append(velocidadesMaximas[hora]);
                        resultado.append(" m/s\n");
                    }
                }
            }
        }

        return resultado.toString();
    }

    @Override
    public int limpiarMonitoreosSinDatos() {
        int cantidadBorrados = 0;

        for (Map<LocalDateTime, RegistroMonitoreo> registros : monitoreos.values()) {
            Iterator<RegistroMonitoreo> iterador = registros.values().iterator();

            while (iterador.hasNext()) {
                RegistroMonitoreo registro = iterador.next();

                if (registro.energiaGenerada == 0
                && registro.incidenciasHora == 0) {

                    iterador.remove();
                    cantidadBorrados++;
                }
            }
        }

        return cantidadBorrados;
    }

    @Override
    public List<String> plantasOrdenadasPorEnergiaGenerada() {
        List<String> nombresOrdenados = new ArrayList<>();
        Map<String, Double> totales = new HashMap<>();

        for (Planta planta : plantas.values()) {
            String nombre = planta.nombre;
            double total = totalGenerado(nombre);

            totales.put(nombre, total);

            int posicion = 0;

            while (posicion < nombresOrdenados.size()
            && totales.get(nombresOrdenados.get(posicion))
            >= total) {

                posicion++;
            }

            nombresOrdenados.add(posicion, nombre);
        }

        List<String> resultado = new ArrayList<>();

        for (String nombre : nombresOrdenados) {
            resultado.add(nombre + ": " + totales.get(nombre));
        }

        return resultado;
    }

    @Override
    public List<String> plantasInactivasEn(String fechaHora) {
        LocalDateTime fecha = LocalDateTime.parse(fechaHora);

        List<String> resultado = new ArrayList<>();

        for (Planta planta : plantas.values()) {
            if (planta.estuvoInactivaEn(fecha)) {
                resultado.add(planta.nombre);
            }
        }

        return resultado;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("SmartGrids\n");

        if (plantas.isEmpty()) {
            resultado.append("No hay plantas registradas.\n");
        }

        for (Planta planta : plantas.values()) {
            resultado.append(planta.toString());
            resultado.append("\n");

            Map<LocalDateTime, RegistroMonitoreo> registros =
            monitoreos.get(planta.nombre);

            if (registros == null || registros.isEmpty()) {
                resultado.append("  Sin monitoreos.\n");
            } else {
                for (RegistroMonitoreo registro : registros.values()) {
                    resultado.append("  ");
                    resultado.append(registro.toString());
                    resultado.append("\n");
                }
            }
        }

        return resultado.toString();
    }
}