package com.rmi.ri;
import java.rmi.*;
public interface Calculator extends Remote{
	public int sum(int x,int y) throws RemoteException;
}
