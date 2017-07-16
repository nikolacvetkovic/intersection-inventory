<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="topNoScroll.jsp" %>
        <!-- === BEGIN CONTENT === -->
        <script src="/assets/js/pdfobject.js" type="text/javascript"></script>
        <div id="content">
            <div class="container background-white">
                <div class="container">
                    <div class="row margin-vert-30">
                        <h2 class="margin-bottom-20">[&nbsp;${intersection.symbol}&nbsp;] &nbsp; ${intersection.title}</h2>
                        
                        <h4 class="margin-bottom-20" style="text-align: center;color: black;">Dispozicija</h4>
                        
                        <div id="disposition"></div>
                        <script>PDFObject.embed("/pdf/${intersection.pdf}", "#disposition");</script>
                        <a target="_blank" href="/pdf/${intersection.pdf}">Prikaži crtež na celoj strani</a>
                        
                        <h4 class="margin-bottom-20" style="text-align: center;color: black;">Upravljački uređaj</h4>
                        <table>
                            <thead>
                                <tr>
                                    <th>Proizvođač uređaja</th><th>Model</th><th>Godina proizvodnje</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${tsc.manufacturer}</td><td>${tsc.model}</td><td>${tsc.yearOfProduction}</td>
                                </tr>
                            </tbody>
                        </table>
                        
                        <h4 class="margin-bottom-20" style="text-align: center;color: black;">Detektori</h4>
                        <table>
                            <thead>
                                <tr>
                                    <th>Oznaka prilaza</th><th>Prilaz</th><th>Oznaka detektora</th><th>Namena</th><th>Dimenzije</th><th>Položaj</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${detectors}" var="detector">
                                <tr>
                                    <td>${detector.access.symbol}</td><td>${detector.access.title}</td><td>${detector.symbol}</td><td>${detector.purpose}</td><td>${detector.xDimension} x ${detector.yDimension}</td><td>${detector.position}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        
                        <h4 class="margin-bottom-20" style="text-align: center;color: black;">Stubovi</h4>
                        <table>
                            <thead>
                                <tr>
                                    <th>Oznaka prilaza</th><th>Prilaz</th><th>Oznaka stuba</th><th>Koordinata X</th><th>Koordinata Y</th><th>Tip stuba</th><th>Model stuba</th><th>Proizvođač</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${poles}" var="pole">
                                <tr>
                                    <td>${pole.access.symbol}</td><td>${pole.access.title}</td><td>${pole.symbol}</td><td>${pole.xcoordinate}</td><td>${pole.ycoordinate}</td><td>${pole.type}</td><td>${pole.model}</td><td>${pole.manufacturer}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        
                        <h4 class="margin-bottom-20" style="text-align: center;color: black;">Lanterne</h4>
                        <table>
                            <thead>
                                <tr>
                                    <th>Prilaz</th><th>Oznaka stuba</th><th>Oznaka lanterne</th><th>Tip lanterne</th><th>Hijerarhija</th><th>Dimenzije</th><th>Kontrasna tabla</th><th>Davač zvuka</th><th>Proizvođač</th><th>Model</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${signalheads}" var="signalhead">
                                <tr>
                                    <td>${signalhead.pole.access.title}</td><td>${signalhead.pole.symbol}</td><td>${signalhead.symbol}</td><td>${signalhead.type}</td><td>${signalhead.importance}</td><td>${signalhead.dimension}</td><td>${signalhead.contrasttable}</td><td>${signalhead.sound}</td><td>${signalhead.manufacturer}</td><td>${signalhead.model}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        
                        <h4 class="margin-bottom-20" style="text-align: center;color: black;">Pešački tasteri</h4>
                        <table>
                            <thead>
                                <tr>
                                    <th>Prilaz</th><th>Oznaka stuba</th><th>Oznaka tastera</th><th>Zvučna potvrda najave</th><th>Svetlosna potvrda najave</th><th>Lokator ton</th><th>Proizvođač</th><th>Model</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pedestrianpushbuttons}" var="pedestrianpushbutton">
                                <tr>
                                    <td>${pedestrianpushbutton.pole.access.title}</td><td>${pedestrianpushbutton.pole.symbol}</td><td>${pedestrianpushbutton.symbol}</td><td>${pedestrianpushbutton.sound}</td><td>${pedestrianpushbutton.light}</td><td>${pedestrianpushbutton.locatortone}</td><td>${pedestrianpushbutton.manufacturer}</td><td>${pedestrianpushbutton.model}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        
                        <h4 class="margin-bottom-20" style="text-align: center;color: black;">Pešački displeji</h4>
                        <table>
                            <thead>
                                <tr>
                                    <th>Prilaz</th><th>Oznaka stuba</th><th>Oznaka displeja</th><th>Tip funkcionalnosti</th><th>Proizvođač</th><th>Model</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pedestriandisplays}" var="pedestriandisplay">
                                <tr>
                                    <td>${pedestriandisplay.pole.access.title}</td><td>${pedestriandisplay.pole.symbol}</td><td>${pedestriandisplay.symbol}</td><td>${pedestriandisplay.typefunction}</td><td>${pedestriandisplay.manufacturer}</td><td>${pedestriandisplay.model}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        
                    </div>
                </div>
            </div>
        </div>
        <!-- === END CONTENT === -->
        <%@include file="bottom.jsp" %>