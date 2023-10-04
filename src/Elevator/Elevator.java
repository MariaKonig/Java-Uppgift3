package Elevator;

class Elevator {

    //  Variabeln är privat för att kunna kontrollera indata innan den tilldelas.
    //  Den kan endast ändras via metoder inuti klassen. Defaultvärdet(Startvåning) är satt till 0(BV).
    private int level = 0;

     /* En setter gör felkontroller av våningar innan de tilldelas variabeln level.
        Om våningsplanet är mindre än -2 eller mer än 10 kastar den ett ogiltigt-argument-exception som fångas upp av
        metoder i klassen som anropar den här metoden.
        Metoden är privat då den är tänkt att enbart anropas av instansmetoder inuti klassen.   */
     private void setLevel(int level) {
         if(level >= -2 && level <= 10) {
             this.level = level;
         } else{
             throw new IllegalArgumentException();
            }
     }
    //  Konstruktorer skapar upp hiss-objekt med eller utan in-argument. Om in-argumentet är ogiltigt
    //  fångas det upp och ett felmeddelande skrivs ut. Objektet skapas fortfarande men med default värdet våning 0.
    public Elevator(int level){
         try {
             setLevel(level);
         }catch (IllegalArgumentException e){
             System.out.println("Ogiltig startvåning. Hissen börjar nu på våning 0");
         }
    }
    public Elevator(){}

     /* Metod som förflyttar hiss. Den sparar nuvarande våning för att kunna jämföra om man åker upp eller ner
        i relation med våningen man vill åka till. Om våningen man vill åka till är ogiltigt, skrivs ett felmeddelande ut,
        hissen står kvar på samma våning och metoden avbryts.
        Metoden kollar om nya och gamla våningen är samma, då skrivs ett meddelande ut om detta och metoden avbryts.
        Metoden ger sen hiss-objektet den nya våningen och beroende på om nya våningen är större eller mindre
        än gamla våningen skrivs passande meddelande ut om hissens förflyttning.    */
    public void goTo(int newLevel)throws IllegalArgumentException{

        int oldLevel = level;
        try{
            setLevel(newLevel);
        }catch(IllegalArgumentException e){
            System.out.println("Ogiltig våning. Hissen står still.");
            return;
        }

        if(newLevel == oldLevel){
            System.out.println("Hissen står redan på våning " + this.level);
            return;
        }

        String message = "Hissen åker ";

        if(newLevel>oldLevel){
            message += "upp ";
        }else{
            message += "ner ";
        }

        message += "till våning " + newLevel;
        System.out.println(message);
    }

    //  Metod som returnerar vilken våning hiss-objektet befinner sig på. Jag hade hellre skickat tillbaka en sträng
    //  för att slippa upprepa mig i main men enligt specifikationerna skulle den här metoden returnera en int.
    public int where(){
        return level;
    }
}
