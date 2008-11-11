import weka.classifiers.Classifier;
import weka.core.Instances;
import extractors.Extractors;
import extractors.GreyScaleHistogram;
import extractors.HSBHistogram;


public class Tree {
	
	Node raiz;
	
	public Tree() throws Exception{
		init();
	}
	
	
	
	public void init() throws Exception{
		
		ClassificationUtil util = new ClassificationUtil();
		
	
		
		// NO raiz (COISA)
		
		String[] classes={"mamifero","anfibio","ave"};
		Extractors extratores[]={new HSBHistogram(), new GreyScaleHistogram()};
		Classifier cls = util.loadModel("coisa");
		
		Node raiz = new Node("coisa",classes,cls,extratores,"dataSet_Coisa_hist_color_gray.arff");
		
		// NO AVES
		
		classes= new String[]{"pato","tucano"};
		extratores= new Extractors[]{new HSBHistogram(), new GreyScaleHistogram()};
		Classifier cls2 = util.loadModel("aves");
		
		Node aves = new Node("aves",classes,cls2,extratores,"dataSet_aves_hist_color_gray.arff");
		
		// RELATIONS
		
		raiz.setLinkedNode(aves);
		
		this.raiz=raiz;
			
	}
	
	

}
