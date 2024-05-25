package grafosAdjacencia;

import java.io.*;
import java.util.Scanner;
import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		FileInputStream grafoFile = new FileInputStream("C:\\Users\\helio\\eclipse-workspace\\Grafos\\bin\\grafosAdjacencia\\grafo.txt");
		Scanner scanner = new Scanner(grafoFile);
	
		int numVertices = scanner.nextInt();
		int numArestas = scanner.nextInt();
		int origem = scanner.nextInt();
	
		scanner.nextLine();
	
		Vertice[] vertices = new Vertice[numVertices];
	
		int index = 0;
	
		while (scanner.hasNextLine()) {
		    String linha = scanner.nextLine();
		    String[] vetorLinha = linha.split(" ");
		    
		    String nome = vetorLinha[0];
		    String[] arestas = new String[vetorLinha.length - 1];
		    
		    for (int i = 1; i < vetorLinha.length; i++) {
		        arestas[i - 1] = vetorLinha[i];
		    }
		    
		    vertices[index] = new Vertice(nome, index, arestas);
		    index++;
		}
	
		scanner.close();
	       
		GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(numVertices, numVertices, vertices);
	
		// Adição das arestas ao grafo
		for (Vertice vertice : vertices) {
		    for (int i = 0; i < vertice.getTamanhoAresta(); i++) {
		        for(int j = 0; j < vertices.length; j++) {
		            int destino = 0;
		            if (vertice.getAresta(i).equals(vertices[j].getNome())) {
		                destino = vertices[j].getIndice();
		                grafo.adicionarAresta(vertice.getIndice(), destino);
		            }
		        }
		    }
		}
	        
        grafo.imprimirMatriz();
        System.out.println();
        
        //adicione os vertices métos que precisar disponíveis na classe GrafoMatrizAdjacencia
               
        JFrame frame = new JFrame("Visualização do Grafo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GrafoVisualizacao grafoPanel = new GrafoVisualizacao(grafo);
        frame.add(grafoPanel);
        frame.pack();
        frame.setVisible(true);
        
	}
}
