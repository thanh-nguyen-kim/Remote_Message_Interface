/**
 * 
 */
package com.rmi.client;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.rmi.ri.Calculator;
/**
 * @author Thanh
 *
 */
public class RMIClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String host="";
		try {
			Calculator stub=(Calculator) Naming.lookup("rmi://localhost:2016/Calculator");
			System.out.println(stub.sum(2,3));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
