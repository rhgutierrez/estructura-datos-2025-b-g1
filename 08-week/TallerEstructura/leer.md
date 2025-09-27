# 📄 Evidencias – Taller de Figuras Geométricas en Java

## 1. Explicación de la solución
Se implementaron las clases **`Circulo`**, **`Rectangulo`** y **`Triangulo`**, todas heredando de una abstracción común (**`Figura`**) que asegura un contrato con los siguientes métodos:
- `calcularArea()`
- `calcularPerimetro()`
- `getNombre()`
- `getColor()`

Además, se utilizó un **`ArrayList<Figura>`** para almacenar múltiples objetos de diferentes clases. Esto permite demostrar **polimorfismo**, ya que el programa trata a todas las figuras como instancias de `Figura`, aunque cada una tenga cálculos específicos.

Finalmente, el programa recorre la lista e imprime la información de cada figura, mostrando que es posible manipular colecciones heterogéneas en Java.

---

## 2. Creación de objetos e inserción en ArrayList

Ejemplo tomado de `Main.java`:

```java
List<Figura> figuras = new ArrayList<>();

// CÍRCULOS
figuras.add(new Circulo(1.8, "Magenta"));
figuras.add(new Circulo(9.2, "Cian"));

// RECTÁNGULOS
figuras.add(new Rectangulo(2.3, 7.5, "Gris"));
figuras.add(new Rectangulo(8.0, 8.0, "Naranja"));

// TRIÁNGULOS
figuras.add(new Triangulo(5.0, 5.0, 6.0, "Violeta"));
figuras.add(new Triangulo(10.0, 6.0, 8.0, "Café"));
