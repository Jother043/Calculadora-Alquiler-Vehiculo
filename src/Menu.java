import EntradaSalida.Lectora;

public class Menu {
    private static final int MAX_VEHICULOS = 200;
    private Vehiculo[] listaVehiculos;

    public Menu(String matricula, Vehiculo[] listaVehiculos) {

        this.listaVehiculos = new Vehiculo[MAX_VEHICULOS];
    }

    public static void main(String[] args) {
        int opciones = 0;
        do {
            System.out.println(menu());
            opciones = Lectora.leerEnteroDeRango("Introduce la opción deseada. ", 4, 0);
        } while (opciones != 3);

    }

    public static String menu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Elige una opción: ").append("\n");
        sb.append("1. Alta vehiculo ").append("\n");
        sb.append("2. Cálculo del precio del alquiler ").append("\n");
        sb.append("3. Salir");


        return sb.toString();
    }


}
