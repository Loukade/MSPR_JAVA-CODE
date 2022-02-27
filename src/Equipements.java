import java.util.ArrayList;
import java.util.List;

public class Equipements {
    private List<String> equipements = new ArrayList<String>();

    public List<String> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<String> equipements) {
        this.equipements = equipements;
    }

    public void addEquipements(String equipement){
        this.equipements.add(equipement);
    }

    public void clearEquipement(){
        this.equipements.clear();
    }

    @Override
    public String toString() {
        return "Equipements{" +
                "equipements=" + equipements +
                '}';
    }
}
