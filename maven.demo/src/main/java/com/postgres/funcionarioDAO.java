package com.postgres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.postgres.DAO;
import com.postgres.funcionario;

	public class funcionarioDAO extends DAO {
		
		public funcionarioDAO() {
			super();
			conectar();
		}

		public void finalize() {
			close();
		}
		
		
		public boolean insert(funcionario funcionario) {
			boolean status = false;
			try {  
				Statement st = conexao.createStatement();
				String sql = "INSERT INTO funcionario (iden, nome, senha, sexo) "
					       + "VALUES ("+funcionario.getiden()+ ", '" + funcionario.getnome() + "', '"  
					       + funcionario.getSenha() + "', '" + funcionario.getSexo() + "');";
				System.out.println(sql);
				st.executeUpdate(sql);
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
		}

		
		public funcionario get(int iden) {
			funcionario funcionario = null;
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM produto WHERE id=" + iden;
				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);	
		        if(rs.next()){            
		        	 funcionario = new funcionario(rs.getInt("iden"), rs.getString("nome"), rs.getString("senha"), rs.getString("sexo").charAt(0));
		        }
		        st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return funcionario;
		}
		
		
		public List<funcionario> get() {
			return get("");
		}

		
		public List<funcionario> getOrderByiden() {
			return get("iden");		
		}
		
		
		public List<funcionario> getOrderBynome() {
			return get("nome");		
		}
		
		
		public List<funcionario> getOrderBySexo() {
			return get("sexo");		
		}
		
		
		private List<funcionario> get(String orderBy) {	
		
			List<funcionario> funcionarios = new ArrayList<funcionario>();
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM funcionario" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);	           
		        while(rs.next()) {	            	
		        	funcionario u = new funcionario(rs.getInt("iden"), rs.getString("nome"), rs.getString("senha"), rs.getString("sexo").charAt(0));
		            funcionarios.add(u);
		        }
		        st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return funcionarios;
		}


		public List<funcionario> getSexoMasculino() {
			List<funcionario> funcionarios = new ArrayList<funcionario>();
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM funcionario WHERE funcionario.sexo LIKE 'M'";
				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);	           
		        while(rs.next()) {	            	
		        	funcionario u = new funcionario(rs.getInt("iden"), rs.getString("nome"), rs.getString("senha"), rs.getString("sexo").charAt(0));
		            funcionarios.add(u);
		        }
		        st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return funcionarios;
		}
		
		
		public boolean update(funcionario funcionario) {
			boolean status = false;
			try {  
				Statement st = conexao.createStatement();
				String sql = "UPDATE funcionario SET nome = '" + funcionario.getnome() + "', senha = '"  
					       + funcionario.getSenha() + "', sexo = '" + funcionario.getSexo() + "'"
						   + " WHERE iden = " + funcionario.getiden();
				System.out.println(sql);
				st.executeUpdate(sql);
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
		}
		
		public boolean delete(int iden) {
			boolean status = false;
			try {  
				Statement st = conexao.createStatement();
				String sql = "DELETE FROM funcionario WHERE iden = " + iden;
				System.out.println(sql);
				st.executeUpdate(sql);
				st.close();
				status = true;
			} catch (SQLException u) {  
				throw new RuntimeException(u);
			}
			return status;
		}
		
		
		public boolean autenticar(String nome, String senha) {
			boolean resp = false;
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM funcionario WHERE nome LIKE '" + nome + "' AND senha LIKE '" + senha  + "'";
				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);
				resp = rs.next();
		        st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return resp;
		}	
	}