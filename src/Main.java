import EntradaSalida.Lectora;

import java.util.Arrays;


public class Main {
    private static final int MAX_VEHICULOS = 200;
    //Le pongo final para que sea inmutable, ya que main solo lanza y no necesito un constructor.
    private static final Vehiculo[] listaVehiculos = new Vehiculo[MAX_VEHICULOS];

    public static void main(String[] args) {
        int opciones;
        do {
            System.out.println(menu());
            opciones = Lectora.leerEnteroDeRango("Introduce la opción deseada. ", 5, 0);
            opciones(opciones);
        } while (opciones != 4);
    }

    public static void opciones(int opciones) {
        switch (opciones) {
            case 1:
                //Llamada al método darDealtaVehiculo.
                try {
                    darDealtaVehiculo();
                } catch (AlquilerVehiculosException e) {
                    System.out.println("Error al ingresar vehiculo, algo falló. ");
                    e.printStackTrace();
                }
                break;
            case 2:
                //Llamada a método calcularPrecioVehiculo.
                try {
                    calcularPrecioVehiculo();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al calcular precio del vehiculo, algo falló. ");
                    e.printStackTrace();
                }
                break;
            case 3:
                imprimir();
                break;
        }
    }

    public static void darDealtaVehiculo() throws AlquilerVehiculosException {

        boolean correcto = false;
        String tipoVehiculo = "";
        /*
        Pedimos al usuario que seleccione un tipo de vehículo, de los que hemos cuesto en el switch.
         */
        while (!correcto) {
            tipoVehiculo = Lectora.solicitarCadenaMinus("Indica el tipo de vehiculo que quieres dar " +
                    "de alta [ coche, microbús, furgoneta ]");
            switch (tipoVehiculo) {
                case "coche":
                case "microbus":
                case "furgoneta":
                    correcto = true;
            }
        }

        /*
        Pedimos al usuario que introduzca una matricula.
         */
        correcto = false;
        String matricula = "";
        while (!correcto) {
            matricula = Lectora.solicitarCadenaMayus("Introduce la matricula del vehiculo. ");
            if (!matricula.trim().isEmpty()) {
                correcto = true;
            }
        }

        /*
        Pedimos al usuario que ingrese la gama de vehiculo.
         */
        correcto = false;
        Vehiculo.GamaCoche gamaVehiculo = null;
        String gama;
        while (!correcto) {
            gama = Lectora.solicitarCadenaMayus("Introduce la gama de vehiculo a elegir " +
                    Arrays.toString(Vehiculo.GamaCoche.values()));
            /*
            Creamos el try catch con IllegalArgumentException, para comprobar que no hemos metido un argumento inválido
            esta clase de excepción está predefinida en Java y nos lo indica en la documentación del valueOf.
             */
            try {
                gamaVehiculo = Vehiculo.GamaCoche.valueOf(gama);
                correcto = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error el valor introducido es incorrecto");
            }
        }
        /*
         Pedimos al usuario que introduzca el tipo de carburante deseado.
         */
        correcto = false;
        Vehiculo.Carburante tipoCarburante = null;
        String carburante;
        /*
        Mientras no sea correcto pedimos al usuario el tipo de carburante, esto lo evaluamos lanzando una excepción
        IllegalArgumentExceptio, quiere decir que si el valor pasado mediante el valueOf no está en el enum entonces
        no es un argumento válido como puede ser un espacio u otro nombre que no sea los contemplados.
         */
        while (!correcto) {
            carburante = Lectora.solicitarCadenaMayus("Introduce el tipo de carburante del vehiculo. " +
                    Arrays.toString(Vehiculo.Carburante.values()));
            try {
                tipoCarburante = Vehiculo.Carburante.valueOf(carburante);
                correcto = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error el valor introducido es incorrecto");
            }

        }
        /*
        Creamos un switch que dependiendo el tipoVehiculo que le gue le hemos pasado hará unas cosas en cada caso.
        Caso coche: hacemos un new de Vehículo, pero como esta es una clase abstracta tenemos que poner Vehículo v = new
        Coche, si no nos daria un error al generarlo y tampoco nos estaríamos refiriendo a nada, si no recuerdo mal se llamaba
        upCasting
        Caso microbús: nos pide el número de plazas que tiene que tener el bus una vez hemos verificado que es correcto
        el número de plazas vamos a generar el microbús y lo añadiremos a la lista.
        Caso de furgoneta: Nos indica que introduzcamos el precio máximo una vez introducido verificamos que es correcto
        y añadimos el vehículo en cuestión.
         */
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
                break;
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
                break;
        }
    }

    /**
     * Este método se encarga de calcular el precio del alquiler por dia de los vehículos existente en la  lisita.
     */
    public static void calcularPrecioVehiculo() {

        boolean correcto = false;
        String matricula = "";
        //Si la matricula es correcta el while se acaba.
        while (!correcto) {
            matricula = Lectora.solicitarCadenaMayus("Introduce la matricula del vehiculo. ");
            //Verificamos que la matrícula sea correcta y exista dentro de la lista de vehículos.
            try {
                vehiculoBuscado(matricula);
                correcto = true;
            } catch (AlquilerVehiculosException e) {
                System.out.println("Introduce un vehiculo existente. ");
            }
        }
        //Indicamos el número de dias que va a alquilar el coche.
        int numDias = 0;
        numDias = Lectora.leerEnteroPositivo("Introduce el número de dias del alquiler. ");
        try {
            System.out.println(vehiculoBuscado(matricula).getPrecioBase() + vehiculoBuscado(matricula).getPrecioPorDia(numDias));
        } catch (AlquilerVehiculosException e) {
            System.out.println("Error al imprimir el costo del alquiler del coche seleccionado. ");
        }
    }

    /**
     * Método al que utilizamos para añadir un vehículo a la lista
     *
     * @param vehiculo
     */
    public static void addVehiculo(Vehiculo vehiculo) {

        boolean espacio = false;
        for (int i = 0; i < listaVehiculos.length && !espacio; i++) {
            if (listaVehiculos[i] == null) {
                espacio = true;
                listaVehiculos[i] = vehiculo;
            }
        }
    }

    //Imprimimos la lista.
    public static void imprimir() {
        String vehiculo = "";
        for (int i = 0; i < listaVehiculos.length; i++) {
            if (!(listaVehiculos[i] == null)) {
                System.out.println(listaVehiculos[i]);
            }
        }
    }

    /**
     * Método que nos busca el vehículo por matrícula.
     * @param matricula
     * @return
     * @throws AlquilerVehiculosException
     */
    public static Vehiculo vehiculoBuscado(String matricula) throws AlquilerVehiculosException {
        boolean encontrado = false;
        Vehiculo vehiculoPrecio = null;
        /*
        Recorremos el array de vehículos y buscamos que el vehículo "i" su matrícula es igual a la introducida
        procedemos al siguiente paso, si no lanzamos una excepción.
         */
        for (int i = 0; i < listaVehiculos.length && !encontrado; i++) {
            if (listaVehiculos[i].getMatricula().equals(matricula)) {
                vehiculoPrecio = listaVehiculos[i];
                encontrado = true;
            } else {
                throw new AlquilerVehiculosException("Introduce un vehiculo existente.");
            }
        }
        return vehiculoPrecio;
    }

    public static String menu() {
        StringBuilder string = new StringBuilder();
        string.append("Elige una opción: ").append("\n");
        string.append("1. Alta vehiculo. ").append("\n");
        string.append("2. Cálculo del precio del alquiler. ").append("\n");
        string.append("3. Imprimir lista de vehículos. ").append("\n");
        string.append("4. Salir. ");
        return string.toString();
    }
}