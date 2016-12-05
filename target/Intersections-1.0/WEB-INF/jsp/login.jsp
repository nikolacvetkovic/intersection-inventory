<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="top1.jsp" %>
        <!-- === BEGIN CONTENT === -->
        <div id="content">
            <div class="container background-white">
                <div class="container">
                    <div class="row margin-vert-30">
                        <!-- Login Box -->
                        <div class="col-md-6 col-md-offset-3 col-sm-offset-3">
                            <form class="login-page" action="log/login" method="post">
                                <div class="login-header margin-bottom-30">
                                    <h2>Prijava</h2>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    <span class="input-group-addon">
                                        <i class="fa fa-user"></i>
                                    </span>
                                    <input placeholder=" Korisničko ime" class="form-control" type="text" name="username">
                                </div>
                                <div class="input-group margin-bottom-20">
                                    <span class="input-group-addon">
                                        <i class="fa fa-lock"></i>
                                    </span>
                                    <input placeholder=" Šifra" class="form-control" type="password" name="password">
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        
                                    </div>
                                    <div class="col-md-6">
                                        <button class="btn btn-primary pull-right" type="submit">Prijavi se</button>
                                    </div>
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
        <%@include file="bottom1.jsp" %>