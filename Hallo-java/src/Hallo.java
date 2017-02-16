
public class Hallo {

	public static void main(String[] args) {
	
		
		int a = 10;
		int b = 3;
		int z = 0;
		while (a != b){
		if (a > b){
			a--;
			z++;
			}
		else{
				a++;
				z--;
			}
		}
		z += a;
		System.out.println("z ist " +z);
	}

}
