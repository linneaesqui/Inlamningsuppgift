import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextProcessorTest {

    @Test
    void testRowCount() {
//        Jag testar metoden getRowCount för en rad, samtidigt som jag ser så att mina tomma rader inte räknas.
        TextProcessor processor = new TextProcessor();
        processor.processText("\n\n\nDethär gick ju ganska bra!");
        assertEquals(1, processor.getRowCount());
    }

    @Test
    void testLongestWord() {
//        Här vill jag testa så att min funktion för att ta bort specialtecken fungerar,
//        samtidigt som jag vill testa min metod getLongestWord.
        TextProcessor processor = new TextProcessor();
        processor.processText("jag tycker om pitbulls!");
        assertEquals("pitbulls", processor.getLongestWord());

    }

    @Test
       void testStopAtSameRow() {
//        Här vill jag testa så att min boolean-metod verkligen returnerar ett
//        falskt värde om jag inte skriver stop på egen rad
        TextProcessor processor = new TextProcessor();
        processor.processText("Hej, jag heter Linnea stop");
        assertFalse(processor.ifStop()); //Tydligare metod för att kolla boolska värden än assertEquals()
    }

    @Test
    void testOnlyStop() {
//        Här vill jag se så programmet verkligen inte körs om användaren skriver stop direkt
        TextProcessor processor = new TextProcessor();
        processor.processText("stop");
        assertEquals(0, processor.getWordCount());
        assertEquals(0, processor.getRowCount());
        assertEquals(0, processor.getCharCount());
        assertEquals("", processor.getLongestWord());
        assertTrue(processor.ifStop());
    }

    @Test
    void testStopIgnoresUpperCase() {
        TextProcessor processor = new TextProcessor();
        processor.processText("STOP");
        assertTrue(processor.ifStop());
    }

    @Test
    void testOnlySpecialChar() {
//        Jag vill testa så att specialtecken räknas som tecken, men inte som ord.
//        Detta testet failade från början pga en felplacering av både wordCount och min replaceAll-metod. Så jag fick tänka om.
//        Den gav tillbaka Actual: 1 för wordCount. Jag flyttade då min replaceAll till innan min split.
//        Och satte wordCount++ i min for each loop, tillsammans med ett villkor om att ordet inte får vara blankt.
        TextProcessor processor = new TextProcessor();
        processor.processText("!!!!!!!!!!!!");
        assertEquals(0, processor.getWordCount());
        assertEquals(12, processor.getCharCount());
    }
    @Test
    void testOnlySpace() {
//        Detta testet failade också pga att jag placerat kontrollen av tomma rader i min main-klass.
//        Fick tänka om igen och förbättra/vara konsekvent med var jag la logiken. Det var sagt att mellanslag skulle
//        räknas, men jag tyckte inte det kändes användarvänligt att räkna tomma rader och bara mellanslag.
        TextProcessor processor = new TextProcessor();
        processor.processText("       ");
        assertEquals(0, processor.getWordCount());
        assertEquals(0, processor.getCharCount());
    }
}

//Jag har lagt ner mycket tid på att tolka och testa. Testa olika gränsvärden och se vad som händer. Kraven var ganska
//diffusa och jag valde att försöka göra ett program jag kunde känna mig nöjd med.
//Saker jag lagt till/tolkat:
//Jag valde att bara ta med det första ordet i longestWord om det finns två ord som är lika långa.
//Jag vill inte räkna med specialtecken (förutom bindestreck) i ordlängden.
//Jag vill inte räkna med tomma rader och rader med endast mellanslag
//Jag vill inte räkna "ord" med endast specialtecken som ord i wordCount
//Jag har funderat på huruvida jag borde ha kvar vissa tecken inuti ord när jag beräknar ordlängd, exempelvis en epost-adress.
//Jag bestämde mig för att om jag hade jobbat på det här systemet så är det något jag skulle gå till en kollega och fråga.
//Beroende på hur vi skulle välja att tolka kraven, så hade jag eventuellt fått ändra på replaceAll. Jag har hittat lösningar,
//men kände att det började bli lite väl komplext och långsökt.
