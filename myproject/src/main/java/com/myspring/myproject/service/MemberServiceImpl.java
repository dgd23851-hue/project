package com.myspring.myproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myspring.myproject.dao.MemberDAO;
import com.myspring.myproject.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO; 
	
	// memberDAO �Ӽ��� setter�� �̿��Ͽ� ���� ���Ͽ��� ������ memberDAO ���� �����մϴ�.
//	public void setMemberDAO(MemberDAO memberDAO) {
//		this.memberDAO = memberDAO;
//	}
	
	@Override
	public List searchMemberList(String name) throws DataAccessException{
		List searchMemberList = null;
		searchMemberList = memberDAO.searchMemberList(name);
		return searchMemberList;
	}
	
	@Override
	public List listMembers() throws DataAccessException{
		List membersList = null;
		membersList = memberDAO.selectAllMembers();
		return membersList;
	}
	
	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException{
		MemberVO loginMember;
		loginMember = memberDAO.selectLoginMember(memberVO);
		return loginMember;
	}
	
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException{
		int addMember = 0;
		addMember = memberDAO.insertMember(memberVO);
		return addMember;
	}
	
	@Override
	public void updateMember(MemberVO memberVO) throws DataAccessException{
		memberDAO.updateMember(memberVO);
	}
	
	@Override
	public void delMember(String id) throws DataAccessException{
		memberDAO.deleteMember(id);
	}
	
}
