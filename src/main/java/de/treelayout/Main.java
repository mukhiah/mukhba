package de.treelayout;

import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Tree;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import eu.mihosoft.vrl.workflow.Connection;
import eu.mihosoft.vrl.workflow.Connector;
import javafx.application.Application;
import javafx.stage.Stage;

import eu.mihosoft.vrl.workflow.FlowFactory;
import eu.mihosoft.vrl.workflow.VFlow;
import eu.mihosoft.vrl.workflow.VNode;
import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
//import eu.mihosoft.vrl.workflow.fx.FXSkinFactory;
import javafx.scene.Scene;
import jfxtras.labs.scene.layout.ScalableContentPane;
import org.apache.commons.collections15.Factory;

/**
 *
 *
 */
public class Main extends Application {

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *     
* @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Only for TESTING, we do not need it as we get the Flow graph
        // which should already be filled
        VFlow flow = FlowFactory.newFlow();
        createTree(flow);

        /**
         * *****This is out code which produces Treelayout
         */
        GetTreeLayout gtl = new GetTreeLayout(flow);
        gtl.generateTreeLayout();

        /**
         * *********************************************
         */
        // make it visible
        flow.setVisible(true);

        // create scalable root pane
        ScalableContentPane canvas = new ScalableContentPane();

        // define background style
        canvas.setStyle("-fx-background-color: linear-gradient(to bottom, rgb(10,32,60), rgb(42,52,120));");

        // add two nodes to the flow
        int counter = 0;
        VNode prevNode = null;
        for (Iterator it = gtl.getTreeLayout().getGraph().getVertices().iterator(); it.hasNext();) {
            VNode ver = (VNode) it.next();
            Point2D point = gtl.getTreeLayout().locations.get(ver);

            Connector input = ver.addInput("data");
            Connector output = ver.addOutput("data");

            ver.setX(point.getX() * 10);
            ver.setY(point.getY() * 10);
            System.out.println("Xposition=>" + ver.getX() + ":Yposition=>" + ver.getY());
            counter++;
            // ver.setX();
        }

        Collection<Integer> cn = gtl.getGraph().getEdges(EdgeType.DIRECTED);

        for (int j = 0; j < cn.size(); j++) {
            Pair<VNode> nds = gtl.getGraph().getEndpoints(j);

            flow.connect(nds.getFirst(), nds.getSecond(), "control");

            System.out.println("Knoten=>" + j);
        }

        // create skin factory for flow visualization
        FXSkinFactory fXSkinFactory = new FXSkinFactory(canvas.getContentPane());

        // generate the ui for the flow
        //flow.setSkinFactory(fXSkinFactory);
        flow.setSkinFactories(fXSkinFactory);
        // the usual application setup
        Scene scene = new Scene(canvas, 800, 800);
        primaryStage.setTitle("VWorkflows erste Knoten");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void createTree(VFlow flow) {

        VNode n1 = flow.newNode();
        VNode n2 = flow.newNode();
        VNode n3 = flow.newNode();
        /*VNode n4 = flow.newNode();
        VNode n5 = flow.newNode();
        VNode n6 = flow.newNode();
        VNode n7 = flow.newNode();
        VNode n8 = flow.newNode();
        VNode n9 = flow.newNode();
        VNode n10 = flow.newNode();*/

        VNode prevNode = null;

        Connector outputn11 = n1.addOutput("data");
        Connector outputn12 = n1.addOutput("data");
        Connector output2 = n2.addInput("data");
        Connector output22 = n3.addInput("data");

        flow.connect(outputn11, output2);
        flow.connect(outputn12, output22);
        /*flow.connect(n2, n4, "control");
        flow.connect(n2, n5, "control");
        flow.connect(n3, n6, "control");
        flow.connect(n3, n7, "control");
        flow.connect(n4, n8, "control");
        flow.connect(n4, n9, "control");
        */
        
        
        

    }

    Factory<DirectedGraph<String, Integer>> graphFactory
            = new Factory<DirectedGraph<String, Integer>>() {

                public DirectedGraph<String, Integer> create() {
                    return new DirectedSparseMultigraph<String, Integer>();
                }
            };

    Factory<Tree<String, Integer>> treeFactory
            = new Factory<Tree<String, Integer>>() {

                public Tree<String, Integer> create() {
                    return new DelegateTree<String, Integer>(graphFactory);
                }
            };

    Factory<Integer> edgeFactory = new Factory<Integer>() {
        int i = 0;

        public Integer create() {
            return i++;
        }
    };

    Factory<String> vertexFactory = new Factory<String>() {
        int i = 0;

        public String create() {
            return "V" + i++;

        }
    };
}
