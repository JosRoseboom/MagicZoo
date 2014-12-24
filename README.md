MagicZoo
========



Magische dierentuin

Probleem
Er is een dierentuin met leeuwen, wolven en geiten. Als een leeuw een geit eet verandert hij in een wolf. Als een leeuw een wolf eet verandert hij in een geit. Als een wolf een geit eet verandert hij in een leeuw. De vraag is nu wat de grootst mogelijke stabiele populatie dieren in de dierentuin is gegeven een beginaantal leeuwen, wolven en geiten.

Analyse
Eerste constatering is dat een populatie slechts stabiel is als hij uit 1 soort bestaat. Tweede constatering is dat elke soort 2 “maaltijden” heeft waarbij hun aantal met 1 afneemt, terwijl er 1 soort maaltijd is waarbij hun aantal met 1 toeneemt.
Het is zaak een set maaltijden te kiezen waarbij het aantal geiten, wolven of leeuwen gemaximaliseerd wordt, terwijl er van de andere twee soorten geen dieren meer in de dierentuin zijn.
Voor elke mogelijke stabiele populatie (alleen leeuwen, alleen wolven of alleen geiten) valt dit probleem te formuleren als een lineair programmeerprobleem. Voor de zoektocht naar een maximale populatie leeuwen ziet dit er als volgt uit:

	Max startLeeuwen + WEG - LEG - LEW
	zodanig dat:
		startGeiten		=	WEG + LEG - LEW
		startWolven		=	LEW + WEG - LEG
		WEG, LEG, LEW	≥	0  
		WEG, LEG, LEW 	∈	N 

Hier staat WEG voor wolf eet geit (wolf en geit nemen af met 1, leeuw neemt toe met 1), LEG voor leeuw eet geit (leeuw en geit nemen af met 1, wolf neemt toe met 1) en LEW voor leeuw eet wolf (leeuw en wolf nemen af met 1 en geit neemt toe met 1). Voor zoektochten naar de maximale populatie geiten en de maximale populatie wolven zijn op dezelfde wijze te formuleren.
Door de eis van de geheeltalligheid en het gebrek aan totale unimodulariteit kunnen we de simplex methode niet gebruiken (zie http://en.wikipedia.org/wiki/Integer_programming).

Implementatie
Voor het oplossen van dit integer lineair is het opensource pakket ojAlgo gebruikt: http://ojalgo.org/.  Voor elke mogelijke stabiele populatie wordt een optimalisatie gedaan. De output van het programma is (voor bv de gevraagde (2055,2006,2017) ):

INFEASIBLE 0.0 @ [0.0, 0.0, 0.0]
Er kon geen stabiele dierentuin gevonden worden met alleen leeuwen

OPTIMAL 2017.0 @ [19.0, 2036.0, 0.0]
Optimale oplossing gevonden voor dierentuin met 4023 wolven

INFEASIBLE 0.0 @ [0.0, 0.0, 0.0]
Er kon geen stabiele dierentuin gevonden worden met alleen geiten

De oplossing is (0,4023,0)

Laatste regel is de output zoals gevraagd. Daarboven de resultaten van de pogingen naar het zoeken van de stabiele maxima per soort. Voor de leeuwen en de geiten bestaat geen geldige oplossing. Voor de wolven wel. Dit resultaat laat zich lezen als: 
Er is een optimale oplossing gevonden
Deze oplossing heeft het aantal wolven met 2017 doen toenemen
Hiertoe heeft 19 keer een wolf een geit opgegeten
Hiertoe heeft 2036 keer een leeuw een geit opgegeten
Hiertoe is geen wolf door een leeuw opgegeten