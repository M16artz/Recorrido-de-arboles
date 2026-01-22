package main;

import model.BinaryNode;
import service.TraversalManager;
import static service.TreeManager.leerYGenerarArbol;

public class Main {

    public static String RUTA_ARCHIVO = "src/resources/arbol_10_nodos.txt";

    public static void main(String[] args) {

        // 2. Ejecutar la lectura
        System.out.println("--- Leyendo archivo y construyendo árbol ---");
        try {
            BinaryNode raiz = leerYGenerarArbol(RUTA_ARCHIVO);
            System.out.println("ARBOL CORRECTAMENTE CREADO");
            System.out.println("PRE ORDEN");
            TraversalManager.preOrden(raiz);
            System.out.println("\nIN ORDEN");
            TraversalManager.inOrden(raiz);
            System.out.println("\nPOST ORDEN");
            TraversalManager.postOrden(raiz);
            System.out.println("\nANCHURA");
            TraversalManager.porAnchura(raiz);

        } catch (Exception e) {
            System.err.println("ERROR CRÍTICO: " + e.getMessage());
        }
    }
}
