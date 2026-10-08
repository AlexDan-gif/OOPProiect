public class Elev {
    private int id;
    private String nume;
    private int varsta;
    private double medie;

    public Elev() {
        this.id = 1;
        this.nume = "Anonim";
        this.varsta = 18;
        this.medie = 5.0;
    }

    public Elev(int id, String nume, int varsta, double medie) {
        setId(id);
        setNume(nume);
        setVarsta(varsta);
        setMedie(medie);
    }

    public Elev(int id, String nume) {
        setId(id);
        setNume(nume);
        this.varsta = 18;
        this.medie = 5.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            System.out.println("Eroare: ID-ul trebuie sa fie pozitiv!");
            return;
        }
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        if (nume == null || nume.trim().isEmpty()) {
            System.out.println("Eroare: Numele nu poate fi gol!");
            return;
        }
        this.nume = nume.trim();
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        if (varsta < 6 || varsta > 100) {
            System.out.println("Eroare: Varsta invalida!");
            return;
        }
        this.varsta = varsta;
    }

    public double getMedie() {
        return medie;
    }

    public void setMedie(double medie) {
        if (medie < 1.0 || medie > 10.0) {
            System.out.println("Eroare: Media trebuie sa fie intre 1 si 10!");
            return;
        }
        this.medie = medie;
    }
    public boolean estePromovat() {
        return this.medie >= 5.0;
    }

    @Override
    public String toString() {
        String statut = estePromovat() ? "Promovat" : "Nepromovat";
        return "ID: " + id + " | Nume: " + nume + " | Varsta: " + varsta + " | Medie: " + medie + " | Statut: " + statut;
    }
}