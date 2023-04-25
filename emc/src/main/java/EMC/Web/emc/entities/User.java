package EMC.Web.emc.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table; 


@Table
@Entity
public class User implements Serializable {
	@Id
	private Long matricule;
    private String pwd;
    private String nom;
	private String prenom;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "User")
	Set<Cheque> cheques;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Agence agence;

	public User(Long matricule, String pwd, String nom, String prenom, Role role, Set<Cheque> cheques, Agence agence) {
		super();
		this.matricule = matricule;
		this.pwd = pwd;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
		this.cheques = cheques;
		this.agence = agence;
	}

	public User() {
		super();
	}

	public Long getMatricule() {
		return matricule;
	}

	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Cheque> getCheques() {
		return cheques;
	}

	public void setCheques(Set<Cheque> cheques) {
		this.cheques = cheques;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	@Override
	public String toString() {
		return "User [matricule=" + matricule + ", pwd=" + pwd + ", nom=" + nom + ", prenom=" + prenom + ", role="
				+ role + ", cheques=" + cheques + ", agence=" + agence + "]";
	}
	
	
	
	}

	

