package EMC.Web.emc.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Compte implements Serializable{
	@Id
	@GeneratedValue
	private Long rib;
	private String numCompte;
	
	@OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH})
	private Client client;

	public Compte( String numCompte, Client client) {
		super();
		this.numCompte = numCompte;
		this.client = client;
	}

	public Compte() {
		super();
	}

	public Long getRib() {
		return rib;
	}

	public void setRib(Long rib) {
		this.rib = rib;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Compte [rib=" + rib + ", numCompte=" + numCompte + ", client=" + client + "]";
	}
	
	
	
	
	
	
	

}
