<?php
session_start();
require_once("/var/www/html/pruebas_Carlos/controller/homeController.php");
// require_once('C:\xampp\htdocs\proyecto_siw\pruebas_Carlos\controller\homeController.php');

// Verificar si el usuario es admin
if (empty($_SESSION['usuario']) || $_SESSION['usuario'] !== 'admin@admin.com') {
    header("Location: index.php"); // Redirigir si el usuario no es admin
    exit;
}

// Crear instancia del controlador
$controller = new homeController();

// Obtener la lista de usuarios
$usuarios = $controller->obtenerUsuarios();

// Variables para el manejo de errores y mensajes
$error = '';
$success = '';

// Verificar si se ha enviado el formulario de creación de usuario
if ($_SERVER['REQUEST_METHOD'] === 'POST' && !empty($_POST['correo']) && !empty($_POST['contraseña'])) {
    // Obtener los datos del formulario
    $correo = $_POST['correo'];
    $contraseña = $_POST['contraseña'];

    // Validar los datos del formulario
    if (empty($correo) || empty($contraseña)) {
        $error = "Por favor, complete todos los campos.";
    } else {
        // Intentar crear el usuario
        $resultado = $controller->guardarUsuario($correo, $contraseña);
        if ($resultado) {
            $success = "Usuario creado exitosamente.";
        } else {
            $error = "Error al crear el usuario. Inténtelo nuevamente.";
        }
    }
}

// Verificar si se ha enviado el formulario de borrado de usuario
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['usuario_id'])) {
    $usuario_id = $_POST['usuario_id'];
    
    // Llamar al método en el controlador para borrar el usuario
    $resultado = $controller->borrarUsuario($usuario_id);
    
    if ($resultado) {
        $success = "Usuario borrado exitosamente.";
    } else {
        $error = "Error al borrar el usuario. Inténtelo nuevamente.";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <!-- Incluir estilos CSS -->
    <link rel="stylesheet" href="../../asset/css/estilos.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            margin-top: 20px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .alert {
            margin-top: 10px;
        }

        form {
            margin-top: 20px;
            width: 100%;
        }

        label {
            font-weight: bold;
        }

        .input-group {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 10px;
        }

        .input-group label {
            margin-right: 10px;
        }

        .btn-outline-warning {
            font-weight: bold;
            color: #ffc107;
            border-color: #ffc107;
            background-color: #343a40;
            margin-top: 20px;
            margin-bottom: 40px;
            border-radius: 8px;
            padding: 10px 20px;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        .btn-outline-warning:hover {
            color: #000;
            background-color: #ffc107;
        }

        .btn-container {
            display: flex;
            justify-content: center;
            margin-top: 10px;
        }

        .btn-container button {
            margin: 0 10px;
        }
    </style>
</head>
<body>
    <!-- Contenido principal -->
    <h2>Panel de Administración</h2>
    <div class="container mt-5">
        <?php if (!empty($error)) : ?>
            <div class="alert alert-danger" role="alert">
                <?php echo $error; ?>
            </div>
        <?php endif; ?>
        <?php if (!empty($success)) : ?>
            <div class="alert alert-success" role="alert">
                <?php echo $success; ?>
            </div>
        <?php endif; ?>
        <form action="admin.php" method="post">
            <div class="input-group">
                <label for="correo">Correo electrónico:</label>
                <input type="email" name="correo" class="form-control" id="correo" required>
            </div>
            <div class="input-group">
                <label for="contraseña">Contraseña:</label>
                <input type="password" name="contraseña" class="form-control" id="contraseña" required>
            </div>
            <div class="btn-container">
                <button type="submit" class="btn btn-outline-warning">Crear</button>
            </div>
        </form>
    </div>
    <div class="container mt-5">
    <h4>Listado de Usuarios</h4>
    <table class="table">
        <thead>
            <tr>
                <th>Correo Electrónico</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($usuarios as $usuario) : ?>
                <tr>
                    <td><?php echo $usuario['correo']; ?></td>
                    <td>
                        <form action="admin.php" method="post">
                            <input type="hidden" name="usuario_id" value="<?php echo $usuario['id']; ?>">
                            <button type="submit" name="borrar" class="btn btn-outline-danger">Borrar</button>
                        </form>
                    </td>
                </tr>
            <?php endforeach; ?>
        </tbody>
    </table>
    </div>
</body>
</html>
