package com.virtualbiblio.virtualbiblio.serviceImpl;

import com.virtualbiblio.virtualbiblio.model.Admin;
import com.virtualbiblio.virtualbiblio.model.Utilisateur;
import com.virtualbiblio.virtualbiblio.repository.UtilisateurRepository;
import com.virtualbiblio.virtualbiblio.service.MailSenderService;
import com.virtualbiblio.virtualbiblio.service.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    MailSenderService mailSenderService;
    @Transactional
    @Override
    public String ajoutUtilisateur(Utilisateur utilisateur) {

         utilisateurRepository.save(utilisateur);
        mailSenderService.sendEmail(
                utilisateur.getEmail(),
                "Votre compte a été créer avec succès \n pour activer votre compte veillez cliquez sur ce lien http://localhost:8080/api/utilisateur/restore/"+utilisateur.getIdUtilisateur(),
                "Création de compte sur Vbiblio http://localhost:8080/api/utilisateur"

        );
         return "success";
    }

    @Override
    public Utilisateur afficheUtilisateur(Long id) {
        return utilisateurRepository.findById(id).get();
    }

    @Override
    public List<Utilisateur> listUtilisateur() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur, Long id) {
        Utilisateur updateUtilisateur = utilisateurRepository.findById(id).get();

        updateUtilisateur.setNom(utilisateur.getNom());
        updateUtilisateur.setPrenom(utilisateur.getPrenom());
        updateUtilisateur.setEmail(utilisateur.getEmail());
        updateUtilisateur.setLogin(utilisateur.getLogin());
        updateUtilisateur.setTelephone(utilisateur.getTelephone());
        updateUtilisateur.setPassword(utilisateur.getPassword());
        updateUtilisateur.setProfile(utilisateur.getProfile());
        return utilisateurRepository.save(updateUtilisateur);
    }

    @Override
    public Void deleteUtilisateur(Long id) {
            utilisateurRepository.deleteById(id);
        return null;
    }

    @Override
    public Utilisateur disable(Long id) {
        Utilisateur disable = utilisateurRepository.findById(id).get();
        disable.setDeleted(true);
        return utilisateurRepository.save(disable);
    }

    @Transactional
    @Override
    public Utilisateur restore(Long id) {
        Utilisateur restore = utilisateurRepository.findById(id).get();
        restore.setDeleted(false);
        utilisateurRepository.save(restore);
        return null;
    }

    @Override
    public Utilisateur login(String login, String password) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByLoginAndPassword(login,password);

       if(utilisateur.isEmpty())
       {            return null;
        }

        if(utilisateur.get().isDeleted())
        {
            throw new IllegalStateException("Votre compte administrateur est désactivé !");
        }

        return utilisateur.get();
    }

    @Override
    public List<Utilisateur> listByDeleted(Boolean deleted) {
        return utilisateurRepository.findByDeleted(deleted);
    }

    @Override
    public Utilisateur resetpassword(String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);

        if(utilisateur.isEmpty())
        {
            return null;
        }else {


            return null;
        }

    }

    @Override
    public Utilisateur changePassword(Long id, Utilisateur utilisateur) {
        Utilisateur Updateutilisateur = utilisateurRepository.findById(id).get();

        Updateutilisateur.setPassword(utilisateur.getPassword());

            return utilisateurRepository.save(Updateutilisateur);
    }

}
