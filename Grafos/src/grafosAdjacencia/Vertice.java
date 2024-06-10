package grafosAdjacencia;

public class Vertice {
	private String nome;
    private int indice;
    private String[] arestas;
    private boolean visitado;
    private boolean descoberto;
    private int distancia;
    
    public Vertice(String nome, int indice) {
        this.nome = nome;
        this.indice = indice;
        this.visitado = false;
        this.descoberto = false;
        this.distancia = Integer.MAX_VALUE; // Inicializa com infinito
         
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
    
    public void visitar() {
    	this.visitado = true;
    }
    
    public boolean foiVisitado() {
    	return visitado;
    }
    
    public void descobrir() {
    	this.descoberto = true;
    }
    
    public boolean foiDescoberto() {
    	return descoberto;
    }
    
    public void reiniciarVertice() {
    	this.visitado = false;
    	this.descoberto = false;
    }
    
    public void setDistancia(int distancia) {
    	this.distancia = distancia;
    }
    
    public int getDistancia() {
    	return distancia;
    }
}
