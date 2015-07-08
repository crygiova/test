package test;

import java.util.Observable;

public class SimCalObservable extends Observable {

	public SimCalObservable() {
		super();
	}
	
	public void setChanged() {
		super.setChanged();
		this.notifyObservers();
	}
}
