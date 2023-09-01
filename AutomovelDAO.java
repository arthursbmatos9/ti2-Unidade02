package exercicio02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Automovel;

public class AutomovelDAO extends DAO
{
	
		public AutomovelDAO() {
			super();
			conectar();
		}
		
		public void finaliza() {
			close();
		}
		
		public boolean insert(Automovel automovel) 
		{
			boolean status = false;
			try {  
						Statement st = conexao.createStatement();
						String sql = "INSERT INTO Automoveis (marca, modelo, placa, ano) "
							       + "VALUES ("+automovel.getMarca()+ ", '" + automovel.getModelo() + "', '"  
							       + automovel.getPlaca() + "', '" + automovel.getAno() + "');";
						System.out.println(sql);
						st.executeUpdate(sql);
						st.close();
						status = true;
					} catch (SQLException u) {  
						throw new RuntimeException(u);
				}
			    return status;
		}
		
		public Automovel get(String placa) {
			Automovel automovel = null;
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM automovel WHERE placa=" + placa;
				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);	
		        if(rs.next()){            
		        	 automovel = new Automovel(rs.getString("marca"), rs.getString("modelo"), rs.getString("placa"), rs.getAno("ano"));
		        }
		        st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return automovel;
		}
		
		public List<Automovel> get() {
					return get("");
					
		}
				
		public List<Automovel> getOrderByPlaca() {
			return get("placa");	
		}
		
		public List<Automovel> getOrderByAno() {
			return get("ano");		
		}
		
		
		public List<Automovel> getOrderByModelo() {
			return get("modelo");		
		}
			
		private List<Automovel> get(String orderBy) {	
			
			List<Automovel> automoveis = new ArrayList<Automovel>();
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String sql = "SELECT * FROM automovel" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);	           
		        while(rs.next()) {	            	
		        	Automovel u = new Automovel(rs.getString("marca"), rs.getString("modelo"), rs.getString("placa"), rs.getInt("ano");
		            automoveis.add(u);
		        }
		        st.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return automoveis;
		}

		public boolean update(Automovel automovel) {
					boolean status = false;
					try {  
						Statement st = conexao.createStatement();
						String sql = "UPDATE Automoveis SET marca = '" + automovel.getMarca() + "', modelo = '"  
							       + automovel.getModelo() + "', ano = '" + automovel.getAno() + "'"
								   + " WHERE placa = " + automovel.getPlaca();
						System.out.println(sql);
						st.executeUpdate(sql);
						st.close();
						status = true;
					} catch (SQLException u) {  
						throw new RuntimeException(u);
					}
					return status;
		}
				
		public boolean delete(String placa) {
					boolean status = false;
					try {  
						Statement st = conexao.createStatement();
						String sql = "DELETE FROM Automoveis WHERE placa = " + placa;
						System.out.println(sql);
						st.executeUpdate(sql);
						st.close();
						status = true;
					} catch (SQLException u) {  
						throw new RuntimeException(u);
					}
					return status;
		}
}

