import weka.*;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;


public class Classification {
	
	
	
	
	public Classifier  buildClassifier(Classifier cls, Instances train) throws Exception{
		
		cls.buildClassifier(train);
		
		return cls;
	}
	
	
	public double classifyInstance(Classifier cls, Instance i) throws Exception{
		
		return cls.classifyInstance(i);
		
	}
	
	public boolean instanceCorrect(Classifier cls, Instance i) throws Exception{
		
		if(cls.classifyInstance(i)==i.classValue()) return true;
		else return false;
	}

}
