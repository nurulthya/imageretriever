import weka.classifiers.*;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;


public class MainTeste {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Classification c= new Classification();
		Instances train= c.arffToInstances("dataSetGlobalCinzaEveryBody.arff");
		Instances t= c.arffToInstances("dataSetGlobalCinzaTest.arff");

	    SMO svm = new SMO();
		c.buildClassifier(svm, train);
		
		Instance teste = t.instance(10);
		
		System.out.println("valor inst:"+teste.classValue());
		System.out.println("valor predict:"+svm.classifyInstance(teste));
		
		

	}

}
