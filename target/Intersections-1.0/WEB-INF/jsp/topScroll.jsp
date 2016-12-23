<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- === BEGIN HEADER === -->
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <head>
        <!-- Title -->
        <title>Baza semaforizovanih raskrsnica</title>
        <!-- Meta -->
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <!-- Favicon -->
        <link rel="shortcut icon" href="/assets/img/traffic.png">
        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" href="/assets/css/bootstrap.css">
        <!-- Template CSS -->
        <link rel="stylesheet" href="/assets/css/animate.css">
        <link rel="stylesheet" href="/assets/css/font-awesome.css">
        <link rel="stylesheet" href="/assets/css/nexus.css">
        <link rel="stylesheet" href="/assets/css/responsive.css">
        <link rel="stylesheet" href="/assets/css/custom.css">
        <!-- Google Fonts-->
        <link href="http://fonts.googleapis.com/css?family=Raleway:100,300,400" type="text/css" rel="stylesheet">
        <link href="http://fonts.googleapis.com/css?family=Roboto:400,300" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div id="social" class="visible-lg">
        </div>
        <!-- Header -->
        <div id="header" style="background-position: 50% 0%; height:100%;" data-stellar-background-ratio="0.5">
            <div class="container">
                <div >
                    <h1 class="naslov">Baza semaforizovanih raskrsnica<h1>
                </div>
                <!-- Top Menu -->
                <div id="hornav" class="row text-light">
                    <div class="col-md-12">
                        <div class="text-center visible-lg">
                            <ul id="hornavmenu" class="nav navbar-nav">
                                <li>
                                    <a href="/" class="fa-home active">Početna</a>
                                </li>
                                <li>
                                    <span class="fa-gears ">Unos</span>
                                    <ul>
                                        <li>
                                            <a href="../insert/intersection">Raskrsnica</a>
                                        </li>
                                        <li>
                                            <a href="../insert/trafficsignalcontroller">Upravljački uređaj</a>
                                        </li>
                                        <li>
                                            <a href="../insert/access">Prilaz</a>
                                        </li>
                                        <li>
                                            <a href="../insert/detector">Detector</a>
                                        </li>
                                        <li>
                                            <a href="../insert/pole">Stub</a>
                                        </li>
                                        <li>
                                            <a href="../insert/signalhead">Lanterna</a>
                                        </li>
                                        <li>
                                            <a href="../insert/pedestrianpushbutton">Pešački taster</a>
                                        </li>
                                        <li>
                                            <a href="../insert/pedestriandisplay">Pešački displej</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <span class="fa-copy ">Ažuriranje</span>
                                    <ul>
                                        <li>
                                            <a href="../update/intersection">Raskrsnica</a>
                                        </li>
                                        <li>
                                            <a href="../update/trafficsignalcontroller">Upravljački uređaj</a>
                                        </li>
                                        <li>
                                            <a href="../update/access">Prilaz</a>
                                        </li>
                                        <li>
                                            <a href="../update/detector">Detector</a>
                                        </li>
                                        <li>
                                            <a href="../update/pole">Stub</a>
                                        </li>
                                        <li>
                                            <a href="../update/signalhead">Lanterna</a>
                                        </li>
                                        <li>
                                            <a href="../update/pedestrianpushbutton">Pešački taster</a>
                                        </li>
                                        <li>
                                            <a href="../update/pedestriandisplay">Pešački displej</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <span class="fa-th ">Pregled</span>
                                    <ul>
                                        <li>
                                            <a href="/use/search">Browser</a>
                                        </li>
                                        <li>
                                            <a href="/use/export">Excel fajl</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <sec:authorize access="isAnonymous()">
                                        <a href="/login"><span class="fa-sign-in ">Prijavi se</span></a>
                                    </sec:authorize>
                                    <sec:authorize access="isAuthenticated()">
                                        <div>
                                        <form method="post" action="/logout">
                                            <button id="taster"style="font-size:15px; padding: 14px; border: none; background-color: transparent;"><i class="fa-sign-out"></i> ODJAVI SE</button>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </form>
                                    </div>
                                    </sec:authorize>
                                </li>
                                
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- End Top Menu -->
            </div>
        </div>
        <!-- End Header -->
        <!-- === END HEADER === -->