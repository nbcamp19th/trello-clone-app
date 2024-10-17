#  Spring Trello 프로젝트

## 📖 프로젝트 소개
내일배움캠프를 진행하면서 배웠던 Spring에 대한 모든 기능을 적극 활용하여 Trello를 구현합니다.

## ⌚ 프로젝트 기간
* 24.10.14 - 24.10.17

## 📚 기술 스택
### 📋 Languages

### 💻 Developers
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20JPA-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20JWT-FBBA00?style=for-the-badge&logo=Spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![ELK](https://img.shields.io/badge/ELK-%230377CC.svg?style=for-the-badge&)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![AWS S3](https://img.shields.io/badge/AWS%20S3-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)
![YAML](https://img.shields.io/badge/yaml-%23ffffff.svg?style=for-the-badge&logo=yaml&logoColor=151515)
![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white)

### 🎛️ 환경
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![macOS](https://img.shields.io/badge/mac%20os-000000?style=for-the-badge&logo=macos&logoColor=F0F0F0)
![Windows](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)

## 🏠 멤버 구성 및 기능 구현
|                 김나람                  |                 나유화                  |                   정지윤                    |                     조준호                      |                       황호진                        |
|:------------------------------------:|:------------------------------------:|:----------------------------------------:|:--------------------------------------------:|:------------------------------------------------:|
| [@kim-na-ram](https://github.com/kim-na-ram) | [@fargoe](https://github.com/fargoe) | [@jiyumi00](https://github.com/jiyumi00) | [@JUNO0432](https://github.com/JUNO0432) | [@ballqs](https://github.com/ballqs) |

## 🤝 역할 분담
* 김나람 : Spring Security , JWT 인증/인가 , 유저 , 리스트 , CI/CD
* 나유화 : 보드 , ELK
* 정지윤 : 댓글 , 알람(SLACK BOT 활용) , SSE
* 조준호 : 카드 , 담당자 , 인덱싱
* 황호진 : 워크스페이스 , Redis 동시성 제어
  <br>

## 🚩 기능 구현

<details>
<summary>유저</summary>

* 회원가입
* 회원탈퇴
* 로그인
* 권한변경

</details>

<details>
<summary>워크스페이스</summary>

* 워크스페이스 조회
* 워크스페이스 생성
* 워크스페이스 수정
* 워크스페이스 삭제
* 워크스페이스 멤버초대

</details>

<details>
<summary>보드</summary>

* 보드 생성
* 보드 수정
* 보드 다건 조회
* 보드 단건 조회
* 보드 삭제

</details>

<details>
<summary>리스트</summary>

* 리스트 생성
* 리스트 수정
* 리스트 순서 조정
* 리스트 삭제

</details>

<details>
<summary>카드</summary>

* 카드 생성
* 카드 수정
* 카드 다건 조회
* 카드 상세 조회
* 카드 삭제
* 카드 상태 업데이트

</details>

<details>
<summary>담당자</summary>

* 매니저 등록

</details>

<details>
<summary>댓글</summary>

* 댓글 등록
* 댓글 수정
* 댓글 삭제

</details>

## ☁ 와이어프레임
https://www.figma.com/design/qQXpAfUTnn0wtCwxr0giZ5/team19_%ED%94%8C%EB%9F%AC%EC%8A%A4%EC%A3%BC%EC%B0%A8?node-id=0-1&node-type=canvas&t=rBTdZvIv9bdM5NKS-0

## ☁ ERD 다이어그램
![image](https://github.com/user-attachments/assets/d9367381-5f80-4a8a-9b77-db6bbf390802)

## 📑 API 명세서
![image](https://github.com/user-attachments/assets/9fad27cf-ed1d-48ce-ab2a-052c019dfa4c)
![image](https://github.com/user-attachments/assets/bb1529ab-f646-4708-be0d-6fbe4320453b)
![image](https://github.com/user-attachments/assets/c5c221f5-0497-4663-9c44-832ac2875230)
![image](https://github.com/user-attachments/assets/9caf4236-0c3c-46b2-9c95-60e3598d8bdd)
![image](https://github.com/user-attachments/assets/9d90e969-17fd-454c-89b5-dcb3b1a97a91)
![image](https://github.com/user-attachments/assets/1ed70796-c140-4d7a-bedf-714c24cd008e)

## 아키텍쳐

### Redis Cluster Architecture

이 프로젝트에 사용된 Redis는 Cluster를 사용하여 **데이터 분산**, **고가용성**, **동시성 제어**를 구현하였습니다.
Redis 클러스터는 여러 개의 노드로 구성되어 있으며, 각 노드는 서로 다른 데이터의 파티션을 저장합니다. 
이를 통해 데이터 처리 성능을 극대화하고, 장애 발생 시 **Failover**를 통해 데이터를 안전하게 관리합니다.

#### 주요 특징
- **데이터 샤딩**: Redis 클러스터는 데이터를 여러 노드에 분산 저장하여 데이터 저장 및 조회 성능을 향상시킵니다.
- **고가용성**: Master-Slave 구조로 구성된 클러스터에서 Master 노드가 장애가 발생하면 Slave 노드가 자동으로 Master로 승격되어 지속적인 서비스를 보장합니다.
- **자동 Failover**: 노드 간 통신을 통해 노드 상태를 모니터링하고, 장애 시 자동으로 Failover가 발생하여 고가용성을 유지합니다.



#### 주요 설정

```yaml
# Redis 클러스터 설정
spring:
  redis:
    cluster:
      nodes: 127.0.0.1:7000, 127.0.0.1:7001, 127.0.0.1:7002, 127.0.0.1:7003, 127.0.0.1:7004, 127.0.0.1:7005
      max-redirects: 3
```

```java
// RedisConfig 설정
@Bean
public RedisConnectionFactory redisConnectionFactory() {
    List<RedisNode> redisNodes = nodes.stream()
            .map(node -> {
                String[] parts = node.split(":");
                return new RedisNode(parts[0], Integer.parseInt(parts[1]));
            }).toList();
    RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
    redisClusterConfiguration.setClusterNodes(redisNodes);
    return new LettuceConnectionFactory(redisClusterConfiguration);
}

@Bean
public RedissonClient redissonClient() {
    final Config config = new Config();

    ClusterServersConfig csc = config.useClusterServers()
            .setScanInterval(2000)
            .setConnectTimeout(100)
            .setTimeout(3000)
            .setRetryAttempts(3)
            .setRetryInterval(1500);

    nodes.forEach(node -> csc.addNodeAddress("redis://" + node));

    return Redisson.create(config);
}
```

#### 고가용성 테스트
![7](https://github.com/user-attachments/assets/3d4920c0-3de5-4c7e-a4c4-07dec240ca40)
![4](https://github.com/user-attachments/assets/4d65ff0c-d5c1-437f-bc15-cb477d1b72e0)

ELK
S3
SLACK 알림
인덱싱한 내용
Redis

## 트러블슈팅


## 📑 프로젝트 후기

### 김나람
CI를 적용해볼 수 있어서 좋았습니다.

### 나유화
ㅇ[후에휴융헹휴ㅔㅇ휴ㅔㅇ

### 정지윤
SSE 기반의 실시간 통신과 이벤트에 관한 내용을 알게 되었습니다.

### 조준호
많은걸 배워가는 느낌이었습니다. 접근방식과 트러블슈팅과 여러 회의를통해 좋은내용을 알아가는 시간이었습니다.

### 황호진
Redis Cluster를 하면서 많이 힘들었지만 많은 것을 알게 되었습니다. 덕분에 docker-compose 또한 완벽하게 터득할수 있었습니다.