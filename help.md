	Internationalization:

By default, a Spring Boot application will look for message files containing internationalization keys and values in the src/main/resources folder.
The file for the default locale will have the name messages.properties, and files for each locale will be named messages_XX.properties, where XX is the locale code.
The keys for the values that will be localized have to be the same in every file, with values appropriate to the language they correspond to.

	Static Resources
By default Spring Boot serves static resources defined under the following paths:

/META-INF/resources/
/resources/
/static/
/public/
Except for index.html which is served as the root resource when accessing the root URL of a Spring Boot application, this page need not exist under the above paths.

In order to change the default paths of static resources, you can define “spring.resources.static-locations” attribute under application.properties as the following:

spring.resources.static-locations=/html/,/js/,/css/

	home.html should exist under any of these paths:

src/main/resources/META-INF/resources/home.html
src/main/resources/resources/home.html
src/main/resources/static/home.html
src/main/resources/public/home.html

	CacheControl
	
In Spring Boot, we can set spring.resources.cache-period property to specify the cache period for the static resources in seconds. By default this value is zero. The value set to this property (say n) will cause the response header of Cache-Control:max-age=n to be sent to the browser.
spring.resources.cache-period=10

	maven 

mvn dependency:purge-local-repository