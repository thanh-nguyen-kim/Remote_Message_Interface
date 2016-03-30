package com.rmi.ri;
import java.rmi.*;
public interface Calculator extends Remote{
	//private static int _sum=0;
	public String sum() throws RemoteException;
	public void setVal(String val) throws RemoteException;
}
