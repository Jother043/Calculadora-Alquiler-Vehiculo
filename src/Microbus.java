public class Microbus extends Vehiculo{


        private static final int PLAZAS_MAX = 16;
        private static final double PRECIO_PLAZAS = 5;
        private static int plazas;

        public Microbus(GamaCoche gama, Carburante carburante, String matricula, int plazas) throws AlquilerVehiculosException {
            super(gama, carburante, matricula);

            this.plazas = plazas;
        }

    /**
     * Método que nos devuelve el producto del precio de una plaza por las elegidas por el usuario.
     * @return
     */
    @Override
        public  double getPrecioBase(){
            return super.getPrecioBase() + (PRECIO_PLAZAS * plazas);
        }

    /**
     * Método que nos valida que la plaza no se exceda se un mínimo y un máximo.
     * @param plazas
     * @return
     */
    public static boolean validarPlazas(int plazas) {
            return (plazas > 0 && plazas < PLAZAS_MAX);
        }

    @Override
    public String toString() {
        return "Microbús: " +
                super.toString() +
                "Plazas =" + plazas;
    }
}
