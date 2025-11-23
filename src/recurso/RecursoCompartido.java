package recurso;

import monitores.MonitorLectoresEscritores;

public class RecursoCompartido {
    private String dato;
    private MonitorLectoresEscritores monitor;

    // inyectamo en el constructir el monitor
    public RecursoCompartido(MonitorLectoresEscritores monitor) {
        this.monitor = monitor;
        this.dato = "Dato inicial";
    }

    public String leer() {
        monitor.comenzarLectura();
        // lee el dato
        String valor = this.dato;
        monitor.terminarLectura();
        return valor;
    }

    public void escribir(String nuevoDato) {
        monitor.comenzarEscritura();
        // Escribir el dato
        this.dato = nuevoDato;
        monitor.terminarEscritura();
    }

    //obtener acutal
    public String getDato() {
        return dato;
    }
}