package timeruse;

import java.util.Date;
import java.util.TimerTask;

import db.jdbcmysql;

public class Store4timer extends TimerTask {
	jdbcmysql sub=new jdbcmysql();
	String order;
	int num;
	//@ Override
      public void run() {
	          System.out.println("store1���� Task ����ɶ��G" +order+ new Date());
	          //���䤤�@��order���q�� ���P�_
	          	sub.substrate("Store4", order, num);
	          	sub.update("Store4ordercomplete", order, num);
	          };
	          public void accetproder(String order, int num){
	        	  this.order=order;
	        	  this.num=num;
	          };
	          
	         
}