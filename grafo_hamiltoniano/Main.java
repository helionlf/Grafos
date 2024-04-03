public class Main {
    public static void main(String[] args) {
        
        //grafo 01
        int numVertices = 7;
        int numArestas = 14;
        
        String arestaA[] = {"b", "c", "f", "g"};
        String arestaB[] = {"a", "c", "d", "f"};
        String arestaC[] = {"b", "a", "e", "d"};
        String arestaD[] = {"c", "b", "g", "e"};
        String arestaE[] = {"d", "c", "g", "f"};
        String arestaF[] = {"e", "b", "a", "g"};
        String arestaG[] = {"f", "e", "d", "a"};
        
        Vertice a = new Vertice("a", 0, arestaA);
        Vertice b = new Vertice("b", 1, arestaB);
        Vertice c = new Vertice("c", 2, arestaC);
        Vertice d = new Vertice("d", 3, arestaD);
        Vertice e = new Vertice("e", 4, arestaE);
        Vertice f = new Vertice("f", 5, arestaF);
        Vertice g = new Vertice("g", 6, arestaG);
        
        //grafo 02
        // int numVertices = 7;
        // int numArestas = 13;
        
        // String arestaA[] = {"b", "c", "f", "g"};
        // String arestaB[] = {"a", "c", "d", "f"};
        // String arestaC[] = {"a", "b", "d"};
        // String arestaD[] = {"b", "c", "e", "g"};
        // String arestaE[] = {"d", "f", "g"};
        // String arestaF[] = {"a", "b", "e", "g"};
        // String arestaG[] = {"a", "d", "e", "f"};
        
        // Vertice a = new Vertice("a", 0, arestaA);
        // Vertice b = new Vertice("b", 1, arestaB);
        // Vertice c = new Vertice("c", 2, arestaC);
        // Vertice d = new Vertice("d", 3, arestaD);
        // Vertice e = new Vertice("e", 4, arestaE);
        // Vertice f = new Vertice("f", 5, arestaF);
        // Vertice g = new Vertice("g", 6, arestaG);
        
        //grafo 03
        // int numVertices = 7;
        // int numArestas = 10;
        
        // String arestaA[] = {"b", "c", "f", "g"};
        // String arestaB[] = {"a", "c"};
        // String arestaC[] = {"a", "b", "d"};
        // String arestaD[] = {"c", "e"};
        // String arestaE[] = {"d", "f", "g"};
        // String arestaF[] = {"a", "e", "g"};
        // String arestaG[] = {"a", "e", "f"};
        
        // Vertice a = new Vertice("a", 0, arestaA);
        // Vertice b = new Vertice("b", 1, arestaB);
        // Vertice c = new Vertice("c", 2, arestaC);
        // Vertice d = new Vertice("d", 3, arestaD);
        // Vertice e = new Vertice("e", 4, arestaE);
        // Vertice f = new Vertice("f", 5, arestaF);
        // Vertice g = new Vertice("g", 6, arestaG);
        
        //grafo 04
        // int numVertices = 7;
        // int numArestas = 9;
        
        // String arestaA[] = {"b", "c", "f", "g"};
        // String arestaB[] = {"a", "c"};
        // String arestaC[] = {"a", "b", "d"};
        // String arestaD[] = {"c", "e"};
        // String arestaE[] = {"d", "f"};
        // String arestaF[] = {"a", "e", "g"};
        // String arestaG[] = {"a", "e", "f"};
        
        // Vertice a = new Vertice("a", 0, arestaA);
        // Vertice b = new Vertice("b", 1, arestaB);
        // Vertice c = new Vertice("c", 2, arestaC);
        // Vertice d = new Vertice("d", 3, arestaD);
        // Vertice e = new Vertice("e", 4, arestaE);
        // Vertice f = new Vertice("f", 5, arestaF);
        // Vertice g = new Vertice("g", 6, arestaG);
        
        Vertice[] vertices = {a, b, c, d, e, f, g};
        
        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(numVertices, numVertices, vertices);

        //Adição das arestas ao grafo
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
        
        System.out.println("Grafo original:\n");
        grafo.imprimirMatriz();
        
        if(grafo.isEuleriano()) {
            System.out.println("\nÉ Euleriano");
        } else {
            System.out.println("\nNão é Euleriano");
        }
        
        if(grafo.notEuleriano()) {
            System.out.println("\nÉ Não Euleriano");
        } else {
            System.out.println("\nNão é Não Euleriano");
        }
        
        if(grafo.isSemeEuleriano()) {
            System.out.println("\nÉ Seme Euleriano");
        } else {
            System.out.println("\nNão é Seme Euleriano");
        }
    }
}
