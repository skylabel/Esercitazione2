package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.intecs.mab.ExplorationRate;
import com.intecs.mab.Player;
import com.intecs.mab.UniformExplorationPlayer;
import com.intecs.mab.UpperConfidenceBound;

class TestArchive {
	
	private Archive archive;
	
	@BeforeEach
	private void initialize() {
		archive = new Archive();
	}
	
	@Test
	void testNewPlayerRegistration() throws NullPointerException, PlayerIsAllReadyPresentException {
		Player player = createAndrea();
		assertFalse(archive.getPlayers().contains(player));
		archive.register(player);
		assertTrue(archive.getPlayers().contains(player));
	}

	@Test
	void testNullPlayerRegistration() throws PlayerIsAllReadyPresentException {
		assertThrows(NullPointerException.class, ()->{archive.register(null);});
	}

	@Test
	void testExistingPlayerRegistration1() throws NullPointerException, PlayerIsAllReadyPresentException {
		Player andrea = createAndrea();
		archive.register(andrea);
		assertThrows(PlayerIsAllReadyPresentException.class, ()->{archive.register(andrea);});
	}

	@Test
	void testExistingPlayerRegistration2() throws NullPointerException, PlayerIsAllReadyPresentException {
		Player andrea = createAndrea();
		archive.register(andrea);
		Player luca = createLuca();
		archive.register(luca);
		assertThrows(PlayerIsAllReadyPresentException.class, ()->{archive.register(andrea);});
	}
	
	@Test
	void testDeleteExistingPlayer() throws NullPointerException, PlayerIsAllReadyPresentException, PlayerIsNotPresentException {
		Player andrea = createAndrea();
		archive.register(andrea);
		archive.delete(andrea);
		assertFalse(archive.getPlayers().contains(andrea));
	}

	@Test
	void testDeleteNullPlayer() throws PlayerIsAllReadyPresentException, PlayerIsNotPresentException {
		assertThrows(NullPointerException.class, ()->{archive.delete(null);});
	}
	
	@Test
	void testDeleteNotExistingPlayer1() throws NullPointerException, PlayerIsAllReadyPresentException {
		Player andrea = createAndrea();
		assertThrows(PlayerIsNotPresentException.class, ()->{archive.delete(andrea);});
	}

	@Test
	void testDeleteNotExistingPlayer2() throws NullPointerException, PlayerIsAllReadyPresentException {
		Player luca = createLuca();
		archive.register(luca);
		Player andrea = createAndrea();
		assertThrows(PlayerIsNotPresentException.class, ()->{archive.delete(andrea);});
	}
	
	
	
	private Player createAndrea() {
		GregorianCalendar birthdate = new GregorianCalendar(1987, 12, 24);
		Player player = new UpperConfidenceBound("Andrea", birthdate);
		return player;
	}
	
	private Player createLuca() {
		GregorianCalendar birthdateLuca = new GregorianCalendar(1987, 7, 18);
		Player luca = new UniformExplorationPlayer("Luca", birthdateLuca, new ExplorationRate(100));
		return luca;
	}
	
}
