import java.util.ArrayList;

import extractors.Extractors;

import weka.classifiers.Classifier;
import weka.core.Instance;

public class Node {
 
	private String nome_node;
	 
	private String[] classes;
	 
	private ArrayList<String> list_imgs;
	 
	private Classifier classifier;
	 
	private Extractors[] list_extractors;
	 
	private ArrayList<Node> list_linked_nodes;
	
	public Node(String nome, String[] classes, Classifier cls, Extractors[] extratores){
		this.nome_node=nome;
		this.classes=classes;
		this.classifier=cls;
		this.list_extractors = extratores;
		
		this.list_imgs= new ArrayList<String>();
	}
	 
	 
	public String classify(ImageR img) throws Exception {
		
		//gera a instancia de acordo com o Node
		Instance ins=img.instanceGenerator(this.list_extractors);
		// adiciona o nome da imagem na lista de imagens que entraram neste Node
		this.list_imgs.add(img.getNome());
		// classifica a instancia
		int cl=(int)this.classifier.classifyInstance(ins);
		
		System.out.println("classe manual:"+ classes[(int)ins.classValue()]);
		return  classes[cl];
	}
	
	public void setLinkedNode(Node n){
		this.list_linked_nodes.add(n);
	}
	 
	 
}
 
