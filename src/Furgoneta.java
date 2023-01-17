public class Furgoneta extends Vehiculo {

    private static final int PMA_MAXIMO = 3500;
    private static final double PRECIO_PMA = 0.5;
    private static int pma;

    public Furgoneta(GamaCoche gama, Carburante carburante, String matricula, int pma) throws AlquilerVehiculosException {
        super(gama, carburante, matricula);
        //Si el método validar retorna falso entonces nos lanzara una excepción
        this.pma = pma;
    }

    /**
     * Método que devuelve el producto del pma por 0,5 del plus por peso
     * para después en el main sumárselo al precio por dias según gama.
     * @return
     */
    @Override
    public double getPrecioBase(){
        return super.getPrecioBase() + (PRECIO_PMA * pma);
    }

    /**
     * Método que valida la pma entre dos valores.
     * @param pma
     * @return
     */
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
