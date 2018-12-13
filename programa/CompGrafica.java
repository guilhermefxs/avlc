package programa;

import java.util.Scanner;
import java.lang.Math;

public class CompGrafica {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Digite o numero de pontos da sua forma geom�trica tridimensional: ");
		int numPontos = in.nextInt();
		double[][] matriz = new double[3][numPontos];
		
		for (int i = 0; i < numPontos; i++) {
			System.out.print("Digite as coordenadas do " + (i + 1) + "� ponto separadas por espa�o: ");
			for (int j = 0; j < 3; j++)
				matriz[j][i] = in.nextDouble();
		}
		
		System.out.println();
		imprimirPontos(matriz);
		
		System.out.print("Quantas transforma��es voc� quer fazer na sua forma geom�trica? ");
		int numTrans = in.nextInt();
		
		for (int i = 0; i < numTrans; i++) {
			System.out.print("Qual ser� sua " + (i + 1) + "� transforma��o? Digite 1 para mudan�a de escala, 2 para rota��o ao redor de um eixo ordenado ou 3 para transla��o: ");
			int trans;
			
			do {
				trans = in.nextInt();
				if (trans < 1 || trans > 3)
					System.out.print("C�digo inv�lido! Digite novamente: ");
			} while (trans < 1 || trans > 3);
			
			if (trans == 1) {
				double[][] matrizEscalar = new double[3][3];
				char eixo = 'x';
				
				for (int j = 0; j < 3; j++) {
					System.out.print("Digite o quanto voc� quer escalar o eixo " + Character.toString((char) (eixo + j)) + ": ");
					matrizEscalar[j][j] = in.nextDouble();
				}
				
				matriz = multMatrizes(matrizEscalar, matriz);
				System.out.println();
				
				imprimirPontos(matriz);
			} else if (trans == 2) {
				double[][] matrizRotacao = new double[3][3];
				System.out.print("Sobre que eixo voc� quer rotacionar sua figura? Digite 1 para o eixo x, 2 para o eixo y ou 3 para o eixo z: ");
				int valor;
				
				do {
					valor = in.nextInt();
					if (valor < 1 || valor > 3)
						System.out.print("C�digo inv�lido! Digite novamente: ");
				} while (valor < 1 || valor > 3);
				
				System.out.print("Voc� quer rotacionar quantos graus sobre esse eixo? Para rota��o no sentido hor�rio, digite o �ngulo, em graus, negativo: ");
				double angulo = in.nextDouble();
				angulo *= (Math.PI / 180);
				
				double sen = Math.sin(angulo);
				double cos = Math.cos(angulo);
				
				matrizRotacao[valor - 1][valor - 1] = 1;
				
				if (valor == 1) {
					matrizRotacao[1][1] = cos;
					matrizRotacao[1][2] = (-1) * sen;
					matrizRotacao[2][1] = sen;
					matrizRotacao[2][2] = cos;
				} else if (valor == 2) {
					matrizRotacao[0][0] = cos;
					matrizRotacao[0][2] = sen;
					matrizRotacao[2][0] = (-1) * sen;
					matrizRotacao[2][2] = cos;
				} else {
					matrizRotacao[0][0] = cos;
					matrizRotacao[0][1] = (-1) * sen;
					matrizRotacao[1][0] = sen;
					matrizRotacao[1][1] = cos;
				}
				
				matriz = multMatrizes(matrizRotacao, matriz);
				System.out.println();
				
				imprimirPontos(matriz);
			} else {
				double[][] matrizTrans = new double[3][numPontos];
				char eixo = 'x';
				
				for (int j = 0; j < 3; j++) {
					System.out.print("Digite o quanto voc� quer transladar sobre o eixo " + Character.toString((char) (eixo + j)) + ": ");
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
		System.out.println("Estes s�o os pontos da sua forma geometrica agora: ");
		
		for (int i = 0; i < matriz[0].length; i++) {
			System.out.print("P" + (i + 1) + ": (");
			for (int j = 0; j < 2; j++)
				System.out.printf("%.2f; ", matriz[j][i]);
			
			System.out.printf("%.2f)\n", matriz[2][i]);
		}
		
		System.out.println();
	}
}


















