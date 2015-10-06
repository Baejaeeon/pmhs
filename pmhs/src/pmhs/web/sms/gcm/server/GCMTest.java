package pmhs.web.sms.gcm.server;

import java.net.URLEncoder;

import pmhs.web.sms.android.server.Message;
import pmhs.web.sms.android.server.Result;
import pmhs.web.sms.android.server.Sender;

/**
 * GCM �޽��� �߼��� ���� ���� ���� �ҽ��̴�.
 * �ϴ��� ������ Ŀ���͸������Ͽ� ���� �����ܿ��� �߼۸��� ���� �����ϴ�.
 * @author Leminity
 *
 */
public class GCMTest {
     
    private static final String REGISTRATION_ID = "APA91bE5HsLhd7RMFlaOrTaR_ntLlz9Tu6cta90iVLddPhMHfOi22qHN98ujp4-fzALilE-rk89kBVQHHz80Qt_k6bbFP5KVOV58uEsH5-8iGkiauU5wuX9BDElCXGhHfUuNNvgpPEIX";//registration Id �Է�
    private static final String APIKEY = "AIzaSyA4qGGT0X6Atccat8yfWz2YyiWSaIQuLoE"; //GCM���� �߱޹��� ApiKey�� �Է�
     
    public static void main(String[] args) {
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
            e.printStackTrace();
        }
    }
     
}