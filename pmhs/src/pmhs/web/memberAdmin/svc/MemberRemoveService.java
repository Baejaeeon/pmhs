package pmhs.web.memberAdmin.svc;

import static pmhs.db.JdbcUtil.*;

import java.sql.Connection;

import pmhs.web.memberAdmin.dao.MemberAdminDAO;


public class MemberRemoveService {

   public boolean removeMember(String[] removeIdArray) {
      // TODO Auto-generated method stub
      Connection con = getConnect();
      MemberAdminDAO memberAdminDAO = MemberAdminDAO.getInstance();
      memberAdminDAO.setConnection(con); // Ä¿³Ø¼Ç ÁÖÀÔ
      boolean deleteSuccess = false;
      int deleteCount = memberAdminDAO.deleteMember(removeIdArray);
      if(deleteCount > 0){
         deleteSuccess = true;
         commit(con);
      }
      else{
         rollback(con);
      }
      return deleteSuccess;
   }
}