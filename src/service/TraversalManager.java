package service;

import java.util.LinkedList;
import java.util.Queue;
import model.BinaryNode;

/**
 *
 * @author MikelMZ : Miguel Armas
 */
public class TraversalManager {
public static void preOrden(BinaryNode node) {
        if (node == null) {
            return;
        }
        
        // 1. Procesar la raíz
        System.out.print(node.getValue() + " "); 
        // 2. Recorrer subárbol izquierdo
        preOrden(node.getLeft());
        // 3. Recorrer subárbol derecho
        preOrden(node.getRight());
    }

    // ==========================================
    // 2. RECORRIDO INORDEN (In-order)
    // Orden: Izquierda -> Raíz -> Derecha
    // ==========================================
    public static void inOrden(BinaryNode node) {
        if (node == null) {
            return;
        }
        // 1. Recorrer subárbol izquierdo
        inOrden(node.getLeft());
        // 2. Procesar la raíz
        System.out.print(node.getValue() + " ");
        // 3. Recorrer subárbol derecho
        inOrden(node.getRight());
    }

    // ==========================================
    // 3. RECORRIDO POSTORDEN (Post-order)
    // Orden: Izquierda -> Derecha -> Raíz
    // ==========================================
    public static void postOrden(BinaryNode node) {
        if (node == null) {
            return;
        }
        // 1. Recorrer subárbol izquierdo
        postOrden(node.getLeft());
        // 2. Recorrer subárbol derecho
        postOrden(node.getRight()); 
        // 3. Procesar la raíz
        System.out.print(node.getValue() + " ");
    }

    // ==========================================
    // 4. RECORRIDO POR ANCHURA (Breadth-first / Level-order)
    // Orden: Nivel por nivel, de izquierda a derecha
    // Requiere una Estructura de Datos COLA (Queue)
    // ==========================================
    public static void porAnchura(BinaryNode root) {
        if (root == null) {
            return;
        }

        // Usamos LinkedList porque implementa la interfaz Queue
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Sacar el nodo del frente de la cola
            BinaryNode actual = queue.poll();

            // Procesar el nodo
            System.out.print(actual.getValue() + " ");

            // Si tiene hijo izquierdo, agregarlo a la cola
            if (actual.getLeft() != null) {
                queue.add(actual.getLeft());
            }

            // Si tiene hijo derecho, agregarlo a la cola
            if (actual.getRight() != null) {
                queue.add(actual.getRight());
            }
        }
    }
}
