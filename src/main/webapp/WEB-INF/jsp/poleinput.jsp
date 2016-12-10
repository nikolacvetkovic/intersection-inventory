<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="topNoScroll.jsp" %>
<%@include file="sideMenu.jsp" %>

                            <form class="login-page" action="poleinput" method="post">
                                <div class="login-header margin-bottom-30">
                                    
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Raskrsnica: <select class="form-unos" name="idInt" onchange="if(this.value!==-1) window.location='./poleinput?idInt='+this.value">
                                        <option value="-1">Odaberi raskrsnicu</option>
                                        <c:forEach items="${intersections}" var="intersection">
                                            <option <c:if test="${intersection.id==selectedIntersection.id}">selected</c:if> value="${intersection.id}">[&nbsp;${intersection.symbol}&nbsp;] &nbsp; ${intersection.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Prilaz: <select class="form-unos" name="idAcc">
                                        <option value="-1">Odaberi prilaz</option>
                                        <c:forEach items="${accesses}" var="access">
                                            <option value="${access.id}">[&nbsp;${access.symbol}&nbsp;] &nbsp; ${access.title}</option>
                                        </c:forEach>
                                                </select>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Oznaka stuba: <input class="form-oznaka" type="text" name="symbol"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Koordinata x: <input class="form-oznaka" type="text" name="xcoordinate"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Koordinata y: <input class="form-oznaka" type="text" name="ycoordinate"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Tip stuba: <input class="form-proiz" type="text" name="type"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Model stuba: <input class="form-proiz" type="text" name="modelPole"/>
                                </div>
                                <div class="input-group margin-bottom-20">
                                    Proizvođač: <input class="form-proiz1" type="text" name="manufacturer"/>
                                </div>
                                    
                                
                                <div class="row">
                                    <div class="col-md-6">
                                        
                                    </div>
                                    <div class="col-md-6">
                                        <input class="btn btn-primary pull-right" type="submit" value="Unesi"/>
                                    </div>
                                </div>
                                
                            </form>
                        
                        <!-- End Main Content -->
                    </div>
                    <!-- End Main Column -->
                </div>
            </div>
        </div>
        <!-- === END CONTENT === -->
        <%@include file="bottom.jsp" %>