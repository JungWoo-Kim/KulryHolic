<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
  <head>
    <title>Kurly Holic</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Alex+Brush" rel="stylesheet">

    <link rel="stylesheet" href="resources/assets/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="resources/assets/css/animate.css">
    
    <link rel="stylesheet" href="resources/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="resources/assets/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="resources/assets/css/magnific-popup.css">

    <link rel="stylesheet" href="resources/assets/css/aos.css">

    <link rel="stylesheet" href="resources/assets/css/ionicons.min.css">

    <link rel="stylesheet" href="resources/assets/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="resources/assets/css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="resources/assets/css/flaticon.css">
    <link rel="stylesheet" href="resources/assets/css/icomoon.css">
    <link rel="stylesheet" href="resources/assets/css/style.css">
    
    <link rel="stylesheet" href="resources/assets/css/kurly/kurly.css">
    <link rel="stylesheet" href="resources/assets/css/kurly/kurlyCopy.css">
    <link rel="stylesheet" href="resources/assets/css/kurly/picklist.css">
   	<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
  </head>
  <script>
	$(function () {
		$("#goLogin").on("click", function(){
			if($("#id").val() == ""){
				alert("id를 입력해주세요");
				$("#id").focus();
				return;
			}
			
			if($("#password").val() == ""){
				alert("id를 입력해주세요");
				$("#password").focus();
				return;
			}
			
			$.ajax({
				type:"post",
				url:"/member/login",
				data:$("#loginForm").serialize(),
				success:function(data){
					if(data.code == "0000"){
						location.href = '/main';
					} else{
						alert(data.message);
					}
				}
			});
		});
	});
  </script>
  <body>
  	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="/">Kurly Holic</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>
	    </div>
	  </nav>
    <!-- END nav -->
    
    <div class="hero-wrap" style="background-image: url('resources/assets/images/kurly/bg_kurly.png'); height: 470px !important; ">
    </div>


    <section class="ftco-section ftco-degree-bg">
      <div class="container">
        <div class="row">
          <div class="col-md-12 ftco-animate">
			<div class="css-1bb6q2p etkckst1">
				<div class="css-a7gihu etkckst0">로그인</div>
				<div class="css-zmiiuu e11xlc5p0">
					<form id="loginForm">
						<div class="css-46b038 e18ap6t76">
							<div class="css-1accgqb e1uzxhvi6">
								<div class="css-176lya2 e1uzxhvi3">
									<input data-testid="input-box" name="id" id="id" placeholder="아이디를 입력해주세요" type="text" class="css-1bkd15f e1uzxhvi2" value="">
								</div>
							</div>
							<div class="css-1accgqb e1uzxhvi6">
								<div class="css-176lya2 e1uzxhvi3">
									<input data-testid="input-box" name="password" id="password" placeholder="비밀번호를 입력해주세요" type="password" class="css-1bkd15f e1uzxhvi2" value="">
								</div>
							</div>
						</div>
						<div class="css-s4i9n2 e18ap6t71">
							<button class="css-qaxuc4 e4nu7ef3" type="button" height="54" radius="3" id="goLogin">
								<span class="css-ymwvow e4nu7ef1">로그인</span>
							</button>
						</div>
					</form>
				</div>
			</div>
		  </div> <!-- .col-md-8 -->
        </div>
      </div>
    </section> <!-- .section -->

    <footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">dirEngine</h2>
              <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
              <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-5">
              <h2 class="ftco-heading-2">Information</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">About</a></li>
                <li><a href="#" class="py-2 d-block">Service</a></li>
                <li><a href="#" class="py-2 d-block">Terms and Conditions</a></li>
                <li><a href="#" class="py-2 d-block">Become a partner</a></li>
                <li><a href="#" class="py-2 d-block">Best Price Guarantee</a></li>
                <li><a href="#" class="py-2 d-block">Privacy and Policy</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Customer Support</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">FAQ</a></li>
                <li><a href="#" class="py-2 d-block">Payment Option</a></li>
                <li><a href="#" class="py-2 d-block">Booking Tips</a></li>
                <li><a href="#" class="py-2 d-block">How it works</a></li>
                <li><a href="#" class="py-2 d-block">Contact Us</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
            	<h2 class="ftco-heading-2">Have a Questions?</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@yourdomain.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="resources/assets/js/jquery.min.js"></script>
  <script src="resources/assets/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="resources/assets/js/popper.min.js"></script>
  <script src="resources/assets/js/bootstrap.min.js"></script>
  <script src="resources/assets/js/jquery.easing.1.3.js"></script>
  <script src="resources/assets/js/jquery.waypoints.min.js"></script>
  <script src="resources/assets/js/jquery.stellar.min.js"></script>
  <script src="resources/assets/js/owl.carousel.min.js"></script>
  <script src="resources/assets/js/jquery.magnific-popup.min.js"></script>
  <script src="resources/assets/js/aos.js"></script>
  <script src="resources/assets/js/jquery.animateNumber.min.js"></script>
  <script src="resources/assets/js/bootstrap-datepicker.js"></script>
  <script src="resources/assets/js/jquery.timepicker.min.js"></script>
  <script src="resources/assets/js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="resources/assets/js/google-map.js"></script>
  <script src="resources/assets/js/main.js"></script>
    
  </body>
</html>