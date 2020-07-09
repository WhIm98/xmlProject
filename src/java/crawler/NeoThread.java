/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import contants.DataContants;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author HP
 */
public class NeoThread extends BaseThread implements Runnable{
    private ServletContext context;

    public NeoThread(ServletContext context) {
        this.context = context;
    }

    

    @Override
    public void run() {
        while(true){
            try {
                NeoCategoriesCrawler categoriesCrawler = new NeoCategoriesCrawler(context);
                Map<String,String> categories = categoriesCrawler.getCategories(DataContants.getInstance().getNeoSportLink(context));
                for(Map.Entry<String,String> entry : categories.entrySet()){
                    Thread crawlingThread = new Thread(new NeoCrawler(context,entry.getKey(), entry.getValue()));
                    crawlingThread.start();
                    synchronized (BaseThread.getInstance()){
                        while(BaseThread.isSuspended()){
                            BaseThread.getInstance().wait();
                        }
                    }
                }
                NeoThread.sleep(TimeUnit.DAYS.toMillis(1));
                synchronized (BaseThread.getInstance()){
                    while(BaseThread.isSuspended()){
                        BaseThread.getInstance().wait();
                    }
                }
            } catch (InterruptedException e) {
                Logger.getLogger(NeoThread.class.getName()).log(Level.SEVERE,null, e);
            }
        }
    }
    
    
}
