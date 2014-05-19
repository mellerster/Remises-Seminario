<!DOCTYPE html>
<html>
<head>
    <title>Pomodoro Task Manager</title>
    <blueprint:resources/>
    <link href="${resource(dir: 'css', file: 'prueba.css')}" type="text/css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="span-24 last">RemisesOnline Itinerarios</h1>
        <div class="span-24 last">
            <div class="span-16">
                <h2>Itinerarios existentes</h2>
            </div>
            <div class="span-4">
                <ul class="actions"><li>Nuevo</li></ul>
            </div>
            <div class="span-4 last"></div>
        </div>
        <div class="span-20"> 
            <g:render template="itinerarioCard" collection="${itinerarios}" var="itinerario"/>
        </div>
        <div class="span-4 last">
            <dl class="sidebar">
                <dt>Show</dt>
                <dd>
                    <ul>
                        <li>Open</li>
                        <li>Done</li>
                        <li>All</li>
                    </ul>
                </dd>
                <dt>Itinerarios locos</dt>
                <dd>
                    <ul>
                    <g:each in="${ itinerarios }" var="itinerario">
                        <li>${ itinerario.descripcion }</li>
                    </g:each>
                    </ul>
                </dd>
            </dl>
        </div>
    </div>
</body>
</html>
