package com.rmi.ri;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
	private int _sum=0,tmp=-10000;
	private String desKey="thanhthanhthanhthanh";
	private ArrayList<Listener> listeners=new ArrayList<Listener>();
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
			if(tmp!=-10000){
				System.out.println(tmp+" sum");
				this.tmp+=value;
				sum();
				
			}
			this.tmp=value;
		}
		catch(Exception e){
			System.out.println("error");
		}
	};
	@Override
	public void sum() throws RemoteException {
		// TODO Auto-generated method stub
		try{


			SecretKey key=SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(desKey.getBytes()));
			_sum+=tmp;
			tmp=-10000;
			String val=(new DesCipher(key)).encrypt(String.valueOf(_sum));
			//return val;
			for(int i=0;i<listeners.size();i++){
				if(listeners.get(i)!=null)
					listeners.get(i).CallBack(val);
			}
		}
		catch(Exception e){}
		//return "error";
	}

	@Override
	public void AddListener(Listener listener) throws RemoteException {
		// TODO Auto-generated method stub
		if(this.listeners.contains(listener)) return;
		else this.listeners.add(listener);
	}

}
