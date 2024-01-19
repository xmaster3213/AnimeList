This application is a school project and needed to be made in italian.

# Animelist
*Progetto di programmazione di Sistemi Mobile A.A. 2022/2023*

## Funzionalita’ dell’applicazione
L’applicazione e’ una libreria che permette agli utenti di tenere
traccia degli anime che ha visto, sta guardando o che ha
intenzione di guardare. Sono offerte varie funzionalita’ come:
- La possibilita’ di cercare nuovi anime da aggiungere alla
propria lista, cosi’ da non perdere mai cio’ che si ha
intenzione di vedere in futuro.
- Usare filtri per trovare nuovi anime a cui si potrebbe
essere interessati, come cercare anime in base alla
stagione dell’anno, alla loro popolarita’, quale uscira’ nei
prossimi mesi, assieme ad altri filtri che l’utente puo’
decidere di aggiungere per soddisfare le proprie esigenze.
- Tenere traccia di tutti gli anime che si intende vedere, si
sta guardando e si ha intenzione di vedere. Questo si puo’
fare anche dando loro un voto, segnando quando si ha
iniziato la serie, quando la si ha finita e a che episodio si e’
arrivati.
- Controllare alcune informazioni generali di ogni anime,
come il voto medio, lo stato di rilascio, la lista di episodi
rilasciati con link al sito per vederli, e la lista dei
personaggi presenti all’interno della serie.
- Controllare lo stato del proprio profilo, che permette di
vedere statistiche sul numero totale di anime guardati, il
numero di ore, in media che voto si assegna alle opere,
oltre ad alcuni grafici che ci permettono di vedere quanti
anime abbiamo iniziato a vedere per ogni anno o la
distribuzione delle serie che abbiamo nella nostra lista.

## Tecnologie Utilizzate

- Material design per alcune parti di grafica dell’applicazione
- Retrofit2 per interagire con l’[API di Anilist](https://anilist.gitbook.io/anilist-apiv2-docs/)
- OkHttp3 per poter eseguire autenticazione sulle API, e per
loggare le chiamate sulla rete
- Picasso per scaricare le immagini e caricarle
sull’interfaccia in maniera asincrona
- PhilJay per creare grafici con i dati statistici dell’utente

## L’Applicazione

### Home page

![Home Page](https://cdn.discordapp.com/attachments/642513113111068682/1197892486471893062/1ekyHaSTONYCvx4oT9CwvPA3aka_ujQTzxfl1XASBJQ_J01ujzzHoYmjvKgDkBA.png?ex=65bceb50&is=65aa7650&hm=a8fedecfe29fba845f14866e25a1424f277243969e65ae1308cd055c56a1c2a0&)

Al premere di un anime qualsiasi l’utente verra’ portato alla pagina dell’anime specifico.
(Immagine seguente)

### Anime

![Anime Page](https://cdn.discordapp.com/attachments/642513113111068682/1197893281430909020/17bmIQVtGsovx_SjgmeSsz8NKVa5iNTNsoxadb9PR5iUIqSVQ-QicWwBDzf3Fo2Q.png?ex=65bcec0e&is=65aa770e&hm=f8919db24cce3626503270ba75d096f5305412745fdf9e7b0668e0cb2db4c1ff&)

In questa pagina e’ possibile vedere tutte le informazioni sull’anime, anche scrollando tra le varie pagine proposto (overview, characters, episodes).
All’interno della pagina dei personaggi e degli episodi e’ possibile premere sull’elemento per essere portati relativamente alla pagina del personaggio e al sito dell’episodio.
Premendo sul tasto (completed in questo caso) verra’ mostrata un’interfaccia per modificare lo stato dell’anime in particolare all’interno della propria lista.

### Character

![Character Page](https://cdn.discordapp.com/attachments/642513113111068682/1197893302188523560/19yaM_--f04ElFqWYxs04wF637jzW6ItEFAzdYqeJvuHMz47mJ4CiaxL---9yFUY.png?ex=65bcec13&is=65aa7713&hm=a026d54d64d7c601995520a4ef5bfcbe55409d489a800c28b1c607414f0e9317&)

Pagina con tutte le informazioni del personaggio selezionato.

### Discovery

![Discovery Page](https://cdn.discordapp.com/attachments/642513113111068682/1197893886987730944/1_L7Kd_7rqT3iqHnoTXsvEnPpG4rZe36Oy0whTfS8jU5HdvsjdKISY7bw7sntGA.png?ex=65bcec9e&is=65aa779e&hm=8c5def8bf3e6133ebcf9879ec2f32034e2efda92889f7f939bcc88b9ee98dbe3&)

Questa pagina permette all’utente di cercare fra tutta la lista di anime, applicando se desiderato anche dei filtri per la ricerca. Questo puo’ essere fatto premendo sul tasto alla destra della barra di ricerca e in seguito aggiungendo i filtri desiderati.
Anche in questa pagina premere su un qualsiasi anime portera’ l’utente alla pagina specifica dell’anime selezionato.

### My List

![My List Page](https://cdn.discordapp.com/attachments/642513113111068682/1197893914665947156/1eAXMdk3J9J9ZHtx0R_pr5v8P8w8BjIsJbAe8TC6rvYWgBqBk5w2Tya67mzuOo2o.png?ex=65bceca5&is=65aa77a5&hm=f9e8cb7f4b3c557014fa0422c4ca9eb0d8eeb5f3f0daa1e8f8256b897b6dff77&)

In questa pagina l’utente puo’ scorrere fra tutti gli anime che ha aggiunto alla sua lista, e in caso lo ritenga necessario anche modificarne i valori che gli ha dato, come voto, data di aggiunta, e altro. Questo puo’ essere compiuto premendo sul tasto a forma di matita sulla destra dell’elemento.
Inoltre l’utente puo’ filtrare quali elementi vuole vedere o ordinarli in modo diverso in base alle sue preferenze premendo sul tasto in alto a destra.

### Profile

![Profile Page](https://cdn.discordapp.com/attachments/642513113111068682/1197893940314128414/1ElH-xE_vxgM2snPObxYTrPRhvE7c8CfkdpHMX0G5QpBzcCE-0f00vSZjfOw4MQ.png?ex=65bcecab&is=65aa77ab&hm=82c5d5d903badb456b84db1a086e97aa3490943ab473c98f7ee00715e2b14f0c&)

In questa pagina l’utente potra’ visualizzare il proprio profilo, con le sue statistiche e grafici.

### Login

![Login Page](https://cdn.discordapp.com/attachments/642513113111068682/1197893963370205214/1Yi3nU7NgLu1ObbmZC-R2sTP3xz5KzeLiJq1fGQiropOUz4ZQrsxxMonxxFPzfg.png?ex=65bcecb0&is=65aa77b0&hm=740b277e8f7c689c069470a7e1f5152931a494ffd0ce044c0573e9154225260e&)

Pagina di login alla applicazione.

## Mockup Applicazione

![Mockups Applicazione](https://cdn.discordapp.com/attachments/642513113111068682/1197894696488402954/1B73a_-11-FOgX4w5UYtBNI5X31qk0dLxKapOqkO_2gGKdS7U1utr0k-OvUdNfyM.png?ex=65bced5f&is=65aa785f&hm=60fb7642885ad1dd5d4fd634afbf0f03607156e918b5886c4b19c1c123f6421b&)
![Mockups Applicazione](https://cdn.discordapp.com/attachments/642513113111068682/1197894711780851813/1LrOAiLSb3yeYyMPxxmhuWYmHly6PTQy6had3dbtN0h6lSjDF8kBMd9xkqja3jA.png?ex=65bced63&is=65aa7863&hm=51c8135be887a2af3dc1cc1d2a6b232476c447d2c5bdd068d49015f208f44ecd&)

Il mockup fatto in adobe XD all’interno del progetto e’ interagibile per una migliore presentazione dell’applicativo.
