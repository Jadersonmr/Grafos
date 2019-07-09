package grafos_trabalho_3;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Principal {

	static Grafo g = new Grafo();
	
	public static void main(String[] args) {
		ArrayList<Vertice> vertices = new ArrayList<>();
		
		int nv = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de vértices"));
		for (int i = 1; i <= nv; i++) {
			Vertice v = new Vertice();
			v.setPeso(Integer.parseInt(JOptionPane.showInputDialog("Vértice: "+i)));
			v.setPai(v.getPeso());
			vertices.add(v);
		}
		
		int n_adj = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de arestas"));
		ArrayList<Aresta> arestas = new ArrayList<>();
		for (int j = 0; j < n_adj; j++) {
			Aresta aresta = new Aresta();
			aresta.setAresta(Integer.parseInt(JOptionPane.showInputDialog("Digite o peso da aresta")));
			
			Vertice origem = new Vertice();
			origem.setPeso(Integer.parseInt(JOptionPane.showInputDialog("Digite a origem")));
			origem.setPai(origem.getPeso());
			aresta.setOrigem(origem);
			
			Vertice destino = new Vertice();
			destino.setPeso(Integer.parseInt(JOptionPane.showInputDialog("Digite a destino")));
			destino.setPai(destino.getPeso());
			aresta.setDestino(destino);
			
			arestas.add(aresta);
		}
		
		g.setGrafo(vertices);
		g.setArestas(arestas);
		
		kruskal(g);
		
		PrimJarnik(g);
	}
	
	private static void kruskal(Grafo g) {
		ArrayList<Aresta> arestas = new ArrayList<>();
		
		for (int i = 0; i < g.getArestas().size(); i++) {//Ordena as arestas em ordem crescente
			ordenaAresta(g.getArestas());
		}
		
		for (int i = 0; i < g.tamanho()-1; i++) {
			int u = g.getArestas().get(i).getOrigem().getPai();
			int v = g.getArestas().get(i).getDestino().getPai();
			if (u != v) {
				g.getArestas().get(i).getOrigem().setPai(v);
				arestas.add(g.getArestas().get(i));
			}
		}
		
		int custo = 0;
		System.out.println("--------Kruskal----------");
		System.out.println("Arestas: ");
		for (int i = 0; i < arestas.size(); i++) {
			System.out.print(arestas.get(i).getAresta()+" - ");
			custo += arestas.get(i).getAresta();
		}
		System.out.println("\n Custo: "+custo);
	}
	
	private static void PrimJarnik(Grafo g) {
		ArrayList<Integer> arvConj = new ArrayList<>();
		
		g.getGrafo().get(0).setPeso(0);
		int atual = g.getGrafo().get(0).getPeso();
		int a = 999;
		arvConj.add(atual);

		while (arvConj.size() < g.tamanho()) {
		
			for (int i = 0; i < g.getArestas().size(); i++) {
				if (atual == g.getArestas().get(i).getOrigem().getPeso()) {
					for (int j = 0; j < arvConj.size(); j++) {
						if (g.getArestas().get(i).getDestino().getPeso() != arvConj.get(j)) {
							int anterior = g.getArestas().get(i).getDestino().getPeso();
							g.getArestas().get(i).getDestino().setPeso(g.getArestas().get(i).getAresta());
							int novo = g.getArestas().get(i).getDestino().getPeso();
							if (novo < a) {
								a = novo;
							}
							alteraAresta(novo, anterior);
						}
					}
				}
				if (atual == g.getArestas().get(i).getDestino().getPeso()) {
					for (int j = 0; j < arvConj.size(); j++) {
						if (g.getArestas().get(i).getOrigem().getPeso() != arvConj.get(j)) {
							int anterior = g.getArestas().get(i).getOrigem().getPeso();
							g.getArestas().get(i).getOrigem().setPeso(g.getArestas().get(i).getAresta());
							int novo = g.getArestas().get(i).getOrigem().getPeso();
							if (novo < a) {
								a = novo;
							}
							alteraAresta(novo, anterior);
						}
					}				
				}
			}
			
			atual = a;
			arvConj.add(atual);
		}
		
		int custo = 0;
		System.out.println("\n----------Prim-Jarnik---------");
		System.out.println("Arestas: ");
		for (int i = 1; i < arvConj.size(); i++) {
			System.out.print(arvConj.get(i)+" - ");
			custo += arvConj.get(i);
		}
		System.out.println("\n Custo: "+custo);
	}
	
	public static void alteraAresta(int peso, int anterior) {
		for (int i = 0; i < g.getArestas().size(); i++) {
			if (g.getArestas().get(i).getOrigem().getPeso() == anterior) {
				g.getArestas().get(i).getOrigem().setPeso(peso);
			}
			if (g.getArestas().get(i).getDestino().getPeso() == anterior) {
				g.getArestas().get(i).getDestino().setPeso(peso);
			}
		}
	}
	
	
	static void ordenaAresta(ArrayList<Aresta> v){
		int pos = 0;
		int ultima = v.size()-1;
		Aresta aux;
		
		while (ultima != 0){
			while (pos != ultima) {
				if (v.get(pos).getAresta() > v.get(pos+1).getAresta()) {
					aux = v.get(pos);
					v.set(pos, v.get(pos+1));
					v.set(pos+1, aux);
				}
				pos++;
			}
			pos = 0;
			ultima--;
		}
	}
	
	static void ordenaVertice(ArrayList<Vertice> v){
		int pos = 0;
		int ultima = v.size()-1;
		Vertice aux;
		
		while (ultima != 0){
			while (pos != ultima) {
				if (v.get(pos).getPeso() > v.get(pos+1).getPeso()) {
					aux = v.get(pos);
					v.set(pos, v.get(pos+1));
					v.set(pos+1, aux);
				}
				pos++;
			}
			pos = 0;
			ultima--;
		}
	}
	
}
