package pmhs.web.member.svc;

import static pmhs.db.JdbcUtil.*;


import java.sql.Connection;
import java.util.ArrayList;

import pmhs.web.member.dao.MemberDAO;
import pmhs.web.member.vo.Zipcode;

public class ZipSearchService {

   public ArrayList<Zipcode> getZipSearchData(String dong) {
      // TODO Auto-generated method stub
      Connection con = getConnect();
      MemberDAO memberDAO = MemberDAO.getInstance();
      memberDAO.setConnection(con);
      
      ArrayList<Zipcode> zipSearchList = memberDAO.selectZipcodeList(dong);
      
      close(con);
      
      return zipSearchList;
   }
   }