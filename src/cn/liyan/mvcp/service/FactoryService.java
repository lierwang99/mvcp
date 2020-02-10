package cn.liyan.mvcp.service;

public class FactoryService {
	public static UserService getUserService() {
		return new UserServiceImpl();
	}
	public static  boolean isBlankSpace(String arg) {
		for(int x=0;x<arg.length();x++) {
			char c = arg.charAt(x);
			String single = String.valueOf(c);
			if( ! " ".equals(single)){
				return false ;
			}	
		}
		return true;
	}
}
