import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Figura> figuras = new ArrayList<>();

        figuras.add(new Circulo(1.8, "Magenta"));
        figuras.add(new Circulo(9.2, "Cian"));

        figuras.add(new Rectangulo(2.3, 7.5, "Gris"));
        figuras.add(new Rectangulo(8.0, 8.0, "Naranja"));

        figuras.add(new Triangulo(5.0, 5.0, 6.0, "Violeta"));
        figuras.add(new Triangulo(10.0, 6.0, 8.0, "Café"));

        for (Figura f : figuras) {
            f.mostrarInformacion();
        }

        long total = figuras.size();
        long circs = figuras.stream().filter(f -> f instanceof Circulo).count();
        long rects = figuras.stream().filter(f -> f instanceof Rectangulo).count();
        long triangs = figuras.stream().filter(f -> f instanceof Triangulo).count();

        System.out.println("Resumen:");
        System.out.println("Total de figuras: " + total);
        System.out.println("Círculos: " + circs + " | Rectángulos: " + rects + " | Triángulos: " + triangs);
    }
}
