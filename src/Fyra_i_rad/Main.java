package Fyra_i_rad;

import java.util.Scanner;

/*  Main-klassen till spelet 4 i rad. Ett spel som går ut på att lägga fyra brickor i rad vågrätt, lodrätt eller diagonalt.
    Som motståndare har man datorn. Men låt dig inte luras av datorns tillsynes avsaknad av logik,
    för plötslig kan den ändra sin strategi från en femårings till en mästares. Lycka till!  */
public class Main {
    public static void main(String[] arg) {

        Scanner scan = new Scanner(System.in);
        boolean isPlaying = true;
        boolean askToPlay;

        /*  Yttre while-loop låter spelaren köra flera gånger utan att behöva kompilera om programmet.
            Inuti loopen skapas ett nytt objekt upp av klassen Game och metoden play anropas.
            play metoden startar spelet och kommer att fortsätta köras tills det att någon har vunnit eller det blivit oavgjort.

            När det händer kommer programmet att hoppa ut till main igen och fortsätta läsa nästa rad i koden som är
            initiering/återställning av variabeln askToPlay som sätts till true.
            Detta görs i den yttre loopen för att möjliggöra att inre loopen körs igen efter den sätts till false inuti den inre loopen.

            I den inre while-loopen frågas spelaren om denne vill spela en gång till.
            Svaren ska ges med bokstäverna "Y" för yes eller "N" för no och kontrolleras inuti en switch-sats.

            Om spelaren vill fortsätta spela sätts askToPlay till false för att avbryta den inre loopen och koden
            fortsätter köra i den yttre loopen som återigen skapar upp ett nytt objekt av klassen Game och kör metoden play
            samt återställer variabeln askToPlay till true så spelaren kan tillfrågas igen efter rundan.

            Om spelaren inte vill fortsätta spela sätts både askToPlay och isPlaying variablerna till false.
            Både den inre och yttre while-loopen kommer att upphöra att köras och då det inte finns någon mer kod efter
            den yttre while-loopen avslutas programmet.

            Om spelaren skriver in något annat än "Y" eller "N" kommer ett felmeddelande skrivas ut.
            Variabeln isPlaying kommer fortsätta vara true och spelaren får ett nytt försök att skriva in sitt svar då
            den inre loopen kommer att köras igen.   */

        while (isPlaying) {

            Game newGame = new Game();
            newGame.play();
            askToPlay = true;

            while (askToPlay) {

                System.out.println("want to play again? Y/N \n");
                String answer = scan.nextLine();

                switch (answer.toUpperCase()) {

                    case "Y": {
                        askToPlay = false;
                        break;
                    }
                    case "N":
                        isPlaying = false;
                        askToPlay = false;
                        break;

                    default: {
                        System.out.println("Invalid answer. Try again");
                    }
                }
            }
        }
    }
}
/*------------RAPPORT---------------------------------------------------------------------------------------------------------------
    Har valt att göra två klasser för att separera spelbrädan och allt det visuella från spellogiken.
    Processen gick till så att jag började skapa GameBoard-klassen med spelbräde och print metod och försökte skriva ut det från main.
    Efter det lyckades skapade jag upp markörer och försökte lägga dom från Game-klassen. När det gick skapade jag upp metod för metod
    i Game-klassen och kollade för varje tillägg jag gjorde att programmet gjorde som jag tänkt mig. Det kollade jag genom att spela/hårdkoda
    data och skriva ut. Men även en del de-bugging. Sen skrev jag while-looparna i main som tillåter att man kör programmet igen.
    Och slutligen skrev jag kommentarerna. Det har tagit otroligt lång tid att skriva klart kommentarerna men jag känner att det va bra att göra
    det sist då jag upptäckte förbättringar som kunde göras i koden när jag skulle sätta ord på vad metoden gjorde och hur dom gjorde det.

    Förbättringar som skulle kunna göras i koden, förutom dom som jag nämnt i kommentarerna i källkoden, är att göra
    en smartare motståndare som kan vara lite mer reaktiv i sina drag. Och man skulle också kunna välja om man ska vara 2
    spelare istället för att spela mot en dator. Man skulle även kunna välja vilken färg man vill spela med. Och såklart
    göra lite snyggare animation skulle vara en klar förbättring. Den skulle kunna öppna upp ett eget fönster med spelbrädan
    där man kan klicka på knappar istället för att skriva in siffror.

    Känner verkligen att jag har lärt mig mer om hur objekt och arv funkar. Och jag har även lärt mig att saker
    är mer komplicerade än vad man först kan tro. Jag trodde att ett fyra-i-rad spel skulle vara busenkelt men det visade sig vara
    ett ganska stort projekt för mig. Det jobbigaste tyckte jag var att förstå arven i klassen och få huvudet runt att
    det är en del av sub-klassen även fast jag inte kan se variablerna eller metoderna. Jag använde t.ex this.board väldigt
    ofta tills jag insåg att det inte behövdes. En annan sak som var svår att acceptera var att mitt spelbräde objekt skapas
    upp även fast jag inte aktivt skapat upp den genom att skriva klassen och new.

    Fortfarande inte helt bekväm med hur det funkar och förhoppningsvis sätter det sig mer under nästa kurs.
    Jag tycket den här uppgiften var jätte kul att göra!

*/