package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	
	public List<String> createGraph(int numeroLettere) {
		WordDAO dao= new WordDAO();
		Graphs.addAllVertices(grafo, dao.getAllWordsFixedLength(numeroLettere));
		
		for(String s:grafo.vertexSet()){
			connessioneParola(s);
		}
		
			return new ArrayList<String>(grafo.vertexSet());
	}

	private void connessioneParola(String s) {
		for(String p:grafo.vertexSet())
		{
			int count=0;
			
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)!=p.charAt(i))
					count++;
			}
			
			if(count==1)
				grafo.addEdge(s, p);
		}		
		
		
	}

	public List<String> displayNeighbours(String parolaInserita) {
		  return Graphs.neighborListOf(grafo, parolaInserita);
	}

	public String findMaxDegree() {
		int max=0;
		String risultato="";
		for( String s: grafo.vertexSet() ) { 
		for( DefaultEdge e: grafo.edgesOf(s) ) {
		     if(grafo.degreeOf(s)>max) {
		    	 max=grafo.degreeOf(s);
		    	 risultato=s;
		     }
		}
	}
		return risultato;
		
	}
	
}
