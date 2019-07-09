package grafos_dijkstra;

import java.util.ArrayList;

public class Vertice {

	private String vertice;
	private ArrayList<Vertice> vizinhos = new ArrayList<>();
	private ArrayList<Aresta> arestas = new ArrayList<>();
	private int distancia;
	private Vertice anterior;
	private boolean visitado = false;
	
	@Override
	public String toString() {
		return this.getVertice();
	}
	
	public String getVertice() {
		return vertice;
	}
	public void setVertice(String vertice) {
		this.vertice = vertice;
	}
	public ArrayList<Vertice> getVizinhos() {
		return vizinhos;
	}
	public void setVizinhos(ArrayList<Vertice> vizinhos) {
		this.vizinhos = vizinhos;
	}
	public ArrayList<Aresta> getArestas() {
		return arestas;
	}
	public void setArestas(ArrayList<Aresta> arestas) {
		this.arestas = arestas;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	public Vertice getAnterior() {
		return anterior;
	}
	public void setAnterior(Vertice anterior) {
		this.anterior = anterior;
	}
	public boolean isVisitado() {
		return visitado;
	}
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	
}
