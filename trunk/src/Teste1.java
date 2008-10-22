import ij.ImagePlus;

import java.awt.Frame;
import java.io.File;

import javax.swing.JFileChooser;


public class Teste1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int hist[];
		String paths[];
          
		
		 paths=new Teste1().GetPath();
	    
		 
		// Executa esta ação para todas as imagens selecionadas no filechooser
		 for(int i=0;i<paths.length;i++){
			 
			 ImagePlus imp= new ImagePlus(paths[i]);
			 imp.show();
			  hist=imp.getProcessor().getHistogram();
	     }
		
		
	  
	    
		
	}
	
	public String[] GetPath() {
		
		File files[];
		String paths[];
		JFileChooser chooser = new JFileChooser();
		
		
		chooser.setMultiSelectionEnabled(true);
		chooser.showOpenDialog(new Frame());
		
		
		String path= chooser.getSelectedFile().getPath();
		files =chooser.getSelectedFiles();
		
		paths= new String[files.length];
		for(int i=0;i<files.length;i++){
			paths[i]=files[i].getPath();
		}
		
		return paths;
	}


}
