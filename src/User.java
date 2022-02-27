public class User {
    private String nom;
    private String prenom;
    private String motdepasse;
    private String grade;
    private Equipements equipements;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = parseName(nom);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = parseName(prenom);
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Equipements getEquipements() {
        return equipements;
    }

    public void setEquipements(Equipements equipements) {
        this.equipements = equipements;
    }

    private String parseName(String prenom){
        if (prenom.contains("é")){
            prenom = prenom.replace("é","e");
        }
        if (prenom.contains("è")){
            prenom = prenom.replace("è","e");
        }
        if (prenom.contains("ë")){
            prenom = prenom.replace("ë","e");
        }
        return prenom;
    }

    @Override
    public String toString() {
        return "User{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                ", grade='" + grade + '\'' +
                ", equipements=" + equipements +
                '}';
    }
}
