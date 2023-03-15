
## Docs



## HTTPS
Ex:  https://127.0.0.1:8443/HelloWorld-web/index.jsf

** server.log  WARN

2023-03-15 07:53:45,983 WARN  [org.jboss.as.domain.management.security] (default I/O-11) WFLYDM0113: Generated self signed certificate at D:\JBOSS\wildfly-21.0.2.Final\standalone\configuration\application.keystore. Please note that self signed certificates are not secure, and should only be used for testing purposes. Do not use this self signed certificate in production.

## Security Headers

https://securityheaders.com/

* HTTP Strict Transport Security
```
/subsystem=undertow/configuration=filter/response-header=hsts-header:add(header-name="Strict-Transport-Security",header-value="max-age=31536000;")
/subsystem=undertow/server=default-server/host=default-host/filter-ref=hsts-header:add

```
HTTP Strict Transport Security was defined as a web security standard in 2012 in RFC 6797. The main goal of creating this standard was to help prevent man-in-the-middle (MITM) attacks that use SSL stripping.
SSL stripping is a technique in which a malicious user forces the browser to connect to a site over HTTP so that it can intercept packets and intercept or modify confidential information. HSTS is also a good way to protect against cookie hijacking.


## How to restrict access to WildFly web application by IP or Host?

