package pmhs.web.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnABoardListService;
import pmhs.web.board.vo.PageInfo;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardListAction implements Action {
	   
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
			
			List<QnABoardVO> articleList = null; // �� ������ ������ ����Ʈ
			// �ش� �������� ��µǴ� �� ����� ������ �÷���
			
			// �����Ͻ� ���� ó���� ���� ���� ��ü ����
			QnABoardListService qnABoardListService = new QnABoardListService();
			
			count = qnABoardListService.getQnAArticleCount(); // �� ���� ������ �����´�.
			if(count > 0) { 
				// ���� �ϳ��� ������ �������� �� ���� ������
				articleList = qnABoardListService.getArticleList(startRow, pageSize); // �ش� �������� ���ڵ� 10���� �����´�.
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
			request.setAttribute("articleList", articleList);
			PageInfo pageInfo = new PageInfo(); // �������� ���� ������ ó���ϴ� ��ü ����
			pageInfo.setCount(count);
			pageInfo.setCurrentPage(currentPage);
			pageInfo.setEndPage(endPage);
			pageInfo.setNumber(number);
			pageInfo.setPageCount(pageCount);
			pageInfo.setStartPage(startPage);
			
			request.setAttribute("pageInfo", pageInfo);
			ActionForward forward = new ActionForward();
			forward.setUrl("/board/qnAList.jsp"); // list.jsp ������ ������
			
			return forward;
		}

	}

