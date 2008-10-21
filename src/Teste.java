import ij.ImagePlus;


public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path="caminho";
		ImagePlus imp= new ImagePlus(path);
		int histograma[]= imp.getProcessor().getHistogram();
	
	}

}
