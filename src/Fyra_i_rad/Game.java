package Fyra_i_rad;

import java.util.Random;
import java.util.Scanner;

/*  Klassen Game kör all spellogik. Den extend-ar klassen GameBoard och använder sig av alla dess metoder och samtliga variabler.
    Så det kändes naturligt att den här klassen skulle ha allt GameBoard klassen har.
    Jag hade också kunnat skapa upp ett nytt objekt av klassen GameBoard i den här klassen. Och då hade jag vart tvungen att skriva instans namnet innan
    varje anrop.  */
public class Game extends GameBoard {
    //StatusBoard är till för att kolla om någon har fått 4 i rad. Den lagrar tal som adderas beroende på placering.
    // rad 1 i matrisen motsvarar vilka rader spelare x har spelat på och hur många gånger.
    // rad 2 motsvarar vilka rader spelare o har spelat på och hur många gånger.
    // rad 3 motsvarar vilka kolumner spelare x har spelat på och hur många gånger.
    // rad 4 motsvarar vilka kolumner spelare o har spelat på och hur många gånger.
    // Ett drag uppdaterar 2 värden i statusBoarden, rad och kolumn för resp spelare
    // Om något index kommer upp till värdet 4, betyder det att någon har vunnit genom att lägga 4 på samma rad eller kolumn.
    protected int[][] statusBoard = new int[4][4];
    Scanner scan = new Scanner(System.in);
    Boolean isComputersTurn = false;
    Boolean isPlaying = true;

    /*  Metoden play hanterar spelets turordning och moment.
        Den börjar med att anropa metoden clearAndPrint som rensar terminalen och skriver ut en tom spelbrädan.
        Sen hoppar den in i en while-loop som kommer köras tills spelet har hittat en vinnare eller det blir oavgjort.
        I loopen printar den ut att det är spelarens tur.

        Sen anropas metoden choice som läser in och placerar spelarens drag. Metoden sätter också isComputersTurn till true om spelarens drag var giltig.
        Sen kallar den på metoden checkFourInRow som kollar om det finns en vinnare. Metoden avslutar då spelet automatiskt genom att sätta isPlaying till false.
        Om spelarens drag va giltig har variabeln isComputersTurn sats till true och datorn kommer att spela sitt drag.
        Då kommer metoden checkFourInRow kolla igen om det finns en vinnare.

        Om spelarens drag inte var giltigt kommer variabeln isComputersTurn fortsätta vara false.
        Koden kommer då hoppa över datorns tur, börja om loopen igen och det blir spelarens tur igen.   */
    public void play() {
        clearAndPrint();
        while (isPlaying) {
            System.out.println("Din tur. Skriv in vilken siffra du vill lägga på\n");
            choice();
            checkFourInRow();
            if (isComputersTurn) {
                computersTurn();
                checkFourInRow();
            }
        }
    }

    /*  Metoden choice läser in spelarens val av kolumn att lägga på. Det gör den genom en scanner som läser in nästa tal som skrivs i terminalen.
        Inläsningen ligger i en try-catch för att fånga upp om något annat än ett tal har skrivits in och skriver då ut ett felmeddelande.
        Sedan kollar den om det inlästa talet är mellan 1 och 4. Om inte skrivs ett annat felmeddelande ut.
        Om spelaren skrivit in ett tal och talet är mellan 1 och 4 anropas metoden placeChoice som försöker placera ut draget i brädan. */
    private void choice() {
        int choice;

        try {
            choice = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
            showMessageAndPrint("Inte ett gilltigt kommando. Försök igen");
            return;
        }
        if (choice >= 1 && choice <= 4) {
            placeChoice(marker_x, choice - 1);
        } else {
            showMessageAndPrint("Finns bara rad 1 till 4");
        }

    }

    /*  Metoden placeChoice sätter markören på nästa lediga rad i vald kolumn. Den vill ha in-argumenten vilken spelare och val av kolumn.
        Den börjar kolla längst ner och kollar om platsen är ledig, om inte jobbar den sig upp rad för rad. Om en ledig plats hittas uppdateras spelbrädet samt
        statusboarden. Den uppdaterade spelbrädan målas ut och isComputersTurn sätts till true.
        Om den valda kolumnen är full skickar den ett felmeddelande till spelaren om det är dennes tur. Koden kommer att köra vidare i play-metoden,
        men eftersom isComputersTurn aldrig blev satt till true kommer den att hoppa över datorns tur och köra om och det blir spelarens tur igen.
        Annars om det är datorns tur ropar den på computersTurn som får köra igen och datorn får välja på nytt.

    */
    private void placeChoice(String player, int choice) {

        for (int i = 3; i >= 0; i--) {
            if (board[i][choice].equals(" ")) {

                setBoard(player, i, choice);
                updateStatusBoard(player, i, choice);
                clearAndPrint();
                isComputersTurn = true;
                return;
            }
        }

        if (player.equals(marker_x)) {
            showMessageAndPrint("Raden är full. Välj en annan rad.");
        } else {
            computersTurn();
        }
    }

    /*Metoden uppdaterar statusBoard genom att addera till det index som motsvarar spelarens drag. Visualisering:
         ex. Spelare x spelade på rad 1 kolumn 3. Då adderas det index som motsvarar den positionen med 1.
        Spelare x placerade på rad      [1,0,0,0] <- varje index motsvarar en rad på brädan. Rad 1 blir första indexet.
        Spelare o placerade på rad      [0,0,0,0]
        Spelare x placerade på kolumn   [0,0,1,0] <- varje index motsvarar en kolumn på brädan. kolumn 3 blir 3e indexet.
        Spelare o placerade på kolumn   [0,0,0,0]
     */
    private void updateStatusBoard(String player, int row, int column) {
        if (player.equals(marker_x)) {
            statusBoard[0][row]++;
            statusBoard[2][column]++;
        } else {
            statusBoard[1][row]++;
            statusBoard[3][column]++;
        }
    }

    /*  Metoden computersTurn genererar datorns val av drag. Random genererar ett tal mellan 1 och 4.
        Talet representerar vilken kolumn den väljer att spela på. Talet skickas sen till metoden placeChoice som försöker lägga ut det draget på ledig rad.
        Om raden är full kommer det att bli datorns tur igen som får ett nytt försök att välja kolumn.    */
    private void computersTurn() {
        Random rand = new Random();
        int column = rand.nextInt(0, 4);
        placeChoice(marker_o, column);
    }

    /*  Metoden checkFourInRow kollar alla möjliga sätt någon kan ha vunnit på(vågrätt, lodrätt, diagonalt).
        Kollar också om det är oavgjort och inga fler drag går att spela.   */
    private void checkFourInRow() {
        /*  Loopar igenom hela scoreBoard-matrisen för att se om någon spelare vunnit genom att lägga 4 i rad på en kolumn eller rad. Den kollar mer specifikt
            ifall något värde i matrisen är lika med 4. Det skulle betyda att någon spelare lagt på samma rad 4 gånger alternativt samma kolumn 4 gånger.    */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (statusBoard[i][j] == 4) {
                    if (i == 0 || i == 2) {
                        weHaveAWinner(marker_x);
                    } else {
                        weHaveAWinner(marker_o);
                    }
                    return;
                }
            }
        }

        /*  Kollar om spelare vunnit diagonalt från vänster till höger nerifrån-upp => /
            Börjar med att kolla längst ner till vänster. Om det indexet inte är tomt, sparar sparas markören som ligger i den positionen.
            Sen kollar den diagonalt i resterande 3 index om det som ligger där är samma som markören. Isf finns det en vinnare och
            markören skickas till metoden weHaveAWinner för utskrift och avslut av spel.
            return används i slutet av satsen för att avbryta läsning av resterande kod i metoden. */
        String marker;
        if (!board[0][3].equals(" ")) {
            marker = board[0][3];
            if (marker.equals(board[1][2]) && marker.equals(board[2][1]) && marker.equals(board[3][0])) {
                weHaveAWinner(marker);
                return;
            }
        }
        /*  Kollar om spelare lagt diagonalt från vänster till höger uppifrån-ner => \ Samma princip som tidigare if-sats men åt andra hållet.
            Kunde ha gjort en if-else sats men av någon anledning kom den inte in i koden då. Så jag valde att lösa det med två separata if-satser. */
        if (!board[0][0].equals(" ")) {
            marker = board[0][0];
            if (marker.equals(board[1][1]) && marker.equals(board[2][2]) && marker.equals(board[3][3])) {
                weHaveAWinner(marker);
                return;
            }
        }

        /*  Sats kollar om alla kolumner i översta raden är fyllda. Om alla kolumner är fyllda finns inga mer drag att göra vilket betyder att det är oavgjort.
            Då koden ligger sist i metoden som kollar för vinst betyder det att ingen har vunnit, då det hade avbrutit metoden.
            Ingen return metod här då det är sista satsen i metoden.    */
        if (!board[0][0].equals(" ") && !board[0][1].equals(" ") && !board[0][2].equals(" ") && !board[0][3].equals(" ")) {
            weHaveAWinner("Oavgjort!");
        }
    }

    /*  Metoden weHaveAWinner tar in vinnar-markören som in-argument (eller texten "oavgjort") och printar ett resultat meddelande till spelaren.
        Avslutar med att sätta isPlaying till false vilket avslutar while-loopen i play-metoden och programmet hoppar ut från klassen Game till main-koden igen. */
    private void weHaveAWinner(String winner) {
        clearAndPrint();
        if (winner.equals(marker_x)) {
            showMessageAndPrint("You win! Well played");
        } else if (winner.equals(marker_o)) {
            showMessageAndPrint("The computer wins!");
        } else {
            showMessageAndPrint(winner);
        }

        isPlaying = false;
    }

    /*  Metoden showMessageAndPrint gör nästan samma sak som clearAndPrint med undantaget att den skriver ut ytterligare en text ovanför spelplanet.
        Och slutligen sätter den isComputersTurn till false, detta då den bara används för felmeddelanden till spelare pga av felaktig indata samt
        annonsering av vinnare.
        Genom att sätta isComputersTurn till false hoppar den över datorns tur i play-metoden och det blir spelarens tur igen. */
    private void showMessageAndPrint(String message) {
        clearScreen();
        System.out.println(message + "\n");
        print();
        isComputersTurn = false;
    }

    //Metoden clearAndPrint rensar först skärmen och sen målar den upp brädan. Används bl.a. varje gång ett drag görs.
    private void clearAndPrint() {
        clearScreen();
        print();
    }

    /*  Metoden clearScreen blev en ganska ful rad kod som blev lösningen till problemet att det målas ut nya brädor efter varandra och
        de inte ska synas i terminalen samtidigt. Valde att göra det till en egen metod för att den används flera gånger i koden
        men mest för att jag inte ville se den mer än nödvändigt.    */
    private void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

    }
}


