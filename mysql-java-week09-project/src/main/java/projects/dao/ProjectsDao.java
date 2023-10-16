package projects;

public class ProjectsDao {
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

	public Recipe insertProject(Project project) {
		// TODO Auto-generated method stub
		String sql = ""
				+ "INSERT INTO " + PROJECT_TABLE + " "
				+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
				+ "(?, ?, ?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, project.getProjectName(), String.class);
				setParameter(stmt, 2, project.getEstimatedHours(), Integer.class);
				setParameter(stmt, 3, project.getActualHours(), Integer.class);
				setParameter(stmt, 4, project.getDifficulty(), Integer.class);
				setParameter(stmt, 5, project.getNotes(), String.class );
				
				stmt.executeUpdate();
				Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
				
				commitTransaction(conn);
				
				recipe.setProjectId(projectId);
				return project;
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

}
