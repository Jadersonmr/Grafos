package representacao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Representacao {

    private static int menu(){
		String m = "1 - Cadastrar vertice\n"
				+ "0 - Sair";
		return Integer.parseInt(JOptionPane.showInputDialog(m));
    }
    
	public static void main(String[] args) {
		
		String tipo = JOptionPane.showInputDialog("Grafo orientado? s/n.");
		String valorado = JOptionPane.showInputDialog("Grafo valorado? s/n.");
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();

		//menu
		int op = 0;
        do {            
            op = menu();
            switch (op){
                case 1:
                	ArrayList<String> a = new ArrayList<String>();
            		Vertice v = new Vertice();
        			v.setVertice(JOptionPane.showInputDialog("Digite o vertice."));
        			
        			int res;
        			do {
        				res = JOptionPane.showConfirmDialog(null, "Adicionar aresta?");
        				if (res == 0) {
        					a.add(JOptionPane.showInputDialog("Digite o aresta."));
						} 				        				
					} while (res == 0);
        			
        			if (!a.isEmpty()) {
        				v.setAresta(a);
					}
        			vertices.add(v);
                	break;
            }
        } while (op != 0);

		int t = vertices.size();
		
		//Lista de arestas -----------------------------------------------
		System.out.println("Lista de arestas.");
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < vertices.get(i).getAresta().size(); j++) {
				System.out.print(vertices.get(i).getVertice());
				System.out.println(vertices.get(i).getAresta().get(j));
			}
		}
		System.out.println();
		
		//Lista de adjacência---------------------------------------------------------
		System.out.println("Lista de adjacência.");
		for (int i = 0; i < t; i++) {
			System.out.print(vertices.get(i).getVertice());
			for (int j = 0; j < vertices.get(i).getAresta().size(); j++) {
				System.out.println(vertices.get(i).getAresta().get(j));
			}
		}
		System.out.println();
		
		//Matriz de adjacência ---------------------------------------------------
		String[][] matriz_adj = new String[t][t];
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < t; j++) {
				matriz_adj[i][j] = vertices.get(j).getVertice();
			}
		}
		
		System.out.println("Matriz de adjacência.");
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < vertices.get(i).getAresta().size(); j++) {
				String x = vertices.get(i).getAresta().get(j);
				if (!x.isEmpty() && x.equals(matriz_adj[i][j])) {
					if (valorado.equals("s")) {
						matriz_adj[i][j] = vertices.get(i).getAresta().get(j);
						if (tipo.equals("n")) {
							matriz_adj[j][i] = vertices.get(i).getAresta().get(j);
						}
					} else {
						matriz_adj[i][j] = "1";
						if (tipo.equals("n")) {
							matriz_adj[j][i] = "1";
						}
					}
				}else {
					matriz_adj[i][j] = "0";
					matriz_adj[j][i] = "0";
				}
			}
		}
		
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < t; j++) {
				System.out.print(matriz_adj[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		//Matriz de incidência------------------------------------------------------------
		String[][] matriz_inc = new String[t][t];
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < t; j++) {
				matriz_inc[i][j] = vertices.get(j).getVertice();
			}
		}
		
		System.out.println("Matriz de incidência.");
		ArrayList<String> inc = new ArrayList<String>();
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < vertices.get(i).getAresta().size(); j++) {
				inc.add(vertices.get(i).getAresta().get(j));
			}
		}
		
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < inc.size(); j++) {
				if (inc.get(i).equals(matriz_inc[i][j])) {
					matriz_inc[i][j] = "1";
				}else if (inc.get(i).equals(matriz_inc[j][i])) {
					matriz_inc[j][i] = "-1";
				}else {
					matriz_inc[i][j] = "0";
				}
			}
		}
	
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < t; j++) {
				System.out.print(matriz_inc[i][j]);
			}
			System.out.println();
		}
		System.out.println();

	}
}
