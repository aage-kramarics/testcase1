package test.aage.testcase1.intlayer;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import test.aage.testcase1.domain.AvtaleStatus;
import test.aage.testcase1.domain.AvtaleStatusVerdi;
import test.aage.testcase1.domain.KundeStatus;
import test.aage.testcase1.domain.NyAvtaleInfo;

@Path("/") 
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IntLayerApi {

    @Autowired
    private OpprettAvtaleTjeneste opprettAvtaleTjeneste;
    
	@Path("avtale")
    @PUT 
    public Response opprettAvtale(NyAvtaleInfo nyAvtaleInfo) {
		// TODO valider input og returner 400 hvis feil
		
		KundeStatus kundeStatus = null;
		AvtaleStatus avtaleStatus = null;
    	try {			
			kundeStatus = opprettAvtaleTjeneste.opprettKunde(nyAvtaleInfo.getKunde());
			// TODO hvis kundestatus tilsier at vi ikke kan gå videre, sende feilrespons
    	} catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    	}
    	
    	try {						
			avtaleStatus = opprettAvtaleTjeneste.opprettAvtale(nyAvtaleInfo);
			// TODO hvis avtaleStatus tilsier at vi ikke kan gå videre, sende feilrespons
    	} catch (Exception e) {
    		// TODO rull tilbake ny kunde hvis denne ble opprettet ?
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    	}
    	
		avtaleStatus.setKunde(kundeStatus);
    	try {						
			opprettAvtaleTjeneste.sendAvtale(nyAvtaleInfo, avtaleStatus);
    	} catch (Exception e) {
    		// TODO legg utsendelse på "feilkø"
    		avtaleStatus.setStatus(AvtaleStatusVerdi.NYOPPRETTET);        // Signaliserer at avtalen ble opprettet, men ennå ikke sendt ut
	        return Response.status(Response.Status.OK).entity(avtaleStatus).build();
    	}
			
    	try {						
			opprettAvtaleTjeneste.oppdaterAvtaleStatus(avtaleStatus.getAvtaleNr(), AvtaleStatusVerdi.SENDT_KUNDE);
    	} catch (Exception e) {
    		// TODO legg statusoppdatering på "feilkø"
    		avtaleStatus.setStatus(AvtaleStatusVerdi.SENDT_KUNDE);        // Signaliserer at avtalen ble opprettet og sendt kunde, men ennå ikke aktivert
	        return Response.status(Response.Status.OK).entity(avtaleStatus).build();
    	}
			
		avtaleStatus.setStatus(AvtaleStatusVerdi.AKTIV);        // Signaliserer at avtalen ble aktivert
	    return Response.status(Response.Status.OK).entity(avtaleStatus).build();
    }
}

