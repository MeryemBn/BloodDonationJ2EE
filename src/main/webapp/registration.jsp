<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>SIGN UP</title>
<link rel="icon" type="image/png" href="images/logoicon.png" />

<!-- Font Icon -->
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/register.css">
</head>
<body>
	<input type="hidden" id="status"
		value="<%=request.getAttribute("status")%>">

	<div class="main">

		<!-- Sign up form -->
		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>

						<form method="post" action="register" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="full-name"><i
									class="zmdi zmdi-account material-icons-name"></i></label> <input
									type="text" name="full-name" id="full-name"
									placeholder="Your Full Name" />
							</div>

							<div class="form-group">
								<label for="bloodgroup"><i
									class="fa-sharp fa-solid fa-droplet"
									style="margin-bottom: 25px;"></i></label> <select
									style="padding-left: 35px; background: none;" name="bloodgroup"
									id="bloodgroup" class="select">
									<option value="" disabled selected>Select your Blood
										Group</option>
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

							<!-- Modifier le champ gender pour utiliser la classe radio-group -->
							<div class="form-group" style="margin-top: -25px;">
								<label
									style="margin-top: -25px; font-size: 14px; font-weight: bold">Gender:</label><br>
								<div class="radio-group"
									style="display: flex; width: 80%; margin: 14px 0; justify-content: space-between;">
									<input type="radio" id="male" name="gender" value="male"
										style="margin-bottom: 22px;"> <label for="male"
										style="margin-left: 65px;">Male</label> <input type="radio"
										id="female" name="gender" value="female"
										style="margin-left: 25px; margin-bottom: 20px;"> <label
										for="female" style="margin-left: 200px;">Female</label>
								</div>
							</div>

							<div class="form-group" style="margin-top: -25px;">
								<label for="age"><i class="zmdi zmdi-calendar"></i></label> <input
									type="number" name="age" id="age" placeholder="Your Age" />
							</div>

							<div class="form-group">
								<label for="address"><i class="zmdi zmdi-pin"></i></label> <input
									type="text" name="address" id="address"
									placeholder="Your Address" />
							</div>

							<div class="form-group">
								<label for="phone"><i class="zmdi zmdi-phone"></i></label> <input
									type="tel" name="phone" id="phone"
									placeholder="Your Phone Number" />
							</div>

							<div class="form-group">
								<label for="username"><i class="zmdi zmdi-account-box"></i></label>
								<input type="text" name="username" id="username"
									placeholder="Your Username" />
							</div>

							<div class="form-group">
								<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
									type="password" name="password" id="password"
									placeholder="Password" />
							</div>

							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup4.png" alt="sing up image">
						</figure>
						<a href="login.jsp" class="signup-image-link">I am already
							member</a>
					</div>
				</div>
			</div>
		</section>


	</div>
	<!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

	<script type="text/javascript">
		var status = document.getElementById("status").value;
		if (status == "success") {
			swal("Congrats", "Account Created Successfuly", "success");
		} else if (status == "failed") {
			swal("Error", "An error occured while creating your account",
					"error");
		}
	</script>

</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
