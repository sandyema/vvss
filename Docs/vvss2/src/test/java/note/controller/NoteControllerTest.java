package note.controller;

import note.model.Nota;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteControllerTest {
    NoteController controller;
    @Before
    public void setUp() throws Exception {
        controller=new NoteController();
    }

    @Test
    public void addNota_ECP_valid() throws Exception {

        try{
            controller.addNota(new Nota(4,"matematica",8));
            assertTrue(true);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }

    @Test
    public void addNota_ECP_nonvalid1() throws Exception {

        try{
            controller.addNota(new Nota(-4,"matematica",5));
            assertTrue(false);
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void addNota_ECP_nonvalid2() throws Exception {

        try{
            controller.addNota(new Nota(3,"sport",12));
            assertTrue(false);
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void addNota_BVA_nonvalid1() throws Exception {

        try{
            controller.addNota(new Nota(0,"matematica",7));
            assertTrue(false);
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void addNota_BVA_valid1() throws Exception {

        try{
            controller.addNota(new Nota(1,"sport",5));
            assertTrue(true);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }


    @Test

    public void addNota_BVA_valid2() throws Exception {

        try{
            controller.addNota(new Nota(2,"romana",6));
            assertTrue(true);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }


    @Test
    public void addNota_BVA_nonvalid2() throws Exception {

        try{
            controller.addNota(new Nota(3,"matematica",0));
            assertTrue(false);
        }
        catch (Exception e){
            assertTrue(true);
        }
    }
}