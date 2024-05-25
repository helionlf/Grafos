package grafosAdjacencia;

import java.awt.*;
import javax.swing.*;

public class GrafoVisualizacao extends JPanel {
	private GrafoMatrizAdjacencia grafo;
    private int diametroVertice = 30;

    GrafoVisualizacao(GrafoMatrizAdjacencia grafo) {
        this.grafo = grafo;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenharGrafo(g);
    }

    private void desenharGrafo(Graphics g) {
        int numVertices = grafo.getNumVertices();
        String[] nomesVertices = grafo.getNomesVertices(); // Supondo que você tenha esse método que retorna os nomes dos vértices

        for (int i = 0; i < numVertices; i++) {
            int x = (int) (getWidth() * (0.5 + 0.4 * Math.cos(2 * Math.PI * i / numVertices)));
            int y = (int) (getHeight() * (0.5 + 0.4 * Math.sin(2 * Math.PI * i / numVertices)));

            // Desenhar vértice
            g.setColor(Color.BLACK);
            g.fillOval(x - diametroVertice / 2, y - diametroVertice / 2, diametroVertice, diametroVertice);
            g.setColor(Color.WHITE);
            g.drawString(nomesVertices[i], x - 5, y + 5);

            for (int j = 0; j < numVertices; j++) {
                if (grafo.existeAresta(i, j) && i != j) {
                    // Desenhar aresta
                    int x2 = (int) (getWidth() * (0.5 + 0.4 * Math.cos(2 * Math.PI * j / numVertices)));
                    int y2 = (int) (getHeight() * (0.5 + 0.4 * Math.sin(2 * Math.PI * j / numVertices)));
                    g.setColor(Color.BLACK);
                    g.drawLine(x, y, x2, y2);
                }
            }
        }
    }
}
