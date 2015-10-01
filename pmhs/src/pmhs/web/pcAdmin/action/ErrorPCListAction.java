package pmhs.web.pcAdmin.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.vo.PageInfo;
import pmhs.web.pcAdmin.svc.ErrorPCListService;
import pmhs.web.pcAdmin.svc.ReservationListService;
import pmhs.web.pcAdmin.vo.ErrorPCInfo;
import pmhs.web.pcAdmin.vo.ReservationInfo;

public class ErrorPCListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int pageSize = 10; // �� ������ �� ��µ� ���� ���� ����
		
		String pageNum = request.getParameter("pageNum"); // ������ ��ȣ�� �޾ƿ´�.
		if(pageNum == null) {
			pageNum = "1"; // ������ ��ȣ�� ������ ������ ������ �Խ��� 1page�� �����ش�.
		}
		
		// ������ ��ȣ�� ����ؼ� ����¡ ó�� �� ������ ������ ���̹Ƿ�
		// ������ ��ȣ���� ����Ÿ������ ����
		int currentPage = Integer.parseInt(pageNum);
		
		// �ش� �������� ��µǴ� �۵� �� ���� ���� ��µǴ� ���� ���ڵ� ��ȣ
		int startRow = (currentPage - 1) * pageSize + 1;
		// ���� ������ : 1
		// (1 - 1) * pageSize + 1 ---------> 1
		// ���� ������ : 2
		// (2 - 1) * pageSize + 1 ---------> 11
		
		int count = 0;
		// count : �� ���� ������ ������ ����
		int number = 0;
		// number : �ش� �������� ���� ���� ��µǴ� ���� ��ȣ
		
		List<ErrorPCInfo> errorPCList = null; // �� ������ ������ ����Ʈ
		// �ش� �������� ��µǴ� �� ����� ������ �÷���
		
		// �����Ͻ� ���� ó���� ���� ���� ��ü ����
		ErrorPCListService errorPCListService = new ErrorPCListService();
		
		count = errorPCListService.getErrorPCCount(); // �� ������ ������ �����´�.
		if(count > 0) { 
			// ������ �ϳ��� ������ �������� ���� ���� ������
			errorPCList = errorPCListService.getErrorPCList(startRow, pageSize); // �ش� �������� ���ڵ� 10���� �����´�.
		}
		
		// ��ü ���������� ���������� -1�� �ؼ� pageSize�� ���Ѵ�.
		number = count - (currentPage - 1) * pageSize;
		// �� ���� ���� : 134
		// ���� ������ : 1
		// 134 - (1 - 1) * 10 -------> 134
		
		int startPage = 0;
		int pageCount = 0;
		int endPage = 0;
		
		if(count > 0) { // ���� �ϳ��� �����ϸ�...
			pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			// �� ������ ������ ����.
			// ex) �� ���� ���� 13���̸� �������� 2�� �ʿ�..
	
			startPage = ((currentPage -1) / pageSize) * pageSize + 1;
			// ���� ������ �׷��� ù��° �������� ����.
			// [1][2][3][4][5][6][7]...[10] -------> ó�� ������ �׷�
			// ���� ������ ��ŸƮ ������ : [11][12][13]....[20]
					
			int pageBlock = 10;
			endPage = startPage + pageBlock - 1;
			
			// ������ ������ �׷��� ���..
			if(endPage > pageCount) endPage = pageCount;
		}
		
		// ������ �ϱ� ��, ���� �� �� ����
		request.setAttribute("errorPCList", errorPCList);
		PageInfo pageInfo = new PageInfo(); // �������� ���� ������ ó���ϴ� ��ü ����
		pageInfo.setCount(count);
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setNumber(number);
		pageInfo.setPageCount(pageCount);
		pageInfo.setStartPage(startPage);
		
		request.setAttribute("pageInfo", pageInfo);
		ActionForward forward = new ActionForward();
		forward.setUrl("/pc/errorPCList.jsp"); // list.jsp ������ ������
		
		return forward;
	}

}
