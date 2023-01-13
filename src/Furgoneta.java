public class Furgoneta extends Vehiculo {

    private static final int PMA_MAXIMO = 3500;
    private int pma;

    public Furgoneta(GamaCoche gama, Carburante carburante, String matricula, int pma) throws AlquilerVehiculosException {
        super(gama, carburante, matricula);

        if (validarPma()) {
            throw new AlquilerVehiculosException("Error el peso maximo autorizado no puede ser 0. ");
        }
        this.pma = pma;
    }

    public double getPrecioPma() {
        return super.getPrecioBase() + (0.5 * pma);
    }

    public boolean validarPma() {
        return pma > 0 && pma <= PMA_MAXIMO;
    }
}
