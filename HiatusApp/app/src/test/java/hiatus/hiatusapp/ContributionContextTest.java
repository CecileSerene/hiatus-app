package hiatus.hiatusapp;

import org.junit.Test;

import hiatus.hiatusapp.ContributionContext.ContributionContext;
import hiatus.hiatusapp.ContributionContext.ContributionText;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ContributionContextTest {
    @Test
    public void textConstructor(){
        String instructions = "Ecrivez les premiers mots qui vous viennent sur le thème du jeu";
        ContributionText text = new ContributionText(instructions,"Jeu",50);

        assertEquals(text.getInstructions(),instructions);
        assertEquals(text.getNb_of_characters(),50);
        assertEquals(text.isModifications_allowed(),true);
    }
}