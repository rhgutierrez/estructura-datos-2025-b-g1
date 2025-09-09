/*
Autor: Ronald Humberto Gutierrez Garzon
Fecha: 08/09/2025
 */
public class Ejercicio6 {
    public static void main(String[] args) {
        ListaSimple cola = new ListaSimple();
        cola.insertarFin(101);
        cola.insertarFin(102);
        cola.insertarFin(103);
        int atendido1 = cola.eliminarInicio();
        int atendido2 = cola.eliminarInicio();
        System.out.println("Atendidos: " + atendido1 + ", " + atendido2);
    }
}
