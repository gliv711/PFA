package PFA.ModifierInfo.Module;

public class information {
    private String adresse;
    private String email;
    private String telephone;
    private String nom;
    
    public information(String adresse, String email, String telephone, String nom) {
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.nom = nom;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
}
