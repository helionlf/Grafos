package grafosAdjacencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class GrafoMatrizAdjacencia {
	private int numVertices;
    private int numArestas;
    private Vertice[] nomesVertices;
    private int[][] matrizAdjacencia;

    public GrafoMatrizAdjacencia(int numVertices, int numArestas,  Vertice[] vertices) {
        this.numVertices = numVertices;
        this.numArestas = numArestas;
        this.nomesVertices = vertices;
        this.matrizAdjacencia = new int[numVertices][numArestas];
        
        // Inicializa a matriz com zeros
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numArestas; j++) {
                matrizAdjacencia[i][j] = 0;
            }
        }
    }
    
    public int getNumVertices() {
    	return this.numVertices;
    }
    
    public boolean existeAresta(int i, int j) {
        return matrizAdjacencia[i][j] != 0;
    }
    
    public String[] getNomesVertices() {
        String[] nomes = new String[numVertices];
        for (int i = 0; i < numVertices; i++) {
            nomes[i] = nomesVertices[i].getNome();
        }
        return nomes;
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
    
     private int temAdjacente(int vertice) {
        int count = 0;
        for (int j = 0; j < numArestas; j++) {
            if (matrizAdjacencia[vertice][j] == 0 && vertice != j) {
                count++;
            }
        }
        return count;
    }
    
    public int verticeComMaisArestas() {
        int maiorNumeroArestas = 0;

        for (Vertice vertice : nomesVertices) {
            int tamanhoArestas = vertice.getTamanhoAresta();
            if (tamanhoArestas > maiorNumeroArestas) {
                maiorNumeroArestas = tamanhoArestas;
            }
        }
        return maiorNumeroArestas;
    }
    
    public int verticeComMenosArestas() {
        int menorNumeroArestas = verticeComMaisArestas();

        for (Vertice vertice : nomesVertices) {
            int tamanhoArestas = vertice.getTamanhoAresta();
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
    
    public ArrayList encontrarNaoAdjacentes(Vertice entrada) {
        ArrayList<Vertice> verticesNaoAdjacentes = new ArrayList<>();
        for(int i = 0; i < numVertices; i++) {
            if(matrizAdjacencia[entrada.getIndice()][i] == 0 && entrada.getIndice() != i) {
                for(Vertice vertice : nomesVertices) {
                    if(vertice.getIndice() == i) {
                        verticesNaoAdjacentes.add(vertice);
                    }
                }
            }
        }
        
        return verticesNaoAdjacentes;
    }
    
    private boolean isOre(Vertice entrada) {
        ArrayList<Vertice> verticesNaoAdjacentes = encontrarNaoAdjacentes(entrada);
        for(int i = 0; i < verticesNaoAdjacentes.size(); i++) {
            if(entrada.getTamanhoAresta() + verticesNaoAdjacentes.get(i).getTamanhoAresta() < numVertices) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean isHamiltonianoOre() {
        for(Vertice vertice : nomesVertices) {
            if(!isOre(vertice)) {
                return false;
            }
        }
        return true;
    }

    private void verificarFecho(Vertice entrada) {
        ArrayList<Vertice> verticesNaoAdjacentes = encontrarNaoAdjacentes(entrada);
        boolean adicionou = false;
        
        for(int i = 0; i < verticesNaoAdjacentes.size(); i++) {
            int arestaEntrada = contarArestas(entrada.getIndice());
            int arestaSaida = contarArestas(verticesNaoAdjacentes.get(i).getIndice());
            int somaArestas = arestaEntrada + arestaSaida;
            if(somaArestas >= numVertices) {
                adicionarAresta(entrada.getIndice(), verticesNaoAdjacentes.get(i).getIndice());
                adicionarAresta(verticesNaoAdjacentes.get(i).getIndice(), entrada.getIndice());
                adicionou = true;
            }
        }
        
        if(adicionou == true && encontrarNaoAdjacentes(entrada).size() > 0) {
            verificarFecho(entrada);
        }
    }
    
    public boolean isHamiltonianoBondyChvatal() {
        for(Vertice vertice : nomesVertices) {
            verificarFecho(vertice);
            //imprimirMatriz();
            //System.out.println(" ");
        }
        
        int cont = 0;
        for(int i = 0; i < numVertices; i++) {
            int tamnho = contarArestas(i);
            if(tamnho == numVertices-1) {
                cont++;
            }
        }
        
        if(cont == numVertices) {
            return true;
        }
        
        return false;
    }
    
    public boolean isHamiltonianoDirac() {
        if(numVertices >= 3 && verticeComMenosArestas() >= numVertices/2.0) {
            return true;
        }
        return false;
    } 

    public boolean isEuleriano() {
        int cont = 0;
        for (int i = 0; i < numVertices; i++) {
            int arestas = contarArestas(i);
            if(arestas%2 != 0) {
               cont++; 
            }
        }
        
        if(cont > 0) {
            return false;
        }
        
        return true;
    }
    
    public boolean isSemeEuleriano() {
        int cont = 0;
        for (int i = 0; i < numVertices; i++) {
            int arestas = contarArestas(i);
            if(arestas%2 != 0) {
               cont++; 
            }
        }
        
        if(cont == 2 || isEuleriano()) {
            return true;
        }
        
        return false;
    }
    
    public void encontrarCaminhoEuleriano() {
        if (!isSemeEuleriano()) {
            System.out.println("O grafo não possui um caminho Euleriano.");
            return;
        }

        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> resultado = new ArrayList<>();

        int verticeInicial = 0;
        for (int i = 0; i < numVertices; i++) {
            if (contarArestas(i) % 2 != 0) {
                verticeInicial = i;
                break;
            }
        }

        stack.push(verticeInicial);
        while (!stack.isEmpty()) {
            int atual = stack.peek();
            int proximo = 0;

            for (int i = 0; i < numVertices; i++) {
                if (matrizAdjacencia[atual][i] == 1) {
                    if(i != 0 || contarArestas(i) > 1) {
                        proximo = i;
                        break;
                    }
                }
            }

            matrizAdjacencia[atual][proximo] = 0;
            matrizAdjacencia[proximo][atual] = 0;
            
            stack.push(proximo);

            if (contarArestas(proximo) == 0) {
                while(!stack.isEmpty()) {
                    resultado.add(stack.pop());
                }
            }
        }

        
        // Exiber caminho euleriano
        int peso = 0;
        System.out.println("Caminho Euleriano:");
        for (int i = resultado.size() - 1; i >= 0; i--) {
            System.out.print(nomesVertices[resultado.get(i)].getNome());
            if (i != 0) {
                System.out.print(" <- 1 -> ");
                peso++;
            }
        }
        System.out.println();
        System.out.println("Soma dos pesos: " + peso);
    }
    
    public void bfs(Vertice origem) {
        Queue<Vertice> fila = new LinkedList<>();
        origem.visitar();
        fila.add(origem);

 
        Map<Vertice, Vertice> predecessores = new HashMap<>();
        predecessores.put(origem, null);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();
            //System.out.println("Vértice " + atual.getNome() + " -> Distância até a origem: " + atual.getDistancia());

            for (int i = 0; i < numVertices; i++) {
                if (matrizAdjacencia[atual.getIndice()][i] == 1) {
                    Vertice vizinho = nomesVertices[i];
                    if (!vizinho.foiVisitado()) {
                        vizinho.setDistancia(atual.getDistancia() + 1);
                        vizinho.visitar();
                        fila.add(vizinho);
                        predecessores.put(vizinho, atual);
                    } else {
                        if (predecessores.get(atual) != vizinho) {
                            matrizAdjacencia[atual.getIndice()][i] = 0;
                            matrizAdjacencia[i][atual.getIndice()] = 0;
                        }
                    }
                }
            }
            
            atual.descobrir();
        }

        // Imprime o caminho de cada vértice até a origem
//        for (Vertice vertice : predecessores.keySet()) {
//            System.out.print("Caminho de " + vertice.getNome() + " até a origem: ");
//            Vertice predecessor = predecessores.get(vertice);
//            while (predecessor != null) {
//                System.out.print(predecessor.getNome() + " -> ");
//                predecessor = predecessores.get(predecessor);
//            }
//            System.out.println("Origem");
//        }
        
//        for (int i = 0; i < numVertices; i++) {
//        	System.out.println(nomesVertices[i].getNome() + " (" + nomesVertices[i].foiDescoberto() + ")");
//        }
    }

    public void dfs(Vertice origem) {
//    	bfs(origem);
//    	for(int i = 0; i < numVertices; i++) {
//    		nomesVertices[i].reiniciarVertice();
//    	}
    	
    	Stack<Vertice> stack = new Stack<>();
    	
    	stack.push(origem);
    	while(!stack.isEmpty()) {
    		Vertice atual = stack.peek();
    		stack.pop();
    		System.out.print(atual.getIndice() + " ");
    		atual.visitar();
    		for (int i = 0; i < numVertices; i++) {
                if (matrizAdjacencia[atual.getIndice()][i] == 1) {
                	if(!nomesVertices[i].foiVisitado()) {
                		stack.push(nomesVertices[i]);
                	}
                }
    		}
    		
    	}
    }
    
    public void dfsRecur(Vertice origem) {    	
    	origem.visitar();
    	System.out.print(origem.getIndice() + " ");
    	
    	for (int i = 0; i < numVertices; i++) {
            if (matrizAdjacencia[origem.getIndice()][i] == 1) {
            	if(!nomesVertices[i].foiVisitado()) {
            		dfsRecur(nomesVertices[i]); 
            	}
            }
		}
    }
    
    public void imprimirMatriz() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(nomesVertices[i].getNome() + " ");
            for (int j = 0; j < numArestas; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
}
