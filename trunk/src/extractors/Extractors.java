package extractors;
import ij.ImagePlus;

public interface Extractors {
	
	/**
	 * 
	 * @return um vetor de características
	 */
	public double[] getFeatures(ImagePlus img);
	
	/**
	 * 
	 * @return os nomes das features retornadas e seu tipo e formato do arff 
	 */
	public String[] getAttributesNames();
	
	public void setId(int id);
	public int getId();
	public void setName(String name);
	public String getName();
	public void setImage(ImagePlus img);
 
}
 
