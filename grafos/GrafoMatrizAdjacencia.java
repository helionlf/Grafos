import java.util.HashMap;
import java.util.Map;

public class GrafoMatrizAdjacencia {
    private int numVertices;
    private int numArestas;
    private Estado[] nomesEstados;
    private int[][] matrizAdjacencia;

    public GrafoMatrizAdjacencia(int numVertices, int numArestas,  Estado[] estados) {
        this.numVertices = numVertices;
        this.numArestas = numArestas;
        this.nomesEstados = estados;
        this.matrizAdjacencia = new int[numVertices][numArestas];
        
        // Inicializa a matriz com zeros
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numArestas; j++) {
                matrizAdjacencia[i][j] = 0;
            }
        }
    }
    

    public void adicionarAresta(int origem, int destino) {
        matrizAdjacencia[origem][destino] = 1;
    }
    
    private int contarArestas(int vertice) {
        int count = 0;
        for (int j = 0; j < numArestas; j++) {
            if (matrizAdjacencia[vertice][j] == 1) {
                count++;
            }
        }
        return count;
    }
    
    public int verticeComMaisArestas() {
        int maiorNumeroArestas = 0;

        for (Estado estado : nomesEstados) {
            int tamanhoArestas = estado.getTamanhoAresta();
            if (tamanhoArestas > maiorNumeroArestas) {
                maiorNumeroArestas = tamanhoArestas;
            }
        }
        return maiorNumeroArestas;
    }
    
    public int verticeComMenosArestas() {
        int menorNumeroArestas = verticeComMaisArestas();

        for (Estado estado : nomesEstados) {
            int tamanhoArestas = estado.getTamanhoAresta();
            if (tamanhoArestas < menorNumeroArestas) {
                menorNumeroArestas = tamanhoArestas;
            }
        }
        return menorNumeroArestas;
    }
    
    public void relacaoVerticesArestas() {
        Map<Integer, Integer> frequencia = new HashMap<>();

        for (int i = 0; i < numVertices; i++) {
            int arestas = contarArestas(i);
            frequencia.put(arestas, frequencia.getOrDefault(arestas, 0) + 1);
        }

        System.out.println("\nRelação de Estados e Arestas:");
        for (Map.Entry<Integer, Integer> entry : frequencia.entrySet()) {
            System.out.println(entry.getValue() + " estado(s) com " + entry.getKey() + " aresta(s).");
        }
    }

    public void imprimirMatriz() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(nomesEstados[i].getNome() + " ");
            for (int j = 0; j < numArestas; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
}