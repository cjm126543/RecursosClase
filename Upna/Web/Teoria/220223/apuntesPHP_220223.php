<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Examples</title>
    <meta name='viewport' content='width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no'/>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <link href="" rel="stylesheet">
    </head>
<body>

    <?php
        print("hola");
        
        $a = 21;
        if ($a > 20) {
            ?>
                <ol>
                    <li>uno</li>
                    <li>dos</li>
                </ol>
    <?php
        } else {
    ?>
                <ul>
                    <li>tres</li>
                    <li>cuatro</li>
                </ul>
    <?php
        }
    ?>

</body>
</html>