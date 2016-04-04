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
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;
import com.rmi.DES.*;
import com.rmi.ri.Calculator;
import com.rmi.ri.Listener;
/**
 * @author Thanh
 *
 */
public class RMIClient implements Listener{

	/**
	 * @param args
	 */
	public static final String desKey="thanhthanhthanhthanh";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub

		boolean flag=false;
		RMIClient client=new RMIClient();
		UnicastRemoteObject.exportObject(client, 0);
		System.out.println("Attempt to connect");
		try {
			Calculator stub=null;
			//auto connect 10 times
			for(int i=0;i<10;i++){
				stub=(Calculator) Naming.lookup("rmi://localhost:2016/Calculator");
				if(stub!=null){
					System.out.println("Connect success!");
					
					stub.AddListener(client);
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
			//in.close();
			System.out.println("Press any key to attemp setvalue: ");
			System.in.read();
			//System.in.wait(1);;
			SecretKey key=SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(desKey.getBytes()));
			String val=(new DesCipher(key)).encrypt(String.valueOf(value));
			stub.setVal(val);
			flag=true;
			System.out.println("Press key A to attempt sum: ");
			//System.in.
			in.next();
			stub.sum();
			in.close();
			//System.out.println((new DesCipher(key)).decrypt(result));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void CallBack(String result) {
		// TODO Auto-generated method stub
		SecretKey key;
		try {
			key = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(desKey.getBytes()));
			System.out.println("Sum: "+(new DesCipher(key)).decrypt(result));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String result=stub.sum();
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
