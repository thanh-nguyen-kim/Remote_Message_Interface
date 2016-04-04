/**
 * 
 */
package com.rmi.ri;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Thanh
 *
 */
public interface Listener extends Remote {
public void CallBack(String result) throws RemoteException;
}
