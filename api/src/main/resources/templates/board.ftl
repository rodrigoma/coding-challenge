<!DOCTYPE html>

<html lang="en">

<style type="text/css">
    .tg  {border-collapse:collapse;border-spacing:0;}
    .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
    .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
    .tg .tg-mxga{font-size:20px;background-color:#34cdf9;vertical-align:top}
    .tg .tg-d7j0{font-size:20px;background-color:#ffcb2f;vertical-align:top}
    .tg .tg-eklo{font-size:20px;vertical-align:top}
    .tg .tg-rwpf{font-size:20px;background-color:#34cdf9}
    .tg .tg-l3th{font-size:20px;background-color:#ffcb2f}
</style>

<body>

Kalah ID: ${kalahId}
<br>
Current Player: ${current}
<br>

<br>
<table class="tg">
    <tr>
        <th class="tg-mxga" colspan="7">${player1.id}</th>
        <th class="tg-eklo"></th>
    </tr>
    <tr>
        <td class="tg-rwpf" rowspan="2">${player1.largePit.stones}</td>
        <#list player1.pits?reverse as pit>
            <td class="tg-rwpf">
                <#if current == "PLAYER_1">
                    <a href="/games/${kalahId}/move/${pit.id}">${pit.stones}</a>
                <#else>
                    ${pit.stones}
                </#if>
            </td>
        </#list>
        <td class="tg-l3th" rowspan="2">${player2.largePit.stones}</td>
    </tr>
    <tr>
        <#list player2.pits as pit>
            <td class="tg-d7j0">
                <#if current == "PLAYER_2">
                    <a href="/games/${kalahId}/move/${pit.id}">${pit.stones}</a>
                <#else>
                    ${pit.stones}
                </#if>
            </td>
        </#list>
    </tr>
    <tr>
        <td class="tg-eklo"></td>
        <td class="tg-d7j0" colspan="7">${player2.id}</td>
    </tr>
</table>

<br><br>
<a href="/games/start">Start New Game</a>
</body>

</html>