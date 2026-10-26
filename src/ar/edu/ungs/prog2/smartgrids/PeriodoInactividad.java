package ar.edu.ungs.prog2.smartgrids;

import java.time.LocalDateTime;

public class PeriodoInactividad {
    private LocalDateTime inicio;
    private LocalDateTime fin;

    public PeriodoInactividad(LocalDateTime inicio) {
        if (inicio == null) {
            throw new RuntimeException("El inicio no puede ser null");
        }

        this.inicio = inicio;
        this.fin = null;
    }

    public void cerrar(LocalDateTime fin) {
        if (fin == null || fin.isBefore(inicio)) {
            throw new RuntimeException("Fecha de fin invalida");
        }

        if (this.fin != null) {
            throw new RuntimeException("El periodo ya esta cerrado");
        }

        this.fin = fin;
    }

    public boolean contiene(LocalDateTime fechaHora) {
        if (fechaHora.isBefore(inicio)) {
            return false;
        }

        if (fin == null) {
            return true;
        }

        return fechaHora.isBefore(fin);
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    @Override
    public String toString() {
        return "PeriodoInactividad [inicio=" + inicio
                + ", fin=" + fin + "]";
    }
}