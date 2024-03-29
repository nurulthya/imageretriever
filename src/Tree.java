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
		Classifier cls = util.loadModel("coisa_smo");

		Node raiz = new Node("coisa",classes,cls,extratores,"dataSet_Coisa_hist_color_gray.arff");

		// NO AVES

		classes= new String[]{"pato","tucano"};
		extratores= new Extractors[]{new HSBHistogram(), new GreyScaleHistogram()};
		cls = util.loadModel("ave_smo");

		Node ave = new Node("ave",classes,cls,extratores,"dataSet_aves_hist_color_gray.arff");


		// NO ANFIBIO

		classes= new String[]{"frog","naja"};
		extratores= new Extractors[]{new HSBHistogram(), new GreyScaleHistogram()};
		cls = util.loadModel("anfibio_smo");

		Node anfibio = new Node("anfibio",classes,cls,extratores,"dataSet_anfibio_hist_color_gray.arff");

		// NO MAMIFERO

		classes= new String[]{"baleia","leao","elefante"};
		extratores= new Extractors[]{new HSBHistogram(), new GreyScaleHistogram()};
		cls = util.loadModel("mamifero_smo");

		Node mamifero = new Node("mamifero",classes,cls,extratores,"dataSet_mamifero_hist_color_gray.arff");




		// RELATIONS

		raiz.setLinkedNode(ave);

		raiz.setLinkedNode(anfibio);
		raiz.setLinkedNode(mamifero);


		this.raiz=raiz;

	}


	public void printClasses(Node no){

		if(no.list_linked_nodes.isEmpty()){

			
			System.out.println("NO:"+no.getNome_node()+":"+no.numInstances);
			for(int i=0;i<no.classes.length;i++){

				System.out.println(no.classes[i]+ ":" + no.correctInstances[i]);
			}
			System.out.println();
		}else{
			for(int i=0;i<no.getList_linked_nodes().size();i++){

				printClasses(no.getList_linked_nodes().get(i));
				
			}
		}

	}

}
