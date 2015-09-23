package pmhs.web.message.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.message.svc.MessageReceiveListService;
import pmhs.web.message.vo.MessageVO;
import pmhs.web.message.vo.PageInfo;

public class MessageReceiveListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum"); // 페이지 번호를 받아온다.
		if (pageNum == null) {
			pageNum = "1"; // 페이지 번호를 눌러서 들어오지 않으면 게시판 1page를 보여준다.
		}

		// 페이지 번호를 사용해서 페이징 처리 시 연산을 수행할 것이므로
		// 페이지 번호값을 정수타입으로 변경
		int currentPage = Integer.parseInt(pageNum);

		// 해당 페이지에 출력되는 글들 중 가장 먼저 출력되는 글의 레코드 번호
		int startRow = (currentPage - 1) * pageSize + 1;
		// 현재 페이지 : 1
		// (1 - 1) * pageSize + 1 ---------> 1
		// 현재 페이지 : 2
		// (2 - 1) * pageSize + 1 ---------> 11

		int count = 0;
		// count : 총 글의 개수를 저장할 변수
		int number = 0;
		// number : 해당 페이지에 가장 먼저 출력되는 글의 번호

		List<MessageVO> receiveList = null; // 글 정보를 저장할 리스트
		// 해당 페이지에 출력되는 글 목록을 저장할 컬렉션

		MessageReceiveListService messageReceiveListService = new MessageReceiveListService();

		count = messageReceiveListService.getArticleCount();
		if (count > 0) {
			// 글이 하나라도 있으면 리스팅할 글 정보 얻어오기
			receiveList = messageReceiveListService.getReceiveList(startRow, pageSize); // 해당
		}

		// 전체 페이지에서 현재페이지 -1을 해서 pageSize를 곱한다.
		number = count - (currentPage - 1) * pageSize;
		// 총 글의 개수 : 134
		// 현재 페이지 : 1
		// 134 - (1 - 1) * 10 -------> 134

		int startPage = 0;
		int pageCount = 0;
		int endPage = 0;
		if (count > 0) {
			pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			// 총 페이지 개수를 구함.
			// ex) 총 글의 개수 13개이면 페이지는 2개 필요..

			startPage = ((currentPage - 1) / pageSize) * pageSize + 1;
			// 현재 페이지 그룹의 첫번째 페이지를 구함.
			// [1][2][3][4][5][6][7]...[10] -------> 처음 페이지 그룹
			// 다음 페이지 스타트 페이지 : [11][12][13]....[20]

			int pageBlock = 10;
			endPage = startPage + pageBlock - 1;

			// 마지막 페이지 그룹인 경우..
			if (endPage > pageCount)
				endPage = pageCount;
		}

		request.setAttribute("receiveList", receiveList);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCount(count);
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setNumber(number);
		pageInfo.setPageCount(pageCount);
		pageInfo.setStartPage(startPage);

		request.setAttribute("pageInfo", pageInfo);
		ActionForward forward = new ActionForward();
		forward.setUrl("/message/messageReceiveList.jsp");
		return forward;
	}

}
