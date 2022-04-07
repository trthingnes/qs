# Qs - Prosjekt i fullstack applikasjonsutvikling

Qs er et køsystem for læringsassistenter og faglærer som gir oversikt over hvilke studenter som trenger veiledning, eller godkjenning av øvinger. Faglærere kan lage nye fag, og redigere faginfomasjonen om dette er nødvendig. De kan også legge til studenter, og gi rettigheter som læringsassistenter til de som trenger det. Læringsassistenter kan hente studenter fra køen for å hjelpe dem. Studenter kan stille seg i kø og se aktive fag i navigasjonsmenyen. Faglærere vil i dette tilfellet som en forenkling fungere som admin for systemet.

## Teamet

Prosjektoppgave i faget IDATT2105 er gjennomført av

- Tor-Øyvind Paulsrud Bakken
- Jonathan Løseth
- Tobias Rødahl Thingnes

## Innhold

1. [Applikasjonen](#applikasjonen)
2. [Teknologier](#teknologier)
3. [Installasjon](#installasjon)
4. [Databaseskjema](#databaseskjema)
5. [Applikasjonsstruktur](#applikasjonsstruktur)
6. [Sikkerhet](#sikkerhet)
7. [API-dokumentasjon](#api-dokumentasjon)
8. [CI](#ci)
9. [Kommentarer](#kommentarer)
10. [Fremtiden](#fremtiden)

## Applikasjonen

Prosjektet består av en backend og en frontend hvor begge er utviklet med mye brukte rammeverk. På frontend benytter vi Vue 3, med Vuetify 3 som komponentrammeverk. Vue er et godt utviklet rammeverk, med gode muligheter for skalerbarhet og oppdateringer som kan bli nødvendig i fremtiden. Vi brukte JavaScript til å skrive frontend, noe som bydde på litt problemer. I etterkant ser vi at vi burde ha benyttet et språk med sterkere typer som TypeScript. På backend har vi brukt Spring Boot som rammeverk for å bygge apiet, og Maven som byggesystem. Vi har også valgt å skrive i Kotlin i stedet for Java. Dette på grunn av den forbedrede lesbarheten og funksjonaliteten. Kotlin er i tillegg krysskompatibelt med Java og støtter derfor alle de samme rammeverkene.

Applikasjonen har tre viktige aktører:

1. Endebruker (student)
2. Moderator (læringsassistent)
3. Administrator (faglærer)

En _endebruker_ har begrenset tilgang og kan

- Logge inn
- Endre passord
- Endre profil
- Stille seg i kø
- Se egne detaljer i emne

En _moderator_ har tilgang til alle aspekter en endebruker har, i tillegg til følgende:

- Endre kø
- Markere øvinger som godkjent
- Liste alle studenter i et emne

En _administrator_ har også tilgang til følgende:

- Opprette nye emner
- Endre eksisterende emner
- Gi tilgang til andre brukere

## Teknologier

- **Vue** - JavaScript-rammeverk for å bygge brukergrensesnitt.
- **Vuetify** - Material Design komponent-rammeverk som benyttes i kombinasjon med Vue.
- **Prettier** - Formateringsverktøy for JavaScript-kode.
- **Spring Boot** - Server-side rammeverk for å lage API.
- **Spring Data** - Utvidelse til Spring som forenkler prosessen med å hente/lagre informasjon.
- **Spring Security + JWT** - Utvidelse til Spring som tillater sikring av endepunkter.
- **Swagger** - Utvidelse som samler API-dokumentasjon på en samlet side.
- **H2 Database Engine** - Databaseløsning som tillater lagring både som fil og i minne.
- **Java Persistence API** - Database-løsning for å "oversette" klasser til database-tabeller.

## Installasjon

1. Enten last ned kildekoden i en zippet mappe og unzippe den, eller klone repo'et.
2. Åpne kildekoden i en IDE som støtter Kotlin.
3. Kjør BackendApplication, og gå til `http://localhost:8888/h2`.
4. Åpne filen `sampledata.txt` fra backend mappen, og legg dette inn i databasen.
5. Bytt mappe i terminalen til frontend, og kjør `npm run serve`.
6. Prosjektet skal nå kjøre, med testbrukere som blir lagt til i sampledata.txt filen.

## Databaseskjema

<img src="https://user-images.githubusercontent.com/70698120/161603282-329e5f0a-0bdc-4a66-abd7-5685b6e4ae6d.png" height="400" />

## Applikasjonsstruktur

### Backend

<img src="https://user-images.githubusercontent.com/70698120/161535150-c3cf3a49-4007-4b83-b886-cd2061002b50.png" height="300" />

**Config**

Inneholder konfigureringsfilene til prosjektet. Her ligger kun Swagger-konfigurasjonen for øyeblikket. Sikkerhetskonfigurasjonen ligger under `security`.

**Entity**

Entity-klassene er klassene som blir mappet til databasen ved hjelp av JPA. Det er disse som bestemmer hvilke tabeller, verdier og koblinger vi får i databasen.

**Controller**

Controller-klassene tilbyr endepunkter for klienter over HTTP. Alle funksjonene i kontrollerene tilbyr ett endepunkt med en metode.

**DTO**

DTO (Data Transfer Object) er klasser som "pakker" inn data som sendes mellom backend og frontend. Disse brukes når vi tar imot data som metode-parameter, og når vi sender en respons.

**Repository**

Repository-klassene er ansvarlige for å kommunisere med databasen. Her definerer vi den funksjonaliteten vi trenger, så tar `Spring Data` seg av resten.

**Security**

Security-klassene konfigurerer og tilbyr tjenester som kreves for at innlogging og autentisering skal fungere som forventet. Det er her `Principal` blir definert slik at denne kan injiseres i endepunktene i kontrollerene.

**Service**

Service-klassene er et mellomledd mellom kontrollerene og repository-ene og inneholder per nå konfigurasjon som kreves for autentisering av brukere.

### Frontend

<img src="https://user-images.githubusercontent.com/70698120/161611377-b6ad3287-4bf8-4871-b24c-6532a0b0c043.png" height="200" />

**Components**

Vi har valgt å bruke komponenter der det er hensiktsmessig. For eksempel navigasjonsbaren til venstre på skjermen. Denne komponenten skal være tilgjengelig til enhver tid, og er en sentral del av applikasjonsvinduet.

**Plugins**

Inneholder Javascript-filer som importerer og initialiserer Vuetify i prosjektet, samt definerer fonten "Roboto" som hovedfont.

**Router, store og services**

Router og store-mappene inneholder konfigurasjonen til `Vue Router` og `Vuex`. Dette er to utvidelser av `Vue` som brukes veldig ofte. Router-mappen inneholder informasjon om hvilke URL-er som peker til hvilket view. Store-mappen inneholder variabler som kan settes i en felles midlertidig lagringsplass. Services holder api.js som er filen som inneholder alle metoder som kaller API-et.

**Views**

Inneholder alle vinduer, altså de som ligger til høyre for navigasjonsbaren. Prosjektet bytter hyppig mellom views, i motsetning til components.

## Sikkerhet

Sikkerhet har vært et fokus gjennom hele prosessen, da vi mener det er et av de viktigste punktene å ta hensyn til som student. Vi bruker cookies for å lagre autentiseringen til en bruker i 24 timer. Etter dette, må brukeren logge inn på nytt.

Cookien brukes hver gang api-kall blir gjort, som gjør at nettsiden alltid vil sjekke om brukeren er autentisert eller ikke. Dette lar oss også sikre at en bruker ikke kan hente ut andre brukeres informasjon.

Alle passord er saltet og hashet med BCrypt. Dette for å sikre sensitiv informasjon i tilfellet det skulle skje en sikkerhetslekkasje.

## API-dokumentasjon

Prosjektet har tatt i bruk Swagger 2 til API-dokumentasjon. Denne har addressen http://localhost:8888/swagger-ui/. Prosjektet må kjøres lokalt, og backend må kjøres for at Swagger UI skal kunne vises.

For å autentisere, kan "authorization-controller" brukes:

![image](https://user-images.githubusercontent.com/70698120/161600561-4dd5b644-49b2-4b21-a365-e796608eed25.png)

Vi har ikke konfigurert Swagger for å kunne sende forespørsler derifra, men alle endepunktene er dokumentert.

## CI

Prosjektet bruker lintere både i frontend og backend for å passe på at kode blir rett formatert. Det er laget CI-filer for både backend og frontend som automatisk kjører tester og kontrollerer formattering når man lager en pull request til `main`.

[Frontend CI](https://github.com/trthingnes/qs/blob/main/.github/workflows/frontend-ci.yml) og
[Backend CI](https://github.com/trthingnes/qs/blob/main/.github/workflows/backend-ci.yml).

## Kommentarer

- Vi tok en alt for stor bit av oppgaven da vi satte oss ned for å planlegge. Vi syns det var veldig vanskelig å forenkle bort funksjonalitet siden alt mer eller mindre trenger å være der for å kunne ha et produkt som fungerer til slutt. At vi ikke forenklet bort en større del av oppgaven gjorde at vi fikk for lite tid til å gjøre på andre ting som dokumentering, refaktorering og testing av den koden vi har.

- Alt for mye tid gikk med på å få `Spring Security` til å fungere på en god måte. Om vi skulle gjennomført prosjektet igjen er dette det første vi skulle forenklet bort, siden dette ga veldig lite resultat igjen for veldig mye arbeid.

- Som følge av `Spring Boot` og `Spring Security` fikk vi store problemer med testing i backend. Gjentatte forsøk på å skrive tester ble gjort gjennom hele prosjektet, men etter å ha brukt et urimelig antall timer på dette bestemte vi oss for å bruke tiden vår til å implementere funksjonalitet i stedet. Det finnes en branch i repoet som har forsøket vårt på testing. Vi tar opplevelsen som læring og tar med oss videre at vi bør legge mye mer tid i testingen tidlig.

- I frontend kom vi for sent i gang med å skrive tester slik at da vi begynte, hadde prosjektet så mange dependencies at det ble svært vanskelig å nøste opp i alt. For eksempel fikk vi problemer med cookies, men selv om vi flyttet cookies bort fra views og komponenter oppstod det nye problemer med for eksempel `router`.

- Vi vil si oss veldig fornøyde med produktet vi har lagd selv om vi ikke er helt fornøyde med alle aspekter som testing og refaktorering. Brukergrensesnittet er veldesignet og ser helt fantastisk ut. I tillegg fungerer det veldig godt på mobil. Endepunktene er godt strukturert og gir mening. Vi har også fått implementert nesten all funksjonaliteten som oppgaven ber om.

## Fremtiden

Vi vet at vi mangler noe funksjonalitet som burde være med:

- Bedre testing, både på frontend og backend.
- Muligheten for moderatorer og administratorer til å aktivere/deaktivere køer.
- Muligheten for moderatorer og administratorer til å endre rekkefølgen på køen.
- Vise hvilken øving en student trenger godkjent eller hjelp med. Nå vises ikke øvingsnummer.

Framtidig funksjonalitet vi kunne ønske var med:

- Sortere kø slik at hjelp kommer over godkjenning, og alle i køen blir sortert fra lavest til høyest oppgavenummer.
- Funksjonalitet for at studass kan flytte en student X antall plasser bakover i køen dersom de tilfeldigvis ikke var klar når det var deres tur likevel, men at de slipper å stille seg i kø på nytt.
