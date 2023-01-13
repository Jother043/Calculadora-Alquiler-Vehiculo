package EntradaSalida;
import java.lang.Exception;

public class excepciones {
    public class NegativeNumberException extends Exception{
        public NegativeNumberException() {
            super("No se permiten numeros negativos.");
        }
    }
}
