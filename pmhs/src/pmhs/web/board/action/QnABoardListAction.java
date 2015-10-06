package pmhs.web.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pmhs.action.Action;
import pmhs.vo.ActionForward;
import pmhs.web.board.svc.QnABoardListService;
import pmhs.web.board.vo.PageInfo;
import pmhs.web.board.vo.QnABoardVO;

public class QnABoardListAction implements Action {
	   
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			int pageSize = 10;
			
			String pageNum = request.getParameter("pageNum"); 
			if(pageNum == null) {
				pageNum = "1"; 
			}

			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * pageSize + 1;			
			int count = 0;
			int number = 0;
	
			List<QnABoardVO> articleList = null; 
			QnABoardListService qnABoardListService = new QnABoardListService();
			HttpSession sesion = request.getSession();
			
			count = qnABoardListService.getQnAArticleCount(); 
			if(count > 0) { 
				
				articleList = qnABoardListService.getArticleList(startRow, pageSize); 
			}
			

			number = count - (currentPage - 1) * pageSize;
			
			int startPage = 0;
			int pageCount = 0;
			int endPage = 0;
			
			if(count > 0) {
				pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		
		
				startPage = ((currentPage -1) / pageSize) * pageSize + 1;
						
				int pageBlock = 10;
				endPage = startPage + pageBlock - 1;
				
	
				if(endPage > pageCount) endPage = pageCount;
			}
			

			request.setAttribute("articleList", articleList);
			PageInfo pageInfo = new PageInfo(); 
			pageInfo.setCount(count);
			pageInfo.setCurrentPage(currentPage);
			pageInfo.setEndPage(endPage);
			pageInfo.setNumber(number);
			pageInfo.setPageCount(pageCount);
			pageInfo.setStartPage(startPage);
			
			request.setAttribute("pageInfo", pageInfo);
			ActionForward forward = new ActionForward();
			forward.setUrl("/board/qnAList.jsp");
			
			return forward;
		}

	}

