package main;

import com.intecs.mab.Application;
import com.intecs.mab.Username;
import com.intecs.mab.exception.ElementIsAlreadyPresentException;
import com.intecs.mab.exception.IllegalUsernameException;
import com.intecs.mab.exception.LoginRequiredException;
import com.intecs.mab.exception.PlayerIsNotPresentException;
import com.intecs.player.EpsilonGreedy;
import com.intecs.player.ExplorationRate;
import com.intecs.player.Player;
import com.intecs.player.UniformExploration;
import com.intecs.player.UpperConfidenceBound;

public class Main {

    public static void main(String[] args) throws  
    PlayerIsNotPresentException, ElementIsAlreadyPresentException, 
    LoginRequiredException, IllegalUsernameException {
        Application application = new Application();
        application.cleanDB();
        
        Username pippo = Username.createUserNameByUser("Pippo");
        Player uniformPlayer = createDefaultUniformPlayer(pippo);
        uniformPlayer.register();
        application.login(pippo);
        application.play();
        
        Username topolino = Username.createUserNameByUser("Topolino");
        Player ucbPlayer = createUCBPlayer(topolino);
        ucbPlayer.register();
        application.login(topolino);
        application.play();
        
        Username pluto = Username.createUserNameByUser("Pluto");
        Player epsgPlayer = createEpsGPlayer(pluto);
        epsgPlayer.register();
        application.login(pluto);
        application.play();
       
        System.out.println(application.getRanking());
        
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

	private static Player createEpsGPlayer(Username username) throws IllegalUsernameException {
		String birthdate = new String("1987-11-24");
		return new EpsilonGreedy(username, "Fiorenzo", birthdate);
	}

}
