package com.sabiantart.jsontosqllite.models;

public class Employees {
    private final String id;
    private final String idrole;
    private final String username;
    private final String password;
    private final String email;
    private final String avatar;
    private final String prenom;
    private final String nom;
    private final String telephone;
    private final String statut;
    private final String dateinscription;
    private final String iduser;
    private final String datemiseajour;
    private final String sexe;
    private final String codeuser;
    private final String rolead1;
    private final String rolead2;
    private final String rolead3;
    private final String section;
    private final String langue;
    private final String synced;

    public Employees(String id, String idrole, String username, String password, String email, String avatar, String prenom, String nom, String telephone, String statut, String dateinscription, String iduser, String datemiseajour, String sexe, String codeuser, String rolead1, String rolead2, String rolead3, String section, String langue, String synced) {
        this.id = id;
        this.idrole = idrole;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.statut = statut;
        this.dateinscription = dateinscription;
        this.iduser = iduser;
        this.datemiseajour = datemiseajour;
        this.sexe = sexe;
        this.codeuser = codeuser;
        this.rolead1 = rolead1;
        this.rolead2 = rolead2;
        this.rolead3 = rolead3;
        this.section = section;
        this.langue = langue;
        this.synced = synced;
    }

    public String getId() {
        return id;
    }

    public String getIdrole() {
        return idrole;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getStatut() {
        return statut;
    }

    public String getDateinscription() {
        return dateinscription;
    }

    public String getIduser() {
        return iduser;
    }

    public String getDatemiseajour() {
        return datemiseajour;
    }

    public String getSexe() {
        return sexe;
    }

    public String getCodeuser() {
        return codeuser;
    }

    public String getRolead1() {
        return rolead1;
    }

    public String getRolead2() {
        return rolead2;
    }

    public String getRolead3() {
        return rolead3;
    }

    public String getSection() {
        return section;
    }

    public String getLangue() {
        return langue;
    }

    public String getSynced() {
        return synced;
    }
}
