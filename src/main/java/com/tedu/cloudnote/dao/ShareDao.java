package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import com.tedu.cloudnote.entity.Share;

public interface ShareDao {
	public int save(Share share);
	public List<Share> findLikeTitle(Map<String,Object> map);
	public Share findById(String shareId);
}
