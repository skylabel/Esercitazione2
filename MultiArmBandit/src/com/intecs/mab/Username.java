package com.intecs.mab;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Username {

    String username;

    public Username(String username) throws IllegalUsernameException {
          if(checkUserValidity(username))
              this.username = username;
         else
             throw new IllegalUsernameException();
    }

    private boolean checkUserValidity(String username) {
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
}
