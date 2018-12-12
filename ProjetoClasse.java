package projetoPacote;

import java.util.Scanner;
import java.lang.Math;

public class ProjetoClasse {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Diga o numero de pontos: ");
		int numPontos = in.nextInt();
		double[][] matriz = new double[3][numPontos];
		
		for (int i = 0; i < numPontos; i++) {
			System.out.print("Digite as coordenadas do " + (i + 1) + "º ponto separadas por espaço: ");
			for (int j = 0; j < 3; j++)
				matriz[j][i] = in.nextInt();
		}
		
		imprimirPontos(matriz);
		
		System.out.print("Quantas transformações você quer fazer na sua imagem? ");
		int numTrans = in.nextInt();
		
		for (int i = 0; i < numTrans; i++) {
			System.out.print("Qual será sua " + (i + 1) + "ª transformação? Digite 1 para mudança de escala, 2 para rotação sobre um eixo ordenado ou 3 para translação: ");
			int trans;
			
			do {
				trans = in.nextInt();
				if (trans < 1 || trans > 3)
					System.out.print("Código inválido! Digite novamente: ");
			} while (trans < 1 || trans > 3);
			
			if (trans == 1) {
				double[][] matrizEscalar = new double[3][3];
				char eixo = 'x';
				
				for (int j = 0; j < 3; j++) {
					System.out.print("Digite o quanto você quer escalar o eixo " + Character.toString((char) (eixo + j)) + ": ");
					matrizEscalar[j][j] = in.nextDouble();
				}
				
				matriz = multMatrizes(matrizEscalar, matriz);
				System.out.println();
				
				imprimirPontos(matriz);
			} else if (trans == 2) {
				double[][] matrizRotacao = new double[3][3];
				System.out.print("Sobre que eixo você quer rotacionar sua figura? Digite 1 para o eixo x, 2 para o eixo y ou 3 para o eixo z: ");
				int valor;
				
				do {
					valor = in.nextInt();
					if (valor < 1 || valor > 3)
						System.out.print("Código inválido! Digite novamente: ");
				} while (valor < 1 || valor > 3);
				
				if (valor == 1) {
					
				} else if (valor == 2) {
					
				} else {
					
				}
			} else {
				double[][] matrizTrans = new double[3][numPontos];
				char eixo = 'x';
				
				for (int j = 0; j < 3; j++) {
					System.out.print("Digite o quanto você quer transladar sobre o eixo " + Character.toString((char) (eixo + j)) + ": ");
					double valor = in.nextDouble();
					
					for (int k = 0; k < numPontos; k++)
						matrizTrans[j][k] = valor;
				}
				
				matriz = somarMatrizes(matrizTrans, matriz);
				System.out.println();
				
				imprimirPontos(matriz);
			}
		}
	}
	
	public static double[][] somarMatrizes(double[][] matriz1, double[][] matriz2) {
		int linhas = matriz1.length;
		int colunas = matriz1[0].length;
		
		double[][] result = new double[linhas][colunas];
		for (int i = 0; i < linhas; i++)
			for (int j = 0; j < colunas; j++)
				result[i][j] = matriz1[i][j] + matriz2[i][j];
		
		return result;
	}
	
	public static double[][] multMatrizes(double[][] matriz1, double[][] matriz2) {
		int linhas = matriz1.length;
		int colunas = matriz2[0].length;
		
		double[][] result = new double[linhas][colunas];
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				double soma = 0;
				for (int k = 0; k < matriz1[0].length; k++)
					soma += matriz1[i][k] * matriz2[k][j];
				
				result[i][j] = soma;
			}
		}
		
		return result;
	}
	
	public static void imprimirPontos(double[][] matriz) {
		System.out.println("Estes são os pontos da sua imagem agora: ");
		
		for (int i = 0; i < matriz[0].length; i++) {
			System.out.print("P" + (i + 1) + ": (");
			for (int j = 0; j < 2; j++)
				System.out.print(matriz[j][i] + ", ");
			
			System.out.println(matriz[2][i] + ")");
		}
		
		System.out.println();
	}
}


















