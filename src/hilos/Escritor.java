package hilos;

import recurso.RecursoCompartido;

public class Escritor extends Thread {
    private RecursoCompartido recurso;
    private String nombre;
    private int intentos;

    public Escritor(RecursoCompartido recurso, String nombre, int intentos) {
        this.recurso = recurso;
        this.nombre = nombre;
        this.intentos = intentos;
    }

    @Override
    public void run() {
        for (int i = 0; i < intentos; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000));

                // escribe  el recurso
                String nuevoDato = "Dato-de-" + nombre + "-v" + i;
                recurso.escribir(nuevoDato);
                System.out.println(nombre + " escribió: " + nuevoDato);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}