package com.myspring.myproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myspring.myproject.service.MemberService;
import com.myspring.myproject.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl extends MultiActionController implements MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberVO memberVO;

    /**
     * 이름으로 회원 검색
     * - 요청 URI로부터 뷰 이름을 계산(getViewName)
     * - 서비스에서 검색 결과를 받아 모델에 담아 반환
     */
    @RequestMapping(value = "/member/searchMemberList.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView searchMemberList(@RequestParam("name") String name,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {

        String viewName = getViewName(request);
        ModelAndView mav = new ModelAndView(viewName);

        List searchMemberList = memberService.searchMemberList(name);
        mav.addObject("membersList", searchMemberList);
        mav.addObject("name", name);

        return mav;
    }

    /**
     * 회원 목록 조회
     */
    @RequestMapping(value = "/member/listMembers.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String viewName = getViewName(request);

        logger.info("listMembers viewName = {}", viewName);
        logger.debug("listMembers viewName = {}", viewName);

        List membersList = memberService.listMembers();

        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("membersList", membersList);

        return mav;
    }

    /**
     * 로그인 폼 이동
     * - /WEB-INF/views/member/loginForm.jsp 같은 뷰로 매핑되도록 ViewResolver 설정을 따릅니다.
     */
    @RequestMapping(value = "/member/loginForm.do", method = RequestMethod.GET)
    public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String viewName = getViewName(request);
        return new ModelAndView(viewName);
    }

    /**
     * 회원가입 폼 이동
     */
    @RequestMapping(value = "/member/memberForm.do", method = RequestMethod.GET)
    public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String viewName = getViewName(request);
        return new ModelAndView(viewName);
    }

    /**
     * 로그인 처리 (POST)
     * - 인증 성공 시 세션에 member, isLogOn 저장 후 메인으로 리다이렉트
     * - 실패 시 로그인 폼으로 리다이렉트
     */
    @RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("member") MemberVO member,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {

        String viewName = getViewName(request);
        ModelAndView mav = new ModelAndView(viewName);

        memberVO = memberService.login(member);

        if (memberVO != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", memberVO); // 이후 화면에서 로그인 사용자 정보 사용
            session.setAttribute("isLogOn", true);    // 로그인 여부 플래그

            mav.setViewName("redirect:/main.do");
        } else {
            logger.info("로그인 실패: 아이디/비밀번호 불일치");
            mav.setViewName("redirect:/member/loginForm.do");
        }

        return mav;
    }

    /**
     * 로그아웃
     * - 세션에서 로그인 관련 속성을 제거하고 메인으로 리다이렉트
     */
    @RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("member");
            session.removeAttribute("isLogOn");
            // 필요시 session.invalidate()로 세션 자체를 만료시킬 수도 있습니다.
        }

        return new ModelAndView("redirect:/main.do");
    }

    /**
     * 회원가입 처리 (POST)
     * - 가입 후 목록 페이지로 이동
     */
    @RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
    public ModelAndView addMember(@ModelAttribute("member") MemberVO memberVO,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        memberService.addMember(memberVO);

        return new ModelAndView("redirect:/main.do");
    }

    /**
     * 회원정보 수정 폼 이동
     * - 파라미터로 받은 memberVO를 그대로 모델에 담아 뷰에서 사용
     */
    @RequestMapping(value = "/member/memberUpdateForm.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateForm(@ModelAttribute("member") MemberVO memberVO,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        String viewName = getViewName(request);

        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("member", memberVO);

        return mav;
    }

    /**
     * 회원정보 수정 처리
     */
    @RequestMapping(value = "/member/updateMember.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateMember(@ModelAttribute("member") MemberVO memberVO,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        memberService.updateMember(memberVO);

        return new ModelAndView("redirect:/member/listMembers.do");
    }

    /**
     * 회원 삭제
     */
    @RequestMapping(value = "/member/delete.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView delMember(@RequestParam("id") String id,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        logger.info("삭제 요청 id = {}", id);

        memberService.delMember(id);

        return new ModelAndView("redirect:/member/listMembers.do");
    }

    /**
     * 요청 URI에서 뷰 이름(viewName)만 추출
     *
     * 예) /myproject/member/listMembers.do  ->  /member/listMembers
     * - contextPath를 제거
     * - 세미콜론(;)이나 쿼리스트링(?) 이전까지만 사용
     * - .do 등 확장자는 제거
     * - 마지막 슬래시(/)부터 끝까지를 사용하여 "/member/listMembers" 형태로 반환
     *
     * ViewResolver(prefix/suffix 설정)에 의해 최종 JSP 경로로 매핑됩니다.
     */
    private String getViewName(HttpServletRequest request) throws Exception {

        String contextPath = request.getContextPath();

        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        if (uri == null || uri.trim().isEmpty()) {
            uri = request.getRequestURI();
        }

        int begin = 0;
        if (contextPath != null && !contextPath.isEmpty()) {
            begin = contextPath.length();
        }

        int end;
        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } else {
            end = uri.length();
        }

        String fileName = uri.substring(begin, end);        // 예) /member/listMembers.do
        if (fileName.indexOf(".") != -1) {
            fileName = fileName.substring(0, fileName.lastIndexOf(".")); // 확장자 제거
        }
        if (fileName.lastIndexOf("/") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length()); // 마지막 슬래시부터 사용
        }
        return fileName; // 예) /member/listMembers
    }
}
