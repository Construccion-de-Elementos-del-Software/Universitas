package co.edu.poli.ces.universitas.dao;

public class ConexionMySql {
    //Modificadores, el nivel de acceso que va a tener un atributo, private, public, protected
    // Tipos de datos Nativos: int, float, double Objetual: String
    private static final int N = 5;
    private int number1;
    private float number2;
    protected boolean isNumber;
    String name;

    private int[] numbers;

    // Constructor
    public ConexionMySql(){
        number1 = 12;
        number2 = 3;
        name= "Santiago";
        isNumber = false;
        numbers = new int[N];
        numbers[0] = 100;
        numbers[N-1] = 200;
    }

}
