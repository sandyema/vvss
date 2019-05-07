package note.repository;

import note.model.Corigent;
import note.model.Elev;
import note.model.MedieElev;
import note.model.Nota;
import note.main.ClasaException;

import java.util.HashMap;
import java.util.List;

public interface ClasaRepository {
	
	public void creazaClasa(List<Elev> elevi, List<Nota> note);
	public HashMap<Elev, HashMap<String, List<Double>>> getClasa();
	public List<MedieElev> calculeazaMedii() throws ClasaException;
	public void afiseazaClasa();
	public List<Corigent> getCorigenti();
}
