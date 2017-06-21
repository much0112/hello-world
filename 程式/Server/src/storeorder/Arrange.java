package storeorder;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import db.jdbcmysql;

public class Arrange {
	 String[] strcom=new String[50];	
	 String[] Store={"","Store1","Store2","Store3","Store4"};
	 String[] order={"","order1","order2","order3","order4"};
	static int priority[][]=new int [5][5];
	static int count[][]=new int[5][5];
	static String[] select=new String[10];//�Ȥ�d�߼ƶq
	 static int[] selecttoinform=new int[10];//���a�d�߼ƶq
	int str=0;//���a����
	int ord=0;//�\�I����
	int judge=0;//�P�_�ƶq�D���u���v��
	int selectnum=0;//�Ȥ�d��order�}�C���в�(�C���I�s���|�k�s)
	
	int ordercomplete=0;//���a�d���I�\�ƶq
	public synchronized  void Arrange(String msg) {
		// TODO Auto-generated method stub
		//strcom=msg.split("  -  : ");
		strcom=msg.split(":");
			if(strcom[0].equals(Store[1]))
			{		
				if(strcom[1].equals(order[1]))
					count[1][1]++;					
				else if(strcom[1].equals(order[2]))
					count[1][2]++;
				else if(strcom[1].equals(order[3]))
					count[1][3]++;
				else
					count[1][4]++;
				
			}
			if(strcom[0].equals(Store[2]))
			{	
				if(strcom[1].equals(order[1]))
					count[2][1]++;				
				else if(strcom[1].equals(order[2]))
					count[2][2]++;
				else if(strcom[1].equals(order[3]))
					count[2][3]++;
				else
					count[2][4]++;	
			}
			if(strcom[0].equals(Store[3]))
			{	
				if(strcom[1].equals(order[1]))
					count[3][1]++;
				else if(strcom[1].equals(order[2]))
					count[3][2]++;
				else if(strcom[1].equals(order[3]))
					count[3][3]++;
				else
					count[3][4]++;
			}
			if(strcom[0].equals(Store[4]))
			{	
				if(strcom[1].equals(order[1]))
					count[4][1]++;
				else if(strcom[1].equals(order[2]))
					count[4][2]++;
				else if(strcom[1].equals(order[3]))
					count[4][3]++;
				else
					count[4][4]++;
			}			
	}
	public  synchronized  int check(){
		
		
		for( str=1;str<=4;str++)
		{	
			
			for( ord=1;ord<=4;ord++)
			{
					if(count[str][ord]>=10)
					{					
						for(int k=1;k<=4;k++)//���F��*10���e�X�A�~�|���u���v�A�i�@�B
							for(int h=1;h<=4;h++)
								{
									if(count[k][h]>=1&&count[k][h]<10)//
										{
											priority[k][h]++;
										}
								}
						judge=1;
						//count[str][ord]=0;
						return 1;
					}
					else if(priority[str][ord]>=3)
					{
						//priority[str][ord]=0;
						judge=2;
						return 1;
					}
				
			}
		}
		return 0;
	}
	public synchronized void send(){
		//count[str][ord]�ݭn�e�X���priority[str][ord]�n�e�X�A���P�_��e�X(�F�Ѥ@�U��Ʈw�K�[)
		jdbcmysql send=new jdbcmysql();
		send.update(Store[str], order[ord], count[str][ord]);
		if(judge==1)
		{
		count[str][ord]=0;
		priority[str][ord]=0;
		judge=0;
		}
		else
		{
			count[str][ord]=0;
			priority[str][ord]=0;
			judge=0;
		}
	}
	public synchronized void readorder(String num){
		if(selectnum==0){
			select[selectnum]=num;
			selectnum++;
			}		
		else {
			
			select[selectnum]=num;
			selectnum++;
		}
		//System.out.println(select[selectnum]);		
		}
	public synchronized String[] selectsend(){//�^�ǫȤ�լd��T
		return select;
	};
	public synchronized void timeradd(){
		for(int k=1;k<=4;k++)//���F��*10���e�X�A�~�|���u���v�A�i�@�B
			for(int h=1;h<=4;h++)
				{
					if(count[k][h]>=1&&count[k][h]<10)//
						{
							priority[k][h]=3;
						}
				}
		
	};
	public synchronized boolean timeradd1(){//���F��^�ǵ�storeorder���p�ɾ��}�l�p��
		for(int k=1;k<=4;k++)//���F��*10���e�X�A�~�|���u���v�A�i�@�B
			for(int h=1;h<=4;h++)
				{
					if(count[k][h]>=1&&count[k][h]<10)//
						{
							return true;
						}
				}
		return false;
		
	};
	public synchronized void readtodoint(int num)//�s��store �άOcomplete���ƶq
	{
		//System.out.println(num);
		if(ordercomplete==0){
			selecttoinform[ordercomplete]=num;			
			ordercomplete++;
			}	
		
		else {
			
			selecttoinform[ordercomplete]=num;
			ordercomplete++;
			//System.out.println(selecttoinform[ordercomplete]);
		}
		
		
	}
	public synchronized int[] returnordertodo(){//Ū��store �άOcomplete���ƶq
		ordercomplete=0;
		return selecttoinform;
	}
	
	
	
	
	
}
