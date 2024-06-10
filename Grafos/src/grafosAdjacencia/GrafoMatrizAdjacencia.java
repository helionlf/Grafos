package grafosAdjacencia;

import java.util.*;

public class GrafoMatrizAdjacencia {
	private int numVertices;
    private Vertice[] nomesVertices;
    private int[][] matrizAdjacencia;

    public GrafoMatrizAdjacencia(int numVertices, Vertice[] vertices) {
        this.numVertices = numVertices;
        this.nomesVertices = vertices;
        this.matrizAdjacencia = new int[numVertices][numVertices];
        
        // Inicializa a matriz com infinito (representando arestas inexistentes)
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    matrizAdjacencia[i][j] = 0; // distância para si mesmo é 0
                } else {
                    matrizAdjacencia[i][j] = Integer.MAX_VALUE; // representação de infinito
                }
            }
        }
    }
    
    public int getNumVertices() {
    	return this.numVertices;
    }
    
    public boolean existeAresta(int i, int j) {
    	return matrizAdjacencia[i][j] != Integer.MAX_VALUE;
    }
    
    public String[] getNomesVertices() {
        String[] nomes = new String[numVertices];
        for (int i = 0; i < numVertices; i++) {
            nomes[i] = nomesVertices[i].getNome();
        }
        return nomes;
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        matrizAdjacencia[origem][destino] = peso;
    }
    
      
    public void dijkstra(int origem) {
        int[] distancias = new int[numVertices];
        boolean[] visitados = new boolean[numVertices];
        int[] predecessores = new int[numVertices];
        PriorityQueue<Vertice> heap = new PriorityQueue<>(Comparator.comparingInt(Vertice::getDistancia));

        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(predecessores, -1);
        distancias[origem] = 0;
        nomesVertices[origem].setDistancia(0);
        heap.add(nomesVertices[origem]);

        while (!heap.isEmpty()) {
            Vertice u = heap.poll();
            int uIndice = u.getIndice();

            if (visitados[uIndice]) continue;
            visitados[uIndice] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!visitados[v] && matrizAdjacencia[uIndice][v] != Integer.MAX_VALUE) {
                    int novaDistancia = distancias[uIndice] + matrizAdjacencia[uIndice][v];
                    if (novaDistancia < distancias[v]) {
                        distancias[v] = novaDistancia;
                        predecessores[v] = uIndice;
                        nomesVertices[v].setDistancia(novaDistancia);
                        heap.add(nomesVertices[v]);
                    }
                }
            }
        }

        for (int i = 0; i < numVertices; i++) {
            if (distancias[i] == Integer.MAX_VALUE) {
                System.out.println("Distância de " + nomesVertices[origem].getNome() + " para " + nomesVertices[i].getNome() + " é infinita");
            } else {
                System.out.print("Distância de " + nomesVertices[origem].getNome() + " para " + nomesVertices[i].getNome() + " é " + distancias[i]);
                System.out.print(" e o caminho é: ");
                imprimirCaminho(predecessores, origem, i);
                System.out.println();
            }
        }
    }
    
    
    public void initializeSingleSource(int origem, int[] distancias, int[] predecessores) {
        for (int i = 0; i < numVertices; i++) {
            distancias[i] = Integer.MAX_VALUE;
            predecessores[i] = -1;
        }
        distancias[origem] = 0;
    }

    public void relax(int u, int v, int[] distancias, int[] predecessores) {
        if (distancias[u] != Integer.MAX_VALUE && distancias[v] > distancias[u] + matrizAdjacencia[u][v]) {
            distancias[v] = distancias[u] + matrizAdjacencia[u][v];
            predecessores[v] = u;
        }
    }
    
    public boolean bellmanFord(int origem) {
        int[] distancias = new int[numVertices];
        int[] predecessores = new int[numVertices];

        initializeSingleSource(origem, distancias, predecessores);

        for (int i = 1; i < numVertices; i++) {
            for (int u = 0; u < numVertices; u++) {
                for (int v = 0; v < numVertices; v++) {
                    if (matrizAdjacencia[u][v] != Integer.MAX_VALUE) {
                        relax(u, v, distancias, predecessores);
                    }
                }
            }
        }

        boolean[] cicloNegativo = new boolean[numVertices];
        for (int u = 0; u < numVertices; u++) {
            for (int v = 0; v < numVertices; v++) {
                if (matrizAdjacencia[u][v] != Integer.MAX_VALUE && distancias[v] > distancias[u] + matrizAdjacencia[u][v]) {
                    marcarCicloNegativo(v, predecessores, cicloNegativo);
                }
            }
        }

        imprimirResultados(distancias, predecessores, origem, cicloNegativo);
        return true;
    }

    private void marcarCicloNegativo(int v, int[] predecessores, boolean[] cicloNegativo) {
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        fila.add(v);
        
        while (!fila.isEmpty()) {
            int u = fila.poll();
            if (!visitados.contains(u)) {
                visitados.add(u);
                cicloNegativo[u] = true;
                for (int i = 0; i < predecessores.length; i++) {
                    if (predecessores[i] == u) {
                        fila.add(i);
                    }
                }
            }
        }
    }

    private void imprimirResultados(int[] distancias, int[] predecessores, int origem, boolean[] cicloNegativo) {
        for (int i = 0; i < numVertices; i++) {
            if (cicloNegativo[i]) {
                System.out.println("Caminho de " + nomesVertices[origem].getNome() + " para " + nomesVertices[i].getNome() + " é -∞ (ciclo negativo)");
            } else if (distancias[i] == Integer.MAX_VALUE) {
                System.out.println("Distância de " + nomesVertices[origem].getNome() + " para " + nomesVertices[i].getNome() + " é infinita");
            } else {
                System.out.print("Distância de " + nomesVertices[origem].getNome() + " para " + nomesVertices[i].getNome() + " é " + distancias[i]);
                System.out.print(" e o caminho é: ");
                imprimirCaminho(predecessores, origem, i);
                System.out.println();
            }
        }
    }

    

    private void imprimirCaminho(int[] predecessores, int origem, int destino) {
        if (destino == origem) {
            System.out.print(nomesVertices[origem].getNome());
        } else if (predecessores[destino] == -1) {
            System.out.print("Não há caminho");
        } else {
            imprimirCaminho(predecessores, origem, predecessores[destino]);
            System.out.print(" -> " + nomesVertices[destino].getNome());
        }
    }

    
    public void imprimirMatriz() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(nomesVertices[i].getNome() + " ");
            for (int j = 0; j < numVertices; j++) {
                if (matrizAdjacencia[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(matrizAdjacencia[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
}
