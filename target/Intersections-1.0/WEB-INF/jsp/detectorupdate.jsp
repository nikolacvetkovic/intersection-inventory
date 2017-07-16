<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="topNoScroll.jsp" %>
<%@include file="sideMenu.jsp" %>

                            <form class="login-page" action="detector" method="post">
                                <div class="login-header margin-bottom-30">
                                    
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Raskrsnica: <select class="form-unos" name="idInt" onchange="if(this.value!==-1) window.location='./detector?idInt='+this.value">
                                        <option value="-1">Odaberi raskrsnicu</option>
                                        <c:forEach items="${intersections}" var="intersection">
                                            <option <c:if test="${intersection.id==selectedIntersection.id}">selected</c:if> value="${intersection.id}">[&nbsp;${intersection.symbol}&nbsp;] &nbsp; ${intersection.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Prilaz: <select class="form-unos" name="idAcc" onchange="if(this.value!==-1) window.location='./detector?idAcc='+this.value+'&idInt=${selectedIntersection.id}'">
                                        <option value="-1">Odaberi prilaz</option>
                                        <c:forEach items="${accesses}" var="access">
                                            <option <c:if test="${access.id==selectedAccess.id}">selected</c:if> value="${access.id}">[&nbsp;${access.symbol}&nbsp;] &nbsp; ${access.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Detektor: <select class="form-unos" name="idDet" onchange="if(this.value!==-1) window.location='./detector?idDet='+this.value+'&idInt=${selectedIntersection.id}&idAcc=${selectedAccess.id}'">
                                        <option value="-1">Odaberi detektor</option>
                                        <c:forEach items="${detectors}" var="detector">
                                            <option <c:if test="${detector.id==selectedDetector.id}">selected</c:if> value="${detector.id}">&nbsp;${detector.symbol}&nbsp;</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Oznaka detektora: <input class="form-oznaka" type="text" name="symbol" value="${selectedDetector.symbol}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Tip detektora: <input class="form-proiz" type="text" name="type" value="${selectedDetector.type}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Namena detektora: <input class="form-proiz" type="text" name="purpose" value="${selectedDetector.purpose}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Dužina petlje: <input class="form-oznaka" type="text" name="xDimension" value="${selectedDetector.xDimension}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Širina petlje: <input class="form-oznaka" type="text" name="yDimension" value="${selectedDetector.yDimension}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Udaljenost od linije zaustavljanja: <input class="form-oznaka" type="text" name="position" value="${selectedDetector.position}"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    <input class="form-oznaka" type="hidden" name="idDetector" value="${selectedDetector.id}"/>
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