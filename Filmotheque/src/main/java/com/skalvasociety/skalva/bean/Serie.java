package com.skalvasociety.skalva.bean;


import java.util.LinkedList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SERIE")
public class Serie extends MediaTMDB {
		
	private Double popularite;
	private Double note;	
	private List<Saison> saison = new LinkedList<Saison>();
	private static final int RESUME_COURT_LONG = 50;
	

	public Double getPopularite() {
		return popularite;
	}
	public void setPopularite(Double popularite) {
		this.popularite = popularite;
	}
	public Double getNote() {
		return note;
	}
	public void setNote(Double note) {
		this.note = note;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serie")
	public List<Saison> getSaison() {
		return saison;
	}
	public void setSaison(List<Saison> saison) {
		this.saison = saison;
	}
	
	@Override
	public void setResumeCourt(String resume) {
		int endIndex = RESUME_COURT_LONG;
		if(resume ==  null)
			return;
		
		if (resume.length() <= endIndex){
			endIndex = resume.length();
		}		
		super.setResumeCourt(resume.substring(0, endIndex)+"...");
	}
}

