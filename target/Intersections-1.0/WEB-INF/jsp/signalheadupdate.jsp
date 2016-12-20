<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="topNoScroll.jsp" %>
<%@include file="sideMenu.jsp" %>

                            <form class="login-page" action="signalhead" method="post">
                                <div class="login-header margin-bottom-30">
                                    
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Raskrsnica: <select class="form-unos" name="idInt" onchange="if(this.value!==-1) window.location='./signalhead?idInt='+this.value">
                                        <option value="-1">Odaberi raskrsnicu</option>
                                        <c:forEach items="${intersections}" var="intersection">
                                            <option <c:if test="${intersection.id==selectedIntersection.id}">selected</c:if> value="${intersection.id}">[&nbsp;${intersection.symbol}&nbsp;] &nbsp; ${intersection.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Stub: <select class="form-unos" name="idPol" onchange="if(this.value!==-1) window.location='./signalhead?idPol='+this.value+'&idInt=${selectedIntersection.id}'">
                                        <option value="-1">Odaberi stub</option>
                                        <c:forEach items="${poles}" var="pole">
                                            <option <c:if test="${pole.id==selectedPole.id}">selected</c:if> value="${pole.id}">&nbsp;${pole.symbol}&nbsp;</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Lanterna: <select class="form-unos" name="idSig" onchange="if(this.value!==-1) window.location='./signalhead?idSig='+this.value+'&idInt=${selectedIntersection.id}&idPol=${selectedPole.id}'">
                                        <option value="-1">Odaberi lanternu</option>
                                        <c:forEach items="${signalheads}" var="signalhead">
                                            <option <c:if test="${signalhead.id==selectedSignalHead.id}">selected</c:if> value="${signalhead.id}">&nbsp;${signalhead.symbol}&nbsp;</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Oznaka lanterne: <input class="form-oznaka" type="text" name="symbol" value="${selectedSignalHead.symbol}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Tip lanterne: <input class="form-proiz" type="text" name="type" value="${selectedSignalHead.type}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Hijerarhija: <input class="form-proiz1" type="text" name="importance" value="${selectedSignalHead.importance}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Dimenzije: <input class="form-proiz1" type="text" name="dimension" value="${selectedSignalHead.dimension}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Kontrastna tabla: <input class="form-oznaka" type="text" name="contrasttable" value="${selectedSignalHead.contrasttable}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Davač zvuka: <input class="form-oznaka" type="text" name="sound" value="${selectedSignalHead.sound}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Proizvođač: <input class="form-proiz1" type="text" name="manufacturer" value="${selectedSignalHead.manufacturer}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Model lanterne: <input class="form-proiz1" type="text" name="modelSig" value="${selectedSignalHead.model}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    <input class="form-proiz1" type="hidden" name="idSignal" value="${selectedSignalHead.id}"/>
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