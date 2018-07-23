package test;

import com.intecs.mab.*;
import com.intecs.mab.exception.ElementIsAlreadyPresentException;
import com.intecs.mab.exception.IllegalUsernameException;
import com.intecs.mab.exception.LoginRequiredException;
import com.intecs.mab.exception.PlayerIsNotPresentException;
import com.intecs.player.ExplorationRate;
import com.intecs.player.Player;
import com.intecs.player.UniformExploration;

import db.ArchiveDB;
import db.PlayerDataCorruptionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestApplication {

    private IArchiveDB archive;
    private Application application;
    private Username pippo, topolino;

    @BeforeEach
    private void initialize() throws SQLException, ClassNotFoundException, IllegalUsernameException {
        archive = ArchiveDB.getInstance();
        archive.cleanPlayerTable();
        application = new Application();
        pippo = Username.createUserNameByUser("Pippo");
        topolino = Username.createUserNameByUser("Topolino");
    }

    @Test
    void testPlayWithoutLogin() {
        assertThrows(LoginRequiredException.class, () -> {
            application.play();
        });
    }

    @Test
    void testCorrectPlayerLogin_() throws ElementIsAlreadyPresentException, SQLException, 
    ClassNotFoundException, PlayerDataCorruptionException, PlayerIsNotPresentException, 
    IllegalUsernameException {
    	Player player = createUniformPlayer(pippo);
    	player.register();
    	assertEquals(player, application.login(pippo));
    }

//    @Test
//    void testRegistrationNewPlayer() throws ClassNotFoundException, PlayerIsAlreadyPresentException,
//            SQLException, IllegalUsernameException, PlayerDataCorruptionException, PlayerIsNotPresentException {
//        Player player = createUniformPlayer(pippo);
//        application.registerUser(player);
//        assertTrue(archive.isPresent("Pippo"));
//    }

    @Test
    void testRegistrationExistingPlayer() throws ClassNotFoundException, ElementIsAlreadyPresentException,
            SQLException, IllegalUsernameException, PlayerDataCorruptionException, PlayerIsNotPresentException {
        Player player = createUniformPlayer(pippo);
        player.register();
        assertThrows(ElementIsAlreadyPresentException.class, () -> {
            player.register();
        });
    }

    @Test
    void testUnregistrationExistingPlayer() throws ClassNotFoundException, ElementIsAlreadyPresentException,
            SQLException, PlayerIsNotPresentException, IllegalUsernameException, PlayerDataCorruptionException {
    	Player player = createUniformPlayer(pippo);
    	player.register();
        player.delete(player.getUserName());
    	assertFalse(archive.isPresent("Pippo"));
    }

    @Test
    void testUnregistrationNotExistingPlayer() throws IllegalUsernameException {
        Player player = createUniformPlayer(pippo);
        assertThrows(PlayerIsNotPresentException.class, () -> {
            player.delete(pippo.getValue());
        });
    }

    private Player createUniformPlayer(Username username) throws IllegalUsernameException {
    	ExplorationRate rate = new ExplorationRate(50);
    	String birthdate=new String("1984-07-14");
    	return new UniformExploration(username, "Luca", birthdate, rate);
    }

}
