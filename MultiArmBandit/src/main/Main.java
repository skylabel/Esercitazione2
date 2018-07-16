package main;

import com.intecs.mab.*;
import db.ArchiveDB;
import db.PlayerDataCorruptionException;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, 
    PlayerIsNotPresentException, PlayerIsAlreadyPresentException, PlayerDataCorruptionException, 
    LoginRequiredException, IllegalUsernameException {
        Application application = new Application(getCleanDB());
        
        Username pippo = new Username("Pippo");
        Player uniformPlayer = createDefaultUniformPlayer(pippo);
        application.registerUser(uniformPlayer);
        application.login_(pippo);
        application.play();
        
        Username topolino = new Username("Topolino");
        Player ucbPlayer = createUCBPlayer(topolino);
        application.registerUser(ucbPlayer);
        application.login_(topolino);
        application.play();
    }

	private static IArchiveDB getCleanDB() throws ClassNotFoundException, SQLException {
		IArchiveDB archive = new ArchiveDB();
        archive.cleanPlayerTable();
		return archive;
	}

	private static Player createDefaultUniformPlayer(Username username) throws IllegalUsernameException {
        ExplorationRate rate = new ExplorationRate(50);
        String birthdate = new String("1984-06-19");
		Player uniformPlayer = new UniformExploration(username,"Luca", birthdate, rate);
		return uniformPlayer;
	}

	private static Player createUCBPlayer(Username username) throws IllegalUsernameException {
		String birthdate = new String("1987-11-24");
		return new UpperConfidenceBound(username, "Andrea", birthdate);
	}

}
