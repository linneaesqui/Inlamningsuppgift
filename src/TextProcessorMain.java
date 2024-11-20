import java.util.Scanner;

public class TextProcessorMain {

    public static void main(String[] args) {

        TextProcessor myProcessor = new TextProcessor();
        Scanner scan = new Scanner(System.in);
        boolean keepGoing = true;

        System.out.println("Skriv in valfritt antal rader med löpande text. " +
                "För att avsluta programmet, skriv stop. \nObs att stop ska skrivas på en egen rad!");
        while (keepGoing) { //Jag skapar en while-loop för att läsa in och bearbeta rad för rad.
            myProcessor.processText(scan.nextLine()); //varje rad som läses in, bearbetas i vår processText-metod.
            if (myProcessor.ifStop()) { //Om ifStop returnerar true...
                keepGoing = false; //...sätts keepGoing till false och vi avslutar vår loop.
            }
        }
        //här nedan hämtar vi och skriver ut våra lagrade värden med hjälp av våra get-metoder.
        System.out.println("Antal rader: " + myProcessor.getRowCount());
        System.out.println("Antal tecken: " + myProcessor.getCharCount());
        System.out.println("Antal ord: " + myProcessor.getWordCount());
        System.out.println("Längsta ordet: " + myProcessor.getLongestWord());

    }
}
