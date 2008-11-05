import ij.ImagePlus;

import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Teste1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int hist[];
		File files[];
		String classe;

		files = new Teste1().GetFiles();

		// Executa esta a��o para todas as imagens selecionadas no filechooser
		for (int i = 0; i < files.length; i++) {

			ImagePlus imp = new ImagePlus(files[i].getPath());
			// mostra a imagem
			//imp.show();
			hist = imp.getProcessor().getHistogram();
			
			//if(files[i].getName().charAt(0)=='c') classe="carro";
			//else classe="folha";
			classe = "anfibio";
			//classe = "ave";
			//classe = "mamifero";
			
			escreveDados(imp, classe);
			
		}
		
		System.out.println("terminou");
		System.exit(0);

	}

	public File[] GetFiles() {

		File files[];
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("image//"));

		chooser.setMultiSelectionEnabled(true);
		chooser.showOpenDialog(new Frame());

		String path = chooser.getSelectedFile().getPath();
		files = chooser.getSelectedFiles();

		return files;
	}

	public static void escreveDados(ImagePlus pImg, String pImgClass) {
		try // esta sintaxe faz parte do tratamento de exce��es
		{

			File arquivo = new File("dataSetGlobalCinzaTest.arff");
			boolean arquivoExiste = arquivo.exists(); 
			
			// C�digo para apagar o dataSet e criar um novo vazio.
//			if (arquivoExiste) {
//				if (JOptionPane.showConfirmDialog(null, "O arquivo j� existe, deseja criar um novo?", "Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE ) == 0) {
//					if (arquivo.delete()){
//						JOptionPane.showMessageDialog(null, "Arquivo apagado!");
//						arquivoExiste = false;
//					}
//				}
//			}
			
			BufferedWriter escreve = new BufferedWriter(new FileWriter(arquivo, true));
			
			int[] histogram = pImg.getProcessor().getHistogram();

			if (!arquivoExiste) {
				escreve.write("% =============================================");
				escreve.newLine();
				escreve.write("% Instituto Tecnol�gico de Aeron�utica - 2008");
				escreve.newLine();
				escreve.write("%   .: Ricardo Rodrigues & Thiago Trigo :.");
				escreve.newLine();
				escreve.write("% =============================================");
				escreve.newLine();
				escreve.newLine();
				escreve.write("@relation " + "imageHistogram");
				escreve.newLine();

				for (int i = 0; i < histogram.length; i++) {
					escreve.write("@attribute ");
					escreve.write("TOM_" + i);
					escreve.write(" REAL");
					escreve.newLine();
				}

				escreve.write("@attribute ");
				escreve.write("class {mamifero,anfibio,ave}");
				escreve.newLine();
				escreve.newLine();

				escreve.write("@data");

			}
			
			escreve.newLine();
			// Escreve todos o valor de cada atributo e 
			// no final do la�o escreve a classe (por enquanto � o nome do path)
			for (int i = 0; i < histogram.length; i++) {
				float valor = ((float)100*histogram[i]/(pImg.getHeight()*pImg.getWidth()));
				escreve.write( valor + ",");
			}
			escreve.write(pImgClass);

			/*
			 * Na linha abaixo garantimos que todo o Buffer ser� transferido
			 * para o arquivo atrav�s do m�todo "flush", e na pr�xima linha
			 * fechamos o arquivo utilizando o m�todo "close".
			 */

			escreve.flush();
			escreve.close();
		} catch (IOException e) {
			System.err.println("IO EXCEPTION!");
		}
	}

}
