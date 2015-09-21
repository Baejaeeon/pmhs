package pmhs.vo;

// 요청에 대한 비지니스 로직이 완성된 후
// 포워딩 될 뷰페이지 정보를 저장할 클래스
public class ActionForward {
	private String url; // 포워딩될 url이 저장될 변수
	private boolean redirect; // 리다이엑트 인지 디스패치 인지 결정하는 변수
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}
