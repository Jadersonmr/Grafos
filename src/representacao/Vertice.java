package representacao;

import java.util.ArrayList;

public class Vertice {

	String vertice;
	ArrayList<String> aresta = new ArrayList<String>();
	
	public String getVertice() {
		return vertice;
	}
	public void setVertice(String vertice) {
		this.vertice = vertice;
	}
	public ArrayList<String> getAresta() {
		return aresta;
	}
	public void setAresta(ArrayList<String> aresta) {
		this.aresta = aresta;
	}

}
