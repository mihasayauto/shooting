package board.action;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import board.entity.Board;
import board.form.BoardForm;
import facebook4j.Facebook;
import facebook4j.FacebookException;



public class BoardAction {

	@Resource
	@ActionForm
	protected BoardForm boardForm;
	@Resource
	public JdbcManager jdbcManager;
	@Resource
	public HttpSession session;

	// ページング処理用
	// 前のページがあるか
	public boolean hasNext = false;
	// 次のページがあるか
	public boolean hasPrev = false;
	// 総件数
	long total;
	// １ページに◯件ずつ表示
	int LIMIT = 5;
	// 表示するエンティティのリスト
	public List<Board> boardList;

	public String name;

	@Execute(validator = false)
	public String index() {
		name = (String) session.getAttribute("name");
		// ページング処理
		// ページ番号を所得
		int page = IntegerConversionUtil.toPrimitiveInt(this.boardForm.page);
		this.total = jdbcManager.from(Board.class).getCount();
		// 総件数を所得
		boardList = jdbcManager.from(Board.class).orderBy("posteddate").limit(LIMIT).offset(page * LIMIT)
				.getResultList();
		// 前のページがあるか判定
		if (page != 0) {
			hasPrev = true;
		}
		if ((page + 1) * LIMIT < total) {
			hasNext = true;
		}
		return "input.jsp";
	}

	// 投稿を押した時の処理
	@Execute(validator = true, input = "input.jsp")
	public String next() throws FacebookException {
		// Board.entityを挿入
		Board board = new Board();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		// nameはセッションから取る
		name = (String) session.getAttribute("name");
		board.setName(name);
		board.setMessage(boardForm.message);
		board.setPosteddate(time);
		// データベースに挿入
		int count = jdbcManager.insert(board).execute();

		// facebookに投稿
		Facebook facebook = (Facebook) session.getAttribute("facebook");
		if (facebook != null) {
			facebook.postStatusMessage(boardForm.message);
		}

		// twiiterに投稿
		//Twitter twitter = (Twitter) session.getAttribute("Twitter");
		//if (twitter != null) {
		//	twitter.updateStatus(boardForm.message);
		//}

		return "input.jsp";
	}

	// ログアウト
	@Execute(validator = false)
	public String logout() {
		session.removeAttribute("name");
		return "/login?redirect=true";
	}
}
