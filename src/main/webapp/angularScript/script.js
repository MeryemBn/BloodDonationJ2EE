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
	
	$scope.rendezvousList = [];
	$scope.modalVisible1 = false;
	$scope.selectedRendezvous = {};
	$scope.modalTitle1 = "";
	$scope.searchText1 = "";


	$scope.loadRendezvous = function() {
		$http.get("/BloodDonation/RendezvousController").then(function(response) {
			$scope.rendezvousList = response.data;
			console.log($scope.rendezvousList);
		});
	};

	$scope.search = function(rendezvous) {
		var id = rendezvous.id.toString();
		var name = rendezvous.name.toLowerCase();
		var searchText1 = $scope.searchText1.toLowerCase();

		if (id.includes(searchText1) || name.includes(searchText1)) {
			return true;
		}
		return false;
	};
	
	$scope.confirmDelete = function(id) {
		var result = confirm("Are you sure you want to delete this rendezvous?");
		if (result) {
			$scope.deleteRendezvous(id);
		}
	};

	$scope.deleteRendezvous = function(id) {
		$http.delete("/BloodDonation/RendezvousController?id=" + id).then(function(response) {
			$scope.loadRendezvous();
		});
	};

	$scope.newRendezvous = function() {
		$scope.modalVisible1 = "New Rendezvous";
        $scope.selectedRendezvous = {
            groupeSanguin: "A+" // Default value for blood group
        };
        $scope.modalVisible = true;
        console.log($scope.modalVisible1);
	};

	$scope.editRendezvous = function(rendezvous) {
		$scope.modalTitle1 = "Edit Rendezvous";
		$scope.selectedRendezvous = angular.copy(rendezvous);
		$scope.modalVisible1 = true;
	};

	$scope.saveRendezvous = function() {
		if ($scope.selectedRendezvous.id) {
			$http.put("/BloodDonation/RendezvousController", $scope.selectedRendezvous).then(function(response) {
				$scope.loadRendezvous();
				$scope.closeModal1();
			});
		} else {
			$http.post("/BloodDonation/RendezvousController", $scope.selectedRendezvous).then(function(response) {
				$scope.loadRendezvous();
				$scope.closeModal1();
			});
		}
	};

	$scope.closeModal1 = function() {
		$scope.modalVisible1 = false;
		$('body').removeClass('modal-open');
		$('.modal-backdrop').remove();
	};

    $scope.formatDate = function(dateString) {
    // Convertir la chaîne de date en objet Date
    var dateParts = dateString.split(' '); // Séparer la chaîne de date
    var month = $scope.getMonthIndex(dateParts[0]); // Obtenir l'index du mois à partir du nom du mois
    var day = parseInt(dateParts[1].replace(',', ''), 10); // Obtenir le jour
    var year = parseInt(dateParts[2], 10); // Obtenir l'année
    var date = new Date(year, month, day);
    // Formater la date selon le format souhaité (par exemple, "2 mai 2024")
    return date.getDate() + ' ' + $scope.getMonthName(date.getMonth()) + ', ' + date.getFullYear();
    };

   $scope.getMonthIndex = function(monthName) {
    var months = ['janvier', 'février', 'mars', 'avril', 'mai', 'juin', 'juillet', 'août', 'septembre', 'octobre', 'novembre', 'décembre'];
    return months.indexOf(monthName);
   };



	$scope.loadRendezvous();
	//Donations List
});
