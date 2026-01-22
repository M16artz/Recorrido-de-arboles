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

    public static BinaryNode leerYGenerarArbol(String rutaArchivo) throws Exception {
        // Mapas para guardar los nodos y sus conexiones temporalmente
        // Mapa: ID -> Objeto Nodo
        Map<Integer, BinaryNode> mapaNodos = new HashMap<>();
        // Mapa: ID -> Arreglo de enteros {idIzquierdo, idDerecho}
        Map<Integer, int[]> mapaConexiones = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = br.readLine();

            if (linea == null) {
                throw new Exception("El archivo está vacío.");
            }

            // --- VALIDACIÓN 1: El Encabezado ---
            int cantidadEsperada;
            try {
                cantidadEsperada = Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                throw new Exception("La primera línea debe ser un número entero que indique la cantidad de nodos.");
            }

            int lineasLeidas = 0;

            // --- LECTURA DE NODOS ---
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue; // Saltar líneas vacías si las hay
                }
                String[] partes = linea.split(";");

                // --- VALIDACIÓN 2: Formato de línea ---
                if (partes.length != 4) {
                    throw new Exception("Formato incorrecto en línea: " + linea + ". Se esperaban 4 elementos.");
                }

                try {
                    int id = Integer.parseInt(partes[0]);
                    int valor = Integer.parseInt(partes[1]);
                    int izq = Integer.parseInt(partes[2]);
                    int der = Integer.parseInt(partes[3]);

                    // Crear nodo y guardar referencia
                    if (mapaNodos.containsKey(id)) {
                        throw new Exception("ID de nodo duplicado encontrado: " + id);
                    }

                    BinaryNode nuevoNodo = new BinaryNode(id, valor);
                    mapaNodos.put(id, nuevoNodo);

                    // Guardar conexiones para procesar después
                    mapaConexiones.put(id, new int[]{izq, der});
                    lineasLeidas++;

                } catch (NumberFormatException e) {
                    throw new Exception("Los datos del nodo deben ser números enteros. Error en: " + linea);
                }
            }

            // --- VALIDACIÓN 3: Cantidad de Nodos ---
            if (lineasLeidas != cantidadEsperada) {
                throw new Exception("El archivo declara " + cantidadEsperada + " nodos, pero se encontraron " + lineasLeidas + " definiciones.");
            }
        }

        // --- CONSTRUCCIÓN DEL ÁRBOL (Enlazado) ---
        Set<Integer> hijos = new HashSet<>(); // Para rastrear quién es hijo de alguien

        for (Map.Entry<Integer, int[]> entrada : mapaConexiones.entrySet()) {
            int idPadre = entrada.getKey();
            int idIzq = entrada.getValue()[0];
            int idDer = entrada.getValue()[1];

            BinaryNode padre = mapaNodos.get(idPadre);

            // Enlazar Izquierdo
            if (idIzq != -1) {
                if (!mapaNodos.containsKey(idIzq)) {
                    throw new Exception("El nodo " + idPadre + " apunta a un hijo izquierdo " + idIzq + " que no está definido en el archivo.");
                }
                padre.setLeft(mapaNodos.get(idIzq));
                hijos.add(idIzq);
            }

            // Enlazar Derecho
            if (idDer != -1) {
                if (!mapaNodos.containsKey(idDer)) {
                    throw new Exception("El nodo " + idPadre + " apunta a un hijo derecho " + idDer + " que no está definido en el archivo.");
                }
                padre.setRight(mapaNodos.get(idDer));
                hijos.add(idDer);
            }
        }

        // --- IDENTIFICAR LA RAÍZ ---
        // La raíz es aquel nodo que existe en el mapa pero no está en el set de 'hijos'
        BinaryNode raiz = null;
        for (Integer id : mapaNodos.keySet()) {
            if (!hijos.contains(id)) {
                if (raiz != null) {
                    throw new Exception("Se encontraron múltiples raíces (Árbol desconectado). IDs: " + raiz.getIndex() + " y " + id);
                }
                raiz = mapaNodos.get(id);
            }
        }

        if (raiz == null && !mapaNodos.isEmpty()) {
            // Caso borde: Ciclo completo (A->B->A), se devuelve el primero arbitrariamente o error
            System.out.println("Advertencia: No se encontró una raíz clara (posible ciclo). Usando el primer nodo como raíz.");
            return mapaNodos.values().iterator().next();
        }

        return raiz;
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
