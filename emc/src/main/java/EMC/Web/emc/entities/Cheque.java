package EMC.Web.emc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Cheque implements Serializable{
	@Id
	private Long numCheque;
	private Float montant;
	private String devise;
	private Date dateCréation;
	private Date dateSortie;
	@Enumerated(EnumType.STRING)
	private StatutCheque statut;

	@ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH})
	@JoinColumn(name="bordereau_id")
	private Bordereau bordereau;
	
	
	@ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH})
	private Client clientCh;
	
	@ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH})
	private Banque banque;
	
	@ManyToOne(cascade = CascadeType.ALL)
	User User;

	public Cheque(Long numCheque, Float montant, String devise, Date dateCréation, Date dateSortie, StatutCheque statut,
			Bordereau bordereau, Client clientCh, Banque banque,User user) {
		super();
		this.numCheque = numCheque;
		this.montant = montant;
		this.devise = devise;
		this.dateCréation = dateCréation;
		this.dateSortie = dateSortie;
		this.bordereau = bordereau;
		this.clientCh = clientCh;
		this.banque = banque;
		this.statut=statut;
		User = user;
	}

	public Cheque() {
		super();
	}

	public Long getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(Long numCheque) {
		this.numCheque = numCheque;
	}

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	public Date getDateCréation() {
		return dateCréation;
	}

	public void setDateCréation(Date dateCréation) {
		this.dateCréation = dateCréation;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}



	public Bordereau getBordereau() {
		return bordereau;
	}

	public void setBordereau(Bordereau bordereau) {
		this.bordereau = bordereau;
	}

	public Client getClientCh() {
		return clientCh;
	}

	public void setClientCh(Client clientCh) {
		this.clientCh = clientCh;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public StatutCheque getStatut() {
		return statut;
	}

	public void setStatut(StatutCheque statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "Cheque [numCheque=" + numCheque + ", montant=" + montant + ", devise=" + devise + ", dateCréation="
				+ dateCréation + ", dateSortie=" + dateSortie + ", statut=" + statut + ", bordereau=" + bordereau
				+ ", clientCh=" + clientCh + ", banque=" + banque + ", User=" + User + "]";
	}


	
}
