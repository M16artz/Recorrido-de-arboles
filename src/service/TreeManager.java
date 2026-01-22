package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import model.BinaryNode;

/**
 *
 * @author MikelMZ : Miguel Armas
 */
public class TreeManager {

    public static BinaryNode readAndCreateTree(String filePath) throws Exception {
        // Mapas para guardar los nodos y sus conexiones temporalmente
        // Mapa: ID -> Objeto Nodo
        Map<Integer, BinaryNode> nodeMap = new HashMap<>();
        // Mapa: ID -> Arreglo de enteros {idIzquierdo, idDerecho}
        Map<Integer, int[]> conectionsMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            if (line == null) {
                throw new Exception("El archivo está vacío.");
            }

            // --- VALIDACIÓN 1: El Encabezado ---
            int expectedNodes;
            try {
                expectedNodes = Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                throw new Exception("La primera línea debe ser un número entero que indique la cantidad de nodos.");
            }

            int readedLines = 0;

            // --- LECTURA DE NODOS ---
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // Saltar líneas vacías si las hay
                }
                String[] parts = line.split(";");

                // --- VALIDACIÓN 2: Formato de línea ---
                if (parts.length != 4) {
                    throw new Exception("Formato incorrecto en línea: " + line + ". Se esperaban 4 elementos.");
                }

                try {
                    int id = Integer.parseInt(parts[0]);
                    int value = Integer.parseInt(parts[1]);
                    int left = Integer.parseInt(parts[2]);
                    int right = Integer.parseInt(parts[3]);

                    // Crear nodo y guardar referencia
                    if (nodeMap.containsKey(id)) {
                        throw new Exception("ID de nodo duplicado encontrado: " + id);
                    }

                    BinaryNode newNode = new BinaryNode(id, value);
                    nodeMap.put(id, newNode);

                    // Guardar conexiones para procesar después
                    conectionsMap.put(id, new int[]{left, right});
                    readedLines++;

                } catch (NumberFormatException e) {
                    throw new Exception("Los datos del nodo deben ser números enteros. Error en: " + line);
                }
            }

            // --- VALIDACIÓN 3: Cantidad de Nodos ---
            if (readedLines != expectedNodes) {
                throw new Exception("El archivo declara " + expectedNodes + " nodos, pero se encontraron " + readedLines + " definiciones.");
            }
        }

        // --- CONSTRUCCIÓN DEL ÁRBOL (Enlazado) ---
        Set<Integer> sons = new HashSet<>(); // Para rastrear quién es hijo de alguien

        for (Map.Entry<Integer, int[]> entry : conectionsMap.entrySet()) {
            int idParent = entry.getKey();
            int idLeft = entry.getValue()[0];
            int idRight = entry.getValue()[1];

            BinaryNode parent = nodeMap.get(idParent);

            // Enlazar Izquierdo
            if (idLeft != -1) {
                if (!nodeMap.containsKey(idLeft)) {
                    throw new Exception("El nodo " + idParent + " apunta a un hijo izquierdo " + idLeft + " que no está definido en el archivo.");
                }
                parent.setLeft(nodeMap.get(idLeft));
                sons.add(idLeft);
            }

            // Enlazar Derecho
            if (idRight != -1) {
                if (!nodeMap.containsKey(idRight)) {
                    throw new Exception("El nodo " + idParent + " apunta a un hijo derecho " + idRight + " que no está definido en el archivo.");
                }
                parent.setRight(nodeMap.get(idRight));
                sons.add(idRight);
            }
        }

        // --- IDENTIFICAR LA RAÍZ ---
        // La raíz es aquel nodo que existe en el mapa pero no está en el set de 'hijos'
        BinaryNode root = null;
        for (Integer id : nodeMap.keySet()) {
            if (!sons.contains(id)) {
                if (root != null) {
                    throw new Exception("Se encontraron múltiples raíces (Árbol desconectado). IDs: " + root.getIndex() + " y " + id);
                }
                root = nodeMap.get(id);
            }
        }

        if (root == null && !nodeMap.isEmpty()) {
            // Caso borde: Ciclo completo (A->B->A), se devuelve el primero arbitrariamente o error
            System.out.println("Advertencia: No se encontró una raíz clara (posible ciclo). Usando el primer nodo como raíz.");
            return nodeMap.values().iterator().next();
        }

        return root;
    }

    // Método auxiliar para imprimir el árbol (Pre-order) y verificar que funciona
    public static void imprimirArbol(BinaryNode nodo, String prefijo) {
        if (nodo == null) {
            return;
        }
        System.out.println(prefijo + "--> " + nodo);
        imprimirArbol(nodo.getLeft(), prefijo + "    L");
        imprimirArbol(nodo.getRight(), prefijo + "    R");
    }

}
