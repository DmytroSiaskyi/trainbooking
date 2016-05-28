<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Статистика | Залізнична каса</title>
</head>
<body>
    <div class="jspBody">
        <h2>Статистика каси</h2>
        <h3>Таблиця проданих квитків касирами</h3>
        <div class="ticketsTable">
            <table border="1" width="100%" cellpadding="5" bgcolor="#7f7f7f">
                <th>Клієнт</th>
                <th>Касир</th>
                <th>Ціна</th>
                <th>Дата</th>
                <tr>
                <c:forEach var="ticket" items="${list1}"><tr>
                    <td>${ticket.client}</td>
                    <td>${ticket.cashier}</td>
                    <td>${ticket.price}</td>
                    <td>${ticket.date}</td>
            </tr></c:forEach>
                </tr>
            </table>
        </div>
        <h3>Таблиця вільних квитків</h3>
        <div class="ticketsTable">
            <table border="1" width="100%" cellpadding="5" bgcolor="#7f7f7f">
                <th>Номер квитка</th>
                <th>Звідки</th>
                <th>Куди</th>
                <th>Ціна</th>
                <th>Місце</th>
                <th>Вагон</th>
                <tr>
                    <c:forEach var="todayTicket" items="${list2}"><tr>
                        <td>${todayTicket.id}</td>
                        <td>${todayTicket.start}</td>
                        <td>${todayTicket.end}</td>
                        <td>${todayTicket.price}</td>
                        <td>${todayTicket.position}</td>
                        <td>${todayTicket.carriage}</td>
                </tr></c:forEach>
                </tr>
            </table>
        </div>
        <h3>Статистика продаж квитків касирами</h3>
        <div class="ticketsTable">
            <table border="1" width="100%" cellpadding="5" bgcolor="#7f7f7f">
                <th>Касир</th>
                <th>Кількість</th>
                <th>Сума</th>
                <tr>
                    <c:forEach var="saleStat" items="${list3}"><tr>
                        <td>${saleStat.cashier}</td>
                        <td>${saleStat.number}</td>
                        <td>${saleStat.sum}</td></tr>
                    </c:forEach>
                </tr>
            </table>
        </div>
        <h3>Статистика покупок квитків пасажирами</h3>
        <div class="ticketsTable">
            <table border="1" width="100%" cellpadding="5" bgcolor="#7f7f7f">
                <th>Клієнт</th>
                <th>Кількість квитків</th>
                <tr>
                    <c:forEach var="clientTickets" items="${list4}"><tr>
                        <td>${clientTickets.client}</td>
                        <td>${clientTickets.count}</td>
                    </tr>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </div>
    <br>
</body>
</html>
