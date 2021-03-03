package application;

import mvc.Controller;
import mvc.Level;
import mvc.LevelOne;

public class Main {

	public static void main(String[] args) {
		Level level = new LevelOne();
		Controller controller = new Controller(level);
	}
}