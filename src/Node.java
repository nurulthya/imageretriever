import java.util.ArrayList;

import weka.classifiers.Classifier;
import weka.core.Instance;

public class Node {
 
	private String id_node;
	 
	private ArrayList classes;
	 
	private ArrayList list_imgs;
	 
	private Classifier classifier;
	 
	private Extractors[] list_extractors;
	 
	private Node[] list_linked_nodes;
	 
	public Instance instanceGenerator() {
		return null;
	}
	 
	public String classifier() {
		return null;
	}
	 
	public void operation2() {
	 
	}
	 
}
 
