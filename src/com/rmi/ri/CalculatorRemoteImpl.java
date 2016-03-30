package com.rmi.ri;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;

import com.rmi.DES.DesCipher;

public class CalculatorRemoteImpl extends UnicastRemoteObject 
implements Calculator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _sum=0,tmp=0;
	private String desKey="thanhthanhthanhthanh";
	public CalculatorRemoteImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setVal(String val) throws RemoteException {
		try{
			SecretKey key=SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(desKey.getBytes()));
			String _val=(new DesCipher(key)).decrypt(val);
			int value=Integer.valueOf(_val);
			this.tmp=value;
		}
		catch(Exception e){
			System.out.println("error");
		}
	};
	@Override
	public String sum() throws RemoteException {
		// TODO Auto-generated method stub
		try{


			SecretKey key=SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(desKey.getBytes()));
			_sum+=tmp;
			String val=(new DesCipher(key)).encrypt(String.valueOf(_sum));
			return val;
		}
		catch(Exception e){}
		return "error";
	}

}
