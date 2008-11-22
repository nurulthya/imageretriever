import ij.ImagePlus;

import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import extractors.Extractors;
import extractors.GLCM_Texture;
import extractors.GreyScaleHistogram;
import extractors.HSBHistogram;


public class DataSetGenerator {
	
	
	static String nomeDataSet;
	static String classesNames;
	static String classe;
	static boolean aux;
	String relation;
	
	public DataSetGenerator(){
		
		
		File files[];
		files = getFiles();
		
		// CONFIGURACOES
		aux=true;
		nomeDataSet = "data/datasets/dataSet_testTextura.arff";
		classesNames = "{pato,tucano,frog,naja,baleia,leao,elefante}";
		relation= "textura";
		
		Extractors extratores[]={new GLCM_Texture(0,1)};
		
		
		// Executa esta ação para todas as imagens selecionadas no filechooser
		for (int i = 0; i < files.length; i++) {

			ImagePlus imp = new ImagePlus(files[i].getPath());
		
			escreveDados(imp, this.getImageClass(files[i].getName()), extratores);
			
		}	
		
		JOptionPane.showMessageDialog(null, "Dataset gerado com sucesso!");
		System.exit(0);

	}
	
	public String getImageClass(String nome){
		
		if(nome.charAt(0)=='p') return "pato";
		if(nome.charAt(0)=='t') return "tucano";
		if(nome.charAt(0)=='f') return "frog";
		if(nome.charAt(0)=='n') return "naja";
		if(nome.charAt(0)=='b') return "baleia";
		if(nome.charAt(0)=='l') return "leao";
		if(nome.charAt(0)=='e') return "elefante";
		else return "?";
		
	}
	
	
	
	public File[] getFiles() {

		File files[];
		
		JFileChooser chooser = new JFileChooser();
		//chooser.setCurrentDirectory(new File("data/image/"));

		chooser.setMultiSelectionEnabled(true);
		chooser.showOpenDialog(new Frame());

		String path = chooser.getSelectedFile().getPath();
		files = chooser.getSelectedFiles();

		return files;
	}
	
	public void escreveDados(ImagePlus pImg, String pImgClass, Extractors[] extratores) {
		try // esta sintaxe faz parte do tratamento de exceções
		{

			File arquivo = new File(nomeDataSet);
			
			boolean arquivoExiste = arquivo.exists();
			
			
			//Código para apagar o dataSet e criar um novo vazio.
			if (arquivoExiste && aux) {
				if (JOptionPane.showConfirmDialog(null, "O arquivo já existe, deseja criar um novo?", "Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE ) == 0) {
					if (arquivo.delete()){
						JOptionPane.showMessageDialog(null, "Arquivo apagado!");
						arquivoExiste = false;
					}
				}
				aux=false;
			}
			
			BufferedWriter escreve = new BufferedWriter(new FileWriter(arquivo, true));
			
			
			if (!arquivoExiste) {
				escreve.write("% =============================================");
				escreve.newLine();
				escreve.write("% Instituto Tecnológico de Aeronáutica - 2008");
				escreve.newLine();
				escreve.write("%   .: Ricardo Rodrigues & Thiago Trigo :.");
				escreve.newLine();
				escreve.write("% =============================================");
				escreve.newLine();
				escreve.newLine();
				escreve.write("@relation " + relation);
				escreve.newLine();

				
				for (int i=0; i<extratores.length; i++){
					
					String attNames[]=extratores[i].getAttributesNames();
					
					for(int j=0; j<attNames.length;j++){
						
						try{
							escreve.write(attNames[j]);
							escreve.newLine();
						}catch (ArrayIndexOutOfBoundsException e){
							System.out.println("asd");
						}
					}
					
				}
				
				

				escreve.write("@attribute ");
				escreve.write("class "+ classesNames);
				escreve.newLine();
				escreve.newLine();

				escreve.write("@data");

			}
			
			escreve.newLine();
			
			// Escreve todos o valor de cada atributo e 
			// no final do laço escreve a classe (por enquanto é o nome do path)
			
			
			for (int i=0; i<extratores.length; i++){
				
				double features[] = extratores[i].getFeatures(pImg);
				
				for(int j=0; j<features.length;j++){
					
					escreve.write(features[j]+ ",");
					
				}
			}
			
			escreve.write(pImgClass);
			
			

			/*
			 * Na linha abaixo garantimos que todo o Buffer será transferido
			 * para o arquivo através do método "flush", e na próxima linha
			 * fechamos o arquivo utilizando o método "close".
			 */

			escreve.flush();
			escreve.close();
		} catch (IOException e) {
			System.err.println("IO EXCEPTION!");
		}
	}

}
