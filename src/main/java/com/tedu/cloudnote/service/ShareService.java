package com.tedu.cloudnote.service;

import java.util.List;


import com.tedu.cloudnote.entity.Share;
import com.tedu.cloudnote.util.NoteResult;

public interface ShareService {
	public NoteResult<Object> shareNote(String noteId);
	public NoteResult<List<Share>> searchNote(int page, String likeTitle);
	public NoteResult<Share> loadShare(String shareId);
}
