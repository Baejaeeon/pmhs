package pmhs.web.memberAdmin.action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.memberAdmin.svc.MemberRemoveService;

public class MemberRemoveAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
      request.setCharacterEncoding("UTF-8");
      String[] removeIdArray = request.getParameterValues("delete1");
      MemberRemoveService memberRemoveService = new MemberRemoveService();
      
      boolean removeSuccess = memberRemoveService.removeMember(removeIdArray);
      ActionForward forward = null;
      if(removeSuccess){
         forward = new ActionForward();
         forward.setUrl("memberList.mema");
      }
      else{
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('삭제실패')");
         out.println("history.back();");
         out.println("</script>");
      }
      return forward;
   }

}