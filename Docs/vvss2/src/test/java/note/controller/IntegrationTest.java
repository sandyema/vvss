package note.controller;

import note.controller.NoteController;
import note.model.Corigent;
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

public class IntegrationTest {

	NoteController ctrl;

	@Before
	public void setUp() throws Exception {
		ctrl = new NoteController();
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void test1() throws ClasaException {
		//P->A
		Nota nota = new Nota(1000, "Desena", 10);

		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
	}

	@Test
	public void test2() throws ClasaException {
		//P->B
		Elev e1 = new Elev(1, "Elev1");
		Elev e2 = new Elev(2, "Elev2");
		ctrl.addElev(e1);
		ctrl.addElev(e2);
		Nota n1 = new Nota(1,"Materie1", 10);
		ctrl.addNota(n1);
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<MedieElev> rezultate = ctrl.calculeazaMedii();
		for(MedieElev m : rezultate)
			if(m.getElev().getNrmatricol() == 2)
                assertEquals(m.getMedie(),NaN,0.0001);
	}

	@Test
	public void test3() throws ClasaException {
		//P->C
		Elev e1 = new Elev(1, "Elev1");
		Elev e2 = new Elev(2, "Elev2");
		ctrl.addElev(e1);
		ctrl.addElev(e2);
		Nota n1 = new Nota(1,"Materie1", 1);
		Nota n2 = new Nota(1,"Materie1", 7);
		Nota n3 = new Nota(1,"Materie2", 10);
		Nota n4 = new Nota(1,"Materie2", 10);
		Nota n5 = new Nota(2,"Materie2", 4);
		Nota n6 = new Nota(2,"Materie2", 5);
		Nota n7 = new Nota(2,"Materie2", 3);
		Nota n8 = new Nota(2,"Materie1", 4);
		ctrl.addNota(n1);
		ctrl.addNota(n2);
		ctrl.addNota(n3);
		ctrl.addNota(n4);
		ctrl.addNota(n5);
		ctrl.addNota(n6);
		ctrl.addNota(n7);
		ctrl.addNota(n8);
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Corigent> corigenti = ctrl.getCorigenti();
        if(corigenti.size()==2)
            assertTrue(true);
        else
            assertTrue(false);	}

	@Test
	public void test4() throws ClasaException {
		//P->B->A A-valid B-valid
		Elev e1 = new Elev(1, "Elev1");
		ctrl.addElev(e1);
		Nota nota = new Nota(1, "Desena", 10);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<MedieElev> rezultate = ctrl.calculeazaMedii();
		assertEquals(1, rezultate.size());
	}

	@Test
	public void test5() throws ClasaException {
		//P->B->A A-invalid B-valid
		Elev e1 = new Elev(1, "Elev1");
		ctrl.addElev(e1);
		try {
            Nota nota = new Nota(1, "geo", 10);
            ctrl.addNota(nota);
            assertTrue(false);
        }
        catch (ClasaException ex){
		    assertTrue(true);
        }
        Nota nota1 = new Nota(1, "Istorie", 5);
        ctrl.addNota(nota1);


        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<MedieElev> rezultate = ctrl.calculeazaMedii();
		assertEquals(1, rezultate.size());


	}

	@Test
	public void test6() throws ClasaException {
		//P->B->A A-valid B-invalid
        for (MedieElev medieElev:ctrl.calculeazaMedii()
                ) {
            if(medieElev.equals(null)){
                assertTrue(true);
            }
            else
                assertTrue(false);
        }
		Nota nota = new Nota(1000, "Desena", 10);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
	}

	@Test
	public void test7() throws ClasaException {
		//P->B->A->C B-valid A-valid C-valid
		Elev e1 = new Elev(1, "Elev1");
		ctrl.addElev(e1);
		Nota nota = new Nota(1, "Desena", 10);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<MedieElev> rezultate = ctrl.calculeazaMedii();
		assertEquals(1, rezultate.size());
		List<Corigent> corigenti = ctrl.getCorigenti();
		assertEquals(corigenti.size(),0);

	}

	@Test
	public void test8() throws ClasaException {
		//P->B->A->C B-invalid A-valid C-valid
        for (MedieElev medieElev:ctrl.calculeazaMedii()
                ) {
            if(medieElev.equals(null)){
                assertTrue(true);
            }
            else
                assertTrue(false);
        }

		Elev e1 = new Elev(1, "Elev1");
		ctrl.addElev(e1);
		Nota nota = new Nota(1, "Desena", 10);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Corigent> corigenti = ctrl.getCorigenti();
		assertEquals(corigenti.size(),0);
	}

	@Test
	public void test9() throws ClasaException {
		//P->B->A->C B-valid A-invalid C-valid
		Elev e1 = new Elev(1, "Elev1");
		ctrl.addElev(e1);
        try {
            Nota nota = new Nota(1, "geo", 10);
            ctrl.addNota(nota);
            assertTrue(false);
        }
        catch (ClasaException ex){
            assertTrue(true);
        }

        Nota nota1 = new Nota(1, "Istorie", 4);
        ctrl.addNota(nota1);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());


        List<MedieElev> rezultate = ctrl.calculeazaMedii();
        assertEquals(1, rezultate.size());


		List<Corigent> corigenti = ctrl.getCorigenti();
		assertEquals(1,corigenti.size());
    }

}
