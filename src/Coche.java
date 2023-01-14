public class Coche extends Vehiculo {

    //Creamos dos constantes una para gasoil y otra para gasolina
    private static final double PRECIO_GASOIL = 2;
    private static final double PRECIO_GASOLINA = 3.5;
    public Coche(GamaCoche gama, Carburante carburante, String matricula) throws AlquilerVehiculosException {
        super(gama, carburante, matricula);
    }

    /**
     * Método sobreescrito que nos devuelve el precio de cada caso.
     * @return
     */
    @Override
    public double getPrecioBase(){
        double sumaPrecio = 0;

        switch (this.getCarburante()){
            case GASOIL:
                sumaPrecio = PRECIO_GASOIL;
                break;
            case GASOLINA:
                sumaPrecio = PRECIO_GASOLINA;
                break;
        }

        return  + sumaPrecio;
    }

    @Override
    public String toString() {
        return "Coche: " +
                super.toString() ;
    }
}
