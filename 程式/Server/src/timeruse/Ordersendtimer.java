package timeruse;

import java.util.Date;
import java.util.TimerTask;

import storeorder.Arrange;

public class Ordersendtimer extends TimerTask {
	//@ Override
      public void run() {
	          System.out.println(" Task ����ɶ��G���u���v����" + new Date());
	          //���u���v����
	          Arrange add=new Arrange();
	         add.timeradd();
	          };
}
