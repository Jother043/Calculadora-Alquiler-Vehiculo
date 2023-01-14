import EntradaSalida.Lectora;

import java.util.Arrays;
import java.util.Locale;

public class Main {
    private static final int MAX_VEHICULOS = 200;
    //Le pongo final para que sea inmutable, ya que main solo lanza y no necesito un constructor.
    private static final Vehiculo[] listaVehiculos = new Vehiculo[MAX_VEHICULOS];

    public static void main(String[] args) {
        int opciones = 0;
        do {
            System.out.println(menu());
            opciones = Lectora.leerEnteroDeRango("Introduce la opción deseada. ", 4, 0);
            opciones(opciones);
        } while (opciones != 3);
    }

    public static void opciones(int opciones) {
        switch (opciones) {
            case 1:
                try {
                    darDealtaVehiculo();
                } catch (AlquilerVehiculosException e) {
                    System.out.println("Error al ingresar vehiculo, algo falló. ");
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    calcularPrecioVehiculo();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al calcular precio del vehiculo, algo falló. ");
                    e.printStackTrace();
                }
                break;
            case 3:
        }
    }

    public static void darDealtaVehiculo() throws AlquilerVehiculosException {

        boolean correcto = false;
        String tipoVehiculo = "";
        while (!correcto) {
            tipoVehiculo = Lectora.solicitarCadenaMinus("Indica el tipo de vehiculo que quieres dar " +
                    "de alta [ coche, microbús, furgoneta ]");
            tipoVehiculo.toUpperCase();
            switch (tipoVehiculo) {
                case "coche":
                case "microbus":
                case "furgoneta":
                    correcto = true;
            }
        }

        correcto = false;
        String matricula = "";
        while (!correcto) {
            matricula = Lectora.solicitarCadenaMayus("Introduce la matricula del vehiculo. ");
            if (matricula.length() > 0) {
                correcto = true;
            }
        }

        /*
        Pedimos al usuario que ingrese la gama de vehiculo.
         */
        correcto = false;
        Vehiculo.GamaCoche gamaVehiculo = null;
        String gama = "";
        while (!correcto) {
            gama = Lectora.solicitarCadenaMayus("Introduce la gama de vehiculo a elegir " +
                    Arrays.toString(Vehiculo.GamaCoche.values()));
            /*
            Creamos el try catch con IllegalArgumentException, para comprobar que no hemos metido un argumento inválido
            esta clase de excepción está predefinida en Java.
             */
            try {
                gamaVehiculo = Vehiculo.GamaCoche.valueOf(gama);
                correcto = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error el valor introducido es incorrecto");
            }
        }

        correcto = false;
        Vehiculo.Carburante tipoCarburante = null;
        String carburante;
        while (!correcto) {
            carburante = Lectora.solicitarCadenaMayus("Introduce el tipo de carburante del vehiculo. " +
                    Arrays.toString(Vehiculo.Carburante.values()));
            try {
                tipoCarburante = Vehiculo.Carburante.valueOf(carburante);
                correcto = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error el valor introducido es incorrecto");
            }

            switch (tipoVehiculo) {
                case "coche":
                    Vehiculo coche = new Coche(gamaVehiculo, tipoCarburante, matricula);
                    addVehiculo(coche);
                    break;
                case "microbus":
                    correcto = false;
                    int numPlazas = 0;
                    while (!correcto) {
                        numPlazas = Lectora.leerEnteroPositivo("Introduce el número de plazas del micro bus el" +
                                "el maximo es 16.");
                        if (Microbus.validarPlazas(numPlazas)) {
                            correcto = true;
                        }
                    }
                    Vehiculo microBus = new Microbus(gamaVehiculo, tipoCarburante, matricula, numPlazas);
                    addVehiculo(microBus);
                case "furgoneta":
                    correcto = false;
                    int pma = 0;
                    while (!correcto) {
                        pma = Lectora.leerEnteroPositivo("Introduce el peso máximo autorizado siendo 3500 el máximo.");
                        if (Furgoneta.validarPma(pma)) {
                            correcto = true;
                        }
                    }
                    Vehiculo furgoneta = new Furgoneta(gamaVehiculo, tipoCarburante, matricula, pma);
                    addVehiculo(furgoneta);
            }
        }
    }

    public static void calcularPrecioVehiculo() {
        boolean correcto = false;
        String matricula = "";
        while (!correcto) {
            matricula = Lectora.solicitarCadenaMayus("Introduce la matricula del vehiculo. ");
            if (matricula.length() > 0) {
                correcto = true;
            }
        }
        vehiculoBuscado(matricula);
        correcto = false;
        int numDias = 0;
        while (!correcto) {
            numDias = Lectora.leerEnteroPositivo("Introduce la matricula del vehiculo. ");
            if (numDias > 0) {
                correcto = true;
            }
        }

        try {
            System.out.println(vehiculoBuscado(matricula).getPrecioPorDia(numDias));
        } catch (AlquilerVehiculosException e) {
            e.printStackTrace();
        }
    }

    public static void addVehiculo(Vehiculo vehiculo) {

        boolean espacio = false;
        for (int i = 0; i < listaVehiculos.length && !espacio; i++) {
            if (listaVehiculos[i] == null) {
                espacio = true;
                listaVehiculos[i] = vehiculo;
            }
        }
        for (int i = 0; i < listaVehiculos.length; i++) {
            System.out.println(listaVehiculos[i]);
        }
    }

    public static Vehiculo vehiculoBuscado(String matricula) {
        boolean encontrado = false;
        Vehiculo vehiculoPrecio = null;
        for (int i = 0; i < listaVehiculos.length && !encontrado; i++) {
            if (listaVehiculos[i].getMatricula().equals(matricula)) {
                vehiculoPrecio = listaVehiculos[i];
                encontrado = true;

            }
        }
        return vehiculoPrecio;
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