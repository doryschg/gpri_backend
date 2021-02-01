FROM desaregistry.impuestos.gob.bo/siat_service:java11

COPY cronjobs /etc/crontabs/root
COPY gpri-validacion.jar /opt
COPY gpri-registro.jar /opt

CMD ["crond", "-f"]
