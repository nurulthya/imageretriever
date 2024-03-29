package extractors;
//=====================================================
//      Name:           GLCM_Texture
//      Project:         Gray Level Correlation Matrix Texture Analyzer
//      Version:         0.4
//
//      Author:           Julio E. Cabrera
//      Date:             06/10/05
//      Comment:       Calculates texture features based in Gray Level Correlation Matrices
//			   Changes since 0.1 version: The normalization constant (R in Haralick's paper, pixelcounter here)
//			   now takes in account the fact that for each pair of pixel you take in account there are two entries to the 
//			   grey level co-ocurrence matrix
//	 		   Changes were made also in the Correlation parameter. Now this parameter is calculated according to Walker's paper

//=====================================================


//===========imports===================================
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.measure.ResultsTable;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

import java.awt.Rectangle;

//===========source====================================
public class GLCM_Texture implements Extractors {
	static int step = 1;
	static int selectedStep = 0;
	static boolean doIcalculateASM = true;
	static boolean doIcalculateContrast = true;
	static boolean doIcalculateCorrelation = true;
	static boolean doIcalculateIDM = true;
	static boolean doIcalculateEntropy = true;
	String[] attributeNames = new String[5];
	double[] attributeValues= new double[5];
	private ImagePlus imp;
	private int id;
	private String name;



	public GLCM_Texture(int angle, int step){
		this.step=step;
		this.selectedStep=angle;
		geraAttributeNames();

	}

	public void run(ImagePlus img) {

		// This part get al the pixel values into the pixel [ ] array via the Image Processor
     
      
      ImageConverter c= new ImageConverter(img);
      c.convertToGray8();
      
      ImageProcessor ip= img.getProcessor();
       
		byte [] pixels =(byte []) ip.getPixels();
		int width = ip.getWidth();
		Rectangle r = ip.getRoi();

		//}



		// The variable a holds the value of the pixel where the Image Processor is sitting its attention
		// The varialbe b holds the value of the pixel which is the neighbor to the  pixel where the Image Processor is sitting its attention

		int a;
		int b;
		double pixelCounter=0;


		//====================================================================================================
		// This part computes the Gray Level Correlation Matrix based in the step selected by the user

		int offset, i;
		double [] [] glcm= new double [257][257];

		if (selectedStep==0) {

			for (int y=r.y; y<(r.y+r.height); y++) 	{
				offset = y*width;
				for (int x=r.x; x<(r.x+r.width); x++)	 {
					i = offset + x;

					a = 0xff & pixels[i];
					b = 0xff &	(ip.getPixel (x+step, y));					
					glcm [a][b] +=1;
					glcm [b][a] +=1;
					pixelCounter +=2;


				}
			}
		}

		//float [] [] glcmf= new float [257][257];
		//for (a=0;  a<257; a++)  {
		//	for (b=0; b<257;b++) {
		//		glcmf[a][b]=(float)glcm[a][b];
		//	}
		//}
		//new ImagePlus("glcm", new FloatProcessor(glcmf)).show();	

		if (selectedStep==90) {

			for (int y=r.y; y<(r.y+r.height); y++) 	{
				offset = y*width;
				for (int x=r.x; x<(r.x+r.width); x++)	 {
					i = offset + x;


					a = 0xff & pixels[i];
					b = 0xff &	(ip.getPixel (x, y-step));					
					glcm [a][b] +=1;
					glcm [b][a] +=1;
					pixelCounter +=2;

				}
			}
		}		   		

		if (selectedStep==180) {

			for (int y=r.y; y<(r.y+r.height); y++) 	{
				offset = y*width;
				for (int x=r.x; x<(r.x+r.width); x++)	 {
					i = offset + x;


					a = 0xff & pixels[i];
					b = 0xff &	(ip.getPixel (x-step, y));					
					glcm [a][b] +=1;
					glcm [b][a] +=1;
					pixelCounter +=2;

				}
			}
		}		

		if (selectedStep==270) {

			for (int y=r.y; y<(r.y+r.height); y++) 	{
				offset = y*width;
				for (int x=r.x; x<(r.x+r.width); x++)	 {
					i = offset + x;


					a = 0xff & pixels[i];
					b = 0xff &	(ip.getPixel (x, y+step));					
					glcm [a][b] +=1;
					glcm [b][a] +=1;
					pixelCounter +=2;

				}
			}
		}		
		//=====================================================================================================

		// This part divides each member of the glcm matrix by the number of pixels. The number of pixels was stored in the pixelCounter variable
		// The number of pixels is used as a normalizing constant


		for (a=0;  a<257; a++)  {

			for (b=0; b<257;b++) {
				glcm[a][b]=(glcm[a][b])/(pixelCounter);
			}
		}


		
		int row = 0;	
		//=====================================================================================================
		// This part calculates the angular second moment; the value is stored in asm

		if (doIcalculateASM==true){
			double asm=0.0;
			for (a=0;  a<257; a++)  {
				for (b=0; b<257;b++) {
					asm=asm+ (glcm[a][b]*glcm[a][b]);
				}
			}
			this.attributeValues[row]=asm;
			row++;

		}

		//=====================================================================================================
		// This part calculates the contrast; the value is stored in contrast

		if (doIcalculateContrast==true){
			double contrast=0.0;
			for (a=0;  a<257; a++)  {
				for (b=0; b<257;b++) {
					contrast=contrast+ (a-b)*(a-b)*(glcm[a][b]);
				}
			}
			this.attributeNames[row]="CONT_"+this.selectedStep;
			this.attributeValues[row]=contrast;
			row++;
		}

		//=====================================================================================================
		//This part calculates the correlation; the value is stored in correlation
		// px []  and py [] are arrays to calculate the correlation
		// meanx and meany are variables  to calculate the correlation
		//  stdevx and stdevy are variables to calculate the correlation

		if (doIcalculateCorrelation==true){

			//First step in the calculations will be to calculate px [] and py []
			double correlation=0.0;
			double px=0;
			double py=0;
			double meanx=0.0;
			double meany=0.0;
			double stdevx=0.0;
			double stdevy=0.0;

			for (a=0; a<257;a++){
				for (b=0; b <257; b++){
					px=px+a*glcm [a][b];  
					py=py+b*glcm [a][b];

				} 
			}



			// Now calculate the standard deviations
			for (a=0; a<257; a++){
				for (b=0; b <257; b++){
					stdevx=stdevx+(a-px)*(a-px)*glcm [a][b];
					stdevy=stdevy+(b-py)*(b-py)*glcm [a][b];
				}
			}


			// Now finally calculate the correlation parameter

			for (a=0;  a<257; a++)  {
				for (b=0; b<257;b++) {
					correlation=correlation+( (a-px)*(b-py)*glcm [a][b]/(stdevx*stdevy)) ;
				}
			}
			
			this.attributeNames[row]="CORR_"+this.selectedStep;
			this.attributeValues[row]=correlation;
			row++;




		}
		//===============================================================================================
		// This part calculates the inverse difference moment

		if (doIcalculateIDM==true){
			double IDM=0.0;
			for (a=0;  a<257; a++)  {
				for (b=0; b<257;b++) {
					IDM=IDM+ (glcm[a][b]/(1+(a-b)*(a-b)))  ;
				}
			}
			this.attributeNames[row]="IDM_"+this.selectedStep;
			this.attributeValues[row]=IDM;
			row++;


		}

		//===============================================================================================
		// This part calculates the entropy

		if (doIcalculateEntropy==true){
			double entropy=0.0;
			for (a=0;  a<257; a++)  {
				for (b=0; b<257;b++) {
					if (glcm[a][b]==0) {}
					else {entropy=entropy-(glcm[a][b]*(Math.log(glcm[a][b])));}
				}
			}
			
			this.attributeNames[row]="ENT_"+this.selectedStep;
			this.attributeValues[row]=entropy;
			row++;


		}




//		double suma=0.0;
//		for (a=0;  a<257; a++)  {
//			for (b=0; b<257;b++) {
//				suma= suma + glcm[a][b];
//			}
//		}
		


		//===============================================================================================
		//		TextWindow tw = new TextWindow("Haralick's texture features   ", "", 400, 200);
		//		tw.append("  ");
		//		tw.append ("Total pixels analyzed  "+ pixelCounter);
		//		tw.append ( "Selected Step   " + selectedStep);
		//		tw.append ("Size of the step   "+ step);
		//		tw.append ("3 a la quinta   "+ Math.pow(3,5));

	}

	public String[] getAttributesNames() {
		// TODO Auto-generated method stub
		
		return this.attributeNames;
	}

	public double[] getFeatures(ImagePlus img) {
		// TODO Auto-generated method stub
		
		 run(img);
		 return this.attributeValues;
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
	
	public void geraAttributeNames(){
		
		this.attributeNames[0]="@attribute ASM_"+this.selectedStep+" REAL";
		this.attributeNames[1]="@attribute CONT_"+this.selectedStep+" REAL";
		this.attributeNames[2]="@attribute CORR_"+this.selectedStep+" REAL";
		this.attributeNames[3]="@attribute IDM_"+this.selectedStep+" REAL";
		this.attributeNames[4]="@attribute ENT_"+this.selectedStep+" REAL";
		
	}

	
}
