/*
Autor: Ronald Humberto Gutierrez Garzon
Fecha: 08/09/2025
 */
public class Ejercicio3 {
    public static void main(String[] args) {
        ListaSimple ls = new ListaSimple();
        ls.insertarFin(5);
        ls.insertarFin(6);
        ls.insertarFin(7);
        int eliminado = ls.eliminarEn(1);
        System.out.println("Eliminado: " + eliminado);
        ls.mostrar();
    }
}
