package net.craigrm.dip.scanners;

public class BracketedCSVScannerStub  implements IBracketedCSVScanner{

	private String[] identifierNames;
	public BracketedCSVScannerStub(String... identifierNames) {
		this.identifierNames = identifierNames;
	}

	public String[] getElements() {
		return identifierNames;
	}

}
