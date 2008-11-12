import com.sun.org.apache.bcel.internal.classfile.Attribute;

import weka.core.Instance;
import weka.core.Instances;



public class MainTeste2 {

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
		int result;
		

		for(int i=0; i<datateste.numInstances();i++){

			

				
				//Instance inst= new Instance(1,datateste.instance(i).toDoubleArray());
				Instance ins = new Instance(datateste.instance(i));
				ins.setDataset(no.getHeader());
				
				// primeira instancia
				result=no.classify(new ImageR(i,ins));
				

				for(int j=1; j<=no.list_linked_nodes.size();j++){
						
					//System.out.println(result+", "+no.list_linked_nodes.get(j).nome_node);
					
					if(no.list_linked_nodes.get(j-1).nome_node.compareTo(no.classes[result])==0){
						
						no=no.list_linked_nodes.get(j-1);
						no.numInstances++;
						ins.setDataset(no.getHeader());
						no.setList_imgs((int)ins.classValue()+"");
						int classValue = (int)ins.classValue();
						result=no.classify(new ImageR(i,ins));
						
						
						String valorRetornado = "";
						
						
						/* Dá uma olhada nessas impressões,
						 * ele vai imprimir:
						 * 1) o nome do nó (mamifero, anfibio ou ave)
						 * 2) o valor da classe (0 ou 1 ou 2 ou 3 ou 4 ou 5 ou 6)
						 * 3) o valor da classificacao (0 ou 1 ou 2) - valor nominal depende da classe.
						 * Execute o código e observe as impressões, ele coloca, por exemplo, 
						 * Nó: mamifero| Classvalue: 4 | result: 0, O vetor de classes dos mamíferos é
						 * {"baleia","leao","elefante"}, o classeValue igual a 4 representa
						 * a baleia, como você pode observar no switch abaixo, e o valor result igual a 0
						 * representa a baleia no vetor de classes. 
						*/
						System.out.println("Nó: "+ no.getNome_node() +"| Classvalue: " + classValue + " | result: " + result);
						
						switch (classValue){
							case 0: valorRetornado = "pato";break;
							case 1: valorRetornado = "tucano";break;
							case 2: valorRetornado = "frog";break;
							case 3: valorRetornado = "naja";break;
							case 4: valorRetornado = "baleia";break;
							case 5: valorRetornado = "leao";break;
							case 6: valorRetornado = "elefante";break;
						
						}
						
						//System.out.println("Real: " + valorRetornado + " Obtido: " + no.classes[result]);
						if(no.classes[result].compareTo(valorRetornado)==0){
							no.correctClassificationAs(result);
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
