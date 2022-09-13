<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
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
   	<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
	    
  </head>
  
    <script>
        $(function () {
			$("#"+$("#tab").val()).css("color", "#f85959");
        });
        
        function goCate(cate, id, type){
			$("#cate").val(cate);
			$("#keyword").val(id);
			$("#searchForm").attr("action", "/category");
			$("#searchForm").submit();
		}
        
        function goDetail(id){
			$("#cate").val("_id");
			$("#keyword").val(id);
			$("#searchForm").attr("action", "/detail");
			$("#searchForm").submit();
		}
        
        function goTab(tab){
			$("#tab").val(tab);
			$("#searchForm").attr("action", "/main");
			$("#searchForm").submit();
		}
    </script>
  
  <body>
  	<form action="#" id="searchForm" method="post">
  		<input type="hidden" name="cate" id="cate">
  		<input type="hidden" name="keyword" id="keyword">
  		<input type="hidden" name="type" id="type">
  		<input type="hidden" name="tab" id="tab" value="${tabCode }">
  	</form>
    
	<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	  <div class="container">
	    <a class="navbar-brand" href="/main">Kurly Holic</a>
	    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="oi oi-menu"></span> Menu
	    </button>
	
	    <div class="collapse navbar-collapse" id="ftco-nav">
	      <ul class="navbar-nav ml-auto">
	        <li class="nav-item"><a href="/mypage" class="nav-link"><span>마이페이지</span></a></li>
	        <li class="nav-item cta"><a href="/logout" class="nav-link"><span>로그아웃</span></a></li>
	      </ul>
	    </div>
	  </div>
	</nav>
    <!-- END nav -->
    
    <div class="hero-wrap" style="background-image: url('resources/assets/images/kurly/bg_kurly.png'); height: 470px !important; ">
    </div>
    
    <div class="col-md-12" style="padding: 2em 0 0 0;">
    	<div class="SectionTitle css-2u0lrw ej3ms6t2">
    		<div class="css-og139h e10b7g5l1">
    			<span class="css-r6zrzr ej3ms6t1">
    				<a href="#" onclick="goTab('1')" id="1">전체</a> | 
    				<a href="#" onclick="goTab('2')" id="2">푸드컬리</a> | 
    				<a href="#" onclick="goTab('3')" id="3">리빙컬리</a> </span>
    		</div>
    	</div>
    </div>
    
    <section class="ftco-section ftco-destination">
    	<div class="container">
    		<div class="row justify-content-start mb-5 pb-3">
          <div class="col-md-7 heading-section ftco-animate">
          	<span class="subheading"></span>
            <h2 class="mb-4"><strong>이 상품 어때요</strong></h2>
            <span class="justify-content-md-center align-items-md-	center" style="position: absolute; top: 13px; right: 0; font-size: 20px;">
          </div>
        </div>
    		<div class="row">
    			<div class="col-md-12">
    				<div class="destination-slider owl-carousel ftco-animate">
	    				<c:forEach var="item" items="${suggestionList.hits }">
	    					<div class="item">
			    				<div class="destination">
			    					<a href="#" class="img d-flex justify-content-center align-items-center" style="background-image: url(resources/assets/images/kurly/${item._source.category }1.jpg);" onclick="javascript:goDetail('${item._id }')">
			    						<div class="icon d-flex justify-content-center align-items-center">
			    							<span class="icon-search2"></span>
			    						</div>
			    					</a>
			    					<div class="text p-3">
			    						<h3><a href="#" onclick="javascript:goDetail('${item._id }')">${item._source.name }</a></h3>
			    						<span class="listing"><fmt:formatNumber type="number" maxFractionDigits="3" value="${item._source.reduced_price }" /> 원</span>
			    					</div>
			    				</div>
		    				</div>
		    			</c:forEach>
    				</div>
    			</div>
    		</div>
    	</div>
    </section>
    
    <c:if test="${!empty meaningList}">
	    <section class="ftco-section ftco-destination">
	    	<div class="container">
	    		<div class="row justify-content-start mb-5 pb-3">
	          <div class="col-md-11 heading-section ftco-animate">
	          	<span class="subheading"></span>
	            <h2 class="mb-4"><strong>가치소비</strong></h2>
	          </div>
	        </div>
	    		<div class="row">
	    			<div class="col-md-12">
	    				<div class="destination-slider owl-carousel ftco-animate">
		    				<c:forEach var="item" items="${meaningList.hits }">
		    					<div class="item">
				    				<div class="destination">
				    					<a href="#" class="img d-flex justify-content-center align-items-center" style="background-image: url(resources/assets/images/kurly/${item._source.category }1.jpg);" onclick="javascript:goDetail('${item._id }')">
				    						<div class="icon d-flex justify-content-center align-items-center">
				    							<span class="icon-search2"></span>
				    						</div>
				    					</a>
				    					<div class="text p-3">
				    						<h3><a href="#" onclick="javascript:goDetail('${item._id }')">${item._source.name }</a></h3>
				    						<span class="listing"><fmt:formatNumber type="number" maxFractionDigits="3" value="${item._source.reduced_price }" /> 원</span>
				    					</div>
				    				</div>
			    				</div>
			    			</c:forEach>
	    				</div>
	    			</div>
	    		</div>
	    	</div>
	    </section>
    </c:if>
    
    <section class="ftco-section ftco-destination">
    	<div class="container">
    		<div class="row justify-content-start mb-5 pb-3">
          <div class="col-md-7 heading-section ftco-animate">
          	<span class="subheading"></span>
            <h2 class="mb-4"><strong>실시간 베스트</strong></h2>
            <span class="justify-content-md-center align-items-md-	center" style="position: absolute; top: 13px; right: 0; font-size: 20px;">
          </div>
        </div>
    		<div class="row">
    			<div class="col-md-12">
    				<div class="destination-slider owl-carousel ftco-animate">
	    				<c:forEach var="item" items="${realTimeBestList.hits }">
	    					<div class="item">
			    				<div class="destination">
			    					<a href="#" class="img d-flex justify-content-center align-items-center" style="background-image: url(resources/assets/images/kurly/${item._source.category }1.jpg);" onclick="javascript:goDetail('${item._source.item_id }')">
			    						<div class="icon d-flex justify-content-center align-items-center">
			    							<span class="icon-search2"></span>
			    						</div>
			    					</a>
			    					<div class="text p-3">
			    						<h3><a href="#" onclick="javascript:goDetail('${item._source.item_id }')">${item._source.item_name }</a></h3>
			    						<span class="listing"><fmt:formatNumber type="number" maxFractionDigits="3" value="${item._source.reduced_price }" /> 원</span>
			    					</div>
			    				</div>
		    				</div>
		    			</c:forEach>
    				</div>
    			</div>
    		</div>
    	</div>
    </section>
    
    <c:forEach var="info" items="${categoryList }">
	    <section class="ftco-section ftco-destination">
	    	<div class="container">
	    		<div class="row justify-content-start mb-5 pb-3">
	          <div class="col-md-11 heading-section ftco-animate">
	          	<span class="subheading"></span>
	            <h2 class="mb-4"><strong>${info.key }</strong></h2>
	            <span class="justify-content-md-center align-items-md-center main-btn" style="position: absolute; top: 13px; right: 0; font-size: 20px;">
				<a href="#" class="main-btn" onclick="javascript:goCate('category', '${info.key }', 'term')" style="font-weight: bold;">더보기</a></span>
	          </div>
	        </div>
	    		<div class="row">
	    			<div class="col-md-12">
	    				<div class="destination-slider owl-carousel ftco-animate">
		    				<c:forEach var="item" items="${info.value.hits }">
		    					<div class="item">
				    				<div class="destination">
				    					<a href="#" class="img d-flex justify-content-center align-items-center" style="background-image: url(resources/assets/images/kurly/${item._source.category }1.jpg);" onclick="javascript:goDetail('${item._id }')">
				    						<div class="icon d-flex justify-content-center align-items-center">
				    							<span class="icon-search2"></span>
				    						</div>
				    					</a>
				    					<div class="text p-3">
				    						<h3><a href="#" onclick="javascript:goDetail('${item._id }')">${item._source.name }</a></h3>
				    						<span class="listing"><fmt:formatNumber type="number" maxFractionDigits="3" value="${item._source.reduced_price }" /> 원</span>
				    					</div>
				    				</div>
			    				</div>
			    			</c:forEach>
	    				</div>
	    			</div>
	    		</div>
	    	</div>
	    </section>
    </c:forEach>

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
  <script src="resources/assets/https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="resources/assets/js/google-map.js"></script>
  <script src="resources/assets/js/main.js"></script>
  </body>
</html>