#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=galendar-server

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 어플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."
nohup java -jar \
    -Dspring.config.location=classpath:/application.yml,classpath:/application-$IDLE_PROFILE.yml \
    -Dspring.profiles.active=$IDLE_PROFILE \
    -DEMAIL_PASSWORD="hwvt jjxa qipu jyci" \
    -DEMAIL_USERNAME="siwon0876@gmail.com" \
    -DAWS_ACCESS_KEY="AKIA4J3VX2R65LRGD3X6" \
    -DAWS_SECRET_KEY="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" \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &