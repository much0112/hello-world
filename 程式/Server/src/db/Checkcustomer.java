package db;

import java.awt.Cursor;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;

import storeorder.Arrange;
public class Checkcustomer {//�Ȥᵥ�ݤ��t
	jdbcmysql sub=new jdbcmysql();
	
	 private Connection con = null; //Database objects 
	 private Connection con1=null;
	  //�s��object 
	  private Statement stat = null;
	  private Statement stat1=null;
	  //����,�ǤJ��sql������r�� 
	 
	  private ResultSet rs = null; 
	 
	  //���G�� 
	  private PreparedStatement pst = null; 
	  //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m
	  private static String[] test1={"account","order1","number1","complete1"};
	  private static String test="CREATE TABLE customer (" + 
			    "    account     varchar(20) " + 
			    "  , order1    varchar(20) " + 
			    "  , number1  	INTEGER " +
			    "  , complete1		INTEGER	"+
			    "	, order2    varchar(20) " + 
			    "  , number2  	INTEGER " +
			    "  , complete2		INTEGER "+
			    "	, order3    varchar(20) " + 
			    "  , number3  	INTEGER " +
			    "  , complete3		INTEGER "+
			    "	, order4    varchar(20) " + 
			    "  , number4  	INTEGER " +
			    "  , complete4		INTEGER "+
			    "	, order5    varchar(20) " + 
			    "  , number5  	INTEGER " +
			    "  , complete5		INTEGER "+
			    "	, order6    varchar(20) " + 
			    "  , number6  	INTEGER " +
			    "  , complete6		INTEGER "+
			    "	, order7    varchar(20) " + 
			    "  , number7  	INTEGER " +
			    "  , complete7		INTEGER "+
			    ")";
	  
	  private static String insterttest = "insert into customer(account,order1,number1,complete1,order2,number2,complete2,order3"+""
	  		+ ",number3,complete3) " + 
		      "value('abcde','store1_order3',2,0,'store2_order2',3,0,'store1_order4',1,0) ";
	  private static String insterttest1 = "insert into customer("+test1+") " + 
			      "value('abcde','store1_order3',2,0) ";
	  private static String instert="insert into customer("+""+")";//�q�o��}�l��A�Ȥ�ݳ̯ӳB�z�L����̧O���A������ƶq���n
	  public void createTable(String str) 
	  { 
	    try 
	    { 
	      stat = con.createStatement(); 
	      stat.executeUpdate(str); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("CreateDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	    	
	      Close();
	    } 
	  } 
	  public void insertTable(String str) 
	  { //insert into Store1(order1,order2,order3,order4) " + "value(0,0,0,0) "
	    try 
	    { 
	      pst = con.prepareStatement(str);      
	      pst.executeUpdate(); 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("InsertDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    } 
	  } 
	/*  public void selectnum(String ord,String order ,String num,String com,int storecom,String subcom){//��M�U���I�\�ƶq�M�ݧ����ƶq
		Checkcustomer add=new Checkcustomer();
			//  int i=0;														//ord=store1-order1,store-order2...//num=number1,number2...//com=complete1,complete2...				
		      String[] tmp1=new String[10];									//order �Oorder1-order10
		      String[] tmp2=new String[10];									//storecom�O���a�����ƶq(order1��order4)
		      tmp1=ord.split(":");
		     // System.out.println(tmp1[0]);
		      tmp2=tmp1[1].split("'");			      
		  try 																//subcom�O���w���ӧ������ex.store1ordercomplete
		    {   			 	
			  stat = con.createStatement();			  
			  rs = stat.executeQuery("SELECT id,"+num+","+com +" FROM customertable WHERE "+order+"="+ord);//�o��order1�n��(�Oorder1��order10)��ord
			//  System.out.println("SELECT id,"+num+","+com +" FROM customertable WHERE "+order+"="+ord);	
			  while(rs.next()) 
		      { 	System.out.println("���b�j����Ʈw�����t.......");	    	  
		    	// System.out.print(rs.getInt(num)+"\t\t"+rs.getInt(com)+"\n");
		    //	 if(rs.getInt(num)>rs.getInt(com)&&storecom>=rs.getInt(num))
		      if(rs.getInt(num)>rs.getInt(com))//���U�ȬO���ݶ񺡪���
		    	 {		   
		    		 if(storecom>=rs.getInt(num))//�ݤ��t�����\�I�j�󵥩�ӫȤ��\�I�ƶq
		    		 { sub.sub(subcom, tmp2[0],rs.getInt(num));
		    		//System.out.println(subcom);
		    		add.update("customertable",com,rs.getInt(num),order,ord,rs.getInt("id")); //update �f�tselect
		    		 //System.out.println(tmp2[0]+"���n�G"+storecom+"�M��n�"+rs.getInt(num));
		    		 //�n�⦩����storecomlete�A�[�^�h
		    		 //System.out.println(rs.getInt("id"));		
		    		}
		    		 
		    		 //else if{�аO���L���L�o��}{ �����æ^�ǲĤ@�Ӹ������ƶq �٦����аO�u�ਫ�L�o�̤@��}
		    		 
		    		 //else if (�������\�I�ܹs){�N�����^�ǹs�F}
		    	 }
		      }
			  //�^�Ǹ��L�Ȥ᪺�\�I�ƶq	�Y��n�����N�^�ǹs    
		    } 
		    catch(SQLException e) 
		    { 
		      System.out.println("DropDB Exception :" + e.toString()); 
		   
		    } 
		    finally 
		    { 
		    	
		      Close(); 
		      
		    } 
	  }*/
	  public int selectnum(String ord,String order ,String num,String com,int storecom,String subcom){//��M�U���I�\�ƶq�M�ݧ����ƶq
			Checkcustomer add=new Checkcustomer();
				//  int i=0;														//ord=store1-order1,store-order2...//num=number1,number2...//com=complete1,complete2...				
			      String[] tmp1=new String[10];									//order �Oorder1-order10
			      String[] tmp2=new String[10];									//storecom�O���a�����ƶq(order1��order4)
			      tmp1=ord.split(":");
			     // System.out.println(tmp1[0]);
			      tmp2=tmp1[1].split("'");
			      int order_number_fix=0;
			      int flag=0;
			  try 																//subcom�O���w���ӧ������ex.store1ordercomplete
			    {   			 	
				  stat = con.createStatement();			  
				  rs = stat.executeQuery("SELECT id,"+num+","+com +" FROM customertable WHERE "+order+"="+ord);//�o��order1�n��(�Oorder1��order10)��ord
				//  System.out.println("SELECT id,"+num+","+com +" FROM customertable WHERE "+order+"="+ord);	
				  while(rs.next()) 
			      { 	System.out.println("���b�j����Ʈw�����t.......");	    	
			      if(rs.getInt(num)>rs.getInt(com))//���U�ȬO���ݶ񺡪���
			    	 {		   
			    		 if(storecom>=rs.getInt(num))//�ݤ��t�����\�I�j�󵥩�ӫȤ��\�I�ƶq
			    		 { 
			    			 if(storecom-rs.getInt(num)>=0)
			    			 { 			    			 
			    			 			sub.sub(subcom, tmp2[0],rs.getInt(num));			    	
			    			 				add.update("customertable",com,rs.getInt(num),order,ord,rs.getInt("id")); //update �f�tselect
			    			 					//System.out.println(tmp2[0]+"���n�G"+storecom+"�M��n�"+rs.getInt(num));
			    			 					//�n�⦩����storecomlete�A�[�^�h			    		
			    			 					System.out.println(rs.getInt("id"));
			    			 					storecom=storecom-rs.getInt(num);
			    			 }
			    		}
			    		 
			    		else if(flag==0)
			    		{
			    			order_number_fix=rs.getInt(num);//�J��Ĥ@�ӳQ���L���Ȥ�O��æ^�ǥL���ƶq
			    			flag=1;//�аO�b�����o�^��e�����|�A�^��o
			    		}			    		 
			    		 
			    	 }
			      }
				  return order_number_fix;
				  //�^�Ǹ��L�Ȥ᪺�\�I�ƶq	�Y��n�����N�^�ǹs    
			    } 
			    catch(SQLException e) 
			    { 
			      System.out.println("DropDB Exception :" + e.toString()); 
			      return 0;			   
			    } 
			    finally 
			    { 
			    	
			      Close(); 
			      
			    } 
		  }
	  //�U�����d��ɪ����t
	  public int selectnum2(String ord,String order ,String num,String com,int storecom,String subcom){//��M�U���I�\�ƶq�M�ݧ����ƶq
			Checkcustomer add=new Checkcustomer();
				//  int i=0;														//ord=store1-order1,store-order2...//num=number1,number2...//com=complete1,complete2...				
			      String[] tmp1=new String[10];									//order �Oorder1-order10
			      String[] tmp2=new String[10];									//storecom�O���a�����ƶq(order1��order4)
			      tmp1=ord.split(":");
			     // System.out.println(tmp1[0]);
			      tmp2=tmp1[1].split("'");	
			      int flag=0;//�аO���L����ӫȤ� 0�L  1��
			  try 																//subcom�O���w���ӧ������ex.store1ordercomplete
			    {   			 	
				  stat = con.createStatement();			  
				  rs = stat.executeQuery("SELECT id,"+num+","+com +" FROM customertable WHERE "+order+"="+ord);//�o��order1�n��(�Oorder1��order10)��ord
				//  System.out.println("SELECT id,"+num+","+com +" FROM customertable WHERE "+order+"="+ord);	
				  while(rs.next()) 
			      { 	System.out.println("2���b�j����Ʈw�����t.......");	    	  
			    	// System.out.print(rs.getInt(num)+"\t\t"+rs.getInt(com)+"\n");
			    //	 if(rs.getInt(num)>rs.getInt(com)&&storecom>=rs.getInt(num))
			      if(rs.getInt(num)>rs.getInt(com))//���U�ȬO���ݶ񺡪���
			    	 {		   
			    		 if(storecom>=rs.getInt(num))//�ݤ��t�����\�I�j�󵥩�ӫȤ��\�I�ƶq
			    		 { 
			    			 sub.sub(subcom, tmp2[0],rs.getInt(num));
			    		//System.out.println(subcom);
			    		add.update("customertable",com,rs.getInt(num),order,ord,rs.getInt("id")); //update �f�tselect
			    		 //System.out.println(tmp2[0]+"���n�G"+storecom+"�M��n�"+rs.getInt(num));
			    		 //�n�⦩����storecomlete�A�[�^�h
			    		 //System.out.println(rs.getInt("id"));	
			    		
			    		flag= 1;//��ܤ���
			    		break;
			    		}
			    		 else
			    		 {
			    			 flag=0;
			    			 break;
			    			//�Y�S����N���X  
			    		 }	    	
			    	 }
			      }
				  return flag;
				  //�^�Ǹ��L�Ȥ᪺�\�I�ƶq	�Y��n�����N�^�ǹs    
			    } 
			    catch(SQLException e) 
			    { 
			      System.out.println("DropDB Exception :" + e.toString()); 
			   return 0;
			    } 
			    finally 
			    { 
			    	
			      Close(); 
			      
			    } 
		  }
	  
	  public void update(String store, String com, int count,String order,String ordername,int id)//�N�B�z���\�I���w�����\�I��
	  {				//store���W�Acom��b�����ت��ƶq(complete1-complete10)�Acount�n�[�bcomplete���ƶq
		  try			//order�O���w���order1order2order3....order10�Aordername���w���W�r
		  {
			 pst=con.prepareStatement("update customertable  set "+com+" = "+com+" + "+count+" WHERE "+order+"="+ordername+" and id="+id); 
			 pst.executeUpdate(); 
		  }
		  catch(SQLException e)
		  {
			  System.out.println("Update Exception: "+e.toString());
		  }
		  finally
		  {
			  Close();
		  }
	  }	  
	    
	  
	public Checkcustomer() 
	  { 
	    try { 
	      Class.forName("com.mysql.jdbc.Driver"); 
	      //���Udriver 
	      con = DriverManager.getConnection( 
	      "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5", 
	      "root","12345");
	      con1 = DriverManager.getConnection( 
	    	      "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5", 
	    	      "root","12345");
	      //���oconnection	 
	//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
	//localhost�O�D���W,test�Odatabase�W
	//useUnicode=true&characterEncoding=Big5�ϥΪ��s�X      
	    } 
	    catch(ClassNotFoundException e) 
	    { 
	      System.out.println("DriverClassNotFound :"+e.toString()); 
	    }//���i��|����sqlexception 
	    catch(SQLException x){ 
	      System.out.println("Exception :"+x.toString()); 
	    }
	    } 
	private void Close() 
	  { 
	    try 
	    { 
	      if(rs!=null) 
	      { 
	        rs.close();
	        rs = null; 
	      } 
	      if(stat!=null) 
	      { 
	        stat.close(); 
	        stat = null; 
	      } 
	      if(pst!=null) 
	      { 
	        pst.close(); 
	        pst = null; 
	      } 
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("Close Exception :" + e.toString()); 
	    } 
	  }
	
	 public void select(){//��M�U���I�\�ƶq�M�ݧ����ƶq			   
			  try 																//subcom�O���w���ӧ������ex.store1ordercomplete
			    {   			 
				 	
				  stat = con.createStatement();			  
				  rs = stat.executeQuery("SELECT * FROM `human`");//�o��order1�n��(�Oorder1��order10)��ord
				//  System.out.println("SELECT id,"+num+","+com +" FROM customertable WHERE "+order+"="+ord);	
				  while(rs.next()) 
			      { 	System.out.println("���Ӷ�");	    	  
			    	 System.out.print(rs.getString("situation")+"\t\t"+rs.getString("seat")+"\n");
			    	 
			      }	
			    
			    } 
			    catch(SQLException e) 
			    { 
			      System.out.println("DropDB Exception :" + e.toString()); 
			   
			    } 
			    finally 
			    { 
			    	
			      Close(); 
			      
			    } 
		  }
	public static void main(String[] args) 
	  { 	  
	   Checkcustomer a=new Checkcustomer();
	    //a.createTable(test);
	  //  a.insertTable(insterttest1);
	  //  a.SelectTable("customer");
		//a.selectnum("'Store2  -  : order3'", "order1","number1","complete1",3,"store2ordercomplete");
	  // a.update("customertable","complete1",4,"order1","'Store1  -  : order1'",4);
	   a.select();
	
	  }

}
