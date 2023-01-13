package EntradaSalida;

public class PruebaException {
    public static void main(String[] args) {

//        double num = Lectora.leerDouble("Introduce un double");
//        System.out.println(num);
//        char caracter = Lectora.leerCaracter("Introduce un caracter");
//        System.out.println(caracter);
//
//        char sn = Lectora.leerCaracterSN("Introduce un caracter s o n");
//        System.out.println(sn);
//        String mensaje = Lectora.solicitarCadena("Introduce una cadena");
//        System.out.println(mensaje);

        String[] opciones = {"Rojo", "azul", "verde"};
        String cadena = Lectora.leerOpciones("Introduce una opcion", opciones);
        System.out.println(cadena);

    }
}