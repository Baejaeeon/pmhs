package pmhs.web.sms.gcm.server;

import java.net.URLEncoder;

import pmhs.web.sms.android.server.Message;
import pmhs.web.sms.android.server.Result;
import pmhs.web.sms.android.server.Sender;

/**
 * GCM 메시지 발송을 위한 샘플 구현 소스이다.
 * 하단의 내용을 커스터마이즈하여 실제 서버단에서 발송모듈로 구현 가능하다.
 * @author Leminity
 *
 */
public class GCMTest {
     
    private static final String REGISTRATION_ID = "APA91bE5HsLhd7RMFlaOrTaR_ntLlz9Tu6cta90iVLddPhMHfOi22qHN98ujp4-fzALilE-rk89kBVQHHz80Qt_k6bbFP5KVOV58uEsH5-8iGkiauU5wuX9BDElCXGhHfUuNNvgpPEIX";//registration Id 입력
    private static final String APIKEY = "AIzaSyA4qGGT0X6Atccat8yfWz2YyiWSaIQuLoE"; //GCM에서 발급받은 ApiKey를 입력
     
    public static void main(String[] args) {
        try {
            String sendTlt = "CU_PMHS";
            String sendMsg = "Message";
  
            Sender sender = new Sender(APIKEY);
             
            /**
             * message Build 부분에서 addData로 추가한 값은 어플리케이션의
             * onMessage(context, intent)에서 Intent로 전달되며
             * intent.getExtras().getString("title")형태로 얻어와 사용 가능하다.
             */
            Message message = new Message.Builder()
            .addData("title", URLEncoder.encode(sendTlt, "UTF-8"))
            .addData("msg", URLEncoder.encode(sendMsg, "UTF-8"))
            .build();
  
            //발송할 메시지, 발송할 타깃(RegistrationId, Retry 횟수)
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