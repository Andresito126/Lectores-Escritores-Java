package hilos;

import recurso.RecursoCompartido;

public class Lector extends Thread {
    private RecursoCompartido recurso;
    private String nombre;
    private int intentos;

    public Lector(RecursoCompartido recurso, String nombre, int intentos) {
        this.recurso = recurso;
        this.nombre = nombre;
        this.intentos = intentos;
    }

    @Override
    public void run() {
        for (int i = 0; i < intentos; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000));

                // Leer del recurso
                String dato = recurso.leer();
                System.out.println(nombre + " leyó: " + dato);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}