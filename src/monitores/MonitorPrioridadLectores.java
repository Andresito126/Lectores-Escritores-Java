package monitores;

public class MonitorPrioridadLectores implements MonitorLectoresEscritores {
    private int lectoresLeyendo = 0;
    private boolean escritorEscribiendo = false;
    private int lectoresEsperando = 0;
    private boolean lectoresTienenPrioridad = false;

    @Override
    public synchronized void comenzarLectura() {
        lectoresEsperando++;

        // solo espera si se esta escribiendo
        while (escritorEscribiendo) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        lectoresEsperando--;
        lectoresLeyendo++;

        //para que otros lectores entren
        lectoresTienenPrioridad = true;
    }

    @Override
    public synchronized void terminarLectura() {
        lectoresLeyendo--;

        if (lectoresLeyendo == 0) {
            //los sescritores entran
            lectoresTienenPrioridad = false;
            notifyAll();
        }
    }

    @Override
    public synchronized void comenzarEscritura() {
        // espera a que haya lectores esperando
        while (lectoresLeyendo > 0 || escritorEscribiendo || lectoresEsperando > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        escritorEscribiendo = true;
    }

    @Override
    public synchronized void terminarEscritura() {
        escritorEscribiendo = false;
        notifyAll();
    }
}