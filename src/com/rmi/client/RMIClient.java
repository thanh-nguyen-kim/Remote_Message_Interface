/**
 * 
 */
package com.rmi.client;
import java.rmi.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import com.rmi.DES.*;
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
		String desKey="thanhthanhthanhthanh";
		boolean flag=false;
		System.out.println("Attempt to connect");
		try {
			Calculator stub=null;
			//auto connect 10 times
			for(int i=0;i<10;i++){
				stub=(Calculator) Naming.lookup("rmi://localhost:2016/Calculator");
				if(stub!=null){
					System.out.println("Connect success!");
					break;
				}
			}
			if(stub==null){
				System.out.println("Connect Unsuccess! Press any key to quit.");
				System.in.read();
				return;
			}
			Scanner in = new Scanner(System.in);
			System.out.println("Enter value: ");
			int value = in.nextInt();
			System.out.println("Press any key to attemp setvalue: ");
			System.in.read();
			SecretKey key=SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(desKey.getBytes()));
			String val=(new DesCipher(key)).encrypt(String.valueOf(value));
			stub.setVal(val);
			flag=true;
			System.out.println("Press any key to attemp sum: ");
			System.in.read();
			String result=stub.sum();
			System.out.println((new DesCipher(key)).decrypt(result));
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
