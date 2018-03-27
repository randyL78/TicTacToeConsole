/**
 * TestPlayers
 * Class to test Player classes
 * @author Randy Layne
 */
public class TestPlayers {

  public static void main(String[] args) {
    Player player1 = new Player("Human", "Ranbo");
    System.out.println(player1);

    ComputerPlayer player2 = new ComputerPlayer(1);
    System.out.println(player2);
    System.out.println(player2.getName() + " wins!");

    HumanPlayer player3 = new HumanPlayer("Randy", 5, 2, 12);
    System.out.println(player3);

    HumanPlayer player4 = new HumanPlayer();
    player4.addWin();
    System.out.println(player4);

    Players players = new Players(player2, player3);
    players.flip();
    System.out.println(players.players[players.currentPlayer()]);
    players.flip();
    System.out.println(players.getCurrentName());
    players.replace(player4, players.currentPlayer());
    System.out.println(players.getCurrentName());
  }
}