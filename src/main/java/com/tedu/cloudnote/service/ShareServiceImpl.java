package com.tedu.cloudnote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.dao.ShareDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;

@Service("shareService")
@Transactional//3.�ƶ����,����ǵķ���
//�����DataSourceTransactionManager��������
public class ShareServiceImpl implements ShareService {

	@Resource
	private ShareDao shareDao;
	@Resource
	private NoteDao noteDao;
	
	public NoteResult<Object> shareNote(String noteId) {
		
		Note note = noteDao.findById(noteId);
		//����Share
		Share share = new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_body(note.getCn_note_body());
		share.setCn_share_title(note.getCn_note_title());
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);
		//TODO �޸�cn_note��type_idֵ
		String s = null; s.length();
		int status = shareDao.save(share);
		NoteResult<Object> result = new NoteResult<Object>();
		if(status >0){
			result.setMsg("��ӳɹ�");
			result.setStatus(0);
		}else{
			result.setMsg("���ʧ��");
			result.setStatus(1);
		}
		
		return result;
	}

	public NoteResult<List<Share>> searchNote(int page,String likeTitle) {
		
		if(likeTitle!= null&&!"".equals(likeTitle)){
			likeTitle = "%"+likeTitle.trim()+"%";
		}
		int begin = (page-1)*5;
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("likeTitle", likeTitle);
		List<Share> shares = shareDao.findLikeTitle(map);
		NoteResult<List<Share>> result = new NoteResult<List<Share>>();
		if(shares.size()>0){//ע��
			result.setData(shares);
			result.setMsg("��ѯ�ɹ�");
			result.setStatus(0);
			
		}else{
			result.setMsg("��ѯʧ��");
			result.setStatus(1);
		}
		return result;
	}

	public NoteResult<Share> loadShare( String shareId) {
		
		Share share = shareDao.findById(shareId);
		NoteResult<Share> result = new NoteResult<Share>();
		if(share != null){
			result.setData(share);
			result.setMsg("��ȡ�ɹ�");
			result.setStatus(0);
			System.out.println("loadShare");
		}else{
			result.setMsg("��ȡʧ��");
			result.setStatus(1);
		}
		return result;
	}

}
