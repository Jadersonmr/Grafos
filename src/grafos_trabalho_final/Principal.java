package grafos_trabalho_final;

import java.util.ArrayList;

public class Principal {

	static Grafo g = new Grafo();
	static ArrayList<Vertice> vertices = new ArrayList<>();
	//static String tipo = "n";
	
	public static void main(String[] args) {		
		/*ArrayList<Vertice> vertices = new ArrayList<>();
		tipo = JOptionPane.showInputDialog("Grafo orientado? s/n.");

		//Adicionar novo vertice
    	int n_v = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de vértices"));
    	for (int i = 1; i <= n_v; i++) {//Adiciona os vertices
    		Vertice v = new Vertice();
    		ArrayList<Vertice> adjacentes = new ArrayList<>();//Lista de vizinhos
    		ArrayList<Aresta> arestas = new ArrayList<>();//Lista de arestas
    		
    		v.setVertice(JOptionPane.showInputDialog("Vértice: "+i));
    		
    		int n_adj =  Integer.parseInt(JOptionPane.showInputDialog("Digite o numero de vértices adjacentes"));
    		for (int j = 0; j < n_adj; j++) {//Adiciona os vizinhos e arestas
				Vertice novo = new Vertice();
    			novo.setVertice(JOptionPane.showInputDialog("Digite o vizinho de "+v.getVertice()));
    			adjacentes.add(novo);
    			
    			Aresta aresta = new Aresta();
    			aresta.setAresta(Integer.parseInt(JOptionPane.showInputDialog("Digite o valor da aresta")));
    			arestas.add(aresta);
			}
    		v.setVizinhos(adjacentes);
    		v.setArestas(arestas);
    		vertices.add(v);
		}
        g.setGrafo(vertices);*/
        
		criaGrafo();
		
        dijkstra(g);

        System.out.println(g.getGrafo());

	}
	
	private static void dijkstra(Grafo g) {
		String inicial = "A";
		ArrayList<Vertice> fila = new ArrayList<>();
		
		for (int i = 0; i < g.getGrafo().size(); i++) {//Adiciona distancia infinita para cada um dos vértices ou se for o inicial adiciona distancia 0
			Vertice vertice = g.getGrafo().get(i);//Pega o vertice na posição
			
			vertice.setAnterior(null);
			if (vertice.getVertice().equals(inicial)) {
				vertice.setDistancia(0);
			}else {
				vertice.setDistancia(99999);
			}
			
			for (int j = 0; j < vertice.getVizinhos().size(); j++) {//Adiciona as distâncias aos vizinhos dos vértices
				vertice.getVizinhos().get(j).setDistancia(99999);
				if (vertice.getVizinhos().get(j).getVertice().equals(inicial)) {
					vertice.getVizinhos().get(j).setDistancia(0);
				}
			}
			
			fila.add(vertice);
		}
		
		while (!fila.isEmpty()) {
			Vertice atual = new Vertice();
			atual = fila.get(0);
			int menor = fila.get(0).getDistancia();
			
			for (int i = 0; i < fila.size(); i++) {//Pega o vértice com a menor distancia
				if (fila.get(i).getDistancia() < menor) {
					menor = fila.get(i).getDistancia();
					atual = fila.get(i);
				}
			}
			fila.remove(atual);
			
			for (int i = 0; i < atual.getVizinhos().size(); i++) {
				calculaCaminho(atual, fila, i);
			}

			System.out.println("Vértice: "+atual.getVertice()+" Distância: "+atual.getDistancia()+" Anterior: "+atual.getAnterior());
		}
	}
	
	public static void calculaCaminho(Vertice atual, ArrayList<Vertice> fila, int i) {
		if (atual.getVizinhos().get(i).isVisitado() == false) {
			int alt = atual.getDistancia() + atual.getArestas().get(i).getAresta();//Soma a distância do atual com a aresta que o liga ao vizinho
			if (alt < atual.getVizinhos().get(i).getDistancia()) {
				atual.getVizinhos().get(i).setDistancia(alt);
				atual.getVizinhos().get(i).setAnterior(atual);
				atual.setVisitado(true);
				
				for (int j = 0; j < fila.size(); j++) {//Coloca a distância no vértice da fila
					if (fila.get(j).getVertice().equals(atual.getVizinhos().get(i).getVertice())) {
						fila.get(j).setDistancia(alt);
						fila.get(j).setAnterior(atual);
						fila.get(j).setVisitado(true);
					}
					for (int j2 = 0; j2 < fila.get(j).getVizinhos().size(); j2++) {//Coloca a distância no vizinhos dos vértices da fila
						if (fila.get(j).getVizinhos().get(j2).equals(atual.getVizinhos().get(i))) {
							fila.get(j).getVizinhos().get(j2).setDistancia(alt);
							fila.get(j).getVizinhos().get(j2).setAnterior(atual);
							fila.get(j).getVizinhos().get(j2).setVisitado(true);
						}
					}
				}
			}
		}
	}

	public static void criaGrafo() {
		ArrayList<Aresta> arestas = new ArrayList<>();
		ArrayList<Vertice> adjacentes = new ArrayList<>();//Lista de vizinhos
		
		Vertice v = new Vertice();
		Vertice adj = new Vertice();
		Aresta aresta = new Aresta();
		
		v.setVertice("A");
		adj.setVertice("B");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		adj = new Vertice();
		aresta = new Aresta();
		adj.setVertice("C");
		adjacentes.add(adj);
		aresta.setAresta(700);
		arestas.add(aresta);
		adj = new Vertice();
		aresta = new Aresta();
		adj.setVertice("G");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		adj = new Vertice();
		aresta = new Aresta();
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		vertices.add(v);
		arestas.clear();
		adjacentes.clear();
		
		v = new Vertice();
		
		v.setVertice("B");
		adj.setVertice("A");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		adj = new Vertice();
		aresta = new Aresta();
		adj.setVertice("E");
		adjacentes.add(adj);
		aresta.setAresta(400);
		arestas.add(aresta);
		adj = new Vertice();
		aresta = new Aresta();
		adj.setVertice("F");
		adjacentes.add(adj);
		aresta.setAresta(750);
		arestas.add(aresta);
		adj = new Vertice();
		aresta = new Aresta();
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("C");
		adj.setVertice("A");
		adjacentes.add(adj);
		aresta.setAresta(700);
		arestas.add(aresta);
		adj = new Vertice();
		aresta = new Aresta();
		adj.setVertice("J");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		adj = new Vertice();
		aresta = new Aresta();
		adj.setVertice("D");
		adjacentes.add(adj);
		aresta.setAresta(300);
		arestas.add(aresta);
		adj = new Vertice();
		aresta = new Aresta();
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		adj = new Vertice();
		aresta = new Aresta();
		
		v.setVertice("D");
		adj.setVertice("C");
		adjacentes.add(adj);
		aresta.setAresta(300);
		arestas.add(aresta);
		adj.setVertice("J");
		adjacentes.add(adj);
		aresta.setAresta(1100);
		arestas.add(aresta);
		adj.setVertice("R");
		adjacentes.add(adj);
		aresta.setAresta(700);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("E");
		adj.setVertice("B");
		adjacentes.add(adj);
		aresta.setAresta(400);
		arestas.add(aresta);
		adj.setVertice("H");
		adjacentes.add(adj);
		aresta.setAresta(800);
		arestas.add(aresta);
		adj.setVertice("I");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("F");
		adj.setVertice("B");
		adjacentes.add(adj);
		aresta.setAresta(750);
		arestas.add(aresta);
		adj.setVertice("G");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		adj.setVertice("H");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("G");
		adj.setVertice("A");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		adj.setVertice("F");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		adj.setVertice("J");
		adjacentes.add(adj);
		aresta.setAresta(900);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("H");
		adj.setVertice("E");
		adjacentes.add(adj);
		aresta.setAresta(800);
		arestas.add(aresta);
		adj.setVertice("F");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		adj.setVertice("J");
		adjacentes.add(adj);
		aresta.setAresta(800);
		arestas.add(aresta);
		adj.setVertice("M");
		adjacentes.add(adj);
		aresta.setAresta(700);
		arestas.add(aresta);
		adj.setVertice("L");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("I");
		adj.setVertice("E");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		adj.setVertice("M");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("J");
		adj.setVertice("G");
		adjacentes.add(adj);
		aresta.setAresta(900);
		arestas.add(aresta);
		adj.setVertice("C");
		adjacentes.add(adj);
		aresta.setAresta(1200);
		arestas.add(aresta);
		adj.setVertice("D");
		adjacentes.add(adj);
		aresta.setAresta(1100);
		arestas.add(aresta);
		adj.setVertice("K");
		adjacentes.add(adj);
		aresta.setAresta(1400);
		arestas.add(aresta);
		adj.setVertice("H");
		adjacentes.add(adj);
		aresta.setAresta(800);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("K");
		adj.setVertice("J");
		adjacentes.add(adj);
		aresta.setAresta(1400);
		arestas.add(aresta);
		adj.setVertice("L");
		adjacentes.add(adj);
		aresta.setAresta(300);
		arestas.add(aresta);
		adj.setVertice("R");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		adj.setVertice("N");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("L");
		adj.setVertice("H");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		adj.setVertice("K");
		adjacentes.add(adj);
		aresta.setAresta(300);
		arestas.add(aresta);
		adj.setVertice("O");
		adjacentes.add(adj);
		aresta.setAresta(800);
		arestas.add(aresta);
		adj.setVertice("N");
		adjacentes.add(adj);
		aresta.setAresta(1200);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("M");
		adj.setVertice("I");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		adj.setVertice("H");
		adjacentes.add(adj);
		aresta.setAresta(700);
		arestas.add(aresta);
		adj.setVertice("O");
		adjacentes.add(adj);
		aresta.setAresta(900);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("N");
		adj.setVertice("L");
		adjacentes.add(adj);
		aresta.setAresta(1200);
		arestas.add(aresta);
		adj.setVertice("K");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		adj.setVertice("S");
		adjacentes.add(adj);
		aresta.setAresta(800);
		arestas.add(aresta);
		adj.setVertice("P");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("O");
		adj.setVertice("M");
		adjacentes.add(adj);
		aresta.setAresta(900);
		arestas.add(aresta);
		adj.setVertice("L");
		adjacentes.add(adj);
		aresta.setAresta(800);
		arestas.add(aresta);
		adj.setVertice("P");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("P");
		adj.setVertice("O");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		adj.setVertice("N");
		adjacentes.add(adj);
		aresta.setAresta(600);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("R");
		adj.setVertice("D");
		adjacentes.add(adj);
		aresta.setAresta(700);
		arestas.add(aresta);
		adj.setVertice("K");
		adjacentes.add(adj);
		aresta.setAresta(500);
		arestas.add(aresta);
		adj.setVertice("S");
		adjacentes.add(adj);
		aresta.setAresta(1000);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		v.setVertice("S");
		adj.setVertice("R");
		adjacentes.add(adj);
		aresta.setAresta(1000);
		arestas.add(aresta);
		adj.setVertice("N");
		adjacentes.add(adj);
		aresta.setAresta(800);
		arestas.add(aresta);
		v.setVizinhos(adjacentes);
		v.setArestas(arestas);
		arestas.clear();
		adjacentes.clear();
		vertices.add(v);
		
		v = new Vertice();
		
		g.setGrafo(vertices);
	}
}
