package strategy;

import java.util.Random;

public class ProbStrategy implements Strategy {
	private Random random;
	private int prevHandValue = 0;
	private int currentHandValue = 0;
	// 過去の勝敗を反映した確率計算のための表
	//[前回に出した手][今回出す手]
	private int[][] history = {
			{1,1,1,},
			{1,1,1,},
			{1,1,1,},
	};

	public ProbStrategy(int seed) {
		random = new Random(seed);
	}

	/**
	 * historyの値から確率で計算を行う
	 * 例えば、history[0][0]の値が3、history[0][1]の値が5、history[0][2]の値が7の場合
	 * グー、チョキ、パーを出す割合を3:5:7として次の手を決定する。
	 * 0以上15未満(15は3+5+7の合計値)の乱数値を得て、
	 * 0以上3未満ならグー
	 * 3以上8未満ならチョキ
	 * 8以上15未満ならパー
	 * とする
	 */
	@Override
	public Hand nextHand() {
		int bet = random.nextInt(getSum(currentHandValue));
		int handValue = 0;
		if (bet < history[currentHandValue][0]) {
			handValue = 0;
		} else if (bet < history[currentHandValue][0] + history[currentHandValue][1]) {
			handValue = 1;
		} else {
			handValue = 2;
		}

		prevHandValue = currentHandValue;
		currentHandValue = handValue;
		return Hand.getHand(handValue);
	}

	private int getSum(int hv) {
		int sum = 0;
		for (int i = 0; i < 3; i++) {
			sum += history[hv][i];
		}
		return sum;
	}

	/**
	 * nextHandメソッドで返した手の勝敗を元に、historyフィールドの内容を更新する
	 *
	 */
	@Override
	public void study(boolean win) {
		if (win) {
			history[prevHandValue][currentHandValue]++;
		} else {
			history[prevHandValue][(currentHandValue + 1) % 3]++;
			history[prevHandValue][(currentHandValue + 2) % 3]++;
		}
	}
}
