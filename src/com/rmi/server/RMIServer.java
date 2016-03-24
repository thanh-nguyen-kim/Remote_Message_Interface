/**
 * 
 */
package com.rmi.server;
import java.rmi.*;
import com.rmi.ri.*;
import java.rmi.registry.*;
/**
 * @author Thanh
 *
 */
public class RMIServer {
	public static void main(String arg[]){
		try {
			System.out.println("Attempt to start Server. :)");
			LocateRegistry.createRegistry(2016);
			Calculator stub=new CalculatorRemote();
			Naming.rebind("rmi://localhost:2016/Calculator", stub);
			//			Registry registry=LocateRegistry.getRegistry();
			//			registry.bind("Calc", stub);

			System.out.println("Server is ready. :)");
		}
		catch(Exception e){e.printStackTrace();}
	}
}