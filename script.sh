IMAGE_NAME="hannoi/hyundai_autoever_itstudy_jeonghan"
TAG="jenkinsgradle-1.0"

# DB 접속 정보는 EC2 호스트의 env 파일에서 주입 (레포에 커밋하지 않음)
ENV_FILE="${ENV_FILE:-$HOME/jenkinsgradle.env}"
if [ ! -f "$ENV_FILE" ]; then
  echo "환경변수 파일이 없습니다: $ENV_FILE"
  echo "DB_HOST / DB_PORT / DB_NAME / DB_USERNAME / DB_PASSWORD 를 작성한 뒤 다시 실행하세요."
  exit 1
fi

#기존 이미지가 있는지 확인
if docker images | grep -q "$IMAGE_NAME"; then
  echo "이미지 $IMAGE_NAME 삭제 중..."
  docker rmi -f "$IMAGE_NAME:$TAG"
fi

#컨테이너도 삭제
docker ps -q -f name=calculator | grep -q . && sudo docker stop calculator

#새로운 이미지 다운로드
echo "이미지 $IMAGE_NAME:$TAG 다운로드 중..."
docker pull "$IMAGE_NAME:$TAG"

docker run -d -p 8082:8082 --rm --name calculator \
  --env-file "$ENV_FILE" \
  "$IMAGE_NAME:$TAG"
docker image prune -f
echo "작업 완료!!!"
