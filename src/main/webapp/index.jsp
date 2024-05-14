<%
if(session.getAttribute("user") == null){
	response.sendRedirect("login.jsp");
}
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Blood Donation</title>
<!-- Favicon-->
<link rel="icon" type="image/png" href="images/logoicon.png" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/index-styles.css" rel="stylesheet" />

 <style>
    /* Style for hover effect */
    .navbar-nav .nav-link:hover {
      color: #025492 !important;
    }
    .navbar-nav .nav-link.active {
      background-color: #025492 !important;
      color: white !important;
    }
    
    .slider-wrapper {
  position: relative;
}
.slider {
  --slide-width: clamp(320px, 100vw, 2000px);
  --slide-height: clamp(240px, 100vh, 1020px);
  display: grid;
  grid-auto-flow: column;
  scroll-snap-type: x mandatory;
  max-width: var(--slide-width);
  overflow-y: hidden;
  overflow-x: auto;
  scroll-behavior: smooth;
  -ms-overflow-style: none;
  scrollbar-width: none;}

.slider::-webkit-scrollbar {
  display: none;
}
.slide {
  scroll-snap-align: start;
  width: var(--slide-width);
  height: var(--slide-height);
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}
  </style>
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg text-uppercase fixed-top"
		id="mainNav" style="background-color:red;">
		<div class="container">
			<a class="navbar-brand" href="#page-top">Blood Donation System</a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#pourquoi">Why donate blood?</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#about">About</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#contact">Make Appointment</a></li>
					<li class="nav-item mx-0 mx-lg-1"><a
						class="nav-link py-3 px-0 px-lg-3 rounded" href="#" onclick="confirmLogout()">Logout</a></li>
					
				</ul>
			</div>
		</div>
	</nav>
	<!-- Masthead-->
<header class="masthead  text-white text-center">
    <div class="slider" style="height: 90vh; width: 100%; margin-top:-120px" >
        <div class="slider">
   			<div id="slide-1" class="slide" style="flex: 1; background-image: url(images/background.jpg); background-size: cover; background-position: center;"></div>
            <div id="slide-2" class="slide" style="flex: 1; background-image: url(images/background8.png); background-size: cover; background-position: center;"></div>
            <div id="slide-3" class="slide" style="flex: 1; background-image: url(images/background6.jpg); background-size: cover; background-position: center;"></div>
    </div>
</header>
	<!-- Portfolio Section-->
	<section class="page-section portfolio" id="pourquoi">
		<div class="container">
			<!-- Portfolio Section Heading-->
			<h2
				class="page-section-heading text-center text-uppercase text-secondary mb-0">WHY DONATE BLOOD?</h2>
			<!-- Icon Divider-->
			<div class="divider-custom">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fa-solid fa-droplet"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>
			<!-- Portfolio Grid Items-->
			<div class="row justify-content-center">
				<!-- Portfolio Item 1-->
				<div class="col-md-6 col-lg-4 mb-5" style="text-align:center;">
					<div class="portfolio-item mx-auto">
						
						<img src="https://www.donneurdesang.be/icones/3-vies.svg" alt="">
                        <div class="uk-card-body">
                            <h3 class="uk-card-title h-small-uppercase">3 lives</h3>
                            <p style="font-size:18px">Because in 30 minutes, you can save 3 lives !</p>
                        </div>
					</div>
				</div>
				<!-- Portfolio Item 2-->
				<div class="col-md-6 col-lg-4 mb-5" style="text-align:center;">
					<div class="portfolio-item mx-auto">
						<img src="https://www.donneurdesang.be/icones/500000.svg" alt="">
						                        <div class="uk-card-body">
                            <h3 class="uk-card-title h-small-uppercase">500,000 pockets</h3>
                            <p style="font-size:18px">Because in Morocco, we need almost 500,000 bags a year to meet our needs.</p>
                        </div>
						
					</div>
				</div>
				<!-- Portfolio Item 3-->
				<div class="col-md-6 col-lg-4 mb-5" style="text-align:center;">
					<div class="portfolio-item mx-auto">
						<img src="https://www.donneurdesang.be/icones/1-sur-7.svg" alt="">
                        <div class="uk-card-body">
                            <h3 class="uk-card-title h-small-uppercase">1 in 7 people</h3>
                            <p style="font-size:18px">Because less than 1 in 10 people donate blood, whereas 1 in 7 will need it one day.</p>
                        </div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- About Section-->
	<section class="page-section text-white mb-0" id="about" style="background-color:red;">
		<div class="container">
			<!-- About Section Heading-->
			<h2
				class="page-section-heading text-center text-uppercase text-white">About</h2>
			<!-- Icon Divider-->
			<div class="divider-custom divider-light">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fa-solid fa-droplet"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>
			<!-- About Section Content-->
			<div class="row">
				<div class="col-lg-4 ms-auto">
					<p class="lead">Welcome to our Blood Donation System, where 
						 towards saving lives. Join us in this noble cause, 
						 empowering each other to be the lifeline for 
						 those in critical need. Together, we bridge the gap between donors and recipients</p>
				</div>
				<div class="col-lg-4 me-auto">
					<p class="lead">Our Blood Donation System connects donors 
					with those in critical need, fostering a commun
					dedicated to saving lives. Join us in this noble
					 endeavor, where every donation is a beacon of hope for someone in need.</p>
				</div>
			</div>
		</div>
	</section>
	<!-- Contact Section-->
	<section class="page-section" id="contact">
		<div class="container">
			<!-- Contact Section Heading-->
			<h2
				class="page-section-heading text-center text-uppercase text-secondary mb-0">Make an Appointment</h2>
			<!-- Icon Divider-->
			<div class="divider-custom">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fa-solid fa-droplet"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>
			<!-- Contact Section Form-->
			<div class="row justify-content-center">
				<div class="col-lg-8 col-xl-7">
					<!-- * * * * * * * * * * * * * * *-->
					<!-- * * SB Forms Contact Form * *-->
					<!-- * * * * * * * * * * * * * * *-->
					<!-- This form is pre-integrated with SB Forms.-->
					<!-- To make this form functional, sign up at-->
					<!-- https://startbootstrap.com/solution/contact-forms-->
					<!-- to get an API token!-->
					<form id="contactForm" data-sb-form-api-token="API_TOKEN">
						<!-- Name input-->
						<div class="form-floating mb-3">
							<input class="form-control" id="name" type="text"
								placeholder="Enter your name..." data-sb-validations="required" />
							<label for="name">Full name</label>
							<div class="invalid-feedback" data-sb-feedback="name:required">A
								name is required.</div>
						</div>
						<!-- Email address input-->
						<div class="form-floating mb-3">
							<input class="form-control" id="email" type="email"
								placeholder="name@example.com"
								data-sb-validations="required,email" /> <label for="email">Email
								address</label>
							<div class="invalid-feedback" data-sb-feedback="email:required">An
								email is required.</div>
							<div class="invalid-feedback" data-sb-feedback="email:email">Email
								is not valid.</div>
						</div>
						<!-- Phone number input-->
						<div class="form-floating mb-3">
							<input class="form-control" id="phone" type="tel"
								placeholder="(123) 456-7890" data-sb-validations="required" />
							<label for="phone">Phone number</label>
							<div class="invalid-feedback" data-sb-feedback="phone:required">A
								phone number is required.</div>
						</div>
						<!-- Message input-->
						<div class="form-floating mb-3">
							<textarea class="form-control" id="message" type="text"
								placeholder="Enter your message here..." style="height: 10rem"
								data-sb-validations="required"></textarea>
							<label for="message">Message</label>
							<div class="invalid-feedback" data-sb-feedback="message:required">A
								message is required.</div>
						</div>
						<!-- Submit success message-->
						<!---->
						<!-- This is what your users will see when the form-->
						<!-- has successfully submitted-->
						<div class="d-none" id="submitSuccessMessage">
							<div class="text-center mb-3">
								<div class="fw-bolder">Form submission successful!</div>
								To activate this form, sign up at <br /> <a
									href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
							</div>
						</div>
						<!-- Submit error message-->
						<!---->
						<!-- This is what your users will see when there is-->
						<!-- an error submitting the form-->
						<div class="d-none" id="submitErrorMessage">
							<div class="text-center text-danger mb-3">Error sending
								message!</div>
						</div>
						<!-- Submit Button-->
						<button class="btn btn-primary btn-xl disabled" id="submitButton"
							type="submit" style="background-color:#025492">Send</button>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- Footer-->
	<footer class="footer text-center" style="background-color:red;">
		<div class="container">
			<div class="row">
				<!-- Footer Location-->
				<div class="col-lg-4 mb-5 mb-lg-0">
					<h4 class="text-uppercase mb-4">Location</h4>
					<p class="lead mb-0">
						Riad Salam Agadir <br /> Morocco
					</p>
				</div>
				<!-- Footer Social Icons-->
				<div class="col-lg-4 mb-5 mb-lg-0">
					<h4 class="text-uppercase mb-4">Around the Web</h4>
					<a class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-facebook-f"></i></a> <a
						class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-twitter"></i></a> <a
						class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-linkedin-in"></i></a> <a
						class="btn btn-outline-light btn-social mx-1" href="#!"><i
						class="fab fa-fw fa-dribbble"></i></a>
				</div>
				<!-- Footer About Text-->
				<div class="col-lg-4">
					<h4 class="text-uppercase mb-4">About website</h4>
					<p class="lead mb-0">
						Join us in this noble cause, empowering each other to be the lifeline for those in critical need.
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- Copyright Section-->
	<div class="copyright py-4 text-center text-white">
		<div class="container">
			<small>Copyright &copy; Blood Donation System 2024. All rights reserved. | Privacy Policy | Terms of Service</small>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	<script>
	function confirmLogout() {
        var result = confirm("Are you sure you want to logout?");
        if (result) {
            window.location.href = "logout"; // Rediriger vers le servlet de déconnexion
        }
    }
	
	function autoSlide() {
	    // Récupérer toutes les diapositives
	    const slides = document.querySelectorAll('.slide');
	    const slider = document.querySelector('.slider');
	    // Récupérer l'index de la diapositive active
	    let currentIndex = 0;

	    // Fonction pour changer la diapositive
	    function changeSlide() {
	        // Masquer la diapositive actuelle
	        slides[currentIndex].style.display = 'none';
	        // Augmenter l'index ou retourner à 0 si c'est la dernière diapositive
	        currentIndex = (currentIndex + 1) % slides.length;
	        // Afficher la nouvelle diapositive
	        slides[currentIndex].style.display = 'block';
	    }

	    // Appeler la fonction pour la première fois après un délai de 3 secondes
	    setTimeout(changeSlide, 3000);

	    // Définir l'intervalle pour changer les diapositives toutes les 3 secondes
	    setInterval(changeSlide, 3000);
	}

	// Appeler la fonction quand le document est prêt
	document.addEventListener('DOMContentLoaded', autoSlide);

	</script>
	
	</body>
	</html>
