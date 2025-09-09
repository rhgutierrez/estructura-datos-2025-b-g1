/*
Autor: Ronald Humberto Gutierrez Garzon
Fecha: 08/09/2025
 */
class ListaSimple {
    private Nodo cabeza;
    private Nodo cola;

    public void insertarInicio(int valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        if (cola == null) cola = nuevo;
    }

    public void insertarFin(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = cola = nuevo;
            return;
        }
        cola.siguiente = nuevo;
        cola = nuevo;
    }

    public void insertarEn(int posicion, int valor) {
        if (posicion < 0) throw new IndexOutOfBoundsException("Posición inválida");
        if (posicion == 0) { insertarInicio(valor); return; }
        Nodo actual = cabeza;
        for (int i = 0; i < posicion - 1; i++) {
            if (actual == null) throw new IndexOutOfBoundsException("Posición fuera de rango");
            actual = actual.siguiente;
        }
        if (actual == null) throw new IndexOutOfBoundsException("Posición fuera de rango");
        Nodo nuevo = new Nodo(valor);
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        if (nuevo.siguiente == null) cola = nuevo;
    }

    public int eliminarInicio() {
        if (cabeza == null) throw new IllegalStateException("Lista vacía");
        int val = cabeza.dato;
        cabeza = cabeza.siguiente;
        if (cabeza == null) cola = null;
        return val;
    }

    public int eliminarFin() {
        if (cabeza == null) throw new IllegalStateException("Lista vacía");
        if (cabeza == cola) {
            int v = cabeza.dato;
            cabeza = cola = null;
            return v;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != cola) {
            actual = actual.siguiente;
        }
        int v = cola.dato;
        actual.siguiente = null;
        cola = actual;
        return v;
    }

    public int eliminarEn(int posicion) {
        if (posicion < 0) throw new IndexOutOfBoundsException("Posición inválida");
        if (posicion == 0) return eliminarInicio();
        Nodo actual = cabeza;
        for (int i = 0; i < posicion - 1; i++) {
            if (actual == null || actual.siguiente == null)
                throw new IndexOutOfBoundsException("Posición fuera de rango");
            actual = actual.siguiente;
        }
        if (actual.siguiente == null) throw new IndexOutOfBoundsException("Posición fuera de rango");
        int v = actual.siguiente.dato;
        if (actual.siguiente == cola) cola = actual;
        actual.siguiente = actual.siguiente.siguiente;
        return v;
    }

    public void mostrar() {
        Nodo aux = cabeza;
        StringBuilder sb = new StringBuilder();
        while (aux != null) {
            sb.append(aux.dato);
            if (aux.siguiente != null) sb.append(" -> ");
            aux = aux.siguiente;
        }
        System.out.println(sb.toString());
    }
}
