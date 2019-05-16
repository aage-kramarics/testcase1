package test.aage.testcase1.fagsystem;

import test.aage.testcase1.domain.AvtaleStatus;
import test.aage.testcase1.domain.NyAvtaleInfo;

public class BrevSystem { // Mock oppførsel, bruker avtaleType til å styre oppførsel

	public void sendAvtale(NyAvtaleInfo avtale, AvtaleStatus avtaleStatus) {

		if ("FeilerVedBrevsending".equals(avtale.getAvtaleType())) {
			throw new RuntimeException("Kall til BrevSystem feiler");
		}
		
	}
}
