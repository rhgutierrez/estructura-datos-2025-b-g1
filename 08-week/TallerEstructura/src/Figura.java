public interface Figura {
    double calcularArea();
    double calcularPerimetro();
    String getNombre();
    String getColor();

    default void mostrarInformacion() {
        System.out.println("=== " + getNombre().toUpperCase() + " ===");
        System.out.println("Color: " + getColor());
        System.out.printf("Área: %.4f%n", calcularArea());
        System.out.printf("Perímetro: %.4f%n", calcularPerimetro());
        System.out.println();
    }
}
