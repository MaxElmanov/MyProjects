package ru.javabegin.training.spring.impls.toshiba;

import ru.javabegin.training.spring.interfaces.Head;

import javax.inject.Named;

@Named
public class ToshibaHead implements Head {

	public void calc() {
		System.out.println("Thinking about Toshiba...");
	}

}
