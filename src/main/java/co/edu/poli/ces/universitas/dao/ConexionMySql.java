package co.edu.poli.ces.universitas.dao;

public class ConexionMySql {
    //Modificadores, el nivel de acceso que va a tener un atributo, private, public, protected
    // Tipos de datos Nativos: int, float, double Objetual: String
    // static, ambitos de acceso
    private static final int N = 5;
    private int number1;
    private float number2;
    protected boolean isNumber;
    String name;
    String lastname;

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
    ConexionMySql(String name){
        isNumber = false;
        // this: la referencia a si misma, es la referencia al mismo objeto cuando se instancia
        this.name = name;


    }
    /* Este constrcutor sale error, porque es igual al anterior, no importanque los parametros sean diferentes (name, lastname), son del mismo tipo (String)
    Por lo tanto, Java los considera igual
    ConexionMySql(String lastname){

    }*/

    ConexionMySql(boolean isNumber){
        this.isNumber = isNumber;
    }
    ConexionMySql(String name, int N ){
        this.name = name;
        this.numbers = new int[N];
    }

    public void initArray(){
        this.numbers = new int[this.N];
        for (int i = 0;i<N;i++){
            // (int) parse, que pasa de double a int
            this.numbers[i] = (int) (Math.random()*20+1);
        }

    }
    public void mostraVector(){
        int tam = this.numbers.length;
        for (int i = 0;i<tam;i++){
            System.out.println(this.numbers[i]);
        }

    }

    // Varieble tipo objeto, cuando accedo a metodos

    // Hay dos ambitos deferentes, cuando la clase no está en memoria y cuando si está en memoria
    /*
    public static void main(String[] args) {
        System.out.println(ConexionMySql); // Podemos ver si ponemos el curso en ConexionMySql, aperece N es igual a 20
                                           // La clase no está en memoria, pero puedo acceder a este atributo porque es estatica
        ConexionMySql.N = 9; Esto da error porque N es final, es decir, constante
        //Variable des tipo objeto
        ConexionMySql con = new ConexionMySql(); //Le estoy diciendo a java que levante la clase en memoria
        //Cuando levantamos este objeto en memoria, con el new ConexionMySql, llamamos al constructor
        System.out.println(con); representación de la dirección donde se almacena los objetos
        // Cuando ejecutamos el proyecto y finaliza, el java garbaje collected quita la información de la ram
    }*/
    public static void main(String[] args) {
        ConexionMySql con = new ConexionMySql();
        con.initArray();
        con.mostraVector();
    }




}
