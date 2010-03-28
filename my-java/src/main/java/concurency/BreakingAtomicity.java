package concurency;

public class BreakingAtomicity {

	int value = 0;

	public int getValue() {
		return value;
	}

	// synchronization doesn't provide atomicity - a new thread was able to step
	// into between first and second increment
	synchronized void increaseValue() {
		value++;
		value++;
	}

	public static void main(String[] args) {

		final BreakingAtomicity ba = new BreakingAtomicity();

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					if ((ba.value % 2) != 0) {
						System.err.println(ba.getValue());
						System.exit(1);
					}
				}
			}
		}).start();

		while (true) {
			ba.increaseValue();
		}

	}

}
