public class Coche extends Vehiculo {

    //Creamos dos constantes una para gasoil y otra para gasolina
    private static final double PRECIO_GASOIL = 2;
    private static final double PRECIO_GASOLINA = 3.5;
    public Coche(GamaCoche gama, Carburante carburante, String matricula) throws AlquilerVehiculosException {
        super(gama, carburante, matricula);
    }

    public double precioPorCarburante() throws AlquilerVehiculosException{

        double sumaPrecio = 0;

        switch (this.getCarburante()){
            case GASOIL:
                sumaPrecio = PRECIO_GASOIL;
                break;
            case GASOLINA:
                sumaPrecio = PRECIO_GASOLINA;
                break;
            default:
                throw new AlquilerVehiculosException("Tienes que elegir entre gasoil o gasolina. ");
        }

        return getPrecioBase() + sumaPrecio;
    }

}
