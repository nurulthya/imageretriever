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
		String paths[];

		paths = new Teste1().GetPath();

		// Executa esta ação para todas as imagens selecionadas no filechooser
		for (int i = 0; i < paths.length; i++) {

			ImagePlus imp = new ImagePlus(paths[i]);
			// mostra a imagem
			imp.show();
			hist = imp.getProcessor().getHistogram();
			escreveDados(imp, paths[i]);
		}

	}

	public String[] GetPath() {

		File files[];
		String paths[];
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("image//"));

		chooser.setMultiSelectionEnabled(true);
		chooser.showOpenDialog(new Frame());

		String path = chooser.getSelectedFile().getPath();
		files = chooser.getSelectedFiles();

		paths = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			paths[i] = files[i].getPath();
		}

		return paths;
	}

	public static void escreveDados(ImagePlus pImg, String pImgClass) {
		try // esta sintaxe faz parte do tratamento de exceções
		{

			File arquivo = new File("dataSet.arf");
			boolean arquivoExiste = arquivo.exists(); 
			
			// Código para apagar o dataSet e criar um novo vazio.
//			if (arquivoExiste) {
//				if (JOptionPane.showConfirmDialog(null, "O arquivo já existe, deseja criar um novo?", "Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE ) == 0) {
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
				escreve.write("% Instituto Tecnológico de Aeronáutica - 2008");
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
					escreve.write(" int");
					escreve.newLine();
				}

				escreve.write("@attribute ");
				escreve.write("class string");
				escreve.newLine();
				escreve.newLine();

				escreve.write("@data");

			}
			
			escreve.newLine();
			// Escreve todos o valor de cada atributo e 
			// no final do laço escreve a classe (por enquanto é o nome do path)
			for (int i = 0; i < histogram.length; i++) {
				escreve.write(histogram[i] + ",");
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
