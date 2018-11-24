package Test;

import model.ParcAttraction;
import model.Tarif;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ParcAttractionTest {

    @Test(expected = NullPointerException.class)
    public final void nullArg() {
        ParcAttraction p = new ParcAttraction(null, 40);

    }


    @Test
    public final void sellingAssociation() {
        ParcAttraction p = new ParcAttraction("Miage", 40);
        p.init();
        p.vendreBillets(5,Tarif.REDILL);
        assertEquals(5,p.getSaled().get(Tarif.REDILL).intValue());
    }

    @Test
    public final void nbPlaceDispoTest() {
        ParcAttraction p = new ParcAttraction("Miage", 40);
        p.init();
        p.vendreBillets(5,Tarif.REDILL);
        assertEquals(35,p.nbPlacesDispo());
    }

    @Test
    public final void chiffreaffaireTest() {
        ParcAttraction p = new ParcAttraction("Miage", 40);
        p.init();
        p.vendreBillets(5,Tarif.REDILL5);
        p.vendreBillets(15,Tarif.REDILL);
        p.vendreBillets(2,Tarif.INDJRILL);
        assertTrue(581.0==p.chiffreAffaires());
        }
}
