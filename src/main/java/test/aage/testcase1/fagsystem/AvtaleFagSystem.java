package test.aage.testcase1.fagsystem;

import test.aage.testcase1.domain.AvtaleStatus;
import test.aage.testcase1.domain.AvtaleStatusVerdi;
import test.aage.testcase1.domain.NyAvtaleInfo;

public class AvtaleFagSystem { // Mock oppførsel, bruker avtaleType til å styre oppførsel

	public AvtaleStatus opprettAvtale(NyAvtaleInfo avtale) {
		
		if ("Nyopprettet".equals(avtale.getAvtaleType())) {
			return new AvtaleStatus("12345", null, AvtaleStatusVerdi.NYOPPRETTET, "dummydata");
		}
		
		if ("FeilerVedOppdatering".equals(avtale.getAvtaleType())) {
			return new AvtaleStatus("FeilerVedOppdatering", null, AvtaleStatusVerdi.NYOPPRETTET, "dummydata");
		}
		
		if ("FeilerVedBrevsending".equals(avtale.getAvtaleType())) {
			return new AvtaleStatus("FeilerVedBrevsending", null, AvtaleStatusVerdi.NYOPPRETTET, "dummydata");
		}
		
		if ("Exception".equals(avtale.getAvtaleType())) {
			throw new RuntimeException("Kall til AvtaleFagsystem.opprettAvtale feiler");
		}
		
		return null;
	}
	
	public void oppdaterAvtaleStatus(String avtaleNr, AvtaleStatusVerdi avtaleStatusVerdi) {
		
		if ("FeilerVedOppdatering".equals(avtaleNr)) {
			throw new RuntimeException("Kall til AvtaleFagsystem.oppdaterStatus feiler");
		}
		
	}
}
