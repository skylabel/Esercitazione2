package test;

import com.intecs.mab.*;
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
        archive = new ArchiveDB();
        archive.cleanPlayerTable();
        application = new Application(archive);
        pippo = new Username("Pippo");
        topolino = new Username("Topolino");
    }

    @Test
    void testPlayWithoutLogin() {
        assertThrows(LoginRequiredException.class, () -> {
            application.play();
        });
    }

    @Test
    void testCorrectPlayerLogin_() throws PlayerIsAlreadyPresentException, SQLException, 
    ClassNotFoundException, PlayerDataCorruptionException, PlayerIsNotPresentException, 
    IllegalUsernameException {
    	Player player = createUniformPlayer(pippo);
    	application.registerUser(player);
    	assertEquals(player,application.login_(pippo));
    }

    @Test
    void testRegistrationNewPlayer() throws ClassNotFoundException, PlayerIsAlreadyPresentException,
    SQLException, IllegalUsernameException {
        Player player = createUniformPlayer(pippo);
        application.registerUser(player);
        assertTrue(archive.isPresent("Pippo"));
    }

    @Test
    void testRegistrationExistingPlayer() throws ClassNotFoundException, PlayerIsAlreadyPresentException,
    SQLException, IllegalUsernameException {
        Player player = createUniformPlayer(pippo);
        application.registerUser(player);
        assertThrows(PlayerIsAlreadyPresentException.class, () -> {
        	application.registerUser(player);
        });
    }

    @Test
    void testUnregistrationExistingPlayer() throws ClassNotFoundException, PlayerIsAlreadyPresentException,
    SQLException, PlayerIsNotPresentException, IllegalUsernameException {
    	Player player = createUniformPlayer(pippo);
    	application.registerUser(player);
    	application.unregisterPlayer(pippo);
    	assertFalse(archive.isPresent("Pippo"));
    }

    @Test
    void testUnregistrationNotExistingPlayer() throws IllegalUsernameException {
        Player player = createUniformPlayer(pippo);
        assertThrows(PlayerIsNotPresentException.class, () -> {
            application.unregisterPlayer(pippo);
        });
    }

    private Player createUniformPlayer(Username username) throws IllegalUsernameException {
    	ExplorationRate rate = new ExplorationRate(50);
    	String birthdate=new String("1984-07-14");
    	return new UniformExploration(username, "Luca", birthdate, rate);
    }

}
