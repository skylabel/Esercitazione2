package com.intecs.mab;
import java.util.Objects;
import java.util.regex.Pattern;

import com.intecs.mab.exception.IllegalUsernameException;

import java.util.regex.Matcher;

public class Username {

    String username;

    private Username(String username) {
         this.username=username;
    }
    
    
    public static Username createUserNameByUser(String username) throws IllegalUsernameException {
    	 if(!checkUserValidity(username))  throw new IllegalUsernameException();
           
         else return new Username(username);
           
    }
    public static Username getUserNamefromDB(String username)   {
    	if(!checkUserValidity(username))  throw new IllegalStateException("UsernName: "+username +" Corrupted in db");
    	
    	else return new Username(username);
    	
    }
    
    
    private static boolean checkUserValidity(String username) {
        Pattern p = Pattern.compile("^[A-Z][a-z0-9_-]{3,16}$");
        Matcher m = p.matcher(username);
        return m.find();
    }

    public String getValue() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return Objects.equals(username, username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

	@Override
	public String toString() {
		return "Username [username=" + username + "]";
	}


}
