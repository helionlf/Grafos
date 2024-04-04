import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class GrafoMatrizAdjacencia {
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
            System.out.println("O grafo não possui um caminho euleriano.");
            return;
        }

        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> resultado = new ArrayList<>();

        // Encontra um vértice com grau ímpar para iniciar o caminho euleriano
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

            // Encontra o próximo vértice adjacente não visitado
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdjacencia[atual][i] == 1) {
                    proximo = i;
                    break;
                }
            }

            // Remove a aresta entre o vértice atual e o próximo
            matrizAdjacencia[atual][proximo] = 0;
            matrizAdjacencia[proximo][atual] = 0;

            // Adiciona o próximo vértice ao caminho euleriano
            stack.push(proximo);

            // Se não houver mais arestas saindo do próximo vértice, adiciona-o ao resultado
            if (contarArestas(proximo) == 0) {
                while(!stack.isEmpty()) {
                    resultado.add(stack.pop());
                }
            }
        }

        // Exibe o caminho euleriano
        System.out.println("Caminho Euleriano:");
        for (int i = resultado.size() - 1; i >= 0; i--) {
            System.out.print(nomesVertices[resultado.get(i)].getNome());
            if (i != 0) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
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
    
    public void encontrarCaminhoEulerianoTeste() {
        if (!isEuleriano()) {
            System.out.println("O grafo não possui um ciclo euleriano.");
            return;
        }
    
        // Cria uma cópia da matriz de adjacência para marcar as arestas visitadas
        int[][] copiaMatrizAdjacencia = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                copiaMatrizAdjacencia[i][j] = matrizAdjacencia[i][j];
            }
        }
    
        Stack<Integer> ciclo = new Stack<>();
        ArrayList<Integer> resultado = new ArrayList<>();
        int verticeAtual = 0; // Inicia a busca a partir do primeiro vértice
    
        ciclo.push(verticeAtual);
        while (!ciclo.isEmpty()) {
            if (contarArestas(verticeAtual) == 0) {
                // Adiciona o vértice ao ciclo euleriano
                resultado.add(verticeAtual);
                // Remove a última aresta do ciclo
                verticeAtual = ciclo.pop();
            } else {
                // Encontra a próxima aresta não visitada e a adiciona ao ciclo
                for (int i = 0; i < numVertices; i++) {
                    if (copiaMatrizAdjacencia[verticeAtual][i] == 1) {
                        ciclo.push(verticeAtual);
                        copiaMatrizAdjacencia[verticeAtual][i] = 0;
                        copiaMatrizAdjacencia[i][verticeAtual] = 0;
                        verticeAtual = i;
                        break;
                    }
                }
            }
        }
    
        // Exibe o ciclo euleriano
        System.out.println("Ciclo Euleriano:");
        for (int i = resultado.size() - 1; i >= 0; i--) {
            System.out.print(nomesVertices[resultado.get(i)].getNome());
            if (i != 0) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

}
