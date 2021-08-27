//****************************************************************************************
// File:          PLSQLQueries.java
// Description:   Demo of PLSQL Queries with Java Access Provider
//                Describes how to execute SELECT query statement through the PL/SQL Gateway
//****************************************************************************************



import ifs.fnd.ap.Record;
import ifs.fnd.ap.RecordAttribute;
import ifs.fnd.ap.PlsqlCommand;
import ifs.fnd.ap.PlsqlSelectCommand;
import ifs.fnd.ap.PlsqlBaseMethodCommand;
import ifs.fnd.ap.PlsqlBaseMethodAction;
import ifs.fnd.ap.PlsqlBaseMethodType;
import ifs.fnd.ap.RecordCollection;
import ifs.fnd.ap.Server;
import ifs.fnd.util.Str;

import java.util.Scanner;

import ifs.fnd.ap.APException;

public abstract class PLSQLQueries {

   public static void main(String [] args) {

      try {

         // if(pars.length != 3) {
         //    System.out.println("Syntax : java PLSQLQueries [connectstring] [identity] [password]");
         //    System.out.println();
         //    return;
         // }

         Scanner stringInput= new Scanner(System.in);

         System.out.print("Connection URL:port :");
         String connectionUrl = stringInput.nextLine();
         System.out.print("Username : ");
         String username = stringInput.nextLine();
         System.out.print("Password :");
         String password = stringInput.nextLine();
         
         stringInput.close();
         // Create a server and invoke server
         Server srv = new Server();
         
         srv.setConnectionString(connectionUrl);
         srv.setCredentials(username,password);
         
         
         PlsqlSelectCommand cmd = new PlsqlSelectCommand(srv, "SELECT * FROM FND_USER WHERE DESCRIPTION LIKE :DESC");
         cmd.getBindVariables().add("DESC", "A%");
         System.out.println();
         System.out.println("Invoking FndPLSQLSelectCommand..." );
         RecordCollection result = cmd.executeQuery();
         System.out.println("Invoke done!" );
         System.out.println();
         
         if(result!=null){
            System.out.println("Users");
            System.out.println("======");
            for(int i = 0; i < result.size(); i++){
               System.out.println((String)result.get(i).findValue("DESCRIPTION"));
            }
         } 
         else
            System.out.println("No Users found!");
         System.out.println();
      } 
      catch(APException err) {
         err.printStackTrace(System.out);
      }
   }
}