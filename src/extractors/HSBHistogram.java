package extractors;
import ij.ImagePlus;
import ij.ImageStack;
import ij.process.ColorProcessor;



public class HSBHistogram  implements Extractors{

	private ImagePlus imp;
	private int id;
	private String name;
	
	
	public String[] getAttributesNames() {
		// TODO Auto-generated method stub
		String attributes[] = new String[765];
		
		char hsb[]={'h','s','b'};
		int j=0;
        int k=0;		
		
		for(int i=0; i<765; i++){

			attributes[i]="@attribute "+hsb[j]+"_"+i+" REAL";
			k++;

			if(k==255){
				j++;
				k=0;
			}
		}
		
//		for(int i =0; i<attributes.length;i++){
//			System.out.print(attributes[i]+",");
//		}
		
		return attributes;
	}

	public double[] getFeatures(ImagePlus img) {

		this.imp= img;
		
		int w = imp.getWidth();
		int h = imp.getHeight();
		int npixels = w*h;
		ImageStack hsbStack = imp.getStack();
		ImageStack hueStack = new ImageStack(w,h);
		ImageStack satStack = new ImageStack(w,h);
		ImageStack brightStack = new ImageStack(w,h);
		byte[] hue,s,b;
		ColorProcessor cp;
		int n = hsbStack.getSize();
		for (int i=1; i<=n; i++) {

			hue = new byte[w*h];
			s = new byte[w*h];
			b = new byte[w*h];
			cp = (ColorProcessor)hsbStack.getProcessor(1);
			cp.getHSB(hue,s,b);
			hsbStack.deleteSlice(1);

			hueStack.addSlice(null,hue);
			satStack.addSlice(null,s);
			brightStack.addSlice(null,b);

		}


		int hsb[][] = new int[3][255];



		hsb[0] = new ImagePlus(" (hue)",hueStack).getProcessor().getHistogram();
		hsb[1] = new ImagePlus(" (saturation)",satStack).getProcessor().getHistogram();     
		hsb[2] = new ImagePlus(" (brightness)",brightStack).getProcessor().getHistogram();



		// coloa a matriz em um unico vetor
		double features[]= new double[765];

		int j=0;
		int k=0;

		for(int i=0; i<765; i++){

			features[i]=hsb[j][k];
			k++;

			if(k==255){
				j++;
				k=0;
			}
		}


		return features;
	}



	public void setImage(ImagePlus img) {

		this.imp = img;

	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id=id;

	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;

	}





}
