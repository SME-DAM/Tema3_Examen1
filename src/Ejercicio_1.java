import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio_1 {

	static public void ordenaCaracteres(char[] vector) {
		boolean working = true;
		while (working) {
			working=false;
			for(int index = 0; index < vector.length - 1; index++) {
				if (vector[index]>vector[index+1]) {
					char temp = vector[index];
					vector[index] = vector[index + 1];
					vector[index + 1] = temp;
					working=true;
				}
			}
		}
	}
	
	private static boolean comparaCifras(int base, int multiplicando) {
		char[] numero = String.format("%d",base*multiplicando).toCharArray();
		char[] origen = String.format("%d", base).toCharArray();
		if (origen.length != numero.length) return false;
		ordenaCaracteres(origen);
		ordenaCaracteres(numero);
		return String.valueOf(origen).equals(String.valueOf(numero));
	}
	private static int sacaMMC(int numero) {
		int mmc = 0;
		for (int i = 1; i < 10; i++) {
			if (comparaCifras(numero,i)) {
				mmc = i;
			} else {
				return mmc;
			}
		}
		return mmc;
	}


	private static void listaMMC(int objetivo) {
		int[] maximo = {0,0};
		for (int i =1; i < 15000000; i++) {
			int actual = sacaMMC(i);
			if (actual >= objetivo) {
				System.out.printf("El numero %9d cumple: MMC(%d) = %d\n",i, i, actual);
				if (i >= maximo[1]) {
					maximo[0]=actual;
					maximo[1]=i;
				}
			}
		}
		System.out.printf("\nEl máximo número ha sido %9d: MMC(%d) = %d\n\n",maximo[1],maximo[1], maximo[0]);
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("CALCULADOR DE MMC\n-----------------");
			System.out.println("1.- Calcular el MMC de n");
			System.out.println("2.- Muestra los numeros con MMC(n) >= x");
			System.out.print("Introduce opcion: ");
			try {
				int opcion = sc.nextInt();
				if (opcion == 1) {
					System.out.print("Introduce el número del que quieras calcular el MMC: ");
					int numero = sc.nextInt();
					int mmc = sacaMMC(numero);
					System.out.printf("\nEl MMC de %d es: %d\n", numero, mmc);
				} else if (opcion == 2) {
					System.out.print("Introduce el MMC inferior a buscar: ");
					int objetivo = sc.nextInt();
					listaMMC(objetivo);
				} else {
					System.out.print("\u001B[31m\n\nSolo tienes dos opciones, 1 o 2!\u001B[0m\n\n");
				}
			} catch (InputMismatchException e) {
				System.out.print("\u001B[31m\n\nUsa numeros, melón!\u001B[0m\n\n");
				sc.nextLine();
			}
		}
	}
}
