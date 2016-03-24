package com.rmi.ri;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorRemote extends UnicastRemoteObject 
implements Calculator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CalculatorRemote() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int sum(int x, int y) throws RemoteException {
		// TODO Auto-generated method stub
		return x+y;
	}

}
