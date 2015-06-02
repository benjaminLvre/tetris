package com.polytech.stfu.jeu;

public class Chrono extends Thread{
	private long temps;
	private boolean pause;
	private boolean fin;
	private long tempsCourant;
	private Object lockFin;
	private Object lockTemps;
	private Object lockPause;
	private Object lockTempsCourant;
	
	public Chrono(){
		super();
		lockFin = new Object();
		lockTemps = new Object();
		lockPause = new Object();
		lockTempsCourant = new Object();
	}
	
	public void run(){
		synchronized (lockTemps) {
			temps = 0;
		}
		synchronized (lockFin) {
			fin = false;
		}
		synchronized (lockPause) {
			pause = false;
		}
		synchronized (lockTempsCourant) {
			tempsCourant = System.currentTimeMillis();
		}
		while(!fin){
			while(pause){
				yield();
			}
			try {
				sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lockPause) {
				synchronized (lockTemps) {
					temps += System.currentTimeMillis() - tempsCourant;
				}
				synchronized (lockTempsCourant) {
					tempsCourant = System.currentTimeMillis();
				}
			}
		}
	}
	
	public void pause(){
		synchronized (lockPause){
			pause = true;
		}
	}
	
	public void restart(){
		synchronized (lockPause) {
			synchronized (lockTempsCourant) {
				tempsCourant = System.currentTimeMillis();
			}
			pause = false;
		}
	}
	
	public void end(){
		synchronized (lockFin) {
			fin = true;
		}
	}
	
	public int getTemps(){
		return (int)(temps/1000);
	}
}
