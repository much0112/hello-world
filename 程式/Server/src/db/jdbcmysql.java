package db; 
 
import java.awt.Cursor;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;

import storeorder.Arrange; 
 
public class jdbcmysql { 
  private Connection con = null; //Database objects 
  //�s��object 
  private Statement stat = null; 
  //����,�ǤJ��sql������r�� 
  private ResultSet rs = null; 
  //���G�� 
  private PreparedStatement pst = null; 
  //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 
  //���Q��?�Ӱ��Х� 
  private int[] dodish=new int[5];
  
  private String dropdbSQL = "DROP TABLE User "; 
  private String dropstore1 = "DROP TABLE Store1 ";
  
  private static String test="CREATE TABLE Store1 (" + 
		    "    order1     INTEGER " + 
		    "  , order2    	INTEGER " + 
		    "  , order3  	INTEGER " +
		    "  , order4		INTEGER	)";
  
  
  private static String createdbSQL = "CREATE TABLE User (" + 
    "    id     INTEGER " + 
    "  , name    VARCHAR(20) " + 
    "  , passwd  VARCHAR(20))"; //sample
  private static String store1 = "CREATE TABLE Store1 (" + 
		    "    order1     INTEGER " + 
		    "  , order2    	INTEGER " + 
		    "  , order3  	INTEGER " +
		    "  , order4		INTEGER	)"; 
  private static String store2 = "CREATE TABLE Store2 (" + 
		    "    order1     INTEGER " + 
		    "  , order2    	INTEGER " + 
		    "  , order3  	INTEGER " +
		    "  , order4		INTEGER	)";
  private static String store3 = "CREATE TABLE Store3 (" + 
		    "    order1     INTEGER " + 
		    "  , order2    	INTEGER " + 
		    "  , order3  	INTEGER " +
		    "  , order4		INTEGER	)";
  private static String store4 = "CREATE TABLE Store4 (" + 
		    "    order1     INTEGER " + 
		    "  , order2    	INTEGER " + 
		    "  , order3  	INTEGER " +
		    "  , order4		INTEGER	)"; 
  private static String store1ordercomplete = "CREATE TABLE store1ordercomplete (" + 
		    "    order1     INTEGER " + 
		    "  , order2    	INTEGER " + 
		    "  , order3  	INTEGER " +
		    "  , order4		INTEGER	)"; 
  private static String store2ordercomplete = "CREATE TABLE store2ordercomplete (" + 
		    "    order1     INTEGER " + 
		    "  , order2    	INTEGER " + 
		    "  , order3  	INTEGER " +
		    "  , order4		INTEGER	)"; 
  private static String store3ordercomplete = "CREATE TABLE store3ordercomplete (" + 
		    "    order1     INTEGER " + 
		    "  , order2    	INTEGER " + 
		    "  , order3  	INTEGER " +
		    "  , order4		INTEGER	)"; 
  private static String store4ordercomplete = "CREATE TABLE store4ordercomplete (" + 
		    "    order1     INTEGER " + 
		    "  , order2    	INTEGER " + 
		    "  , order3  	INTEGER " +
		    "  , order4		INTEGER	)"; 
  
  
  private String insertdbSQL = "insert into User(id,name,passwd) " + 
      "select ifNULL(max(id),0)+1,?,? FROM User"; 
  private String insertstore1 = "insert into Store1(order1,order2,order3,order4) " + 
	      "value(0,0,0,0) ";
  private String update="update Store 1 set order1=order1+1";
 
  private String selectSQL = "select * from User ";
  private static String Store1="select * from Store1";
  private static String Store2="select * from Store2";
  private static String Store3="select * from Store3";
  private static String Store4="select * from Store4";
  public jdbcmysql() 
  { 
    try { 
      Class.forName("com.mysql.jdbc.Driver"); 
      //���Udriver 
      con = DriverManager.getConnection( 
      "jdbc:mysql://localhost/android_api?useUnicode=true&characterEncoding=Big5", 
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
    catch(SQLException x) { 
      System.out.println("Exception :"+x.toString()); 
    } 
    
  } 
  //�إ�table���覡 
  //�i�H�ݬ�Statement���ϥΤ覡 
  public void createTable(String store) 
  { 
    try 
    { 
      stat = con.createStatement(); 
      stat.executeUpdate(store); 
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
  //�s�W��� 
  //�i�H�ݬ�PrepareStatement���ϥΤ覡 
 /* public void insertTable( String name,String passwd) 
  { 
    try 
    { 
      pst = con.prepareStatement(insertdbSQL);      
      pst.setString(1, name); 
      pst.setString(2, passwd); 
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
  } */
  public void insertTable(String store) 
  { //insert into Store1(order1,order2,order3,order4) " + "value(0,0,0,0) "
    try 
    { 
      pst = con.prepareStatement("insert into "+store+"(order1,order2,order3,order4) value(0,0,0,0) ");      
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
  public void substrate(String store, String order, int count)//�����ݰ��\�I�n��i�w�����\�I��
  {
	  try
	  {
		 pst=con.prepareStatement("update "+store+" set "+order+" = "+order+" - "+count); 
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
  public void update(String store, String order, int count)//�N�B�z���\�I���w�����\�I��
  {
	  try
	  {
		 pst=con.prepareStatement("update "+store+" set "+order+" = "+order+" + "+count); 
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
  public void sub(String store, String order, int count)//����complete�n��i�Ȥ�ت��(�ʧ@�u������)
  {
	  try
	  {
		 pst=con.prepareStatement("update "+store+" set "+order+" = "+order+" - "+count); 
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
  
  //�R��Table, 
  //��إ�table�ܹ� 
  public void dropTable(String store) 
  { 
    try 
    { 
      stat = con.createStatement(); 
      stat.executeUpdate("DROP TABLE "+store); 
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
  //�d�߸�� 
  //�i�H�ݬݦ^�ǵ��G���Ψ��o��Ƥ覡 
  public String[] SelectTable(String str) 
  { Arrange select=new Arrange();
   String[] count=new String[10];
    try 
    { 
      stat = con.createStatement(); 
      rs = stat.executeQuery("select * from "+str); 
     // System.out.println("order1\t\torder2\t\torder3\t\torder4"); 
      while(rs.next()) 
      { 
    	 /* System.out.println(rs.getInt("order1")+"\t\t"+ 
            rs.getInt("order2")+"\t\t"+rs.getInt("order3")+"\t\t"+rs.getInt("order4"));*/
    	/*  select.readorder(rs.getString("order1"));
    	  select.readorder(rs.getString("order2"));
    	  select.readorder(rs.getString("order3"));
    	  select.readorder(rs.getString("order4"));*/
    	  count[0]=rs.getString("order1");
    	  count[1]=rs.getString("order2");
    	  count[2]=rs.getString("order3");
    	  count[3]=rs.getString("order4");
    	    	  
      } 
       return count; 
    } 
    catch(SQLException e) 
    { 
      System.out.println("DropDB Exception :" + e.toString()); 
      return count;
    } 
    finally 
    { 
      Close(); 
    } 
  }
  
  public void selectcheckcustomer(String str) 
  { 
    try 
    { 
      stat = con.createStatement(); 
      rs = stat.executeQuery("select * from "+str); 
     // System.out.println("order1\t\torder2\t\torder3\t\torder4"); 
      while(rs.next()) 
      { 
    	 /* System.out.println(rs.getInt("order1")+"\t\t"+ 
            rs.getInt("order2")+"\t\t"+rs.getInt("order3")+"\t\t"+rs.getInt("order4"));*/
    	  
    	  
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
  public int[] storecheckcomp(String str)//�T�{�����ƶq���� 
  { int[] complete=new int[10];
    try 
    { 
      stat = con.createStatement(); 
      rs = stat.executeQuery("select * from "+str); 
     // System.out.println("order1\t\torder2\t\torder3\t\torder4"); 
     
      while(rs.next()) {
    	/*System.out.println(rs.getInt("order1")+"\t\t"+ 
            rs.getInt("order2")+"\t\t"+rs.getInt("order3")+"\t\t"+rs.getInt("order4"));*/
    	 complete[0]=rs.getInt("order1");
    	 complete[1]=rs.getInt("order2");
    	 complete[2]=rs.getInt("order3");
    	 complete[3]=rs.getInt("order4");
      }
      
      return complete;
    } 
    catch(SQLException e) 
    { 
      System.out.println("DropDB Exception :" + e.toString()); 
      return complete;
    } 
    finally 
    { 
      Close(); 
    } 
  }
  public int[] store1checkorder(String str) 
  { Arrange select=new Arrange();
  int[] count=new int[10];
    try 
    { 
      stat = con.createStatement(); 
      rs = stat.executeQuery("select * from "+str); 
     // System.out.println("order1\t\torder2\t\torder3\t\torder4"); 
      while(rs.next()) 
      { 
    	/*System.out.println(rs.getInt("order1")+"\t\t"+ 
            rs.getInt("order2")+"\t\t"+rs.getInt("order3")+"\t\t"+rs.getInt("order4"));*/
    	 /*select.readtodoint(rs.getInt("order1"));
    	  select.readtodoint(rs.getInt("order2"));
    	  select.readtodoint(rs.getInt("order3"));
    	  select.readtodoint(rs.getInt("order4"));*/
    	  count[0]=rs.getInt("order1");
    	  count[1]=rs.getInt("order2");
    	  count[2]=rs.getInt("order3");
    	  count[3]=rs.getInt("order4");    	  
      }
      return count;
    } 
    catch(SQLException e) 
    { 
      System.out.println("DropDB Exception :" + e.toString()); 
      return count;
    } 
    finally 
    { 
      Close(); 
    } 
  }
  
  //����ϥΧ���Ʈw��,�O�o�n�����Ҧ�Object 
  //�_�h�b����Timeout��,�i��|��Connection poor�����p 
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
  public  void startover(){
	  
	  	dropTable("Store1");
		dropTable("Store2");
		dropTable("Store3");
		dropTable("Store4");
		dropTable("Store1ordercomplete");
		dropTable("Store2ordercomplete");
		dropTable("Store3ordercomplete");
		dropTable("Store4ordercomplete");
		createTable(store1);
		createTable(store2);
		createTable(store3);
		createTable(store4);
		createTable(store1ordercomplete);
		createTable(store2ordercomplete);
		createTable(store3ordercomplete);
		createTable(store4ordercomplete);
		insertTable("Store1");
		insertTable("Store2");
		insertTable("Store3");
		insertTable("Store4");
		insertTable("Store1ordercomplete");
		insertTable("Store2ordercomplete");
		insertTable("Store3ordercomplete");
		insertTable("Store4ordercomplete");
  }
 
  public static void main(String[] args) 
  { 	  
    //���ݬݬO�_���` 
    jdbcmysql test = new jdbcmysql();     
   //test.dropTable("Store2");
   // test.createTable(store1);
   // test.createTable(store2);
  //  test.createTable(store3);
   // test.createTable(store4);
   // test.insertTable("Store1");
   // test.insertTable("Store2");
   // test.insertTable("Store3");
   // test.insertTable("Store4");
   //test.update("Store2","order1",20);
   // test.insertTable("yku2", "7890"); 
  //test.SelectTable();
    //test.SelectTable("Store2");
    //test.substrate("Store2", "order1", 10);
    //test.substrate("Store2ordercomplete","order1",20);
    //test.store1checkorder("Store1");
    
  } 
}