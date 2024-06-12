package main;

import java.io.IOException;

import controller.Controller;

public class ControllerTest {

	public static void main(String[] args) {
		Controller c = new Controller();
		c.createFiled();
		try {
			c.start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
