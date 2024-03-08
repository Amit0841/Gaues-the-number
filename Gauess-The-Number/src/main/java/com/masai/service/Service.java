package com.masai.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.masai.model.Responce;

@org.springframework.stereotype.Service
public class Service implements ServiceInterface{
	List<Responce> map=new ArrayList<>();
	
	int number=0;
	String userName;
	int totalAttempt=0;
	String guessNumber="";
	
 void generateNumber(Integer numb) {
	 Random random = new Random();

     int randomNumber = random.nextInt(numb) + 1;
     number=randomNumber;

}
	
	@Override
	public Responce createGame(Integer level, String name) {
		userName=name;
		 totalAttempt=0;
		 guessNumber="";
		generateNumber(level);
		Responce r=new Responce();
		r.setResponceString("Welcome "+name);
		return r;
	}

	@Override
	public Responce testNumber(Integer number) {
		totalAttempt++;
		guessNumber+=String.valueOf(number)+", ";
		
		Responce r=new Responce();
		r.setAtempt(totalAttempt);
		r.setGuessNumber(guessNumber);
		
		if(number==this.number) {

			r.setGuessNumber(this.userName);
			boolean b=true;
			for (Responce existingResponce : map) {
				 if (existingResponce.getGuessNumber().equals(this.userName)) {
					 b=false;
					 if(existingResponce.getAtempt()>totalAttempt) {
					 existingResponce.setAtempt(totalAttempt);
					 }
					 
				 }
			}
			
			if(b) {
			map.add(r);
			}
			
			 r.setResponceString("Done");
			
			 return r;
		}else if(number<this.number) {
			 r.setResponceString("Too low");
			return r;
		}else{
			 r.setResponceString("Too High");
			return r;
		}	
	}

	@Override
	public List<Responce> getResult() {
		Collections.sort(map, Comparator.comparingInt(Responce::getAtempt));
		return this.map;
	}

	@Override
	public Responce resetGame() {
		Responce r=new Responce();
		this.map=new ArrayList();
		r.setResponceString("Game Restert");
		return r;
	}

	@Override
	public Responce sms() {

	         
	        try {
	            
	            String apiKey = "apikey=" + "NGM3MTQyNzc2NDQ0Njk2YTY4N2E1NTVhNDY2NTUxNDI=";
	            String message = "&message=" +URLEncoder.encode("Greetings from Simplifying Tech","UTF-8") ;
	            
	            String numbers = "&numbers=" + "7980165535";
	 
	            String url="https://api.textlocal.in/send/?"+ apiKey + message + numbers;
	            		
	            URL conn = new URL(url);
	            URLConnection c= conn.openConnection();
	            
	            c.setDoOutput(true);
	             
	            BufferedReader rd = new BufferedReader(new
	            		InputStreamReader(c.getInputStream()));
	            
	            StringBuffer stringBuffer = new StringBuffer();
	            String line;
	            while ((line = rd.readLine()) != null) {
	                stringBuffer.append(line).append("\n");
	            }
	            System.out.println(stringBuffer.toString());
	            rd.close();
	 
	 
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
	    
		return null;
	}

}
