/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class BaseThread extends Thread{

    protected BaseThread() {
    }
    private static BaseThread instance;
    private final static Object LOCK = new Object();
    
    public static BaseThread getInstance(){
        synchronized (LOCK){
            if(instance==null){
                instance = new BaseThread();
            }
        }
        return instance;
    }
    
    private static boolean suspended = false;

    public static boolean isSuspended() {
        return suspended;
    }

    public static void setSuspended(boolean aSuspended) {
        suspended = aSuspended;
    }
    
    public void suspendThread(){
        setSuspended(true);
        System.out.println("suspended");
    }
    
    public synchronized void resumeThread(){
        setSuspended(false);
        notifyAll();
        System.out.println("resume");
    }

    
    
    
}
