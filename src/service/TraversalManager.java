package service;

import java.util.LinkedList;
import java.util.Queue;
import model.BinaryNode;

/**
 *
 * @author MikelMZ : Miguel Armas
 */
public class TraversalManager {
public static void preOrden(BinaryNode nodo) {
        if (nodo == null) {
            return;
        }
        
        // 1. Procesar la raíz
        System.out.print(nodo.getValue() + " "); 
        // 2. Recorrer subárbol izquierdo
        preOrden(nodo.getLeft());
        // 3. Recorrer subárbol derecho
        preOrden(nodo.getRight());
    }

    // ==========================================
    // 2. RECORRIDO INORDEN (In-order)
    // Orden: Izquierda -> Raíz -> Derecha
    // ==========================================
    public static void inOrden(BinaryNode nodo) {
        if (nodo == null) {
            return;
        }
        // 1. Recorrer subárbol izquierdo
        inOrden(nodo.getLeft());
        // 2. Procesar la raíz
        System.out.print(nodo.getValue() + " ");
        // 3. Recorrer subárbol derecho
        inOrden(nodo.getRight());
    }

    // ==========================================
    // 3. RECORRIDO POSTORDEN (Post-order)
    // Orden: Izquierda -> Derecha -> Raíz
    // ==========================================
    public static void postOrden(BinaryNode nodo) {
        if (nodo == null) {
            return;
        }
        // 1. Recorrer subárbol izquierdo
        postOrden(nodo.getLeft());
        // 2. Recorrer subárbol derecho
        postOrden(nodo.getRight()); 
        // 3. Procesar la raíz
        System.out.print(nodo.getValue() + " ");
    }

    // ==========================================
    // 4. RECORRIDO POR ANCHURA (Breadth-first / Level-order)
    // Orden: Nivel por nivel, de izquierda a derecha
    // Requiere una Estructura de Datos COLA (Queue)
    // ==========================================
    public static void porAnchura(BinaryNode raiz) {
        if (raiz == null) {
            return;
        }

        // Usamos LinkedList porque implementa la interfaz Queue
        Queue<BinaryNode> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            // Sacar el nodo del frente de la cola
            BinaryNode actual = cola.poll();

            // Procesar el nodo
            System.out.print(actual.getValue() + " ");

            // Si tiene hijo izquierdo, agregarlo a la cola
            if (actual.getLeft() != null) {
                cola.add(actual.getLeft());
            }

            // Si tiene hijo derecho, agregarlo a la cola
            if (actual.getRight() != null) {
                cola.add(actual.getRight());
            }
        }
    }
}
