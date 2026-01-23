package main;

import model.BinaryNode;
import service.TraversalManager;
import static service.TreeManager.readAndCreateTree;

public class Main {

    public static String RUTA_ARCHIVO = "src/resources/arbol_10_nodos.txt";

    public static void main(String[] args) {

        // 2. Ejecutar la lectura
        System.out.println("--- Leyendo archivo y construyendo árbol ---");
        try {
            BinaryNode raiz = readAndCreateTree(RUTA_ARCHIVO);
            System.out.println("ARBOL CORRECTAMENTE CREADO");
            System.out.println("=== Árbol ===");
            printWide(raiz);

            System.out.println("\n=== Recorridos ===");
            System.out.println("PRE ORDEN");
            TraversalManager.preOrden(raiz);
            System.out.println("\nIN ORDEN");
            TraversalManager.inOrden(raiz);
            System.out.println("\nPOST ORDEN");
            TraversalManager.postOrden(raiz);
            System.out.println("\nPOR NIVELES");
            TraversalManager.byLevel(raiz);

            System.out.println("\n=== Métricas ===");
            System.out.println("Tamaño: " + TraversalManager.size(raiz));
            System.out.println("Altura: " + TraversalManager.height(raiz));

        } catch (Exception e) {
            System.err.println("ERROR CRÍTICO: " + e.getMessage());
        }
    }

    public static void printWide(BinaryNode root) {
        printWide(root, "", true);
    }

    private static void printWide(BinaryNode node, String prefix, boolean isTail) {
        if (node == null) return;
        if (node.getRight() != null) {
            printWide(node.getRight(), prefix + (isTail ? "│   " : "    "), false);
        }
        System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.getValue());
        if (node.getLeft() != null) {
            printWide(node.getLeft(), prefix + (isTail ? "    " : "│   "), true);
        }
    }
    
}
