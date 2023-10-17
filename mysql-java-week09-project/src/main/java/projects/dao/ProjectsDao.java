package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import provided.util.DaoBase;
import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
import projects.exception.DbException;

public class ProjectsDao extends DaoBase {
	private static final String PROJECT_TABLE = "project";
	private static final String MATERIAL_TABLE = "material";
	private static final String STEP_TABLE = "step";
	private static final String CATEGORY_TABLE = "category";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";

	public void executeBatch(List<String> sqlBatch) {
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(Statement stmt = conn.createStatement()) {
				for(String sql : sqlBatch) {
					stmt.addBatch(sql);
				}
				
				stmt.executeBatch();
				commitTransaction(conn);
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

	public Project insertProject(Project project) {
		String sql = ""
				+ "INSERT INTO " + PROJECT_TABLE + " "
				+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, project.getProjectName(), String.class);
				setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
				setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
				setParameter(stmt, 4, project.getDifficulty(), Integer.class);
				setParameter(stmt, 5, project.getNotes(), String.class );
				
				stmt.executeUpdate();
				Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
				
				commitTransaction(conn);
				
				project.setProjectId(projectId);
				return project;
			} catch (Exception e) {
				rollbackTransaction(conn);
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	
	public Project findAProject(String projectName) {
		String sql = ""
				+ "SELECT * FROM " + PROJECT_TABLE + " "
				+ "WHERE project_name LIKE '%" + projectName + "%' "
				+ "LIMIT 1";
		
		return getProjects(sql).get(0);
	}

	public List<Material> findMaterialsByProjectId(Integer projectId) {
		String sql = ""
				+ "SELECT * FROM " + MATERIAL_TABLE + " "
				+ "WHERE project_id =" + projectId + " ";
		
		return getMaterials(sql);
	}
	
	public List<Step> findStepsByProjectId(Integer projectId) {
		String sql = ""
				+ "SELECT * FROM " + STEP_TABLE + " "
				+ "WHERE project_id =" + projectId + " ";
		
		return getSteps(sql);		
	}
	
	public List<Category> findCategoriesByProjectId(Integer projectId) {
		// Could not use simple SELECT, had to add JOIN as CATEGORY details only stored in CATEGORY TABLE, though
		// no project_id in CATEGORY TABLE
		String sql = ""
				+ "SELECT c.* FROM " + PROJECT_CATEGORY_TABLE + " pc "
				+ "JOIN " + CATEGORY_TABLE + " c USING(category_id) "
				+ "WHERE pc.project_id =" + projectId + " ";
		
		return getCategories(sql);
	}
	
	// SELECT QUERY BY OBJECT 
	private List<Category> getCategories(String query) {
		// Moved this out into its own Method as I feel we will be calling these more from with different queries
		try(Connection conn = DbConnection.getConnection()) {			
			try(Statement stmt = conn.createStatement()) {
				
				ResultSet results = stmt.executeQuery(query);
				
				List<Category> categoryResults = new LinkedList<>();
				while(results.next()) {
					Category c = new Category();
					c.setCategoryId(Integer.valueOf(results.getString("category_id")));
					c.setCategoryName(results.getString("category_name"));
					
					categoryResults.add(c);
				}
				
				return categoryResults;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	
	private List<Step> getSteps(String query) {
		try(Connection conn = DbConnection.getConnection()) {			
			try(Statement stmt = conn.createStatement()) {
				
				ResultSet results = stmt.executeQuery(query);
				
				List<Step> stepResults = new LinkedList<>();
				while(results.next()) {
					Step s = new Step();
					s.setProjectId(Integer.valueOf(results.getString("project_id")));
					s.setStepId(Integer.valueOf(results.getString("step_id")));
					s.setStepText(results.getString("step_text"));
					s.setStepOrder(Integer.valueOf(results.getString("step_order")));
					
					stepResults.add(s);
				}
				
				return stepResults;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	
	private List<Material> getMaterials(String query) {
		try(Connection conn = DbConnection.getConnection()) {			
			try(Statement stmt = conn.createStatement()) {
				
				ResultSet results = stmt.executeQuery(query);
				
				List<Material> materialResults = new LinkedList<>();
				while(results.next()) {
					Material m = new Material();
					m.setProjectId(Integer.valueOf(results.getString("project_id")));
					m.setMaterialId(Integer.valueOf(results.getString("material_id")));
					m.setMaterialName(results.getString("material_name"));
					m.setNumRequired(Integer.valueOf(results.getString("num_required")));
					// Just a hint for working with BigDecimal --- BigDecimal(input).setScale(2)
					m.setCost(new BigDecimal(results.getDouble("cost")).setScale(2));
					
					materialResults.add(m);
				}
				
				return materialResults;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	private List<Project> getProjects(String query) {
		try(Connection conn = DbConnection.getConnection()) {			
			try(Statement stmt = conn.createStatement()) {
				
				ResultSet results = stmt.executeQuery(query);
				
				List<Project> projectResults = new LinkedList<>();
				while(results.next()) {
					Project p = new Project();
					p.setProjectId(Integer.valueOf(results.getString("project_id")));
					p.setProjectName(results.getString("project_name"));
					// Just a hint for working with BigDecimal --- BigDecimal(input).setScale(2)
					p.setEstimatedHours(new BigDecimal(results.getDouble("estimated_hours")).setScale(2));
					p.setActualHours(new BigDecimal(results.getDouble("actual_hours")).setScale(2));
					p.setDifficulty(Integer.valueOf(results.getString("difficulty")));
					p.setNotes(results.getString("notes"));
					
					p.setMaterials(findMaterialsByProjectId(p.getProjectId()));
					p.setSteps(findStepsByProjectId(p.getProjectId()));
					p.setCategories(findCategoriesByProjectId(p.getProjectId()));
					
					projectResults.add(p);
				}
				
				
				
				return projectResults;
				
			} catch (Exception e) {
				// Commented out previous coding: 
				//rollbackTransaction(conn);
				
				// Reason: Rollback not needed for SELECT Query 
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
}
