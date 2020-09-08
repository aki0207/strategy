package strategy;

public class Main {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Main randmeseed1 randomeseed2");
			System.out.println("Example: java Main 314 15");
			System.exit(0);
		}

		int seed1 = Integer.parseInt(args[0]);
		int seed2 = Integer.parseInt(args[1]);
		Player p1 = new Player("Jiro", new WinningStrategy(seed1));
		Player p2 = new Player("Taro", new ProbStrategy(seed2));
		for (int i = 0; i < 10000; i++) {
			Hand nexHand1 = p1.nextHand();
			Hand nexHand2 = p2.nextHand();
			if (nexHand1.isStrongerThan(nexHand2)) {
				System.out.println("Winner:" + p1);
				p1.win();
				p2.lose();
			} else if (nexHand2.isStrongerThan(nexHand1)) {
				System.out.println("Winner:" + p2);
				p1.lose();
				p2.win();
			} else {
				System.out.println("Even…");
				p1.even();
				p2.even();
			}
		}

		System.out.println("Total result:");
		System.out.println(p1);
		System.out.println(p2);

	}

}
