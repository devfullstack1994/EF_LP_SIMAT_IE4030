# Imagen base con Tomcat + Java
FROM tomcat:9.0-jdk17

# Eliminar apps por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR al contenedor
COPY target/EF_LP_SIMAT_IE4030-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Exponer puerto
EXPOSE 8080

# Ejecutar Tomcat
CMD ["catalina.sh", "run"]
