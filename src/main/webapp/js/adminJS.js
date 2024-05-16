// add hovered class to selected list item
let list = document.querySelectorAll(".myProject-navigation li");

function activeLink() {
  list.forEach((item) => {
    item.classList.remove("hovered");
  });
  this.classList.add("hovered");
}

list.forEach((item) => item.addEventListener("mouseover", activeLink));

// Menu Toggle
let toggle = document.querySelector(".myProject-toggle");
let navigation = document.querySelector(".myProject-navigation");
let main = document.querySelector(".myProject-main");

toggle.onclick = function (){
  navigation.classList.toggle("active");
  main.classList.toggle("active");
};
function showSection(sectionId) {
            // Hide all sections
            var sections = document.getElementsByClassName('mainsection');
            for (var i = 0; i < sections.length; i++) {
                sections[i].classList.add('hidden');
            }

            // Show the selected section
            var selectedSection = document.getElementById(sectionId);
            if (selectedSection) {
                selectedSection.classList.remove('hidden');
            }
        }

 function confirmLogout() {
        var result = confirm("Are you sure you want to logout?");
        if (result) {
            window.location.href = "logout"; // Rediriger vers le servlet de déconnexion
        }
    }
    
