import java.util.Arrays;
import java.util.Scanner;

public class Estado{

    private static Scanner sc = new Scanner(System.in);

    public enum TipoEstadoCivil{
        SOLTERO,CASADO, VIUDO,DIVORCIADO
    }



    public static void main(String[] args) {
        TipoEstadoCivil estado = null;
        String cadena;
        System.out.println("Introduce estado civil:" + Arrays.toString(TipoEstadoCivil.values()));
        cadena=sc.nextLine().toUpperCase();
        estado = Estado.valueOf(cadena);

    }

    private static TipoEstadoCivil valueOf(String cadena) {
        return TipoEstadoCivil.valueOf(cadena);
    }
}
