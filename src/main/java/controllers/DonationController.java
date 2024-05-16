package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Donation;
import models.DonationService;

public class DonationController extends HttpServlet {
	
	private DonationService donationService;

	@Override
	public void init() throws ServletException {
		super.init();
		donationService = new DonationService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Donation> donations = donationService.getAllDonations();
		Gson gson = new Gson();
		String jsonData = gson.toJson(donations);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonData);
		out.flush();
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        Donation donation = gson.fromJson(request.getReader(), Donation.class);
        donation.setBloodGroup(donationService.getBloodGroupByDonorId(donation.getDonorId()));
        boolean success = donationService.addDonation(donation);
        response.setStatus(success ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        Donation donation = gson.fromJson(request.getReader(), Donation.class);
        donation.setBloodGroup(donationService.getBloodGroupByDonorId(donation.getDonorId()));
        boolean success = donationService.updateDonation(donation);
        response.setStatus(success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
    }
}
