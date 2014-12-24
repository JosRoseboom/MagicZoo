package zooPuzzle;

import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation.Result;
import org.ojalgo.optimisation.Variable;

public class ZooModel {
	
	public static final int LIONS	=	1;
	public static final int WOLVES	=	2;
	public static final int GOATS	=	4;
	
	private ExpressionsBasedModel model;
	private int nOfStartAnimals;
	private String pieceName;
	
	public ZooModel(int pieceToFindMaxFor, int startLions, int startWolves, int startGoats){
		model = new ExpressionsBasedModel();

		Variable weg = Variable.make("weg").lower(0).integer(true).weight(-1);
		Variable leg = Variable.make("leg").lower(0).integer(true).weight(-1);
		Variable lew = Variable.make("lew").lower(0).integer(true).weight(-1);
		
		if(pieceToFindMaxFor == LIONS){
			pieceName	=	"leeuwen";
			weg.weight(1);
			nOfStartAnimals = startLions;
		}
		if(pieceToFindMaxFor == WOLVES){
			pieceName	=	"wolven";
			leg.weight(1);
			nOfStartAnimals = startWolves;
		}
		if(pieceToFindMaxFor == GOATS){
			pieceName	=	"geiten";
			lew.weight(1);
			nOfStartAnimals = startGoats;
		}

		model.addVariable(weg);
		model.addVariable(leg);
		model.addVariable(lew);
		
		if(pieceToFindMaxFor == LIONS || pieceToFindMaxFor == WOLVES){
			Expression goatConstraint	=	model.addExpression("goatConstraint");
			goatConstraint.level(startGoats);
			goatConstraint.setLinearFactor(weg, 1);
			goatConstraint.setLinearFactor(leg, 1);
			goatConstraint.setLinearFactor(lew, -1);			
		}
		
		if(pieceToFindMaxFor == LIONS || pieceToFindMaxFor == GOATS){
			Expression wolfConstraint	=	model.addExpression("wolfConstraint");
			wolfConstraint.level(startWolves);
			wolfConstraint.setLinearFactor(weg, 1);
			wolfConstraint.setLinearFactor(leg, -1);
			wolfConstraint.setLinearFactor(lew, 1);
		}
		
		if(pieceToFindMaxFor == WOLVES || pieceToFindMaxFor == GOATS){
			Expression lionConstraint	=	model.addExpression("LionConstraint");
			lionConstraint.level(startLions);
			lionConstraint.setLinearFactor(weg, -1);
			lionConstraint.setLinearFactor(leg, 1);
			lionConstraint.setLinearFactor(lew, 1);
		}
	}
	
	public int getMaxAnimals(){
		Result result = model.maximise();
		System.out.println(result);
		int nOfAnimals	=	-1;
		if(result.getState().isFeasible()){
			nOfAnimals	=	 nOfStartAnimals + (int) result.getValue();
			if(result.getState().isOptimal())
				System.out.println("Optimale oplossing gevonden voor dierentuin met " + nOfAnimals + " " + pieceName);
		
		} else
			System.out.println("Er kon geen stabiele dierentuin gevonden worden met alleen " + pieceName);

		System.out.println("");	
		return nOfAnimals;
	}
}