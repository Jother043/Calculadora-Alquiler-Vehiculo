public class Furgoneta extends Vehiculo {

    private static final int PMA_MAXIMO = 3500;
    private static int pma;

    public Furgoneta(GamaCoche gama, Carburante carburante, String matricula, int pma) throws AlquilerVehiculosException {
        super(gama, carburante, matricula);
        //Si el método validar retorna falso entonces nos lanzara una excepción
        if (!validarPma(pma)) {
            throw new AlquilerVehiculosException("Error el peso maximo autorizado no puede ser 0. ");
        }
        this.pma = pma;
    }

    @Override
    public double getPrecioBase() throws AlquilerVehiculosException{
        return (0.5 * pma);
    }

    public static boolean validarPma(int pma) {
        return pma > 0 && pma <= PMA_MAXIMO;
    }

    @Override
    public String toString() {
        return "Furgoneta: " +
                super.toString() +
                "pma=" + pma ;
    }
}
