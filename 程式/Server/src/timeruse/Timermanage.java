package timeruse;

import java.util.Date;
import java.util.Timer;

import storeorder.TimerDemo;
import storeorder.TimerStore1;

public class Timermanage {
	String[] order={"order1","order2","order3","order4"};
	 public static void main(String[] args) {		  
		 Timermanage timerDemo = new Timermanage();
         // timerDemo.testScheduleDelay();
        // timerDemo.store1timer();
        //  timerDemo.testScheduleDelayAndPeriod();
        //  timerDemo.testScheduleDateAndPeriod();
    }
	public  void store1timer(int i,int j,int time){//�W�L20�ӭp�ɶ}�l��		  
		 Store1timer timers = new Store1timer();
		 Timer timer=new Timer();
		      //    System.out.println("Delay�G3��");
		          //System.out.println("In testScheduleDelay�G" + new Date());		  
		          // schedule(TimerTask task, long delay)
		 		timers.accetproder(order[i], j);
		 		System.out.println("Store1��"+order[i]+"�n������"+j);
		          timer.schedule(timers, j*time);		          
		         try {
		             Thread.sleep(j*(time+100));//sleep�ɶ��n�񵥫�(schedule)���A�T�O��p�ɧ���		             
		          }
		              catch(InterruptedException e) {
		          }		  
		          timer.cancel();
		        System.out.println("�����@�����G"+ new Date());
		      }
	public  void store2timer(int i,int j){//�W�L20�ӭp�ɶ}�l��
		  
		 Store2timer timers = new Store2timer();
		 Timer timer=new Timer();
		      //    System.out.println("Delay�G3��");
		        //  System.out.println("In testScheduleDelay�G" + new Date());
		  
		          // schedule(TimerTask task, long delay)
		 		timers.accetproder(order[i],j);
		 		System.out.println("Store2��"+order[i]+"�n������"+j);
		          timer.schedule(timers, j*1000);		          
		         try {
		             Thread.sleep(j*1100);
		          }
		              catch(InterruptedException e) {
		          }		  
		          timer.cancel();
		         System.out.println("End testScheduleDelay�G"+ new Date());
		      }
	public void store3timer(int i,int j){//�W�L20�ӭp�ɶ}�l��
		  
		 Store3timer timers = new Store3timer();
		 Timer timer=new Timer();
		      //    System.out.println("Delay�G3��");
		        //  System.out.println("In testScheduleDelay�G" + new Date());
		  
		          // schedule(TimerTask task, long delay)
		 		timers.accetproder(order[i],j);
		          timer.schedule(timers, j*1000);		 
		          System.out.println("Store3��"+order[i]+"�n������"+j);
		         try {
		             Thread.sleep(j*1100);
		          }
		              catch(InterruptedException e) {
		          }		  
		          timer.cancel();
		         System.out.println("End testScheduleDelay�G"+ new Date());
		      }
	public void store4timer(int i,int j){//�W�L20�ӭp�ɶ}�l��
		  
		 Store4timer timers = new Store4timer();
		 Timer timer=new Timer();
		      //    System.out.println("Delay�G3��");
		        //  System.out.println("In testScheduleDelay�G" + new Date());
		  
		          // schedule(TimerTask task, long delay)
		 		timers.accetproder(order[i],j);
		          timer.schedule(timers, j*1000);	
		          System.out.println("Store4��"+order[i]+"�n������"+j);
		         try {
		             Thread.sleep(j*1100);
		          }
		              catch(InterruptedException e) {
		          }		  
		          timer.cancel();
		         System.out.println("End testScheduleDelay�G"+ new Date());
		      }
	
	public void ordersendtimer(){//�p�ɨ��u���v�[���e�ܸ�Ʈw
		 Ordersendtimer timers = new Ordersendtimer();
		 Timer timer=new Timer();
		          //System.out.println("Delay�G3��");
		          System.out.println("�p�ɭn�e�X�G" + new Date());
		  
		           //schedule(TimerTask task, long delay)
		          timer.schedule(timers, 10000);		          
		         try {
		             Thread.sleep(10000);
		          }
		              catch(InterruptedException e) {
		          }		  
		          timer.cancel();
		         System.out.println("�p�ɮɶ���G"+ new Date());
		 
	 }

}
