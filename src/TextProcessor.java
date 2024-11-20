public class TextProcessor {
//    Här nedan radar jag upp alla egenskaper jag vill att mina objekt ska ha. Eftersom vi inte vill ändra dem utanför klassen,
//    samt att alla objekt ska ha samma defaultvärden när de skapas, och konstruktorn inte ska läsa in några parametrar,
//    valde jag att initiera instansvariablerna direkt som private. Ingen konstruktor alltså.
    private int rowCount = 0;
    private int charCount = 0;
    private int wordCount = 0;
    private String longestWord = "";

//    Efter många om och men så insåg jag till slut att jag skulle lägga till en boolean,
//    så jag kan returnera true eller false till min main-metod, så att den fattar när den ska sluta.
    private boolean isStop = false;

    public void processText(String textRow) {
        if (textRow.isBlank()) //Jag vill inte bearbeta tomma rader och rader helt utan tecken.
            return;
        if (textRow.equalsIgnoreCase("stop")) { //kontrollerar om användaren skrivit stop enligt anvisningarna
            isStop = true; //i så fall sätts vår boolean isStop till true
            return; //och vi avslutar metoden
        }

        rowCount++;
        charCount += textRow.length();

//        Eftersom det ofta ingår olika skiljetecken i en löpande text, fick jag gräva lite efter en enkel metod för
//        att ta bort dessa.
//        Jag tycker inte att det blev snyggt när det längsta ordet hade ett kommatecken vid sig, och det skulle
//        också kunna bli fel, eftersom att ett ord blir ett tecken längre med ett skiljetecken. Jag fann denhär som
//        hade samma princip (regex) som split-metoden du lärde oss, och tyckte det var passande. Genom placeringen i koden
//        tar jag även bort skiljetecken som står för sig, så de inte kommer med i wordCount. Det enda jag inte
//        lyckats lösa är hur jag ska kunna urskilja bindestreck inuti ett ord från bindestreck intill ett ord.
        String noSpecialChar = textRow.replaceAll("[^a-zA-ZåäöÅÄÖ0-9- ]", "");

//        Jag delar upp min textrad vid varje blanksteg och lagrar orden var för sig i en string-array.
        String[] wordArray = (noSpecialChar.split(" "));

//        Jag har valt en for each-loop för att gå igenom alla ord i min wordArray.
//        Detta är en loop som jag minns från programmering 1, och jag tycker den är i särklass bäst för att gå
//        igenom och jämföra alla värden sparade i en Array.
        for  (String word : wordArray) {
//            En konsekvens av att jag tog bort specialtecknen innan blev att två mellanslag ibland hamnar bredvid varandra,
//            och då skapas en split med ett tomt index i min Array. Jag räknar nedan endast ord som inte är "blank".
            if (!word.isBlank()) {
                wordCount++;
                if (longestWord.length() < word.length()) {
                longestWord = word;
                }
            }
        }
    }
//    Min boolean-metod är till för att vi ska kunna kontrollera om stop skrivits in från main-metoden.
//    Den returnerar värdet på min boolean isStop. Skrivs stop in, sätts isStop till true och vi returnerar
//    detta till main-metoden, där vi genom en if-sats kontrollerar värdet och eventuellt avslutar while-loopen.
    public boolean ifStop() {
        return isStop;
    }
//    Nedan är alla get-metoder som ska kunna returnera värdena för våra egenskaper/variabler.
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