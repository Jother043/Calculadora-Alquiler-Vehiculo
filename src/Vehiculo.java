public abstract class  Vehiculo {

    private static final int MAX_VEHICULOS = 200;
    private String matricula;
    private String gamaVehiculo;
    private Vehiculo[] listaVehiculos;

    public Vehiculo(String matricula, String gamaVehiculo) {
        this.matricula = matricula;
        this.gamaVehiculo = gamaVehiculo;
        this.listaVehiculos = new Vehiculo[MAX_VEHICULOS];
    }


}
