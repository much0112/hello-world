package timeruse;

import java.util.Date;
import java.util.TimerTask;

import db.jdbcmysql;

public class Store3timer extends TimerTask {
	jdbcmysql sub=new jdbcmysql();
	String order;
	int num;
	//@ Override
      public void run() {
	          System.out.println("store1���� Task ����ɶ��G" +order+ new Date());
	          //���䤤�@��order���q�� ���P�_
	          	sub.substrate("Store3", order, num);
	          	sub.update("Store3ordercomplete", order, num);
	          };
	          public void accetproder(String order,int num){
	        	  this.order=order;
	        	  this.num=num;
	          };
	          
	         
}
