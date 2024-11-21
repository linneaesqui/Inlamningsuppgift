import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextProcessorTest {


    @Test
    void testOnlySpace() {
//        testar så att en rad med bara blanksteg inte körs.
//        Detta testet failade först pga att jag satt kontrollen av tomma rader i main-klassen. Fick tänka om igen och
//        förbättra/vara konsekvent med var jag la logiken.
        TextProcessor processor = new TextProcessor();
        processor.processText("       ");
        assertEquals(0, processor.getWordCount());
        assertEquals(0, processor.getCharCount());
    }

    @Test
    void testEmptyRow() {
//        testar så att tomma rader inte räknas.
        TextProcessor processor = new TextProcessor();
        processor.processText("\n");
        assertEquals(0, processor.getRowCount());
        assertEquals(0, processor.getCharCount());
    }

    @Test
    void testLongestWord() {
//        testar så att replaceAll() för specialtecken fungerar, testar samtidigt logiken för getLongestWord.
        TextProcessor processor = new TextProcessor();
        processor.processText("jag tycker om pitbulls!");
        assertEquals("pitbulls", processor.getLongestWord());
    }

    @Test
        void testRemoveSpaceInCharCount() {
//        testar så att mellanslagen inte räknas i teckenräknaren
        TextProcessor processor = new TextProcessor();
        processor.processText("Testar om den tar bort mellanslag");
        assertEquals(28, processor.getCharCount());
    }

    @Test
       void testNotStop() {
//        testar så att boolean-metoden verkligen returnerar ett falskt värde om stop inte skrivs på egen rad
        TextProcessor processor = new TextProcessor();
        processor.processText("Hej, jag heter Linnea stop");
        assertFalse(processor.hasStopped()); //Tydligare metod för att kolla boolska värden än assertEquals()
    }

    @Test
    void testStopAndTrim() {
//        testar så programmet verkligen inte körs om användaren skriver stop direkt, testar samtidigt trim().
        TextProcessor processor = new TextProcessor();
        processor.processText(" stop ");
        assertEquals(0, processor.getWordCount());
        assertEquals(0, processor.getRowCount());
        assertEquals(0, processor.getCharCount());
        assertEquals("", processor.getLongestWord());
        assertTrue(processor.hasStopped());
    }

    @Test
    void testHasStoppedIgnoresUpperCase() {
//        testar så att ignoreUpperCase() fungerar för hasStopped().
        TextProcessor processor = new TextProcessor();
        processor.processText("STOP");
        assertTrue(processor.hasStopped());
    }

    @Test
    void testForeignLetters() {
//        testar en förnyad version av replaceAll, där även utländska bokstäver ska räknas
        TextProcessor processor = new TextProcessor();
        processor.processText("Linnéa Muños Crème Øl");
        assertEquals(18, processor.getCharCount());
    }

    @Test
    void testRemoveHyphenFromLongestWord() {
//        testar så att longestWord räknar bort bindestreck
        TextProcessor processor = new TextProcessor();
        processor.processText("Här är min e-post, skriv till mig senare");
        assertEquals("senare", processor.getLongestWord());
    }

    @Test
    void testWordWithHyphen() {
//        testar så att bindestrecket håller ihop orden och sparas i longestWord
        TextProcessor processor = new TextProcessor();
        processor.processText("Håller binde-streck ihop långorden?");
        assertEquals("binde-streck", processor.getLongestWord());
    }

    @Test
    void testOnlySpecialChar() {
//        testar så att specialtecken räknas som tecken, men inte som ord.
//        Detta testet failade från början pga en felplacering av både wordCount och replaceAll-metoden. Så jag fick tänka om.
//        Den gav tillbaka Actual: 1 för wordCount. Jag flyttade då replaceAll till innan spliten.
//        Och satte wordCount++ i for each loopen, tillsammans med ett villkor om att ordet inte får vara blankt.
        TextProcessor processor = new TextProcessor();
        processor.processText("!!!!!!!!!!!!");
        assertEquals(0, processor.getWordCount());
        assertEquals(12, processor.getCharCount());
    }
}


