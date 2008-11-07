import ij.ImagePlus;

import java.util.ArrayList;

import extractors.Extractors;

import weka.core.Instance;

public class Image {
 
	private ImagePlus img;
	 
	private ArrayList<double[]> features;
	 
	private int id_img;
	
	private Instance instance;
	
	
	/**
	 * Constructor
	 * @param id - Image id
	 * @param path - Image Path
	 */
	public Image (int id,String path){
		 
		this.img = new ImagePlus(path);
		this.id_img= id;
		
		features= new ArrayList<double[]>();
	}
	
	/**
	 * 
	 * @param id - Extractor index
	 * @param feature - Features vector
	 */
	public void setFeatures(int id, double feature[]){
		
		this.features.add(id, feature);
		
	}
	
	public Instance instanceGenerator(Extractors[] extractors) {
		
		ArrayList<double[]> list_features = new ArrayList<double[]>();
		
		// passar os estratores ou um vetor de indices?????
		for(int i=0; i< extractors.length; i++){
			
			// se imagem nao tem essas features extraidas
			if(this.features.get(i)==null){
				
				// extrai features
				double f[]=extractors[i].getFeatures(this.img);
				list_features.add(f);
				
				// add no vetor de features da imagem
				this.features.set(i, f);
			}
			// pega features ja extraídas
			else{
				list_features.add(this.features.get(i));
			}
			
			// TRANSFORMAR A LISTA DE []'s EM UM UNICO double []
		}
		
		return null;
	}
	 
}
 
