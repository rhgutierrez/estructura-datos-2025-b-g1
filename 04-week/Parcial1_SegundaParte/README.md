# ParcialLibreria

Sistema de consola para administrar un catálogo de biblioteca con operaciones básicas de **altas/bajas**, **búsqueda**, **préstamos/devoluciones** y **historial**. Implementado en **Java 17** usando **arreglos y listas enlazadas**.

---

## 📌 Descripción corta

* **Catálogo de libros** (máx. 100).
* **Disponibilidad por sucursal** en matriz 3x100.
* **Préstamos activos** en lista simple.
* **Historial de eventos** en lista doble (altas, bajas, préstamos, devoluciones).

---

## 🧠 Razones de elección y complejidad

* **Arreglo de libros** → acceso directo rápido, búsqueda **O(n)**.
* **Matriz de disponibilidad** → actualización/consulta **O(1)**.
* **Lista simple de préstamos** → inserción **O(1)**, devolución **O(k)**.
* **Lista doble para historial** → inserción **O(1)**, recorrido en ambas direcciones **O(h)**.

> Adecuado para un ejercicio académico con pocos datos (≤100 libros).

---

## ▶️ Cómo compilar/ejecutar

### Requisitos

* **Java 17** instalado.

### Compilar

```bash
javac ParcialLibreria.java
```

### Ejecutar

```bash
java ParcialLibreria
```

Mostrará un menú con opciones:

```
1) Alta libro
2) Baja logica
...
10) Historial atras
0) Salir
```

---

## ⚠️ Limitaciones

* Catálogo fijo (`MAX_LIBROS=100`).
* No hay persistencia (datos en memoria).
* `listarHistorialAdelante()` no imprime (se deja vacío tal cual el código).
