<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="models.DashBloodGroupService" %>
<%@ page import="models.DashAppointmentService" %>
<%@ page import="java.util.List" %>
<%
if (session.getAttribute("user") == null) {
	response.sendRedirect("login.jsp");
}
%>

<!DOCTYPE html>
<html lang="en" ng-app="adminApp">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Blood Donation | Admin</title>
<link rel="icon" type="image/png" href="images/logoicon.png" />
<!-- ======= Styles ====== -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<link rel="stylesheet" href="css/adminCss.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<style>
	     .hidden {
	         display: none;
	      }
	      .myProject-graphBox{
			position: relative;
			width: 100%;
			padding : 20px;
			display: grid;
			grid-template-columns: 1fr 2fr;
			grid-gap: 30px;
			min-height:200px ;
		  }
			.myProject-graphBox .myProject-box{
				position:relative;
				background:#fff;
				width: 100%;
				padding : 20px;
				box-shadow: 0 7px 25px rgba(0,0,0,0.08);
				border-radius: 20px;
			}
	</style>
	
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Blood Group');
        data.addColumn('number', 'Quantity');
        
        var dataListJson = '<%= request.getAttribute("dataListJson") %>';
        var dataList = JSON.parse(dataListJson); // Parse JSON string into JavaScript object
        console.log(dataList);


        // Populate the chart data
        if (Array.isArray(dataList)) { // Check if dataList is an array
            dataList.forEach(function(item) {
                data.addRow([item.groupeSanguin, item.qte]);
            });
        }

        var options = {
        		 title: 'Blood Group Distribution', // Title text
        		    width: 900,
        		    height: 500, 
        		    is3D: true,
        		    titleTextStyle: {
        		        color: '#3366cc', 
        		        fontSize: 28, 
        		        bold: true, 
        		        italic: false 
        		    }        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
        
        console.log(dataList);

    }
</script>

</head>
<body>
	<!-- =============== Navigation ================ -->
	<div class="myProject-container">
		<div class="myProject-navigation">
			<ul>
				<li><a href="#"><span class="icon"></span><span
						class="title">Blood Donation</span></a></li>
				<li><a href="dashboard" onclick="showSection('dashboard')"><span
						class="icon"><ion-icon name="home-outline"></ion-icon></span><span
						class="title">Dashboard</span></a></li>
				<li><a href="#" onclick="showSection('donors')"><span
						class="icon"><ion-icon name="people-outline"></ion-icon></span><span
						class="title">Donors</span></a></li>
				<li><a href="#" onclick="showSection('appointments')"><span
						class="icon"><ion-icon name="calendar-outline"></ion-icon></span><span
						class="title">Appointments</span></a></li>
				<li><a href="#" onclick="showSection('donations')"><span
						class="icon"><ion-icon name="heart-circle-outline"></ion-icon></span><span
						class="title">Donations</span></a></li>
				<li><a href="#" onclick="confirmLogout()"><span
						class="icon"><ion-icon name="log-out-outline"></ion-icon></span><span
						class="title">Log Out</span></a></li>
			</ul>
		</div>

		<!-- ========================= Main ==================== -->
		<div class="myProject-main">
			<div class="myProject-topbar">
				<div class="myProject-toggle" >
					<ion-icon name="menu-outline"></ion-icon>
				</div>
			</div>

			<!-- ======================= Cards ================== -->
			<section id="dashboard" class="mainsection">
            <div class="myProject-cardBox">
                <div class="myProject-card">
                    <div>
                        <div class="numbers"><%= session.getAttribute("donorCount")%></div>
                        <div class="myProject-cardName">Donors</div>
                    </div>

                    <div class="myProject-iconBx">
                        <ion-icon name="people-outline"></ion-icon>
                    </div>
                </div>

                <div class="myProject-card">
                    <div>
                        <div class="numbers"><%= session.getAttribute("bloodGroupCount") %></div>
                        <div class="myProject-cardName">Blood Group</div>
                    </div>

                    <div class="myProject-iconBx">
                        <i class="fa-solid fa-droplet"></i>
                    </div>
                </div>

                <div class="myProject-card">
                    <div>
                        <div class="numbers"><%= session.getAttribute("appointmentCount") %></div>
                        <div class="myProject-cardName">Appointment</div>
                    </div>

                    <div class="myProject-iconBx">
                        <ion-icon name="calendar-outline"></ion-icon>
                    </div>
                </div>

                <div class="myProject-card">
                    <div>
                        <div class="numbers"><%= session.getAttribute("donationCount") %></div>
                        <div class="myProject-cardName">Donations</div>
                    </div>

                    <div class="myProject-iconBx">
                        <ion-icon name="heart-circle-outline"></ion-icon>
                    </div>
                </div>
            </div>
            
            <div class="myProject-graphBox">
            <div class="myProject-box">
            	<div id="piechart_3d" style="width: 600px; height: 500px;"></div>
            </div>
            <div class="myProject-box">
			    <h2 class="text-center" style="color:#3366cc; padding-bottom:20px;">Appointments of this week</h2>
			    <table class="table table-bordered text-center"> <!-- Added 'text-center' class to center the table content -->
			        <thead>
			            <tr>
			                <th>Last Name</th>
			                <th>First Name</th>
			                <th>Blood Group</th>
			                <th>Date</th>
			                <th>Time</th>
			                <th>Status</th>
			            </tr>
			        </thead>
			        <tbody>
			            <% @SuppressWarnings("unchecked")
			            List<DashAppointmentService> rendezVousList = (List<DashAppointmentService>) request.getAttribute("rendezVousList");
			            for (DashAppointmentService rendezVous : rendezVousList) { %>
			                <tr>
			                    <td><%= rendezVous.getNom() %></td>
			                    <td><%= rendezVous.getPrenom() %></td>
			                    <td><%= rendezVous.getGroupeSanguin() %></td>
			                    <td><%= rendezVous.getDateRendezVous() %></td>
			                    <td><%= rendezVous.getHeureRendezVous() %></td>
			                    <td style="color: <%= rendezVous.getStatus().equals("done") ? "#006b21" : "grey" %>;
			                    		background-color: <%= rendezVous.getStatus().equals("done") ? "#86e49d" : "#ebc474" %>;
			                    		   padding: .5rem 0;
			                    		   margin: 5px;
										    border-radius: 2rem;
										    text-align: center;">
			                        <%= rendezVous.getStatus() %>
			                    </td>
			                </tr>
			            <% } %>
			        </tbody>
			    </table> 
			</div>

            </div>
            </section>
			<!-- ======================= DONORS DETAILS ================== -->
			<section id="donors" class="mainsection hidden"
				ng-controller="donorController">
				<div class="container mt-5">
					<h2 class="text-center mb-5"
						style="font-family: Arial, sans-serif; color: #025492; text-shadow: 1px 1px 2px #ccc; font-size: 4rem">Donors
						List</h2>
					<div class="row mb-3">
						<div class="col-md-6">
							<input type="text" class="form-control"
								placeholder="Search by ID or Name" ng-model="searchText">
						</div>
						<div class="col-md-6 text-right">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#exampleModalCenter" ng-click="newDonor()">Add
								Donor</button>
						</div>
					</div>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Blood Group</th>
								<th>Gender</th>
								<th>Age</th>
								<th>Address</th>
								<th>Phone Number</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="donor in donors | filter:search">
								<td>{{ donor.id }}</td>
								<td>{{ donor.name }}</td>
								<td>{{ donor.bloodGroup }}</td>
								<td>{{ donor.gender }}</td>
								<td>{{ donor.age }}</td>
								<td>{{ donor.address }}</td>
								<td>{{ donor.phoneNumber }}</td>
								<td>
									<button class="btn btn-warning" data-toggle="modal"
										data-target="#exampleModalCenter" ng-click="editDonor(donor)">Edit</button>
									<button class="btn btn-danger"
										ng-click="confirmDelete(donor.id)">Delete</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- Donor Modal For Add AND Edit-->
				<div class="modal" id="exampleModalCenter" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalCenterTitle"
					aria-hidden="true" ng-show="modalVisible">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">{{ modalTitle }}</h5>
								<button type="button" class="close" ng-click="closeModal()">&times;</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-group">
										<label>Name</label> <input type="text" class="form-control"
											ng-model="selectedDonor.name">
									</div>
									<div class="form-group">
										<label>Blood Group</label> <select class="form-control"
											ng-model="selectedDonor.bloodGroup">
											<option value="A+">A+</option>
											<option value="A-">A-</option>
											<option value="B+">B+</option>
											<option value="B-">B-</option>
											<option value="AB+">AB+</option>
											<option value="AB-">AB-</option>
											<option value="O+">O+</option>
											<option value="O-">O-</option>
										</select>
									</div>
									<div class="form-group">
										<label>Gender</label><br>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio"
												ng-model="selectedDonor.gender" value="Male"> <label
												class="form-check-label">Male</label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio"
												ng-model="selectedDonor.gender" value="Female"> <label
												class="form-check-label">Female</label>
										</div>
									</div>
									<div class="form-group">
										<label>Age</label> <input type="number" class="form-control"
											ng-model="selectedDonor.age">
									</div>
									<div class="form-group">
										<label>Address</label> <input type="text" class="form-control"
											ng-model="selectedDonor.address">
									</div>
									<div class="form-group">
										<label>Phone Number</label> <input type="text"
											class="form-control" ng-model="selectedDonor.phoneNumber">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									ng-click="closeModal()">Close</button>
								<button type="button" class="btn btn-primary"
									ng-click="saveDonor()">Save</button>
							</div>
						</div>
					</div>
				</div>

			</section>
			
			<section id="appointments" class="mainsection hidden" ng-controller="AppointmentController" >
						<div class="container mt-5">
		        <h2 class="text-center mb-5"
		            style="font-family: Arial, sans-serif; color: #025492; text-shadow: 1px 1px 2px #ccc; font-size: 4rem">Rendezvous
		            List</h2>
		        <div class="row mb-3">
		            <div class="col-md-6">
		                <input type="text" class="form-control" placeholder="Search by ID or Name" ng-model="searchText">
		            </div>
		            <div class="col-md-6 text-right">
		                <button class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter"
		                    ng-click="newRendezvous()">Add Rendezvous</button>
		            </div>
		        </div>
		        <table class="table table-bordered">
		            <thead>
		                <tr>
		                    <th>ID</th>
		                    <th>Name</th>
		                    <th>Blood Group</th>
		                    <th>Date</th>
		                    <th>Time</th>
		                    <th>Actions</th>
		                </tr>
		            </thead>
		            <tbody>
		                 <tr ng-repeat="rendezvous in rendezvousList | filter:searchText1">
		            <td>{{ rendezvous.id }}</td>
		            <td>{{ rendezvous.nom + ' ' + rendezvous.prenom }}</td>
		            <td>{{ rendezvous.groupe_sanguin }}</td>
		            <td>{{ rendezvous.date_rendezvous }}</td>
		            <td>{{ rendezvous.heure_rendezvous }}</td>
		            <td>
		                <button class="btn btn-warning" data-toggle="modal" data-target="#exampleModalCenter" ng-click="editRendezvous(rendezvous)">Edit</button>
		                <button class="btn btn-danger" ng-click="confirmDelete(rendezvous.id)">Delete</button>
		            </td>
		        </tr>
		
		            </tbody>
		        </table>
		    </div>
		
		    <!-- Rendezvous Modal For Add AND Edit-->
		    <div class="modal" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
		        aria-hidden="true" ng-show="modalVisible1">
		        <div class="modal-dialog">
		            <div class="modal-content">
		                <div class="modal-header">
		                    <h5 class="modal-title">{{ modalTitle }}</h5>
		                    <button type="button" class="close" ng-click="closeModal1()">&times;</button>
		                </div>
		                <div class="modal-body">
		                    <form>
		                        <div class="form-group">
		                            <label>Name</label>
		                            <input type="text" class="form-control" ng-model="selectedRendezvous.nom">
		                        </div>
		                        <div class="form-group">
		                            <label>Blood Group</label>
		                            <select class="form-control" ng-model="selectedRendezvous.groupe_sanguin">
		                                <option value="A+">A+</option>
		                                <option value="A-">A-</option>
		                                <option value="B+">B+</option>
		                                <option value="B-">B-</option>
		                                <option value="AB+">AB+</option>
		                                <option value="AB-">AB-</option>
		                                <option value="O+">O+</option>
		                                <option value="O-">O-</option>
		                            </select>
		                        </div>
		                        <div class="form-group">
		                            <label>Date</label>
		                            <input type="date" class="form-control" ng-model="selectedRendezvous.date_rendezvous">
		                        </div>
		                        <div class="form-group">
		                            <label>Time</label>
		                            <input type="time" class="form-control" ng-model="selectedRendezvous.heure_rendezvous">
		                        </div>
		                    </form>
		                </div>
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-secondary" ng-click="closeModal1()">Close</button>
		                    <button type="button" class="btn btn-primary" ng-click="saveRendezvous()">Save</button>
		                </div>
		            </div>
		        </div>
		    </div>
		    	
						
			</section>
			
			
			<section id="donations" class="mainsection hidden" ng-controller="donationController">
			    <div class="container mt-5">
			        <h2 class="text-center mb-5"
			            style="font-family: Arial, sans-serif; color: #025492; text-shadow: 1px 1px 2px #ccc; font-size: 4rem">Donations List</h2>
			        <div class="row mb-3">
			            <div class="col-md-6">
			                <input type="text" class="form-control"
			                    placeholder="Search by Donor Name" ng-model="searchTextDonations">
			            </div>
			            <div class="col-md-6 text-right">
			                <button class="btn btn-primary" data-toggle="modal" data-target="#donationModal" ng-click="newDonation()">Add Donation</button>
			            </div>
			        </div>
			        <!-- Donations Table -->
			        <table class="table table-bordered">
			            <thead>
			                <tr>
			                    <th>ID</th>
			                    <th>Donor Name</th>
			                    <th>Blood Group</th>
			                    <th>Date</th>
			                    <th>Time</th>
			                    <th>Amount Donated</th>
			                    <th>Actions</th>
			                </tr>
			            </thead>
			            <tbody>
			                <tr ng-repeat="donation in donations | filter:searchDonations">
			                    <td>{{ donation.id }}</td>
			                    <td>{{ donation.donorName }}</td>
			                    <td>{{ donation.bloodGroup }}</td>
			                    <td>{{ donation.date }}</td>
			                    <td>{{ donation.time }}</td>
			                    <td>{{ donation.AmountDonated }}</td>
			                    <td>
			                        <button class="btn btn-warning" data-toggle="modal" data-target="#donationModal" ng-click="editDonation(donation)">Edit</button>
			                    </td>
			                </tr>
			            </tbody>
			        </table>
			    </div>
			
			    <!-- Donation Modal For Add AND Edit -->
			    <div class="modal" id="donationModal" tabindex="-1" role="dialog"
			        aria-labelledby="exampleModalCenterTitle" aria-hidden="true"
			        ng-show="modalVisibleDonation">
			        <div class="modal-dialog">
			            <div class="modal-content">
			                <div class="modal-header">
			                    <h5 class="modal-title">{{ modalTitleDonation }}</h5>
			                    <button type="button" class="close" ng-click="closeModalDonation()">&times;</button>
			                </div>
			                <div class="modal-body">
			                    <form>
			                        <div class="form-group">
			                            <label for="donorName">Donor Name</label>
			                            <select class="form-control" ng-model="selectedDonation.donorId" ng-change="updateBloodGroup()" ng-options="donor.id as donor.name for donor in donorNames">
			                                <option value="">Select Donor</option>
			                            </select>
			                        </div>
			                        <div class="form-group">
			                            <label for="bloodGroup">Blood Group</label>
			                            <input type="text" class="form-control" ng-model="selectedDonation.bloodGroup" readonly>
			                        </div>
			                        <div class="form-group">
			                            <label for="amountDonated">Amount Donated</label>
			                            <input type="number" class="form-control" ng-model="selectedDonation.AmountDonated">
			                        </div>
			                    </form>
			                </div>
			                <div class="modal-footer">
			                    <button type="button" class="btn btn-secondary" ng-click="closeModalDonation()">Close</button>
			                    <button type="button" class="btn btn-primary" ng-click="saveDonation()">Save</button>
			                </div>
			            </div>
			        </div>
			    </div>
			</section>

		</div>
	</div>

	<!-- =========== Scripts =========  -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	<script src="js/adminJS.js"></script>
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script src="./angularScript/script.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</body>
</html>
