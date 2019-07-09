package grafos_trabalho_3;

import java.util.ArrayList;

public class Vertice {

	private String vertice;
	private ArrayList<Vertice> vizinhos = new ArrayList<>();
	private ArrayList<Aresta> arestas = new ArrayList<>();
	private int peso;
	private Vertice anterior;
	private int pai;
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
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
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
	public int getPai() {
		return pai;
	}
	public void setPai(int pai) {
		this.pai = pai;
	}
	
}
