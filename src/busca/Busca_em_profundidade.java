package busca;

public class Busca_em_profundidade {

	String[] cor = new String[5];
	Integer[] pi = new Integer[5];
	Integer[] d = new Integer[5];
	Integer[] f = new Integer[5];
	int time = 0;
	
	public void dsf() {
		for (int i = 0; i < cor.length; i++) {
			cor[i] = "white";
			pi[i] = null;
			time = 0;
		}
		
		for (int i = 0; i < cor.length; i++) {
			if (cor[i] == "white") {
				dfsvisit(i);
			}
		}
	}
	
	public void dfsvisit(int i) {
		cor[i] = "gray";
		d[i] = time = time+1;
		for (int j = 0; j < cor.length; j++) {
			if (cor[j] == "white") {
				pi[j] = d[j];
				dfsvisit(j);
			}
		}
		
		cor[i] = "black";
		f[i] = time = time + 1;
	}

}
