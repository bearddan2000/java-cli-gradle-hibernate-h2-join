FROM gradle:jdk11

USER root

VOLUME "/root/log"

WORKDIR /app

ADD --chown=gradle:gradle /bin/ .

RUN chmod -R +x *

ENTRYPOINT ["gradle"]

CMD ["run"]
