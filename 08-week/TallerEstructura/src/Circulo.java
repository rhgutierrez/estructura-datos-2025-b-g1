public class Circulo extends FiguraBase {
    private final double radio;

    public Circulo(double radio, String color) {
        super(color);
        if (radio <= 0) throw new IllegalArgumentException("El radio debe ser positivo.");
        this.radio = radio;
    }

    public double getRadio() { return radio; }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public String getNombre() {
        return "Círculo (radio: " + radio + ")";
    }
}
