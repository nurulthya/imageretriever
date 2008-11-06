import weka.*;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class Classification {
	
	
	
	
	public Classifier  buildClassifier(Classifier cls, Instances train) throws Exception{
		
		cls.buildClassifier(train);
		
		return cls;
	}
	
	
	public boolean instanceCorrect(Classifier cls, Instance i) throws Exception{
		
		if(cls.classifyInstance(i)==i.classValue()) return true;
		else return false;
	}
	
	
	/**
	 * Tranforma um arff em um objeto Instances
	 * @param arff - String com o camiho do arff
	 * @return - Instances pelo arff
	 * @throws Exception
	 */
	public Instances arffToInstances(String arff) throws Exception{
		
		DataSource source = new DataSource(arff);
		Instances data = source.getDataSet();
		 // setting class attribute if the data format does not provide this information
		 // E.g., the XRFF format saves the class attribute information as well
		 
		 if (data.classIndex() == -1){
		   data.setClassIndex(data.numAttributes() - 1);
		 }
		 
		 return data;
	}

}
