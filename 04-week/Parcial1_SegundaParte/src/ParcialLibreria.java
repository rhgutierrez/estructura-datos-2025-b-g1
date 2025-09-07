import java.time.*;
import java.util.*;

public class ParcialLibreria {

    private static final int MAX_LIBROS = 100;
    private static final int SUCURSALES = 3;

    private final Libro[] catalogo = new Libro[MAX_LIBROS];
    private final int[][] disponibilidad = new int[MAX_LIBROS][SUCURSALES];
    private int usados = 0;

    private NodoPrestamo prestamosHead = null;
    private NodoHistoria histHead = null, histTail = null;

    private final Scanner sc = new Scanner(System.in);

    static class Libro {
        int codigo;
        String titulo;
        String autor;
        int stock;
        boolean activo;

        Libro(int codigo, String titulo, String autor, int stock) {
            this.codigo = codigo;
            this.titulo = titulo;
            this.autor = autor;
            this.stock = stock;
            this.activo = true;
        }
    }

    static class NodoPrestamo {
        int codigoLibro;
        String usuario;
        LocalDate fecha;
        boolean devuelto;
        NodoPrestamo next;

        NodoPrestamo(int codigoLibro, String usuario) {
            this.codigoLibro = codigoLibro;
            this.usuario = usuario;
            this.fecha = LocalDate.now();
            this.devuelto = false;
        }
    }

    static class NodoHistoria {
        String tipo;
        String detalle;
        LocalDateTime ts;
        NodoHistoria prev, next;
        NodoHistoria(String tipo, String detalle) {
            this.tipo = tipo;
            this.detalle = detalle;
            this.ts = LocalDateTime.now();
        }
    }

    // ====== HISTORIAL (lista doble) ======
    private void addHist(String tipo, String detalle) {
        NodoHistoria n = new NodoHistoria(tipo, detalle);
        if (histHead == null) {
            histHead = histTail = n;
        } else {
            histTail.next = n;
            n.prev = histTail;
            histTail = n;
        }
    }

    // ====== CATALOGO (arrays) ======
    private int indexPorCodigo(int codigo) {
        for (int i = 0; i < usados; i++) {
            if (catalogo[i] != null && catalogo[i].codigo == codigo) return i;
        }
        return -1;
    }

    private void cargarLibro() {
        System.out.print("Codigo: ");
        int codigo = leerEntero();
        if (indexPorCodigo(codigo) != -1) {
            System.out.println("Ya existe un libro con ese codigo.");
            return;
        }
        if (usados >= MAX_LIBROS) {
            System.out.println("Catalogo lleno.");
            return;
        }
        System.out.print("Titulo: ");
        String titulo = sc.nextLine().trim();
        System.out.print("Autor: ");
        String autor = sc.nextLine().trim();
        System.out.print("Stock inicial: ");
        int stock = leerEntero();
        if (stock < 0) stock = 0;

        Libro l = new Libro(codigo, titulo, autor, stock);
        catalogo[usados] = l;
        disponibilidad[usados][0] = stock;
        usados++;

        addHist("ALTA", "Alta libro " + codigo + " - " + titulo);
        System.out.println("Libro cargado.");
    }

    private void eliminarLibro() {
        System.out.print("Codigo: ");
        int codigo = leerEntero();
        int idx = indexPorCodigo(codigo);
        if (idx == -1) {
            System.out.println("No existe.");
            return;
        }
        catalogo[idx].activo = false;
        catalogo[idx].stock = 0;
        Arrays.fill(disponibilidad[idx], 0);
        addHist("BAJA", "Baja logica libro " + codigo);
        System.out.println("Baja logica realizada.");
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Buscar: ");
        String q = sc.nextLine().toLowerCase();
        boolean alguno = false;
        for (int i = 0; i < usados; i++) {
            Libro l = catalogo[i];
            if (l != null && l.titulo.toLowerCase().contains(q)) {
                imprimirLibro(i);
                alguno = true;
            }
        }
        if (!alguno) System.out.println("Sin coincidencias.");
    }

    private void imprimirLibro(int idx) {
        Libro l = catalogo[idx];
        System.out.println("Cod: " + l.codigo + " | '" + l.titulo + "' | " + l.autor
                + " | stock=" + l.stock + " | activo=" + l.activo);
        System.out.print("Sucursales: ");
        for (int s = 0; s < SUCURSALES; s++) {
            System.out.print("S" + s + "=" + disponibilidad[idx][s] + " ");
        }
        System.out.println();
    }

    private void listarCatalogo() {
        if (usados == 0) {
            System.out.println("Catalogo vacio.");
            return;
        }
        for (int i = 0; i < usados; i++) {
            if (catalogo[i] != null) imprimirLibro(i);
        }
    }

    private void actualizarDisponibilidad() {
        System.out.print("Codigo: ");
        int codigo = leerEntero();
        int idx = indexPorCodigo(codigo);
        if (idx == -1) {
            System.out.println("No existe.");
            return;
        }
        System.out.print("Sucursal [0.." + (SUCURSALES - 1) + "]: ");
        int s = leerEntero();
        if (s < 0 || s >= SUCURSALES) {
            System.out.println("Sucursal invalida.");
            return;
        }
        System.out.print("Nuevo valor: ");
        int val = leerEntero();
        if (val < 0) val = 0;

        disponibilidad[idx][s] = val;
        int total = 0;
        for (int j = 0; j < SUCURSALES; j++) total += disponibilidad[idx][j];
        catalogo[idx].stock = total;

        System.out.println("Stock actualizado (total=" + total + ")");
    }

    // ====== PRESTAMOS (lista simple) ======
    private void prestarLibro() {
        System.out.print("Codigo: ");
        int codigo = leerEntero();
        int idx = indexPorCodigo(codigo);
        if (idx == -1) {
            System.out.println("No existe.");
            return;
        }
        Libro l = catalogo[idx];
        if (!l.activo) {
            System.out.println("Libro dado de baja.");
            return;
        }
        if (l.stock <= 0) {
            System.out.println("Sin stock.");
            return;
        }

        System.out.print("Usuario: ");
        String usuario = sc.nextLine().trim();

        l.stock--;
        if (disponibilidad[idx][0] > 0) disponibilidad[idx][0]--;

        NodoPrestamo n = new NodoPrestamo(codigo, usuario);
        n.next = prestamosHead;
        prestamosHead = n;

        addHist("PRESTAMO", "cod=" + codigo + ", usuario=" + usuario);
        System.out.println("Prestamo registrado.");
    }

    private void devolverLibro() {
        System.out.print("Codigo: ");
        int codigo = leerEntero();
        System.out.print("Usuario: ");
        String usuario = sc.nextLine().trim();

        NodoPrestamo prev = null, cur = prestamosHead;
        while (cur != null) {
            if (cur.codigoLibro == codigo && cur.usuario.equals(usuario) && !cur.devuelto) {
                cur.devuelto = true;
                if (prev == null) prestamosHead = cur.next;
                else prev.next = cur.next;

                int idx = indexPorCodigo(codigo);
                if (idx != -1) {
                    catalogo[idx].stock++;
                    disponibilidad[idx][0]++;
                }
                addHist("DEVOLUCION", "cod=" + codigo + ", usuario=" + usuario);
                System.out.println("Devolucion registrada.");
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        System.out.println("No se encontro prestamo activo.");
    }

    private void listarPrestamos() {
        if (prestamosHead == null) {
            System.out.println("Sin prestamos.");
            return;
        }
        NodoPrestamo p = prestamosHead;
        while (p != null) {
            if (!p.devuelto) {
                System.out.println("Libro " + p.codigoLibro + " | usuario=" + p.usuario + " | fecha=" + p.fecha);
            }
            p = p.next;
        }
    }

    // ====== HISTORIAL ======
    private void listarHistorialAdelante() {
        if (histHead == null) {
            System.out.println("Vacio.");
            return;
        }
        NodoHistoria h = histHead;
        while (h != null) {
             h = h.next;
        }
    }

    private void listarHistorialAtras() {
        if (histTail == null) {
            System.out.println("Vacio.");
            return;
        }
        NodoHistoria h = histTail;
        while (h != null) {
            System.out.println("[" + h.ts + "] " + h.tipo + " - " + h.detalle);
            h = h.prev;
        }
    }

    // ====== MENÚ ======
    private void menu() {
        while (true) {
            System.out.println("\n===== Biblioteca =====");
            System.out.println("1) Alta libro");
            System.out.println("2) Baja logica");
            System.out.println("3) Buscar por titulo");
            System.out.println("4) Listar catalogo");
            System.out.println("5) Actualizar disponibilidad");
            System.out.println("6) Prestar libro");
            System.out.println("7) Devolver libro");
            System.out.println("8) Listar prestamos");
            System.out.println("9) Historial adelante");
            System.out.println("10) Historial atras");
            System.out.println("0) Salir");
            System.out.print("Opcion: ");

            int op = leerEntero();
            switch (op) {
                case 1 -> cargarLibro();
                case 2 -> eliminarLibro();
                case 3 -> buscarLibroPorTitulo();
                case 4 -> listarCatalogo();
                case 5 -> actualizarDisponibilidad();
                case 6 -> prestarLibro();
                case 7 -> devolverLibro();
                case 8 -> listarPrestamos();
                case 9 -> listarHistorialAdelante();
                case 10 -> listarHistorialAtras();
                case 0 -> { System.out.println("Fin"); return; }
                default -> System.out.println("Opcion invalida.");
            }
        }
    }

    // ====== LECTURA SEGURA DE ENTEROS ======
    private int leerEntero() {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un entero: ");
            }
        }
    }

    // ====== MAIN ======
    public static void main(String[] args) {
        new ParcialLibreria().menu();
    }
}
