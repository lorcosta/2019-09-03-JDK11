package it.polito.tdp.food.model;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	private FoodDao dao= new FoodDao();
	private Graph<String, DefaultWeightedEdge> graph;
	private List<String> cammino=new ArrayList<>();
	private Double bestPeso=0.0;
	
	/**
	 * Costruttore della classe model, inizializzo da subito
	 *  i valori da inserire nella finestra
	 */
	public Model() {
	}
	/**
	 *Scorro tutti gli elementi per tornare i DISTINCT
	 */
	public List<String> getPortion(){
		 return dao.getDifferentPortions();
	}

	public void creaGrafo(Double calorie) {
		this.graph= new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		List<String> vertici= dao.getPortionVertices(calorie);
		//aggiungo i vertici
		Graphs.addAllVertices(this.graph, vertici);
		//aggiungo gli archi
		List<Adiacenze> adiacenze=dao.getPortionEdges();
		for(Adiacenze a:adiacenze) {
			if(this.graph.vertexSet().contains(a.getPortion1()) && this.graph.vertexSet().contains(a.getPortion2()))
				Graphs.addEdgeWithVertices(this.graph, a.getPortion1(), a.getPortion2(), a.getPeso());
		}
	}
	public Set<String> getVertici() {
		return this.graph.vertexSet();
	}
	public Integer getNumArchi() {
		return this.graph.edgeSet().size();
	}
	public List<Correlate> cercaCorrelate(String source) {
		List<Correlate> result=new ArrayList<>();
		List<String> correlati=Graphs.neighborListOf(this.graph, source);
		for(String s:correlati) {
			result.add(new Correlate(s,this.graph.getEdgeWeight(this.graph.getEdge(source, s))));
		}
		return result;
		
	}
	public List<String> cercaCammino(Integer passi, String source) {
		Integer livello=0;
		List<String> parziale=new ArrayList<>();
		parziale.add(source);
		ricorsione(parziale,livello,passi);
		return cammino;
	}
	private void ricorsione(List<String> parziale, Integer livello, Integer passi) {
		if(livello==passi) {
			if(calcolaPeso(parziale)>bestPeso) {
				cammino=new ArrayList<String>(parziale);
				bestPeso=calcolaPeso(parziale);
			}
		}
		for(String vicino:Graphs.neighborListOf(this.graph, parziale.get(parziale.size()-1))) {
			if(!parziale.contains(vicino)) {
				parziale.add(vicino);
				ricorsione(parziale,livello++,passi);
				parziale.remove(parziale.size()-1);
			}
		}
		
	}
	private Double calcolaPeso(List<String> parziale) {
		Double peso=0.0;
		for(int i=1;i<parziale.size()-1;i++) {
			peso+=this.graph.getEdgeWeight(this.graph.getEdge(parziale.get(i-1), parziale.get(i)));
		}
		return peso;
	}
	public Double getPesoCammino() {
		return bestPeso;
	}
}
