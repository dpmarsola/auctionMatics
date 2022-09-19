package br.com.auctionMatics.main;

public class Teste extends Thread{
	
	String msg;
	Integer sleeptime;
	
	Teste(String msg, Integer stime){
		this.msg = msg;
		this.sleeptime = stime;
	}
	
	public void run() {
		
		try {
			Thread.sleep(sleeptime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("Hello World Via Classe" + "... " + msg);
		

		
	}

}
