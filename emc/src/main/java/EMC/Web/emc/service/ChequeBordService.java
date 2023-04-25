package EMC.Web.emc.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import EMC.Web.emc.entities.Banque;
import EMC.Web.emc.entities.Bordereau;
import EMC.Web.emc.entities.Cheque;
import EMC.Web.emc.entities.Client;
import EMC.Web.emc.entities.Compte;
import EMC.Web.emc.entities.StatutCheque;
import EMC.Web.emc.exception.UserNotFoundException;
import EMC.Web.emc.repo.BanqueRepo;
import EMC.Web.emc.repo.BordereauRepository;
import EMC.Web.emc.repo.ChequeRepository;
import EMC.Web.emc.repo.ClientRepo;
import EMC.Web.emc.repo.CompteRepo;


@Service
@Transactional
public class ChequeBordService {
	 private static final StatutCheque Reçu = null;
	private final ChequeRepository repoC;
	 private final BordereauRepository repoB;
	 private final BanqueRepo repobanque;
	 private final ClientRepo repoclient;
	 private final CompteRepo repocompte;
	 
	    @Autowired
	    public ChequeBordService(ChequeRepository Repo,BordereauRepository RepoB,BanqueRepo Repobanque,ClientRepo Repoclient,CompteRepo repocompte) {
	        this.repoC = Repo;
	        this.repoB=RepoB;
	        this.repobanque=Repobanque;
	        this.repoclient=Repoclient;
	        this.repocompte=repocompte;
	    }
	    public Integer LonguerCheque(Long numcheque) {
	    	String cheque = numcheque.toString();
	    	int len =cheque.length();
	    	if(len==7) {
	    		return 1;
	    	}
	    	else {
	    		return 2;
	    	}
	    }
	    
	    public Cheque créerCheque(Long numCheque,Float montant,String devise,Bordereau bor) {
	    	Cheque ch=new Cheque(numCheque,montant,devise,null,null,null,bor,null,null,null);
	    	return ch;
	    }
	    public Bordereau créerBordereau(Long numBordereau,Date datBbordereau,List<Cheque> chequeList ) {
	    	Bordereau bordereau=new Bordereau(numBordereau,datBbordereau,chequeList);
	    	return bordereau;
	    }
	    
	    public Bordereau RechercherBordereau(Long numBordereau ) {
	  	    	return repoB.findById(numBordereau)
	  	    			.orElseThrow(() -> new UserNotFoundException("bordereau introuvable"));
	    	
	    }
	    
	    public Cheque RechercherCheque(Long numCheque ) {
  	    	return repoC.findById(numCheque)
  	    			.orElseThrow(() -> new UserNotFoundException("cheque introuvable"));
    	
    }
	    
	    public Boolean ChequeExistant(Long numCheque) {
	    	if(repoC.findById(numCheque).isPresent()) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    
	    public List<Bordereau> afficherBordereau(){
	    	List<Bordereau> listeToday = new ArrayList<Bordereau>();
		    List<Bordereau> liste=repoB.findAll();
			if(liste.isEmpty()) {
				return null;
			}
			else{
				for(int i=0;i<liste.size();i++) {
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(liste.get(i).getDateBordereau());
					cal1.set(Calendar.HOUR_OF_DAY, 0);
					cal1.set(Calendar.MINUTE, 0);
					cal1.set(Calendar.SECOND, 0);
					cal1.set(Calendar.MILLISECOND, 0);

					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(new Date());
					cal2.set(Calendar.HOUR_OF_DAY, 0);
					cal2.set(Calendar.MINUTE, 0);
					cal2.set(Calendar.SECOND, 0);
					cal2.set(Calendar.MILLISECOND, 0);

					if (cal1.getTime().equals(cal2.getTime())) {
						Bordereau b=liste.get(i);
						listeToday.add(b);
					}
					
					Bordereau bordereau=this.RechercherBordereau(liste.get(i).getNumBordereau());
					liste.get(i).setNumBordereau(bordereau.getNumBordereau());
					liste.get(i).setDateBordereau(bordereau.getDateBordereau());
					liste.get(i).setListeCh(bordereau.getListeCh());

				}
			}
			return listeToday;

	    }
	    
	    public Integer NbrCheques(List<Bordereau> liste) {
	    	Integer c=0;
			for(int i=0;i<liste.size();i++) {
				Bordereau bordereau=this.RechercherBordereau(liste.get(i).getNumBordereau());
				c=c+bordereau.getListeCh().size();
			}
			return c;
	    }
	    
	    public List<Cheque> afficherCheques(){
	    	 List<Cheque> liste=repoC.findAll();
	    	 return liste;
	    }
	    
	    public Cheque SaveUpdatedCheque(Cheque cheque) {
	    	Banque banque = cheque.getBanque();
	        repobanque.save(banque);
	        Client client=cheque.getClientCh();
	        Compte compte=client.getCompte();
	        repoclient.save(client);
	        repocompte.save(compte);
	    	return repoC.save(cheque);
	    }
	   
	   
	    public Cheque UpdateCheque(Long numch,String nomClient, String numCompte, Long codeBanque, String nomBanque, Long codeSwift) {
	    	Cheque cheque=this.RechercherCheque(numch);
	    	if(cheque!=null) {
	    		List<Cheque>liste=new ArrayList<>();
		    	liste.add(cheque);
		        Banque banque = new Banque(codeBanque, nomBanque, codeSwift, null, null, liste);
		        repobanque.save(banque);
		        cheque.setBanque(banque);
		        Compte compte=new Compte(numCompte,null);
		        repocompte.save(compte);
		        Client client=new Client(nomClient,null,null,null,compte,liste,null);
		        repoclient.save(client);
		        compte.setClient(client);
		        repocompte.save(compte);
		        cheque.setClientCh(client);
	    	}
	        return cheque;
	    }
	    
	    public Client créerClient(String nomClient,Compte compte) {
	    	Client client=new Client();
	    	client.setNomClient(nomClient);
	    	client.setCompte(compte);
	    	return client;
	    	
	    }
	    
	    public Compte créerCompte(String numCompte) {
	    	Compte compte=new Compte();
	    	compte.setNumCompte(numCompte);
	    	return compte;
	    }
	    
	    public Banque créerBanque( Long codeBanque, String nomBanque, Long codeSwift,Cheque ch) {
	    	List<Cheque>liste=new ArrayList<>();
	    	liste.add(ch);
	        Banque banque = new Banque(codeBanque, nomBanque, codeSwift, null, null, liste);
	    	return banque;
	    	
	    }
	    
//suivie
	    public List<Cheque> listeRecu(){
	    	List<Cheque> listeRecu = new ArrayList<Cheque>();
	    	StatutCheque statut = null;
	    	List<Cheque> liste=repoC.findAll();
			if(liste.isEmpty()) {
				return null;
			}
			else{
				for(int i=0;i<liste.size();i++) {
						if(liste.get(i).getStatut()==statut.Reçu) {
							listeRecu.add(liste.get(i));
						}
					}
				}
			
			return listeRecu;

	    }
	    
	    
	    
	    
	    
	    
}