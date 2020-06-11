package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	private FoodDao dao= new FoodDao();
	private Map<Integer,Portion> idMapPortion= new HashMap<>();
	private Graph<String, DefaultWeightedEdge> graph;
	/**
	 * Costruttore della classe model, inizializzo da subito
	 *  i valori da inserire nella finestra
	 */
	public Model() {
		dao.listAllPortions(idMapPortion);
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
			if(this.graph.getEdge(a.getPortion1(), a.getPortion2())==null)
				Graphs.addEdgeWithVertices(this.graph, a.getPortion1(), a.getPortion2(), a.getPeso());
		}
	}
	public Integer getNumVertici() {
		return this.graph.vertexSet().size();
	}
	public Integer getNumArchi() {
		return this.graph.edgeSet().size();
	}
	public List<String> cercaCorrelate(String source) {
		//TODO finire creando una classe apposita per contentere peso e arco 
		
	}
}
