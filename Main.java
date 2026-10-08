import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Elev> listaElevi = new ArrayList<>();
    private static List<Sala> listaSali = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int optiune = -1;

        while (optiune != 18 && optiune != 0) {
            afiseazaMeniu();
            System.out.print("Alege o optiune: ");

            try {
               optiune = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Eroare: Introdu un numar!");
                continue;
            }


            switch (optiune) {
                case 1: adaugaElev(); break;
                case 2: creeazaSala(); break;
                case 3: adaugaElevInSala(); break;
                case 4: afiseazaTotiElevii(); break;
                case 5: afiseazaToateSalile(); break;
                case 6: afiseazaEleviiDintrOSala(); break;
                case 7: cautaElevDupaId(); break;
                case 8: mutaElev(); break;
                case 9: stergeElev(); break;
                case 10: stergeSala(); break;
                case 11: filtreazaPromovati(); break;
                case 12: filtreazaNepromovati(); break;
                case 13: filtreazaDupaMedie(); break;
                case 14: filtreazaDupaVarsta(); break;
                case 15: cautaElevDupaNume(); break;
                case 16: afiseazaStatistici(); break;
                case 17: sorteazaElevii(); break;
                case 18:
                case 0:
                    System.out.println("Aplicatia s-a inchis.");
                    break;
                default:
                    System.out.println("Eroare: Optiune inexistenta! Alege din nou.");
            }
            System.out.println();
        }
    }

    private static void afiseazaMeniu() {
        System.out.println("1. Adauga elev");
        System.out.println("2. Creeaza sala");
        System.out.println("3. Adauga elev intr-o sala");
        System.out.println("4. Afiseaza toti elevii");
        System.out.println("5. Afiseaza toate salile");
        System.out.println("6. Afiseaza elevii unei sali");
        System.out.println("7. Cauta elev dupa ID");
        System.out.println("8. Muta elev dintr-o sala in alta");
        System.out.println("9. Sterge elev");
        System.out.println("10. Sterge sala");
        System.out.println("11. Filtreaza elevii promovati");
        System.out.println("12. Filtreaza elevii nepromovati");
        System.out.println("13. Filtreaza elevii dupa interval de medie");
        System.out.println("14. Filtreaza elevii dupa varsta");
        System.out.println("15. Cauta elevi dupa nume");
        System.out.println("16. Afiseaza statistici");
        System.out.println("17. Sorteaza elevii");
        System.out.println("18. Inchide aplicatia (sau 0)");

    }


    private static void adaugaElev() {
        System.out.print("ID elev: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Varsta: ");
        int varsta = Integer.parseInt(scanner.nextLine());
        System.out.print("Medie: ");
        double medie = Double.parseDouble(scanner.nextLine());

        Elev elev = new Elev(id, nume, varsta, medie);
        listaElevi.add(elev);
        System.out.println("Elev adaugat cu succes!");
    }


    private static void creeazaSala() {
        System.out.print("ID sala: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Denumire sala: ");
        String denumire = scanner.nextLine();
        System.out.print("Capacitate: ");
        int capacitate = Integer.parseInt(scanner.nextLine());

        Sala sala = new Sala(id, denumire, capacitate);
        listaSali.add(sala);
        System.out.println("Sala creata cu succes!");
    }

        private static void adaugaElevInSala() {
        System.out.print("ID elev: ");
        int idElev = Integer.parseInt(scanner.nextLine());
        System.out.print("ID sala: ");
        int idSala = Integer.parseInt(scanner.nextLine());

        Elev e = gasesteElevDupaId(idElev);
        Sala s = gasesteSalaDupaId(idSala);

        if (e != null && s != null) {
            if (s.adaugaElev(e)) {
                System.out.println("Elev adaugat in sala!");
            }
        } else {
            System.out.println("Eroare: Elevul sau sala nu exista!");
        }
    }


    private static void afiseazaTotiElevii() {
        if (listaElevi.isEmpty()) {
            System.out.println("Nu exista elevi.");
            return;
        }
        for (Elev e : listaElevi) {
            System.out.println(e);
        }
    }

        private static void afiseazaToateSalile() {
        if (listaSali.isEmpty()) {
            System.out.println("Nu exista sali.");
            return;
        }
        for (Sala s : listaSali) {
            System.out.println(s);
        }
    }
    private static void afiseazaEleviiDintrOSala() {
        System.out.print("ID sala: ");
        int idSala = Integer.parseInt(scanner.nextLine());
        Sala s = gasesteSalaDupaId(idSala);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Sala nu a fost gasita!");
        }
    }

    private static void cautaElevDupaId() {
        System.out.print("ID elev: ");
        int id = Integer.parseInt(scanner.nextLine());
        Elev e = gasesteElevDupaId(id);
        if (e != null) {
            System.out.println("Elev gasit: " + e);
        } else {
            System.out.println("Elevul nu exista.");
        }
    }

    private static void mutaElev() {
        System.out.print("ID elev: ");
        int idElev = Integer.parseInt(scanner.nextLine());
        System.out.print("ID sala sursa: ");
        int idSursa = Integer.parseInt(scanner.nextLine());
        System.out.print("ID sala destinatie: ");
        int idDestinatie = Integer.parseInt(scanner.nextLine());

        Sala sursa = gasesteSalaDupaId(idSursa);
        Sala destinatie = gasesteSalaDupaId(idDestinatie);

        if (sursa != null && destinatie != null) {
            Elev e = gasesteElevDupaId(idElev);
            if (e != null && sursa.stergeElev(idElev)) {
                destinatie.adaugaElev(e);
                System.out.println("Elev mutat cu succes!");
            } else {
                System.out.println("Elevul nu se afla in sala sursa.");
            }
        } else {
            System.out.println("Eroare: Sali invalide.");
        }
    }


    private static void stergeElev() {
        System.out.print("ID elev: ");
        int id = Integer.parseInt(scanner.nextLine());
        Elev e = gasesteElevDupaId(id);
        if (e != null) {
            listaElevi.remove(e);
            for (Sala s : listaSali) {
                s.stergeElev(id);
            }
            System.out.println("Elev sters!");
        } else {
            System.out.println("Elevul nu exista.");
        }
    }
    private static void stergeSala() {
        System.out.print("ID sala: ");
        int id = Integer.parseInt(scanner.nextLine());
        Sala s = gasesteSalaDupaId(id);
        if (s != null) {
            listaSali.remove(s);
            System.out.println("Sala stearsa!");
        } else {
            System.out.println("Sala nu exista.");
        }
    }
    private static void filtreazaPromovati() {
        for (Elev e : listaElevi) {
            if (e.estePromovat()) {
                System.out.println(e);
            }
        }
    }

    private static void filtreazaNepromovati() {
        for (Elev e : listaElevi) {
            if (!e.estePromovat()) {
                System.out.println(e);
            }
        }
    }
    private static void filtreazaDupaMedie() {
        System.out.print("Medie minima: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Medie maxima: ");
        double max = Double.parseDouble(scanner.nextLine());

        for (Elev e : listaElevi) {
            if (e.getMedie() >= min && e.getMedie() <= max) {
                System.out.println(e);
            }
        }
    }

    private static void filtreazaDupaVarsta() {
        System.out.print("Varsta cautata: ");
        int v = Integer.parseInt(scanner.nextLine());
        for (Elev e : listaElevi) {
            if (e.getVarsta() == v) {
                System.out.println(e);
            }
        }
    }


    private static void cautaElevDupaNume() {
        System.out.print("Nume cautat: ");
        String text = scanner.nextLine().toLowerCase();
        for (Elev e : listaElevi) {
            if (e.getNume().toLowerCase().contains(text)) {
                System.out.println(e);
            }
        }
    }

    private static void afiseazaStatistici() {
        if (listaElevi.isEmpty()) {
            System.out.println("Nu exista date.");
            return;
        }
        double suma = 0;
        int promovati = 0;

        for (Elev e : listaElevi) {
            suma += e.getMedie();
            if (e.estePromovat()) promovati++;
        }

        System.out.println("Total elevi: " + listaElevi.size());
        System.out.println("Media generala: " + (suma / listaElevi.size()));
        System.out.println("Elevi promovati: " + promovati);
        System.out.println("Elevi nepromovati: " + (listaElevi.size() - promovati));
    }

    private static void sorteazaElevii() {
        System.out.println("1. Dupa medie (descrescator)");
        System.out.println("2. Dupa nume (alfabetic)");
        System.out.print("Alege: ");
        int opt = Integer.parseInt(scanner.nextLine());

        List<Elev> copie = new ArrayList<>(listaElevi);
        if (opt == 1) {
            copie.sort((e1, e2) -> Double.compare(e2.getMedie(), e1.getMedie()));
        } else if (opt == 2) {
            copie.sort((e1, e2) -> e1.getNume().compareToIgnoreCase(e2.getNume()));
        }

        for (Elev e : copie) {
            System.out.println(e);
        }
    }

    private static Elev gasesteElevDupaId(int id) {
        for (Elev e : listaElevi) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    private static Sala gasesteSalaDupaId(int id) {
        for (Sala s : listaSali) {
            if (s.getId() == id) return s;
        }
        return null;
    }
}