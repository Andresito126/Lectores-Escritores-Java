package monitores;

public interface MonitorLectoresEscritores {

    //para que un lector solicite permiso para leer
    void comenzarLectura();

    void terminarLectura();

    // para que el escritor solicite permiso para escribir
    void comenzarEscritura();

    // para que un escritor indique que terminó de escribir
    void terminarEscritura();

}
