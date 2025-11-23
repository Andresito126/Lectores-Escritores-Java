import monitores.MonitorLectoresEscritores;
import monitores.MonitorPrioridadLectores;
import recurso.RecursoCompartido;
import hilos.Lector;
import hilos.Escritor;

public class Main {
    public static void main(String[] args) {
        MonitorLectoresEscritores monitor = new MonitorPrioridadLectores();
        RecursoCompartido recurso = new RecursoCompartido(monitor);

        Lector lector1 = new Lector(recurso, "Lector 1", 5);
        Lector lector2 = new Lector(recurso, "Lector 2", 5);
        Lector lector3 = new Lector(recurso, "Lector 3", 5);
        Escritor escritor1 = new Escritor(recurso, "Escritor 1", 2);
        Escritor escritor2 = new Escritor(recurso, "Escritor 2", 2);

        System.out.println("iniciando");

        lector1.start();
        lector2.start();
        lector3.start();

        try { Thread.sleep(50); } catch (InterruptedException e) {}

        escritor1.start();
        escritor2.start();

        try {
            lector1.join();
            lector2.join();
            lector3.join();
            escritor1.join();
            escritor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("finalizado");
        System.out.println("Valor del recurso final: " + recurso.getDato());
    }
}