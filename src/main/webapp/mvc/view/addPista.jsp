    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="ISO-8859-1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
            <title>Nueva pista</title>
        </head>
        <body>
            <div class="container">
                <h1>Crear pista</h1>
                <form>
                    <br/>
                    <label for="pistaName">Nombre</label>
                    <input type="text" class="input-form" name="pistaName">
                    <br/>
                    <label for="pistaType">Tipo</label>
                    <select id="pistaType" class="select-type" name="pistaType">
                        <option value="">Infantil</option>
                        <option value="">Familiar</option>
                        <option value="">Adultos</option>
                    </select>
                    <select id="pistaType" class="select-type" name="pistaType">
                        <option value="">Reservado</option>
                        <option value="">Disponible</option>
                        <option value="">Mantenimiento</option>
                    </select>
                    <br/>
                </body>
                </html>