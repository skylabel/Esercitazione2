package main;

import com.intecs.mab.*;
import db.ArchiveDB;
import db.PlayerDataCorruptionException;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, PlayerIsNotPresentException, PlayerIsAlreadyPresentException, PlayerDataCorruptionException, LoginRequiredException {
        ExplorationRate rate = new ExplorationRate(50);
        String birthdate = new String("1984-06-19");
        String username=new String("Topolino");
        Player uniformPlayer = new UniformExplorationPlayer(username,"Luca", birthdate, rate);
        IArchiveDB archive = new ArchiveDB();


        Application application = new Application(archive);
      //  application.registerUser(uniformPlayer);

        application.login(username);

        application.play();
    }

}