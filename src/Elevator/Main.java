package Elevator;

public class Main {
    public static void main(String[] arg){

        /*  Testar:
            - Konstruktor utan in-argument
            - Defaultvärde av instansvariabeln level
            - goTo-metoden
            - Programmets hantering av ogiltig förflyttning
        */

        Elevator ettan = new Elevator();
        System.out.println("Hissen är på våning " + ettan.where());
        ettan.goTo(5);
        ettan.goTo(-100);
        System.out.println("Hissen är på våning " + ettan.where());

        /*  Testar:
            - Ogiltig in-argument till konstruktor
            - Förflyttning till samma våningsplan
        */

        var tvåan = new Elevator(-3);
        System.out.println("Hissen är på våning " + tvåan.where());
        tvåan.goTo(-2);
        tvåan.goTo(-2);

    }
}
/*
G-uppgiften kändes kul. Det svåraste tyckte jag var att kasta och fånga upp felmeddelanden
eftersom jag ville att programmet skulle rulla på och inte krascha pga felinmatad data.
*/