package note.controller;

import com.sun.java.swing.plaf.motif.MotifEditorPaneUI;
import note.controller.NoteController;
import note.model.Elev;
import note.model.MedieElev;
import note.model.Nota;
import note.main.ClasaException;
import note.utils.Constants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.lang.Float.NaN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MediiTest {

    private NoteController ctrl;

    @Before
    public void init(){
        ctrl = new NoteController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Test
    public void test1() throws ClasaException{
        Elev e1 = new Elev(1, "Maihai");
        Elev e2 = new Elev(2, "George");
        Elev e3 = new Elev(3, "Alex");
        ctrl.addElev(e1);
        ctrl.addElev(e2);
        ctrl.addElev(e3);
        Nota n1 = new Nota(1,"matematica", 10);
        Nota n2 = new Nota(1,"matematica", 7);
        Nota n3 = new Nota(1,"romana", 10);
        Nota n4 = new Nota(1,"romana", 10);
        Nota n5 = new Nota(2,"romana", 4);
        Nota n6 = new Nota(2,"romana", 3);
        Nota n7 = new Nota(2,"romana", 6);
        Nota n8 = new Nota(2,"matematica", 7);
        Nota n9 = new Nota(3,"matematica", 10);
        Nota n10 = new Nota(3,"romana", 8);
        Nota n11 = new Nota(3,"romana", 5);
        Nota n12 = new Nota(3,"matematica", 9);
        try {
            ctrl.addNota(n1);
            ctrl.addNota(n2);
            ctrl.addNota(n3);
            ctrl.addNota(n4);
            ctrl.addNota(n5);
            ctrl.addNota(n6);
            ctrl.addNota(n7);
            ctrl.addNota(n8);
            ctrl.addNota(n9);
            ctrl.addNota(n10);
            ctrl.addNota(n11);
            ctrl.addNota(n12);

        } catch (ClasaException e) {
            throw e;
        }

        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());

        List<MedieElev> rezultate = ctrl.calculeazaMedii();
        assertEquals(rezultate.size(),3);
    }

    @Test
    public void test2() throws ClasaException {
        for (MedieElev medieElev:ctrl.calculeazaMedii()
             ) {
            if(medieElev.equals(null)){
                assertTrue(true);
            }
            else
                assertTrue(false);
        }
    }

    @Test
    public void test3() throws ClasaException {
        Elev e1 = new Elev(1, "Mihai");
        Elev e2 = new Elev(2, "George");
        ctrl.addElev(e1);
        ctrl.addElev(e2);
        Nota n1 = new Nota(1,"matematica", 10);
        ctrl.addNota(n1);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<MedieElev> rezultate = ctrl.calculeazaMedii();
        for(MedieElev m : rezultate)
            if(m.getElev().getNrmatricol() == 2)
                assertEquals(m.getMedie(),NaN,0.0001);
    }

    @Test
    public void test4() throws ClasaException {
        Elev e1 = new Elev(1, "Mihai");
        Elev e2 = new Elev(2, "George");
        ctrl.addElev(e1);
        ctrl.addElev(e2);
//		Nota n1 = new Nota(1,"Materie1", 10);
//
//		ctrl.addNota(n1);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<MedieElev> rezultate = ctrl.calculeazaMedii();
        for(MedieElev m : rezultate)
            if(m.getElev().getNrmatricol() == 1)
                assertEquals(m.getMedie(),NaN,0.0001);
        for(MedieElev m1 : rezultate)
            if(m1.getElev().getNrmatricol() == 2)
                assertEquals(m1.getMedie(),NaN,0.0001);
    }

    @Test
    public void test5() throws ClasaException{
        Elev e1 = new Elev(1, "Maihai");
        Elev e2 = new Elev(2, "George");
        Elev e3 = new Elev(3, "Alex");
        ctrl.addElev(e1);
        ctrl.addElev(e2);
        ctrl.addElev(e3);
        Nota n1 = new Nota(1,"matematica", 10);
        Nota n2 = new Nota(2,"matematica", 7);
        Nota n3 = new Nota(2,"romana", 10);

        try {
            ctrl.addNota(n1);
            ctrl.addNota(n2);
            ctrl.addNota(n3);

        } catch (ClasaException e) {
            throw e;
        }

        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());

        List<MedieElev> rezultate = ctrl.calculeazaMedii();
        assertEquals(rezultate.size(),3);
    }
}