package net.craigrm.dip.gameturn;

public class DipTurn {

	public static void main (String[] args) {
		if (args.length != 4) {
			doUsage();
		}
		GameTurn gt = new GameTurn(args[0], args[1], args[2], args[3]);
		gt.generateNewPosition();
	}
	
	private static void doUsage() {
		System.out.println("Usage: ");
		System.out.println(">java DipTurn mapfilename, positionfilename, ordersfilename, resultsfilename");
	}
}
