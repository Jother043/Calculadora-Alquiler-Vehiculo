public class Microbus extends Vehiculo{


        private static final int PLAZAS_MAX = 16;
        private int plazas;

        public Microbus(GamaCoche gama, Carburante carburante, String matricula, int plazas) throws AlquilerVehiculosException {
            super(gama, carburante, matricula);

            if (validarPlazas()) {
                throw new AlquilerVehiculosException("Error el peso . ");
            }
            this.plazas = plazas;
        }

        public double getPrecioPlazas() {
            return super.getPrecioBase() + (5 * plazas);
        }

        public boolean validarPlazas() {
            return plazas > 0 && plazas <= PLAZAS_MAX;
        }

}
