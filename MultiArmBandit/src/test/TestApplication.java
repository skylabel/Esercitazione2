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

    @BeforeEach
    private void initialize() throws SQLException, ClassNotFoundException {
        archive = new ArchiveDB();
        archive.cleanPlayerTable();
        application = new Application(archive);
    }

    @Test
    void testPlayWithoutLogin() {
        assertThrows(LoginRequiredException.class, () -> {
            application.play();
        });
    }

    @Test
    void testCorrectPlayerLogin() throws PlayerIsAlreadyPresentException, SQLException, ClassNotFoundException,
            PlayerDataCorruptionException, PlayerIsNotPresentException, IllegalUsernameException {
        Player player = createUniformPlayer("Pippo");
        application.registerUser(player);
        assertEquals(player,application.login("Pippo"));

    }

    @Test
    void testRegistrationNewPlayer() throws ClassNotFoundException, PlayerIsAlreadyPresentException,
            SQLException, IllegalUsernameException {
        Player player = createUniformPlayer("Pippo");
        application.registerUser(player);
        assertTrue(archive.isPresent("Pippo"));
    }

    @Test
    void testRegistrationExistingPlayer() throws ClassNotFoundException, PlayerIsAlreadyPresentException,
            SQLException, IllegalUsernameException {
        Player player = createUniformPlayer("Pippo");
        application.registerUser(player);
        assertThrows(PlayerIsAlreadyPresentException.class, () -> {
            application.registerUser(player);
        });
    }

    @Test
    void testUnregistrationExistingPlayer() throws ClassNotFoundException, PlayerIsAlreadyPresentException,
            SQLException, PlayerIsNotPresentException, IllegalUsernameException {
        Player player = createUniformPlayer("Pippo");
        application.registerUser(player);
        application.unregisterPlayer(player);
        assertFalse(archive.isPresent("Pippo"));
    }

    @Test
    void testUnregistrationNotExistingPlayer() throws IllegalUsernameException {
        Player player = createUniformPlayer("Pippo");
        assertThrows(PlayerIsNotPresentException.class, () -> {
            application.unregisterPlayer(player);
        });
    }

    private Player createUniformPlayer(String usernname) throws IllegalUsernameException {
        Username uname = new Username(usernname);
        ExplorationRate rate = new ExplorationRate(50);
        String birthdate=new String("1984-07-14");
        return new UniformExplorationPlayer(uname,"Luca", birthdate, rate);
    }

}
