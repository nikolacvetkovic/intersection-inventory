<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="topNoScroll.jsp" %>
<%@include file="sideMenu.jsp" %>

                            <form class="login-page" action="pedestrianpushbutton" method="post">
                                <div class="login-header margin-bottom-30">
                                    
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Raskrsnica: <select class="form-unos" name="idInt" onchange="if(this.value!==-1) window.location='./pedestrianpushbutton?idInt='+this.value">
                                        <option value="-1">Odaberi raskrsnicu</option>
                                        <c:forEach items="${intersections}" var="intersection">
                                            <option <c:if test="${intersection.id==selectedIntersection.id}">selected</c:if> value="${intersection.id}">[&nbsp;${intersection.symbol}&nbsp;] &nbsp; ${intersection.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Stub: <select class="form-unos" name="idPol" onchange="if(this.value!==-1) window.location='./pedestrianpushbutton?idPol='+this.value+'&idInt=${selectedIntersection.id}'">
                                        <option value="-1">Odaberi stub</option>
                                        <c:forEach items="${poles}" var="pole">
                                            <option <c:if test="${pole.id==selectedPole.id}">selected</c:if> value="${pole.id}">&nbsp;${pole.symbol}&nbsp;</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Taster: <select class="form-unos" name="idPed" onchange="if(this.value!==-1) window.location='./pedestrianpushbutton?idPed='+this.value+'&idInt=${selectedIntersection.id}&idPol=${selectedPole.id}'">
                                        <option value="-1">Odaberi taster</option>
                                        <c:forEach items="${ppbs}" var="ppb">
                                            <option <c:if test="${ppb.id==selectedppb.id}">selected</c:if> value="${ppb.id}">&nbsp;${ppb.symbol}&nbsp;</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Oznaka tastera: <input class="form-oznaka" type="text" name="symbol" value="${selectedppb.symbol}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Zvučna potvrda najave: <input class="form-oznaka" type="text" name="sound" value="${selectedppb.sound}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Svetlosna potvrda najave: <input class="form-oznaka" type="text" name="light" value="${selectedppb.light}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Lokator ton: <input class="form-oznaka" type="text" name="locatortone" value="${selectedppb.locatortone}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Proizvođač: <input class="form-proiz1" type="text" name="manufacturer" value="${selectedppb.manufacturer}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Model: <input class="form-proiz1" type="text" name="modelPed" value="${selectedppb.model}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    <input class="form-proiz1" type="hidden" name="idPedestrian" value="${selectedppb.id}"/>
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