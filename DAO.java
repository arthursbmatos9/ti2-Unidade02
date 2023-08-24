package exercicio02;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "2005";
		String password = "2005";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirAutomovel(Automovel automovel) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO automovel (marca, modelo, placa, ano) "
					       + "VALUES ("+automovel.getMarca()+ ", '" + automovel.getModelo() + "', '"  
					       + automovel.getPlaca() + "', '" + automovel.getAno() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarAutomovel(Automovel automovel) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE automovel SET marca = '" + automovel.getMarca() + "', modelo = '"  
				       + automovel.getModelo() + "', ano = '" + automovel.getAno() + "'"
					   + " WHERE placa = " + automovel.getPlaca();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirAutomovel(int placa) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM automovel WHERE placa = " + placa);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Automovel[] getAutomoveis() {
		Automovel[] automoveis = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM automoveis");		
	         if(rs.next()){
	             rs.last();
	             automoveis = new Automovel[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                automoveis[i] = new Automovel(rs.getString("marca"), rs.getString("modelo"), rs.getString("placa"), rs.getInt("ano"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return automoveis;
	}

	
	public Automovel[] getAutomoveis2023() {
		Automovel[] automoveis = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM automovel WHERE automovel.ano LIKE 2023");		
	         if(rs.next()){
	             rs.last();
	             automoveis = new Automovel[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                automoveis[i] = new Automovel(rs.getString("marca"), rs.getString("modelo"), rs.getString("placa"), rs.getInt("ano"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return automoveis;
	}
}