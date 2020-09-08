package strategy;

import java.util.Random;

public class WinningStrategy implements Strategy{
	private Random random;
	private boolean won = false;
	private Hand prevHand;

	public WinningStrategy(int seed) {
		random = new Random(seed);
	}

	/**
	 * 前回勝っていれば同じ手を出し、負けていればランダムで手を決定する
	 */
	@Override
	public Hand nextHand() {
		if (!won) {
			prevHand = Hand.getHand(random.nextInt(3));
		}
		return prevHand;
	}

	@Override
	public void study(boolean win) {
		won = win;
	}
}
