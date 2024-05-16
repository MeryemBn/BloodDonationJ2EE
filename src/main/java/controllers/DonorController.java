package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Donor;
import models.DonorService;

public class DonorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DonorService donorService;

	@Override
	public void init() throws ServletException {
		super.init();
		donorService = new DonorService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Donor> donors = donorService.getDonors();
		Gson gson = new Gson();
		String jsonData = gson.toJson(donors);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonData);
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		Donor donor = gson.fromJson(request.getReader(), Donor.class);
		boolean success = donorService.insertDonor(donor.getName(), donor.getBloodGroup(), donor.getGender(),
				donor.getAge(), donor.getAddress(), donor.getPhoneNumber());
		response.setStatus(success ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_BAD_REQUEST);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		Donor donor = gson.fromJson(request.getReader(), Donor.class);
		boolean success = donorService.updateDonor(donor.getId(), donor.getName(), donor.getBloodGroup(),
				donor.getGender(), donor.getAge(), donor.getAddress(), donor.getPhoneNumber());
		response.setStatus(success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean success = donorService.deleteDonor(id);
		response.setStatus(success ? HttpServletResponse.SC_NO_CONTENT : HttpServletResponse.SC_BAD_REQUEST);
	}

}
