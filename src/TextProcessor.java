//Jag har lagt ner mycket tid på att tolka och testa. Testa olika gränsvärden och se vad som händer. Kraven var ganska
//diffusa och jag valde att försöka göra ett program jag kunde känna mig nöjd med.
//Saker jag lagt till/tolkat:
//Jag vill inte räkna med tomma rader och rader med endast mellanslag.
//Jag vill att raden med stop ska räknas oavsett mellanslag och bokstavsstorlek.
//Jag valde att bara ta med det första ordet i longestWord om det finns två ord som är lika långa.
//Jag valde att inte räkna med mellanslagen i charCount.
//Jag vill inte räkna med specialtecken i ordlängden, däremot vill jag spara ord med bindestreck i som ett ord.
//Dock ska bindestrecket inte räknas med i ordlängden - e-post ska vara ett ord, med längden 5.
//Jag vill dock räkna med utländska bokstäver i ordlängden.
//Jag vill inte räkna "ord" med endast specialtecken i wordCount.
//Jag har funderat på huruvida jag borde ha kvar vissa tecken inuti ord när jag beräknar ordlängd, exempelvis en epost-adress.
//Jag bestämde mig för att om jag hade jobbat på det här systemet så är det något jag skulle gå till en kollega och fråga.
//Beroende på hur vi skulle välja att tolka kraven, så hade jag eventuellt fått ändra på replaceAll. Jag har hittat lösningar,
//men kände att det började bli lite väl komplext och långsökt.

public class TextProcessor {
//    Här nedan radas alla egenskaper objektet ska ha upp. Eftersom de inte ska ändras från utanför klassen,
//    samt att alla objekt ska ha samma defaultvärden när de skapas, och konstruktorn inte ska läsa in några parametrar,
//    initieras instansvariablerna direkt som private. Ingen konstruktor behövs.
    private int rowCount = 0;
    private int charCount = 0;
    private int wordCount = 0;
    private String longestWord = "";

//    boolean isStop som kan returneras till main-metoden och tala om när programmet ska sluta.
    private boolean isStop = false;

    public void processText(String textRow) {
        if (textRow.isBlank()) //metoden processText ska inte bearbeta tomma rader och rader helt utan tecken.
            return;
        if (textRow.trim().equalsIgnoreCase("stop")) { //kontrollerar om användaren skrivit stop enligt anvisningarna
            isStop = true; //i så fall sätts isStop till true
            return; //och metoden avslutas
        }

        rowCount++;
//        Hjälpmetoden removeSpace, som returnerar en String utan mellanslag, anropas.
        charCount += removeSpace(textRow);

//        Hjälpmetoden removeSpecialChar, som tar bort specialtecken, delar upp en String vid varje mellanslag,
//        och returnerar en StringArray med orden i, anropas.
        String[] wordArray = removeSpecialChar(textRow);

//        Loop för att gå igenom alla ord i wordArray.
//        Kom ihåg for each från programmering 1, och jag tycker den är i särklass bäst för att gå
//        igenom och jämföra alla värden sparade i en Array.
        for  (String word : wordArray) {
//            En konsekvens av att ta bort specialtecknen innan blev att två mellanslag ibland hamnar bredvid varandra,
//            och då skapas en split med en tom String i wordArray. Logiken nedan ser till att dessa inte räknas.
//            Eftersom bindestreck är kvar, tas även "ord" innehållande endast bindestreck bort innan wordCount.
            if (!word.isBlank() && !word.matches("-+")) {
                wordCount++;
//                Hjälpmetoden removeHyphen, som returnerar en String utan bindestreck, anropas för korrekt jämförelse av
//                ordlängd.
                if (removeHyphen(longestWord).length() < removeHyphen(word).length()) {
                longestWord = word;
                }
            }
        }
    }
//    Nedan är alla remove-metoder som skapats för att hålla koden renare och mer lätthanterlig.
    public int removeSpace(String text) {
        return text.replaceAll(" ", "").length();
    }

    public String []removeSpecialChar(String text) {
        return text.replaceAll("[^\\p{L}0-9- ]", "").split(" ");
    }

    public String removeHyphen(String text) {
        return text.replaceAll("-", "");
    }

//    hasStopped() är till för att kontrollera om stop skrivits in från main-metoden.
//    Den returnerar värdet på isStop. Skrivs stop in, sätts isStop till true.
    public boolean hasStopped() {
        return isStop;
    }
//    Nedan är alla get-metoder som ska kunna returnera värdena för instansvariablerna.
    public int getWordCount() {
        return wordCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getCharCount() {
        return charCount;
    }

    public String getLongestWord() {
        return longestWord;
    }
}