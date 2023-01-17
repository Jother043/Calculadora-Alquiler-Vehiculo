public class Coche extends Vehiculo {

    //Creamos dos constantes una para gasoil y otra para gasolina
    private static final double PRECIO_GASOIL = 2;
    private static final double PRECIO_GASOLINA = 3.5;

    public Coche(GamaCoche gama, Carburante carburante, String matricula) throws AlquilerVehiculosException {
        super(gama, carburante, matricula);
    }

    @Override
    public String toString() {
        return "Coche: " +
                super.toString();
    }
}
