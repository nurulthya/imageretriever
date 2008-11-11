import weka.core.Instance;
import weka.core.Instances;



public class MainTeste {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub



		//		Classification c= new Classification();
		//		Instances train= c.arffToInstances("dataSetGlobalCinzaEveryBody.arff");
		//		Instances t= c.arffToInstances("dataSetGlobalCinzaTest.arff");
		//
		//	    SMO svm = new SMO();
		//		c.buildClassifier(svm, train);
		//		
		//		Instance teste = t.instance(29);
		//		
		//		System.out.println("valor inst:"+teste.classValue());
		//		System.out.println("valor predict:"+svm.classifyInstance(teste));

		// teste p/ extrator

		//		ImagePlus img= new ImagePlus("data/images/naja (1).jpg");
		//		img.show();
		//		
		//		HSBHistogram hsb = new HSBHistogram();
		//		double f[]= hsb.getFeatures(img);
		//		hsb.getAttributesNames();


		// teste de classificacao com nova estrutura
		//		ClassificationUtil util = new ClassificationUtil();
		//		Classifier j48 = util.loadModel("j48_cinza_everybody");
		//		Instances datateste= util.arffToInstances("data/datasets/dataSetGlobalCinzaEveryBodyTest.arff");
		//
		//
		//		String[] classes={"tucano", "frog" ,"pato", "baleia", "elefante", "leao", "naja"};
		//
		//		Extractors extrac[]= {new HSBHistogram()};
		//		
		//		ImageR img = new ImageR(1,"data/images/naja (1).jpg");
		//		img.setInstance(datateste.instance(19));




		//DataSetGenerator d= new DataSetGenerator();

		ClassificationUtil util = new ClassificationUtil();
		Instances datateste= util.arffToInstances("dataSet_teste_hist_color_gray2.arff");

		Tree arvore = new Tree();
		Node no = arvore.raiz;
		String result;
		

		for(int i=0; i<datateste.numInstances();i++){

			

				
				//Instance inst= new Instance(1,datateste.instance(i).toDoubleArray());
				Instance ins = new Instance(datateste.instance(i));
				ins.setDataset(no.getHeader());
				
				// primeira instancia
				result=no.classify(new ImageR(i,ins));
				

				for(int j=1; j<=no.list_linked_nodes.size();j++){
						
					//System.out.println(result+", "+no.list_linked_nodes.get(j).nome_node);
					
					if(no.list_linked_nodes.get(j-1).nome_node.compareTo(result)==0){
						
						no=no.list_linked_nodes.get(j-1);
						no.numInstances++;
						no.setList_imgs(no.classes[(int)ins.classValue()]);
						ins.setDataset(no.getHeader());
						//System.out.println("entrou:"+ no.getNome_node());
						result=no.classify(new ImageR(i,ins));
						
						//System.out.println(result+ ","+no.classes[(int)ins.classValue()]);
						if(result.compareTo(no.classes[(int)ins.classValue()])==0){
							no.correctClassificationAs((int)ins.classValue());
						}
						
						//System.out.println(no.getClasse((int)ins.classValue()));
					}
					else{
						//System.out.println(result);
					}
					
				}
				
				no = arvore.raiz;
				
			
		}
		
		
		arvore.printClasses(arvore.raiz);
		
		




	}

}
