import java.util.ArrayList;
import java.util.List;

public class Sala {
    private int id;
    private String denumire;
    private int capacitate;
    private List<Elev> elevi;

    public Sala() {
        this.id = 1;
        this.denumire = "Sala Implicita";
        this.capacitate = 30;
        this.elevi = new ArrayList<>();
    }
    public Sala(int id, String denumire, int capacitate) {
        setId(id);
        setDenumire(denumire);
        setCapacitate(capacitate);
        this.elevi = new ArrayList<>();
    }

    public Sala(int id, String denumire, int capacitate, List<Elev> elevi) {
        setId(id);
        setDenumire(denumire);
        setCapacitate(capacitate);
        setElevi(elevi);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            System.out.println("Eroare: ID-ul salii trebuie sa fie pozitiv!");
            return;
        }
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        if (denumire == null || denumire.trim().isEmpty()) {
            System.out.println("Eroare: Denumirea nu poate fi goala!");
            return;
        }
        this.denumire = denumire.trim();
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        if (capacitate <= 0) {
            System.out.println("Eroare: Capacitatea trebuie sa fie mai mare decat 0!");
            return;
        }
        this.capacitate = capacitate;
    }

    public List<Elev> getElevi() {
        return elevi;
    }

    public void setElevi(List<Elev> elevi) {
        if (elevi != null && elevi.size() <= this.capacitate) {
            this.elevi = elevi;
        } else {
            System.out.println("Eroare: Numarul de elevi depaseste capacitatea salii!");
        }
    }

    public boolean adaugaElev(Elev elev) {
        if (elevi.size() >= capacitate) {
            System.out.println("Eroare: Sala este plina!");
            return false;
        }
        for (Elev e : elevi) {
            if (e.getId() == elev.getId()) {
                System.out.println("Eroare: Elevul exista deja in aceasta sala!");
                return false;
            }
        }
        elevi.add(elev);
        return true;
    }

    public boolean stergeElev(int idElev) {
        for (int i = 0; i < elevi.size(); i++) {
            if (elevi.get(i).getId() == idElev) {
                elevi.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String info = "Sala " + denumire + " (ID: " + id + ", Capacitate: " + elevi.size() + "/" + capacitate + ")\n";
        if (elevi.isEmpty()) {
            info += "  -> Nu sunt elevi in sala.\n";
        } else {
            for (Elev e : elevi) {
                info += "  -> " + e.toString() + "\n";
            }
        }
        return info;
    }
}