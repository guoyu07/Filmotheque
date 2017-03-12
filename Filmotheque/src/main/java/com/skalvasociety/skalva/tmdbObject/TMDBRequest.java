package com.skalvasociety.skalva.tmdbObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skalvasociety.skalva.bean.Film;
import com.skalvasociety.skalva.bean.MediaTMDB;
import com.skalvasociety.skalva.bean.Serie;


public class TMDBRequest {
    
	public TMDBRequest(String API_KEY){
		this.setApi_key(API_KEY);
	}
	
	private final String USER_AGENT = "Mozilla/5.0";
	private String api_key;
	
	public SearchMovie searchMovie(String nameMovie) throws IOException{
			
		
		// Au minimum le nom doit contenir 1 caractères + l'extension
		if(nameMovie.length()<=4)
			return null;
		
		// Suppression extension
		String nameMovieFormat = nameMovie.substring(0, nameMovie.length()-4);
		
		// Remplacement . et _ par espace
		nameMovieFormat = nameMovieFormat.replace('.', ' ');
		nameMovieFormat = nameMovieFormat.replace('_', ' ');
		
		// formatage des espaces pour requetes
		
		nameMovieFormat = nameMovieFormat.replaceAll(" ", "%20");
				
		
		
		String url = "https://api.themoviedb.org/3/search/movie";
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";
		// Query
		url += "&query="+nameMovieFormat;
		// Options
		url += "&page=1&include_adult=false";
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();
				
				// optional default is GET
				con.setRequestMethod("GET");
				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);

				responseCode = con.getResponseCode();
				//System.out.println("2 eme essai");
				//System.out.println("\nSending 'GET' request to URL : " + url);
				//System.out.println("Response Code : " + responseCode);
				
				
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			//System.out.println(response.toString());
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			SearchMovie movie = objectMapper.readValue(response.toString(),SearchMovie.class );
			return movie;
		}else{
			return null;
		}
	}
	
	public MovieDetails getMovieByID (int id) throws IOException{
	
		//https://api.themoviedb.org/3/movie/100?api_key=806c2dcfdd6cab66be30e3353293fee2&language=fr-FR
		
		String url = "https://api.themoviedb.org/3/movie/"+ id;
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();
				
				// optional default is GET
				con.setRequestMethod("GET");
				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);

				responseCode = con.getResponseCode();
				//System.out.println("2 eme essai");
				//System.out.println("\nSending 'GET' request to URL : " + url);
				//System.out.println("Response Code : " + responseCode);
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			//System.out.println(response.toString());
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			MovieDetails movie = objectMapper.readValue(response.toString(),MovieDetails.class );
			return movie;
		}else{
			return null;
		}
	}
	
	public Video getVideoByID (MediaTMDB media) throws IOException{
		//https://api.themoviedb.org/3/movie/75/videos?api_key=806c2dcfdd6cab66be30e3353293fee2&language=en-US
		
		String url = "";
		if (media.getClass() == Film.class){
			url = "https://api.themoviedb.org/3/movie/"+  ((Film)media).getIdTMDB()+ "/videos";
		}else if (media.getClass() == Serie.class){
			url = "https://api.themoviedb.org/3/tv/"+  ((Serie)media).getIdTMDB()+ "/videos";
		}else{
			return null;
		}
		
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=en-US";
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();
				
				// optional default is GET
				con.setRequestMethod("GET");
				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);

				responseCode = con.getResponseCode();
				//System.out.println("2 eme essai");
				//System.out.println("\nSending 'GET' request to URL : " + url);
				//System.out.println("Response Code : " + responseCode);
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			//System.out.println(response.toString());
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			Video video = objectMapper.readValue(response.toString(),Video.class );
			return video;
		}else{
			return null;
		}
	}
	
	public SearchSerie searchSerie (String name) throws JsonParseException, JsonMappingException, IOException{
		//https://api.themoviedb.org/3/search/tv?api_key=806c2dcfdd6cab66be30e3353293fee2&language=fr-FR&query=Vikings&page=1
		
		String nameSerieFormat = name.replaceAll(" ", "%20");		
		String url = "https://api.themoviedb.org/3/search/tv";
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";
		// Query
		url += "&query="+nameSerieFormat;
		// Options
		url += "&page=1";
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();
				
				// optional default is GET
				con.setRequestMethod("GET");
				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);

				responseCode = con.getResponseCode();
				//System.out.println("2 eme essai");
				//System.out.println("\nSending 'GET' request to URL : " + url);
				//System.out.println("Response Code : " + responseCode);
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			//System.out.println(response.toString());
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			SearchSerie serie = objectMapper.readValue(response.toString(),SearchSerie.class );
			return serie;
		}else{
			return null;
		}
	}
	
	public SerieDetails getSerieByID (int id) throws IOException{
		
		//https://api.themoviedb.org/3/tv/300?api_key=806c2dcfdd6cab66be30e3353293fee2&language=fr-FR
			
		String url = "https://api.themoviedb.org/3/tv/"+ id;
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();
				
				// optional default is GET
				con.setRequestMethod("GET");
				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);

				responseCode = con.getResponseCode();
				//System.out.println("2 eme essai");
				//System.out.println("\nSending 'GET' request to URL : " + url);
				//System.out.println("Response Code : " + responseCode);
				
				
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			//System.out.println(response.toString());
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			SerieDetails serie = objectMapper.readValue(response.toString(),SerieDetails.class );
			return serie;
		}else{
			return null;
		}
	}

	
	public SerieSaisonDetails getSerieSaison (int id, String sNumSaison) throws IOException{
		// Au minimum le nom doit contenir saison + espace + numSaison soit 8  caractètres
		if(sNumSaison.length()<=7)
			return null;
		
		// Suppression extension
		String numSaisonFormat = sNumSaison.substring(7, sNumSaison.length());
		int numSaison;
		System.out.println("numSaisonFormat: |"+ numSaisonFormat + "|" );
		try {
			numSaison = Integer.parseInt(numSaisonFormat);
		} catch (Exception e) {
			return null;
		}	
		
		
		//https://api.themoviedb.org/3/tv/46533/season/1?api_key=806c2dcfdd6cab66be30e3353293fee2&language=fr-FR
			
		String url = "https://api.themoviedb.org/3/tv/"+ id;
		// numero saison
		url += "/season/"+numSaison;
		// Api_key
		url += "?api_key="+getApi_key();
		// Language
		url += "&language=fr-FR";
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
		
		if (responseCode == 429){
			try {
				Thread.sleep(5000);
				obj = new URL(url);
				con = (HttpURLConnection) obj.openConnection();
				
				// optional default is GET
				con.setRequestMethod("GET");
				
				//add request header
				con.setRequestProperty("User-Agent", USER_AGENT);

				responseCode = con.getResponseCode();
				//System.out.println("2 eme essai");
				//System.out.println("\nSending 'GET' request to URL : " + url);
				//System.out.println("Response Code : " + responseCode);
				
				
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		
		if (responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			ObjectMapper objectMapper = new ObjectMapper(); 
			SerieSaisonDetails serieSaison = objectMapper.readValue(response.toString(),SerieSaisonDetails.class );
			return serieSaison;
		}else{
			return null;
		}
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	
}
