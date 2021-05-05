package gdu.diary.service;

import java.util.List;

import gdu.diary.dao.*;
import gdu.diary.vo.*;

public class MemberService {
	private MemberDao memberDao;
	
	// 회원 가입 메소드 Create
	public int addMemberByKey(Member member) {

		this.memberDao = new MemberDao();
		List<Member> list = this.memberDao.selectMemberIdByKey(member.getMemberId()); // 중복검사

		if(list.size() != 0) {
			System.out.println("이미 사용중인 아이디 입니다.");
			return 0;
		}
		
		return this.memberDao.insertMember(member);
	}
	
	// 회원 탈퇴 메소드 Delete
	public int removeMemberByKey(Member member) {
		this.memberDao = new MemberDao();
		return this.memberDao.deleteMemberByKey(member);
	}
	
	// 회원 정보 가져오기 Read
	public List<Member> getMemberByKey(Member member) {
		this.memberDao = new MemberDao();
		return this.memberDao.selectMemberByKey(member);
	}
	
	// 비밀번호 수정 메소드 Update
	public int modifyMemberByKey(Member member) {
		this.memberDao = new MemberDao();
		return this.memberDao.updateMemberPwByKey(member);
	}
}
