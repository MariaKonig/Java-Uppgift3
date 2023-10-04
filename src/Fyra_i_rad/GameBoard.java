package Fyra_i_rad;

/*  Klassen GameBoard hanterar allt som har med det visuella att göra.
    Den har spelbrickor(markörer), spelbräde, metod som placerar markörer och print-metoder.
    Den här klassen är superklassen till klassen Game som sköter all spellogik.
*/
public class GameBoard {
    // Markörer i spelet. Markör x representerar spelaren och markör o representerar datorn. Färgerna blir omvända om man inte har dark-mode.
    protected String marker_x = String.valueOf('●'); //Spelare
    protected String marker_o = String.valueOf('◯'); //Dator

    /*  Metod som placerar spelarnas drag på brädan. Den tar in spelarens markör samt vilken rad och kolumn dom vill lägga på.
        Anropas från Game-klassen. Valde att lägga den här metoden i GameBoard klassen och inte i Game klassen
        då den direkt påverkar brädans utseende.  */
    protected void setBoard(String marker, int row, int column) {

        board[row][column] = marker;
    }

    /*  Spelbrädan är initierad med mellanrum för att det inte ska stå null över hela spelplanet när man spelar.
        Hade kunnat göra en loop men spelplanen är inte så stor så jag tyckte det här var snyggaste lösningen.   */
    protected String[][] board = {
            {" ", " ", " ", " "},
            {" ", " ", " ", " "},
            {" ", " ", " ", " "},
            {" ", " ", " ", " "}
    };

    //  Skriva ut metoder-----------------------------------------------------------
    /*  print är en metod som skriver ut spelbrädan. Den anropar toText metoden för att få spelplanen i String-format.
        Sen lägger den till siffror underst på brädan för att visa spelaren vart denne kan lägga och printar slutligen ut slutprodukten i konsolen.
        Det hade vart snyggare om jag hade gjort både toText och print i samma metod. Men då jag modifierade toText från toString metoden kunde jag inte
        ändra för mycket utan att den blev arg. Så jag löste det genom att göra två metoder.    */
    protected void print() {
        StringBuilder newString = new StringBuilder();

        for (String[] row : board) {
            newString.append(toText(row));
            newString.append("\n");
        }
        newString.append("  1 2 3 4");
        System.out.println(newString);
    }

    /*  Metod som returnerar en string-version av en array. toText är en modifikation av Arrays.toString-metoden.
        Jag tog metoden rakt av och gjorde små ändringar i taget för att se hur det påverkade utskriften.
        Till slut lyckades jag ändra tillräckligt mycket för att få något som liknar en spelbräda.
        Jag tyckte toString metoden var ganska svår att förstå sig på inte minst för att jag inte själv skapade logiken.
        Men efter att ha testat med olika ändringar har jag lite mer förståelse för hur den funkar.  */
    private String toText(String[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append("|_");
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append("_|").toString();
            b.append("_");
        }
    }
}
