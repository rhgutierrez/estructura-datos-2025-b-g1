/*
Autor: Ronald Humberto Gutierrez Garzon
Fecha: 08/09/2025
 */
public class Ejercicio5 {
    public static void main(String[] args) {
        ListaCircular lc = new ListaCircular();
        lc.insertarFin(10);
        lc.insertarFin(20);
        lc.insertarEn(1, 15);
        lc.eliminarEn(1);
        System.out.println("Inserción y eliminación en medio probadas.");
    }
}
