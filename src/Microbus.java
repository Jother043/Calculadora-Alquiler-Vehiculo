public class Microbus extends Vehiculo{


        private static final int PLAZAS_MAX = 16;
        private static int plazas;

        public Microbus(GamaCoche gama, Carburante carburante, String matricula, int plazas) throws AlquilerVehiculosException {
            super(gama, carburante, matricula);

            if (!validarPlazas(plazas)) {
                throw new AlquilerVehiculosException("Error el peso . ");
            }
            this.plazas = plazas;
        }

        public double getPrecioPlazas() {
            return super.getPrecioBase() + (5 * plazas);
        }

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
