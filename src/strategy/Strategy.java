package strategy;

public interface Strategy {
	// 次回出す手を決定する
	public abstract Hand nextHand();
	// 前回出した手で勝ったかどうかを学習する
	public abstract void study(boolean win);
}
