package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class DonorService {

	public List<Donor> getDonors() {
		List<Donor> donors = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT * FROM donneur";
			try (PreparedStatement statement = conn.prepareStatement(sql);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("nom");
					String bloodGroup = resultSet.getString("groupe_sanguin");
					String gender = resultSet.getString("sex");
					int age = resultSet.getInt("age");
					String address = resultSet.getString("adresse");
					long phoneNumber = resultSet.getLong("numtel");
					donors.add(new Donor(id, name, bloodGroup, gender, age, address, phoneNumber));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donors;
	}

	public boolean insertDonor(String name, String bloodGroup, String gender, int age, String address,
			long phoneNumber) {
		String password = "don1234";
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "INSERT INTO donneur (nom, groupe_sanguin, sex, age, adresse, numtel, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.setString(1, name);
				statement.setString(2, bloodGroup);
				statement.setString(3, gender);
				statement.setInt(4, age);
				statement.setString(5, address);
				statement.setLong(6, phoneNumber);
				statement.setString(7, name);
				statement.setString(8, password);
				int rowsAffected = statement.executeUpdate();
				return rowsAffected > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteDonor(int id) {
		try (Connection conn = DBConnection.getConnection()) {
			// Attempt to delete the donor
			String sql = "DELETE FROM donneur WHERE id = ?";
			try (PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.setInt(1, id);
				int rowsAffected = statement.executeUpdate();
				return rowsAffected > 0;
			} catch (SQLIntegrityConstraintViolationException e) {
				// If there's a constraint violation, attempt a cascade delete
				try {
					String cascadeSql = "DELETE FROM historiquedonation WHERE donneur = ?";
					try (PreparedStatement cascadeStatement = conn.prepareStatement(cascadeSql)) {
						cascadeStatement.setInt(1, id);
						int rowsAffected = cascadeStatement.executeUpdate();
						// Retry the deletion of the donor after cascade deletion
						if (rowsAffected > 0) {
							return deleteDonor(id);
						} else {
							// If no rows were deleted in the cascade, return false
							return false;
						}
					}
				} catch (SQLException ex) {
					// Handle any SQL errors during cascade delete
					ex.printStackTrace();
					return false;
				}
			}
		} catch (SQLException e) {
			// Handle any SQL errors
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateDonor(int id, String fullName, String bloodGroup, String gender, int age, String address,
			long phoneNumber) {
		String sql = "UPDATE donneur SET nom = ?, groupe_sanguin = ?, sex = ?, age = ?, adresse = ?, numtel = ? WHERE id = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, fullName);
			preparedStatement.setString(2, bloodGroup);
			preparedStatement.setString(3, gender);
			preparedStatement.setInt(4, age);
			preparedStatement.setString(5, address);
			preparedStatement.setLong(6, phoneNumber);
			preparedStatement.setInt(7, id);
			int rowsUpdated = preparedStatement.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
