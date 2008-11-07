import weka.classifiers.Classifier;
import weka.core.Instances;
import extractors.Extractors;
import extractors.HSBHistogram;


public class Tree {
	
	public void init() throws Exception{
		
		ClassificationUtil util = new ClassificationUtil();
		
		Classifier j48 = util.loadModel("j48_cinza_everybody");
		
		Instances datateste= util.arffToInstances("data/datasets/dataSetGlobalCinzaEveryBodyTest.arff");

		String[] classes={"tucano", "frog" ,"pato", "baleia", "elefante", "leao", "naja"};

		Extractors extrac[]= {new HSBHistogram()};
		
	}
	
	

}
