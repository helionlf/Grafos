public class Main {
    public static void main(String[] args) {
        int numVertices = 27;
        int numArestas = 95;

        // Definição das arestas de cada estado
        String rsAresta[] = {"sc"};
        String scAresta[] = {"rs", "pr"};
        String prAresta[] = {"sp", "ms"};
        String spAresta[] = {"pr", "ms", "mg", "rj"};
        String rjAresta[] = {"sp", "mg", "es"};
        String esAresta[] = {"rj", "mg", "ba"};
        String mgAresta[] = {"sp", "rj", "es", "ba", "df", "go", "ms"};
        String dfAresta[] = {"mg", "go"};
        String goAresta[] = {"ba", "df", "mg", "mt"};
        String baAresta[] = {"mg", "es", "se", "al", "pe", "pi", "ma", "to", "go"};
        String seAresta[] = {"ba", "al"};
        String alAresta[] = {"se", "ba", "pe"};
        String peAresta[] = {"al", "ba", "pi", "ce", "pb"};
        String pbAresta[] = {"pe", "ce", "rn"};
        String rnAresta[] = {"pb", "ce"};
        String ceAresta[] = {"rn", "pb", "pe", "pi"};
        String piAresta[] = {"ce", "pe", "ba", "to", "ma"};
        String toAresta[] = {"ba", "pi", "mt", "pa"};
        String paAresta[] = {"to", "mt", "am", "rr", "ap", "ma"};
        String apAresta[] = {"pa"};
        String maAresta[] = {"pi", "ba", "pa"};
        String rrAresta[] = {"pa", "am"};
        String amAresta[] = {"rr", "pa", "mt", "ro", "ac"};
        String acAresta[] = {"am"};
        String roAresta[] = {"am", "mt"};
        String mtAresta[] = {"ro", "ms", "go", "to", "pa", "am"};
        String msAresta[] = {"pr", "sp", "mg", "mt"};

        // Criação dos estados
        Estado rs = new Estado("rs", 0, rsAresta);
        Estado sc = new Estado("sc", 1, scAresta);
        Estado pr = new Estado("pr", 2, prAresta);
        Estado sp = new Estado("sp", 3, spAresta);
        Estado rj = new Estado("rj", 4, rjAresta);
        Estado es = new Estado("es", 5, esAresta);
        Estado mg = new Estado("mg", 6, mgAresta);
        Estado df = new Estado("df", 7, dfAresta);
        Estado go = new Estado("go", 8, goAresta);
        Estado ba = new Estado("ba", 9, baAresta);
        Estado se = new Estado("se", 10, seAresta);
        Estado al = new Estado("al", 11, alAresta);
        Estado pe = new Estado("pe", 12, peAresta);
        Estado pb = new Estado("pb", 13, pbAresta);
        Estado rn = new Estado("rn", 14, rnAresta);
        Estado ce = new Estado("ce", 15, ceAresta);
        Estado pi = new Estado("pi", 16, piAresta);
        Estado to = new Estado("to", 17, toAresta);
        Estado pa = new Estado("pa", 18, paAresta);
        Estado ap = new Estado("ap", 19, apAresta);
        Estado ma = new Estado("ma", 20, maAresta);
        Estado rr = new Estado("rr", 21, rrAresta);
        Estado am = new Estado("am", 22, amAresta);
        Estado ac = new Estado("ac", 23, acAresta);
        Estado ro = new Estado("ro", 24, roAresta);
        Estado mt = new Estado("mt", 25, mtAresta);
        Estado ms = new Estado("ms", 26, msAresta);

        Estado[] estados = {rs, sc, pr, sp, rj, es, mg, df, go, ba, se, al, pe, pb, rn, ce, pi, to, pa, ap, ma, rr, am, ac, ro, mt, ms};

        GrafoMatrizAdjacencia grafo = new GrafoMatrizAdjacencia(numVertices, numVertices, estados);

        //Adição das arestas ao grafo
        for (Estado estado : estados) {
            for (int i = 0; i < estado.getTamanhoAresta(); i++) {
                for(int j = 0; j < estados.length; j++) {
                    int destino = 0;
                    if (estado.getArestas(i).equals(estados[j].getNome())) {
                        destino = estados[j].getIndice();
                        grafo.adicionarAresta(estado.getIndice(), destino);
                    }
                }
            }
        }
        
        
                    
        System.out.println("Grafo representado como matriz Adjacente: \n");
        grafo.imprimirMatriz();
        
        System.out.println("\nEstado(s) com maior número de arestas:");
        
        String estadoComMaisArestas = "";
        for(int i = 0; i < estados.length; i++) {
            if(estados[i].getTamanhoAresta() == grafo.verticeComMaisArestas()) {
                estadoComMaisArestas = estados[i].getNome() + " " + estados[i].printArestas();
                System.out.println(estadoComMaisArestas);
            }
        }
        
        System.out.println("\nEstado(s) com menor número de arestas:");
        
        String estadoComMenosArestas = "";
        for(int i = 0; i < estados.length; i++) {
            if(estados[i].getTamanhoAresta() == grafo.verticeComMenosArestas()) {
                estadoComMenosArestas = estados[i].getNome() + " " + estados[i].printArestas();
                System.out.println(estadoComMenosArestas);
            }
        }
        
        grafo.relacaoVerticesArestas();
    }
}