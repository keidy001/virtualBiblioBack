package com.virtualbiblio.virtualbiblio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private int telephone;
    @Column(unique = true)
    private String login;
    private String password;
    private String profile;
    @JsonIgnore
    @ManyToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL)
    private List<Livre> livre;
    @JsonIgnore
    @ManyToMany(mappedBy = "utilisateurs",cascade = CascadeType.ALL)
    private List<Documents> documents;
    @ManyToOne
    Admin admin;
    private boolean deleted= true;

    public Utilisateur() {

    }

    public Utilisateur(String nom, String prenom, String email, int telephone, String login, String password, String profile, List<Livre> livre, Admin admin,boolean deleted) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.password = password;
        this.profile = profile;
        this.livre = livre;
        this.admin = admin;
        this.deleted = deleted;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<Livre> getLivre() {
        return livre;
    }

    public void setLivre(List<Livre> livre) {
        this.livre = livre;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
