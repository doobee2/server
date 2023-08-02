package test;

import dao.DBC;
import dao.MariaDBCon;
import dto.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//getMember()
public class DaoTest2 {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;

    public static void main(String[] args) {
        DBC con = new MariaDBCon();
        conn = con.connect();

            System.out.println("DB 연결 성공");


        Scanner sc = new Scanner(System.in);
        System.out.println("검색할 회원의 ID를 입력 : ");
        String id = sc.nextLine();

        try {
            String sql = "select * from member where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            Member mem = null;
            if (rs.next()){
                mem = new Member();
                mem.setId(rs.getString("id"));
                mem.setPw(rs.getString("pw"));
                mem.setName(rs.getString("name"));
                mem.setEmail(rs.getString("email"));
                mem.setTel(rs.getString("tel"));
                mem.setRegdate(rs.getString("regdate"));
                mem.setPoint(rs.getInt("point"));
            } else {
                System.out.println("해당 ID의 회원이 존재하지 않습니다.");
            } if(mem!=null) {
                System.out.println("아이디 : " + mem.getId());
                System.out.println("비밀번호 : " + mem.getPw());
                System.out.println("이름 : " + mem.getName());
                System.out.println("이메일 : " + mem.getEmail());
                System.out.println("전화번호 : "+ mem.getTel());
                System.out.println("가입일시 : "+ mem.getRegdate());
                System.out.println("포인트 : " + mem.getPoint());
            }
        } catch (SQLException e) {
            System.out.println("SQL 구문이 처리되지 못했습니다.");
        } finally {
            con.close(rs, pstmt, conn);
        }

    }
}
