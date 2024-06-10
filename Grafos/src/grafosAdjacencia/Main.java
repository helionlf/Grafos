package grafosAdjacencia;


import java.io.*;
import java.util.Scanner;
import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		FileInputStream grafoFile = new FileInputStream("C:\\Users\\helio\\eclipse-workspace\\Grafos\\bin\\grafosAdjacencia\\grafo.txt");
        Scanner scanner = new Scanner(grafoFile);

        int numVertices = scanner.nextInt();
        int origem = scanner.nextInt();
        scanner.nextLine();

        Vertice[] vertices = new Vertice[numVertices];

        for (int i = 0; i < numVertices; i++) {
            String linha = scanner.nextLine();
            String[] vetorLinha = linha.split(" ");
            String nome = vetorLinha[0];
            vertices[i] = new Vertice(nome, i);
        }

        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(numVertices, vertices);

        // Adiciona arestas com pesos
        scanner = new Scanner(new FileInputStream("C:\\Users\\helio\\eclipse-workspace\\Grafos\\bin\\grafosAdjacencia\\grafo.txt"));
        scanner.nextLine();

        for (int i = 0; i < numVertices; i++) {
            String linha = scanner.nextLine();
            String[] vetorLinha = linha.split(" ");
            int index = 1;
            while (index < vetorLinha.length) {
                String nomeAdjacente = vetorLinha[index];
                int peso = Integer.parseInt(vetorLinha[index + 1]);
                for (int j = 0; j < numVertices; j++) {
                    if (vertices[j].getNome().equals(nomeAdjacente)) {
                        grafo.adicionarAresta(vertices[i].getIndice(), vertices[j].getIndice(), peso);
                        break;
                    }
                }
                index += 2;
            }
        }
        
	        
        grafo.imprimirMatriz();
        System.out.println();
        
        grafo.bellmanFord(origem);    
	}
}
