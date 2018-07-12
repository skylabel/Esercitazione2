package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import com.intecs.mab.*;
import db.ArchiveDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestArchive {
	
	private ArchiveDB archive;
	
	@BeforeEach
	private void initialize() throws SQLException, ClassNotFoundException {
		archive = new ArchiveDB();
        archive.cleanPlayerTable();
	}
	
	@Test
	void testNewPlayerRegistration() throws NullPointerException, SQLException, ClassNotFoundException, PlayerIsAlreadyPresentException {
		Player player = createAndrea();
		assertFalse(archive.isPresent(player));//.getPlayers().contains(player));
		archive.register(player);
		assertTrue(archive.isPresent(player));
	}

	@Test
	void testExistingPlayerRegistration1() throws NullPointerException, PlayerIsAlreadyPresentException, SQLException, ClassNotFoundException {
		Player andrea = createAndrea();
		archive.register(andrea);
		assertThrows(PlayerIsAlreadyPresentException.class, ()->{archive.register(andrea);});
	}

	@Test
	void testNullPlayerRegistration() {
		assertThrows(NullPointerException.class, ()->{archive.register(null);});
	}



	@Test
	void testExistingPlayerRegistration2() throws NullPointerException, PlayerIsAlreadyPresentException, SQLException, ClassNotFoundException {
		Player andrea = createAndrea();
		archive.register(andrea);
		Player luca = createLuca();
		archive.register(luca);
		assertThrows(PlayerIsAlreadyPresentException.class, ()->{archive.register(andrea);});
	}
	
	@Test
	void testDeleteExistingPlayer() throws NullPointerException, PlayerIsAlreadyPresentException, PlayerIsNotPresentException, SQLException, ClassNotFoundException {
		Player andrea = createAndrea();
		archive.register(andrea);
		archive.delete(andrea);
		assertFalse(archive.isPresent(andrea));
	}

	@Test
	void testDeleteNullPlayer() {
		assertThrows(NullPointerException.class, ()->{archive.delete(null);});
	}

	@Test
	void testDeleteNotExistingPlayer1() {
		Player andrea = createAndrea();
		assertThrows(PlayerIsNotPresentException.class, ()->{archive.delete(andrea);});
	}

	@Test
	void testDeleteNotExistingPlayer2() throws NullPointerException, PlayerIsAlreadyPresentException, SQLException, ClassNotFoundException {
		Player luca = createLuca();
		archive.register(luca);
		Player andrea = createAndrea();
		assertThrows(PlayerIsNotPresentException.class, ()->{archive.delete(andrea);});
	}

	
	
	private Player createAndrea() {
		String birthdate=new String("1984-5-19");
		Player player = new UpperConfidenceBound("pippo","Andrea", birthdate);
		return player;
	}
	
	private Player createLuca() {
		String birthdate=new String("1984-5-19");
		Player luca = new UniformExplorationPlayer("topolino","Luca", birthdate, new ExplorationRate(100));
		return luca;
	}
	
}
