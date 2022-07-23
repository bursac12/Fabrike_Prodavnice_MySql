# Fabrike_Prodavnice_MySql
MySQL
JPA
JMS
UML


Potrebno je projektovati informacioni sistem za potrebe firme. Firma se sastoji od fabrike i prodajnih mesta. Ovaj informacioni sistem se sastoji od tri tipa aplikacije:
● aplikacije za prodavca
● aplikacije za fabriku
● aplikacije za menadžera prodavnice
Aplikacije trebaju biti implementirane na programskom jeziku Java koristeći JMS za razmenu poruka između aplikacija, MySQL i JPA za komunikaciju izmeću aplikacije i baze podataka. Aplikacije za prodavca i aplikacije za fabriku komuniciraju direktno sa korisnikom putem standardnog ulaza i standardnog izlaza, dok aplikacija za menadžera prodavnice ne koristi standardni ulaz. Potrebno je samostalno osmisliti arhitekturu ovog JMS sistema, kao i baze podataka koje će zadovoljiti sve tražene zahteve.

Aplikacija za prodavce

Sve prodavnice imaju zajedničku bazu u kojoj se čuvaju informacije o stanju artikala u sistemu za svaku od prodavnica. Artikal se u bazi pamti pod određenom šifrom, nazivom, tipom i cenom. Takođe se za svaku prodavnicu pamti njen dnevni promet. Promet se sa sastoji od broja prodatih artikala i ukupnog iznosa. Kada kupac dođe u prodavnicu on može da zatraži da kupi određen artikal u određenoj količini ili da kupi već rezervisani artikal1 u određenoj količini. Prodavac u aplikaciji, na zahtev kupca može da pretraži odredjeni artikal po tipu ili nazivu, gde mu se onda prikazuje lista svih artkala koji zadovoljavaju zadati kriterijum pretrage. Prodavac unosi u sistem željeni zahtev i saopštava kupcu dobijene rezultate. Takođe, kupac može zatražiti i informaciju o stanju artikala u prodavnici (stanje može biti: zapakovan ili otpakovan). Informacije o stanju artikala se ne čuvaju u bazi, već prodavac šalje zahtev menadžeru koji nakon fizičke provere vraća informaciju prodavcu. U slučaju da postoji dovoljna količina traženog artikla na stanju u prodavnici, i da kupac želi da izvrši kupovinu (nakon mogućeg obaveštenja o stanju artikla), prodavac može da evidentira kupovinu promenom količine zadatog artikla na stanju prodavnice. U slučaju da artikal ne postoji na stanju prodavnice, prodavac može da izvrši upit kojim se dobija informacija u kojim prodavnicama postoji traženi artikal u traženoj količini. Takodje, kupac i u ovom slučaju može da traži zapakovan/otpakovan proizvod, nakon čega se menadžeri ostalih prodavnica kontaktiraju direktno kako bi poslali informaciju o stanju artikla. Ako artikal, u zadatoj količini i zadatom stanju, postoji u nekoj prodavnici, može se kontaktirati menadžer radi izvršavanja rezervacije, ukoliko kupac želi istu. Prilikom rezervacije korisnik bira da li će artikal preuzeti u prodavnici u kojoj se trenutno nalazi ili u prodavnici u kojoj se nalaze artikli. U slučaju da je izabrao da željeni artikal preuzme u trenutnoj prodavnici, potrebno je da ostavi svoje kontakt podatke, kako bi bio obavešten o stanju isporuke. Rezervacija ističe nakon 48 časa od prispeća artikla.


Aplikacija za menadžere prodavnice

Menadžeri prodavnice rade:
● proveravaju stanje artikala,
● izvršavaju rezervacije,
● primaju novu robu,
● primaju informacije o promeni cene.
1 Kada prodavcu daje kontakt informacije sa kojima je izvršena rezervacija.
Prilikom provere stanja artikala, artikal će u 70 posto slučajeva biti neotpakovan. Menadžeri takođe imaju pristup bazi prodavnice.


Aplikacija za radnika u fabrici

Pored baze kojoj pristupaju prodavnice, postoji još jedna interna za fabriku kojoj pristupaju radnici. Za svaki artikal se u ovoj bazi čuva naziv, šifra, tip, cena i vreme potrebno za proizvodnju. Smatrati da proizvodnja više istih artikala traje uvek isto vreme, nezavisno od broja artikala. Zaposleni u fabrici zadaje šta da se proizvede sledeće i u kojoj količini. Po završetku proizvodnje, artikli se šalju u proizvoljan objekat. Zaposleni u fabrici može da promeni cenu nekog artikla i tada tu informaciju šalje svim menadžerima.
