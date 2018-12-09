package ie.gmit.sw.ds.RMI;

import java.rmi.Naming;

public class RmiConnector {
	
	private InterfaceRMI ifRmi;
	
	public RmiConnector() throws Exception{
		ifRmi = (InterfaceRMI) Naming.lookup("rmi://127.0.0.1:1099/InterfaceRMI");
	}

}
