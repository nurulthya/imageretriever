import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.Instances;
import extractors.Extractors;
import extractors.HSBHistogram;


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

		ClassificationUtil util = new ClassificationUtil();
		Classifier j48 = util.loadModel("j48_cinza_everybody");
		Instances datateste= util.arffToInstances("data/datasets/dataSetGlobalCinzaEveryBodyTest.arff");


		String[] classes={"tucano", "frog" ,"pato", "baleia", "elefante", "leao", "naja"};

		Extractors extrac[]= {new HSBHistogram()};
		
		ImageR img = new ImageR(1,"data/images/naja (1).jpg");
		img.setInstance(datateste.instance(19));






		Node raiz = new Node("coisa",classes,j48,extrac);
		System.out.println(raiz.classify(img));

		


	}

}
