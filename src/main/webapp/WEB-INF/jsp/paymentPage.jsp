<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/navBar.css">
</head>
<body >

    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary w-100">
            <a class="navbar-brand">Car Rent</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link linkClass" href="carrent">Rent a Car</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">User Profile</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="user-listed-cars">Listed Cars</a>
                            <a class="dropdown-item" href="user-booked-cars">Booked Cars </a>
                            <a class="dropdown-item" href="userUpdateProfile">Update User Profile</a>
                            <a class="dropdown-item" href="update-password">Update User Password</a>
                        </div>
                    </li>
                </ul>
                <br/>
                <form class="form-inline" action="/logout" method="post">
                    <button class="btn btn-danger" type="submit">Logout</button>
                </form>
            </div>
        </nav>
        <form name="paymentForm" action="bookCar" method="post">
            <input type="hidden" name="fromDate" value=${bookingData.fromDate}>
            <input type="hidden" name="toDate" value=${bookingData.toDate}>
            <input type="hidden" name="userId" value=${bookingData.userId}>
            <input type="hidden" name="carId" value=${bookingData.carId}>

            <h1 style="text-align: center">Payment Page</h1><hr>
            <br/>
            <div class="row ">
                <div class="col-lg-12">
                    <div class="row ">
                        <div class="col-lg-4">
                            <div class="card bg-light" style="width:350px">
                                    <h4 class="card-title">User Details</h4>
                                    <p class="card-text"><b>Name:</b>${userData.name}</p>
                                    <p class="card-text"><b>Email:</b>${userData.email} </p>
                                <hr>
                                    <h4>Car Details</h4>
                                    <p class="card-text"><b>Car model:</b>${carData.model} </p>
                                    <p class="card-text"><b>Car description:</b>${carData.description}</p>
                                    <p class="card-text"><b>Booking Date:</b>${bookingData.fromDate}</p> to ${bookingData.toDate}
                                </div>

                        </div>
                        <div class="col-lg-8">
                            <div class="form-group">
                                <label for="customerName">Customer Name</label>
                                <input type="text" class="form-control" id="customerName" name="customerName" value="${paymentDetails.customerName}" required>
                            </div>
                            <div class="form-group">
                                <label for="cardType">Card Type:</label>
                                <select name="cardType" id="cardType" class="form-control">
                                    <option value="1">Visa</option>
                                    <option value="2">Master Card</option>
                                    <option value="3">American Express</option>
                                    <option value="4">Discover</option>
                                </select>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label for="expiryMonth">Expiry Month:</label>
                                        <select name="cardType" id="expiryMonth" class="form-control">
                                            <option value="1">January</option>
                                            <option value="2">February</option>
                                            <option value="3">March</option>
                                            <option value="4">April</option>
                                            <option value="5">May</option>
                                            <option value="6">June</option>
                                            <option value="7">July</option>
                                            <option value="8">August</option>
                                            <option value="9">September</option>
                                            <option value="10">October</option>
                                            <option value="11">November</option>
                                            <option value="12">December</option>
                                        </select>

                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label for="expiryYear">Expiry Year:</label>
                                        <select name="cardType" id="expiryYear" class="form-control">
                                            <option value="1">2019</option>
                                            <option value="2">2020</option>
                                            <option value="3">2021</option>
                                            <option value="4">2022</option>
                                            <option value="5">2023</option>
                                            <option value="6">2024</option>
                                            <option value="7">2025</option>
                                            <option value="8">2026</option>
                                            <option value="9">2027</option>
                                            <option value="10">2028</option>
                                            <option value="11">2029</option>
                                            <option value="12">2030</option>
                                        </select>
                                    </div>
                                </div>

                            </div>

                            <div class="form-group">
                                <label for="cardNumber">Card Number:</label>
                                <input type="number" class="form-control" id="cardNumber" name="cardNumber" value="${paymentDetails.cardNumber}" maxlength="16" required>
                            </div>
                            <p style="color: red">${cardNumberError}</p>
                            <div>
                                <input type="submit" class="btn btn-primary" value="Pay">
                            </div>
                        </div>
                    </div>

                    <br/>
                </div>
            </div>

        </form>
    </div>
    <div class="footer tempClass" style="margin-top:190px;">
        <h6 class="text-center">Car Rent</h6>
        <div class="footer-copyright text-center py-3">© 2019 Copyright:
            <a action="/homePage"> CarRent.com</a>
        </div>
    </div>
<script src="/js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>