package event;

public class GreenhouseControls {
	public static void main(String[] args) {
		GreenhouseController gc = new GreenhouseController();
		System.out.println("start");
		gc.addEvent(gc.new Bell(900000000));
		Event[] eventList = { gc.new LightOn(0) };
		gc.addEvent(gc.new Restart(2000000000, eventList));
		gc.run();
	}
}
