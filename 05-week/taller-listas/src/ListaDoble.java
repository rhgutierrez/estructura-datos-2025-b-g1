/*
Autor: Ronald Humberto Gutierrez Garzon
Fecha: 08/09/2025
 */
class NodoD {
    int dato;
    NodoD prev, next;
    NodoD(int d) { dato = d; }
}

class ListaDoble {
    private NodoD cabeza, cola;

    public void insertarInicio(int valor) {
        NodoD n = new NodoD(valor);
        n.next = cabeza;
        if (cabeza != null) cabeza.prev = n;
        cabeza = n;
        if (cola == null) cola = n;
    }

    public void insertarFin(int valor) {
        NodoD n = new NodoD(valor);
        if (cola == null) {
            cabeza = cola = n;
            return;
        }
        cola.next = n;
        n.prev = cola;
        cola = n;
    }

    public int eliminarInicio() {
        if (cabeza == null) throw new IllegalStateException("Lista vacía");
        int v = cabeza.dato;
        cabeza = cabeza.next;
        if (cabeza != null) cabeza.prev = null; else cola = null;
        return v;
    }

    public int eliminarFin() {
        if (cola == null) throw new IllegalStateException("Lista vacía");
        int v = cola.dato;
        cola = cola.prev;
        if (cola != null) cola.next = null; else cabeza = null;
        return v;
    }
}
