package ru.javabegin.training.spring.impls.toshiba;

import ru.javabegin.training.spring.interfaces.Leg;

import javax.inject.Named;

@Named
public class ToshibaLeg implements Leg {

	public void go() {
		System.out.println("Go to Toshiba!");
	}

}
