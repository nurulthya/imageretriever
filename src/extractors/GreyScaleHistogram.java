package extractors;

import java.util.Iterator;

import ij.ImagePlus;
import ij.ImageStack;
import ij.process.ColorProcessor;

public class GreyScaleHistogram implements Extractors {

	private ImagePlus imp;
	private int id;
	private String name;

	/**
	 * Cria a lista de nome dos atributos
	 * 
	 * @return lista de atributos extraídos
	 */
	public String[] getAttributesNames() {
		// TODO Auto-generated method stub
		String attributes[] = new String[255];

		for (int i = 0; i < 255; i++) {
			attributes[i] = "@attribute " + "GreyIndice_" + i + " REAL";
		}

		return attributes;
	}

	/**
	 * Método de extração de características. Cria uma linha do dataSet.
	 * 
	 * @param pImg -
	 *            imagem da qual será extraída a informação
	 * @return features[] - vetor que representa uma linha do dataSet
	 */
	public double[] getFeatures(ImagePlus pImg) {

		this.imp = pImg;
		int[] histogram = pImg.getProcessor().getHistogram();
		int w = imp.getWidth();
		int h = imp.getHeight();
		int npixels = w * h;
		double features[] = new double[histogram.length];

		// cria uma linha do dataSet
		for (int i = 0; i < histogram.length; i++) {
			float valor = ((float) 100 * histogram[i] / (npixels));
			features[i] = valor;
		}

		return features;
	}

	public static void main(String args[]) {
		// teste p/ extrator

		ImagePlus img = new ImagePlus("data/images/naja (1).jpg");
		img.show();

		GreyScaleHistogram greyHist = new GreyScaleHistogram();
		double f[] = greyHist.getFeatures(img);
		String atributos[] = greyHist.getAttributesNames();
		
				for (int i = 0; i < atributos.length; i++) {
			System.out.println(atributos[i] + " valor: "  + f[i]);
		}
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
		this.id = id;

	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;

	}

}
