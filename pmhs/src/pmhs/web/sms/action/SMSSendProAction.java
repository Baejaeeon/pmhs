package pmhs.web.sms.action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.sms.android.server.Message;
import pmhs.web.sms.android.server.Result;
import pmhs.web.sms.android.server.Sender;
import org.json.simple.parser.ParseException;
import pmhs.web.sms.gcm.server.GCMTest;
import pmhs.web.sms.svc.SMSSendProService;
public class SMSSendProAction implements Action {
	private static final String REGISTRATION_ID = "APA91bE5HsLhd7RMFlaOrTaR_ntLlz9Tu6cta90iVLddPhMHfOi22qHN98ujp4-fzALilE-rk89kBVQHHz80Qt_k6bbFP5KVOV58uEsH5-8iGkiauU5wuX9BDElCXGhHfUuNNvgpPEIX";//registration Id �Է�
	private static final String APIKEY = "AIzaSyA4qGGT0X6Atccat8yfWz2YyiWSaIQuLoE"; //GCM���� �߱޹��� ApiKey�� �Է�
    //org/json/simple/parser/
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int pcSitNum = Integer.parseInt(request.getParameter("pcSitNum"));
		 try {
	            String sendTlt = "CU_PMHS";
	            String sendMsg = "Message";
	  
	            Sender sender = new Sender(APIKEY);
	             
	            /**
	             * message Build �κп��� addData�� �߰��� ���� ���ø����̼���
	             * onMessage(context, intent)���� Intent�� ���޵Ǹ�
	             * intent.getExtras().getString("title")���·� ���� ��� �����ϴ�.
	             */
	            Message message = new Message.Builder()
	            .addData("title", URLEncoder.encode(sendTlt, "UTF-8"))
	            .addData("msg", URLEncoder.encode(sendMsg, "UTF-8"))
	            .build();
	  
	            //�߼��� �޽���, �߼��� Ÿ��(RegistrationId, Retry Ƚ��)
	            Result result = sender.send(message, REGISTRATION_ID, 3);
	            if (result.getMessageId() != null) {
	                System.out.println("Send success");
	            } else {
	                String error = result.getErrorCodeName();
	                System.out.println("Send fail : " + error);
	            }					
	  
	        } catch (Exception e) {
	        	
System.out.println(e.getClass().getName());
}
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setUrl("errorPCDetail.pca?pcSitNum=" + pcSitNum);
		return forward;
	}

}
