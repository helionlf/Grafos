import java.util.ArrayList;

class Vertice {
    private String nome;
    private int indice;
    private String[] arestas;
    
    public Vertice(String nome, int indice, String[] arestas) {
        this.nome = nome;
        this.indice = indice;
        this.arestas = arestas;
         
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public int getIndice() {
        return this.indice;
    }
    
    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    public int getTamanhoAresta() {
        return this.arestas.length;
    }
    
    public String getAresta(int i) {
        return this.arestas[i];
    }
    
    public String printArestas() {
        String str = "[ ";
        for(int i = 0; i < arestas.length; i++) {
            str += arestas[i] + " "; 
        }
        return str + "]";
    }
    
}