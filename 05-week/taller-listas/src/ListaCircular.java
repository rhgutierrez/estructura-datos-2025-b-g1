/*
Autor: Ronald Humberto Gutierrez Garzon
Fecha: 08/09/2025
 */
class NodoC {
    int dato;
    NodoC next;
    NodoC(int d) { dato = d; }
}

class ListaCircular {
    private NodoC cola; // referencia a último; cola.next es la cabeza

    public void insertarInicio(int valor) {
        NodoC n = new NodoC(valor);
        if (cola == null) {
            cola = n;
            n.next = n;
        } else {
            n.next = cola.next;
            cola.next = n;
        }
    }

    public void insertarFin(int valor) {
        insertarInicio(valor);
        cola = cola.next; // el recién insertado pasa a ser cola
    }

    public int eliminarInicio() {
        if (cola == null) throw new IllegalStateException("Lista vacía");
        NodoC head = cola.next;
        int v = head.dato;
        if (head == cola) {
            cola = null;
        } else {
            cola.next = head.next;
        }
        return v;
    }

    public int eliminarFin() {
        if (cola == null) throw new IllegalStateException("Lista vacía");
        NodoC actual = cola.next;
        while (actual.next != cola) actual = actual.next;
        int v = cola.dato;
        actual.next = cola.next;
        cola = actual;
        return v;
    }

    // Inserción en posición (0 = head), O(n)
    public void insertarEn(int posicion, int valor) {
        if (posicion < 0) throw new IndexOutOfBoundsException("Posición inválida");
        if (cola == null || posicion == 0) {
            insertarInicio(valor);
            return;
        }
        NodoC prev = cola.next;
        for (int i = 0; i < posicion - 1; i++) {
            if (prev == cola) break; // si excede, inserta al final
            prev = prev.next;
        }
        NodoC n = new NodoC(valor);
        n.next = prev.next;
        prev.next = n;
        if (prev == cola) cola = n;
    }

    public int eliminarEn(int posicion) {
        if (posicion < 0 || cola == null) throw new IndexOutOfBoundsException("Posición inválida o lista vacía");
        if (posicion == 0) return eliminarInicio();
        NodoC prev = cola.next;
        for (int i = 0; i < posicion - 1; i++) {
            if (prev.next == cola.next) throw new IndexOutOfBoundsException("Posición fuera de rango");
            prev = prev.next;
        }
        NodoC target = prev.next;
        if (target == cola) cola = prev;
        if (target == cola.next) return eliminarInicio();
        prev.next = target.next;
        return target.dato;
    }
}