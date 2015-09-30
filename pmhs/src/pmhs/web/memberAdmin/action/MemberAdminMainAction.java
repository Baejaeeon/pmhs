package pmhs.web.memberAdmin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;

public class MemberAdminMainAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      ActionForward forward = new ActionForward();
      forward.setUrl("/member/memberAdminMain.jsp");
      
      return forward;
   }

}