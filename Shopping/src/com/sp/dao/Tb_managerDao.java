package com.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sp.entity.Tb_manager;
import com.sp.util.DBHelper;

public class Tb_managerDao {
	/**
	 * ��¼����
	 * @param manName
	 * @param pwd
	 * @return
	 */
	public Tb_manager Login(String manName,String pwd){
		Tb_manager manager = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DBHelper.getConn(); //��ȡ�����Դ
			pstmt = conn.prepareStatement("select * from tb_manager where manName=? and pwd=?");
			//���ò���
			pstmt.setString(1, manName);
			pstmt.setString(2, pwd);
			rs=pstmt.executeQuery();//ִ�в�ѯ
			if(rs.next()){
				manager = new Tb_manager();
				manager.setId(rs.getInt("id"));
				manager.setManName(rs.getString("manName"));
				manager.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			DBHelper.closeConn(rs, pstmt, conn);//�ر������Դ
		}
		
		return manager;
			
	}
//	public List<Tb_manager> getmanager(){
//		List<Tb_manager> newsl=new ArrayList<Tb_manager>();
//		String sql="";
//	
//		
//		
//			sql="select * from tb_manager ";
//			
//		
//		Connection conn=DBHelper.getConn();
//		
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try{
//			pstmt=conn.prepareStatement(sql);
//			
//			rs=pstmt.executeQuery();
//			while(rs.next()){
//				Tb_manager move=new Tb_manager();
//				move.setId(rs.getInt("id"));
//				move.setManName(rs.getString("manName"));
//				move.setPwd(rs.getString("pwd"));
//		
//				newsl.add(move);
//			}
//			}catch(Exception e){
//				e.printStackTrace();
//			
//		}finally{
//			DBHelper.closeConn(rs, pstmt, conn);
//		}
//		return  newsl;
//	}
//	
//	
//	
//	public boolean updateNews(Tb_manager news,String pwd,String name){
//		boolean flag = false;
//		Connection conn = null;  //��ȡ���Ӷ���
//		PreparedStatement pstmt = null;
//		try{
//			
//			conn = DBHelper.getConn();//��ȡ���Ӷ���
//			pstmt = conn.prepareStatement("update tb_manager set manName=?,pwd=? where manName=?and pwd=?");
//			//���ò�����ֵ
//			pstmt.setString(1,name);
//			pstmt.setString(2, pwd);//��1������ֵ
//			pstmt.setString(3, news.getManName());
//			pstmt.setString(4, news.getPwd());
//			
//			int n = pstmt.executeUpdate();
//			if(n>0){
//				flag = true;
//			}
//		}catch(Exception ex){
//			
//		}
//		finally{
//			DBHelper.closeConn(null, pstmt, conn); //�ر���Դ
//		}
//		return flag;
//	}
//	
//	
	
}
