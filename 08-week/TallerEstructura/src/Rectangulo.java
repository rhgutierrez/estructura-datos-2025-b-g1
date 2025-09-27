public class Rectangulo extends FiguraBase {
    private final double base;
    private final double altura;

    public Rectangulo(double base, double altura, String color) {
        super(color);
        if (base <= 0 || altura <= 0) throw new IllegalArgumentException("Base y altura deben ser positivas.");
        this.base = base;
        this.altura = altura;
    }

    public double getBase() { return base; }
    public double getAltura() { return altura; }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (base + altura);
    }

    @Override
    public String getNombre() {
        return "Rectángulo (base: " + base + ", altura: " + altura + ")";
    }
}
