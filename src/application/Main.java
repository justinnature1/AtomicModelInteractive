package application;

import mvc.Controller;
import mvc.Experiment;
import mvc.Experiment1;

public class Main {

	public static void main(String[] args) {
		Experiment level = new Experiment1();
		Controller controller = new Controller(level);
	}
}