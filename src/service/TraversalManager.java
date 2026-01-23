package service;

import java.util.LinkedList;
import java.util.Queue;
import model.BinaryNode;

/**
 *
 * @author MikelMZ : Miguel Armas
 */
public class TraversalManager {

    // ==========================================
    // RECORRIDO PREORDEN (In-order)
    // Orden: Raíz -> Izquierda ->  Derecha
    // ==========================================
public static void preOrden(BinaryNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue() + " ");
        preOrden(node.getLeft());
        preOrden(node.getRight());
    }

    // ==========================================
    // RECORRIDO INORDEN (In-order)
    // Orden: Izquierda -> Raíz -> Derecha
    // ==========================================
    public static void inOrden(BinaryNode node) {
        if (node == null) {
            return;
        }
        inOrden(node.getLeft());
        System.out.print(node.getValue() + " ");
        inOrden(node.getRight());
    }

    // ==========================================
    // RECORRIDO POSTORDEN (In-order)
    // Orden: Izquierda -> Derecha -> Raíz
    // ==========================================
    public static void postOrden(BinaryNode node) {
        if (node == null) {
            return;
        }
        postOrden(node.getLeft());
        postOrden(node.getRight());
        System.out.print(node.getValue() + " ");
    }

    // ==========================================
    // 4. RECORRIDO POR ANCHURA (Breadth-first / Level-order)
    // Orden: Nivel por nivel, de izquierda a derecha
    // ==========================================
    public static void byLevel(BinaryNode root) {
        if (root == null) {
            return;
        }

        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryNode actual = queue.poll();
            System.out.print(actual.getValue() + " ");
            if (actual.getLeft() != null) {
                queue.add(actual.getLeft());
            }
            if (actual.getRight() != null) {
                queue.add(actual.getRight());
            }
        }
    }

    public static int size(BinaryNode node) {
        if (node == null) return 0;
        return 1 + size(node.getLeft()) + size(node.getRight());
    }


    public static int height(BinaryNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
}
