public abstract class FiguraBase implements Figura {
    protected final String color;

    protected FiguraBase(String color) {
        this.color = (color == null || color.isBlank()) ? "Sin color" : color.trim();
    }

    @Override
    public String getColor() {
        return color;
    }
}
