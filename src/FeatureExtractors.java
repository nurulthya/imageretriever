import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.WindowManager;
import ij.gui.NewImage;
import ij.io.FileSaver;
import ij.plugin.filter.ParticleAnalyzer;
import ij.plugin.filter.RankFilters;
import ij.process.BinaryProcessor;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

import java.awt.Frame;
import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.swing.JFileChooser;



public class FeatureExtractors {

	 public int[][] HSBextractor(ImagePlus imp) {
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
            //System.gc();
             hueStack.addSlice(null,hue);
             satStack.addSlice(null,s);
             brightStack.addSlice(null,b);
             
             }
         
         
         int hsb[][] = new int[3][255];
         
       
         
      hsb[0] = new ImagePlus(" (hue)",hueStack).getProcessor().getHistogram();
      hsb[1] = new ImagePlus(" (saturation)",satStack).getProcessor().getHistogram();     
      hsb[2] = new ImagePlus(" (brightness)",brightStack).getProcessor().getHistogram();
      
      return hsb;
         
//      for (int i=0 ; i<3;i++){
//    	  
//    	  for (int j=0 ; j<hsb[0].length;j++){
//        	  System.out.print(hsb[i][j]);
//          }
//    	  System.out.println();
//      }
      
//      String title = imp.getTitle();
//       imp.hide();
//        
//        new ImagePlus(title+" (hue)",hueStack).show();
//        new ImagePlus(title+" (saturation)",satStack).show();
//        new ImagePlus(title+" (brightness)",brightStack).show();
    }


}
