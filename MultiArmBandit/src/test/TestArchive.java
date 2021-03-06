package test;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;

import com.intecs.mab.Username;
import com.intecs.mab.exception.IllegalUsernameException;
import com.intecs.player.ExplorationRate;
import com.intecs.player.Player;
import com.intecs.player.UniformExploration;
import com.intecs.player.UpperConfidenceBound;

import db.ArchiveDB;

class TestArchive {
	
	private ArchiveDB archive;
	private Player andrea, luca;
	
	@BeforeEach
	private void initialize() throws SQLException, ClassNotFoundException, IllegalUsernameException {
		archive = ArchiveDB.getInstance();
        archive.cleanPlayerTable();
        andrea = createAndrea();
		luca = createLuca();
	}
	
//	@Test
//	void testNewPlayerRegistration() throws NullPointerException, SQLException, ClassNotFoundException,
//			PlayerIsAlreadyPresentException {
//		assertFalse(archive.isPresent("Pippo"));
//		archive.register(andrea);
//		assertTrue(archive.isPresent("Pippo"));
//	}
//
//	@Test
//	void testExistingPlayerRegistration1() throws NullPointerException, PlayerIsAlreadyPresentException,
//			SQLException, ClassNotFoundException {
//		archive.register(andrea);
//		assertThrows(PlayerIsAlreadyPresentException.class, ()->{archive.register(andrea);});
//	}
//
//	@Test
//	void testNullPlayerRegistration() {
//		assertThrows(NullPointerException.class, ()->{archive.register(null);});
//	}
//
//	@Test
//	void testExistingPlayerRegistration2() throws NullPointerException, PlayerIsAlreadyPresentException, SQLException,
//			ClassNotFoundException {
//		archive.register(andrea);
//		archive.register(luca);
//		assertThrows(PlayerIsAlreadyPresentException.class, ()->{archive.register(andrea);});
//	}
//
//	@Test
//	void testDeleteExistingPlayer() throws NullPointerException, PlayerIsAlreadyPresentException, PlayerIsNotPresentException,
//			SQLException, ClassNotFoundException {
//		archive.register(andrea);
//		archive.delete("Pippo");
//		assertFalse(archive.isPresent("Pippo"));
//	}
//
//	@Test
//	void testDeleteNullPlayer() {
//		assertThrows(NullPointerException.class, ()->{archive.delete(null);});
//	}
//
//	@Test
//	void testDeleteNotExistingPlayer1() {
//		assertThrows(PlayerIsNotPresentException.class, ()->{archive.delete("Pippo");});
//	}
//
//	@Test
//	void testDeleteNotExistingPlayer2() throws NullPointerException, PlayerIsAlreadyPresentException, SQLException,
//			ClassNotFoundException {
//		archive.register(luca);
//		assertThrows(PlayerIsNotPresentException.class, ()->{archive.delete("Pippo");});
//	}
//
	private Player createAndrea() throws IllegalUsernameException {
		String birthdate=new String("1984-5-19");
		Player player = new UpperConfidenceBound(Username.createUserNameByUser("Pippo"),"Andrea", birthdate);
		return player;
	}

	private Player createLuca() throws IllegalUsernameException {
		String birthdate=new String("1984-5-19");
		Player luca = new UniformExploration(Username.createUserNameByUser("Topolino"),"Luca", birthdate, new ExplorationRate(100));
		return luca;
	}
	
}
