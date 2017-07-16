<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="topNoScroll.jsp" %>
        <!-- === BEGIN CONTENT === -->
        <div id="content">
            <div class="container background-white">
                <div class="container">
                    <div class="row margin-vert-30">
                        <!-- Login Box -->
                        <div class="col-md-6 col-md-offset-3 col-sm-offset-3">
                            <form style="padding-bottom: 100px;">
                                <div class="login-header margin-bottom-30">
                                    
                                    <h2>Nije Vam dozvoljen pristup.</h2>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    
                                </div>
                                <div class="input-group margin-bottom-20" style="margin-bottom: 130px;">
                                    
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        
                                    </div>
                                    <div class="col-md-6">
                                        <a href="../" style="background-color: #434a55; padding: 5px; color: white;">Nazad na početnu stranu</a>
                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </div>
                            </form>
                        </div>
                        <!-- End Login Box -->
                    </div>
                </div>
            </div>
        </div>
        <!-- === END CONTENT === -->
        <%@include file="bottom.jsp" %>