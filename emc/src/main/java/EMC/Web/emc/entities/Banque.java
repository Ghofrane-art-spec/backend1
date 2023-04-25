package EMC.Web.emc.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Banque implements Serializable{
	@Id
	private Long codeBanque;
	private String nomBanque;
	private Long swiftBanque;
	private Long tarifBanque;
	private Long fraiBanque;
	@JsonBackReference
	@OneToMany(mappedBy = "banque",cascade = CascadeType.ALL)
	private List<Cheque> cheque;

	public Banque(Long codeBanque, String nomBanque, Long swiftBanque, Long tarifBanque, Long fraiBanque,
			List<Cheque> cheque) {
		super();
		this.codeBanque = codeBanque;
		this.nomBanque = nomBanque;
		this.swiftBanque = swiftBanque;
		this.tarifBanque = tarifBanque;
		this.fraiBanque = fraiBanque;
		this.cheque = cheque;
	}

	public Banque() {
		super();
	}

	public Long getCodeBanque() {
		return codeBanque;
	}

	public void setCodeBanque(Long codeBanque) {
		this.codeBanque = codeBanque;
	}

	public String getNomBanque() {
		return nomBanque;
	}

	public void setNomBanque(String nomBanque) {
		this.nomBanque = nomBanque;
	}

	public Long getSwiftBanque() {
		return swiftBanque;
	}

	public void setSwiftBanque(Long swiftBanque) {
		this.swiftBanque = swiftBanque;
	}

	public Long getTarifBanque() {
		return tarifBanque;
	}

	public void setTarifBanque(Long tarifBanque) {
		this.tarifBanque = tarifBanque;
	}

	public Long getFraiBanque() {
		return fraiBanque;
	}

	public void setFraiBanque(Long fraiBanque) {
		this.fraiBanque = fraiBanque;
	}

	public List<Cheque> getCheque() {
		return cheque;
	}

	public void setCheque(List<Cheque> cheque) {
		this.cheque = cheque;
	}

	@Override
	public String toString() {
		return "Banque [codeBanque=" + codeBanque + ", nomBanque=" + nomBanque + ", swiftBanque=" + swiftBanque
				+ ", tarifBanque=" + tarifBanque + ", fraiBanque=" + fraiBanque + ", cheque=" + cheque + "]";
	}

	
	

}
