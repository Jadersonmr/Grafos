package grafos_dijkstra;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Principal {

	static Grafo g = new Grafo();
	static String tipo;
	
	public static void main(String[] args) {		
		ArrayList<Vertice> vertices = new ArrayList<>();
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
    			if (tipo.equals("n")) {
    				Vertice novo = new Vertice();
        			novo.setVertice(JOptionPane.showInputDialog("Digite o vizinho de "+v.getVertice()));
        			adjacentes.add(novo);
        			
        			Aresta aresta = new Aresta();
        			aresta.setAresta(Integer.parseInt(JOptionPane.showInputDialog("Digite o valor da aresta")));
        			arestas.add(aresta);
				} else {
					Vertice origem = new Vertice();
					Vertice destino = new Vertice();
					Aresta aresta = new Aresta();
					
					String res = JOptionPane.showInputDialog("O vizinho é a origem ou o destino?Responda o ou d");
					
					if (res.equals("o")) {//Se o vizinho for a origem
						origem.setVertice(JOptionPane.showInputDialog("Digite o vertice de origem de "+v.getVertice()));
						adjacentes.add(origem);
						aresta.setOrigem(origem);
						
						aresta.setDestino(v);
					} else {//Se o vizinho for o destino
						destino.setVertice(JOptionPane.showInputDialog("Digite o vertice de destino de "+v.getVertice()));
						adjacentes.add(destino);
						aresta.setDestino(destino);
						
						aresta.setOrigem(v);
					}
					
					aresta.setAresta(Integer.parseInt(JOptionPane.showInputDialog("Digite o valor da aresta")));
        		    arestas.add(aresta);
        		}
			}
    		v.setVizinhos(adjacentes);
    		v.setArestas(arestas);
    		vertices.add(v);
		}
        g.setGrafo(vertices);
        
        dijkstra(g);
	}
	
	private static void dijkstra(Grafo g) {
		String inicial = JOptionPane.showInputDialog("Digite o vertice inicial");
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
				if (tipo.equals("s")) {//Se o grafo for orientado
					if (atual.getArestas().get(i).getOrigem().equals(atual)) {//Verifica se o vértice atual é a origem da aresta
						calculaCaminho(atual, fila, i);
					}
				}else {//Se não for orientado
					calculaCaminho(atual, fila, i);
				}
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

}
