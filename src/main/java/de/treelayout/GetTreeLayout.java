/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.treelayout;

import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Forest;
import eu.mihosoft.vrl.workflow.Connector;
import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VFlowModel;


import eu.mihosoft.vrl.workflow.VNode;
import java.awt.geom.Point2D;
import java.util.Iterator;
import javafx.collections.ObservableList;
import org.apache.commons.collections15.Factory;

/**
 *
 * @author Raazia
 */
public class GetTreeLayout {
    
    private Forest<VNode,Integer> graph;
    private VFlow flow;
    
    String root;
    
    private TreeLayout<VNode,Integer> treeLayout;

    public GetTreeLayout(VFlow flow) {
        this.flow = flow;
    }

    
    
    /*private void getChildernNodes(VNode node){
     System.out.println("--->  "  +node.getId());
    
        ObservableList<Connector> temp = node.getConnectors();
        
        
        
        System.out.println(temp.size());
        
        
        
        if(node.getOutputs()== null || node.getOutputs().size()==0 ){}
            
        else{
            for (Connector connector : temp) {
                graph.addEdge(edgeFactory.create(),node, connector.getNode());
                getChildernNodes(connector.getNode());
            }
            
        }*/
    
    private void getChildernNodes(VNode node, int i) {
        //node.get
         
        if (node instanceof VFlowModel) {
            
            VFlowModel flow = (VFlowModel) node;
           

            if (flow.getNodes() == null || +
                    flow.getNodes().size() == 0
                    || i >= flow.getNodes().size()) {
                --i;
            } 
            else {
                for (VNode child : flow.getNodes()) {
                   graph.addEdge(edgeFactory.create(), node, flow.getNodes().get(i));
                    
                    getChildernNodes(child.getFlow(), ++i);
                }

            }
        }

    }
        
    
    
    public void generateTreeLayout(){
        
        this.graph = new DelegateForest<VNode,Integer>();
        int counter = 0;
        VNode prevNode = null;
        flow.getNodes().get(0).setTitle("1K");
        flow.getNodes().get(1).setTitle("2Kn");
     
         flow.getNodes().get(2).setTitle("3Kn");
        System.out.println("--------"   +flow.getNodes().get(1).getTitle());
           System.out.println("öööö------"+flow.getNodes().get(1).getId());
           //System.out.println("öööö-------" +flow.getNodes().get(2));
           //System.out.println("ayayö--------"+flow.getNodes().get(1));
           
        graph.addVertex(flow.getNodes().get(0));
        
//        getChildernNodes(flow.getNodes().get(0), 0);
        getChildernNodes(flow.getModel(), 0);
       
        
        /*graph.addEdge(edgeFactory.create(),flow.getNodes().get(0),flow.getNodes().get(1));
        graph.addEdge(edgeFactory.create(),flow.getNodes().get(0),flow.getNodes().get(2));
        graph.addEdge(edgeFactory.create(),flow.getNodes().get(1),flow.getNodes().get(3));
        graph.addEdge(edgeFactory.create(),flow.getNodes().get(1),flow.getNodes().get(4));
        graph.addEdge(edgeFactory.create(),flow.getNodes().get(2),flow.getNodes().get(5));
        graph.addEdge(edgeFactory.create(),flow.getNodes().get(2),flow.getNodes().get(6));
        graph.addEdge(edgeFactory.create(),flow.getNodes().get(3),flow.getNodes().get(7));
        graph.addEdge(edgeFactory.create(),flow.getNodes().get(3),flow.getNodes().get(8));
        */
        
        
        
       /* for (Iterator it = flow.getNodes().iterator(); it.hasNext();) {
            
            VNode ver = (VNode)it.next();
             
             
            if(counter != 0){
                graph.addEdge(edgeFactory.create(), prevNode, ver);
            }
            else{
                graph.addVertex(ver);
            }
            
            prevNode = ver;
            counter++;
          // ver.setX();
        }*/
        
        
       	
        
        
        this.treeLayout = new TreeLayout<VNode,Integer>(graph);
    } 

    public TreeLayout<VNode, Integer> getTreeLayout() {
        return treeLayout;
    }

    public Forest<VNode, Integer> getGraph() {
        return graph;
    }
    
     
    Factory<Integer> edgeFactory = new Factory<Integer>() {
		int i=0;
		public Integer create() {
			return i++;
		}};
    
}
