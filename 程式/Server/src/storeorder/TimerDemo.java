package storeorder;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TimerDemo {

	public TimerDemo() {
		// TODO Auto-generated constructor stub
	}

	 public static void main(String[] args) {
		  
		         TimerDemo timerDemo = new TimerDemo();
		         // timerDemo.testScheduleDelay();
		         timerDemo.store1timer();
		        //  timerDemo.testScheduleDelayAndPeriod();
		        //  timerDemo.testScheduleDateAndPeriod();
		    }
	/* void testScheduleDelay(){
		  
		 java.util.Timer timer = new java.util.Timer();
		          System.out.println("Delay�G3��");
		          System.out.println("In testScheduleDelay�G" + new Date());
		  
		          // schedule(TimerTask task, long delay)
		          timer.schedule(new Timer(), 3000);		          
		         try {
		             Thread.sleep(3000);
		          }
		              catch(InterruptedException e) {
		          }		  
		          timer.cancel();
		         System.out.println("End testScheduleDelay�G"+ new Date());
		      }*/	 
	 
	 void store1timer(){
		  
		 TimerStore1 timers = new TimerStore1();
		 Timer timer=new Timer();
		          System.out.println("Delay�G3��");
		          System.out.println("In testScheduleDelay�G" + new Date());
		  
		          // schedule(TimerTask task, long delay)
		          timer.schedule(timers, 3000);		          
		         try {
		             Thread.sleep(3000);
		          }
		              catch(InterruptedException e) {
		          }		  
		          timer.cancel();
		         System.out.println("End testScheduleDelay�G"+ new Date());
		      }
	 
	 
/*	 void testScheduleDelayAndPeriod(){
		  
		 java.util.Timer timer = new java.util.Timer();
		          System.out.println("Delay�G3��, Period�G2��");
		          System.out.println("In testScheduleDelayAndPeriod�G" 
		              + new Date());
		          
		          // schedule(TimerTask task, long delay, long period)
		          timer.schedule(new Timer(), 3000, 2000);
		          
		         try {
		              Thread.sleep(10000);
		          }
		              catch(InterruptedException e) {
		          }
		  
		          timer.cancel();
		          System.out.println("End testScheduleDelayAndPeriod�G" 
		              + new Date() + "\n");
		      }
	  void testScheduleDateAndPeriod(){
		   
		  java.util.Timer timer = new java.util.Timer();
		           
		           // �]�w��Jschedule���� Date firstTime���{�b��15���
		          Calendar calendar = Calendar.getInstance();
		           calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)+15);
		           Date firstTime = calendar.getTime();
		           
		           // �]�i�� simpleDateFormat �����]�w firstTime����T�ɶ�
		           // SimpleDateFormat dateFormatter = 
		           //      new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
		           // Date firstTime = dateFormatter.parse("2011/12/25 13:30:00");
		           
		           System.out.println("In testScheduleDateAndPeriod�G" 
		               + new Date());
		           System.out.println("�]�w���� Date ��15���G" 
		               + firstTime +", Period�G10��");
		                   
		           // schedule(TimerTask task, Date firstTime, long period)
		           timer.schedule(new Timer(), firstTime, 10000);		                   
		           try {
		               Thread.sleep(30000);
		          }
		               catch(InterruptedException e) {
		          }		   
		           timer.cancel();
		           System.out.println("End testScheduleDateAndPeriod�G" 
		              + new Date() + "\n");
		       }*/
	
	
}
