package storeorder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.tools.JavaFileObject;

import db.Checkcustomer;
import db.jdbcmysql;
import timeruse.Timermanage;
public class StoreOrder {		
	static String msg;
	static String selectstr;
	static String[] selectsendstr=new String[10];//�^�ǵ��Ȥ᪺�ƭ�		
	static int[] storeorderassigment=new int[10];//�d�ߧ����\�I�n�h���U���\�I���ǰt
	static String[] order={"order1","order2","order3","order4","order5","order6","order7","order8","order9","order10",};
	static String[] number={"number1","number2","number3","number4","number5","number6","number7","number8","number9","number10"};//�U�ȫت��server�d�ߥ�
	 static String[] complete={"complete1","complete2","complete3","complete4","complete5","complete6","complete7","complete8","complete9","complete10"};//�Ȥ�ت�server�d�ߥ�
	static Thread thread1=new Thread(new Runnable(){//�����I�\��T�ç�J�w��		
		public void run(){			
		try{			
			Arrange arg=new Arrange();			
			ServerSocket server=new ServerSocket(5051);
			System.out.println("���A���Ұʩ�: "+InetAddress.getLocalHost().getHostAddress()+"."+server.getLocalPort());
			Timermanage timerDemo = new Timermanage();
 			Arrange timer=new Arrange();
 			Arrange check=new Arrange();
			while(true){
							Socket s=server.accept();
							DataInputStream in=new DataInputStream(s.getInputStream());
							 msg=in.readUTF();							
							System.out.println("�Ӧ�"+s.getInetAddress()+"�����쪺�T��: "+msg);
							arg.Arrange(msg);								
					/*		if(timer.timeradd1())//thread4
				 				timerDemo.ordersendtimer();//thread4	
							if(check.check()>0)//thread2
							 {
								check.send();
								 System.out.println("�w�e�X");
							 }		//thread2	*/							
						}
			}catch(Exception e){System.out.println("Error"+e);}	
		}
	});
	 static Thread thread2=new Thread(new Runnable(){//�N�\�I�e�X(10�U���@�ӳ��e�X,�����Q���u���v�j3�e�X)
		 public void run(){
			 try {			 
			Arrange check=new Arrange();	
		//	Timermanage timerDemo = new Timermanage();//thread4
 		//	Arrange timer=new Arrange();//thread4
			while(true)
				 { 		
					 if(check.check()>0)
					 {
						 check.send();
						 System.out.println("�w�e�X");
					 }		
				//	 if(timer.timeradd1())//thread4
			 	//			timerDemo.ordersendtimer();//thread4	
					 thread2.yield();
				 }
			 	}catch(Exception e){System.out.println("Error"+e);}
		 	}
	 });		
	 static Thread thread4=new Thread(new Runnable(){//thread4�ɶ���F�e�ܸ�Ʈw
		 	public void run(){
		 		try{
		 			Timermanage timerDemo = new Timermanage();
		 			Arrange timer=new Arrange();
		 			while(true)
		 			{	if(timer.timeradd1())
		 				timerDemo.ordersendtimer();		 				
		 			}
		 		}catch(Exception e){System.out.println("Error"+e);}
		 	}
	 });
	 static Thread thread5=new Thread(new Runnable(){//store1�����aserver(�������婱�A���i��|�P�˵�h�ƶq����@�_���B�z)
		 public void run(){
			 try {			 
				 jdbcmysql sub=new jdbcmysql();
				 Arrange send=new Arrange();
				 Timermanage time=new Timermanage();
				 Checkcustomer check=new Checkcustomer();
				 String[] str={"'Store1:order1'","'Store1:order2'","'Store1:order3'","'Store1:order4'"};
				 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq	
				 int flag=0;//���аO �C�����N�s�ӥH�W�@���t
				while(true)
				 { 	
				int i=0; 
				 store1selecttodo= sub.store1checkorder("Store1");
				 storeorderassigment=sub.storecheckcomp("store1ordercomplete"); //�o�쩱�a�������ƶq 								
					 if(store1selecttodo[0]>0)//�P�_�ƶq�j��0������
					 {
						time.store1timer(0,store1selecttodo[0],2000);							 				 
						 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
						 //�C�j1�������n20��							 
					 }
					 else if(store1selecttodo[1]>0)
					 {
						time.store1timer(1, store1selecttodo[1], 2000);							
					 }
					 if(store1selecttodo[2]>0)//�P�_�ƶq�j��0������
					 {
						time.store1timer(2,store1selecttodo[2],3000);
						 				 
						 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
						 //�C�j1�������n20��						 
					 }
					 else if(store1selecttodo[3]>0)
					 {
						time.store1timer(3, store1selecttodo[3], 4000);						
					 } 	
				 while(i<4)
				 	{											
			if(storeorderassigment[i]>0)
					 {						 
						 System.out.println("���a���\�Iorder"+i+1+" ����:"+storeorderassigment[i]);//�}�l�@���t!!!!!!!!!						 
						 for(int k=0;k<6;k++){
							storeorderassigment=sub.storecheckcomp("store1ordercomplete"); //�o�쩱�a�������ƶq  
							 //order1��order10							
							check.selectnum(str[i],order[k],number[k],complete[k],storeorderassigment[i]," store1ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
								//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
						 }					 
					 }			 					 
					 i++;
				 	}	
				 thread5.sleep(100);
				 }					
			 	}catch(Exception e){System.out.println("Error"+e);}
		 	}
	 });
	 static Thread thread6=new Thread(new Runnable(){//store2�����aserver(���ʺA���ΫK�����ۤv���@�Msop�A���_�ӹ��@�ӤH�B�z)
		 public void run(){
			 try {			 
				 jdbcmysql sub=new jdbcmysql();
				 Arrange send=new Arrange();
				 Timermanage time=new Timermanage();
				 Checkcustomer check=new Checkcustomer();
				 String[] str={"'Store2:order1'","'Store2:order2'","'Store2:order3'","'Store2:order4'"};
				 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq
				 while(true)
				 { 	
				int i=0;			
				 store1selecttodo=sub.store1checkorder("Store2");
				 storeorderassigment=sub.storecheckcomp("store2ordercomplete"); //�o�쩱�a�������ƶq 
				 while(i<4)
				 	{
					// System.out.println(store1selecttodo[i]);
					 if(store1selecttodo[i]>0)//�P�_�ƶq�j��20������
					 {
						 time.store2timer(i,store1selecttodo[i]);
						 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
						 //�C�j1�������n20��
					 }
					 if(storeorderassigment[i]>0)
					 {						 
						// System.out.println("���a���\�Iorder"+i+" �ѤU:"+storeorderassigment[i]);//�}�l�@���t!!!!!!!!!
						 for(int k=0;k<10;k++){							
							 //order1��order10	
							//System.out.println(i+"and"+storeorderassigment[i]);	
							  storeorderassigment=sub.storecheckcomp("store2ordercomplete"); //�o�쩱�a�������ƶq  
							//  System.out.println(str[i]+order[k]+number[k]+complete[k]+storeorderassigment[i]+"  store2ordercomplete");
							check.selectnum(str[i],order[k],number[k],complete[k],storeorderassigment[i]," store2ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W	
						 }					 
					 }
					 i++;
				 	}
				 thread6.sleep(100);
				 }
			 	}catch(Exception e){System.out.println("Error"+e);}
		 	}
	 });
	 static Thread thread7=new Thread(new Runnable(){//store3�����aserver
		 public void run(){
			 try {			 
				 jdbcmysql sub=new jdbcmysql();
				 Arrange send=new Arrange();
				 Timermanage time=new Timermanage();
				 Checkcustomer check=new Checkcustomer();
				 String[] str={"'Store3:order1'","'Store3:order2'","'Store3:order3'","'Store3:order4'"};
				 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq
				 while(true)
				 { 	
				int i=0;	
				 store1selecttodo= sub.store1checkorder("Store3");
				 storeorderassigment=sub.storecheckcomp("store3ordercomplete"); //�o�쩱�a�������ƶq 
				 while(i<4)
				 	{
					// System.out.println(store1selecttodo[i]);
					 if(store1selecttodo[i]>0)//�P�_�ƶq�j��20������
					 {
						 time.store3timer(i,store1selecttodo[i]);
						 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
						 //�C�j1�������n20��
						 
					 }
					 if(storeorderassigment[i]>0)
					 {						 
						 //System.out.println("���a���\�Iorder"+i+" �ѤU:"+storeorderassigment[i]);//�}�l�@���t!!!!!!!!!						 
						 for(int k=0;k<10;k++){
							storeorderassigment=sub.storecheckcomp("store3ordercomplete"); //�o�쩱�a�������ƶq  
							 //order1��order10	
							//System.out.println(i+"and"+storeorderassigment[i]);							
							check.selectnum(str[i],order[k],number[k],complete[k],storeorderassigment[i]," store3ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
							}					 
					 }
					 i++;						 
				 	}
				 thread7.sleep(100);
				 }
			 	}catch(Exception e){System.out.println("Error"+e);}
		 	}
	 });
	 static Thread thread8=new Thread(new Runnable(){//store4�����aserver
		 public void run(){
			 try {			 
				 jdbcmysql sub=new jdbcmysql();
				 Arrange send=new Arrange();
				 Timermanage time=new Timermanage();
				 Checkcustomer check=new Checkcustomer();
				 String[] str={"'Store4:order1'","'Store4:order2'","'Store4:order3'","'Store4:order4'"};
				 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq
				 int flag=0;//�C�����­ӥH�W�@���t�@��
				 while(true)
				 { 	
				int i=0;				
				 store1selecttodo=sub.store1checkorder("Store4");
				 storeorderassigment=sub.storecheckcomp("store4ordercomplete"); //�o�쩱�a�������ƶq 
				 if(flag>=6)flag=0;
				 while(i<4)
				 	{
					// System.out.println(store1selecttodo[i]);
					 if(store1selecttodo[i]>0)//�P�_�ƶq�j��20������
					 {		
						 time.store4timer(i,store1selecttodo[i]);
						 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
						 //�C�j1�������n20��
					 }
					 if(storeorderassigment[i]>5&&flag<5)//���O�Ѥ��ӥH�W�~�����t�A�C�����|���@���O�­ӥH�W�N�����t
					 {						 
						 //System.out.println("���a���\�Iorder"+i+" �ѤU:"+storeorderassigment[i]);//�}�l�@���t!!!!!!!!!						 
						 for(int k=0;k<10;k++){
							storeorderassigment=sub.storecheckcomp("store4ordercomplete"); //�o�쩱�a�������ƶq  
							 //order1��order10	
							//System.out.println(i+"and"+storeorderassigment[i]);							
							check.selectnum(str[i],order[k],number[k],complete[k],storeorderassigment[i]," store4ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
							}						 
					 }
					 else if(storeorderassigment[i]>0&&flag>=5)
					 {
						 for(int k=0;k<10;k++){
								storeorderassigment=sub.storecheckcomp("store4ordercomplete"); //�o�쩱�a�������ƶq  
								 //order1��order10	
								//System.out.println(i+"and"+storeorderassigment[i]);							
								check.selectnum(str[i],order[k],number[k],complete[k],storeorderassigment[i]," store4ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
								}	
					 }
					
					 i++;					 					 
				 	}
				 flag++;
				thread8.sleep(100);
				 }
			 	}catch(Exception e){System.out.println("Error"+e);}
		 	}
	 }); 
	 static Thread thread9=new Thread(new Runnable(){//store1�\�I1�M2�̧ǧ����P��3�M4�]�b�y(�s�p�ɾ�)
		 public void run(){
			 try {			 
				 jdbcmysql sub=new jdbcmysql();
				 Arrange send=new Arrange();
				 Timermanage time=new Timermanage();
				 Checkcustomer check=new Checkcustomer();
				 String[] str={"'Store1  -  : order1'","'Store1  -  : order2'","'Store1  -  : order3'","'Store1  -  : order4'"};
				 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq		
				
				while(true)
				 { 	
				
				 store1selecttodo= sub.store1checkorder("Store1");
				 storeorderassigment=sub.storecheckcomp("store1ordercomplete"); //�o�쩱�a�������ƶq 					
					 if(store1selecttodo[0]>0)//�P�_�ƶq�j��0������
					 {
						time.store1timer(0,store1selecttodo[0],1000);							 				 
						 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
						 //�C�j1�������n20��
						 
					 }
					 else if(store1selecttodo[1]>0)
					 {
						time.store1timer(1, store1selecttodo[1], 2000);
						
					 }
				}					 
			 	}catch(Exception e){System.out.println("Error"+e);}
		 	}
	 });
	 static Thread thread10=new Thread(new Runnable(){//store1�\�I3�M4�̧ǧ����P��1�M2�]�b�y(�s�p�ɾ�)
		 public void run(){
			 try {			 
				 jdbcmysql sub=new jdbcmysql();
				 Arrange send=new Arrange();
				 Timermanage time=new Timermanage();
				 Checkcustomer check=new Checkcustomer();
				 String[] str={"'Store1  -  : order1'","'Store1  -  : order2'","'Store1  -  : order3'","'Store1  -  : order4'"};
				 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq				 
				while(true)
				 { 	
				
				 store1selecttodo= sub.store1checkorder("Store1");
				 storeorderassigment=sub.storecheckcomp("store1ordercomplete"); //�o�쩱�a�������ƶq 					
					 if(store1selecttodo[2]>0)//�P�_�ƶq�j��0������
					 {
						time.store1timer(2,store1selecttodo[2],3000);
						 				 
						 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
						 //�C�j1�������n20��						 
					 }
					 else if(store1selecttodo[3]>0)
					 {
						time.store1timer(3, store1selecttodo[3], 4000);						
					} 		
				 	}					 
			 	}catch(Exception e){System.out.println("Error"+e);}
		 	}
	 });
 static Thread threadsample=new Thread(new Runnable(){//store1�\�I3�M4�̧ǧ����P��1�M2�]�b�y(�s�p�ɾ�)
	 public void run(){
		 System.out.println("Here is the starting point of Thread.");
		int i=1;
	        for (;;) { // infinite loop to print message
	        	i++;
	            System.out.println("User Created Thread1    " +i);
	    //    threadsample.yield();
	        }
	 }});
 static Thread threadsample2=new Thread(new Runnable(){//store1�\�I3�M4�̧ǧ����P��1�M2�]�b�y(�s�p�ɾ�)
	 public void run(){
		 System.out.println("Here is the starting point of Thread.");
		int i=1;
	        for (;;) { // infinite loop to print message
	        	i++;
	            System.out.println("User Created Thread2    " +i);
	      //  threadsample2.yield();
	        }
	 }});
 static Thread threadsample3=new Thread(new Runnable(){//store1�\�I3�M4�̧ǧ����P��1�M2�]�b�y(�s�p�ɾ�)
	 public void run(){
		 System.out.println("Here is the starting point of Thread.");
		int i=1;
	        for (;;) { // infinite loop to print message
	        	i++;
	            System.out.println("User Created Thread3    " +i);
	      //  threadsample2.yield();
	        }
	 }});
 static Thread threadsample4=new Thread(new Runnable(){//store1�\�I3�M4�̧ǧ����P��1�M2�]�b�y(�s�p�ɾ�)
	 public void run(){
		 System.out.println("Here is the starting point of Thread.");
		int i=1;
	        for (;;) { // infinite loop to print message
	        	i++;
	            System.out.println("User Created Thread4    " +i);
	      //  threadsample2.yield();
	        }
	 }});
 static Thread threadsample5=new Thread(new Runnable(){//store1�\�I3�M4�̧ǧ����P��1�M2�]�b�y(�s�p�ɾ�)
	 public void run(){
		 System.out.println("Here is the starting point of Thread.");
		int i=1;
	        for (;;) { // infinite loop to print message
	        	i++;
	            System.out.println("User Created Thread5    " +i);
	      //  threadsample2.yield();
	        }
	 }});
 static Thread threadtest1=new Thread(new Runnable(){//store1�����aserver(�������婱�A���i��|�P�˵�h�ƶq����@�_���B�z)
	 public void run(){
		 try {			 
			 jdbcmysql sub=new jdbcmysql();
			 Arrange send=new Arrange();
			 Timermanage time=new Timermanage();
			 Checkcustomer check=new Checkcustomer();
			 String[] str={"'Store1:order1'","'Store1:order2'","'Store1:order3'","'Store1:order4'"};
			 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq				
			 int[] order_fix=new int[4];//�C�˵榳�L�d��
			int[] order_number_fix=new int[4];//�C�˽��T�w�ƶq���P
			 int[] order_fix_position=new int[4];//�C�˵�d�b���P��m
			while(true)
			 { 				
			 store1selecttodo= sub.store1checkorder("Store1");
			 storeorderassigment=sub.storecheckcomp("store1ordercomplete"); //�o�쩱�a�������ƶq 								
				 if(store1selecttodo[0]>0)//�P�_�ƶq�j��0������
				 {
					time.store1timer(0,store1selecttodo[0],2000);							 				 
					 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
					 //�C�j1�������n20��							 
				 }
				 else if(store1selecttodo[1]>0)
				 {
					time.store1timer(1, store1selecttodo[1], 2000);							
				 }
				 if(store1selecttodo[2]>0)//�P�_�ƶq�j��0������
				 {
					time.store1timer(2,store1selecttodo[2],3000);
					 				 
					 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
					 //�C�j1�������n20��						 
				 }
				 else if(store1selecttodo[3]>0)
				 {
					time.store1timer(3, store1selecttodo[3], 4000);						
				 } 				 										
		if(storeorderassigment[0]>0)//�@���\���t
				 {						 
					 System.out.println("���a���\�Iorder1 "+" ����:"+storeorderassigment[0]);//�}�l�@���t!!!!!!!!!						 
					 for(int k=order_fix_position[0];k<6;k++){
						 if(order_fix[0]==0)
						 {
						storeorderassigment=sub.storecheckcomp("store1ordercomplete"); //�o�쩱�a�������ƶq  
						 //order1��order10							
						order_number_fix[0]=check.selectnum(str[0],order[k],number[k],complete[k],storeorderassigment[0]," store1ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
							//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
						 	if(order_number_fix[0]>0)
						 			{
						 				order_fix_position[0]=k;//�аO�d�b���@��column
						 				order_fix[0]=1;//�аO�d��
						 				System.out.print("���Ȥ�Q���L �w��w");
						 				break;
						 			}
						 	if(order_number_fix[0]==0&&order_fix_position[0]==5)
						 	{
						 		order_fix_position[0]=0;
						 	}
						 }
						 else
						 {
							 if(storeorderassigment[0]>=order_number_fix[0])
							 {
								 check.selectnum2(str[0],order[k],number[k],complete[k],storeorderassigment[0]," store1ordercomplete ");
								 order_fix[0]=0;
								 order_number_fix[0]=0;
								 System.out.println("�ӫȤ�w�����\�I");
							 }
							 else
							 {
								 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
								 break;
							 }
						 }						 
					}		
			 	}
		if(storeorderassigment[1]>0)//�G���\���t
		 {						 
			 System.out.println("���a���\�Iorder2 "+" ����:"+storeorderassigment[1]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[1];k<6;k++){
				 if(order_fix[1]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store1ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[1]=check.selectnum(str[1],order[k],number[k],complete[k],storeorderassigment[1]," store1ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[1]>0)
				 			{
				 				order_fix_position[1]=k;//�аO�d�b���@��column
				 				order_fix[1]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[1]==0&&order_fix_position[1]==5)
				 	{
				 		order_fix_position[1]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[1]>=order_number_fix[1])
					 {
						 check.selectnum2(str[1],order[k],number[k],complete[k],storeorderassigment[1]," store1ordercomplete ");
						 order_fix[1]=0;
						 order_number_fix[1]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}		
	 	}
		if(storeorderassigment[2]>0)//�T���\���t
		 {						 
			 System.out.println("���a���\�Iorder3 "+" ����:"+storeorderassigment[2]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[2];k<6;k++){
				 if(order_fix[2]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store1ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[2]=check.selectnum(str[2],order[k],number[k],complete[k],storeorderassigment[2]," store1ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[2]>0)
				 			{
				 				order_fix_position[2]=k;//�аO�d�b���@��column
				 				order_fix[2]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[2]==0&&order_fix_position[2]==5)
				 	{
				 		order_fix_position[2]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[2]>=order_number_fix[2])
					 {
						 check.selectnum2(str[2],order[k],number[k],complete[k],storeorderassigment[2]," store1ordercomplete ");
						 order_fix[2]=0;
						 order_number_fix[2]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}			
	 	}	
		if(storeorderassigment[3]>0)//�|���\���t
		 {						 
			 System.out.println("���a���\�Iorder4 "+" ����:"+storeorderassigment[3]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[3];k<6;k++){
				 if(order_fix[3]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store1ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[3]=check.selectnum(str[3],order[k],number[k],complete[k],storeorderassigment[3]," store1ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[3]>0)
				 			{
				 				order_fix_position[3]=k;//�аO�d�b���@��column
				 				order_fix[3]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[3]==0&&order_fix_position[3]==5)
				 	{
				 		order_fix_position[3]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[3]>=order_number_fix[3])
					 {
						 check.selectnum2(str[3],order[k],number[k],complete[k],storeorderassigment[3]," store1ordercomplete ");
						 order_fix[3]=0;
						 order_number_fix[3]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}		
	 	}	
			 threadtest1.sleep(100);
			 }					
		 	}catch(Exception e){System.out.println("Error"+e);}
	 	}
 }
  );
 static Thread threadtest2=new Thread(new Runnable(){//store2�����aservertest
	 public void run(){
		 try {			 
			 jdbcmysql sub=new jdbcmysql();
			 Arrange send=new Arrange();
			 Timermanage time=new Timermanage();
			 Checkcustomer check=new Checkcustomer();
			 String[] str={"'Store2:order1'","'Store2:order2'","'Store2:order3'","'Store2:order4'"};
			 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq				
			 int[] order_fix=new int[4];//�C�˵榳�L�d��
			int[] order_number_fix=new int[4];//�C�˽��T�w�ƶq���P
			 int[] order_fix_position=new int[4];//�C�˵�d�b���P��m
			
			while(true)
			 { 	
				int i=0;			
			 store1selecttodo= sub.store1checkorder("Store2");
			 storeorderassigment=sub.storecheckcomp("store2ordercomplete"); //�o�쩱�a�������ƶq 								
			 while(i<4)
			 	{
				// System.out.println(store1selecttodo[i]);
				 if(store1selecttodo[i]>0)//�P�_�ƶq�j��20������
				 {
					 time.store2timer(i,store1selecttodo[i]);
					 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
					 //�C�j1�������n20��
				 }
				 i++;
			 	}
			 
		if(storeorderassigment[0]>0)//�@���\���t
				 {						 
					 System.out.println("���a2���\�Iorder1 "+" ����:"+storeorderassigment[0]);//�}�l�@���t!!!!!!!!!						 
					 for(int k=order_fix_position[0];k<6;k++){
						 if(order_fix[0]==0)
						 {
						storeorderassigment=sub.storecheckcomp("store2ordercomplete"); //�o�쩱�a�������ƶq  
						 //order1��order10							
						order_number_fix[0]=check.selectnum(str[0],order[k],number[k],complete[k],storeorderassigment[0]," store2ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
							//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
						 	if(order_number_fix[0]>0)
						 			{
						 				order_fix_position[0]=k;//�аO�d�b���@��column
						 				order_fix[0]=1;//�аO�d��
						 				System.out.print("���Ȥ�Q���L �w��w");
						 				break;
						 			}
						 	if(order_number_fix[0]==0&&order_fix_position[0]==5)
						 	{
						 		order_fix_position[0]=0;
						 	}
						 }
						 else
						 {
							 if(storeorderassigment[0]>=order_number_fix[0])
							 {
								 check.selectnum2(str[0],order[k],number[k],complete[k],storeorderassigment[0]," store2ordercomplete ");
								 order_fix[0]=0;
								 order_number_fix[0]=0;
								 System.out.println("�ӫȤ�w�����\�I");
							 }
							 else
							 {
								 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
								 break;
							 }
						 }						 
					}		
			 	}
		if(storeorderassigment[1]>0)//�G���\���t
		 {						 
			 System.out.println("���a2���\�Iorder2 "+" ����:"+storeorderassigment[1]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[1];k<6;k++){
				 if(order_fix[1]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store2ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[1]=check.selectnum(str[1],order[k],number[k],complete[k],storeorderassigment[1]," store2ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[1]>0)
				 			{
				 				order_fix_position[1]=k;//�аO�d�b���@��column
				 				order_fix[1]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[1]==0&&order_fix_position[1]==5)
				 	{
				 		order_fix_position[1]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[1]>=order_number_fix[1])
					 {
						 check.selectnum2(str[1],order[k],number[k],complete[k],storeorderassigment[1]," store2ordercomplete ");
						 order_fix[1]=0;
						 order_number_fix[1]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}		
	 	}
		if(storeorderassigment[2]>0)//�T���\���t
		 {						 
			 System.out.println("���a2���\�Iorder3 "+" ����:"+storeorderassigment[2]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[2];k<6;k++){
				 if(order_fix[2]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store2ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[2]=check.selectnum(str[2],order[k],number[k],complete[k],storeorderassigment[2]," store2ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[2]>0)
				 			{
				 				order_fix_position[2]=k;//�аO�d�b���@��column
				 				order_fix[2]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[2]==0&&order_fix_position[2]==5)
				 	{
				 		order_fix_position[2]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[2]>=order_number_fix[2])
					 {
						 check.selectnum2(str[2],order[k],number[k],complete[k],storeorderassigment[2]," store2ordercomplete ");
						 order_fix[2]=0;
						 order_number_fix[2]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}			
	 	}	
		if(storeorderassigment[3]>0)//�|���\���t
		 {						 
			 System.out.println("���a2���\�Iorder4 "+" ����:"+storeorderassigment[3]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[3];k<6;k++){
				 if(order_fix[3]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store2ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[3]=check.selectnum(str[3],order[k],number[k],complete[k],storeorderassigment[3]," store2ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[3]>0)
				 			{
				 				order_fix_position[3]=k;//�аO�d�b���@��column
				 				order_fix[3]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[3]==0&&order_fix_position[3]==5)
				 	{
				 		order_fix_position[3]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[3]>=order_number_fix[3])
					 {
						 check.selectnum2(str[3],order[k],number[k],complete[k],storeorderassigment[3]," store2ordercomplete ");
						 order_fix[3]=0;
						 order_number_fix[3]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}		
	 	}	
			 threadtest2.sleep(100);
			 }					
		 	}catch(Exception e){System.out.println("Error"+e);}
	 	}
	 }
	 );
 static Thread threadtest3=new Thread(new Runnable(){//store3�����aservertest
	 public void run(){
		 try {			 
			 jdbcmysql sub=new jdbcmysql();
			 Arrange send=new Arrange();
			 Timermanage time=new Timermanage();
			 Checkcustomer check=new Checkcustomer();
			 String[] str={"'Store3:order1'","'Store3:order2'","'Store3:order3'","'Store3:order4'"};
			 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq				
			 int[] order_fix=new int[4];//�C�˵榳�L�d��
			int[] order_number_fix=new int[4];//�C�˽��T�w�ƶq���P
			 int[] order_fix_position=new int[4];//�C�˵�d�b���P��m
			
			while(true)
			 { 	
				int i=0;			
			 store1selecttodo= sub.store1checkorder("Store3");
			 storeorderassigment=sub.storecheckcomp("store3ordercomplete"); //�o�쩱�a�������ƶq 								
			 while(i<4)
			 	{
				// System.out.println(store1selecttodo[i]);
				 if(store1selecttodo[i]>0)//�P�_�ƶq�j��20������
				 {
					 time.store3timer(i,store1selecttodo[i]);
					 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
					 //�C�j1�������n20��
				 }
				 i++;
			 	}
			 
		if(storeorderassigment[0]>0)//�@���\���t
				 {						 
					 System.out.println("���a2���\�Iorder1 "+" ����:"+storeorderassigment[0]);//�}�l�@���t!!!!!!!!!						 
					 for(int k=order_fix_position[0];k<6;k++){
						 if(order_fix[0]==0)
						 {
						storeorderassigment=sub.storecheckcomp("store3ordercomplete"); //�o�쩱�a�������ƶq  
						 //order1��order10							
						order_number_fix[0]=check.selectnum(str[0],order[k],number[k],complete[k],storeorderassigment[0]," store3ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
							//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
						 	if(order_number_fix[0]>0)
						 			{
						 				order_fix_position[0]=k;//�аO�d�b���@��column
						 				order_fix[0]=1;//�аO�d��
						 				System.out.print("���Ȥ�Q���L �w��w");
						 				break;
						 			}
						 	if(order_number_fix[0]==0&&order_fix_position[0]==5)
						 	{
						 		order_fix_position[0]=0;
						 	}
						 }
						 else
						 {
							 if(storeorderassigment[0]>=order_number_fix[0])
							 {
								 check.selectnum2(str[0],order[k],number[k],complete[k],storeorderassigment[0]," store3ordercomplete ");
								 order_fix[0]=0;
								 order_number_fix[0]=0;
								 System.out.println("�ӫȤ�w�����\�I");
							 }
							 else
							 {
								 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
								 break;
							 }
						 }						 
					}		
			 	}
		if(storeorderassigment[1]>0)//�G���\���t
		 {						 
			 System.out.println("���a2���\�Iorder2 "+" ����:"+storeorderassigment[1]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[1];k<6;k++){
				 if(order_fix[1]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store3ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[1]=check.selectnum(str[1],order[k],number[k],complete[k],storeorderassigment[1]," store3ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[1]>0)
				 			{
				 				order_fix_position[1]=k;//�аO�d�b���@��column
				 				order_fix[1]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[1]==0&&order_fix_position[1]==5)
				 	{
				 		order_fix_position[1]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[1]>=order_number_fix[1])
					 {
						 check.selectnum2(str[1],order[k],number[k],complete[k],storeorderassigment[1]," store3ordercomplete ");
						 order_fix[1]=0;
						 order_number_fix[1]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}		
	 	}
		if(storeorderassigment[2]>0)//�T���\���t
		 {						 
			 System.out.println("���a2���\�Iorder3 "+" ����:"+storeorderassigment[2]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[2];k<6;k++){
				 if(order_fix[2]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store3ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[2]=check.selectnum(str[2],order[k],number[k],complete[k],storeorderassigment[2]," store3ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[2]>0)
				 			{
				 				order_fix_position[2]=k;//�аO�d�b���@��column
				 				order_fix[2]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[2]==0&&order_fix_position[2]==5)
				 	{
				 		order_fix_position[2]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[2]>=order_number_fix[2])
					 {
						 check.selectnum2(str[2],order[k],number[k],complete[k],storeorderassigment[2]," store3ordercomplete ");
						 order_fix[2]=0;
						 order_number_fix[2]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}			
	 	}	
		if(storeorderassigment[3]>0)//�|���\���t
		 {						 
			 System.out.println("���a2���\�Iorder4 "+" ����:"+storeorderassigment[3]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[3];k<6;k++){
				 if(order_fix[3]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store3ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[3]=check.selectnum(str[3],order[k],number[k],complete[k],storeorderassigment[3]," store3ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[3]>0)
				 			{
				 				order_fix_position[3]=k;//�аO�d�b���@��column
				 				order_fix[3]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[3]==0&&order_fix_position[3]==5)
				 	{
				 		order_fix_position[3]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[3]>=order_number_fix[3])
					 {
						 check.selectnum2(str[3],order[k],number[k],complete[k],storeorderassigment[3]," store3ordercomplete ");
						 order_fix[3]=0;
						 order_number_fix[3]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}		
	 	}	
			 threadtest3.sleep(100);
			 }					
		 	}catch(Exception e){System.out.println("Error"+e);}
	 	}
	 }
	 );
 static Thread threadtest4=new Thread(new Runnable(){//store2�����aservertest
	 public void run(){
		 try {			 
			 jdbcmysql sub=new jdbcmysql();
			 Arrange send=new Arrange();
			 Timermanage time=new Timermanage();
			 Checkcustomer check=new Checkcustomer();
			 String[] str={"'Store4:order1'","'Store4:order2'","'Store4:order3'","'Store4:order4'"};
			 int[] store1selecttodo=new int[10];//���a���d���I�\���ƶq				
			 int[] order_fix=new int[4];//�C�˵榳�L�d��
			int[] order_number_fix=new int[4];//�C�˽��T�w�ƶq���P
			 int[] order_fix_position=new int[4];//�C�˵�d�b���P��m
			
			while(true)
			 { 	
				int i=0;			
			 store1selecttodo= sub.store1checkorder("Store4");
			 storeorderassigment=sub.storecheckcomp("store4ordercomplete"); //�o�쩱�a�������ƶq 								
			 while(i<4)
			 	{
				// System.out.println(store1selecttodo[i]);
				 if(store1selecttodo[i]>0)//�P�_�ƶq�j��20������
				 {
					 time.store4timer(i,store1selecttodo[i]);
					 //�I�s�p�ɾ��}�l��//�p�ɾ��h�O��20�[20
					 //�C�j1�������n20��
				 }
				 i++;
			 	}
			 
		if(storeorderassigment[0]>0)//�@���\���t
				 {						 
					 System.out.println("���a4���\�Iorder1 "+" ����:"+storeorderassigment[0]);//�}�l�@���t!!!!!!!!!						 
					 for(int k=order_fix_position[0];k<6;k++){
						 if(order_fix[0]==0)
						 {
						storeorderassigment=sub.storecheckcomp("store4ordercomplete"); //�o�쩱�a�������ƶq  
						 //order1��order10							
						order_number_fix[0]=check.selectnum(str[0],order[k],number[k],complete[k],storeorderassigment[0]," store4ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
							//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
						 	if(order_number_fix[0]>0)
						 			{
						 				order_fix_position[0]=k;//�аO�d�b���@��column
						 				order_fix[0]=1;//�аO�d��
						 				System.out.print("���Ȥ�Q���L �w��w");
						 				break;
						 			}
						 	if(order_number_fix[0]==0&&order_fix_position[0]==5)
						 	{
						 		order_fix_position[0]=0;
						 	}
						 }
						 else
						 {
							 if(storeorderassigment[0]>=order_number_fix[0])
							 {
								 check.selectnum2(str[0],order[k],number[k],complete[k],storeorderassigment[0]," store4ordercomplete ");
								 order_fix[0]=0;
								 order_number_fix[0]=0;
								 System.out.println("�ӫȤ�w�����\�I");
							 }
							 else
							 {
								 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
								 break;
							 }
						 }						 
					}		
			 	}
		if(storeorderassigment[1]>0)//�G���\���t
		 {						 
			 System.out.println("���a4���\�Iorder2 "+" ����:"+storeorderassigment[1]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[1];k<6;k++){
				 if(order_fix[1]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store4ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[1]=check.selectnum(str[1],order[k],number[k],complete[k],storeorderassigment[1]," store4ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[1]>0)
				 			{
				 				order_fix_position[1]=k;//�аO�d�b���@��column
				 				order_fix[1]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[1]==0&&order_fix_position[1]==5)
				 	{
				 		order_fix_position[1]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[1]>=order_number_fix[1])
					 {
						 check.selectnum2(str[1],order[k],number[k],complete[k],storeorderassigment[1]," store4ordercomplete ");
						 order_fix[1]=0;
						 order_number_fix[1]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}		
	 	}
		if(storeorderassigment[2]>0)//�T���\���t
		 {						 
			 System.out.println("���a4���\�Iorder3 "+" ����:"+storeorderassigment[2]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[2];k<6;k++){
				 if(order_fix[2]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store4ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[2]=check.selectnum(str[2],order[k],number[k],complete[k],storeorderassigment[2]," store4ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[2]>0)
				 			{
				 				order_fix_position[2]=k;//�аO�d�b���@��column
				 				order_fix[2]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[2]==0&&order_fix_position[2]==5)
				 	{
				 		order_fix_position[2]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[2]>=order_number_fix[2])
					 {
						 check.selectnum2(str[2],order[k],number[k],complete[k],storeorderassigment[2]," store4ordercomplete ");
						 order_fix[2]=0;
						 order_number_fix[2]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}			
	 	}	
		if(storeorderassigment[3]>0)//�|���\���t
		 {						 
			 System.out.println("���a4���\�Iorder4 "+" ����:"+storeorderassigment[3]);//�}�l�@���t!!!!!!!!!						 
			 for(int k=order_fix_position[3];k<6;k++){
				 if(order_fix[3]==0)
				 {
				storeorderassigment=sub.storecheckcomp("store4ordercomplete"); //�o�쩱�a�������ƶq  
				 //order1��order10							
				order_number_fix[3]=check.selectnum(str[3],order[k],number[k],complete[k],storeorderassigment[3]," store4ordercomplete ");//�⧹�����\�I(store1ordercomplete)�����[��Ȥ�ت��W
					//str store:1:order1 order order1 number �ƶq complete�ݧ����ƶq   storeorderassigment�������ƶq
				 	if(order_number_fix[3]>0)
				 			{
				 				order_fix_position[3]=k;//�аO�d�b���@��column
				 				order_fix[3]=1;//�аO�d��
				 				System.out.print("���Ȥ�Q���L �w��w");
				 				break;
				 			}
				 	if(order_number_fix[3]==0&&order_fix_position[3]==5)
				 	{
				 		order_fix_position[3]=0;
				 	}
				 }
				 else
				 {
					 if(storeorderassigment[3]>=order_number_fix[3])
					 {
						 check.selectnum2(str[3],order[k],number[k],complete[k],storeorderassigment[3]," store4ordercomplete ");
						 order_fix[3]=0;
						 order_number_fix[3]=0;
						 System.out.println("�ӫȤ�w�����\�I");
					 }
					 else
					 {
						 System.out.println("������ӫȤ᮳���\�I�~�|�~��U�h");
						 break;
					 }
				 }						 
			}		
	 	}	
			 threadtest4.sleep(100);
			 }					
		 	}catch(Exception e){System.out.println("Error"+e);}
	 	}
	 }
	 );
	 public static void main(String[] args)
	{	//jdbcmysql test = new jdbcmysql();
		//test.update("Store2","order1",20);
		//test.startover();
		thread1.start();
		thread2.start();			
		thread4.start();
	//	thread5.start();
	//	thread6.start();
		//thread7.start();
		//thread8.start();
		threadtest1.start();
		threadtest2.start();
		threadtest3.start();
		threadtest4.start();

	}  
}  


