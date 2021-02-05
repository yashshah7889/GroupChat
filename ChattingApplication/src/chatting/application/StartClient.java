package chatting.application;

import java.util.Scanner;

public class StartClient{
    
    public static void main(String [] args){
        	   try{
        		   
        	        System.out.println("Hello new user enter your name:");
        	    	Scanner sc = new Scanner(System.in);
        			String user=sc.next();
                        User1 u=new User1(user);   
                        Thread t1=new Thread(u);
                        t1.start();
	   }catch(Exception e){e.printStackTrace();}
        
    }
}