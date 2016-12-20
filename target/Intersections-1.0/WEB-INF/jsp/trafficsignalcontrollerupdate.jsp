<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="topNoScroll.jsp" %>
<%@include file="sideMenu.jsp" %>
                    
                            <form class="login-page" action="trafficsignalcontroller" method="post">
                                <div class="login-header margin-bottom-30">
                                    
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Raskrsnica: <select class="form-unos" name="idInt" onchange="if(this.value!==-1) window.location='./trafficsignalcontroller?idInt='+this.value">
                                        <option value="-1">Odaberi raskrsnicu</option>
                                        <c:forEach items="${intersections}" var="intersection">
                                            <option <c:if test="${intersection.id==selectedIntersection.id}">selected</c:if> value="${intersection.id}">[&nbsp;${intersection.symbol}&nbsp;] &nbsp; ${intersection.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Proizvođač uređaja: <input class="form-proiz" type="text" name="manufacturer" value="${selectedIntersection.trafficSignalController.manufacturer}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Model uređaja: <input class="form-proiz" type="text" name="modelCon" value="${selectedIntersection.trafficSignalController.model}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Godina proizvodnje: <input class="form-oznaka" type="text" name="yearOfProduction" value="${selectedIntersection.trafficSignalController.yearOfProduction}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    <input class="form-oznaka" type="hidden" name="idCon" value="${selectedIntersection.trafficSignalController.id}"/>
                                </div>
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        
                                    </div>
                                    <div class="col-md-6">
                                        <input class="btn btn-primary pull-right" type="submit" value="Unesi"/>
                                    </div>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        <!-- End Main Content -->
                    </div>
                    <!-- End Main Column -->
                </div>
            </div>
        </div>
        <!-- === END CONTENT === -->
        <%@include file="bottom.jsp" %>