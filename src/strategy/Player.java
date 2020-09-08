package strategy;

public class Player {
	private String name;
	private Strategy strategy;
	private int winCount;
	private int loseCount;
	private int gameCount;

	public Player(String name, Strategy strategy) {
		this.name = name;
		this.strategy = strategy;
	}

	public Hand nextHand() {
		// 実際に次の手を決定するのは自分の「戦略」
		return strategy.nextHand();
	}

	public void win() {
		// 戦略の内部状態を変化させる
		strategy.study(true);
		winCount++;
		gameCount++;
	}

	public void lose() {
		// 戦略の内部状態を変化させる
		strategy.study(false);
		loseCount++;
		gameCount++;
	}

	public void even() {
		gameCount++;
	}

	public String toString() {
		return "[" + name + ":" + gameCount + " games, " + winCount + " win, " +
	loseCount + " lose" + "]";
	}

}
