Koden illustrerer et integrasjonslag med en enkel REST-tjeneste, som kaller flere fagsystemer.
- pakke intlayer: REST-API og tjenesteobjekt
- pakke fagsystem: mockede fagsystem
- pakke domain: dataobjekter inn/ut av API
- pakke jetty (test): jetty-server, klient for å kalle API, main som starter jetty og kaller API med forskjellige input-caser

-----
Forutsetninger/avklaringer av forretningslogikk og mulige scenarier vs fagsystem

Opprett kunde
- feiler ikke så lenge input fra klienten er fullstendig og riktig?
- finnes register over kunder som er "nektet"?
- allerede eksisterende kunder godtas, gis info om dette i respons
- mao. kun tekniske feil eller inputfeil, status nyopprettet, status oppdatert, med evt tilleggsinfo
Får tilbake 
 - statusobjekt med kundenummer og tilleggsinfo, eller
 - feilstatus med forklaring av hvilken input som er feil (evt lage/duplisere validering i int-lag (klient)), eller
 - feilstatus med teknisk feil internt i fagsystem, eller
 - timeout / feil

Opprett avtale
- feiler ikke så lenge input fra klienten er fullstendig og riktig?
- finnes register over kunde-avtalekombo som er "nektet"?
Får tilbake 
 - statusobjekt med avtalenummer og tilleggsinfo, eller
 - feilstatus med forklaring av hvilken input som er feil (evt lage/duplisere validering i int-lag (klient)), eller
 - feilstatus med teknisk feil internt i fagsystem, eller
 - timeout / feil 

Oppdater avtale
Får tilbake 
 - avtalestatusobjekt, eller
 - feilstatus med teknisk feil internt i fagsystem, eller
 - timeout / feil 

Brevtjeneste
Får tilbake 
 - status sendt ok, eller
 - feilstatus med teknisk feil internt i sendetjeneste, eller
 - timeout / feil 

-----
Scenarier og respons fra int-lag 
- opprett kunde eller avtale feiler pga feil/manglende input: status 4xx, forklaring av hva som mangler
  ? skal man slette/deaktivere nyopprettet kunde hvis opprett avtale feiler ?
- teknisk feil ved opprett kunde/avtale: 5xx, forklaring av hva som feiler
- teknisk feil ved brevsending: 5xx, forklaring av status, evt sending til rekjøringskø (int-lag, klient eller driftsrutine tar videre)
- teknisk feil ved oppdatering: som over, men her vil det være annen rekjøring, evt info til kunde
- OK, 2xx med kundeinfo, avtaleinfo

-----
ANBEFALER splitting i status "aktivert" og "sendt til kunde"

Poenget er at brevsending er en operasjon som 1) sender informasjon til ekstern aktør, og 2) ikke kan rulles tilbake,
mens statusoppdatering er en operasjon som kan feile og tydeligvis er "nødvendig" for at avtalen skal være gyldig.
Hvis informasjonen sendt med brev tilsier at avtalen ER gyldig, er dette feil måte/rekkefølge å utføre tingene på.

Beste løsning er å skille mellom status "aktiv/gyldig", som avtalen er i allerede før brevsending, 
og status "sendt til kunde", som må ansees som tilleggsinformasjon.
Begge disse statusene må ansees som OK i forhold til at avtalen er gyldig, 
og så må det finnes "nødrutiner" som håndterer statusoppdatering i ettertid for de som har feilet,
og brevsending + statusoppdatering i ettertid dersom brevsending feilet.

