# ⚡ Complejidad de Operaciones en Listas Enlazadas

Este documento resume la complejidad temporal (en notación Big-O) de las operaciones de **inserción** y **eliminación** en listas enlazadas.

La notación O() indica cuántos pasos, en el peor caso, necesita la operación respecto al tamaño **n** de la lista.

---

## 🔹 Lista Simple
| Operación             | Complejidad | Explicación |
|-----------------------|-------------|-------------|
| Insertar al inicio    | **O(1)**    | Solo se cambia la referencia de la cabeza. |
| Insertar al final     | **O(1)**\*  | Con puntero a `cola`. Sin él: O(n) porque habría que recorrer toda la lista. |
| Insertar en posición  | **O(n)**    | Se debe recorrer la lista hasta llegar a la posición. |
| Eliminar al inicio    | **O(1)**    | Basta mover la cabeza al siguiente nodo. |
| Eliminar al final     | **O(n)**    | Hay que recorrer desde la cabeza hasta el penúltimo nodo. |
| Eliminar en posición  | **O(n)**    | Se recorre la lista hasta el nodo anterior al que se elimina. |

---

## 🔹 Lista Doblemente Enlazada
| Operación             | Complejidad | Explicación |
|-----------------------|-------------|-------------|
| Insertar al inicio    | **O(1)**    | Se ajustan referencias de `cabeza` y `prev`. |
| Insertar al final     | **O(1)**    | Con puntero a `cola`, se enlaza directamente. |
| Insertar en posición  | **O(n)**    | Requiere recorrer la lista hasta la posición. |
| Eliminar al inicio    | **O(1)**    | Se mueve `cabeza` al siguiente y se ajusta `prev`. |
| Eliminar al final     | **O(1)**    | Con puntero a `cola`, se retrocede y ajusta `next`. |
| Eliminar en posición  | **O(n)**    | Se recorre hasta llegar al nodo a eliminar. |

---

## 🔹 Lista Circular (simple con referencia a `cola`)
| Operación             | Complejidad | Explicación |
|-----------------------|-------------|-------------|
| Insertar al inicio    | **O(1)**    | Se inserta antes de `cabeza` y se ajusta `cola.next`. |
| Insertar al final     | **O(1)**    | Con referencia a `cola`, se enlaza de inmediato. |
| Insertar en posición  | **O(n)**    | Se recorre hasta la posición indicada. |
| Eliminar al inicio    | **O(1)**    | Se mueve `cola.next` al siguiente nodo. |
| Eliminar al final     | **O(n)**    | Se debe recorrer para encontrar el nodo anterior a `cola`. |
| Eliminar en posición  | **O(n)**    | Requiere recorrer la lista hasta llegar al nodo a eliminar. |

---

## ✅ Conclusión
- Operaciones en **inicio** casi siempre son O(1).
- Operaciones en **posición arbitraria** son O(n) porque hay que recorrer la lista.
- La lista **doblemente enlazada** es más eficiente al trabajar con el final, ya que permite eliminar en O(1).
- La lista **circular** optimiza ciertas inserciones, pero eliminar al final sigue siendo O(n).  
