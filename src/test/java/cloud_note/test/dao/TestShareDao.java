package cloud_note.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.tedu.cloudnote.dao.ShareDao;
import com.tedu.cloudnote.entity.Share;

import test.TestBase;

public class TestShareDao extends TestBase{

	private ShareDao dao;
	
	@Before
	public void init(){
		dao = super.getContext().getBean("shareDao",ShareDao.class);
	}
	
	@Test
	public void test1(){
		for(int i = 0;i<10;i++){
			Share share = new Share();
			share.setCn_share_id("213"+i);
			share.setCn_share_title("java"+i);
			int status = dao.save(share);
			System.out.println(status);
		}
	}
	
	@Test
	public void test2(){
		//List<Share> shares = dao.findLikeTitle("%java%");
//		for (Share share : shares) {
//			System.out.println(share.getCn_share_title());
//		}
	}
	
}
