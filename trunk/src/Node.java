import java.util.ArrayList;

import extractors.Extractors;

import weka.classifiers.Classifier;
import weka.core.Instance;

public class Node {
 
	public String nome_node;
	 
	public String[] classes;
	 
	private ArrayList<String> list_imgs;
	 
	private Classifier classifier;
	 
	private Extractors[] list_extractors;
	 
	public ArrayList<Node> list_linked_nodes;
	
	public Node(String nome, String[] classes, Classifier cls, Extractors[] extratores){
		
		this.list_imgs =new ArrayList<String>();
		this.list_linked_nodes = new ArrayList<Node>();
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
		//this.list_imgs.add(img.getNome());
		// classifica a instancia
		int cl=(int)this.classifier.classifyInstance(ins);
		
		System.out.println("classe manual:"+ classes[(int)ins.classValue()]);
		return  classes[cl];
	}
	
	public void setLinkedNode(Node n){
		this.list_linked_nodes.add(n);
	}
	
	public boolean isLinkedNodeEmpty(){
		if(this.list_linked_nodes.isEmpty())return true;
		else return false;
	}


	public String getNome_node() {
		return nome_node;
	}


	public void setNome_node(String nome_node) {
		this.nome_node = nome_node;
	}


	public ArrayList<String> getList_imgs() {
		return list_imgs;
	}


	public void setList_imgs(String img) {
		this.list_imgs.add(img);
	}


	public Classifier getClassifier() {
		return classifier;
	}


	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}


	public Extractors[] getList_extractors() {
		return list_extractors;
	}


	public void setList_extractors(Extractors[] list_extractors) {
		this.list_extractors = list_extractors;
	}


	public ArrayList<Node> getList_linked_nodes() {
		return list_linked_nodes;
	}


	public void setList_linked_nodes(ArrayList<Node> list_linked_nodes) {
		this.list_linked_nodes = list_linked_nodes;
	}
	 
	 
}
 
