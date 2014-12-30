package de.treelayout;
import de.treelayout.Edge;
import de.treelayout.Node;
import edu.uci.ics.jung.graph.Forest;
import java.util.Collection;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raazia
 */
public class GraphC<V, E> {
    
    //private Collection<V> vertices;
    //private Edge ed;
    //private int id;
    Forest<V,E> forest;
// konstrucker mit forest Graph;
    
    public GraphC(Forest<V,E> graph){
        forest  = graph;
    
       
    }
  //konstr. mit andere Graph
    //public GraphC( Graph2 Graph){
 
  
  //}
    //konstr. mit andere Graph
    //public GraphC( Graph3 Graph){
  
  
 // }
  
    
  
    
    public Forest<V, E> getForest() {
        return forest;
    }
    
    
}
