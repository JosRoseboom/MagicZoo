package zooPuzzle;

public class ZooSolver {

	public static void main(String[] args) {
		int startGoats	=	2055;
		int startWolves	=	2006;
		int startLions	=	2017;
		
		int max;
				
		int maxLions	=	new ZooModel(ZooModel.LIONS, startLions, startWolves, startGoats).getMaxAnimals();
		int maxWolves	=	new ZooModel(ZooModel.WOLVES, startLions, startWolves, startGoats).getMaxAnimals();
		int maxGoats	=	new ZooModel(ZooModel.GOATS, startLions, startWolves, startGoats).getMaxAnimals();
		
		max	=	Math.max(maxLions, maxWolves);
		max	=	Math.max(max, maxGoats);
		
		if(maxLions == max)
			System.out.println("De oplossing is (0,0," + maxLions + ")");
		if(maxWolves == max)
			System.out.println("De oplossing is (0," + maxWolves + ",0)");
		if(maxGoats == max)
			System.out.println("De oplossing is (" + maxGoats + ",0,0)");
	}
}