<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="topNoScroll.jsp" %>
        <!-- === BEGIN CONTENT === -->
        <div id="content">
            <div class="container background-white">
                <div class="container">
                    <div class="row margin-vert-30">
                        <!-- Login Box -->
                        <div class="col-md-6 col-md-offset-3 col-sm-offset-3">
                            <form class="login-page" name="f" action="/login" method="post">
                                <div class="login-header margin-bottom-30">
                                    <c:if test="${LogoutMessage!=null}">
                                        <h3 style="padding-bottom: 20px; text-decoration: underline;">${LogoutMessage}</h3>
                                    </c:if>
                                    <c:if test="${ErrorMessage!=null}">    
                                        <h3 style="padding-bottom: 20px; text-decoration: underline; color: red;">${ErrorMessage}</h3>
                                    </c:if>
                                    <h2>Prijava</h2>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    <span class="input-group-addon">
                                        <i class="fa fa-user"></i>
                                    </span>
                                    <input placeholder=" Korisničko ime" class="form-control" type="text" name="username" />
                                </div>
                                <div class="input-group margin-bottom-20">
                                    <span class="input-group-addon">
                                        <i class="fa fa-lock"></i>
                                    </span>
                                    <input placeholder=" Šifra" class="form-control" type="password" name="password" />
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        
                                    </div>
                                    <div class="col-md-6">
                                        <button class="btn btn-primary pull-right" type="submit">Prijava</button>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </div>
                                <hr>
                            </form>
                        </div>
                        <!-- End Login Box -->
                    </div>
                </div>
            </div>
        </div>
        <!-- === END CONTENT === -->
        <%@include file="bottom.jsp" %>