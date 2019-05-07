package note.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import note.model.Corigent;
import note.model.MedieElev;

import note.controller.NoteController;
import note.model.Nota;

//functionalitati
//F01.	 adaugarea unei note la o anumita materie (nr. matricol, materie, nota acordata);
//F02.	 calcularea mediilor semestriale pentru fiecare elev (nume, nr. matricol),
//F03.	 afisarea elevilor coringenti, ordonati descrescator dupa numarul de materii la care nu au promovat ?i alfabetic dupa nume.


public class StartApp {

	/**
	 * @param args
	 * @throws ClasaException 
	 */
	public static void main(String[] args) throws note.main.ClasaException {
		// TODO Auto-generated method stub
		NoteController ctrl = new NoteController();
		List<MedieElev> medii = new LinkedList<MedieElev>();
		List<Corigent> corigenti = new ArrayList<Corigent>();
//		ctrl.readElevi(args[0]);
		ctrl.readElevi("src/elevi.txt");
//		ctrl.readNote(args[1]);
		ctrl.readNote("src/note.txt");
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		boolean gasit = false;
		while(!gasit) {
			System.out.println("1. Adaugare Nota");
			System.out.println("2. Calculeaza medii");
			System.out.println("3. Elevi corigenti");
			System.out.println("4. Afiseaza elevi");
			System.out.println("5. Iesire");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
		    try {
				int option = Integer.parseInt(br.readLine());
				switch(option) {
				case 1:

					try {
						System.out.println("Numar matricol\n");
						double nrmatricol=Double.parseDouble(br.readLine());
						System.out.println("Materie\n");
						String materie= br.readLine();
						System.out.println("Nota\n");
						double nota=Double.parseDouble(br.readLine());
						Nota notaElev=new Nota(nrmatricol,materie,nota);

						ctrl.addNota(notaElev);
//					for(Nota notaa: ctrl.getNote())
//						System.out.println(notaa);
////						break;
//					ctrl.afiseazaClasa();
						System.out.println("Nota adaugata cu succes");
					}catch (ClasaException e) {
						System.out.println(e);
					}
					break;
				case 2: medii = ctrl.calculeazaMedii();
						for(MedieElev medie:medii)
							System.out.println(medie);
						break;
				case 3: corigenti = ctrl.getCorigenti();
						for(Corigent corigent:corigenti)
							System.out.println(corigent);
						break;
					case 4:
						ctrl.afiseazaClasa();
						break;
				case 5: gasit = true;
						break;
				default: System.out.println("Introduceti o optiune valida!");
				}
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
