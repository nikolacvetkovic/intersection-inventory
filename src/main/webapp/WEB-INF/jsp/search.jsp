<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="topsearch.jsp" %>
        <!-- === BEGIN CONTENT === -->
        <div id="content">
            <div class="container background-white">
                <div class="container">
                    <div class="row margin-vert-30">
                        <!-- Result Box -->
                            <form class="login-page" action="searchresult" method="post">
                                <div class="login-header margin-bottom-30">
                                    
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Raskrsnica: <select class="form-unos" name="idInt">
                                        <option value="-1">Odaberi raskrsnicu</option>
                                        <c:forEach items="${intersections}" var="intersection">
                                            <option value="${intersection.id}">[&nbsp;${intersection.symbol}&nbsp;] &nbsp; ${intersection.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        
                                    </div>
                                    <div class="col-md-6">
                                        <input class="btn btn-primary pull-right" type="submit" value="Potvrdi"/>
                                    </div>
                                </div>
                                
                            </form>
                        
                        <!-- End Login Box -->
                    </div>
                </div>
            </div>
        </div>
        <!-- === END CONTENT === -->
        <%@include file="bottom1.jsp" %>