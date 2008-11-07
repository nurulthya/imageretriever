import java.util.ArrayList;

import extractors.Extractors;

import weka.classifiers.Classifier;
import weka.core.Instance;

public class Node {
 
	private String id_node;
	 
	private ArrayList<String> classes;
	 
	private ArrayList list_imgs;
	 
	private Classifier classifier;
	 
	private Extractors[] list_extractors;
	 
	private Node[] list_linked_nodes;
	 
	 
	public String classifier(Classifier cls, Instance ins) throws Exception {
		
		int cl=(int)cls.classifyInstance(ins);
		return  classes.get(cl);
	}
	 
	 
}
 
