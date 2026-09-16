IMAGE_NAME="hannoi/hyundai_autoever_itstudy_jeonghan"
TAG="jenkinsgradle-1.0"

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

docker run -d -p 8082:8082 --rm --name calculator "$IMAGE_NAME:$TAG"
docker image prune -f
echo "작업 완료!!!"
