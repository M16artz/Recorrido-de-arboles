package service;

import java.time.Clock;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    List<Integer> resultado=new ArrayList<>();
    preOrden(node,resultado);
    System.out.println(resultado);
    }

    private static void preOrden(BinaryNode node, List<Integer> resultado) {
        if (node == null) {
            return;
        }
        resultado.add(node.getValue());
        preOrden(node.getLeft(), resultado);
        preOrden(node.getRight(), resultado);
    }
    // ==========================================
    // RECORRIDO INORDEN (In-order)
    // Orden: Izquierda -> Raíz -> Derecha
    // ==========================================
    public static void inOrden(BinaryNode node) {
    List<Integer> resultado=new ArrayList<>();
    inOrden(node,resultado);
    System.out.println(resultado);
    }

    private static void inOrden(BinaryNode node, List<Integer> resultado) {
        if (node == null) {
            return;
        }
        inOrden(node.getLeft(), resultado);
        resultado.add(node.getValue());
        inOrden(node.getRight(), resultado);
    }

    // ==========================================
    // RECORRIDO POSTORDEN (In-order)
    // Orden: Izquierda -> Derecha -> Raíz
    // ==========================================
    public static void postOrden(BinaryNode node) {
        List<Integer> resultado=new ArrayList<>();
        postOrden(node,resultado);
        System.out.println(resultado);
    }
    private static void postOrden(BinaryNode node, List<Integer> resultado) {
        if (node == null) {
            return;
        }
        postOrden(node.getLeft(), resultado);
        postOrden(node.getRight(), resultado);
        resultado.add(node.getValue());
    }

    // ==========================================
    // 4. RECORRIDO POR ANCHURA (Breadth-first / Level-order)
    // Orden: Nivel por nivel, de izquierda a derecha
    // ==========================================
    public static void byLevel(BinaryNode root) {
        List<Integer> resultado = new ArrayList<>();

        if (root == null) {
            System.out.println(resultado);
            return;
        }
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryNode actual = queue.poll();
            resultado.add(actual.getValue());

            if (actual.getLeft() != null) {
                queue.add(actual.getLeft());
            }
            if (actual.getRight() != null) {
                queue.add(actual.getRight());
            }
        }
        System.out.println(resultado);
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
