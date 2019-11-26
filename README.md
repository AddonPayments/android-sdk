<a href="https://desarrolladores.addonpayments.com/" target="_blank">
    <img src="https://desarrolladores.addonpayments.com/assets/images/branding/comercia/logo.svg?v=?v=1.14.1" alt="Addon Payments logo" title="Addon Payments" align="right" width="225" />
</a>

# SDK de Android Comercia Global Payments

Este SDK ha sido adaptado por Comercia Global Payments para facilitar las validaciones y la integración de su terminal Addon Payments en su aplicación de Android.

## Soluciones

### General

* Validación de campos del formulario
* Envío de petición vía API mediante el método POST
* Envío de petición JSON vía HPP para la apertura de la pasarela de pagos mediante el método POST
* Minimizar los requisitos de cumplimiento de PCI con las soluciones de HPP
* Cifrado seguro de extremo a extremo

## Requisitos

- Android 4.4+
- Android SDK 15 or later

## Instalación

En la carpeta [clases/validadores](https://github.com/addonpayments/android-sdk/tree/master/clases/validadores) podrá encontrar los archivos java con las funciones para validar los campos de su formulario antes de enviarlo a su servidor.

- AddonPaymentsRemote.java
- AValidateAmexCvn.java
- ValidateCardHolderName.java
- ValidateCardNumber.java
- ValidateCvn.java
- ValidateExpiryDataFormat.java
- ValidateExpiryDateNotInPast.java
- ValidateMesFormat.java
- ValidateYearFormat.java

Si desea implementar la operativa HPP (redirección), las clases para poder enviar la petición se encuentran en la carpeta [clases/HPP](https://github.com/addonpayments/android-sdk/tree/master/clases/HPP).

- Hash.java
- orderID.java

## Documentación y ejemplos

Puede encontrar una documentación adaptada al envío de operaciones por remoto y por redirección, ejecutando el archivo "index.html" desde su servidor.

Este archivo se encuentra dentro de la carpeta "test" del SDK. Si lo prefiere, también puede ver nuestra documentación oficial en la página web de desarrolladores de [Addon Payments](https://desarrolladores.addonpayments.com) donde encontrará además tarjetas con las que realizar pruebas de compra y el resto de librerías disponibles.

*Consejo rápido*: ¡[El código fuente de pruebas incluido](https://github.com/addonpayments/android-sdk/tree/master/test) puede ser una gran fuente de ejemplos de código para usar el SDK! Además proporcionamos un buscador dinámico de errores donde podrá buscar tanto por código como por descripción del error devuelto por el servidor, así como una posible solución.

#### Procesar un pago

Para realizar transacciones desde un cliente Android por remoto, debe enviar las peticiones a su servidor con una de nuestras librerías para procesar las pagos:

- [PHP](https://github.com/AddonPayments/php-sdk)
- [JAVA](https://github.com/AddonPayments/java-sdk)
- [.NET](https://github.com/AddonPayments/net-sdk)

#### Datos de tarjeta de prueba

Nombre      | Número           | Exp Mes   | Exp Año  | CVN
----------- | ---------------- | --------- | -------- | ----
Visa        | 4263970000005262 | 12        | 2025     | 123
MasterCard  | 2223000010005780 | 12        | 2019     | 900
MasterCard  | 5425230000004415 | 12        | 2025     | 123
Discover    | 6011000000000087 | 12        | 2025     | 123
Amex        | 374101000000608  | 12        | 2025     | 1234
JCB         | 3566000000000000 | 12        | 2025     | 123
Diners Club | 36256000000725   | 12        | 2025     | 123

#### Aplicación de ejemplo

Proporcionamos una aplicación de ejemplo que podrá encontrar en la carpeta [app](https://github.com/addonpayments/android-sdk/tree/master/app).

El proyecto de la aplicación lo puede importar en su Android Studio cargando la carpeta "AndroidSDK".

*Para que la operativa API funcione debe modificar la línea del código fuente 201 del archivo "MainActivity.java" e introducir la dirección de su servidor.*

Para obtener más información, puede cargar la documentación en su servidor abriendo el archivo "index.html" que se encuentra en la carpeta [test](https://github.com/addonpayments/android-sdk/tree/master/test).

## Soporte

En caso de que quiera hablar con un especialista de Addon Payments, deberá llamar al teléfono [914 353 028](tel:914353028) o enviar un email a [soporte@addonpayments.com](mailto:soporte@addonpayments.com).

## Contribuyendo

¡Todo nuestro código es de código abierto y animamos a otros desarrolladores a contribuir y ayudar a mejorarlo!

1. Fork it
2. Cree su rama de características (`git checkout -b mi-nueva-feature`)
3. Asegúrese de que las pruebas de SDK son correctas
4. Confirme sus cambios (`git commit -am 'Añadir un commit'`)
5. Empujar a la rama (`git push origin mi-nueva-feature`)
6. Crear una nueva solicitud de extracción

## Licencia

Este proyecto está licenciado bajo la licencia MIT. Consulte el archivo "LICENSE.md" ubicado en la raíz del proyecto para obtener más detalles.
