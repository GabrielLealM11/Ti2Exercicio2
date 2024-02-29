package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Inscricao;

public class InscricaoDAO extends DAO {

    public InscricaoDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
    public boolean insert(Inscricao inscricao) {
        boolean status = false;
        try {
            if (conexao != null) {
                String sql = "INSERT INTO inscricao (nome, idade, posicao, time) VALUES (?, ?, ?, ?)";
                PreparedStatement st = conexao.prepareStatement(sql);
                st.setString(1, inscricao.getnome());
                st.setInt(2, inscricao.getidade());
                st.setString(3, inscricao.getposicao());
                st.setString(4, inscricao.gettime());

                st.executeUpdate();
                st.close();
                status = true;
            } else {
                System.err.println("Conexão não inicializada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
    public Inscricao get(String nome) {
        Inscricao inscricao = null;

        try {
            PreparedStatement st = conexao.prepareStatement("SELECT * FROM inscricao WHERE nome = ?");
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                inscricao = new Inscricao(rs.getString("nome"), rs.getInt("idade"), rs.getString("posicao"), rs.getString("time"));
            }

            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return inscricao;
    }     
    public List<Inscricao> getAll() {
        return getByOrder("");
    }

    
    public List<Inscricao> getByOrder(String orderBy) {   
    
        List<Inscricao> inscricoes = new ArrayList<Inscricao>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM inscricao" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);               
            while(rs.next()) {                 
                Inscricao u = new Inscricao(rs.getString("nome"), rs.getInt("idade"), rs.getString("posicao"), rs.getString("time"));
                inscricoes.add(u);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return inscricoes;
    }
    
	    public List<Inscricao> getinscricaoMasculino() {
		List<Inscricao> Inscricoes = new ArrayList<Inscricao>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM inscricao WHERE inscricao.inscricao LIKE 'M'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Inscricao u = new Inscricao(rs.getString("nome"), rs.getInt("idade"), rs.getString("posicao"), rs.getString("time"));
	            Inscricoes.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Inscricoes;
	}


    public boolean update(Inscricao inscricao) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE matricula SET idade = " + inscricao.getidade() + ", posicao = '"  
                       + inscricao.getposicao() + "', time = " + inscricao.gettime() 
                       + " WHERE nome = '" + inscricao.getnome() + "'";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public boolean delete(String nome) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM inscricao WHERE nome = '" + nome + "'";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    
    public boolean autenticar(int idade, String posicao) {
        boolean resp = false;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM inscricao WHERE nome LIKE '" + idade + "' AND cargo LIKE '" + posicao  + "'";
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