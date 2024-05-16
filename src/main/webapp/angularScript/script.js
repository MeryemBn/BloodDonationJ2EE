var app = angular.module("adminApp", []);

app.controller("donorController", function($scope, $http) {
	
	// Donors List
	$scope.donors = [];
	$scope.modalVisible = false;
	$scope.selectedDonor = {};
	$scope.modalTitle = "";
	$scope.searchText = "";


	$scope.loadDonors = function() {
		$http.get("/BloodDonation/DonorController").then(function(response) {
			$scope.donors = response.data;
			console.log($scope.donors);

		});
	};

	$scope.search = function(donor) {
		var id = donor.id.toString();
		var name = donor.name.toLowerCase();
		var searchText = $scope.searchText.toLowerCase();

		if (id.includes(searchText) || name.includes(searchText)) {
			return true;
		}
		return false;
	};
	
	$scope.confirmDelete = function(id) {
		var result = confirm("Are you sure you want to delete this donor?");
		if (result) {
			$scope.deleteDonor(id);
		}
	};

	$scope.deleteDonor = function(id) {
		$http.delete("/BloodDonation/DonorController?id=" + id).then(function(response) {
			$scope.loadDonors();
		});
	};

	$scope.newDonor = function() {
		$scope.modalTitle = "New Donor";
        $scope.selectedDonor = {
            gender: "Male", // Default value for gender
            bloodGroup: "A+" // Default value for blood group
        };
        $scope.modalVisible = true;
        console.log($scope.modalVisible);
	};

	$scope.editDonor = function(donor) {
		$scope.modalTitle = "Edit Donor";
		$scope.selectedDonor = angular.copy(donor);
		$scope.modalVisible = true;
	};

	$scope.saveDonor = function() {
		if ($scope.selectedDonor.id) {
			$http.put("/BloodDonation/DonorController", $scope.selectedDonor).then(function(response) {
				$scope.loadDonors();
				$scope.closeModal();
			});
		} else {
			$http.post("/BloodDonation/DonorController", $scope.selectedDonor).then(function(response) {
				$scope.loadDonors();
				$scope.closeModal();
			});
		}
	};

	$scope.closeModal = function() {
		$scope.modalVisible = false;
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
	};

	$scope.loadDonors();
	
	//Appointment List
	//Donations List
});
