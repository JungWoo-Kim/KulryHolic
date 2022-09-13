## <웹 페이지>
#### 1. 로그인 기능 구현
 (예시)
* ID : kurlyholic
* PW : kurlyholic
#### 2. 탭 키 구현(전체-푸드컬리-리빙컬리)
* 푸드컬리는 식품 제품만, 리빙컬리는 비식품 제품만 보여주도록 설정
#### 3. 알림 기능 구현
* 찜한 제품의 가격 인하 알림
* 상품별로 재구매 시기가 다가왔을 때 알림
#### 4. 찜 기능 구현
* 찜 기록 남기도록 구성
#### 5. 추천시스템 구현
* 이 상품 어때요
	: 사용자가 조회, 찜, 구매한 상품과 조회시기에 따라서 그와 관련있는 상품 추천
* 카테고리 추천
	: 사용자가 조회, 찜, 구매한 상품과 사용자의 취향을 기반으로 품목 추천
* 가치소비 상품 추천
	: 미닝스코어 상위에 있는 사람이 많이 구매한 가치소비 상품 추천
* 실시간 베스트 상품 추천
	: 시간대별 판매량이 많은 상품 추천
#### 6. 상품 이미지 클릭 시
* 상품 상세 페이지로 넘어가도록 구현(메인페이지)
* 조회 기록 남기도록 구성
#### 7. 더보기 클릭 시
* 해당 품목 제품 목록 보여줌
* 상품 이미지 클릭 시 상품 상세 페이지로 넘어가도록 구현
#### 8. 메인페이지로 돌아가는 버튼 구현
* 조회기록에 따라 새로운 상품/카테고리 추천
#### 9. 마이페이지 생성
* 주문 내역 목록(재구매 알림 버튼)
* 찜한 상품 목록(가격인하 알림 버튼)


## <실행 명령어 (※ Application 종료 상태 시)>

sh /home/ubuntu/kurly_holic_app_start.sh
위 명령어 시연 시, 아래의 명령어들이 실행되며 서버가 구동됩니다

#### 1. ElasticSearch 
nohup /home/ubuntu/elasticsearch-7.9.0/bin/elasticsearch &
#### 2. Java WEB Application
nohup java -jar /home/ubuntu/kurly_holic_server/marketKulry-0.0.1-SNAPSHOT.war &
#### 3. Python Flask API 
nohup python3 /home/ubuntu/recommender_system/recommendation.py &


## <버전 정보>
- elasticsearch 7.9.0
- jdk 1.8.0
- python 3.9.7
- numpy 1.20.3
- pandas 1.3.4
- tqdm 4.62.3
