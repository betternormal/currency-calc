package com.example.currencycalc.common;

// 필요한 상태메세지가 있으면 추가한다
public class ResponseMessage {
    public static final String GET_SUCCESS = "조회 성공";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String POST_SUCCESS = "삽입 성공";
    public static final String BAD_REQUEST = "잘못된 요청";
    public static final String PATCH_SUCCESS = "수정 성공";
    public static final String DELETE_SUCCESS = "삭제 성공";
    public static final String REISSUE_SUCCESS = "재발급 성공";
    public static final String SIGNUP_FAIL = "회원가입 실패";

    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
}
