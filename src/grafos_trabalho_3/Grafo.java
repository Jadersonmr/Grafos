package grafos_trabalho_3;

import java.util.ArrayList;

public class Grafo {

	private ArrayList<Vertice> grafo = new ArrayList<>();
	private ArrayList<Aresta> arestas = new ArrayList<>();
	
	public int tamanho() {
		return getGrafo().size();
	}
	
	public ArrayList<Vertice> getGrafo() {
		return grafo;
	}

	public void setGrafo(ArrayList<Vertice> grafo) {
		this.grafo = grafo;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(ArrayList<Aresta> arestas) {
		this.arestas = arestas;
	}

}
